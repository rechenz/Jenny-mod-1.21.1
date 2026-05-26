/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.introspect;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonAutoDetect;
import software.bernie.shadowed.fasterxml.jackson.annotation.PropertyAccessor;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedField;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedMember;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedMethod;

public interface VisibilityChecker<T extends VisibilityChecker<T>> {
    public T with(JsonAutoDetect var1);

    public T withOverrides(JsonAutoDetect.Value var1);

    public T with(JsonAutoDetect.Visibility var1);

    public T withVisibility(PropertyAccessor var1, JsonAutoDetect.Visibility var2);

    public T withGetterVisibility(JsonAutoDetect.Visibility var1);

    public T withIsGetterVisibility(JsonAutoDetect.Visibility var1);

    public T withSetterVisibility(JsonAutoDetect.Visibility var1);

    public T withCreatorVisibility(JsonAutoDetect.Visibility var1);

    public T withFieldVisibility(JsonAutoDetect.Visibility var1);

    public boolean isGetterVisible(Method var1);

    public boolean isGetterVisible(AnnotatedMethod var1);

    public boolean isIsGetterVisible(Method var1);

    public boolean isIsGetterVisible(AnnotatedMethod var1);

    public boolean isSetterVisible(Method var1);

    public boolean isSetterVisible(AnnotatedMethod var1);

    public boolean isCreatorVisible(Member var1);

    public boolean isCreatorVisible(AnnotatedMember var1);

    public boolean isFieldVisible(Field var1);

    public boolean isFieldVisible(AnnotatedField var1);

