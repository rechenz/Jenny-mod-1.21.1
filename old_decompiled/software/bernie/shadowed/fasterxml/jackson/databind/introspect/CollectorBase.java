/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.introspect;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import software.bernie.shadowed.fasterxml.jackson.databind.AnnotationIntrospector;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotationCollector;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotationMap;
import software.bernie.shadowed.fasterxml.jackson.databind.util.ClassUtil;

class CollectorBase {
    protected static final AnnotationMap[] NO_ANNOTATION_MAPS = new AnnotationMap[0];
    protected static final Annotation[] NO_ANNOTATIONS = new Annotation[0];
    protected final AnnotationIntrospector _intr;

    protected CollectorBase(AnnotationIntrospector intr) {
        this._intr = intr;
    }

    protected final AnnotationCollector collectAnnotations(Annotation[] anns) {
        AnnotationCollector c10 = AnnotationCollector.emptyCollector();
        for (Annotation ann : anns) {
            c10 = c10.addOrOverride(ann);
            if (!this._intr.isAnnotationBundle(ann)) continue;
            c10 = this.collectFromBundle(c10, ann);
        }
        return c10;
    }

    protected final AnnotationCollector collectAnnotations(AnnotationCollector c10, Annotation[] anns) {
        for (Annotation ann : anns) {
            c10 = c10.addOrOverride(ann);
            if (!this._intr.isAnnotationBundle(ann)) continue;
            c10 = this.collectFromBundle(c10, ann);
        }
        return c10;
    }

    protected final AnnotationCollector collectFromBundle(AnnotationCollector c10, Annotation bundle) {
        for (Annotation ann : ClassUtil.findClassAnnotations(bundle.annotationType())) {
            if (CollectorBase._ignorableAnnotation(ann)) continue;
            if (this._intr.isAnnotationBundle(ann)) {
                if (c10.isPresent(ann)) continue;
                c10 = c10.addOrOverride(ann);
                c10 = this.collectFromBundle(c10, ann);
                continue;
            }
            c10 = c10.addOrOverride(ann);
        }
        return c10;
    }

    protected final AnnotationCollector collectDefaultAnnotations(AnnotationCollector c10, Annotation[] anns) {
        for (Annotation ann : anns) {
            if (c10.isPresent(ann)) continue;
            c10 = c10.addOrOverride(ann);
            if (!this._intr.isAnnotationBundle(ann)) continue;
            c10 = this.collectDefaultFromBundle(c10, ann);
        }
        return c10;
    }

    protected final AnnotationCollector collectDefaultFromBundle(AnnotationCollector c10, Annotation bundle) {
        for (Annotation ann : ClassUtil.findClassAnnotations(bundle.annotationType())) {
            if (CollectorBase._ignorableAnnotation(ann) || c10.isPresent(ann)) continue;
            c10 = c10.addOrOverride(ann);
            if (!this._intr.isAnnotationBundle(ann)) continue;
            c10 = this.collectFromBundle(c10, ann);
        }
        return c10;
    }

    protected static final boolean _ignorableAnnotation(Annotation a10) {
        return a10 instanceof Target || a10 instanceof Retention;
    }

    static AnnotationMap _emptyAnnotationMap() {
        return new AnnotationMap();
    }

    static AnnotationMap[] _emptyAnnotationMaps(int count) {
        if (count == 0) {
            return NO_ANNOTATION_MAPS;
        }
        AnnotationMap[] maps = new AnnotationMap[count];
        for (int i2 = 0; i2 < count; ++i2) {
            maps[i2] = CollectorBase._emptyAnnotationMap();
        }
        return maps;
    }
}

