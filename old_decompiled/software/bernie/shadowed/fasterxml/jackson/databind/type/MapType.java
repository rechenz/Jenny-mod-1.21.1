/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.type;

import java.lang.reflect.TypeVariable;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.type.MapLikeType;
import software.bernie.shadowed.fasterxml.jackson.databind.type.TypeBase;
import software.bernie.shadowed.fasterxml.jackson.databind.type.TypeBindings;

public final class MapType
extends MapLikeType {
    private static final long serialVersionUID = 1L;

    private MapType(Class<?> mapType, TypeBindings bindings, JavaType superClass, JavaType[] superInts, JavaType keyT, JavaType valueT, Object valueHandler, Object typeHandler, boolean asStatic) {
        super(mapType, bindings, superClass, superInts, keyT, valueT, valueHandler, typeHandler, asStatic);
    }

    protected MapType(TypeBase base, JavaType keyT, JavaType valueT) {
        super(base, keyT, valueT);
    }

    public static MapType construct(Class<?> rawType, TypeBindings bindings, JavaType superClass, JavaType[] superInts, JavaType keyT, JavaType valueT) {
        return new MapType(rawType, bindings, superClass, superInts, keyT, valueT, null, null, false);
    }

    @Deprecated
    public static MapType construct(Class<?> rawType, JavaType keyT, JavaType valueT) {
        TypeVariable<Class<?>>[] vars = rawType.getTypeParameters();
        TypeBindings bindings = vars == null || vars.length != 2 ? TypeBindings.emptyBindings() : TypeBindings.create(rawType, keyT, valueT);
        return new MapType(rawType, bindings, MapType._bogusSuperClass(rawType), null, keyT, valueT, null, null, false);
    }

    @Override
    @Deprecated
    protected JavaType _narrow(Class<?> subclass) {
        return new MapType(subclass, this._bindings, this._superClass, this._superInterfaces, this._keyType, this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override
    public MapType withTypeHandler(Object h2) {
        return new MapType(this._class, this._bindings, this._superClass, this._superInterfaces, this._keyType, this._valueType, this._valueHandler, h2, this._asStatic);
    }

    @Override
    public MapType withContentTypeHandler(Object h2) {
        return new MapType(this._class, this._bindings, this._superClass, this._superInterfaces, this._keyType, this._valueType.withTypeHandler(h2), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override
    public MapType withValueHandler(Object h2) {
        return new MapType(this._class, this._bindings, this._superClass, this._superInterfaces, this._keyType, this._valueType, h2, this._typeHandler, this._asStatic);
    }

    @Override
    public MapType withContentValueHandler(Object h2) {
        return new MapType(this._class, this._bindings, this._superClass, this._superInterfaces, this._keyType, this._valueType.withValueHandler(h2), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override
    public MapType withStaticTyping() {
        if (this._asStatic) {
            return this;
        }
        return new MapType(this._class, this._bindings, this._superClass, this._superInterfaces, this._keyType.withStaticTyping(), this._valueType.withStaticTyping(), this._valueHandler, this._typeHandler, true);
    }

    @Override
    public JavaType withContentType(JavaType contentType) {
        if (this._valueType == contentType) {
            return this;
        }
        return new MapType(this._class, this._bindings, this._superClass, this._superInterfaces, this._keyType, contentType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override
    public MapType withKeyType(JavaType keyType) {
        if (keyType == this._keyType) {
            return this;
        }
        return new MapType(this._class, this._bindings, this._superClass, this._superInterfaces, keyType, this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override
    public JavaType refine(Class<?> rawType, TypeBindings bindings, JavaType superClass, JavaType[] superInterfaces) {
        return new MapType(rawType, bindings, superClass, superInterfaces, this._keyType, this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override
    public MapType withKeyTypeHandler(Object h2) {
        return new MapType(this._class, this._bindings, this._superClass, this._superInterfaces, this._keyType.withTypeHandler(h2), this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override
    public MapType withKeyValueHandler(Object h2) {
        return new MapType(this._class, this._bindings, this._superClass, this._superInterfaces, this._keyType.withValueHandler(h2), this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override
    public String toString() {
        return "[map type; class " + this._class.getName() + ", " + this._keyType + " -> " + this._valueType + "]";
    }
}

