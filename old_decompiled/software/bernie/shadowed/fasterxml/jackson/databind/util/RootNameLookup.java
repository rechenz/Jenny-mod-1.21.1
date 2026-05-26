/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.util;

import java.io.Serializable;
import software.bernie.shadowed.fasterxml.jackson.databind.AnnotationIntrospector;
import software.bernie.shadowed.fasterxml.jackson.databind.BeanDescription;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.PropertyName;
import software.bernie.shadowed.fasterxml.jackson.databind.cfg.MapperConfig;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedClass;
import software.bernie.shadowed.fasterxml.jackson.databind.type.ClassKey;
import software.bernie.shadowed.fasterxml.jackson.databind.util.LRUMap;

public class RootNameLookup
implements Serializable {
    private static final long serialVersionUID = 1L;
    protected transient LRUMap<ClassKey, PropertyName> _rootNames = new LRUMap(20, 200);

    public PropertyName findRootName(JavaType rootType, MapperConfig<?> config) {
        return this.findRootName(rootType.getRawClass(), config);
    }

    public PropertyName findRootName(Class<?> rootType, MapperConfig<?> config) {
        AnnotatedClass ac2;
        ClassKey key = new ClassKey(rootType);
        PropertyName name = this._rootNames.get(key);
        if (name != null) {
            return name;
        }
        BeanDescription beanDesc = config.introspectClassAnnotations(rootType);
        AnnotationIntrospector intr = config.getAnnotationIntrospector();
        name = intr.findRootName(ac2 = beanDesc.getClassInfo());
        if (name == null || !name.hasSimpleName()) {
            name = PropertyName.construct(rootType.getSimpleName());
        }
        this._rootNames.put(key, name);
        return name;
    }

    protected Object readResolve() {
        return new RootNameLookup();
    }
}

