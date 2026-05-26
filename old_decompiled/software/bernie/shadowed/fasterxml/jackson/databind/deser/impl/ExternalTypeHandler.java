/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.deser.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationFeature;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.SettableBeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.BeanPropertyMap;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.PropertyValueBuffer;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.util.TokenBuffer;

public class ExternalTypeHandler {
    private final JavaType _beanType;
    private final ExtTypedProperty[] _properties;
    private final Map<String, Object> _nameToPropertyIndex;
    private final String[] _typeIds;
    private final TokenBuffer[] _tokens;

    protected ExternalTypeHandler(JavaType beanType, ExtTypedProperty[] properties, Map<String, Object> nameToPropertyIndex, String[] typeIds, TokenBuffer[] tokens) {
        this._beanType = beanType;
        this._properties = properties;
        this._nameToPropertyIndex = nameToPropertyIndex;
        this._typeIds = typeIds;
        this._tokens = tokens;
    }

    protected ExternalTypeHandler(ExternalTypeHandler h2) {
        this._beanType = h2._beanType;
        this._properties = h2._properties;
        this._nameToPropertyIndex = h2._nameToPropertyIndex;
        int len = this._properties.length;
        this._typeIds = new String[len];
        this._tokens = new TokenBuffer[len];
    }

    public static Builder builder(JavaType beanType) {
        return new Builder(beanType);
    }

    public ExternalTypeHandler start() {
        return new ExternalTypeHandler(this);
    }

    public boolean handleTypePropertyValue(JsonParser p2, DeserializationContext ctxt, String propName, Object bean) throws IOException {
        Object ob = this._nameToPropertyIndex.get(propName);
        if (ob == null) {
            return false;
        }
        String typeId = p2.getText();
        if (ob instanceof List) {
            boolean result = false;
            for (Integer index : (List)ob) {
                if (!this._handleTypePropertyValue(p2, ctxt, propName, bean, typeId, index)) continue;
                result = true;
            }
            return result;
        }
        return this._handleTypePropertyValue(p2, ctxt, propName, bean, typeId, (Integer)ob);
    }

    private final boolean _handleTypePropertyValue(JsonParser p2, DeserializationContext ctxt, String propName, Object bean, String typeId, int index) throws IOException {
        boolean canDeserialize;
        ExtTypedProperty prop = this._properties[index];
        if (!prop.hasTypePropertyName(propName)) {
            return false;
        }
        boolean bl2 = canDeserialize = bean != null && this._tokens[index] != null;
        if (canDeserialize) {
            this._deserializeAndSet(p2, ctxt, bean, index, typeId);
            this._tokens[index] = null;
        } else {
            this._typeIds[index] = typeId;
        }
        return true;
    }

    public boolean handlePropertyValue(JsonParser p2, DeserializationContext ctxt, String propName, Object bean) throws IOException {
        boolean canDeserialize;
        Object ob = this._nameToPropertyIndex.get(propName);
        if (ob == null) {
            return false;
        }
        if (ob instanceof List) {
            Iterator it = ((List)ob).iterator();
            Integer index = (Integer)it.next();
            ExtTypedProperty prop = this._properties[index];
            if (prop.hasTypePropertyName(propName)) {
                String typeId = p2.getText();
                p2.skipChildren();
                this._typeIds[index.intValue()] = typeId;
                while (it.hasNext()) {
                    this._typeIds[((Integer)it.next()).intValue()] = typeId;
                }
            } else {
                TokenBuffer tokens = new TokenBuffer(p2, ctxt);
                tokens.copyCurrentStructure(p2);
                this._tokens[index.intValue()] = tokens;
                while (it.hasNext()) {
                    this._tokens[((Integer)it.next()).intValue()] = tokens;
                }
            }
            return true;
        }
        int index = (Integer)ob;
        ExtTypedProperty prop = this._properties[index];
        if (prop.hasTypePropertyName(propName)) {
            this._typeIds[index] = p2.getText();
            p2.skipChildren();
            canDeserialize = bean != null && this._tokens[index] != null;
        } else {
            TokenBuffer tokens = new TokenBuffer(p2, ctxt);
            tokens.copyCurrentStructure(p2);
            this._tokens[index] = tokens;
            boolean bl2 = canDeserialize = bean != null && this._typeIds[index] != null;
        }
        if (canDeserialize) {
            String typeId = this._typeIds[index];
            this._typeIds[index] = null;
            this._deserializeAndSet(p2, ctxt, bean, index, typeId);
            this._tokens[index] = null;
        }
        return true;
    }

