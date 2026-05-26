/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.deser.std;

import software.bernie.shadowed.fasterxml.jackson.core.JsonLocation;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationConfig;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.PropertyMetadata;
import software.bernie.shadowed.fasterxml.jackson.databind.PropertyName;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.CreatorProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.SettableBeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.ValueInstantiator;

public class JsonLocationInstantiator
extends ValueInstantiator.Base {
    public JsonLocationInstantiator() {
        super(JsonLocation.class);
    }

    @Override
    public boolean canCreateFromObjectWith() {
        return true;
    }

    @Override
    public SettableBeanProperty[] getFromObjectArguments(DeserializationConfig config) {
        JavaType intType = config.constructType(Integer.TYPE);
        JavaType longType = config.constructType(Long.TYPE);
        return new SettableBeanProperty[]{JsonLocationInstantiator.creatorProp("sourceRef", config.constructType(Object.class), 0), JsonLocationInstantiator.creatorProp("byteOffset", longType, 1), JsonLocationInstantiator.creatorProp("charOffset", longType, 2), JsonLocationInstantiator.creatorProp("lineNr", intType, 3), JsonLocationInstantiator.creatorProp("columnNr", intType, 4)};
    }

    private static CreatorProperty creatorProp(String name, JavaType type, int index) {
        return new CreatorProperty(PropertyName.construct(name), type, null, null, null, null, index, null, PropertyMetadata.STD_REQUIRED);
    }

    @Override
    public Object createFromObjectWith(DeserializationContext ctxt, Object[] args) {
        return new JsonLocation(args[0], JsonLocationInstantiator._long(args[1]), JsonLocationInstantiator._long(args[2]), JsonLocationInstantiator._int(args[3]), JsonLocationInstantiator._int(args[4]));
    }

    private static final long _long(Object o2) {
        return o2 == null ? 0L : ((Number)o2).longValue();
    }

    private static final int _int(Object o2) {
        return o2 == null ? 0 : ((Number)o2).intValue();
    }
}

