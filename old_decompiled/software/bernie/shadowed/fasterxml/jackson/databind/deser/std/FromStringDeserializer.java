/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.deser.std;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Currency;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.core.util.VersionUtil;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonMappingException;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.exc.InvalidFormatException;
import software.bernie.shadowed.fasterxml.jackson.databind.util.ClassUtil;

public abstract class FromStringDeserializer<T>
extends StdScalarDeserializer<T> {
    public static Class<?>[] types() {
        return new Class[]{File.class, URL.class, URI.class, Class.class, JavaType.class, Currency.class, Pattern.class, Locale.class, Charset.class, TimeZone.class, InetAddress.class, InetSocketAddress.class, StringBuilder.class};
    }

    protected FromStringDeserializer(Class<?> vc) {
        super(vc);
    }

    public static Std findDeserializer(Class<?> rawType) {
        int kind = 0;
        if (rawType == File.class) {
            kind = 1;
        } else if (rawType == URL.class) {
            kind = 2;
        } else if (rawType == URI.class) {
            kind = 3;
        } else if (rawType == Class.class) {
            kind = 4;
        } else if (rawType == JavaType.class) {
            kind = 5;
        } else if (rawType == Currency.class) {
            kind = 6;
        } else if (rawType == Pattern.class) {
            kind = 7;
        } else if (rawType == Locale.class) {
            kind = 8;
        } else if (rawType == Charset.class) {
            kind = 9;
        } else if (rawType == TimeZone.class) {
            kind = 10;
        } else if (rawType == InetAddress.class) {
            kind = 11;
        } else if (rawType == InetSocketAddress.class) {
            kind = 12;
        } else if (rawType == StringBuilder.class) {
            kind = 13;
        } else {
            return null;
        }
        return new Std(rawType, kind);
    }

    @Override
    public T deserialize(JsonParser p2, DeserializationContext ctxt) throws IOException {
        String text = p2.getValueAsString();
        if (text != null) {
            if (text.length() == 0 || (text = text.trim()).length() == 0) {
                return this._deserializeFromEmptyString();
            }
            Exception cause = null;
            try {
                return this._deserialize(text, ctxt);
            }
            catch (IllegalArgumentException | MalformedURLException e10) {
                cause = e10;
                String msg = "not a valid textual representation";
                String m2 = cause.getMessage();
                if (m2 != null) {
                    msg = msg + ", problem: " + m2;
                }
                JsonMappingException e11 = ctxt.weirdStringException(text, this._valueClass, msg);
                e11.initCause(cause);
                throw e11;
            }
        }
        JsonToken t2 = p2.getCurrentToken();
        if (t2 == JsonToken.START_ARRAY) {
            return this._deserializeFromArray(p2, ctxt);
        }
        if (t2 == JsonToken.VALUE_EMBEDDED_OBJECT) {
            Object ob = p2.getEmbeddedObject();
            if (ob == null) {
                return null;
            }
            if (this._valueClass.isAssignableFrom(ob.getClass())) {
                return (T)ob;
            }
            return this._deserializeEmbedded(ob, ctxt);
        }
        return (T)ctxt.handleUnexpectedToken(this._valueClass, p2);
    }

    protected abstract T _deserialize(String var1, DeserializationContext var2) throws IOException;

    protected T _deserializeEmbedded(Object ob, DeserializationContext ctxt) throws IOException {
        ctxt.reportInputMismatch(this, "Don't know how to convert embedded Object of type %s into %s", ob.getClass().getName(), this._valueClass.getName());
        return null;
    }

    protected T _deserializeFromEmptyString() throws IOException {
        return null;
    }

    public static class Std
    extends FromStringDeserializer<Object> {
        private static final long serialVersionUID = 1L;
        public static final int STD_FILE = 1;
        public static final int STD_URL = 2;
        public static final int STD_URI = 3;
        public static final int STD_CLASS = 4;
        public static final int STD_JAVA_TYPE = 5;
        public static final int STD_CURRENCY = 6;
        public static final int STD_PATTERN = 7;
        public static final int STD_LOCALE = 8;
        public static final int STD_CHARSET = 9;
        public static final int STD_TIME_ZONE = 10;
        public static final int STD_INET_ADDRESS = 11;
        public static final int STD_INET_SOCKET_ADDRESS = 12;
        public static final int STD_STRING_BUILDER = 13;
        protected final int _kind;

        protected Std(Class<?> valueType, int kind) {
            super(valueType);
            this._kind = kind;
        }

        @Override
        protected Object _deserialize(String value, DeserializationContext ctxt) throws IOException {
            switch (this._kind) {
                case 1: {
                    return new File(value);
                }
                case 2: {
                    return new URL(value);
                }
                case 3: {
                    return URI.create(value);
                }
                case 4: {
                    try {
                        return ctxt.findClass(value);
                    }
                    catch (Exception e10) {
                        return ctxt.handleInstantiationProblem(this._valueClass, value, ClassUtil.getRootCause(e10));
                    }
                }
                case 5: {
                    return ctxt.getTypeFactory().constructFromCanonical(value);
                }
                case 6: {
                    return Currency.getInstance(value);
                }
                case 7: {
                    return Pattern.compile(value);
                }
                case 8: {
                    int ix = this._firstHyphenOrUnderscore(value);
                    if (ix < 0) {
                        return new Locale(value);
                    }
                    String first = value.substring(0, ix);
                    if ((ix = this._firstHyphenOrUnderscore(value = value.substring(ix + 1))) < 0) {
                        return new Locale(first, value);
                    }
                    String second = value.substring(0, ix);
                    return new Locale(first, second, value.substring(ix + 1));
                }
                case 9: {
                    return Charset.forName(value);
                }
                case 10: {
                    return TimeZone.getTimeZone(value);
                }
                case 11: {
                    return InetAddress.getByName(value);
                }
                case 12: {
                    if (value.startsWith("[")) {
                        int i2 = value.lastIndexOf(93);
                        if (i2 == -1) {
                            throw new InvalidFormatException(ctxt.getParser(), "Bracketed IPv6 address must contain closing bracket", (Object)value, InetSocketAddress.class);
                        }
                        int j2 = value.indexOf(58, i2);
                        int port = j2 > -1 ? Integer.parseInt(value.substring(j2 + 1)) : 0;
                        return new InetSocketAddress(value.substring(0, i2 + 1), port);
                    }
                    int ix = value.indexOf(58);
                    if (ix >= 0 && value.indexOf(58, ix + 1) < 0) {
                        int port = Integer.parseInt(value.substring(ix + 1));
                        return new InetSocketAddress(value.substring(0, ix), port);
                    }
                    return new InetSocketAddress(value, 0);
                }
                case 13: {
                    return new StringBuilder(value);
                }
            }
            VersionUtil.throwInternal();
            return null;
        }

        @Override
        protected Object _deserializeFromEmptyString() throws IOException {
            if (this._kind == 3) {
                return URI.create("");
            }
            if (this._kind == 8) {
                return Locale.ROOT;
            }
            if (this._kind == 13) {
                return new StringBuilder();
            }
            return super._deserializeFromEmptyString();
        }

        protected int _firstHyphenOrUnderscore(String str) {
            int end = str.length();
            for (int i2 = 0; i2 < end; ++i2) {
                char c10 = str.charAt(i2);
                if (c10 != '_' && c10 != '-') continue;
                return i2;
            }
            return -1;
        }
    }
}

