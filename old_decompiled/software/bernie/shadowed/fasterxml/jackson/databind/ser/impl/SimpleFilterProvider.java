/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.ser.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.BeanPropertyFilter;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.FilterProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.PropertyFilter;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;

public class SimpleFilterProvider
extends FilterProvider
implements Serializable {
    private static final long serialVersionUID = 1L;
    protected final Map<String, PropertyFilter> _filtersById;
    protected PropertyFilter _defaultFilter;
    protected boolean _cfgFailOnUnknownId = true;

    public SimpleFilterProvider() {
        this(new HashMap());
    }

    public SimpleFilterProvider(Map<String, ?> mapping) {
        for (Object ob : mapping.values()) {
            if (ob instanceof PropertyFilter) continue;
            this._filtersById = SimpleFilterProvider._convert(mapping);
            return;
        }
        this._filtersById = mapping;
    }

    private static final Map<String, PropertyFilter> _convert(Map<String, ?> filters) {
        HashMap<String, PropertyFilter> result = new HashMap<String, PropertyFilter>();
        for (Map.Entry<String, ?> entry : filters.entrySet()) {
            Object f10 = entry.getValue();
            if (f10 instanceof PropertyFilter) {
                result.put(entry.getKey(), (PropertyFilter)f10);
                continue;
            }
            if (f10 instanceof BeanPropertyFilter) {
                result.put(entry.getKey(), SimpleFilterProvider._convert((BeanPropertyFilter)f10));
                continue;
            }
            throw new IllegalArgumentException("Unrecognized filter type (" + f10.getClass().getName() + ")");
        }
        return result;
    }

    private static final PropertyFilter _convert(BeanPropertyFilter f10) {
        return SimpleBeanPropertyFilter.from(f10);
    }

    @Deprecated
    public SimpleFilterProvider setDefaultFilter(BeanPropertyFilter f10) {
        this._defaultFilter = SimpleBeanPropertyFilter.from(f10);
        return this;
    }

    public SimpleFilterProvider setDefaultFilter(PropertyFilter f10) {
        this._defaultFilter = f10;
        return this;
    }

    public SimpleFilterProvider setDefaultFilter(SimpleBeanPropertyFilter f10) {
        this._defaultFilter = f10;
        return this;
    }

    public PropertyFilter getDefaultFilter() {
        return this._defaultFilter;
    }

    public SimpleFilterProvider setFailOnUnknownId(boolean state) {
        this._cfgFailOnUnknownId = state;
        return this;
    }

    public boolean willFailOnUnknownId() {
        return this._cfgFailOnUnknownId;
    }

    @Deprecated
    public SimpleFilterProvider addFilter(String id, BeanPropertyFilter filter) {
        this._filtersById.put(id, SimpleFilterProvider._convert(filter));
        return this;
    }

    public SimpleFilterProvider addFilter(String id, PropertyFilter filter) {
        this._filtersById.put(id, filter);
        return this;
    }

    public SimpleFilterProvider addFilter(String id, SimpleBeanPropertyFilter filter) {
        this._filtersById.put(id, filter);
        return this;
    }

    public PropertyFilter removeFilter(String id) {
        return this._filtersById.remove(id);
    }

    @Override
    @Deprecated
    public BeanPropertyFilter findFilter(Object filterId) {
        throw new UnsupportedOperationException("Access to deprecated filters not supported");
    }

    @Override
    public PropertyFilter findPropertyFilter(Object filterId, Object valueToFilter) {
        PropertyFilter f10 = this._filtersById.get(filterId);
        if (f10 == null && (f10 = this._defaultFilter) == null && this._cfgFailOnUnknownId) {
            throw new IllegalArgumentException("No filter configured with id '" + filterId + "' (type " + filterId.getClass().getName() + ")");
        }
        return f10;
    }
}

