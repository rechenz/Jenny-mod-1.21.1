/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.lang3.concurrent;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.concurrent.BackgroundInitializer;
import org.apache.commons.lang3.concurrent.ConcurrentException;

public class MultiBackgroundInitializer
extends BackgroundInitializer<MultiBackgroundInitializerResults> {
    private final Map<String, BackgroundInitializer<?>> childInitializers = new HashMap();

    public MultiBackgroundInitializer() {
    }

    public MultiBackgroundInitializer(ExecutorService exec) {
        super(exec);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void addInitializer(String name, BackgroundInitializer<?> init) {
        Validate.notNull(name, "Name of child initializer must not be null!", new Object[0]);
        Validate.notNull(init, "Child initializer must not be null!", new Object[0]);
        MultiBackgroundInitializer multiBackgroundInitializer = this;
        synchronized (multiBackgroundInitializer) {
            if (this.isStarted()) {
                throw new IllegalStateException("addInitializer() must not be called after start()!");
            }
            this.childInitializers.put(name, init);
        }
    }

    @Override
    protected int getTaskCount() {
        int result = 1;
        for (BackgroundInitializer<?> bi2 : this.childInitializers.values()) {
            result += bi2.getTaskCount();
        }
        return result;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    protected MultiBackgroundInitializerResults initialize() throws Exception {
        HashMap inits;
        MultiBackgroundInitializer multiBackgroundInitializer = this;
        synchronized (multiBackgroundInitializer) {
            inits = new HashMap(this.childInitializers);
        }
        ExecutorService exec = this.getActiveExecutor();
        for (BackgroundInitializer bi2 : inits.values()) {
            if (bi2.getExternalExecutor() == null) {
                bi2.setExternalExecutor(exec);
            }
            bi2.start();
        }
        HashMap results = new HashMap();
        HashMap excepts = new HashMap();
        for (Map.Entry e10 : inits.entrySet()) {
            try {
                results.put(e10.getKey(), ((BackgroundInitializer)e10.getValue()).get());
            }
            catch (ConcurrentException cex) {
                excepts.put(e10.getKey(), cex);
            }
        }
        return new MultiBackgroundInitializerResults(inits, results, excepts);
    }

    public static class MultiBackgroundInitializerResults {
        private final Map<String, BackgroundInitializer<?>> initializers;
        private final Map<String, Object> resultObjects;
        private final Map<String, ConcurrentException> exceptions;

        private MultiBackgroundInitializerResults(Map<String, BackgroundInitializer<?>> inits, Map<String, Object> results, Map<String, ConcurrentException> excepts) {
            this.initializers = inits;
            this.resultObjects = results;
            this.exceptions = excepts;
        }

        public BackgroundInitializer<?> getInitializer(String name) {
            return this.checkName(name);
        }

        public Object getResultObject(String name) {
            this.checkName(name);
            return this.resultObjects.get(name);
        }

        public boolean isException(String name) {
            this.checkName(name);
            return this.exceptions.containsKey(name);
        }

        public ConcurrentException getException(String name) {
            this.checkName(name);
            return this.exceptions.get(name);
        }

        public Set<String> initializerNames() {
            return Collections.unmodifiableSet(this.initializers.keySet());
        }

        public boolean isSuccessful() {
            return this.exceptions.isEmpty();
        }

        private BackgroundInitializer<?> checkName(String name) {
            BackgroundInitializer<?> init = this.initializers.get(name);
            if (init == null) {
                throw new NoSuchElementException("No child initializer with name " + name);
            }
            return init;
        }
    }
}

