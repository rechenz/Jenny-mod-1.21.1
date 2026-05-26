/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.jsontype;

import java.io.Serializable;

public final class NamedType
implements Serializable {
    private static final long serialVersionUID = 1L;
    protected final Class<?> _class;
    protected final int _hashCode;
    protected String _name;

    public NamedType(Class<?> c10) {
        this(c10, null);
    }

    public NamedType(Class<?> c10, String name) {
        this._class = c10;
        this._hashCode = c10.getName().hashCode();
        this.setName(name);
    }

    public Class<?> getType() {
        return this._class;
    }

    public String getName() {
        return this._name;
    }

    public void setName(String name) {
        this._name = name == null || name.length() == 0 ? null : name;
    }

    public boolean hasName() {
        return this._name != null;
    }

    public boolean equals(Object o2) {
        if (o2 == this) {
            return true;
        }
        if (o2 == null) {
            return false;
        }
        if (o2.getClass() != this.getClass()) {
            return false;
        }
        return this._class == ((NamedType)o2)._class;
    }

    public int hashCode() {
        return this._hashCode;
    }

    public String toString() {
        return "[NamedType, class " + this._class.getName() + ", name: " + (this._name == null ? "null" : "'" + this._name + "'") + "]";
    }
}

