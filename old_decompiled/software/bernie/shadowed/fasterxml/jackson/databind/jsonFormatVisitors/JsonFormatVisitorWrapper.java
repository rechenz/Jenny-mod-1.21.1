/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.jsonFormatVisitors;

import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonMappingException;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializerProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.jsonFormatVisitors.JsonAnyFormatVisitor;
import software.bernie.shadowed.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import software.bernie.shadowed.fasterxml.jackson.databind.jsonFormatVisitors.JsonBooleanFormatVisitor;
import software.bernie.shadowed.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWithSerializerProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.jsonFormatVisitors.JsonIntegerFormatVisitor;
import software.bernie.shadowed.fasterxml.jackson.databind.jsonFormatVisitors.JsonMapFormatVisitor;
import software.bernie.shadowed.fasterxml.jackson.databind.jsonFormatVisitors.JsonNullFormatVisitor;
import software.bernie.shadowed.fasterxml.jackson.databind.jsonFormatVisitors.JsonNumberFormatVisitor;
import software.bernie.shadowed.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import software.bernie.shadowed.fasterxml.jackson.databind.jsonFormatVisitors.JsonStringFormatVisitor;

public interface JsonFormatVisitorWrapper
extends JsonFormatVisitorWithSerializerProvider {
    public JsonObjectFormatVisitor expectObjectFormat(JavaType var1) throws JsonMappingException;

    public JsonArrayFormatVisitor expectArrayFormat(JavaType var1) throws JsonMappingException;

    public JsonStringFormatVisitor expectStringFormat(JavaType var1) throws JsonMappingException;

    public JsonNumberFormatVisitor expectNumberFormat(JavaType var1) throws JsonMappingException;

    public JsonIntegerFormatVisitor expectIntegerFormat(JavaType var1) throws JsonMappingException;

    public JsonBooleanFormatVisitor expectBooleanFormat(JavaType var1) throws JsonMappingException;

    public JsonNullFormatVisitor expectNullFormat(JavaType var1) throws JsonMappingException;

    public JsonAnyFormatVisitor expectAnyFormat(JavaType var1) throws JsonMappingException;

    public JsonMapFormatVisitor expectMapFormat(JavaType var1) throws JsonMappingException;

    public static class Base
    implements JsonFormatVisitorWrapper {
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
        public JsonObjectFormatVisitor expectObjectFormat(JavaType type) throws JsonMappingException {
            return null;
        }

        @Override
        public JsonArrayFormatVisitor expectArrayFormat(JavaType type) throws JsonMappingException {
            return null;
        }

        @Override
        public JsonStringFormatVisitor expectStringFormat(JavaType type) throws JsonMappingException {
            return null;
        }

        @Override
        public JsonNumberFormatVisitor expectNumberFormat(JavaType type) throws JsonMappingException {
            return null;
        }

        @Override
        public JsonIntegerFormatVisitor expectIntegerFormat(JavaType type) throws JsonMappingException {
            return null;
        }

        @Override
        public JsonBooleanFormatVisitor expectBooleanFormat(JavaType type) throws JsonMappingException {
            return null;
        }

        @Override
        public JsonNullFormatVisitor expectNullFormat(JavaType type) throws JsonMappingException {
            return null;
        }

        @Override
        public JsonAnyFormatVisitor expectAnyFormat(JavaType type) throws JsonMappingException {
            return null;
        }

        @Override
        public JsonMapFormatVisitor expectMapFormat(JavaType type) throws JsonMappingException {
            return null;
        }
    }
}

