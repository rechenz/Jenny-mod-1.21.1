/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.introspect;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonFormat;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonInclude;
import software.bernie.shadowed.fasterxml.jackson.databind.AnnotationIntrospector;
import software.bernie.shadowed.fasterxml.jackson.databind.BeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.PropertyMetadata;
import software.bernie.shadowed.fasterxml.jackson.databind.PropertyName;
import software.bernie.shadowed.fasterxml.jackson.databind.cfg.MapperConfig;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedMember;

public abstract class ConcreteBeanPropertyBase
implements BeanProperty,
Serializable {
    private static final long serialVersionUID = 1L;
    protected final PropertyMetadata _metadata;
    protected transient JsonFormat.Value _propertyFormat;
    protected transient List<PropertyName> _aliases;

    protected ConcreteBeanPropertyBase(PropertyMetadata md) {
        this._metadata = md == null ? PropertyMetadata.STD_REQUIRED_OR_OPTIONAL : md;
    }

    protected ConcreteBeanPropertyBase(ConcreteBeanPropertyBase src) {
        this._metadata = src._metadata;
        this._propertyFormat = src._propertyFormat;
    }

    @Override
    public boolean isRequired() {
        return this._metadata.isRequired();
    }

    @Override
    public PropertyMetadata getMetadata() {
        return this._metadata;
    }

    @Override
    public boolean isVirtual() {
        return false;
    }

    @Override
    @Deprecated
    public final JsonFormat.Value findFormatOverrides(AnnotationIntrospector intr) {
        AnnotatedMember member;
        JsonFormat.Value f10 = null;
        if (intr != null && (member = this.getMember()) != null) {
            f10 = intr.findFormat(member);
        }
        if (f10 == null) {
            f10 = EMPTY_FORMAT;
        }
        return f10;
    }

    @Override
    public JsonFormat.Value findPropertyFormat(MapperConfig<?> config, Class<?> baseType) {
        JsonFormat.Value v2 = this._propertyFormat;
        if (v2 == null) {
            AnnotatedMember member;
            JsonFormat.Value v1 = config.getDefaultPropertyFormat(baseType);
            JsonFormat.Value v22 = null;
            AnnotationIntrospector intr = config.getAnnotationIntrospector();
            if (intr != null && (member = this.getMember()) != null) {
                v22 = intr.findFormat(member);
            }
            v2 = v1 == null ? (v22 == null ? EMPTY_FORMAT : v22) : (v22 == null ? v1 : v1.withOverrides(v22));
            this._propertyFormat = v2;
        }
        return v2;
    }

    @Override
    public JsonInclude.Value findPropertyInclusion(MapperConfig<?> config, Class<?> baseType) {
        AnnotationIntrospector intr = config.getAnnotationIntrospector();
        AnnotatedMember member = this.getMember();
        if (member == null) {
            JsonInclude.Value def = config.getDefaultPropertyInclusion(baseType);
            return def;
        }
        JsonInclude.Value v0 = config.getDefaultInclusion(baseType, member.getRawType());
        if (intr == null) {
            return v0;
        }
        JsonInclude.Value v2 = intr.findPropertyInclusion(member);
        if (v0 == null) {
            return v2;
        }
        return v0.withOverrides(v2);
    }

    @Override
    public List<PropertyName> findAliases(MapperConfig<?> config) {
        List<PropertyName> aliases = this._aliases;
        if (aliases == null) {
            AnnotationIntrospector intr = config.getAnnotationIntrospector();
            if (intr != null) {
                aliases = intr.findPropertyAliases(this.getMember());
            }
            if (aliases == null) {
                aliases = Collections.emptyList();
            }
            this._aliases = aliases;
        }
        return aliases;
    }
}

