/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.node;

import java.util.Iterator;
import java.util.Map;
import software.bernie.shadowed.fasterxml.jackson.core.JsonStreamContext;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonNode;
import software.bernie.shadowed.fasterxml.jackson.databind.node.ContainerNode;
import software.bernie.shadowed.fasterxml.jackson.databind.node.ObjectNode;

abstract class NodeCursor
extends JsonStreamContext {
    protected final NodeCursor _parent;
    protected String _currentName;
    protected Object _currentValue;

    public NodeCursor(int contextType, NodeCursor p2) {
        this._type = contextType;
        this._index = -1;
        this._parent = p2;
    }

    @Override
    public final NodeCursor getParent() {
        return this._parent;
    }

    @Override
    public final String getCurrentName() {
        return this._currentName;
    }

    public void overrideCurrentName(String name) {
        this._currentName = name;
    }

    @Override
    public Object getCurrentValue() {
        return this._currentValue;
    }

    @Override
    public void setCurrentValue(Object v2) {
        this._currentValue = v2;
    }

    public abstract JsonToken nextToken();

    public abstract JsonToken nextValue();

    public abstract JsonToken endToken();

    public abstract JsonNode currentNode();

    public abstract boolean currentHasChildren();

    public final NodeCursor iterateChildren() {
        JsonNode n2 = this.currentNode();
        if (n2 == null) {
            throw new IllegalStateException("No current node");
        }
        if (n2.isArray()) {
            return new ArrayCursor(n2, this);
        }
        if (n2.isObject()) {
            return new ObjectCursor(n2, this);
        }
        throw new IllegalStateException("Current node of type " + n2.getClass().getName());
    }

    protected static final class ObjectCursor
    extends NodeCursor {
        protected Iterator<Map.Entry<String, JsonNode>> _contents;
        protected Map.Entry<String, JsonNode> _current;
        protected boolean _needEntry;

        public ObjectCursor(JsonNode n2, NodeCursor p2) {
            super(2, p2);
            this._contents = ((ObjectNode)n2).fields();
            this._needEntry = true;
        }

        @Override
        public JsonToken nextToken() {
            if (this._needEntry) {
                if (!this._contents.hasNext()) {
                    this._currentName = null;
                    this._current = null;
                    return null;
                }
                this._needEntry = false;
                this._current = this._contents.next();
                this._currentName = this._current == null ? null : this._current.getKey();
                return JsonToken.FIELD_NAME;
            }
            this._needEntry = true;
            return this._current.getValue().asToken();
        }

        @Override
        public JsonToken nextValue() {
            JsonToken t2 = this.nextToken();
            if (t2 == JsonToken.FIELD_NAME) {
                t2 = this.nextToken();
            }
            return t2;
        }

        @Override
        public JsonToken endToken() {
            return JsonToken.END_OBJECT;
        }

        @Override
        public JsonNode currentNode() {
            return this._current == null ? null : this._current.getValue();
        }

        @Override
        public boolean currentHasChildren() {
            return ((ContainerNode)this.currentNode()).size() > 0;
        }
    }

    protected static final class ArrayCursor
    extends NodeCursor {
        protected Iterator<JsonNode> _contents;
        protected JsonNode _currentNode;

        public ArrayCursor(JsonNode n2, NodeCursor p2) {
            super(1, p2);
            this._contents = n2.elements();
        }

        @Override
        public JsonToken nextToken() {
            if (!this._contents.hasNext()) {
                this._currentNode = null;
                return null;
            }
            this._currentNode = this._contents.next();
            return this._currentNode.asToken();
        }

        @Override
        public JsonToken nextValue() {
            return this.nextToken();
        }

        @Override
        public JsonToken endToken() {
            return JsonToken.END_ARRAY;
        }

        @Override
        public JsonNode currentNode() {
            return this._currentNode;
        }

        @Override
        public boolean currentHasChildren() {
            return ((ContainerNode)this.currentNode()).size() > 0;
        }
    }

    protected static final class RootCursor
    extends NodeCursor {
        protected JsonNode _node;
        protected boolean _done = false;

        public RootCursor(JsonNode n2, NodeCursor p2) {
            super(0, p2);
            this._node = n2;
        }

        @Override
        public void overrideCurrentName(String name) {
        }

        @Override
        public JsonToken nextToken() {
            if (!this._done) {
                this._done = true;
                return this._node.asToken();
            }
            this._node = null;
            return null;
        }

        @Override
        public JsonToken nextValue() {
            return this.nextToken();
        }

        @Override
        public JsonToken endToken() {
            return null;
        }

        @Override
        public JsonNode currentNode() {
            return this._node;
        }

        @Override
        public boolean currentHasChildren() {
            return false;
        }
    }
}

