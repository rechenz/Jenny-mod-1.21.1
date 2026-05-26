/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.deser.std;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ArrayBlockingQueue;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.NullValueProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.ValueInstantiator;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.std.CollectionDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeDeserializer;

public class ArrayBlockingQueueDeserializer
extends CollectionDeserializer {
    private static final long serialVersionUID = 1L;

    public ArrayBlockingQueueDeserializer(JavaType containerType, JsonDeserializer<Object> valueDeser, TypeDeserializer valueTypeDeser, ValueInstantiator valueInstantiator) {
        super(containerType, valueDeser, valueTypeDeser, valueInstantiator);
    }

    protected ArrayBlockingQueueDeserializer(JavaType containerType, JsonDeserializer<Object> valueDeser, TypeDeserializer valueTypeDeser, ValueInstantiator valueInstantiator, JsonDeserializer<Object> delegateDeser, NullValueProvider nuller, Boolean unwrapSingle) {
        super(containerType, valueDeser, valueTypeDeser, valueInstantiator, delegateDeser, nuller, unwrapSingle);
    }

    protected ArrayBlockingQueueDeserializer(ArrayBlockingQueueDeserializer src) {
        super(src);
    }

    @Override
    protected ArrayBlockingQueueDeserializer withResolved(JsonDeserializer<?> dd2, JsonDeserializer<?> vd, TypeDeserializer vtd, NullValueProvider nuller, Boolean unwrapSingle) {
        return new ArrayBlockingQueueDeserializer(this._containerType, vd, vtd, this._valueInstantiator, dd2, nuller, unwrapSingle);
    }

    @Override
    protected Collection<Object> createDefaultInstance(DeserializationContext ctxt) throws IOException {
        return null;
    }

    @Override
    public Collection<Object> deserialize(JsonParser p2, DeserializationContext ctxt, Collection<Object> result0) throws IOException {
        if (result0 != null) {
            return super.deserialize(p2, ctxt, result0);
        }
        if (!p2.isExpectedStartArrayToken()) {
            return this.handleNonArray(p2, ctxt, new ArrayBlockingQueue<Object>(1));
        }
        result0 = super.deserialize(p2, ctxt, (Collection<Object>)new ArrayList<Object>());
        return new ArrayBlockingQueue<Object>(result0.size(), false, result0);
    }

    @Override
    public Object deserializeWithType(JsonParser p2, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromArray(p2, ctxt);
    }
}

