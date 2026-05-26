/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.geckolib3.core.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.commons.lang3.tuple.Pair;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.ConstantValue;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimatableModel;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.Animation;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.easing.EasingType;
import software.bernie.geckolib3.core.event.CustomInstructionKeyframeEvent;
import software.bernie.geckolib3.core.event.KeyframeEvent;
import software.bernie.geckolib3.core.event.ParticleKeyFrameEvent;
import software.bernie.geckolib3.core.event.SoundKeyframeEvent;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.keyframe.AnimationPoint;
import software.bernie.geckolib3.core.keyframe.BoneAnimation;
import software.bernie.geckolib3.core.keyframe.BoneAnimationQueue;
import software.bernie.geckolib3.core.keyframe.EventKeyFrame;
import software.bernie.geckolib3.core.keyframe.KeyFrame;
import software.bernie.geckolib3.core.keyframe.KeyFrameLocation;
import software.bernie.geckolib3.core.keyframe.ParticleEventKeyFrame;
import software.bernie.geckolib3.core.keyframe.VectorKeyFrameList;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.core.snapshot.BoneSnapshot;
import software.bernie.geckolib3.core.util.Axis;
import software.bernie.shadowed.eliotlash.mclib.math.IValue;
import software.bernie.shadowed.eliotlash.molang.MolangParser;

public class AnimationController<T extends IAnimatable> {
    static List<ModelFetcher<?>> modelFetchers = new ArrayList();
    protected T animatable;
    protected IAnimationPredicate<T> animationPredicate;
    private final String name;
    protected AnimationState animationState = AnimationState.Stopped;
    public double transitionLengthTicks;
    private ISoundListener<T> soundListener;
    private IParticleListener<T> particleListener;
    private ICustomInstructionListener<T> customInstructionListener;
    public boolean isJustStarting = false;
    private final HashMap<String, BoneAnimationQueue> boneAnimationQueues = new HashMap();
    public double tickOffset;
    protected Queue<Animation> animationQueue = new LinkedList<Animation>();
    protected Animation currentAnimation;
    protected AnimationBuilder currentAnimationBuilder = new AnimationBuilder();
    protected boolean shouldResetTick = false;
    private final HashMap<String, BoneSnapshot> boneSnapshots = new HashMap();
    private boolean justStopped = false;
    protected boolean justStartedTransition = false;
    public Function<Double, Double> customEasingMethod;
    protected boolean needsAnimationReload = false;
    public double animationSpeed = 1.0;
    private final Set<EventKeyFrame<?>> executedKeyFrames = new HashSet();
    public EasingType easingType = EasingType.NONE;

    public static void addModelFetcher(ModelFetcher<?> fetcher) {
        modelFetchers.add(fetcher);
    }

    public static void removeModelFetcher(ModelFetcher<?> fetcher) {
        Objects.requireNonNull(fetcher);
        modelFetchers.remove(fetcher);
    }

    public void setAnimation(AnimationBuilder builder) {
        IAnimatableModel<T> model = this.getModel(this.animatable);
        if (model != null) {
            if (builder == null || builder.getRawAnimationList().size() == 0) {
                this.animationState = AnimationState.Stopped;
            } else if (!builder.getRawAnimationList().equals(this.currentAnimationBuilder.getRawAnimationList()) || this.needsAnimationReload) {
                AtomicBoolean encounteredError = new AtomicBoolean(false);
                LinkedList animations = builder.getRawAnimationList().stream().map(rawAnimation -> {
                    Animation animation = model.getAnimation(rawAnimation.animationName, (IAnimatable)this.animatable);
                    if (animation == null) {
                        System.out.printf("Could not load animation: %s. Is it missing?", rawAnimation.animationName);
                        encounteredError.set(true);
                    }
                    if (animation != null && rawAnimation.loopType != null) {
                        animation.loop = rawAnimation.loopType;
                    }
                    return animation;
                }).collect(Collectors.toCollection(LinkedList::new));
                if (encounteredError.get()) {
                    return;
                }
                this.animationQueue = animations;
                this.currentAnimationBuilder = builder;
                this.shouldResetTick = true;
                this.animationState = AnimationState.Transitioning;
                this.justStartedTransition = true;
                this.needsAnimationReload = false;
            }
        }
    }

