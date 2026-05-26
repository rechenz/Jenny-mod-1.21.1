/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.deser.std;

import java.io.IOException;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationFeature;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;

public class StackTraceElementDeserializer
extends StdScalarDeserializer<StackTraceElement> {
    private static final long serialVersionUID = 1L;

    public StackTraceElementDeserializer() {
        super(StackTraceElement.class);
    }

    @Override
    public StackTraceElement deserialize(JsonParser p2, DeserializationContext ctxt) throws IOException {
        JsonToken t2 = p2.getCurrentToken();
        if (t2 == JsonToken.START_OBJECT) {
            String className = "";
            String methodName = "";
            String fileName = "";
            String moduleName = null;
            String moduleVersion = null;
            String classLoaderName = null;
            int lineNumber = -1;
            while ((t2 = p2.nextValue()) != JsonToken.END_OBJECT) {
                String propName = p2.getCurrentName();
                if ("className".equals(propName)) {
                    className = p2.getText();
                    continue;
                }
                if ("classLoaderName".equals(propName)) {
                    classLoaderName = p2.getText();
                    continue;
                }
                if ("fileName".equals(propName)) {
                    fileName = p2.getText();
                    continue;
                }
                if ("lineNumber".equals(propName)) {
                    if (t2.isNumeric()) {
                        lineNumber = p2.getIntValue();
                        continue;
                    }
                    lineNumber = this._parseIntPrimitive(p2, ctxt);
                    continue;
                }
                if ("methodName".equals(propName)) {
                    methodName = p2.getText();
                    continue;
                }
                if ("nativeMethod".equals(propName)) continue;
                if ("moduleName".equals(propName)) {
                    moduleName = p2.getText();
                    continue;
                }
                if ("moduleVersion".equals(propName)) {
                    moduleVersion = p2.getText();
                    continue;
                }
                this.handleUnknownProperty(p2, ctxt, this._valueClass, propName);
            }
            return this.constructValue(ctxt, className, methodName, fileName, lineNumber, moduleName, moduleVersion, classLoaderName);
        }
        if (t2 == JsonToken.START_ARRAY && ctxt.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
            p2.nextToken();
            StackTraceElement value = this.deserialize(p2, ctxt);
            if (p2.nextToken() != JsonToken.END_ARRAY) {
                this.handleMissingEndArrayForSingle(p2, ctxt);
            }
            return value;
        }
        return (StackTraceElement)ctxt.handleUnexpectedToken(this._valueClass, p2);
    }

    @Deprecated
    protected StackTraceElement constructValue(DeserializationContext ctxt, String className, String methodName, String fileName, int lineNumber, String moduleName, String moduleVersion) {
        return this.constructValue(ctxt, className, methodName, fileName, lineNumber, moduleName, moduleVersion, null);
    }

    protected StackTraceElement constructValue(DeserializationContext ctxt, String className, String methodName, String fileName, int lineNumber, String moduleName, String moduleVersion, String classLoaderName) {
        return new StackTraceElement(className, methodName, fileName, lineNumber);
    }
}

