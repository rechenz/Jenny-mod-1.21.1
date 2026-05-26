/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.cfg;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Locale;
import java.util.TimeZone;
import software.bernie.shadowed.fasterxml.jackson.core.Base64Variant;
import software.bernie.shadowed.fasterxml.jackson.databind.AnnotationIntrospector;
import software.bernie.shadowed.fasterxml.jackson.databind.PropertyNamingStrategy;
import software.bernie.shadowed.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotationIntrospectorPair;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.ClassIntrospector;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import software.bernie.shadowed.fasterxml.jackson.databind.type.TypeFactory;
import software.bernie.shadowed.fasterxml.jackson.databind.util.StdDateFormat;

public final class BaseSettings
implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final TimeZone DEFAULT_TIMEZONE = TimeZone.getTimeZone("UTC");
    protected final ClassIntrospector _classIntrospector;
    protected final AnnotationIntrospector _annotationIntrospector;
    protected final PropertyNamingStrategy _propertyNamingStrategy;
    protected final TypeFactory _typeFactory;
    protected final TypeResolverBuilder<?> _typeResolverBuilder;
    protected final DateFormat _dateFormat;
    protected final HandlerInstantiator _handlerInstantiator;
    protected final Locale _locale;
    protected final TimeZone _timeZone;
    protected final Base64Variant _defaultBase64;

    public BaseSettings(ClassIntrospector ci2, AnnotationIntrospector ai2, PropertyNamingStrategy pns, TypeFactory tf, TypeResolverBuilder<?> typer, DateFormat dateFormat, HandlerInstantiator hi, Locale locale, TimeZone tz, Base64Variant defaultBase64) {
        this._classIntrospector = ci2;
        this._annotationIntrospector = ai2;
        this._propertyNamingStrategy = pns;
        this._typeFactory = tf;
        this._typeResolverBuilder = typer;
        this._dateFormat = dateFormat;
        this._handlerInstantiator = hi;
        this._locale = locale;
        this._timeZone = tz;
        this._defaultBase64 = defaultBase64;
    }

    public BaseSettings withClassIntrospector(ClassIntrospector ci2) {
        if (this._classIntrospector == ci2) {
            return this;
        }
        return new BaseSettings(ci2, this._annotationIntrospector, this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, this._dateFormat, this._handlerInstantiator, this._locale, this._timeZone, this._defaultBase64);
    }

    public BaseSettings withAnnotationIntrospector(AnnotationIntrospector ai2) {
        if (this._annotationIntrospector == ai2) {
            return this;
        }
        return new BaseSettings(this._classIntrospector, ai2, this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, this._dateFormat, this._handlerInstantiator, this._locale, this._timeZone, this._defaultBase64);
    }

    public BaseSettings withInsertedAnnotationIntrospector(AnnotationIntrospector ai2) {
        return this.withAnnotationIntrospector(AnnotationIntrospectorPair.create(ai2, this._annotationIntrospector));
    }

    public BaseSettings withAppendedAnnotationIntrospector(AnnotationIntrospector ai2) {
        return this.withAnnotationIntrospector(AnnotationIntrospectorPair.create(this._annotationIntrospector, ai2));
    }

    public BaseSettings withPropertyNamingStrategy(PropertyNamingStrategy pns) {
        if (this._propertyNamingStrategy == pns) {
            return this;
        }
        return new BaseSettings(this._classIntrospector, this._annotationIntrospector, pns, this._typeFactory, this._typeResolverBuilder, this._dateFormat, this._handlerInstantiator, this._locale, this._timeZone, this._defaultBase64);
    }

    public BaseSettings withTypeFactory(TypeFactory tf) {
        if (this._typeFactory == tf) {
            return this;
        }
        return new BaseSettings(this._classIntrospector, this._annotationIntrospector, this._propertyNamingStrategy, tf, this._typeResolverBuilder, this._dateFormat, this._handlerInstantiator, this._locale, this._timeZone, this._defaultBase64);
    }

    public BaseSettings withTypeResolverBuilder(TypeResolverBuilder<?> typer) {
        if (this._typeResolverBuilder == typer) {
            return this;
        }
        return new BaseSettings(this._classIntrospector, this._annotationIntrospector, this._propertyNamingStrategy, this._typeFactory, typer, this._dateFormat, this._handlerInstantiator, this._locale, this._timeZone, this._defaultBase64);
    }

    public BaseSettings withDateFormat(DateFormat df2) {
        if (this._dateFormat == df2) {
            return this;
        }
        if (df2 != null && this.hasExplicitTimeZone()) {
            df2 = this._force(df2, this._timeZone);
        }
        return new BaseSettings(this._classIntrospector, this._annotationIntrospector, this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, df2, this._handlerInstantiator, this._locale, this._timeZone, this._defaultBase64);
    }

    public BaseSettings withHandlerInstantiator(HandlerInstantiator hi) {
        if (this._handlerInstantiator == hi) {
            return this;
        }
        return new BaseSettings(this._classIntrospector, this._annotationIntrospector, this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, this._dateFormat, hi, this._locale, this._timeZone, this._defaultBase64);
    }

    public BaseSettings with(Locale l2) {
        if (this._locale == l2) {
            return this;
        }
        return new BaseSettings(this._classIntrospector, this._annotationIntrospector, this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, this._dateFormat, this._handlerInstantiator, l2, this._timeZone, this._defaultBase64);
    }

    public BaseSettings with(TimeZone tz) {
        if (tz == null) {
            throw new IllegalArgumentException();
        }
        if (tz == this._timeZone) {
            return this;
        }
        DateFormat df2 = this._force(this._dateFormat, tz);
        return new BaseSettings(this._classIntrospector, this._annotationIntrospector, this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, df2, this._handlerInstantiator, this._locale, tz, this._defaultBase64);
    }

    public BaseSettings with(Base64Variant base64) {
        if (base64 == this._defaultBase64) {
            return this;
        }
        return new BaseSettings(this._classIntrospector, this._annotationIntrospector, this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, this._dateFormat, this._handlerInstantiator, this._locale, this._timeZone, base64);
    }

    public ClassIntrospector getClassIntrospector() {
        return this._classIntrospector;
    }

    public AnnotationIntrospector getAnnotationIntrospector() {
        return this._annotationIntrospector;
    }

    public PropertyNamingStrategy getPropertyNamingStrategy() {
        return this._propertyNamingStrategy;
    }

    public TypeFactory getTypeFactory() {
        return this._typeFactory;
    }

    public TypeResolverBuilder<?> getTypeResolverBuilder() {
        return this._typeResolverBuilder;
    }

    public DateFormat getDateFormat() {
        return this._dateFormat;
    }

    public HandlerInstantiator getHandlerInstantiator() {
        return this._handlerInstantiator;
    }

    public Locale getLocale() {
        return this._locale;
    }

    public TimeZone getTimeZone() {
        TimeZone tz = this._timeZone;
        return tz == null ? DEFAULT_TIMEZONE : tz;
    }

    public boolean hasExplicitTimeZone() {
        return this._timeZone != null;
    }

    public Base64Variant getBase64Variant() {
        return this._defaultBase64;
    }

    private DateFormat _force(DateFormat df2, TimeZone tz) {
        if (df2 instanceof StdDateFormat) {
            return ((StdDateFormat)df2).withTimeZone(tz);
        }
        df2 = (DateFormat)df2.clone();
        df2.setTimeZone(tz);
        return df2;
    }
}