    public AnimationController(T animatable, String name, float transitionLengthTicks, IAnimationPredicate<T> animationPredicate) {
        this.animatable = animatable;
        this.name = name;
        this.transitionLengthTicks = transitionLengthTicks;
        this.animationPredicate = animationPredicate;
        this.tickOffset = 0.0;
    }

    public AnimationController(T animatable, String name, float transitionLengthTicks, EasingType easingtype, IAnimationPredicate<T> animationPredicate) {
        this.animatable = animatable;
        this.name = name;
        this.transitionLengthTicks = transitionLengthTicks;
        this.easingType = easingtype;
        this.animationPredicate = animationPredicate;
        this.tickOffset = 0.0;
    }

    public AnimationController(T animatable, String name, float transitionLengthTicks, Function<Double, Double> customEasingMethod, IAnimationPredicate<T> animationPredicate) {
        this.animatable = animatable;
        this.name = name;
        this.transitionLengthTicks = transitionLengthTicks;
        this.customEasingMethod = customEasingMethod;
        this.easingType = EasingType.CUSTOM;
        this.animationPredicate = animationPredicate;
        this.tickOffset = 0.0;
    }

    public String getName() {
        return this.name;
    }

    public Animation getCurrentAnimation() {
        return this.currentAnimation;
    }

    public AnimationState getAnimationState() {
        return this.animationState;
    }

    public HashMap<String, BoneAnimationQueue> getBoneAnimationQueues() {
        return this.boneAnimationQueues;
    }

    public void registerSoundListener(ISoundListener<T> soundListener) {
        this.soundListener = soundListener;
    }

    public void registerParticleListener(IParticleListener<T> particleListener) {
        this.particleListener = particleListener;
    }

    public void registerCustomInstructionListener(ICustomInstructionListener<T> customInstructionListener) {
        this.customInstructionListener = customInstructionListener;
    }