    public static class Std
    implements VisibilityChecker<Std>,
    Serializable {
        private static final long serialVersionUID = 1L;
        protected static final Std DEFAULT = new Std(JsonAutoDetect.Visibility.PUBLIC_ONLY, JsonAutoDetect.Visibility.PUBLIC_ONLY, JsonAutoDetect.Visibility.ANY, JsonAutoDetect.Visibility.ANY, JsonAutoDetect.Visibility.PUBLIC_ONLY);
        protected final JsonAutoDetect.Visibility _getterMinLevel;
        protected final JsonAutoDetect.Visibility _isGetterMinLevel;
        protected final JsonAutoDetect.Visibility _setterMinLevel;
        protected final JsonAutoDetect.Visibility _creatorMinLevel;
        protected final JsonAutoDetect.Visibility _fieldMinLevel;

        public static Std defaultInstance() {
            return DEFAULT;
        }

        public Std(JsonAutoDetect ann) {
            this._getterMinLevel = ann.getterVisibility();
            this._isGetterMinLevel = ann.isGetterVisibility();
            this._setterMinLevel = ann.setterVisibility();
            this._creatorMinLevel = ann.creatorVisibility();
            this._fieldMinLevel = ann.fieldVisibility();
        }

        public Std(JsonAutoDetect.Visibility getter, JsonAutoDetect.Visibility isGetter, JsonAutoDetect.Visibility setter, JsonAutoDetect.Visibility creator, JsonAutoDetect.Visibility field) {
            this._getterMinLevel = getter;
            this._isGetterMinLevel = isGetter;
            this._setterMinLevel = setter;
            this._creatorMinLevel = creator;
            this._fieldMinLevel = field;
        }

        public Std(JsonAutoDetect.Visibility v2) {
            if (v2 == JsonAutoDetect.Visibility.DEFAULT) {
                this._getterMinLevel = Std.DEFAULT._getterMinLevel;
                this._isGetterMinLevel = Std.DEFAULT._isGetterMinLevel;
                this._setterMinLevel = Std.DEFAULT._setterMinLevel;
                this._creatorMinLevel = Std.DEFAULT._creatorMinLevel;
                this._fieldMinLevel = Std.DEFAULT._fieldMinLevel;
            } else {
                this._getterMinLevel = v2;
                this._isGetterMinLevel = v2;
                this._setterMinLevel = v2;
                this._creatorMinLevel = v2;
                this._fieldMinLevel = v2;
            }
        }

        public static Std construct(JsonAutoDetect.Value vis) {
            return DEFAULT.withOverrides(vis);
        }

        protected Std _with(JsonAutoDetect.Visibility g10, JsonAutoDetect.Visibility isG, JsonAutoDetect.Visibility s2, JsonAutoDetect.Visibility cr2, JsonAutoDetect.Visibility f10) {
            if (g10 == this._getterMinLevel && isG == this._isGetterMinLevel && s2 == this._setterMinLevel && cr2 == this._creatorMinLevel && f10 == this._fieldMinLevel) {
                return this;
            }
            return new Std(g10, isG, s2, cr2, f10);
        }

        @Override
        public Std with(JsonAutoDetect ann) {
            Std curr = this;
            if (ann != null) {
                return this._with(this._defaultOrOverride(this._getterMinLevel, ann.getterVisibility()), this._defaultOrOverride(this._isGetterMinLevel, ann.isGetterVisibility()), this._defaultOrOverride(this._setterMinLevel, ann.setterVisibility()), this._defaultOrOverride(this._creatorMinLevel, ann.creatorVisibility()), this._defaultOrOverride(this._fieldMinLevel, ann.fieldVisibility()));
            }
            return curr;
        }

        @Override
        public Std withOverrides(JsonAutoDetect.Value vis) {
            Std curr = this;
            if (vis != null) {
                return this._with(this._defaultOrOverride(this._getterMinLevel, vis.getGetterVisibility()), this._defaultOrOverride(this._isGetterMinLevel, vis.getIsGetterVisibility()), this._defaultOrOverride(this._setterMinLevel, vis.getSetterVisibility()), this._defaultOrOverride(this._creatorMinLevel, vis.getCreatorVisibility()), this._defaultOrOverride(this._fieldMinLevel, vis.getFieldVisibility()));
            }
            return curr;
        }

        private JsonAutoDetect.Visibility _defaultOrOverride(JsonAutoDetect.Visibility defaults, JsonAutoDetect.Visibility override) {
            if (override == JsonAutoDetect.Visibility.DEFAULT) {
                return defaults;
            }
            return override;
        }

        @Override
        public Std with(JsonAutoDetect.Visibility v2) {
            if (v2 == JsonAutoDetect.Visibility.DEFAULT) {
                return DEFAULT;
            }
            return new Std(v2);
        }

        @Override
        public Std withVisibility(PropertyAccessor method, JsonAutoDetect.Visibility v2) {
            switch (method) {
                case GETTER: {
                    return this.withGetterVisibility(v2);
                }
                case SETTER: {
                    return this.withSetterVisibility(v2);
                }
                case CREATOR: {
                    return this.withCreatorVisibility(v2);
                }
                case FIELD: {
                    return this.withFieldVisibility(v2);
                }
                case IS_GETTER: {
                    return this.withIsGetterVisibility(v2);
                }
                case ALL: {
                    return this.with(v2);
                }
            }
            return this;
        }

        @Override
        public Std withGetterVisibility(JsonAutoDetect.Visibility v2) {
            if (v2 == JsonAutoDetect.Visibility.DEFAULT) {
                v2 = Std.DEFAULT._getterMinLevel;
            }
            if (this._getterMinLevel == v2) {
                return this;
            }
            return new Std(v2, this._isGetterMinLevel, this._setterMinLevel, this._creatorMinLevel, this._fieldMinLevel);
        }

        @Override
        public Std withIsGetterVisibility(JsonAutoDetect.Visibility v2) {
            if (v2 == JsonAutoDetect.Visibility.DEFAULT) {
                v2 = Std.DEFAULT._isGetterMinLevel;
            }
            if (this._isGetterMinLevel == v2) {
                return this;
            }
            return new Std(this._getterMinLevel, v2, this._setterMinLevel, this._creatorMinLevel, this._fieldMinLevel);
        }

        @Override
        public Std withSetterVisibility(JsonAutoDetect.Visibility v2) {
            if (v2 == JsonAutoDetect.Visibility.DEFAULT) {
                v2 = Std.DEFAULT._setterMinLevel;
            }
            if (this._setterMinLevel == v2) {
                return this;
            }
            return new Std(this._getterMinLevel, this._isGetterMinLevel, v2, this._creatorMinLevel, this._fieldMinLevel);
        }

        @Override
        public Std withCreatorVisibility(JsonAutoDetect.Visibility v2) {
            if (v2 == JsonAutoDetect.Visibility.DEFAULT) {
                v2 = Std.DEFAULT._creatorMinLevel;
            }
            if (this._creatorMinLevel == v2) {
                return this;
            }
            return new Std(this._getterMinLevel, this._isGetterMinLevel, this._setterMinLevel, v2, this._fieldMinLevel);
        }

        @Override
        public Std withFieldVisibility(JsonAutoDetect.Visibility v2) {
            if (v2 == JsonAutoDetect.Visibility.DEFAULT) {
                v2 = Std.DEFAULT._fieldMinLevel;
            }
            if (this._fieldMinLevel == v2) {
                return this;
            }
            return new Std(this._getterMinLevel, this._isGetterMinLevel, this._setterMinLevel, this._creatorMinLevel, v2);
        }

        @Override
        public boolean isCreatorVisible(Member m2) {
            return this._creatorMinLevel.isVisible(m2);
        }

        @Override
        public boolean isCreatorVisible(AnnotatedMember m2) {
            return this.isCreatorVisible(m2.getMember());
        }

        @Override
        public boolean isFieldVisible(Field f10) {
            return this._fieldMinLevel.isVisible(f10);
        }

        @Override
        public boolean isFieldVisible(AnnotatedField f10) {
            return this.isFieldVisible(f10.getAnnotated());
        }

        @Override
        public boolean isGetterVisible(Method m2) {
            return this._getterMinLevel.isVisible(m2);
        }

        @Override
        public boolean isGetterVisible(AnnotatedMethod m2) {
            return this.isGetterVisible(m2.getAnnotated());
        }

        @Override
        public boolean isIsGetterVisible(Method m2) {
            return this._isGetterMinLevel.isVisible(m2);
        }

        @Override
        public boolean isIsGetterVisible(AnnotatedMethod m2) {
            return this.isIsGetterVisible(m2.getAnnotated());
        }

        @Override
        public boolean isSetterVisible(Method m2) {
            return this._setterMinLevel.isVisible(m2);
        }

        @Override
        public boolean isSetterVisible(AnnotatedMethod m2) {
            return this.isSetterVisible(m2.getAnnotated());
        }

        public String toString() {
            return String.format("[Visibility: getter=%s,isGetter=%s,setter=%s,creator=%s,field=%s]", new Object[]{this._getterMinLevel, this._isGetterMinLevel, this._setterMinLevel, this._creatorMinLevel, this._fieldMinLevel});
        }
    }
}

