/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.ser.impl;

import java.io.Serializable;
import software.bernie.shadowed.fasterxml.jackson.core.JsonGenerator;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonMappingException;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializerProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import software.bernie.shadowed.fasterxml.jackson.databind.util.NameTransformer;

public abstract class FilteredBeanPropertyWriter {
    public static BeanPropertyWriter constructViewBased(BeanPropertyWriter base, Class<?>[] viewsToIncludeIn) {
        if (viewsToIncludeIn.length == 1) {
            return new SingleView(base, viewsToIncludeIn[0]);
        }
        return new MultiView(base, viewsToIncludeIn);
    }

    private static final class MultiView
    extends BeanPropertyWriter
    implements Serializable {
        private static final long serialVersionUID = 1L;
        protected final BeanPropertyWriter _delegate;
        protected final Class<?>[] _views;

        protected MultiView(BeanPropertyWriter delegate, Class<?>[] views) {
            super(delegate);
            this._delegate = delegate;
            this._views = views;
        }

        @Override
        public MultiView rename(NameTransformer transformer) {
            return new MultiView(this._delegate.rename(transformer), this._views);
        }

        @Override
        public void assignSerializer(JsonSerializer<Object> ser) {
            this._delegate.assignSerializer(ser);
        }

        @Override
        public void assignNullSerializer(JsonSerializer<Object> nullSer) {
            this._delegate.assignNullSerializer(nullSer);
        }

        @Override
        public void serializeAsField(Object bean, JsonGenerator gen, SerializerProvider prov) throws Exception {
            if (this._inView(prov.getActiveView())) {
                this._delegate.serializeAsField(bean, gen, prov);
                return;
            }
            this._delegate.serializeAsOmittedField(bean, gen, prov);
        }

        @Override
        public void serializeAsElement(Object bean, JsonGenerator gen, SerializerProvider prov) throws Exception {
            if (this._inView(prov.getActiveView())) {
                this._delegate.serializeAsElement(bean, gen, prov);
                return;
            }
            this._delegate.serializeAsPlaceholder(bean, gen, prov);
        }

        @Override
        public void depositSchemaProperty(JsonObjectFormatVisitor v2, SerializerProvider provider) throws JsonMappingException {
            if (this._inView(provider.getActiveView())) {
                super.depositSchemaProperty(v2, provider);
            }
        }

        private final boolean _inView(Class<?> activeView) {
            if (activeView == null) {
                return true;
            }
            int len = this._views.length;
            for (int i2 = 0; i2 < len; ++i2) {
                if (!this._views[i2].isAssignableFrom(activeView)) continue;
                return true;
            }
            return false;
        }
    }

    private static final class SingleView
    extends BeanPropertyWriter
    implements Serializable {
        private static final long serialVersionUID = 1L;
        protected final BeanPropertyWriter _delegate;
        protected final Class<?> _view;

        protected SingleView(BeanPropertyWriter delegate, Class<?> view) {
            super(delegate);
            this._delegate = delegate;
            this._view = view;
        }

        @Override
        public SingleView rename(NameTransformer transformer) {
            return new SingleView(this._delegate.rename(transformer), this._view);
        }

        @Override
        public void assignSerializer(JsonSerializer<Object> ser) {
            this._delegate.assignSerializer(ser);
        }

        @Override
        public void assignNullSerializer(JsonSerializer<Object> nullSer) {
            this._delegate.assignNullSerializer(nullSer);
        }

        @Override
        public void serializeAsField(Object bean, JsonGenerator gen, SerializerProvider prov) throws Exception {
            Class<?> activeView = prov.getActiveView();
            if (activeView == null || this._view.isAssignableFrom(activeView)) {
                this._delegate.serializeAsField(bean, gen, prov);
            } else {
                this._delegate.serializeAsOmittedField(bean, gen, prov);
            }
        }

        @Override
        public void serializeAsElement(Object bean, JsonGenerator gen, SerializerProvider prov) throws Exception {
            Class<?> activeView = prov.getActiveView();
            if (activeView == null || this._view.isAssignableFrom(activeView)) {
                this._delegate.serializeAsElement(bean, gen, prov);
            } else {
                this._delegate.serializeAsPlaceholder(bean, gen, prov);
            }
        }

        @Override
        public void depositSchemaProperty(JsonObjectFormatVisitor v2, SerializerProvider provider) throws JsonMappingException {
            Class<?> activeView = provider.getActiveView();
            if (activeView == null || this._view.isAssignableFrom(activeView)) {
                super.depositSchemaProperty(v2, provider);
            }
        }
    }
}