    public Object complete(JsonParser p2, DeserializationContext ctxt, Object bean) throws IOException {
        int len = this._properties.length;
        for (int i2 = 0; i2 < len; ++i2) {
            String typeId = this._typeIds[i2];
            if (typeId == null) {
                TokenBuffer tokens = this._tokens[i2];
                if (tokens == null) continue;
                JsonToken t2 = tokens.firstToken();
                if (t2.isScalarValue()) {
                    JsonParser buffered = tokens.asParser(p2);
                    buffered.nextToken();
                    SettableBeanProperty extProp = this._properties[i2].getProperty();
                    Object result = TypeDeserializer.deserializeIfNatural(buffered, ctxt, extProp.getType());
                    if (result != null) {
                        extProp.set(bean, result);
                        continue;
                    }
                    if (!this._properties[i2].hasDefaultType()) {
                        ctxt.reportInputMismatch(bean.getClass(), "Missing external type id property '%s'", this._properties[i2].getTypePropertyName());
                    } else {
                        typeId = this._properties[i2].getDefaultTypeId();
                    }
                }
            } else if (this._tokens[i2] == null) {
                SettableBeanProperty prop = this._properties[i2].getProperty();
                if (prop.isRequired() || ctxt.isEnabled(DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY)) {
                    ctxt.reportInputMismatch(bean.getClass(), "Missing property '%s' for external type id '%s'", prop.getName(), this._properties[i2].getTypePropertyName());
                }
                return bean;
            }
            this._deserializeAndSet(p2, ctxt, bean, i2, typeId);
        }
        return bean;
    }

    public Object complete(JsonParser p2, DeserializationContext ctxt, PropertyValueBuffer buffer, PropertyBasedCreator creator) throws IOException {
        int len = this._properties.length;
        Object[] values = new Object[len];
        for (int i2 = 0; i2 < len; ++i2) {
            SettableBeanProperty prop;
            String typeId = this._typeIds[i2];
            ExtTypedProperty extProp = this._properties[i2];
            if (typeId == null) {
                if (this._tokens[i2] == null) continue;
                if (!extProp.hasDefaultType()) {
                    ctxt.reportInputMismatch(this._beanType, "Missing external type id property '%s'", extProp.getTypePropertyName());
                } else {
                    typeId = extProp.getDefaultTypeId();
                }
            } else if (this._tokens[i2] == null) {
                prop = extProp.getProperty();
                ctxt.reportInputMismatch(this._beanType, "Missing property '%s' for external type id '%s'", prop.getName(), this._properties[i2].getTypePropertyName());
            }
            values[i2] = this._deserialize(p2, ctxt, i2, typeId);
            prop = extProp.getProperty();
            if (prop.getCreatorIndex() < 0) continue;
            buffer.assignParameter(prop, values[i2]);
            SettableBeanProperty typeProp = extProp.getTypeProperty();
            if (typeProp == null || typeProp.getCreatorIndex() < 0) continue;
            buffer.assignParameter(typeProp, typeId);
        }
        Object bean = creator.build(ctxt, buffer);
        for (int i3 = 0; i3 < len; ++i3) {
            SettableBeanProperty prop = this._properties[i3].getProperty();
            if (prop.getCreatorIndex() >= 0) continue;
            prop.set(bean, values[i3]);
        }
        return bean;
    }

    protected final Object _deserialize(JsonParser p2, DeserializationContext ctxt, int index, String typeId) throws IOException {
        JsonParser p22 = this._tokens[index].asParser(p2);
        JsonToken t2 = p22.nextToken();
        if (t2 == JsonToken.VALUE_NULL) {
            return null;
        }
        TokenBuffer merged = new TokenBuffer(p2, ctxt);
        merged.writeStartArray();
        merged.writeString(typeId);
        merged.copyCurrentStructure(p22);
        merged.writeEndArray();
        JsonParser mp = merged.asParser(p2);
        mp.nextToken();
        return this._properties[index].getProperty().deserialize(mp, ctxt);
    }

