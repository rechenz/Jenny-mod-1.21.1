/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.deser.impl;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationConfig;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonMappingException;
import software.bernie.shadowed.fasterxml.jackson.databind.MapperFeature;
import software.bernie.shadowed.fasterxml.jackson.databind.PropertyName;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.SettableBeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.ValueInstantiator;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.BeanPropertyMap;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.PropertyValue;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.PropertyValueBuffer;

public final class PropertyBasedCreator {
    protected final int _propertyCount;
    protected final ValueInstantiator _valueInstantiator;
    protected final HashMap<String, SettableBeanProperty> _propertyLookup;
    protected final SettableBeanProperty[] _allProperties;

    protected PropertyBasedCreator(DeserializationContext ctxt, ValueInstantiator valueInstantiator, SettableBeanProperty[] creatorProps, boolean caseInsensitive, boolean addAliases) {
        int len;
        this._valueInstantiator = valueInstantiator;
        this._propertyLookup = caseInsensitive ? new CaseInsensitiveMap() : new HashMap();
        this._propertyCount = len = creatorProps.length;
        this._allProperties = new SettableBeanProperty[len];
        if (addAliases) {
            DeserializationConfig config = ctxt.getConfig();
            for (SettableBeanProperty prop : creatorProps) {
                List<PropertyName> aliases = prop.findAliases(config);
                if (aliases.isEmpty()) continue;
                for (PropertyName pn : aliases) {
                    this._propertyLookup.put(pn.getSimpleName(), prop);
                }
            }
        }
        for (int i2 = 0; i2 < len; ++i2) {
            SettableBeanProperty prop;
            this._allProperties[i2] = prop = creatorProps[i2];
            this._propertyLookup.put(prop.getName(), prop);
        }
    }

    public static PropertyBasedCreator construct(DeserializationContext ctxt, ValueInstantiator valueInstantiator, SettableBeanProperty[] srcCreatorProps, BeanPropertyMap allProperties) throws JsonMappingException {
        int len = srcCreatorProps.length;
        SettableBeanProperty[] creatorProps = new SettableBeanProperty[len];
        for (int i2 = 0; i2 < len; ++i2) {
            SettableBeanProperty prop = srcCreatorProps[i2];
            if (!prop.hasValueDeserializer()) {
                prop = prop.withValueDeserializer(ctxt.findContextualValueDeserializer(prop.getType(), prop));
            }
            creatorProps[i2] = prop;
        }
        return new PropertyBasedCreator(ctxt, valueInstantiator, creatorProps, allProperties.isCaseInsensitive(), allProperties.hasAliases());
    }

    public static PropertyBasedCreator construct(DeserializationContext ctxt, ValueInstantiator valueInstantiator, SettableBeanProperty[] srcCreatorProps, boolean caseInsensitive) throws JsonMappingException {
        int len = srcCreatorProps.length;
        SettableBeanProperty[] creatorProps = new SettableBeanProperty[len];
        for (int i2 = 0; i2 < len; ++i2) {
            SettableBeanProperty prop = srcCreatorProps[i2];
            if (!prop.hasValueDeserializer()) {
                prop = prop.withValueDeserializer(ctxt.findContextualValueDeserializer(prop.getType(), prop));
            }
            creatorProps[i2] = prop;
        }
        return new PropertyBasedCreator(ctxt, valueInstantiator, creatorProps, caseInsensitive, false);
    }

    @Deprecated
    public static PropertyBasedCreator construct(DeserializationContext ctxt, ValueInstantiator valueInstantiator, SettableBeanProperty[] srcCreatorProps) throws JsonMappingException {
        return PropertyBasedCreator.construct(ctxt, valueInstantiator, srcCreatorProps, ctxt.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES));
    }

    public Collection<SettableBeanProperty> properties() {
        return this._propertyLookup.values();
    }

    public SettableBeanProperty findCreatorProperty(String name) {
        return this._propertyLookup.get(name);
    }

    public SettableBeanProperty findCreatorProperty(int propertyIndex) {
        for (SettableBeanProperty prop : this._propertyLookup.values()) {
            if (prop.getPropertyIndex() != propertyIndex) continue;
            return prop;
        }
        return null;
    }

    public PropertyValueBuffer startBuilding(JsonParser p2, DeserializationContext ctxt, ObjectIdReader oir) {
        return new PropertyValueBuffer(p2, ctxt, this._propertyCount, oir);
    }

    public Object build(DeserializationContext ctxt, PropertyValueBuffer buffer) throws IOException {
        Object bean = this._valueInstantiator.createFromObjectWith(ctxt, this._allProperties, buffer);
        if (bean != null) {
            bean = buffer.handleIdValue(ctxt, bean);
            PropertyValue pv = buffer.buffered();
            while (pv != null) {
                pv.assign(bean);
                pv = pv.next;
            }
        }
        return bean;
    }

    static class CaseInsensitiveMap
    extends HashMap<String, SettableBeanProperty> {
        private static final long serialVersionUID = 1L;

        CaseInsensitiveMap() {
        }

        @Override
        public SettableBeanProperty get(Object key0) {
            return (SettableBeanProperty)super.get(((String)key0).toLowerCase());
        }

        @Override
        public SettableBeanProperty put(String key, SettableBeanProperty value) {
            key = key.toLowerCase();
            return super.put(key, value);
        }
    }
}

