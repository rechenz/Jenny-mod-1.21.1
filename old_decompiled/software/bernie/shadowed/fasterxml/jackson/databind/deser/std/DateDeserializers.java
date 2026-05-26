/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.deser.std;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Locale;
import java.util.TimeZone;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonFormat;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.databind.BeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonMappingException;
import software.bernie.shadowed.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.ContextualDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.util.ClassUtil;
import software.bernie.shadowed.fasterxml.jackson.databind.util.StdDateFormat;

public class DateDeserializers {
    private static final HashSet<String> _classNames;

    public static JsonDeserializer<?> find(Class<?> rawType, String clsName) {
        if (_classNames.contains(clsName)) {
            if (rawType == Calendar.class) {
                return new CalendarDeserializer();
            }
            if (rawType == java.util.Date.class) {
                return DateDeserializer.instance;
            }
            if (rawType == Date.class) {
                return new SqlDateDeserializer();
            }
            if (rawType == Timestamp.class) {
                return new TimestampDeserializer();
            }
            if (rawType == GregorianCalendar.class) {
                return new CalendarDeserializer((Class<? extends Calendar>)GregorianCalendar.class);
            }
        }
        return null;
    }

    static {
        Class[] numberTypes;
        _classNames = new HashSet();
        for (Class cls : numberTypes = new Class[]{Calendar.class, GregorianCalendar.class, Date.class, java.util.Date.class, Timestamp.class}) {
            _classNames.add(cls.getName());
        }
    }

    public static class TimestampDeserializer
    extends DateBasedDeserializer<Timestamp> {
        public TimestampDeserializer() {
            super(Timestamp.class);
        }

        public TimestampDeserializer(TimestampDeserializer src, DateFormat df2, String formatString) {
            super(src, df2, formatString);
        }

        protected TimestampDeserializer withDateFormat(DateFormat df2, String formatString) {
            return new TimestampDeserializer(this, df2, formatString);
        }

        @Override
        public Timestamp deserialize(JsonParser p2, DeserializationContext ctxt) throws IOException {
            java.util.Date d10 = this._parseDate(p2, ctxt);
            return d10 == null ? null : new Timestamp(d10.getTime());
        }
    }

    public static class SqlDateDeserializer
    extends DateBasedDeserializer<Date> {
        public SqlDateDeserializer() {
            super(Date.class);
        }

        public SqlDateDeserializer(SqlDateDeserializer src, DateFormat df2, String formatString) {
            super(src, df2, formatString);
        }

        protected SqlDateDeserializer withDateFormat(DateFormat df2, String formatString) {
            return new SqlDateDeserializer(this, df2, formatString);
        }

        @Override
        public Date deserialize(JsonParser p2, DeserializationContext ctxt) throws IOException {
            java.util.Date d10 = this._parseDate(p2, ctxt);
            return d10 == null ? null : new Date(d10.getTime());
        }
    }

    @JacksonStdImpl
    public static class DateDeserializer
    extends DateBasedDeserializer<java.util.Date> {
        public static final DateDeserializer instance = new DateDeserializer();

        public DateDeserializer() {
            super(java.util.Date.class);
        }

        public DateDeserializer(DateDeserializer base, DateFormat df2, String formatString) {
            super(base, df2, formatString);
        }

        protected DateDeserializer withDateFormat(DateFormat df2, String formatString) {
            return new DateDeserializer(this, df2, formatString);
        }

        @Override
        public java.util.Date deserialize(JsonParser p2, DeserializationContext ctxt) throws IOException {
            return this._parseDate(p2, ctxt);
        }
    }

    @JacksonStdImpl
    public static class CalendarDeserializer
    extends DateBasedDeserializer<Calendar> {
        protected final Constructor<Calendar> _defaultCtor;

        public CalendarDeserializer() {
            super(Calendar.class);
            this._defaultCtor = null;
        }

        public CalendarDeserializer(Class<? extends Calendar> cc2) {
            super(cc2);
            this._defaultCtor = ClassUtil.findConstructor(cc2, false);
        }

        public CalendarDeserializer(CalendarDeserializer src, DateFormat df2, String formatString) {
            super(src, df2, formatString);
            this._defaultCtor = src._defaultCtor;
        }

        protected CalendarDeserializer withDateFormat(DateFormat df2, String formatString) {
            return new CalendarDeserializer(this, df2, formatString);
        }

        @Override
        public Calendar deserialize(JsonParser p2, DeserializationContext ctxt) throws IOException {
            java.util.Date d10 = this._parseDate(p2, ctxt);
            if (d10 == null) {
                return null;
            }
            if (this._defaultCtor == null) {
                return ctxt.constructCalendar(d10);
            }
            try {
                Calendar c10 = this._defaultCtor.newInstance(new Object[0]);
                c10.setTimeInMillis(d10.getTime());
                TimeZone tz = ctxt.getTimeZone();
                if (tz != null) {
                    c10.setTimeZone(tz);
                }
                return c10;
            }
            catch (Exception e10) {
                return (Calendar)ctxt.handleInstantiationProblem(this.handledType(), d10, e10);
            }
        }
    }

