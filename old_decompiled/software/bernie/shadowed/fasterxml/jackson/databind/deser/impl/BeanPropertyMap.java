/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.deser.impl;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonProcessingException;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationFeature;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonMappingException;
import software.bernie.shadowed.fasterxml.jackson.databind.PropertyName;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.SettableBeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.util.ClassUtil;
import software.bernie.shadowed.fasterxml.jackson.databind.util.NameTransformer;

public class BeanPropertyMap
implements Iterable<SettableBeanProperty>,
Serializable {
    private static final long serialVersionUID = 2L;
    protected final boolean _caseInsensitive;
    private int _hashMask;
    private int _size;
    private int _spillCount;
    private Object[] _hashArea;
    private SettableBeanProperty[] _propsInOrder;
    private final Map<String, List<PropertyName>> _aliasDefs;
    private final Map<String, String> _aliasMapping;

    public BeanPropertyMap(boolean caseInsensitive, Collection<SettableBeanProperty> props, Map<String, List<PropertyName>> aliasDefs) {
        this._caseInsensitive = caseInsensitive;
        this._propsInOrder = props.toArray(new SettableBeanProperty[props.size()]);
        this._aliasDefs = aliasDefs;
        this._aliasMapping = this._buildAliasMapping(aliasDefs);
        this.init(props);
    }

    @Deprecated
    public BeanPropertyMap(boolean caseInsensitive, Collection<SettableBeanProperty> props) {
        this(caseInsensitive, props, Collections.emptyMap());
    }

    protected BeanPropertyMap(BeanPropertyMap base, boolean caseInsensitive) {
        this._caseInsensitive = caseInsensitive;
        this._aliasDefs = base._aliasDefs;
        this._aliasMapping = base._aliasMapping;
        this._propsInOrder = Arrays.copyOf(base._propsInOrder, base._propsInOrder.length);
        this.init(Arrays.asList(this._propsInOrder));
    }

    public BeanPropertyMap withCaseInsensitivity(boolean state) {
        if (this._caseInsensitive == state) {
            return this;
        }
        return new BeanPropertyMap(this, state);
    }

    protected void init(Collection<SettableBeanProperty> props) {
        this._size = props.size();
        int hashSize = BeanPropertyMap.findSize(this._size);
        this._hashMask = hashSize - 1;
        int alloc = (hashSize + (hashSize >> 1)) * 2;
        Object[] hashed = new Object[alloc];
        int spillCount = 0;
        for (SettableBeanProperty prop : props) {
            if (prop == null) continue;
            String key = this.getPropertyName(prop);
            int slot = this._hashCode(key);
            int ix = slot << 1;
            if (hashed[ix] != null && hashed[ix = hashSize + (slot >> 1) << 1] != null) {
                ix = (hashSize + (hashSize >> 1) << 1) + spillCount;
                spillCount += 2;
                if (ix >= hashed.length) {
                    hashed = Arrays.copyOf(hashed, hashed.length + 4);
                }
            }
            hashed[ix] = key;
            hashed[ix + 1] = prop;
        }
        this._hashArea = hashed;
        this._spillCount = spillCount;
    }

    private static final int findSize(int size) {
        int result;
        if (size <= 5) {
            return 8;
        }
        if (size <= 12) {
            return 16;
        }
        int needed = size + (size >> 2);
        for (result = 32; result < needed; result += result) {
        }
        return result;
    }

    public static BeanPropertyMap construct(Collection<SettableBeanProperty> props, boolean caseInsensitive, Map<String, List<PropertyName>> aliasMapping) {
        return new BeanPropertyMap(caseInsensitive, props, aliasMapping);
    }

    @Deprecated
    public static BeanPropertyMap construct(Collection<SettableBeanProperty> props, boolean caseInsensitive) {
        return BeanPropertyMap.construct(props, caseInsensitive, Collections.emptyMap());
    }

    public BeanPropertyMap withProperty(SettableBeanProperty newProp) {
        String key = this.getPropertyName(newProp);
        int end = this._hashArea.length;
        for (int i2 = 1; i2 < end; i2 += 2) {
            SettableBeanProperty prop = (SettableBeanProperty)this._hashArea[i2];
            if (prop == null || !prop.getName().equals(key)) continue;
            this._hashArea[i2] = newProp;
            this._propsInOrder[this._findFromOrdered((SettableBeanProperty)prop)] = newProp;
            return this;
        }
        int slot = this._hashCode(key);
        int hashSize = this._hashMask + 1;
        int ix = slot << 1;
        if (this._hashArea[ix] != null && this._hashArea[ix = hashSize + (slot >> 1) << 1] != null) {
            ix = (hashSize + (hashSize >> 1) << 1) + this._spillCount;
            this._spillCount += 2;
            if (ix >= this._hashArea.length) {
                this._hashArea = Arrays.copyOf(this._hashArea, this._hashArea.length + 4);
            }
        }
        this._hashArea[ix] = key;
        this._hashArea[ix + 1] = newProp;
        int last = this._propsInOrder.length;
        this._propsInOrder = Arrays.copyOf(this._propsInOrder, last + 1);
        this._propsInOrder[last] = newProp;
        return this;
    }

    public BeanPropertyMap assignIndexes() {
        int index = 0;
        int end = this._hashArea.length;
        for (int i2 = 1; i2 < end; i2 += 2) {
            SettableBeanProperty prop = (SettableBeanProperty)this._hashArea[i2];
            if (prop == null) continue;
            prop.assignIndex(index++);
        }
        return this;
    }

    public BeanPropertyMap renameAll(NameTransformer transformer) {
        if (transformer == null || transformer == NameTransformer.NOP) {
            return this;
        }
        int len = this._propsInOrder.length;
        ArrayList<SettableBeanProperty> newProps = new ArrayList<SettableBeanProperty>(len);
        for (int i2 = 0; i2 < len; ++i2) {
            SettableBeanProperty prop = this._propsInOrder[i2];
            if (prop == null) {
                newProps.add(prop);
                continue;
            }
            newProps.add(this._rename(prop, transformer));
        }
        return new BeanPropertyMap(this._caseInsensitive, newProps, this._aliasDefs);
    }

    public BeanPropertyMap withoutProperties(Collection<String> toExclude) {
        if (toExclude.isEmpty()) {
            return this;
        }
        int len = this._propsInOrder.length;
        ArrayList<SettableBeanProperty> newProps = new ArrayList<SettableBeanProperty>(len);
        for (int i2 = 0; i2 < len; ++i2) {
            SettableBeanProperty prop = this._propsInOrder[i2];
            if (prop == null || toExclude.contains(prop.getName())) continue;
            newProps.add(prop);
        }
        return new BeanPropertyMap(this._caseInsensitive, newProps, this._aliasDefs);
    }

    public void replace(SettableBeanProperty newProp) {
        String key = this.getPropertyName(newProp);
        int ix = this._findIndexInHash(key);
        if (ix < 0) {
            throw new NoSuchElementException("No entry '" + key + "' found, can't replace");
        }
        SettableBeanProperty prop = (SettableBeanProperty)this._hashArea[ix];
        this._hashArea[ix] = newProp;
        this._propsInOrder[this._findFromOrdered((SettableBeanProperty)prop)] = newProp;
    }

    public void remove(SettableBeanProperty propToRm) {
        ArrayList<SettableBeanProperty> props = new ArrayList<SettableBeanProperty>(this._size);
        String key = this.getPropertyName(propToRm);
        boolean found = false;
        int end = this._hashArea.length;
        for (int i2 = 1; i2 < end; i2 += 2) {
            SettableBeanProperty prop = (SettableBeanProperty)this._hashArea[i2];
            if (prop == null) continue;
            if (!found && (found = key.equals(this._hashArea[i2 - 1]))) {
                this._propsInOrder[this._findFromOrdered((SettableBeanProperty)prop)] = null;
                continue;
            }
            props.add(prop);
        }
        if (!found) {
            throw new NoSuchElementException("No entry '" + propToRm.getName() + "' found, can't remove");
        }
        this.init(props);
    }

    public int size() {
        return this._size;
    }

    public boolean isCaseInsensitive() {
        return this._caseInsensitive;
    }

    public boolean hasAliases() {
        return !this._aliasDefs.isEmpty();
    }

    @Override
    public Iterator<SettableBeanProperty> iterator() {
        return this._properties().iterator();
    }

    private List<SettableBeanProperty> _properties() {
        ArrayList<SettableBeanProperty> p2 = new ArrayList<SettableBeanProperty>(this._size);
        int end = this._hashArea.length;
        for (int i2 = 1; i2 < end; i2 += 2) {
            SettableBeanProperty prop = (SettableBeanProperty)this._hashArea[i2];
            if (prop == null) continue;
            p2.add(prop);
        }
        return p2;
    }

    public SettableBeanProperty[] getPropertiesInInsertionOrder() {
        return this._propsInOrder;
    }

    protected final String getPropertyName(SettableBeanProperty prop) {
        return this._caseInsensitive ? prop.getName().toLowerCase() : prop.getName();
    }

    public SettableBeanProperty find(int index) {
        int end = this._hashArea.length;
        for (int i2 = 1; i2 < end; i2 += 2) {
            SettableBeanProperty prop = (SettableBeanProperty)this._hashArea[i2];
            if (prop == null || index != prop.getPropertyIndex()) continue;
            return prop;
        }
        return null;
    }

    public SettableBeanProperty find(String key) {
        int slot;
        int ix;
        Object match;
        if (key == null) {
            throw new IllegalArgumentException("Cannot pass null property name");
        }
        if (this._caseInsensitive) {
            key = key.toLowerCase();
        }
        if ((match = this._hashArea[ix = (slot = key.hashCode() & this._hashMask) << 1]) == key || key.equals(match)) {
            return (SettableBeanProperty)this._hashArea[ix + 1];
        }
        return this._find2(key, slot, match);
    }

    private final SettableBeanProperty _find2(String key, int slot, Object match) {
        if (match == null) {
            return this._findWithAlias(this._aliasMapping.get(key));
        }
        int hashSize = this._hashMask + 1;
        int ix = hashSize + (slot >> 1) << 1;
        match = this._hashArea[ix];
        if (key.equals(match)) {
            return (SettableBeanProperty)this._hashArea[ix + 1];
        }
        if (match != null) {
            int i2;
            int end = i2 + this._spillCount;
            for (i2 = hashSize + (hashSize >> 1) << 1; i2 < end; i2 += 2) {
                match = this._hashArea[i2];
                if (match != key && !key.equals(match)) continue;
                return (SettableBeanProperty)this._hashArea[i2 + 1];
            }
        }
        return this._findWithAlias(this._aliasMapping.get(key));
    }

    private SettableBeanProperty _findWithAlias(String keyFromAlias) {
        if (keyFromAlias == null) {
            return null;
        }
        int slot = this._hashCode(keyFromAlias);
        int ix = slot << 1;
        Object match = this._hashArea[ix];
        if (keyFromAlias.equals(match)) {
            return (SettableBeanProperty)this._hashArea[ix + 1];
        }
        if (match == null) {
            return null;
        }
        return this._find2ViaAlias(keyFromAlias, slot, match);
    }

    private SettableBeanProperty _find2ViaAlias(String key, int slot, Object match) {
        int hashSize = this._hashMask + 1;
        int ix = hashSize + (slot >> 1) << 1;
        match = this._hashArea[ix];
        if (key.equals(match)) {
            return (SettableBeanProperty)this._hashArea[ix + 1];
        }
        if (match != null) {
            int i2;
            int end = i2 + this._spillCount;
            for (i2 = hashSize + (hashSize >> 1) << 1; i2 < end; i2 += 2) {
                match = this._hashArea[i2];
                if (match != key && !key.equals(match)) continue;
                return (SettableBeanProperty)this._hashArea[i2 + 1];
            }
        }
        return null;
    }

    public boolean findDeserializeAndSet(JsonParser p2, DeserializationContext ctxt, Object bean, String key) throws IOException {
        SettableBeanProperty prop = this.find(key);
        if (prop == null) {
            return false;
        }
        try {
            prop.deserializeAndSet(p2, ctxt, bean);
        }
        catch (Exception e10) {
            this.wrapAndThrow(e10, bean, key, ctxt);
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Properties=[");
        int count = 0;
        for (SettableBeanProperty prop : this) {
            if (count++ > 0) {
                sb.append(", ");
            }
            sb.append(prop.getName());
            sb.append('(');
            sb.append(prop.getType());
            sb.append(')');
        }
        sb.append(']');
        if (!this._aliasDefs.isEmpty()) {
            sb.append("(aliases: ");
            sb.append(this._aliasDefs);
            sb.append(")");
        }
        return sb.toString();
    }

    protected SettableBeanProperty _rename(SettableBeanProperty prop, NameTransformer xf) {
        JsonDeserializer<Object> newDeser;
        if (prop == null) {
            return prop;
        }
        String newName = xf.transform(prop.getName());
        JsonDeserializer<Object> deser = (prop = prop.withSimpleName(newName)).getValueDeserializer();
        if (deser != null && (newDeser = deser.unwrappingDeserializer(xf)) != deser) {
            prop = prop.withValueDeserializer(newDeser);
        }
        return prop;
    }

    protected void wrapAndThrow(Throwable t2, Object bean, String fieldName, DeserializationContext ctxt) throws IOException {
        boolean wrap;
        while (t2 instanceof InvocationTargetException && t2.getCause() != null) {
            t2 = t2.getCause();
        }
        ClassUtil.throwIfError(t2);
        boolean bl2 = wrap = ctxt == null || ctxt.isEnabled(DeserializationFeature.WRAP_EXCEPTIONS);
        if (t2 instanceof IOException) {
            if (!wrap || !(t2 instanceof JsonProcessingException)) {
                throw (IOException)t2;
            }
        } else if (!wrap) {
            ClassUtil.throwIfRTE(t2);
        }
        throw JsonMappingException.wrapWithPath(t2, bean, fieldName);
    }

    private final int _findIndexInHash(String key) {
        int i2;
        int slot = this._hashCode(key);
        int ix = slot << 1;
        if (key.equals(this._hashArea[ix])) {
            return ix + 1;
        }
        int hashSize = this._hashMask + 1;
        ix = hashSize + (slot >> 1) << 1;
        if (key.equals(this._hashArea[ix])) {
            return ix + 1;
        }
        int end = i2 + this._spillCount;
        for (i2 = hashSize + (hashSize >> 1) << 1; i2 < end; i2 += 2) {
            if (!key.equals(this._hashArea[i2])) continue;
            return i2 + 1;
        }
        return -1;
    }

    private final int _findFromOrdered(SettableBeanProperty prop) {
        int end = this._propsInOrder.length;
        for (int i2 = 0; i2 < end; ++i2) {
            if (this._propsInOrder[i2] != prop) continue;
            return i2;
        }
        throw new IllegalStateException("Illegal state: property '" + prop.getName() + "' missing from _propsInOrder");
    }

    private final int _hashCode(String key) {
        return key.hashCode() & this._hashMask;
    }

    private Map<String, String> _buildAliasMapping(Map<String, List<PropertyName>> defs) {
        if (defs == null || defs.isEmpty()) {
            return Collections.emptyMap();
        }
        HashMap<String, String> aliases = new HashMap<String, String>();
        for (Map.Entry<String, List<PropertyName>> entry : defs.entrySet()) {
            String key = entry.getKey();
            if (this._caseInsensitive) {
                key = key.toLowerCase();
            }
            for (PropertyName pn : entry.getValue()) {
                String mapped = pn.getSimpleName();
                if (this._caseInsensitive) {
                    mapped = mapped.toLowerCase();
                }
                aliases.put(mapped, key);
            }
        }
        return aliases;
    }
}

