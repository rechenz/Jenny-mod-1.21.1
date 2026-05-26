/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.ext;

import java.beans.ConstructorProperties;
import java.beans.Transient;
import java.nio.file.Path;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.PropertyName;
import software.bernie.shadowed.fasterxml.jackson.databind.ext.Java7Support;
import software.bernie.shadowed.fasterxml.jackson.databind.ext.NioPathDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.ext.NioPathSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.Annotated;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedWithParams;

public class Java7SupportImpl
extends Java7Support {
    private final Class<?> _bogus;

    public Java7SupportImpl() {
        Class cls = Transient.class;
        cls = ConstructorProperties.class;
        this._bogus = cls;
    }

    @Override
    public Class<?> getClassJavaNioFilePath() {
        return Path.class;
    }

    @Override
    public JsonDeserializer<?> getDeserializerForJavaNioFilePath(Class<?> rawType) {
        if (rawType == Path.class) {
            return new NioPathDeserializer();
        }
        return null;
    }

    @Override
    public JsonSerializer<?> getSerializerForJavaNioFilePath(Class<?> rawType) {
        if (Path.class.isAssignableFrom(rawType)) {
            return new NioPathSerializer();
        }
        return null;
    }

    @Override
    public Boolean findTransient(Annotated a10) {
        Transient t2 = a10.getAnnotation(Transient.class);
        if (t2 != null) {
            return t2.value();
        }
        return null;
    }

    @Override
    public Boolean hasCreatorAnnotation(Annotated a10) {
        ConstructorProperties props = a10.getAnnotation(ConstructorProperties.class);
        if (props != null) {
            return Boolean.TRUE;
        }
        return null;
    }

    @Override
    public PropertyName findConstructorName(AnnotatedParameter p2) {
        ConstructorProperties props;
        AnnotatedWithParams ctor = p2.getOwner();
        if (ctor != null && (props = ctor.getAnnotation(ConstructorProperties.class)) != null) {
            String[] names = props.value();
            int ix = p2.getIndex();
            if (ix < names.length) {
                return PropertyName.construct(names[ix]);
            }
        }
        return null;
    }
}

