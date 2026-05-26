/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.jsonFormatVisitors;

import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonMappingException;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializerProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import software.bernie.shadowed.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable;
import software.bernie.shadowed.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWithSerializerProvider;

public interface JsonArrayFormatVisitor
extends JsonFormatVisitorWithSerializerProvider {
    public void itemsFormat(JsonFormatVisitable var1, JavaType var2) throws JsonMappingException;

    public void itemsFormat(JsonFormatTypes var1) throws JsonMappingException;

    public static class Base
    implements JsonArrayFormatVisitor {
        protected SerializerProvider _provider;

        public Base() {
        }

        public Base(SerializerProvider p2) {
            this._provider = p2;
        }

        @Override
        public SerializerProvider getProvider() {
            return this._provider;
        }

        @Override
        public void setProvider(SerializerProvider p2) {
            this._provider = p2;
        }

        @Override
        public void itemsFormat(JsonFormatVisitable handler, JavaType elementType) throws JsonMappingException {
        }

        @Override
        public void itemsFormat(JsonFormatTypes format) throws JsonMappingException {
        }
    }
}