    protected static abstract class DateBasedDeserializer<T>
    extends StdScalarDeserializer<T>
    implements ContextualDeserializer {
        protected final DateFormat _customFormat;
        protected final String _formatString;

        protected DateBasedDeserializer(Class<?> clz) {
            super(clz);
            this._customFormat = null;
            this._formatString = null;
        }

        protected DateBasedDeserializer(DateBasedDeserializer<T> base, DateFormat format, String formatStr) {
            super(base._valueClass);
            this._customFormat = format;
            this._formatString = formatStr;
        }

        protected abstract DateBasedDeserializer<T> withDateFormat(DateFormat var1, String var2);

        @Override
        public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
            JsonFormat.Value format = this.findFormatOverrides(ctxt, property, this.handledType());
            if (format != null) {
                TimeZone tz = format.getTimeZone();
                Boolean lenient = format.getLenient();
                if (format.hasPattern()) {
                    String pattern = format.getPattern();
                    Locale loc = format.hasLocale() ? format.getLocale() : ctxt.getLocale();
                    SimpleDateFormat df2 = new SimpleDateFormat(pattern, loc);
                    if (tz == null) {
                        tz = ctxt.getTimeZone();
                    }
                    df2.setTimeZone(tz);
                    if (lenient != null) {
                        df2.setLenient(lenient);
                    }
                    return this.withDateFormat(df2, pattern);
                }
                if (tz != null) {
                    DateFormat df3 = ctxt.getConfig().getDateFormat();
                    if (df3.getClass() == StdDateFormat.class) {
                        Locale loc = format.hasLocale() ? format.getLocale() : ctxt.getLocale();
                        StdDateFormat std = (StdDateFormat)df3;
                        std = std.withTimeZone(tz);
                        std = std.withLocale(loc);
                        if (lenient != null) {
                            std = std.withLenient(lenient);
                        }
                        df3 = std;
                    } else {
                        df3 = (DateFormat)df3.clone();
                        df3.setTimeZone(tz);
                        if (lenient != null) {
                            df3.setLenient(lenient);
                        }
                    }
                    return this.withDateFormat(df3, this._formatString);
                }
                if (lenient != null) {
                    DateFormat df4 = ctxt.getConfig().getDateFormat();
                    String pattern = this._formatString;
                    if (df4.getClass() == StdDateFormat.class) {
                        StdDateFormat std = (StdDateFormat)df4;
                        std = std.withLenient(lenient);
                        df4 = std;
                        pattern = std.toPattern();
                    } else {
                        df4 = (DateFormat)df4.clone();
                        df4.setLenient(lenient);
                        if (df4 instanceof SimpleDateFormat) {
                            ((SimpleDateFormat)df4).toPattern();
                        }
                    }
                    if (pattern == null) {
                        pattern = "[unknown]";
                    }
                    return this.withDateFormat(df4, pattern);
                }
            }
            return this;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        protected java.util.Date _parseDate(JsonParser p2, DeserializationContext ctxt) throws IOException {
            if (this._customFormat != null && p2.hasToken(JsonToken.VALUE_STRING)) {
                String str = p2.getText().trim();
                if (str.length() == 0) {
                    return (java.util.Date)this.getEmptyValue(ctxt);
                }
                DateFormat dateFormat = this._customFormat;
                synchronized (dateFormat) {
                    try {
                        return this._customFormat.parse(str);
                    }
                    catch (ParseException e10) {
                        return (java.util.Date)ctxt.handleWeirdStringValue(this.handledType(), str, "expected format \"%s\"", this._formatString);
                    }
                }
            }
            return super._parseDate(p2, ctxt);
        }
    }
}

