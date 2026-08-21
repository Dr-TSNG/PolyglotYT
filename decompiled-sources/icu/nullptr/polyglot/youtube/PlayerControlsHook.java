package icu.nullptr.polyglot.youtube;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import icu.nullptr.polyglot.ModuleEntryKt;
import icu.nullptr.polyglot.R;
import icu.nullptr.polyglot.util.HookHelpersKt;
import icu.nullptr.polyglot.util.LoggerKt;
import io.github.libxposed.api.XposedInterface;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;
import kotlin.KotlinVersion;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.luckypray.dexkit.DexKitBridge;

/* compiled from: PlayerControlsHook.kt */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0014\u0010\u0014\u001a\u00020\u0015*\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000eH\u0002J\u0016\u0010\u0018\u001a\u0004\u0018\u00010\u0019*\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000eH\u0002J\u001c\u0010\u001a\u001a\u00020\u0015*\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u000eH\u0002J\u001c\u0010\u001c\u001a\u00020\u0015*\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u000eH\u0002J\u0018\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u000eH\u0002J\u0018\u0010\u001e\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u000eH\u0002J\u0018\u0010\u001f\u001a\u00020\u00152\u0006\u0010 \u001a\u00020\u000e2\u0006\u0010!\u001a\u00020\u0019H\u0002J\f\u0010\"\u001a\u00020\u0010*\u00020\u000eH\u0002J\f\u0010#\u001a\u00020\u0010*\u00020\u000eH\u0002J\u0012\u0010$\u001a\b\u0012\u0004\u0012\u00020\u000e0%*\u00020\u0016H\u0002J\f\u0010&\u001a\u00020\u0010*\u00020\u0016H\u0002J\u001b\u0010'\u001a\u0004\u0018\u00010\t*\u00020\u00162\u0006\u0010(\u001a\u00020\u0005H\u0002¢\u0006\u0002\u0010)J\u001c\u0010*\u001a\u00020\t*\u00020+2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010,\u001a\u00020\u0005H\u0002J\f\u0010-\u001a\u00020.*\u00020.H\u0002J\u0013\u0010/\u001a\u0004\u0018\u00010\t*\u000200H\u0002¢\u0006\u0002\u00101J\u0014\u00102\u001a\u00020\u0015*\u0002002\u0006\u00103\u001a\u00020\tH\u0002J\b\u00104\u001a\u00020\u0005H\u0002R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00100\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u00067"}, d2 = {"Licu/nullptr/polyglot/youtube/PlayerControlsHook;", "Licu/nullptr/polyglot/youtube/BaseHook;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "totalHooks", "", "getTotalHooks", "()I", "translateButtons", "Ljava/util/WeakHashMap;", "Landroid/view/View;", "boundSubtitleButtons", "", "install", "dexkit", "Lorg/luckypray/dexkit/DexKitBridge;", "ensureTranslateButtonAfter", "", "Landroid/view/ViewGroup;", "subtitleButton", "createTranslateButton", "Landroid/widget/ImageView;", "moveTranslateButtonAfter", "translateButton", "applyTranslateButtonLayout", "bindTranslateButton", "syncTranslateButtonState", "copyButtonVisuals", "source", "target", "isPlayerSubtitleButton", "isTranslateButton", "findTranslateButtons", "", "hasPlayerSubtitleButton", "findViewIdByEntryName", "entryName", "(Landroid/view/ViewGroup;Ljava/lang/String;)Ljava/lang/Integer;", "resourceId", "Landroid/content/Context;", "type", "copyForParent", "Landroid/view/ViewGroup$LayoutParams;", "firstRelativeTarget", "Landroid/widget/RelativeLayout$LayoutParams;", "(Landroid/widget/RelativeLayout$LayoutParams;)Ljava/lang/Integer;", "replaceHorizontalTarget", "targetId", "translateContentDescription", "TRANSLATE_BUTTON_TAG", "LEGACY_TRANSLATE_BUTTON_TAG_BOUND", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class PlayerControlsHook implements BaseHook {
    private static final String LEGACY_TRANSLATE_BUTTON_TAG_BOUND = "icu.nullptr.polyglot.player_translate_button_bound";
    private static final String TRANSLATE_BUTTON_TAG = "icu.nullptr.polyglot.player_translate_button";
    public static final PlayerControlsHook INSTANCE = new PlayerControlsHook();
    private static final String name = "PlayerControlsHook";
    private static final int totalHooks = 1;
    private static final WeakHashMap<View, View> translateButtons = new WeakHashMap<>();
    private static final WeakHashMap<View, Boolean> boundSubtitleButtons = new WeakHashMap<>();

    private PlayerControlsHook() {
    }

    @Override // icu.nullptr.polyglot.youtube.BaseHook
    public String getName() {
        return name;
    }

    @Override // icu.nullptr.polyglot.youtube.BaseHook
    public int getTotalHooks() {
        return totalHooks;
    }

    @Override // icu.nullptr.polyglot.youtube.BaseHook
    public int install(DexKitBridge dexkit) {
        Intrinsics.checkNotNullParameter(dexkit, "dexkit");
        Class TYPE = Integer.TYPE;
        Intrinsics.checkNotNullExpressionValue(TYPE, "TYPE");
        HookHelpersKt.findAndHookAfter(ViewGroup.class, "addView", new Class[]{View.class, TYPE, ViewGroup.LayoutParams.class}, new Function2() { // from class: icu.nullptr.polyglot.youtube.PlayerControlsHook$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return PlayerControlsHook.install$lambda$1((XposedInterface.Chain) obj, obj2);
            }
        });
        LoggerKt.logD$default(getName(), "Hooked player controls addView", null, 4, null);
        return 1;
    }

    static final Unit install$lambda$1(XposedInterface.Chain chain, Object obj) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Object thisObject = chain.getThisObject();
        final ViewGroup parent = thisObject instanceof ViewGroup ? (ViewGroup) thisObject : null;
        if (parent == null) {
            return Unit.INSTANCE;
        }
        Object arg = chain.getArg(0);
        final View child = arg instanceof View ? (View) arg : null;
        if (child == null) {
            return Unit.INSTANCE;
        }
        if (INSTANCE.isPlayerSubtitleButton(child)) {
            parent.post(new Runnable() { // from class: icu.nullptr.polyglot.youtube.PlayerControlsHook$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    PlayerControlsHook.INSTANCE.ensureTranslateButtonAfter(parent, child);
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0030, code lost:
    
        if (r4 == null) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void ensureTranslateButtonAfter(android.view.ViewGroup r17, android.view.View r18) {
        /*
            Method dump skipped, instructions count: 288
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: icu.nullptr.polyglot.youtube.PlayerControlsHook.ensureTranslateButtonAfter(android.view.ViewGroup, android.view.View):void");
    }

    private final ImageView createTranslateButton(final ViewGroup $this$createTranslateButton, final View subtitleButton) {
        Object m10constructorimpl;
        final ImageView button;
        Context context = $this$createTranslateButton.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        int layoutId = resourceId(context, "youtube_controls_overlay_subtitle_button", "layout");
        if (layoutId != 0) {
            View inflate = LayoutInflater.from($this$createTranslateButton.getContext()).inflate(layoutId, $this$createTranslateButton, false);
            button = inflate instanceof ImageView ? (ImageView) inflate : null;
        } else {
            try {
                Result.Companion companion = Result.INSTANCE;
                Object newInstance = subtitleButton.getClass().getConstructor(Context.class).newInstance($this$createTranslateButton.getContext());
                m10constructorimpl = Result.m10constructorimpl(newInstance instanceof ImageView ? (ImageView) newInstance : null);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                m10constructorimpl = Result.m10constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m16isFailureimpl(m10constructorimpl)) {
                m10constructorimpl = null;
            }
            button = (ImageView) m10constructorimpl;
        }
        if (button == null) {
            return null;
        }
        button.setId(View.generateViewId());
        button.setTag(TRANSLATE_BUTTON_TAG);
        button.setContentDescription(translateContentDescription());
        button.setClickable(true);
        button.setFocusable(true);
        button.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        copyButtonVisuals(subtitleButton, button);
        button.setImageDrawable(ModuleEntryKt.getModule().getRes().getDrawable(R.drawable.outline_translate_24, null));
        button.setOnClickListener(new View.OnClickListener() { // from class: icu.nullptr.polyglot.youtube.PlayerControlsHook$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PlayerControlsHook.createTranslateButton$lambda$8(subtitleButton, button, $this$createTranslateButton, view);
            }
        });
        return button;
    }

    static final void createTranslateButton$lambda$8(View $subtitleButton, ImageView $button, ViewGroup $this_createTranslateButton, View it) {
        ModuleEntryKt.getModule().getConfig().setEnabled(!ModuleEntryKt.getModule().getConfig().getEnabled());
        INSTANCE.syncTranslateButtonState($subtitleButton, $button);
        Toast.makeText($this_createTranslateButton.getContext(), INSTANCE.translateContentDescription(), 0).show();
        LoggerKt.logD$default(INSTANCE.getName(), "Player translate button changed: enabled=" + ModuleEntryKt.getModule().getConfig().getEnabled(), null, 4, null);
    }

    private final void moveTranslateButtonAfter(ViewGroup $this$moveTranslateButtonAfter, View subtitleButton, View translateButton) {
        if ($this$moveTranslateButtonAfter.indexOfChild(translateButton) == $this$moveTranslateButtonAfter.indexOfChild(subtitleButton) + 1) {
            return;
        }
        if ($this$moveTranslateButtonAfter.indexOfChild(translateButton) >= 0) {
            $this$moveTranslateButtonAfter.removeView(translateButton);
        }
        int insertIndex = RangesKt.coerceIn($this$moveTranslateButtonAfter.indexOfChild(subtitleButton) + 1, 0, $this$moveTranslateButtonAfter.getChildCount());
        $this$moveTranslateButtonAfter.addView(translateButton, insertIndex);
    }

    private final void applyTranslateButtonLayout(ViewGroup $this$applyTranslateButtonLayout, View subtitleButton, View translateButton) {
        ViewGroup.LayoutParams subtitleParams = subtitleButton.getLayoutParams();
        if (subtitleParams == null) {
            return;
        }
        translateButton.setLayoutParams(copyForParent(subtitleParams));
        if ($this$applyTranslateButtonLayout instanceof RelativeLayout) {
            ViewGroup.LayoutParams layoutParams = subtitleButton.getLayoutParams();
            RelativeLayout.LayoutParams sourceParams = layoutParams instanceof RelativeLayout.LayoutParams ? (RelativeLayout.LayoutParams) layoutParams : null;
            if (sourceParams == null) {
                return;
            }
            Integer firstRelativeTarget = firstRelativeTarget(sourceParams);
            if (firstRelativeTarget == null && (firstRelativeTarget = findViewIdByEntryName($this$applyTranslateButtonLayout, "player_overflow_button")) == null && (firstRelativeTarget = findViewIdByEntryName($this$applyTranslateButtonLayout, "player_overflow_button_container")) == null && (firstRelativeTarget = findViewIdByEntryName($this$applyTranslateButtonLayout, "new_player_overflow_button")) == null) {
                return;
            }
            int targetId = firstRelativeTarget.intValue();
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(sourceParams);
            INSTANCE.replaceHorizontalTarget(layoutParams2, translateButton.getId());
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(sourceParams);
            INSTANCE.replaceHorizontalTarget(layoutParams3, targetId);
            subtitleButton.setLayoutParams(layoutParams2);
            translateButton.setLayoutParams(layoutParams3);
        }
    }

    private final void bindTranslateButton(final View subtitleButton, final View translateButton) {
        if (Intrinsics.areEqual((Object) boundSubtitleButtons.get(subtitleButton), (Object) true)) {
            return;
        }
        boundSubtitleButtons.put(subtitleButton, true);
        subtitleButton.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: icu.nullptr.polyglot.youtube.PlayerControlsHook$$ExternalSyntheticLambda3
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                PlayerControlsHook.bindTranslateButton$lambda$12(subtitleButton, translateButton, view, i, i2, i3, i4, i5, i6, i7, i8);
            }
        });
        subtitleButton.addOnAttachStateChangeListener(new PlayerControlsHook$bindTranslateButton$2());
    }

    static final void bindTranslateButton$lambda$12(View $subtitleButton, View $translateButton, View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        ViewParent parent = $subtitleButton.getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (viewGroup != null) {
            INSTANCE.applyTranslateButtonLayout(viewGroup, $subtitleButton, $translateButton);
            INSTANCE.syncTranslateButtonState($subtitleButton, $translateButton);
        }
    }

    private final void syncTranslateButtonState(View subtitleButton, View translateButton) {
        translateButton.setVisibility(subtitleButton.getVisibility());
        translateButton.setEnabled(subtitleButton.isEnabled());
        translateButton.setAlpha(subtitleButton.getAlpha());
        translateButton.setContentDescription(translateContentDescription());
        ImageView imageView = translateButton instanceof ImageView ? (ImageView) translateButton : null;
        if (imageView != null) {
            imageView.setImageAlpha(ModuleEntryKt.getModule().getConfig().getEnabled() ? KotlinVersion.MAX_COMPONENT_VALUE : 120);
        }
    }

    private final void copyButtonVisuals(View source, ImageView target) {
        ColorStateList valueOf;
        Drawable.ConstantState constantState;
        Drawable newDrawable;
        target.setMinimumWidth(source.getMinimumWidth());
        target.setMinimumHeight(source.getMinimumHeight());
        target.setPaddingRelative(source.getPaddingStart(), source.getPaddingTop(), source.getPaddingEnd(), source.getPaddingBottom());
        Drawable background = source.getBackground();
        target.setBackground((background == null || (constantState = background.getConstantState()) == null || (newDrawable = constantState.newDrawable()) == null) ? null : newDrawable.mutate());
        ImageView sourceImage = source instanceof ImageView ? (ImageView) source : null;
        if (sourceImage == null || (valueOf = sourceImage.getImageTintList()) == null) {
            valueOf = ColorStateList.valueOf(-1);
            Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(...)");
        }
        target.setImageTintList(valueOf);
    }

    private final boolean isPlayerSubtitleButton(View $this$isPlayerSubtitleButton) {
        Object m10constructorimpl;
        if ($this$isPlayerSubtitleButton.getId() == -1) {
            return false;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            m10constructorimpl = Result.m10constructorimpl(Boolean.valueOf(Intrinsics.areEqual($this$isPlayerSubtitleButton.getResources().getResourceEntryName($this$isPlayerSubtitleButton.getId()), "player_subtitle_button")));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m10constructorimpl = Result.m10constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m16isFailureimpl(m10constructorimpl)) {
            m10constructorimpl = false;
        }
        return ((Boolean) m10constructorimpl).booleanValue();
    }

    private final boolean isTranslateButton(View $this$isTranslateButton) {
        return Intrinsics.areEqual($this$isTranslateButton.getTag(), TRANSLATE_BUTTON_TAG) || Intrinsics.areEqual($this$isTranslateButton.getTag(), LEGACY_TRANSLATE_BUTTON_TAG_BOUND);
    }

    private final List<View> findTranslateButtons(ViewGroup $this$findTranslateButtons) {
        Iterable until = RangesKt.until(0, $this$findTranslateButtons.getChildCount());
        Collection arrayList = new ArrayList();
        Iterator<Integer> it = until.iterator();
        while (it.hasNext()) {
            View childAt = $this$findTranslateButtons.getChildAt(((IntIterator) it).nextInt());
            Iterable iterable = until;
            PlayerControlsHook playerControlsHook = INSTANCE;
            Intrinsics.checkNotNull(childAt);
            if (!playerControlsHook.isTranslateButton(childAt)) {
                childAt = null;
            }
            if (childAt != null) {
                arrayList.add(childAt);
            }
            until = iterable;
        }
        return (List) arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean hasPlayerSubtitleButton(ViewGroup $this$hasPlayerSubtitleButton) {
        Iterable until = RangesKt.until(0, $this$hasPlayerSubtitleButton.getChildCount());
        if ((until instanceof Collection) && ((Collection) until).isEmpty()) {
            return false;
        }
        Iterator it = until.iterator();
        while (it.hasNext()) {
            int nextInt = ((IntIterator) it).nextInt();
            PlayerControlsHook playerControlsHook = INSTANCE;
            View childAt = $this$hasPlayerSubtitleButton.getChildAt(nextInt);
            Intrinsics.checkNotNullExpressionValue(childAt, "getChildAt(...)");
            if (playerControlsHook.isPlayerSubtitleButton(childAt)) {
                return true;
            }
        }
        return false;
    }

    private final Integer findViewIdByEntryName(ViewGroup $this$findViewIdByEntryName, String entryName) {
        Object m10constructorimpl;
        Context context = $this$findViewIdByEntryName.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        Integer directId = Integer.valueOf(resourceId(context, entryName, "id"));
        if (!(directId.intValue() != 0)) {
            directId = null;
        }
        if (directId != null && $this$findViewIdByEntryName.findViewById(directId.intValue()) != null) {
            return directId;
        }
        int childCount = $this$findViewIdByEntryName.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = $this$findViewIdByEntryName.getChildAt(i);
            try {
                Result.Companion companion = Result.INSTANCE;
                m10constructorimpl = Result.m10constructorimpl(child.getResources().getResourceEntryName(child.getId()));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                m10constructorimpl = Result.m10constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m16isFailureimpl(m10constructorimpl)) {
                m10constructorimpl = null;
            }
            String childEntryName = (String) m10constructorimpl;
            if (Intrinsics.areEqual(childEntryName, entryName)) {
                return Integer.valueOf(child.getId());
            }
        }
        return null;
    }

    private final int resourceId(Context $this$resourceId, String name2, String type) {
        return $this$resourceId.getResources().getIdentifier(name2, type, $this$resourceId.getPackageName());
    }

    private final ViewGroup.LayoutParams copyForParent(ViewGroup.LayoutParams $this$copyForParent) {
        Object m10constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            Object newInstance = $this$copyForParent.getClass().getConstructor($this$copyForParent.getClass()).newInstance($this$copyForParent);
            Intrinsics.checkNotNull(newInstance, "null cannot be cast to non-null type android.view.ViewGroup.LayoutParams");
            m10constructorimpl = Result.m10constructorimpl((ViewGroup.LayoutParams) newInstance);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m10constructorimpl = Result.m10constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m16isFailureimpl(m10constructorimpl)) {
            m10constructorimpl = null;
        }
        ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) m10constructorimpl;
        if (layoutParams == null) {
            return $this$copyForParent instanceof RelativeLayout.LayoutParams ? new RelativeLayout.LayoutParams((RelativeLayout.LayoutParams) $this$copyForParent) : $this$copyForParent instanceof ViewGroup.MarginLayoutParams ? new ViewGroup.MarginLayoutParams((ViewGroup.MarginLayoutParams) $this$copyForParent) : new ViewGroup.LayoutParams($this$copyForParent);
        }
        return layoutParams;
    }

    private final Integer firstRelativeTarget(RelativeLayout.LayoutParams $this$firstRelativeTarget) {
        Integer num;
        Iterator it = CollectionsKt.listOf((Object[]) new Integer[]{16, 0}).iterator();
        do {
            num = null;
            if (!it.hasNext()) {
                break;
            }
            Integer valueOf = Integer.valueOf($this$firstRelativeTarget.getRule(((Number) it.next()).intValue()));
            if ((valueOf.intValue() > 0 ? 1 : 0) != 0) {
                num = valueOf;
            }
        } while (num == null);
        return num;
    }

    private final void replaceHorizontalTarget(RelativeLayout.LayoutParams $this$replaceHorizontalTarget, int targetId) {
        $this$replaceHorizontalTarget.removeRule(16);
        $this$replaceHorizontalTarget.removeRule(0);
        $this$replaceHorizontalTarget.addRule(16, targetId);
    }

    private final String translateContentDescription() {
        String state = ModuleEntryKt.getModule().getRes().getString(ModuleEntryKt.getModule().getConfig().getEnabled() ? R.string.enabled : R.string.disabled);
        Intrinsics.checkNotNullExpressionValue(state, "getString(...)");
        return ModuleEntryKt.getModule().getRes().getString(R.string.subtitle_quick_switch_title) + " " + state;
    }
}
