/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.datatype.jsr310.deser;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.regex.Pattern;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonFormat;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.databind.BeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationFeature;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonMappingException;
import software.bernie.shadowed.fasterxml.jackson.datatype.jsr310.DecimalUtils;
import software.bernie.shadowed.fasterxml.jackson.datatype.jsr310.deser.JSR310DateTimeDeserializerBase;

public class InstantDeserializer<T extends Temporal>
extends JSR310DateTimeDeserializerBase<T> {
    private static final long serialVersionUID = 1L;
    private static final Pattern ISO8601_UTC_ZERO_OFFSET_SUFFIX_REGEX = Pattern.compile("\\+00:?(00)?$");
    public static final InstantDeserializer<Instant> INSTANT = new InstantDeserializer<Instant>(Instant.class, DateTimeFormatter.ISO_INSTANT, Instant::from, a10 -> Instant.ofEpochMilli(a10.value), a10 -> Instant.ofEpochSecond(a10.integer, a10.fraction), null, true);
    public static final InstantDeserializer<OffsetDateTime> OFFSET_DATE_TIME = new InstantDeserializer<OffsetDateTime>(OffsetDateTime.class, DateTimeFormatter.ISO_OFFSET_DATE_TIME, OffsetDateTime::from, a10 -> OffsetDateTime.ofInstant(Instant.ofEpochMilli(a10.value), a10.zoneId), a10 -> OffsetDateTime.ofInstant(Instant.ofEpochSecond(a10.integer, a10.fraction), a10.zoneId), (d10, z2) -> d10.withOffsetSameInstant(z2.getRules().getOffset(d10.toLocalDateTime())), true);
    public static final InstantDeserializer<ZonedDateTime> ZONED_DATE_TIME = new InstantDeserializer<ZonedDateTime>(ZonedDateTime.class, DateTimeFormatter.ISO_ZONED_DATE_TIME, ZonedDateTime::from, a10 -> ZonedDateTime.ofInstant(Instant.ofEpochMilli(a10.value), a10.zoneId), a10 -> ZonedDateTime.ofInstant(Instant.ofEpochSecond(a10.integer, a10.fraction), a10.zoneId), ZonedDateTime::withZoneSameInstant, false);
    protected final Function<FromIntegerArguments, T> fromMilliseconds;
    protected final Function<FromDecimalArguments, T> fromNanoseconds;
    protected final Function<TemporalAccessor, T> parsedToValue;
    protected final BiFunction<T, ZoneId, T> adjust;
    protected final boolean replaceZeroOffsetAsZ;
    protected final Boolean _adjustToContextTZOverride;

    protected InstantDeserializer(Class<T> supportedType, DateTimeFormatter formatter, Function<TemporalAccessor, T> parsedToValue, Function<FromIntegerArguments, T> fromMilliseconds, Function<FromDecimalArguments, T> fromNanoseconds, BiFunction<T, ZoneId, T> adjust, boolean replaceZeroOffsetAsZ) {
        super(supportedType, formatter);
        this.parsedToValue = parsedToValue;
        this.fromMilliseconds = fromMilliseconds;
        this.fromNanoseconds = fromNanoseconds;
        this.adjust = adjust == null ? (d10, z2) -> d10 : adjust;
        this.replaceZeroOffsetAsZ = replaceZeroOffsetAsZ;
        this._adjustToContextTZOverride = null;
    }

    protected InstantDeserializer(InstantDeserializer<T> base, DateTimeFormatter f10) {
        super(base.handledType(), f10);
        this.parsedToValue = base.parsedToValue;
        this.fromMilliseconds = base.fromMilliseconds;
        this.fromNanoseconds = base.fromNanoseconds;
        this.adjust = base.adjust;
        this.replaceZeroOffsetAsZ = this._formatter == DateTimeFormatter.ISO_INSTANT;
        this._adjustToContextTZOverride = base._adjustToContextTZOverride;
    }

    protected InstantDeserializer(InstantDeserializer<T> base, Boolean adjustToContextTimezoneOverride) {
        super(base.handledType(), base._formatter);
        this.parsedToValue = base.parsedToValue;
        this.fromMilliseconds = base.fromMilliseconds;
        this.fromNanoseconds = base.fromNanoseconds;
        this.adjust = base.adjust;
        this.replaceZeroOffsetAsZ = base.replaceZeroOffsetAsZ;
        this._adjustToContextTZOverride = adjustToContextTimezoneOverride;
    }

    @Override
    protected JsonDeserializer<T> withDateFormat(DateTimeFormatter dtf) {
        if (dtf == this._formatter) {
            return this;
        }
        return new InstantDeserializer<T>(this, dtf);
    }

    @Override
    public T deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        switch (parser.getCurrentTokenId()) {
            case 8: {
                return this._fromDecimal(context, parser.getDecimalValue());
            }
            case 7: {
                return this._fromLong(context, parser.getLongValue());
            }
            case 6: {
                Temporal value;
                String string = parser.getText().trim();
                if (string.length() == 0) {
                    return null;
                }
                if (this._formatter == DateTimeFormatter.ISO_INSTANT || this._formatter == DateTimeFormatter.ISO_OFFSET_DATE_TIME || this._formatter == DateTimeFormatter.ISO_ZONED_DATE_TIME) {
                    int dots = this._countPeriods(string);
                    if (dots >= 0) {
                        try {
                            if (dots == 0) {
                                return this._fromLong(context, Long.parseLong(string));
                            }
                            if (dots == 1) {
                                return this._fromDecimal(context, new BigDecimal(string));
                            }
                        }
                        catch (NumberFormatException numberFormatException) {
                            // empty catch block
                        }
                    }
                    string = this.replaceZeroOffsetAsZIfNecessary(string);
                }
                try {
                    TemporalAccessor acc = this._formatter.parse(string);
                    value = (Temporal)this.parsedToValue.apply(acc);
                    if (this.shouldAdjustToContextTimezone(context)) {
                        return (T)((Temporal)this.adjust.apply(value, this.getZone(context)));
                    }
                }
                catch (DateTimeException e10) {
                    value = (Temporal)this._rethrowDateTimeException(parser, context, e10, string);
                }
                return (T)value;
            }
            case 12: {
                return (T)((Temporal)parser.getEmbeddedObject());
            }
            case 3: {
                return (T)((Temporal)this._deserializeFromArray(parser, context));
            }
        }
        return (T)((Temporal)this._reportWrongToken(parser, context, JsonToken.VALUE_STRING, JsonToken.VALUE_NUMBER_INT, JsonToken.VALUE_NUMBER_FLOAT));
    }

    @Override
    public JsonDeserializer<T> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
        JsonFormat.Value val;
        InstantDeserializer deserializer = (InstantDeserializer)super.createContextual(ctxt, property);
        if (deserializer != this && (val = this.findFormatOverrides(ctxt, property, this.handledType())) != null) {
            return new InstantDeserializer<T>(deserializer, val.getFeature(JsonFormat.Feature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE));
        }
        return this;
    }

    protected boolean shouldAdjustToContextTimezone(DeserializationContext context) {
        return this._adjustToContextTZOverride != null ? this._adjustToContextTZOverride.booleanValue() : context.isEnabled(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
    }

    protected int _countPeriods(String str) {
        int commas = 0;
        int end = str.length();
        for (int i2 = 0; i2 < end; ++i2) {
            char ch2 = str.charAt(i2);
            if (ch2 >= '0' && ch2 <= '9') continue;
            if (ch2 == '.') {
                ++commas;
                continue;
            }
            return -1;
        }
        return commas;
    }

    protected T _fromLong(DeserializationContext context, long timestamp) {
        if (context.isEnabled(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS)) {
            return (T)((Temporal)this.fromNanoseconds.apply(new FromDecimalArguments(timestamp, 0, this.getZone(context))));
        }
        return (T)((Temporal)this.fromMilliseconds.apply(new FromIntegerArguments(timestamp, this.getZone(context))));
    }

    protected T _fromDecimal(DeserializationContext context, BigDecimal value) {
        long seconds = value.longValue();
        int nanoseconds = DecimalUtils.extractNanosecondDecimal(value, seconds);
        return (T)((Temporal)this.fromNanoseconds.apply(new FromDecimalArguments(seconds, nanoseconds, this.getZone(context))));
    }

    private ZoneId getZone(DeserializationContext context) {
        return this._valueClass == Instant.class ? null : context.getTimeZone().toZoneId();
    }

    private String replaceZeroOffsetAsZIfNecessary(String text) {
        if (this.replaceZeroOffsetAsZ) {
            return ISO8601_UTC_ZERO_OFFSET_SUFFIX_REGEX.matcher(text).replaceFirst("Z");
        }
        return text;
    }

    public static class FromDecimalArguments {
        public final long integer;
        public final int fraction;
        public final ZoneId zoneId;

        private FromDecimalArguments(long integer, int fraction, ZoneId zoneId) {
            this.integer = integer;
            this.fraction = fraction;
            this.zoneId = zoneId;
        }
    }

    public static class FromIntegerArguments {
        public final long value;
        public final ZoneId zoneId;

        private FromIntegerArguments(long value, ZoneId zoneId) {
            this.value = value;
            this.zoneId = zoneId;
        }
    }
}

