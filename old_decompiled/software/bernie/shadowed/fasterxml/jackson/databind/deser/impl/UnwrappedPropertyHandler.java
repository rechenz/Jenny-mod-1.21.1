/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.deser.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.SettableBeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.util.NameTransformer;
import software.bernie.shadowed.fasterxml.jackson.databind.util.TokenBuffer;

public class UnwrappedPropertyHandler {
    protected final List<SettableBeanProperty> _properties;

    public UnwrappedPropertyHandler() {
        this._properties = new ArrayList<SettableBeanProperty>();
    }

    protected UnwrappedPropertyHandler(List<SettableBeanProperty> props) {
        this._properties = props;
    }

    public void addProperty(SettableBeanProperty property) {
        this._properties.add(property);
    }

    public UnwrappedPropertyHandler renameAll(NameTransformer transformer) {
        ArrayList<SettableBeanProperty> newProps = new ArrayList<SettableBeanProperty>(this._properties.size());
        for (SettableBeanProperty prop : this._properties) {
            JsonDeserializer<Object> newDeser;
            String newName = transformer.transform(prop.getName());
            JsonDeserializer<Object> deser = (prop = prop.withSimpleName(newName)).getValueDeserializer();
            if (deser != null && (newDeser = deser.unwrappingDeserializer(transformer)) != deser) {
                prop = prop.withValueDeserializer(newDeser);
            }
            newProps.add(prop);
        }
        return new UnwrappedPropertyHandler(newProps);
    }

    public Object processUnwrapped(JsonParser originalParser, DeserializationContext ctxt, Object bean, TokenBuffer buffered) throws IOException {
        int len = this._properties.size();
        for (int i2 = 0; i2 < len; ++i2) {
            SettableBeanProperty prop = this._properties.get(i2);
            JsonParser p2 = buffered.asParser();
            p2.nextToken();
            prop.deserializeAndSet(p2, ctxt, bean);
        }
        return bean;
    }
}

