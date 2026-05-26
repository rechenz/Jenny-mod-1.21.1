/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.ext;

import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.databind.BeanDescription;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationConfig;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonMappingException;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.Deserializers;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.std.FromStringDeserializer;

public class CoreXMLDeserializers
extends Deserializers.Base {
    static final DatatypeFactory _dataTypeFactory;
    protected static final int TYPE_DURATION = 1;
    protected static final int TYPE_G_CALENDAR = 2;
    protected static final int TYPE_QNAME = 3;

    @Override
    public JsonDeserializer<?> findBeanDeserializer(JavaType type, DeserializationConfig config, BeanDescription beanDesc) {
        Class<?> raw = type.getRawClass();
        if (raw == QName.class) {
            return new Std(raw, 3);
        }
        if (raw == XMLGregorianCalendar.class) {
            return new Std(raw, 2);
        }
        if (raw == Duration.class) {
            return new Std(raw, 1);
        }
        return null;
    }

    static {
        try {
            _dataTypeFactory = DatatypeFactory.newInstance();
        }
        catch (DatatypeConfigurationException e10) {
            throw new RuntimeException(e10);
        }
    }

    public static class Std
    extends FromStringDeserializer<Object> {
        private static final long serialVersionUID = 1L;
        protected final int _kind;

        public Std(Class<?> raw, int kind) {
            super(raw);
            this._kind = kind;
        }

        @Override
        public Object deserialize(JsonParser p2, DeserializationContext ctxt) throws IOException {
            if (this._kind == 2 && p2.hasToken(JsonToken.VALUE_NUMBER_INT)) {
                return this._gregorianFromDate(ctxt, this._parseDate(p2, ctxt));
            }
            return super.deserialize(p2, ctxt);
        }

        @Override
        protected Object _deserialize(String value, DeserializationContext ctxt) throws IOException {
            switch (this._kind) {
                case 1: {
                    return _dataTypeFactory.newDuration(value);
                }
                case 3: {
                    return QName.valueOf(value);
                }
                case 2: {
                    Date d10;
                    try {
                        d10 = this._parseDate(value, ctxt);
                    }
                    catch (JsonMappingException e10) {
                        return _dataTypeFactory.newXMLGregorianCalendar(value);
                    }
                    return this._gregorianFromDate(ctxt, d10);
                }
            }
            throw new IllegalStateException();
        }

        protected XMLGregorianCalendar _gregorianFromDate(DeserializationContext ctxt, Date d10) {
            if (d10 == null) {
                return null;
            }
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(d10);
            TimeZone tz = ctxt.getTimeZone();
            if (tz != null) {
                calendar.setTimeZone(tz);
            }
            return _dataTypeFactory.newXMLGregorianCalendar(calendar);
        }
    }
}

