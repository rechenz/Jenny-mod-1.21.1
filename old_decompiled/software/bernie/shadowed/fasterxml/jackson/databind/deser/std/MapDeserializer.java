/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.deser.std;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonIgnoreProperties;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.databind.AnnotationIntrospector;
import software.bernie.shadowed.fasterxml.jackson.databind.BeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonMappingException;
import software.bernie.shadowed.fasterxml.jackson.databind.KeyDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.MapperFeature;
import software.bernie.shadowed.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.ContextualDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.ContextualKeyDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.NullValueProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.SettableBeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.UnresolvedForwardReference;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.ValueInstantiator;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.PropertyValueBuffer;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.ReadableObjectId;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedMember;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.util.ArrayBuilders;

@JacksonStdImpl
public class MapDeserializer
extends ContainerDeserializerBase<Map<Object, Object>>
implements ContextualDeserializer,
ResolvableDeserializer {
    private static final long serialVersionUID = 1L;
    protected final KeyDeserializer _keyDeserializer;
    protected boolean _standardStringKey;
    protected final JsonDeserializer<Object> _valueDeserializer;
    protected final TypeDeserializer _valueTypeDeserializer;
    protected final ValueInstantiator _valueInstantiator;
    protected JsonDeserializer<Object> _delegateDeserializer;
    protected PropertyBasedCreator _propertyBasedCreator;
    protected final boolean _hasDefaultCreator;
    protected Set<String> _ignorableProperties;

    public MapDeserializer(JavaType mapType, ValueInstantiator valueInstantiator, KeyDeserializer keyDeser, JsonDeserializer<Object> valueDeser, TypeDeserializer valueTypeDeser) {
        super(mapType, null, null);
        this._keyDeserializer = keyDeser;
        this._valueDeserializer = valueDeser;
        this._valueTypeDeserializer = valueTypeDeser;
        this._valueInstantiator = valueInstantiator;
        this._hasDefaultCreator = valueInstantiator.canCreateUsingDefault();
        this._delegateDeserializer = null;
        this._propertyBasedCreator = null;
        this._standardStringKey = this._isStdKeyDeser(mapType, keyDeser);
    }

    protected MapDeserializer(MapDeserializer src) {
        super(src);
        this._keyDeserializer = src._keyDeserializer;
        this._valueDeserializer = src._valueDeserializer;
        this._valueTypeDeserializer = src._valueTypeDeserializer;
        this._valueInstantiator = src._valueInstantiator;
        this._propertyBasedCreator = src._propertyBasedCreator;
        this._delegateDeserializer = src._delegateDeserializer;
        this._hasDefaultCreator = src._hasDefaultCreator;
        this._ignorableProperties = src._ignorableProperties;
        this._standardStringKey = src._standardStringKey;
    }

    protected MapDeserializer(MapDeserializer src, KeyDeserializer keyDeser, JsonDeserializer<Object> valueDeser, TypeDeserializer valueTypeDeser, NullValueProvider nuller, Set<String> ignorable) {
        super(src, nuller, src._unwrapSingle);
        this._keyDeserializer = keyDeser;
        this._valueDeserializer = valueDeser;
        this._valueTypeDeserializer = valueTypeDeser;
        this._valueInstantiator = src._valueInstantiator;
        this._propertyBasedCreator = src._propertyBasedCreator;
        this._delegateDeserializer = src._delegateDeserializer;
        this._hasDefaultCreator = src._hasDefaultCreator;
        this._ignorableProperties = ignorable;
        this._standardStringKey = this._isStdKeyDeser(this._containerType, keyDeser);
    }

    protected MapDeserializer withResolved(KeyDeserializer keyDeser, TypeDeserializer valueTypeDeser, JsonDeserializer<?> valueDeser, NullValueProvider nuller, Set<String> ignorable) {
        if (this._keyDeserializer == keyDeser && this._valueDeserializer == valueDeser && this._valueTypeDeserializer == valueTypeDeser && this._nullProvider == nuller && this._ignorableProperties == ignorable) {
            return this;
        }
        return new MapDeserializer(this, keyDeser, valueDeser, valueTypeDeser, nuller, ignorable);
    }

    protected final boolean _isStdKeyDeser(JavaType mapType, KeyDeserializer keyDeser) {
        if (keyDeser == null) {
            return true;
        }
        JavaType keyType = mapType.getKeyType();
        if (keyType == null) {
            return true;
        }
        Class<?> rawKeyType = keyType.getRawClass();
        return (rawKeyType == String.class || rawKeyType == Object.class) && this.isDefaultKeyDeserializer(keyDeser);
    }

    public void setIgnorableProperties(String[] ignorable) {
        this._ignorableProperties = ignorable == null || ignorable.length == 0 ? null : ArrayBuilders.arrayToSet(ignorable);
    }

    public void setIgnorableProperties(Set<String> ignorable) {
        this._ignorableProperties = ignorable == null || ignorable.size() == 0 ? null : ignorable;
    }

    @Override
    public void resolve(DeserializationContext ctxt) throws JsonMappingException {
        JavaType delegateType;
        if (this._valueInstantiator.canCreateUsingDelegate()) {
            delegateType = this._valueInstantiator.getDelegateType(ctxt.getConfig());
            if (delegateType == null) {
                ctxt.reportBadDefinition(this._containerType, String.format("Invalid delegate-creator definition for %s: value instantiator (%s) returned true for 'canCreateUsingDelegate()', but null for 'getDelegateType()'", this._containerType, this._valueInstantiator.getClass().getName()));
            }
            this._delegateDeserializer = this.findDeserializer(ctxt, delegateType, null);
        } else if (this._valueInstantiator.canCreateUsingArrayDelegate()) {
            delegateType = this._valueInstantiator.getArrayDelegateType(ctxt.getConfig());
            if (delegateType == null) {
                ctxt.reportBadDefinition(this._containerType, String.format("Invalid delegate-creator definition for %s: value instantiator (%s) returned true for 'canCreateUsingArrayDelegate()', but null for 'getArrayDelegateType()'", this._containerType, this._valueInstantiator.getClass().getName()));
            }
            this._delegateDeserializer = this.findDeserializer(ctxt, delegateType, null);
        }
        if (this._valueInstantiator.canCreateFromObjectWith()) {
            SettableBeanProperty[] creatorProps = this._valueInstantiator.getFromObjectArguments(ctxt.getConfig());
            this._propertyBasedCreator = PropertyBasedCreator.construct(ctxt, this._valueInstantiator, creatorProps, ctxt.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES));
        }
        this._standardStringKey = this._isStdKeyDeser(this._containerType, this._keyDeserializer);
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
        Set<String> ignoresToAdd;
        JsonIgnoreProperties.Value ignorals;
        AnnotatedMember member;
        KeyDeserializer keyDeser = this._keyDeserializer;
        if (keyDeser == null) {
            keyDeser = ctxt.findKeyDeserializer(this._containerType.getKeyType(), property);
        } else if (keyDeser instanceof ContextualKeyDeserializer) {
            keyDeser = ((ContextualKeyDeserializer)((Object)keyDeser)).createContextual(ctxt, property);
        }
        JsonDeserializer<Object> valueDeser = this._valueDeserializer;
        if (property != null) {
            valueDeser = this.findConvertingContentDeserializer(ctxt, property, valueDeser);
        }
        JavaType vt = this._containerType.getContentType();
        valueDeser = valueDeser == null ? ctxt.findContextualValueDeserializer(vt, property) : ctxt.handleSecondaryContextualization(valueDeser, property, vt);
        TypeDeserializer vtd = this._valueTypeDeserializer;
        if (vtd != null) {
            vtd = vtd.forProperty(property);
        }
        Set<String> ignored = this._ignorableProperties;
        AnnotationIntrospector intr = ctxt.getAnnotationIntrospector();
        if (MapDeserializer._neitherNull(intr, property) && (member = property.getMember()) != null && (ignorals = intr.findPropertyIgnorals(member)) != null && !(ignoresToAdd = ignorals.findIgnoredForDeserialization()).isEmpty()) {
            ignored = ignored == null ? new HashSet<String>() : new HashSet<String>(ignored);
            for (String str : ignoresToAdd) {
                ignored.add(str);
            }
        }
        return this.withResolved(keyDeser, vtd, valueDeser, this.findContentNullProvider(ctxt, property, valueDeser), ignored);
    }

    @Override
    public JsonDeserializer<Object> getContentDeserializer() {
        return this._valueDeserializer;
    }

    @Override
    public ValueInstantiator getValueInstantiator() {
        return this._valueInstantiator;
    }

    @Override
    public boolean isCachable() {
        return this._valueDeserializer == null && this._keyDeserializer == null && this._valueTypeDeserializer == null && this._ignorableProperties == null;
    }

    @Override
    public Map<Object, Object> deserialize(JsonParser p2, DeserializationContext ctxt) throws IOException {
        if (this._propertyBasedCreator != null) {
            return this._deserializeUsingCreator(p2, ctxt);
        }
        if (this._delegateDeserializer != null) {
            return (Map)this._valueInstantiator.createUsingDelegate(ctxt, this._delegateDeserializer.deserialize(p2, ctxt));
        }
        if (!this._hasDefaultCreator) {
            return (Map)ctxt.handleMissingInstantiator(this.getMapClass(), this.getValueInstantiator(), p2, "no default constructor found", new Object[0]);
        }
        JsonToken t2 = p2.getCurrentToken();
        if (t2 != JsonToken.START_OBJECT && t2 != JsonToken.FIELD_NAME && t2 != JsonToken.END_OBJECT) {
            if (t2 == JsonToken.VALUE_STRING) {
                return (Map)this._valueInstantiator.createFromString(ctxt, p2.getText());
            }
            return (Map)this._deserializeFromEmpty(p2, ctxt);
        }
        Map result = (Map)this._valueInstantiator.createUsingDefault(ctxt);
        if (this._standardStringKey) {
            this._readAndBindStringKeyMap(p2, ctxt, result);
            return result;
        }
        this._readAndBind(p2, ctxt, result);
        return result;
    }

    @Override
    public Map<Object, Object> deserialize(JsonParser p2, DeserializationContext ctxt, Map<Object, Object> result) throws IOException {
        p2.setCurrentValue(result);
        JsonToken t2 = p2.getCurrentToken();
        if (t2 != JsonToken.START_OBJECT && t2 != JsonToken.FIELD_NAME) {
            return (Map)ctxt.handleUnexpectedToken(this.getMapClass(), p2);
        }
        if (this._standardStringKey) {
            this._readAndUpdateStringKeyMap(p2, ctxt, result);
            return result;
        }
        this._readAndUpdate(p2, ctxt, result);
        return result;
    }

    @Override
    public Object deserializeWithType(JsonParser p2, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromObject(p2, ctxt);
    }

    public final Class<?> getMapClass() {
        return this._containerType.getRawClass();
    }

    @Override
    public JavaType getValueType() {
        return this._containerType;
    }

    protected final void _readAndBind(JsonParser p2, DeserializationContext ctxt, Map<Object, Object> result) throws IOException {
        String keyStr;
        boolean useObjectId;
        KeyDeserializer keyDes = this._keyDeserializer;
        JsonDeserializer<Object> valueDes = this._valueDeserializer;
        TypeDeserializer typeDeser = this._valueTypeDeserializer;
        MapReferringAccumulator referringAccumulator = null;
        boolean bl2 = useObjectId = valueDes.getObjectIdReader() != null;
        if (useObjectId) {
            referringAccumulator = new MapReferringAccumulator(this._containerType.getContentType().getRawClass(), result);
        }
        if (p2.isExpectedStartObjectToken()) {
            keyStr = p2.nextFieldName();
        } else {
            JsonToken t2 = p2.getCurrentToken();
            if (t2 == JsonToken.END_OBJECT) {
                return;
            }
            if (t2 != JsonToken.FIELD_NAME) {
                ctxt.reportWrongTokenException(this, JsonToken.FIELD_NAME, null, new Object[0]);
            }
            keyStr = p2.getCurrentName();
        }
        while (keyStr != null) {
            block15: {
                Object key = keyDes.deserializeKey(keyStr, ctxt);
                JsonToken t3 = p2.nextToken();
                if (this._ignorableProperties != null && this._ignorableProperties.contains(keyStr)) {
                    p2.skipChildren();
                } else {
                    try {
                        Object value;
                        if (t3 == JsonToken.VALUE_NULL) {
                            if (this._skipNullValues) break block15;
                            value = this._nullProvider.getNullValue(ctxt);
                        } else {
                            value = typeDeser == null ? valueDes.deserialize(p2, ctxt) : valueDes.deserializeWithType(p2, ctxt, typeDeser);
                        }
                        if (useObjectId) {
                            referringAccumulator.put(key, value);
                        } else {
                            result.put(key, value);
                        }
                    }
                    catch (UnresolvedForwardReference reference) {
                        this.handleUnresolvedReference(ctxt, referringAccumulator, key, reference);
                    }
                    catch (Exception e10) {
                        this.wrapAndThrow(e10, result, keyStr);
                    }
                }
            }
            keyStr = p2.nextFieldName();
        }
    }

    protected final void _readAndBindStringKeyMap(JsonParser p2, DeserializationContext ctxt, Map<Object, Object> result) throws IOException {
        JsonToken t2;
        String key;
        boolean useObjectId;
        JsonDeserializer<Object> valueDes = this._valueDeserializer;
        TypeDeserializer typeDeser = this._valueTypeDeserializer;
        MapReferringAccumulator referringAccumulator = null;
        boolean bl2 = useObjectId = valueDes.getObjectIdReader() != null;
        if (useObjectId) {
            referringAccumulator = new MapReferringAccumulator(this._containerType.getContentType().getRawClass(), result);
        }
        if (p2.isExpectedStartObjectToken()) {
            key = p2.nextFieldName();
        } else {
            t2 = p2.getCurrentToken();
            if (t2 == JsonToken.END_OBJECT) {
                return;
            }
            if (t2 != JsonToken.FIELD_NAME) {
                ctxt.reportWrongTokenException(this, JsonToken.FIELD_NAME, null, new Object[0]);
            }
            key = p2.getCurrentName();
        }
        while (key != null) {
            block15: {
                t2 = p2.nextToken();
                if (this._ignorableProperties != null && this._ignorableProperties.contains(key)) {
                    p2.skipChildren();
                } else {
                    try {
                        Object value;
                        if (t2 == JsonToken.VALUE_NULL) {
                            if (this._skipNullValues) break block15;
                            value = this._nullProvider.getNullValue(ctxt);
                        } else {
                            value = typeDeser == null ? valueDes.deserialize(p2, ctxt) : valueDes.deserializeWithType(p2, ctxt, typeDeser);
                        }
                        if (useObjectId) {
                            referringAccumulator.put(key, value);
                        } else {
                            result.put(key, value);
                        }
                    }
                    catch (UnresolvedForwardReference reference) {
                        this.handleUnresolvedReference(ctxt, referringAccumulator, key, reference);
                    }
                    catch (Exception e10) {
                        this.wrapAndThrow(e10, result, key);
                    }
                }
            }
            key = p2.nextFieldName();
        }
    }

    public Map<Object, Object> _deserializeUsingCreator(JsonParser p2, DeserializationContext ctxt) throws IOException {
        PropertyBasedCreator creator = this._propertyBasedCreator;
        PropertyValueBuffer buffer = creator.startBuilding(p2, ctxt, null);
        JsonDeserializer<Object> valueDes = this._valueDeserializer;
        TypeDeserializer typeDeser = this._valueTypeDeserializer;
        String key = p2.isExpectedStartObjectToken() ? p2.nextFieldName() : (p2.hasToken(JsonToken.FIELD_NAME) ? p2.getCurrentName() : null);
        while (key != null) {
            block13: {
                JsonToken t2 = p2.nextToken();
                if (this._ignorableProperties != null && this._ignorableProperties.contains(key)) {
                    p2.skipChildren();
                } else {
                    SettableBeanProperty prop = creator.findCreatorProperty(key);
                    if (prop != null) {
                        if (buffer.assignParameter(prop, prop.deserialize(p2, ctxt))) {
                            Map result;
                            p2.nextToken();
                            try {
                                result = (Map)creator.build(ctxt, buffer);
                            }
                            catch (Exception e10) {
                                return (Map)this.wrapAndThrow(e10, this._containerType.getRawClass(), key);
                            }
                            this._readAndBind(p2, ctxt, result);
                            return result;
                        }
                    } else {
                        Object value;
                        Object actualKey;
                        block14: {
                            actualKey = this._keyDeserializer.deserializeKey(key, ctxt);
                            try {
                                if (t2 == JsonToken.VALUE_NULL) {
                                    if (this._skipNullValues) break block13;
                                    value = this._nullProvider.getNullValue(ctxt);
                                    break block14;
                                }
                                value = typeDeser == null ? valueDes.deserialize(p2, ctxt) : valueDes.deserializeWithType(p2, ctxt, typeDeser);
                            }
                            catch (Exception e11) {
                                this.wrapAndThrow(e11, this._containerType.getRawClass(), key);
                                return null;
                            }
                        }
                        buffer.bufferMapProperty(actualKey, value);
                    }
                }
            }
            key = p2.nextFieldName();
        }
        try {
            return (Map)creator.build(ctxt, buffer);
        }
        catch (Exception e12) {
            this.wrapAndThrow(e12, this._containerType.getRawClass(), key);
            return null;
        }
    }

    protected final void _readAndUpdate(JsonParser p2, DeserializationContext ctxt, Map<Object, Object> result) throws IOException {
        String keyStr;
        KeyDeserializer keyDes = this._keyDeserializer;
        JsonDeserializer<Object> valueDes = this._valueDeserializer;
        TypeDeserializer typeDeser = this._valueTypeDeserializer;
        if (p2.isExpectedStartObjectToken()) {
            keyStr = p2.nextFieldName();
        } else {
            JsonToken t2 = p2.getCurrentToken();
            if (t2 == JsonToken.END_OBJECT) {
                return;
            }
            if (t2 != JsonToken.FIELD_NAME) {
                ctxt.reportWrongTokenException(this, JsonToken.FIELD_NAME, null, new Object[0]);
            }
            keyStr = p2.getCurrentName();
        }
        while (keyStr != null) {
            Object key = keyDes.deserializeKey(keyStr, ctxt);
            JsonToken t3 = p2.nextToken();
            if (this._ignorableProperties != null && this._ignorableProperties.contains(keyStr)) {
                p2.skipChildren();
            } else {
                try {
                    if (t3 == JsonToken.VALUE_NULL) {
                        if (!this._skipNullValues) {
                            result.put(key, this._nullProvider.getNullValue(ctxt));
                        }
                    } else {
                        Object value = result.get(key);
                        if (value != null) {
                            valueDes.deserialize(p2, ctxt, value);
                        } else {
                            value = typeDeser == null ? valueDes.deserialize(p2, ctxt) : valueDes.deserializeWithType(p2, ctxt, typeDeser);
                            result.put(key, value);
                        }
                    }
                }
                catch (Exception e10) {
                    this.wrapAndThrow(e10, result, keyStr);
                }
            }
            keyStr = p2.nextFieldName();
        }
    }

    protected final void _readAndUpdateStringKeyMap(JsonParser p2, DeserializationContext ctxt, Map<Object, Object> result) throws IOException {
        JsonToken t2;
        String key;
        JsonDeserializer<Object> valueDes = this._valueDeserializer;
        TypeDeserializer typeDeser = this._valueTypeDeserializer;
        if (p2.isExpectedStartObjectToken()) {
            key = p2.nextFieldName();
        } else {
            t2 = p2.getCurrentToken();
            if (t2 == JsonToken.END_OBJECT) {
                return;
            }
            if (t2 != JsonToken.FIELD_NAME) {
                ctxt.reportWrongTokenException(this, JsonToken.FIELD_NAME, null, new Object[0]);
            }
            key = p2.getCurrentName();
        }
        while (key != null) {
            t2 = p2.nextToken();
            if (this._ignorableProperties != null && this._ignorableProperties.contains(key)) {
                p2.skipChildren();
            } else {
                try {
                    if (t2 == JsonToken.VALUE_NULL) {
                        if (!this._skipNullValues) {
                            result.put(key, this._nullProvider.getNullValue(ctxt));
                        }
                    } else {
                        Object old = result.get(key);
                        Object value = old != null ? valueDes.deserialize(p2, ctxt, old) : (typeDeser == null ? valueDes.deserialize(p2, ctxt) : valueDes.deserializeWithType(p2, ctxt, typeDeser));
                        if (value != old) {
                            result.put(key, value);
                        }
                    }
                }
                catch (Exception e10) {
                    this.wrapAndThrow(e10, result, key);
                }
            }
            key = p2.nextFieldName();
        }
    }

    private void handleUnresolvedReference(DeserializationContext ctxt, MapReferringAccumulator accumulator, Object key, UnresolvedForwardReference reference) throws JsonMappingException {
        if (accumulator == null) {
            ctxt.reportInputMismatch(this, "Unresolved forward reference but no identity info: " + reference, new Object[0]);
        }
        ReadableObjectId.Referring referring = accumulator.handleUnresolvedReference(reference, key);
        reference.getRoid().appendReferring(referring);
    }

    static class MapReferring
    extends ReadableObjectId.Referring {
        private final MapReferringAccumulator _parent;
        public final Map<Object, Object> next = new LinkedHashMap<Object, Object>();
        public final Object key;

        MapReferring(MapReferringAccumulator parent, UnresolvedForwardReference ref, Class<?> valueType, Object key) {
            super(ref, valueType);
            this._parent = parent;
            this.key = key;
        }

        @Override
        public void handleResolvedForwardReference(Object id, Object value) throws IOException {
            this._parent.resolveForwardReference(id, value);
        }
    }

    private static final class MapReferringAccumulator {
        private final Class<?> _valueType;
        private Map<Object, Object> _result;
        private List<MapReferring> _accumulator = new ArrayList<MapReferring>();

        public MapReferringAccumulator(Class<?> valueType, Map<Object, Object> result) {
            this._valueType = valueType;
            this._result = result;
        }

        public void put(Object key, Object value) {
            if (this._accumulator.isEmpty()) {
                this._result.put(key, value);
            } else {
                MapReferring ref = this._accumulator.get(this._accumulator.size() - 1);
                ref.next.put(key, value);
            }
        }

        public ReadableObjectId.Referring handleUnresolvedReference(UnresolvedForwardReference reference, Object key) {
            MapReferring id = new MapReferring(this, reference, this._valueType, key);
            this._accumulator.add(id);
            return id;
        }

        public void resolveForwardReference(Object id, Object value) throws IOException {
            Iterator<MapReferring> iterator = this._accumulator.iterator();
            Map<Object, Object> previous = this._result;
            while (iterator.hasNext()) {
                MapReferring ref = iterator.next();
                if (ref.hasId(id)) {
                    iterator.remove();
                    previous.put(ref.key, value);
                    previous.putAll(ref.next);
                    return;
                }
                previous = ref.next;
            }
            throw new IllegalArgumentException("Trying to resolve a forward reference with id [" + id + "] that wasn't previously seen as unresolved.");
        }
    }
}

