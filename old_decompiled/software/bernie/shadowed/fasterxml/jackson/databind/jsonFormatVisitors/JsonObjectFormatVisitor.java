/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.jsonFormatVisitors;

import software.bernie.shadowed.fasterxml.jackson.databind.BeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonMappingException;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializerProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable;
import software.bernie.shadowed.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWithSerializerProvider;

public interface JsonObjectFormatVisitor
extends JsonFormatVisitorWithSerializerProvider {
    public void property(BeanProperty var1) throws JsonMappingException;

    public void property(String var1, JsonFormatVisitable var2, JavaType var3) throws JsonMappingException;

    public void optionalProperty(BeanProperty var1) throws JsonMappingException;

    public void optionalProperty(String var1, JsonFormatVisitable var2, JavaType var3) throws JsonMappingException;

    public static class Base
    implements JsonObjectFormatVisitor {
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
        public void property(BeanProperty prop) throws JsonMappingException {
        }

        @Override
        public void property(String name, JsonFormatVisitable handler, JavaType propertyTypeHint) throws JsonMappingException {
        }

        @Override
        public void optionalProperty(BeanProperty prop) throws JsonMappingException {
        }

        @Override
        public void optionalProperty(String name, JsonFormatVisitable handler, JavaType propertyTypeHint) throws JsonMappingException {
        }
    }
}

