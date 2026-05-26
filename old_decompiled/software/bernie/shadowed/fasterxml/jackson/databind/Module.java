/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind;

import java.util.Collection;
import software.bernie.shadowed.fasterxml.jackson.core.JsonFactory;
import software.bernie.shadowed.fasterxml.jackson.core.JsonGenerator;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.ObjectCodec;
import software.bernie.shadowed.fasterxml.jackson.core.Version;
import software.bernie.shadowed.fasterxml.jackson.core.Versioned;
import software.bernie.shadowed.fasterxml.jackson.databind.AbstractTypeResolver;
import software.bernie.shadowed.fasterxml.jackson.databind.AnnotationIntrospector;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationFeature;
import software.bernie.shadowed.fasterxml.jackson.databind.MapperFeature;
import software.bernie.shadowed.fasterxml.jackson.databind.PropertyNamingStrategy;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializationFeature;
import software.bernie.shadowed.fasterxml.jackson.databind.cfg.MutableConfigOverride;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.Deserializers;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.KeyDeserializers;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.ValueInstantiators;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.ClassIntrospector;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.NamedType;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.Serializers;
import software.bernie.shadowed.fasterxml.jackson.databind.type.TypeFactory;
import software.bernie.shadowed.fasterxml.jackson.databind.type.TypeModifier;

public abstract class Module
implements Versioned {
    public abstract String getModuleName();

    @Override
    public abstract Version version();

    public Object getTypeId() {
        return this.getClass().getName();
    }

    public abstract void setupModule(SetupContext var1);

    public static interface SetupContext {
        public Version getMapperVersion();

        public <C extends ObjectCodec> C getOwner();

        public TypeFactory getTypeFactory();

        public boolean isEnabled(MapperFeature var1);

        public boolean isEnabled(DeserializationFeature var1);

        public boolean isEnabled(SerializationFeature var1);

        public boolean isEnabled(JsonFactory.Feature var1);

        public boolean isEnabled(JsonParser.Feature var1);

        public boolean isEnabled(JsonGenerator.Feature var1);

        public MutableConfigOverride configOverride(Class<?> var1);

        public void addDeserializers(Deserializers var1);

        public void addKeyDeserializers(KeyDeserializers var1);

        public void addSerializers(Serializers var1);

        public void addKeySerializers(Serializers var1);

        public void addBeanDeserializerModifier(BeanDeserializerModifier var1);

        public void addBeanSerializerModifier(BeanSerializerModifier var1);

        public void addAbstractTypeResolver(AbstractTypeResolver var1);

        public void addTypeModifier(TypeModifier var1);

        public void addValueInstantiators(ValueInstantiators var1);

        public void setClassIntrospector(ClassIntrospector var1);

        public void insertAnnotationIntrospector(AnnotationIntrospector var1);

        public void appendAnnotationIntrospector(AnnotationIntrospector var1);

        public void registerSubtypes(Class<?> ... var1);

        public void registerSubtypes(NamedType ... var1);

        public void registerSubtypes(Collection<Class<?>> var1);

        public void setMixInAnnotations(Class<?> var1, Class<?> var2);

        public void addDeserializationProblemHandler(DeserializationProblemHandler var1);

        public void setNamingStrategy(PropertyNamingStrategy var1);
    }
}

