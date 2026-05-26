/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind;

import java.lang.reflect.Type;
import java.util.Locale;
import java.util.TimeZone;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonFormat;
import software.bernie.shadowed.fasterxml.jackson.annotation.ObjectIdGenerator;
import software.bernie.shadowed.fasterxml.jackson.annotation.ObjectIdResolver;
import software.bernie.shadowed.fasterxml.jackson.databind.AnnotationIntrospector;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonMappingException;
import software.bernie.shadowed.fasterxml.jackson.databind.MapperFeature;
import software.bernie.shadowed.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import software.bernie.shadowed.fasterxml.jackson.databind.cfg.MapperConfig;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.Annotated;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import software.bernie.shadowed.fasterxml.jackson.databind.type.TypeFactory;
import software.bernie.shadowed.fasterxml.jackson.databind.util.ClassUtil;
import software.bernie.shadowed.fasterxml.jackson.databind.util.Converter;

public abstract class DatabindContext {
    private static final int MAX_ERROR_STR_LEN = 500;

    public abstract MapperConfig<?> getConfig();

    public abstract AnnotationIntrospector getAnnotationIntrospector();

    public abstract boolean isEnabled(MapperFeature var1);

    public abstract boolean canOverrideAccessModifiers();

    public abstract Class<?> getActiveView();

    public abstract Locale getLocale();

    public abstract TimeZone getTimeZone();

    public abstract JsonFormat.Value getDefaultPropertyFormat(Class<?> var1);

    public abstract Object getAttribute(Object var1);

    public abstract DatabindContext setAttribute(Object var1, Object var2);

    public JavaType constructType(Type type) {
        if (type == null) {
            return null;
        }
        return this.getTypeFactory().constructType(type);
    }

    public JavaType constructSpecializedType(JavaType baseType, Class<?> subclass) {
        if (baseType.getRawClass() == subclass) {
            return baseType;
        }
        return this.getConfig().constructSpecializedType(baseType, subclass);
    }

    public JavaType resolveSubType(JavaType baseType, String subClass) throws JsonMappingException {
        Class<?> cls;
        if (subClass.indexOf(60) > 0) {
            return this.getTypeFactory().constructFromCanonical(subClass);
        }
        try {
            cls = this.getTypeFactory().findClass(subClass);
        }
        catch (ClassNotFoundException e10) {
            return null;
        }
        catch (Exception e11) {
            throw this.invalidTypeIdException(baseType, subClass, String.format("problem: (%s) %s", e11.getClass().getName(), e11.getMessage()));
        }
        if (baseType.isTypeOrSuperTypeOf(cls)) {
            return this.getTypeFactory().constructSpecializedType(baseType, cls);
        }
        throw this.invalidTypeIdException(baseType, subClass, "Not a subtype");
    }

    protected abstract JsonMappingException invalidTypeIdException(JavaType var1, String var2, String var3);

    public abstract TypeFactory getTypeFactory();

    public ObjectIdGenerator<?> objectIdGeneratorInstance(Annotated annotated, ObjectIdInfo objectIdInfo) throws JsonMappingException {
        ObjectIdGenerator<?> gen;
        Class<? extends ObjectIdGenerator<?>> implClass = objectIdInfo.getGeneratorType();
        MapperConfig<?> config = this.getConfig();
        HandlerInstantiator hi = config.getHandlerInstantiator();
        ObjectIdGenerator<?> objectIdGenerator = gen = hi == null ? null : hi.objectIdGeneratorInstance(config, annotated, implClass);
        if (gen == null) {
            gen = ClassUtil.createInstance(implClass, config.canOverrideAccessModifiers());
        }
        return gen.forScope(objectIdInfo.getScope());
    }

    public ObjectIdResolver objectIdResolverInstance(Annotated annotated, ObjectIdInfo objectIdInfo) {
        ObjectIdResolver resolver;
        Class<? extends ObjectIdResolver> implClass = objectIdInfo.getResolverType();
        MapperConfig<?> config = this.getConfig();
        HandlerInstantiator hi = config.getHandlerInstantiator();
        ObjectIdResolver objectIdResolver = resolver = hi == null ? null : hi.resolverIdGeneratorInstance(config, annotated, implClass);
        if (resolver == null) {
            resolver = ClassUtil.createInstance(implClass, config.canOverrideAccessModifiers());
        }
        return resolver;
    }

    public Converter<Object, Object> converterInstance(Annotated annotated, Object converterDef) throws JsonMappingException {
        Converter conv;
        if (converterDef == null) {
            return null;
        }
        if (converterDef instanceof Converter) {
            return (Converter)converterDef;
        }
        if (!(converterDef instanceof Class)) {
            throw new IllegalStateException("AnnotationIntrospector returned Converter definition of type " + converterDef.getClass().getName() + "; expected type Converter or Class<Converter> instead");
        }
        Class converterClass = (Class)converterDef;
        if (converterClass == Converter.None.class || ClassUtil.isBogusClass(converterClass)) {
            return null;
        }
        if (!Converter.class.isAssignableFrom(converterClass)) {
            throw new IllegalStateException("AnnotationIntrospector returned Class " + converterClass.getName() + "; expected Class<Converter>");
        }
        MapperConfig<?> config = this.getConfig();
        HandlerInstantiator hi = config.getHandlerInstantiator();
        Converter converter = conv = hi == null ? null : hi.converterInstance(config, annotated, converterClass);
        if (conv == null) {
            conv = (Converter)ClassUtil.createInstance(converterClass, config.canOverrideAccessModifiers());
        }
        return conv;
    }

    public abstract <T> T reportBadDefinition(JavaType var1, String var2) throws JsonMappingException;

    public <T> T reportBadDefinition(Class<?> type, String msg) throws JsonMappingException {
        return this.reportBadDefinition(this.constructType(type), msg);
    }

    protected final String _format(String msg, Object ... msgArgs) {
        if (msgArgs.length > 0) {
            return String.format(msg, msgArgs);
        }
        return msg;
    }

    protected final String _truncate(String desc) {
        if (desc == null) {
            return "";
        }
        if (desc.length() <= 500) {
            return desc;
        }
        return desc.substring(0, 500) + "]...[" + desc.substring(desc.length() - 500);
    }

    protected String _quotedString(String desc) {
        if (desc == null) {
            return "[N/A]";
        }
        return String.format("\"%s\"", this._truncate(desc));
    }

    protected String _colonConcat(String msgBase, String extra) {
        if (extra == null) {
            return msgBase;
        }
        return msgBase + ": " + extra;
    }

    protected String _calcName(Class<?> cls) {
        if (cls.isArray()) {
            return this._calcName(cls.getComponentType()) + "[]";
        }
        return cls.getName();
    }

    protected String _desc(String desc) {
        if (desc == null) {
            return "[N/A]";
        }
        return this._truncate(desc);
    }
}

