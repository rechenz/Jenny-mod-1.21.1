/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.exc;

import software.bernie.shadowed.fasterxml.jackson.core.JsonGenerator;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.databind.BeanDescription;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonMappingException;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;

public class InvalidDefinitionException
extends JsonMappingException {
    protected final JavaType _type;
    protected transient BeanDescription _beanDesc;
    protected transient BeanPropertyDefinition _property;

    protected InvalidDefinitionException(JsonParser p2, String msg, JavaType type) {
        super(p2, msg);
        this._type = type;
        this._beanDesc = null;
        this._property = null;
    }

    protected InvalidDefinitionException(JsonGenerator g10, String msg, JavaType type) {
        super(g10, msg);
        this._type = type;
        this._beanDesc = null;
        this._property = null;
    }

    protected InvalidDefinitionException(JsonParser p2, String msg, BeanDescription bean, BeanPropertyDefinition prop) {
        super(p2, msg);
        this._type = bean == null ? null : bean.getType();
        this._beanDesc = bean;
        this._property = prop;
    }

    protected InvalidDefinitionException(JsonGenerator g10, String msg, BeanDescription bean, BeanPropertyDefinition prop) {
        super(g10, msg);
        this._type = bean == null ? null : bean.getType();
        this._beanDesc = bean;
        this._property = prop;
    }

    public static InvalidDefinitionException from(JsonParser p2, String msg, BeanDescription bean, BeanPropertyDefinition prop) {
        return new InvalidDefinitionException(p2, msg, bean, prop);
    }

    public static InvalidDefinitionException from(JsonParser p2, String msg, JavaType type) {
        return new InvalidDefinitionException(p2, msg, type);
    }

    public static InvalidDefinitionException from(JsonGenerator g10, String msg, BeanDescription bean, BeanPropertyDefinition prop) {
        return new InvalidDefinitionException(g10, msg, bean, prop);
    }

    public static InvalidDefinitionException from(JsonGenerator g10, String msg, JavaType type) {
        return new InvalidDefinitionException(g10, msg, type);
    }

    public JavaType getType() {
        return this._type;
    }

    public BeanDescription getBeanDescription() {
        return this._beanDesc;
    }

    public BeanPropertyDefinition getProperty() {
        return this._property;
    }
}