    protected final void _deserializeAndSet(JsonParser p2, DeserializationContext ctxt, Object bean, int index, String typeId) throws IOException {
        JsonParser p22 = this._tokens[index].asParser(p2);
        JsonToken t2 = p22.nextToken();
        if (t2 == JsonToken.VALUE_NULL) {
            this._properties[index].getProperty().set(bean, null);
            return;
        }
        TokenBuffer merged = new TokenBuffer(p2, ctxt);
        merged.writeStartArray();
        merged.writeString(typeId);
        merged.copyCurrentStructure(p22);
        merged.writeEndArray();
        JsonParser mp = merged.asParser(p2);
        mp.nextToken();
        this._properties[index].getProperty().deserializeAndSet(mp, ctxt, bean);
    }

    private static final class ExtTypedProperty {
        private final SettableBeanProperty _property;
        private final TypeDeserializer _typeDeserializer;
        private final String _typePropertyName;
        private SettableBeanProperty _typeProperty;

        public ExtTypedProperty(SettableBeanProperty property, TypeDeserializer typeDeser) {
            this._property = property;
            this._typeDeserializer = typeDeser;
            this._typePropertyName = typeDeser.getPropertyName();
        }

        public void linkTypeProperty(SettableBeanProperty p2) {
            this._typeProperty = p2;
        }

        public boolean hasTypePropertyName(String n2) {
            return n2.equals(this._typePropertyName);
        }

        public boolean hasDefaultType() {
            return this._typeDeserializer.getDefaultImpl() != null;
        }

        public String getDefaultTypeId() {
            Class<?> defaultType = this._typeDeserializer.getDefaultImpl();
            if (defaultType == null) {
                return null;
            }
            return this._typeDeserializer.getTypeIdResolver().idFromValueAndType(null, defaultType);
        }

        public String getTypePropertyName() {
            return this._typePropertyName;
        }

        public SettableBeanProperty getProperty() {
            return this._property;
        }

        public SettableBeanProperty getTypeProperty() {
            return this._typeProperty;
        }
    }

    public static class Builder {
        private final JavaType _beanType;
        private final List<ExtTypedProperty> _properties = new ArrayList<ExtTypedProperty>();
        private final Map<String, Object> _nameToPropertyIndex = new HashMap<String, Object>();

        protected Builder(JavaType t2) {
            this._beanType = t2;
        }

        public void addExternal(SettableBeanProperty property, TypeDeserializer typeDeser) {
            Integer index = this._properties.size();
            this._properties.add(new ExtTypedProperty(property, typeDeser));
            this._addPropertyIndex(property.getName(), index);
            this._addPropertyIndex(typeDeser.getPropertyName(), index);
        }

        private void _addPropertyIndex(String name, Integer index) {
            Object ob = this._nameToPropertyIndex.get(name);
            if (ob == null) {
                this._nameToPropertyIndex.put(name, index);
            } else if (ob instanceof List) {
                List list = (List)ob;
                list.add(index);
            } else {
                LinkedList<Object> list = new LinkedList<Object>();
                list.add(ob);
                list.add(index);
                this._nameToPropertyIndex.put(name, list);
            }
        }

        public ExternalTypeHandler build(BeanPropertyMap otherProps) {
            int len = this._properties.size();
            ExtTypedProperty[] extProps = new ExtTypedProperty[len];
            for (int i2 = 0; i2 < len; ++i2) {
                ExtTypedProperty extProp = this._properties.get(i2);
                String typePropId = extProp.getTypePropertyName();
                SettableBeanProperty typeProp = otherProps.find(typePropId);
                if (typeProp != null) {
                    extProp.linkTypeProperty(typeProp);
                }
                extProps[i2] = extProp;
            }
            return new ExternalTypeHandler(this._beanType, extProps, this._nameToPropertyIndex, null, null);
        }
    }
}

