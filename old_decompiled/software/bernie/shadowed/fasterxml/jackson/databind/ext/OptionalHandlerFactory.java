/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.ext;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import software.bernie.shadowed.fasterxml.jackson.databind.BeanDescription;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationConfig;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonMappingException;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializationConfig;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.Deserializers;
import software.bernie.shadowed.fasterxml.jackson.databind.ext.Java7Support;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.Serializers;
import software.bernie.shadowed.fasterxml.jackson.databind.util.ClassUtil;

public class OptionalHandlerFactory
implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String PACKAGE_PREFIX_JAVAX_XML = "javax.xml.";
    private static final String SERIALIZERS_FOR_JAVAX_XML = "software.bernie.shadowed.fasterxml.jackson.databind.ext.CoreXMLSerializers";
    private static final String DESERIALIZERS_FOR_JAVAX_XML = "software.bernie.shadowed.fasterxml.jackson.databind.ext.CoreXMLDeserializers";
    private static final String SERIALIZER_FOR_DOM_NODE = "software.bernie.shadowed.fasterxml.jackson.databind.ext.DOMSerializer";
    private static final String DESERIALIZER_FOR_DOM_DOCUMENT = "software.bernie.shadowed.fasterxml.jackson.databind.ext.DOMDeserializer$DocumentDeserializer";
    private static final String DESERIALIZER_FOR_DOM_NODE = "software.bernie.shadowed.fasterxml.jackson.databind.ext.DOMDeserializer$NodeDeserializer";
    private static final Class<?> CLASS_DOM_NODE;
    private static final Class<?> CLASS_DOM_DOCUMENT;
    private static final Java7Support _jdk7Helper;
    public static final OptionalHandlerFactory instance;

    protected OptionalHandlerFactory() {
    }

    public JsonSerializer<?> findSerializer(SerializationConfig config, JavaType type, BeanDescription beanDesc) {
        JsonSerializer<?> ser;
        Class<?> rawType = type.getRawClass();
        if (_jdk7Helper != null && (ser = _jdk7Helper.getSerializerForJavaNioFilePath(rawType)) != null) {
            return ser;
        }
        if (CLASS_DOM_NODE != null && CLASS_DOM_NODE.isAssignableFrom(rawType)) {
            return (JsonSerializer)this.instantiate(SERIALIZER_FOR_DOM_NODE);
        }
        String className = rawType.getName();
        if (!className.startsWith(PACKAGE_PREFIX_JAVAX_XML) && !this.hasSuperClassStartingWith(rawType, PACKAGE_PREFIX_JAVAX_XML)) {
            return null;
        }
        String factoryName = SERIALIZERS_FOR_JAVAX_XML;
        Object ob = this.instantiate(factoryName);
        if (ob == null) {
            return null;
        }
        return ((Serializers)ob).findSerializer(config, type, beanDesc);
    }

    public JsonDeserializer<?> findDeserializer(JavaType type, DeserializationConfig config, BeanDescription beanDesc) throws JsonMappingException {
        JsonDeserializer<?> deser;
        Class<?> rawType = type.getRawClass();
        if (_jdk7Helper != null && (deser = _jdk7Helper.getDeserializerForJavaNioFilePath(rawType)) != null) {
            return deser;
        }
        if (CLASS_DOM_NODE != null && CLASS_DOM_NODE.isAssignableFrom(rawType)) {
            return (JsonDeserializer)this.instantiate(DESERIALIZER_FOR_DOM_NODE);
        }
        if (CLASS_DOM_DOCUMENT != null && CLASS_DOM_DOCUMENT.isAssignableFrom(rawType)) {
            return (JsonDeserializer)this.instantiate(DESERIALIZER_FOR_DOM_DOCUMENT);
        }
        String className = rawType.getName();
        if (!className.startsWith(PACKAGE_PREFIX_JAVAX_XML) && !this.hasSuperClassStartingWith(rawType, PACKAGE_PREFIX_JAVAX_XML)) {
            return null;
        }
        String factoryName = DESERIALIZERS_FOR_JAVAX_XML;
        Object ob = this.instantiate(factoryName);
        if (ob == null) {
            return null;
        }
        return ((Deserializers)ob).findBeanDeserializer(type, config, beanDesc);
    }

    private Object instantiate(String className) {
        try {
            return ClassUtil.createInstance(Class.forName(className), false);
        }
        catch (LinkageError e10) {
        }
        catch (Exception exception) {
            // empty catch block
        }
        return null;
    }

    private boolean hasSuperClassStartingWith(Class<?> rawType, String prefix) {
        for (Class<?> supertype = rawType.getSuperclass(); supertype != null; supertype = supertype.getSuperclass()) {
            if (supertype == Object.class) {
                return false;
            }
            if (!supertype.getName().startsWith(prefix)) continue;
            return true;
        }
        return false;
    }

    static {
        Class<Document> doc = null;
        Class<Node> node = null;
        try {
            node = Node.class;
            doc = Document.class;
        }
        catch (Exception e10) {
            Logger.getLogger(OptionalHandlerFactory.class.getName()).log(Level.INFO, "Could not load DOM `Node` and/or `Document` classes: no DOM support");
        }
        CLASS_DOM_NODE = node;
        CLASS_DOM_DOCUMENT = doc;
        Java7Support x2 = null;
        try {
            x2 = Java7Support.instance();
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        _jdk7Helper = x2;
        instance = new OptionalHandlerFactory();
    }
}

