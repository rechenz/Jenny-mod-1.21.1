/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.ser.impl;

import java.io.IOException;
import java.util.Set;
import software.bernie.shadowed.fasterxml.jackson.core.JsonGenerator;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.core.type.WritableTypeId;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonMappingException;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializationFeature;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializerProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.impl.ObjectIdWriter;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.std.BeanSerializerBase;
import software.bernie.shadowed.fasterxml.jackson.databind.util.NameTransformer;

public class BeanAsArraySerializer
extends BeanSerializerBase {
    private static final long serialVersionUID = 1L;
    protected final BeanSerializerBase _defaultSerializer;

    public BeanAsArraySerializer(BeanSerializerBase src) {
        super(src, (ObjectIdWriter)null);
        this._defaultSerializer = src;
    }

    protected BeanAsArraySerializer(BeanSerializerBase src, Set<String> toIgnore) {
        super(src, toIgnore);
        this._defaultSerializer = src;
    }

    protected BeanAsArraySerializer(BeanSerializerBase src, ObjectIdWriter oiw, Object filterId) {
        super(src, oiw, filterId);
        this._defaultSerializer = src;
    }

    @Override
    public JsonSerializer<Object> unwrappingSerializer(NameTransformer transformer) {
        return this._defaultSerializer.unwrappingSerializer(transformer);
    }

    @Override
    public boolean isUnwrappingSerializer() {
        return false;
    }

    @Override
    public BeanSerializerBase withObjectIdWriter(ObjectIdWriter objectIdWriter) {
        return this._defaultSerializer.withObjectIdWriter(objectIdWriter);
    }

    @Override
    public BeanSerializerBase withFilterId(Object filterId) {
        return new BeanAsArraySerializer((BeanSerializerBase)this, this._objectIdWriter, filterId);
    }

    @Override
    protected BeanAsArraySerializer withIgnorals(Set<String> toIgnore) {
        return new BeanAsArraySerializer((BeanSerializerBase)this, toIgnore);
    }

    @Override
    protected BeanSerializerBase asArraySerializer() {
        return this;
    }

    @Override
    public void serializeWithType(Object bean, JsonGenerator gen, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
        if (this._objectIdWriter != null) {
            this._serializeWithObjectId(bean, gen, provider, typeSer);
            return;
        }
        gen.setCurrentValue(bean);
        WritableTypeId typeIdDef = this._typeIdDef(typeSer, bean, JsonToken.START_ARRAY);
        typeSer.writeTypePrefix(gen, typeIdDef);
        this.serializeAsArray(bean, gen, provider);
        typeSer.writeTypeSuffix(gen, typeIdDef);
    }

    @Override
    public final void serialize(Object bean, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (provider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED) && this.hasSingleElement(provider)) {
            this.serializeAsArray(bean, gen, provider);
            return;
        }
        gen.writeStartArray();
        gen.setCurrentValue(bean);
        this.serializeAsArray(bean, gen, provider);
        gen.writeEndArray();
    }

    private boolean hasSingleElement(SerializerProvider provider) {
        BeanPropertyWriter[] props = this._filteredProps != null && provider.getActiveView() != null ? this._filteredProps : this._props;
        return props.length == 1;
    }

    protected final void serializeAsArray(Object bean, JsonGenerator gen, SerializerProvider provider) throws IOException {
        int i2;
        BeanPropertyWriter[] props = this._filteredProps != null && provider.getActiveView() != null ? this._filteredProps : this._props;
        try {
            for (BeanPropertyWriter prop : props) {
                if (prop == null) {
                    gen.writeNull();
                    continue;
                }
                prop.serializeAsElement(bean, gen, provider);
            }
        }
        catch (Exception e10) {
            String name = i2 == props.length ? "[anySetter]" : props[i2].getName();
            this.wrapAndThrow(provider, (Throwable)e10, bean, name);
        }
        catch (StackOverflowError e11) {
            JsonMappingException mapE = JsonMappingException.from(gen, "Infinite recursion (StackOverflowError)", (Throwable)e11);
            String name = i2 == props.length ? "[anySetter]" : props[i2].getName();
            mapE.prependPath(new JsonMappingException.Reference(bean, name));
            throw mapE;
        }
    }

    public String toString() {
        return "BeanAsArraySerializer for " + this.handledType().getName();
    }
}

