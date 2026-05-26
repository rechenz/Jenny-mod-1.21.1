/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.annotation;

import software.bernie.shadowed.fasterxml.jackson.annotation.ObjectIdGenerator;

public interface ObjectIdResolver {
    public void bindItem(ObjectIdGenerator.IdKey var1, Object var2);

    public Object resolveId(ObjectIdGenerator.IdKey var1);

    public ObjectIdResolver newForDeserialization(Object var1);

    public boolean canUseFor(ObjectIdResolver var1);
}

