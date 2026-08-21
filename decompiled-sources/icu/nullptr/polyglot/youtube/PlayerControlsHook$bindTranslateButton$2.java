package icu.nullptr.polyglot.youtube;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.util.WeakHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PlayerControlsHook.kt */
@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0007"}, d2 = {"icu/nullptr/polyglot/youtube/PlayerControlsHook$bindTranslateButton$2", "Landroid/view/View$OnAttachStateChangeListener;", "onViewAttachedToWindow", "", "view", "Landroid/view/View;", "onViewDetachedFromWindow", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class PlayerControlsHook$bindTranslateButton$2 implements View.OnAttachStateChangeListener {
    PlayerControlsHook$bindTranslateButton$2() {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(final View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        ViewParent parent = view.getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (viewGroup != null) {
            viewGroup.post(new Runnable() { // from class: icu.nullptr.polyglot.youtube.PlayerControlsHook$bindTranslateButton$2$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    PlayerControlsHook$bindTranslateButton$2.onViewAttachedToWindow$lambda$0(view);
                }
            });
        }
    }

    static final void onViewAttachedToWindow$lambda$0(View $view) {
        ViewParent parent = $view.getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (viewGroup != null) {
            PlayerControlsHook.INSTANCE.ensureTranslateButtonAfter(viewGroup, $view);
        }
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        WeakHashMap weakHashMap;
        WeakHashMap weakHashMap2;
        boolean hasPlayerSubtitleButton;
        Intrinsics.checkNotNullParameter(view, "view");
        weakHashMap = PlayerControlsHook.translateButtons;
        View button = (View) weakHashMap.remove(view);
        if (button == null) {
            return;
        }
        weakHashMap2 = PlayerControlsHook.boundSubtitleButtons;
        weakHashMap2.remove(view);
        ViewParent parent = button.getParent();
        ViewGroup parent2 = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (parent2 == null) {
            return;
        }
        hasPlayerSubtitleButton = PlayerControlsHook.INSTANCE.hasPlayerSubtitleButton(parent2);
        if (!hasPlayerSubtitleButton) {
            parent2.removeView(button);
        }
    }
}