    public void process(double tick, AnimationEvent<T> event, List<IBone> modelRendererList, HashMap<String, Pair<IBone, BoneSnapshot>> boneSnapshotCollection, MolangParser parser, boolean crashWhenCantFindBone) {
        Animation animation;
        IAnimatableModel<T> model;
        parser.setValue("query.life_time", tick / 20.0);
        if (this.currentAnimation != null && (model = this.getModel(this.animatable)) != null && (animation = model.getAnimation(this.currentAnimation.animationName, (IAnimatable)this.animatable)) != null) {
            ILoopType loop = this.currentAnimation.loop;
            this.currentAnimation = animation;
            this.currentAnimation.loop = loop;
        }
        this.createInitialQueues(modelRendererList);
        double actualTick = tick;
        tick = this.adjustTick(tick);
        if (this.animationState == AnimationState.Transitioning && tick >= this.transitionLengthTicks) {
            this.shouldResetTick = true;
            this.animationState = AnimationState.Running;
            tick = this.adjustTick(actualTick);
        }
        assert (tick >= 0.0) : "GeckoLib: Tick was less than zero";
        PlayState playState = this.testAnimationPredicate(event);
        if (playState == PlayState.STOP || this.currentAnimation == null && this.animationQueue.size() == 0) {
            this.animationState = AnimationState.Stopped;
            this.justStopped = true;
            return;
        }
        if (this.justStartedTransition && (this.shouldResetTick || this.justStopped)) {
            this.justStopped = false;
            tick = this.adjustTick(actualTick);
        } else if (this.currentAnimation == null && this.animationQueue.size() != 0) {
            this.shouldResetTick = true;
            this.animationState = AnimationState.Transitioning;
            this.justStartedTransition = true;
            this.needsAnimationReload = false;
            tick = this.adjustTick(actualTick);
        } else if (this.animationState != AnimationState.Transitioning) {
            this.animationState = AnimationState.Running;
        }
        if (this.animationState == AnimationState.Transitioning) {
            if (tick == 0.0 || this.isJustStarting) {
                this.justStartedTransition = false;
                this.currentAnimation = this.animationQueue.poll();
                this.resetEventKeyFrames();
                this.saveSnapshotsForAnimation(this.currentAnimation, boneSnapshotCollection);
            }
            if (this.currentAnimation != null) {
                this.setAnimTime(parser, 0.0);
                for (BoneAnimation boneAnimation : this.currentAnimation.boneAnimations) {
                    AnimationPoint zPoint;
                    AnimationPoint yPoint;
                    AnimationPoint xPoint;
                    BoneAnimationQueue boneAnimationQueue = this.boneAnimationQueues.get(boneAnimation.boneName);
                    BoneSnapshot boneSnapshot = this.boneSnapshots.get(boneAnimation.boneName);
                    Optional<IBone> first = modelRendererList.stream().filter(x2 -> x2.getName().equals(boneAnimation.boneName)).findFirst();
                    if (!first.isPresent()) {
                        if (!crashWhenCantFindBone) continue;
                        throw new RuntimeException("Could not find bone: " + boneAnimation.boneName);
                    }
                    BoneSnapshot initialSnapshot = first.get().getInitialSnapshot();
                    assert (boneSnapshot != null) : "Bone snapshot was null";
                    VectorKeyFrameList<KeyFrame<IValue>> rotationKeyFrames = boneAnimation.rotationKeyFrames;
                    VectorKeyFrameList<KeyFrame<IValue>> positionKeyFrames = boneAnimation.positionKeyFrames;
                    VectorKeyFrameList<KeyFrame<IValue>> scaleKeyFrames = boneAnimation.scaleKeyFrames;
                    if (!rotationKeyFrames.xKeyFrames.isEmpty()) {
                        xPoint = this.getAnimationPointAtTick(rotationKeyFrames.xKeyFrames, 0.0, true, Axis.X);
                        yPoint = this.getAnimationPointAtTick(rotationKeyFrames.yKeyFrames, 0.0, true, Axis.Y);
                        zPoint = this.getAnimationPointAtTick(rotationKeyFrames.zKeyFrames, 0.0, true, Axis.Z);
                        boneAnimationQueue.rotationXQueue.add(new AnimationPoint(null, tick, this.transitionLengthTicks, boneSnapshot.rotationValueX - initialSnapshot.rotationValueX, (double)xPoint.animationStartValue));
                        boneAnimationQueue.rotationYQueue.add(new AnimationPoint(null, tick, this.transitionLengthTicks, boneSnapshot.rotationValueY - initialSnapshot.rotationValueY, (double)yPoint.animationStartValue));
                        boneAnimationQueue.rotationZQueue.add(new AnimationPoint(null, tick, this.transitionLengthTicks, boneSnapshot.rotationValueZ - initialSnapshot.rotationValueZ, (double)zPoint.animationStartValue));
                    }
                    if (!positionKeyFrames.xKeyFrames.isEmpty()) {
                        xPoint = this.getAnimationPointAtTick(positionKeyFrames.xKeyFrames, 0.0, false, Axis.X);
                        yPoint = this.getAnimationPointAtTick(positionKeyFrames.yKeyFrames, 0.0, false, Axis.Y);
                        zPoint = this.getAnimationPointAtTick(positionKeyFrames.zKeyFrames, 0.0, false, Axis.Z);
                        boneAnimationQueue.positionXQueue.add(new AnimationPoint(null, tick, this.transitionLengthTicks, boneSnapshot.positionOffsetX, (double)xPoint.animationStartValue));
                        boneAnimationQueue.positionYQueue.add(new AnimationPoint(null, tick, this.transitionLengthTicks, boneSnapshot.positionOffsetY, (double)yPoint.animationStartValue));
                        boneAnimationQueue.positionZQueue.add(new AnimationPoint(null, tick, this.transitionLengthTicks, boneSnapshot.positionOffsetZ, (double)zPoint.animationStartValue));
                    }
                    if (scaleKeyFrames.xKeyFrames.isEmpty()) continue;
                    xPoint = this.getAnimationPointAtTick(scaleKeyFrames.xKeyFrames, 0.0, false, Axis.X);
                    yPoint = this.getAnimationPointAtTick(scaleKeyFrames.yKeyFrames, 0.0, false, Axis.Y);
                    zPoint = this.getAnimationPointAtTick(scaleKeyFrames.zKeyFrames, 0.0, false, Axis.Z);
                    boneAnimationQueue.scaleXQueue.add(new AnimationPoint(null, tick, this.transitionLengthTicks, boneSnapshot.scaleValueX, (double)xPoint.animationStartValue));
                    boneAnimationQueue.scaleYQueue.add(new AnimationPoint(null, tick, this.transitionLengthTicks, boneSnapshot.scaleValueY, (double)yPoint.animationStartValue));
                    boneAnimationQueue.scaleZQueue.add(new AnimationPoint(null, tick, this.transitionLengthTicks, boneSnapshot.scaleValueZ, (double)zPoint.animationStartValue));
                }
            }
        } else if (this.getAnimationState() == AnimationState.Running) {
            this.processCurrentAnimation(tick, actualTick, parser, crashWhenCantFindBone);
        }
    }

