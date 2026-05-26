/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.deser.std;

import java.io.IOException;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.BeanDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.BeanDeserializerBase;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.SettableBeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.util.NameTransformer;

public class ThrowableDeserializer
extends BeanDeserializer {
    private static final long serialVersionUID = 1L;
    protected static final String PROP_NAME_MESSAGE = "message";

    public ThrowableDeserializer(BeanDeserializer baseDeserializer) {
        super(baseDeserializer);
        this._vanillaProcessing = false;
    }

    protected ThrowableDeserializer(BeanDeserializer src, NameTransformer unwrapper) {
        super((BeanDeserializerBase)src, unwrapper);
    }

    @Override
    public JsonDeserializer<Object> unwrappingDeserializer(NameTransformer unwrapper) {
        if (this.getClass() != ThrowableDeserializer.class) {
            return this;
        }
        return new ThrowableDeserializer(this, unwrapper);
    }

    @Override
    public Object deserializeFromObject(JsonParser p2, DeserializationContext ctxt) throws IOException {
        if (this._propertyBasedCreator != null) {
            return this._deserializeUsingPropertyBased(p2, ctxt);
        }
        if (this._delegateDeserializer != null) {
            return this._valueInstantiator.createUsingDelegate(ctxt, this._delegateDeserializer.deserialize(p2, ctxt));
        }
        if (this._beanType.isAbstract()) {
            return ctxt.handleMissingInstantiator(this.handledType(), this.getValueInstantiator(), p2, "abstract type (need to add/enable type information?)", new Object[0]);
        }
        boolean hasStringCreator = this._valueInstantiator.canCreateFromString();
        boolean hasDefaultCtor = this._valueInstantiator.canCreateUsingDefault();
        if (!hasStringCreator && !hasDefaultCtor) {
            return ctxt.handleMissingInstantiator(this.handledType(), this.getValueInstantiator(), p2, "Throwable needs a default contructor, a single-String-arg constructor; or explicit @JsonCreator", new Object[0]);
        }
        Object throwable = null;
        Object[] pending = null;
        int pendingIx = 0;
        while (p2.getCurrentToken() != JsonToken.END_OBJECT) {
            String propName = p2.getCurrentName();
            SettableBeanProperty prop = this._beanProperties.find(propName);
            p2.nextToken();
            if (prop != null) {
                if (throwable != null) {
                    prop.deserializeAndSet(p2, ctxt, throwable);
                } else {
                    if (pending == null) {
                        int len = this._beanProperties.size();
                        pending = new Object[len + len];
                    }
                    pending[pendingIx++] = prop;
                    pending[pendingIx++] = prop.deserialize(p2, ctxt);
                }
            } else if (PROP_NAME_MESSAGE.equals(propName) && hasStringCreator) {
                throwable = this._valueInstantiator.createFromString(ctxt, p2.getText());
                if (pending != null) {
                    int len = pendingIx;
                    for (int i2 = 0; i2 < len; i2 += 2) {
                        prop = (SettableBeanProperty)pending[i2];
                        prop.set(throwable, pending[i2 + 1]);
                    }
                    pending = null;
                }
            } else if (this._ignorableProps != null && this._ignorableProps.contains(propName)) {
                p2.skipChildren();
            } else if (this._anySetter != null) {
                this._anySetter.deserializeAndSet(p2, ctxt, throwable, propName);
            } else {
                this.handleUnknownProperty(p2, ctxt, throwable, propName);
            }
            p2.nextToken();
        }
        if (throwable == null) {
            throwable = hasStringCreator ? this._valueInstantiator.createFromString(ctxt, null) : this._valueInstantiator.createUsingDefault(ctxt);
            if (pending != null) {
                int len = pendingIx;
                for (int i3 = 0; i3 < len; i3 += 2) {
                    SettableBeanProperty prop = (SettableBeanProperty)pending[i3];
                    prop.set(throwable, pending[i3 + 1]);
                }
            }
        }
        return throwable;
    }
}

