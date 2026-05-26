/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.geckolib3.core.builder;

public interface ILoopType {
    public boolean isRepeatingAfterEnd();

    public static enum EDefaultLoopTypes implements ILoopType
    {
        LOOP(true),
        PLAY_ONCE,
        HOLD_ON_LAST_FRAME;

        private final boolean looping;

        private EDefaultLoopTypes(boolean looping) {
            this.looping = looping;
        }

        private EDefaultLoopTypes() {
            this(false);
        }

        @Override
        public boolean isRepeatingAfterEnd() {
            return this.looping;
        }
    }
}