    private void setAnimTime(MolangParser parser, double tick) {
        parser.setValue("query.anim_time", tick / 20.0);
    }

    private IAnimatableModel<T> getModel(T animatable) {
        for (ModelFetcher<?> modelFetcher : modelFetchers) {
            IAnimatableModel model = (IAnimatableModel)modelFetcher.apply(animatable);
            if (model == null) continue;
            return model;
        }
        System.out.printf("Could not find suitable model for animatable of type %s. Did you register a Model Fetcher?%n", animatable.getClass());
        return null;
    }

    protected PlayState testAnimationPredicate(AnimationEvent<T> event) {
        return this.animationPredicate.test(event);
    }

    private void saveSnapshotsForAnimation(Animation animation, HashMap<String, Pair<IBone, BoneSnapshot>> boneSnapshotCollection) {
        for (Pair<IBone, BoneSnapshot> snapshot : boneSnapshotCollection.values()) {
            if (animation == null || animation.boneAnimations == null || !animation.boneAnimations.stream().anyMatch(x2 -> x2.boneName.equals(((IBone)snapshot.getLeft()).getName()))) continue;
            this.boneSnapshots.put(snapshot.getLeft().getName(), new BoneSnapshot(snapshot.getRight()));
        }
    }

    private void processCurrentAnimation(double tick, double actualTick, MolangParser parser, boolean crashWhenCantFindBone) {
        assert (this.currentAnimation != null);
        if (tick >= this.currentAnimation.animationLength) {
            this.resetEventKeyFrames();
            if (!this.currentAnimation.loop.isRepeatingAfterEnd()) {
                Animation peek = this.animationQueue.peek();
                if (peek == null) {
                    this.animationState = AnimationState.Stopped;
                    return;
                }
                this.animationState = AnimationState.Transitioning;
                this.shouldResetTick = true;
                this.currentAnimation = this.animationQueue.peek();
            } else {
                this.shouldResetTick = true;
                tick = this.adjustTick(actualTick);
            }
        }
        this.setAnimTime(parser, tick);
        List<BoneAnimation> boneAnimations = this.currentAnimation.boneAnimations;
        for (BoneAnimation boneAnimation : boneAnimations) {
            BoneAnimationQueue boneAnimationQueue = this.boneAnimationQueues.get(boneAnimation.boneName);
            if (boneAnimationQueue == null) {
                if (!crashWhenCantFindBone) continue;
                throw new RuntimeException("Could not find bone: " + boneAnimation.boneName);
            }
            VectorKeyFrameList<KeyFrame<IValue>> rotationKeyFrames = boneAnimation.rotationKeyFrames;
            VectorKeyFrameList<KeyFrame<IValue>> positionKeyFrames = boneAnimation.positionKeyFrames;
            VectorKeyFrameList<KeyFrame<IValue>> scaleKeyFrames = boneAnimation.scaleKeyFrames;
            if (!rotationKeyFrames.xKeyFrames.isEmpty()) {
                boneAnimationQueue.rotationXQueue.add(this.getAnimationPointAtTick(rotationKeyFrames.xKeyFrames, tick, true, Axis.X));
                boneAnimationQueue.rotationYQueue.add(this.getAnimationPointAtTick(rotationKeyFrames.yKeyFrames, tick, true, Axis.Y));
                boneAnimationQueue.rotationZQueue.add(this.getAnimationPointAtTick(rotationKeyFrames.zKeyFrames, tick, true, Axis.Z));
            }
            if (!positionKeyFrames.xKeyFrames.isEmpty()) {
                boneAnimationQueue.positionXQueue.add(this.getAnimationPointAtTick(positionKeyFrames.xKeyFrames, tick, false, Axis.X));
                boneAnimationQueue.positionYQueue.add(this.getAnimationPointAtTick(positionKeyFrames.yKeyFrames, tick, false, Axis.Y));
                boneAnimationQueue.positionZQueue.add(this.getAnimationPointAtTick(positionKeyFrames.zKeyFrames, tick, false, Axis.Z));
            }
            if (scaleKeyFrames.xKeyFrames.isEmpty()) continue;
            boneAnimationQueue.scaleXQueue.add(this.getAnimationPointAtTick(scaleKeyFrames.xKeyFrames, tick, false, Axis.X));
            boneAnimationQueue.scaleYQueue.add(this.getAnimationPointAtTick(scaleKeyFrames.yKeyFrames, tick, false, Axis.Y));
            boneAnimationQueue.scaleZQueue.add(this.getAnimationPointAtTick(scaleKeyFrames.zKeyFrames, tick, false, Axis.Z));
        }
        if (this.soundListener != null || this.particleListener != null || this.customInstructionListener != null) {
            KeyframeEvent event;
            for (EventKeyFrame eventKeyFrame : this.currentAnimation.soundKeyFrames) {
                if (this.executedKeyFrames.contains(eventKeyFrame) || !(tick >= eventKeyFrame.getStartTick())) continue;
                event = new SoundKeyframeEvent<T>(this.animatable, tick, (String)eventKeyFrame.getEventData(), this);
                this.soundListener.playSound((SoundKeyframeEvent<T>)event);
                this.executedKeyFrames.add(eventKeyFrame);
            }
            for (ParticleEventKeyFrame particleEventKeyFrame : this.currentAnimation.particleKeyFrames) {
                if (this.executedKeyFrames.contains(particleEventKeyFrame) || !(tick >= particleEventKeyFrame.getStartTick())) continue;
                event = new ParticleKeyFrameEvent<T>(this.animatable, tick, particleEventKeyFrame.effect, particleEventKeyFrame.locator, particleEventKeyFrame.script, this);
                this.particleListener.summonParticle((ParticleKeyFrameEvent<T>)event);
                this.executedKeyFrames.add(particleEventKeyFrame);
            }
            for (EventKeyFrame eventKeyFrame : this.currentAnimation.customInstructionKeyframes) {
                if (this.executedKeyFrames.contains(eventKeyFrame) || !(tick >= eventKeyFrame.getStartTick())) continue;
                event = new CustomInstructionKeyframeEvent<T>(this.animatable, tick, (String)eventKeyFrame.getEventData(), this);
                this.customInstructionListener.executeInstruction((CustomInstructionKeyframeEvent<T>)event);
                this.executedKeyFrames.add(eventKeyFrame);
            }
        }
        if (this.transitionLengthTicks == 0.0 && this.shouldResetTick && this.animationState == AnimationState.Transitioning) {
            this.currentAnimation = this.animationQueue.poll();
        }
    }

