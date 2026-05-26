/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.type;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import software.bernie.shadowed.fasterxml.jackson.core.JsonGenerator;
import software.bernie.shadowed.fasterxml.jackson.core.JsonProcessingException;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.core.type.WritableTypeId;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonSerializable;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializerProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.type.TypeBindings;
import software.bernie.shadowed.fasterxml.jackson.databind.type.TypeFactory;

public abstract class TypeBase
extends JavaType
implements JsonSerializable {
    private static final long serialVersionUID = 1L;
    private static final TypeBindings NO_BINDINGS = TypeBindings.emptyBindings();
    private static final JavaType[] NO_TYPES = new JavaType[0];
    protected final JavaType _superClass;
    protected final JavaType[] _superInterfaces;
    protected final TypeBindings _bindings;
    volatile transient String _canonicalName;

    protected TypeBase(Class<?> raw, TypeBindings bindings, JavaType superClass, JavaType[] superInts, int hash, Object valueHandler, Object typeHandler, boolean asStatic) {
        super(raw, hash, valueHandler, typeHandler, asStatic);
        this._bindings = bindings == null ? NO_BINDINGS : bindings;
        this._superClass = superClass;
        this._superInterfaces = superInts;
    }

    protected TypeBase(TypeBase base) {
        super(base);
        this._superClass = base._superClass;
        this._superInterfaces = base._superInterfaces;
        this._bindings = base._bindings;
    }

    @Override
    public String toCanonical() {
        String str = this._canonicalName;
        if (str == null) {
            str = this.buildCanonicalName();
        }
        return str;
    }

    protected String buildCanonicalName() {
        return this._class.getName();
    }

    @Override
    public abstract StringBuilder getGenericSignature(StringBuilder var1);

    @Override
    public abstract StringBuilder getErasedSignature(StringBuilder var1);

    @Override
    public TypeBindings getBindings() {
        return this._bindings;
    }

    @Override
    public int containedTypeCount() {
        return this._bindings.size();
    }

    @Override
    public JavaType containedType(int index) {
        return this._bindings.getBoundType(index);
    }

    @Override
    @Deprecated
    public String containedTypeName(int index) {
        return this._bindings.getBoundName(index);
    }

    @Override
    public JavaType getSuperClass() {
        return this._superClass;
    }

    @Override
    public List<JavaType> getInterfaces() {
        if (this._superInterfaces == null) {
            return Collections.emptyList();
        }
        switch (this._superInterfaces.length) {
            case 0: {
                return Collections.emptyList();
            }
            case 1: {
                return Collections.singletonList(this._superInterfaces[0]);
            }
        }
        return Arrays.asList(this._superInterfaces);
    }

    @Override
    public final JavaType findSuperType(Class<?> rawTarget) {
        JavaType type;
        if (rawTarget == this._class) {
            return this;
        }
        if (rawTarget.isInterface() && this._superInterfaces != null) {
            int count = this._superInterfaces.length;
            for (int i2 = 0; i2 < count; ++i2) {
                JavaType type2 = this._superInterfaces[i2].findSuperType(rawTarget);
                if (type2 == null) continue;
                return type2;
            }
        }
        if (this._superClass != null && (type = this._superClass.findSuperType(rawTarget)) != null) {
            return type;
        }
        return null;
    }

    @Override
    public JavaType[] findTypeParameters(Class<?> expType) {
        JavaType match = this.findSuperType(expType);
        if (match == null) {
            return NO_TYPES;
        }
        return match.getBindings().typeParameterArray();
    }

    @Override
    public void serializeWithType(JsonGenerator g10, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
        WritableTypeId typeIdDef = new WritableTypeId(this, JsonToken.VALUE_STRING);
        typeSer.writeTypePrefix(g10, typeIdDef);
        this.serialize(g10, provider);
        typeSer.writeTypeSuffix(g10, typeIdDef);
    }

    @Override
    public void serialize(JsonGenerator gen, SerializerProvider provider) throws IOException, JsonProcessingException {
        gen.writeString(this.toCanonical());
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected static StringBuilder _classSignature(Class<?> cls, StringBuilder sb, boolean trailingSemicolon) {
        if (cls.isPrimitive()) {
            if (cls == Boolean.TYPE) {
                sb.append('Z');
                return sb;
            } else if (cls == Byte.TYPE) {
                sb.append('B');
                return sb;
            } else if (cls == Short.TYPE) {
                sb.append('S');
                return sb;
            } else if (cls == Character.TYPE) {
                sb.append('C');
                return sb;
            } else if (cls == Integer.TYPE) {
                sb.append('I');
                return sb;
            } else if (cls == Long.TYPE) {
                sb.append('J');
                return sb;
            } else if (cls == Float.TYPE) {
                sb.append('F');
                return sb;
            } else if (cls == Double.TYPE) {
                sb.append('D');
                return sb;
            } else {
                if (cls != Void.TYPE) throw new IllegalStateException("Unrecognized primitive type: " + cls.getName());
                sb.append('V');
            }
            return sb;
        } else {
            sb.append('L');
            String name = cls.getName();
            int len = name.length();
            for (int i2 = 0; i2 < len; ++i2) {
                char c10 = name.charAt(i2);
                if (c10 == '.') {
                    c10 = '/';
                }
                sb.append(c10);
            }
            if (!trailingSemicolon) return sb;
            sb.append(';');
        }
        return sb;
    }

    protected static JavaType _bogusSuperClass(Class<?> cls) {
        Class<?> parent = cls.getSuperclass();
        if (parent == null) {
            return null;
        }
        return TypeFactory.unknownType();
    }
}