    private void createInitialQueues(List<IBone> modelRendererList) {
        this.boneAnimationQueues.clear();
        for (IBone modelRenderer : modelRendererList) {
            this.boneAnimationQueues.put(modelRenderer.getName(), new BoneAnimationQueue(modelRenderer));
        }
    }

    protected double adjustTick(double tick) {
        if (this.shouldResetTick) {
            if (this.getAnimationState() == AnimationState.Transitioning) {
                this.tickOffset = tick;
            } else if (this.getAnimationState() == AnimationState.Running) {
                this.tickOffset = tick;
            }
            this.shouldResetTick = false;
            return 0.0;
        }
        return this.animationSpeed * Math.max(tick - this.tickOffset, 0.0);
    }

    private AnimationPoint getAnimationPointAtTick(List<KeyFrame<IValue>> frames, double tick, boolean isRotation, Axis axis) {
        KeyFrameLocation<KeyFrame<IValue>> location = this.getCurrentKeyFrameLocation(frames, tick);
        Object currentFrame = location.currentFrame;
        double startValue = ((IValue)((KeyFrame)currentFrame).getStartValue()).get();
        double endValue = ((IValue)((KeyFrame)currentFrame).getEndValue()).get();
        if (isRotation) {
            if (!(((KeyFrame)currentFrame).getStartValue() instanceof ConstantValue)) {
                startValue = Math.toRadians(startValue);
                if (axis == Axis.X || axis == Axis.Y) {
                    startValue *= -1.0;
                }
            }
            if (!(((KeyFrame)currentFrame).getEndValue() instanceof ConstantValue)) {
                endValue = Math.toRadians(endValue);
                if (axis == Axis.X || axis == Axis.Y) {
                    endValue *= -1.0;
                }
            }
        }
        return new AnimationPoint((KeyFrame<IValue>)currentFrame, (Double)location.currentTick, ((KeyFrame)currentFrame).getLength(), startValue, (Double)endValue);
    }

    private KeyFrameLocation<KeyFrame<IValue>> getCurrentKeyFrameLocation(List<KeyFrame<IValue>> frames, double ageInTicks) {
        double totalTimeTracker = 0.0;
        for (KeyFrame<IValue> frame : frames) {
            if (!((totalTimeTracker += frame.getLength().doubleValue()) > ageInTicks)) continue;
            double tick = ageInTicks - (totalTimeTracker - frame.getLength());
            return new KeyFrameLocation<KeyFrame<IValue>>(frame, tick);
        }
        return new KeyFrameLocation<KeyFrame<IValue>>(frames.get(frames.size() - 1), ageInTicks);
    }

    private void resetEventKeyFrames() {
        this.executedKeyFrames.clear();
    }

    public void markNeedsReload() {
        this.needsAnimationReload = true;
    }

    public void clearAnimationCache() {
        this.currentAnimationBuilder = new AnimationBuilder();
    }

    public double getAnimationSpeed() {
        return this.animationSpeed;
    }

    public void setAnimationSpeed(double animationSpeed) {
        this.animationSpeed = animationSpeed;
    }

    @FunctionalInterface
    public static interface ModelFetcher<T>
    extends Function<IAnimatable, IAnimatableModel<T>> {
    }

    @FunctionalInterface
    public static interface ICustomInstructionListener<A extends IAnimatable> {
        public void executeInstruction(CustomInstructionKeyframeEvent<A> var1);
    }

    @FunctionalInterface
    public static interface IParticleListener<A extends IAnimatable> {
        public void summonParticle(ParticleKeyFrameEvent<A> var1);
    }

    @FunctionalInterface
    public static interface ISoundListener<A extends IAnimatable> {
        public void playSound(SoundKeyframeEvent<A> var1);
    }

    @FunctionalInterface
    public static interface IAnimationPredicate<P extends IAnimatable> {
        public PlayState test(AnimationEvent<P> var1);
    }
}

