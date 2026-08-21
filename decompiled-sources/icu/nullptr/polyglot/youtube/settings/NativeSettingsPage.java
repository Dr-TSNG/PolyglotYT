package icu.nullptr.polyglot.youtube.settings;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;
import android.window.OnBackInvokedCallback;
import icu.nullptr.polyglot.ModuleEntryKt;
import icu.nullptr.polyglot.settings.SettingsOption;
import icu.nullptr.polyglot.translate.ConnectivityTestResult;
import icu.nullptr.polyglot.translate.ConnectivityTester;
import icu.nullptr.polyglot.util.LoggerKt;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* compiled from: NativeSettingsPage.kt */
@Metadata(d1 = {"\u0000\u0096\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\b\u0000\u0018\u0000 D2\u00020\u0001:\u0003BCDBA\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0001\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u000e\u0010\u000fJ\u0006\u0010\u001e\u001a\u00020\u001fJ\u0006\u0010 \u001a\u00020!J\u0006\u0010\"\u001a\u00020\u001fJ\u0006\u0010#\u001a\u00020\u001fJ\b\u0010$\u001a\u00020\u001fH\u0002J\u0018\u0010%\u001a\u00020!2\u0006\u0010&\u001a\u00020\u00162\u0006\u0010'\u001a\u00020!H\u0002J\b\u0010(\u001a\u00020!H\u0002J\u0012\u0010)\u001a\u0004\u0018\u00010\u00162\u0006\u0010*\u001a\u00020+H\u0002J\u0012\u0010,\u001a\u0004\u0018\u00010\u00012\u0006\u0010*\u001a\u00020-H\u0002J\u0010\u0010.\u001a\u00020\u001f2\u0006\u0010*\u001a\u00020/H\u0002J\u0010\u00100\u001a\u00020\u001f2\u0006\u0010*\u001a\u000201H\u0002J\u0010\u00102\u001a\u00020\u001f2\u0006\u00103\u001a\u000204H\u0002J\b\u00105\u001a\u00020\u001fH\u0002J\u0010\u00106\u001a\u00020\u001f2\u0006\u00107\u001a\u000208H\u0002J\f\u00109\u001a\u00020:*\u00020:H\u0002J\b\u0010;\u001a\u00020\u001fH\u0002J\b\u0010<\u001a\u00020\u0005H\u0002J\u0014\u0010=\u001a\u00020>*\u00020\u00052\u0006\u0010?\u001a\u00020>H\u0002J\b\u0010@\u001a\u00020\u001fH\u0002J\b\u0010A\u001a\u00020\u001fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006E"}, d2 = {"Licu/nullptr/polyglot/youtube/settings/NativeSettingsPage;", "", "fragment", "rootScreen", "context", "Landroid/content/Context;", "classes", "Licu/nullptr/polyglot/youtube/settings/HostPreferenceClasses;", "adapter", "Licu/nullptr/polyglot/youtube/settings/HostPreferenceAdapter;", "controller", "Licu/nullptr/polyglot/youtube/settings/SettingsPageController;", "activity", "Landroid/app/Activity;", "<init>", "(Ljava/lang/Object;Ljava/lang/Object;Landroid/content/Context;Licu/nullptr/polyglot/youtube/settings/HostPreferenceClasses;Licu/nullptr/polyglot/youtube/settings/HostPreferenceAdapter;Licu/nullptr/polyglot/youtube/settings/SettingsPageController;Landroid/app/Activity;)V", "getActivity", "()Landroid/app/Activity;", "toolbarTitle", "Licu/nullptr/polyglot/youtube/settings/HostToolbarTitle;", "backStack", "Ljava/util/ArrayDeque;", "Licu/nullptr/polyglot/youtube/settings/NativeSettingsPage$ScreenState;", "mainHandler", "Landroid/os/Handler;", "connectivityTestRunning", "Ljava/util/concurrent/atomic/AtomicBoolean;", "currentScreen", "systemBackCallback", "Landroid/window/OnBackInvokedCallback;", "open", "", "navigateBack", "", "refreshSummaries", "detachFromHostRoot", "clearActiveState", "navigateTo", "screen", "pushCurrent", "returnToRoot", "renderScreen", "node", "Licu/nullptr/polyglot/youtube/settings/SettingsScreenNode;", "renderPreference", "Licu/nullptr/polyglot/youtube/settings/SettingsNode;", "openSelectionDialog", "Licu/nullptr/polyglot/youtube/settings/SelectionSettingsNode;", "openTextInputDialog", "Licu/nullptr/polyglot/youtube/settings/TextSettingsNode;", "handleSettingsAction", "action", "Licu/nullptr/polyglot/youtube/settings/SettingsAction;", "testConnectivity", "showConnectivityTestResult", "result", "Licu/nullptr/polyglot/translate/ConnectivityTestResult;", "compactForDialog", "", "rebuildCurrentScreen", "dialogContext", "dp", "", "value", "registerSystemBackCallback", "unregisterSystemBackCallback", "ScreenState", "RenderedRow", "Companion", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class NativeSettingsPage {

    @Deprecated
    public static final String CONNECTIVITY_TEST_THREAD_NAME = "PolyglotYT-ConnectivityTest";
    private static final Companion Companion = new Companion(null);

    @Deprecated
    public static final int MAX_CONNECTIVITY_RESULT_LENGTH = 1000;

    @Deprecated
    public static final String TAG = "NativeSettingsPage";
    private final Activity activity;
    private final HostPreferenceAdapter adapter;
    private final ArrayDeque<ScreenState> backStack;
    private final HostPreferenceClasses classes;
    private final AtomicBoolean connectivityTestRunning;
    private final Context context;
    private final SettingsPageController controller;
    private ScreenState currentScreen;
    private final Object fragment;
    private final Handler mainHandler;
    private final Object rootScreen;
    private OnBackInvokedCallback systemBackCallback;
    private final HostToolbarTitle toolbarTitle;

    /* compiled from: NativeSettingsPage.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[SettingsAction.values().length];
            try {
                iArr[SettingsAction.TestConnectivity.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public NativeSettingsPage(Object fragment, Object rootScreen, Context context, HostPreferenceClasses classes, HostPreferenceAdapter adapter, SettingsPageController controller, Activity activity) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(rootScreen, "rootScreen");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(classes, "classes");
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(controller, "controller");
        this.fragment = fragment;
        this.rootScreen = rootScreen;
        this.context = context;
        this.classes = classes;
        this.adapter = adapter;
        this.controller = controller;
        this.activity = activity;
        this.toolbarTitle = new HostToolbarTitle(this.activity, HostToolbarTitleKt.hostSettingsTitle(this.context));
        this.backStack = new ArrayDeque<>();
        this.mainHandler = new Handler(Looper.getMainLooper());
        this.connectivityTestRunning = new AtomicBoolean(false);
    }

    public final Activity getActivity() {
        return this.activity;
    }

    public final void open() {
        this.toolbarTitle.capture();
        this.backStack.clear();
        ScreenState screen = renderScreen(PolyglotSettingsTree.INSTANCE.root());
        if (screen == null) {
            return;
        }
        this.currentScreen = null;
        navigateTo(screen, false);
    }

    public final boolean navigateBack() {
        ScreenState previous = this.backStack.isEmpty() ? null : this.backStack.removeLast();
        if (previous == null) {
            return returnToRoot();
        }
        this.currentScreen = previous;
        boolean shown = this.adapter.showPreferenceScreen(this.fragment, previous.getScreen());
        if (shown) {
            this.toolbarTitle.show(previous.getNode().getTitle());
            refreshSummaries();
        }
        return shown;
    }

    public final void refreshSummaries() {
        ScreenState screen = this.currentScreen;
        if (screen == null) {
            return;
        }
        for (RenderedRow row : screen.getRows()) {
            CharSequence summary = SettingsTreeKt.summary(row.getNode());
            if (summary != null) {
                this.adapter.setSummary(row.getPreference(), summary);
            }
        }
    }

    public final void detachFromHostRoot() {
        clearActiveState();
        this.toolbarTitle.restore();
    }

    private final void clearActiveState() {
        this.backStack.clear();
        this.currentScreen = null;
        unregisterSystemBackCallback();
        Activity activity = this.activity;
        if (activity != null) {
            this.controller.deactivate(activity);
        }
    }

    private final boolean navigateTo(ScreenState screen, boolean pushCurrent) {
        ScreenState screenState;
        if (pushCurrent && (screenState = this.currentScreen) != null) {
            this.backStack.addLast(screenState);
        }
        this.currentScreen = screen;
        Activity activity = this.activity;
        if (activity != null) {
            this.controller.activate(activity, this);
        }
        registerSystemBackCallback();
        boolean shown = this.adapter.showPreferenceScreen(this.fragment, screen.getScreen());
        if (shown) {
            this.toolbarTitle.show(screen.getNode().getTitle());
            refreshSummaries();
        }
        return shown;
    }

    private final boolean returnToRoot() {
        boolean shown = this.adapter.showPreferenceScreen(this.fragment, this.rootScreen);
        clearActiveState();
        this.toolbarTitle.restore();
        return shown;
    }

    private final ScreenState renderScreen(SettingsScreenNode node) {
        Object preference;
        Object screen = this.adapter.createPreferenceScreen(this.fragment, this.context, node.getKey(), node.getTitle());
        if (screen == null) {
            return null;
        }
        List rows = new ArrayList();
        for (SettingsNode child : node.getChildren()) {
            if (child.getVisible().invoke().booleanValue() && (preference = renderPreference(child)) != null && this.adapter.addPreference(screen, preference, this.classes.getPreference())) {
                rows.add(new RenderedRow(child, preference));
            }
        }
        return new ScreenState(node, screen, rows);
    }

    private final Object renderPreference(final SettingsNode node) {
        if (node instanceof SwitchSettingsNode) {
            return this.adapter.createSwitchPreference(this.context, this.classes, ((SwitchSettingsNode) node).getKey(), ((SwitchSettingsNode) node).getTitle(), ((SwitchSettingsNode) node).getIcon(), ((SwitchSettingsNode) node).getSummary().invoke(), ((SwitchSettingsNode) node).getChecked().invoke().booleanValue(), new Function1() { // from class: icu.nullptr.polyglot.youtube.settings.NativeSettingsPage$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return NativeSettingsPage.renderPreference$lambda$3(SettingsNode.this, this, ((Boolean) obj).booleanValue());
                }
            });
        }
        if (node instanceof SelectionSettingsNode) {
            Object createPreference$default = HostPreferenceAdapter.createPreference$default(this.adapter, this.context, this.classes, ((SelectionSettingsNode) node).getKey(), ((SelectionSettingsNode) node).getTitle(), SettingsTreeKt.summary(node), ((SelectionSettingsNode) node).getIcon(), false, 64, null);
            this.controller.registerClickHandler(createPreference$default, new Function0() { // from class: icu.nullptr.polyglot.youtube.settings.NativeSettingsPage$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Boolean.valueOf(NativeSettingsPage.renderPreference$lambda$5$lambda$4(NativeSettingsPage.this, node));
                }
            });
            return createPreference$default;
        }
        if (node instanceof TextSettingsNode) {
            Object createPreference$default2 = HostPreferenceAdapter.createPreference$default(this.adapter, this.context, this.classes, ((TextSettingsNode) node).getKey(), ((TextSettingsNode) node).getTitle(), ((TextSettingsNode) node).getSummary().invoke(), ((TextSettingsNode) node).getIcon(), false, 64, null);
            this.controller.registerClickHandler(createPreference$default2, new Function0() { // from class: icu.nullptr.polyglot.youtube.settings.NativeSettingsPage$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Boolean.valueOf(NativeSettingsPage.renderPreference$lambda$7$lambda$6(NativeSettingsPage.this, node));
                }
            });
            return createPreference$default2;
        }
        if (node instanceof ActionSettingsNode) {
            Object createPreference$default3 = HostPreferenceAdapter.createPreference$default(this.adapter, this.context, this.classes, ((ActionSettingsNode) node).getKey(), ((ActionSettingsNode) node).getTitle(), ((ActionSettingsNode) node).getSummary().invoke(), ((ActionSettingsNode) node).getIcon(), false, 64, null);
            this.controller.registerClickHandler(createPreference$default3, new Function0() { // from class: icu.nullptr.polyglot.youtube.settings.NativeSettingsPage$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Boolean.valueOf(NativeSettingsPage.renderPreference$lambda$9$lambda$8(NativeSettingsPage.this, node));
                }
            });
            return createPreference$default3;
        }
        if (!(node instanceof SettingsScreenNode)) {
            throw new NoWhenBranchMatchedException();
        }
        Object createPreference$default4 = HostPreferenceAdapter.createPreference$default(this.adapter, this.context, this.classes, ((SettingsScreenNode) node).getKey(), ((SettingsScreenNode) node).getTitle(), null, ((SettingsScreenNode) node).getIcon(), false, 64, null);
        this.controller.registerClickHandler(createPreference$default4, new Function0() { // from class: icu.nullptr.polyglot.youtube.settings.NativeSettingsPage$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(NativeSettingsPage.renderPreference$lambda$11$lambda$10(NativeSettingsPage.this, node));
            }
        });
        return createPreference$default4;
    }

    static final Unit renderPreference$lambda$3(SettingsNode $node, NativeSettingsPage this$0, boolean checked) {
        ((SwitchSettingsNode) $node).getOnChanged().invoke(Boolean.valueOf(checked));
        this$0.refreshSummaries();
        return Unit.INSTANCE;
    }

    static final boolean renderPreference$lambda$5$lambda$4(NativeSettingsPage this$0, SettingsNode $node) {
        this$0.openSelectionDialog((SelectionSettingsNode) $node);
        return true;
    }

    static final boolean renderPreference$lambda$7$lambda$6(NativeSettingsPage this$0, SettingsNode $node) {
        this$0.openTextInputDialog((TextSettingsNode) $node);
        return true;
    }

    static final boolean renderPreference$lambda$9$lambda$8(NativeSettingsPage this$0, SettingsNode $node) {
        this$0.handleSettingsAction(((ActionSettingsNode) $node).getAction());
        return true;
    }

    static final boolean renderPreference$lambda$11$lambda$10(NativeSettingsPage this$0, SettingsNode $node) {
        ScreenState screen = this$0.renderScreen((SettingsScreenNode) $node);
        if (screen == null) {
            return false;
        }
        this$0.navigateTo(screen, true);
        return true;
    }

    private final void openSelectionDialog(final SelectionSettingsNode node) {
        Iterable options = node.getOptions();
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(options, 10));
        Iterator it = options.iterator();
        while (it.hasNext()) {
            String label = ((SettingsOption) it.next()).getLabel();
            Intrinsics.checkNotNull(label, "null cannot be cast to non-null type kotlin.CharSequence");
            arrayList.add(label);
        }
        CharSequence[] labels = (CharSequence[]) ((List) arrayList).toArray(new CharSequence[0]);
        int checkedIndex = 0;
        Iterator<SettingsOption> it2 = node.getOptions().iterator();
        while (true) {
            if (!it2.hasNext()) {
                checkedIndex = -1;
                break;
            } else if (Intrinsics.areEqual(((SettingsOption) it2.next()).getValue(), node.getSelectedValue().invoke())) {
                break;
            } else {
                checkedIndex++;
            }
        }
        new AlertDialog.Builder(dialogContext()).setTitle(node.getTitle()).setSingleChoiceItems(labels, checkedIndex, new DialogInterface.OnClickListener() { // from class: icu.nullptr.polyglot.youtube.settings.NativeSettingsPage$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                NativeSettingsPage.openSelectionDialog$lambda$14(SelectionSettingsNode.this, this, dialogInterface, i);
            }
        }).setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null).show();
    }

    static final void openSelectionDialog$lambda$14(SelectionSettingsNode $node, NativeSettingsPage this$0, DialogInterface dialog, int which) {
        SettingsOption option = (SettingsOption) CollectionsKt.getOrNull($node.getOptions(), which);
        if (option == null) {
            return;
        }
        if (!Intrinsics.areEqual(option.getValue(), $node.getSelectedValue().invoke())) {
            $node.getOnSelected().invoke(option.getValue());
            this$0.rebuildCurrentScreen();
        } else {
            this$0.refreshSummaries();
        }
        dialog.dismiss();
    }

    private final void openTextInputDialog(final TextSettingsNode node) {
        Context dialogContext = dialogContext();
        final EditText input = new EditText(dialogContext);
        input.setInputType(node.getInputType());
        input.setSingleLine(true);
        input.setText(node.getValue().invoke());
        Editable text = input.getText();
        input.setSelection(text != null ? text.length() : 0);
        FrameLayout frameLayout = new FrameLayout(dialogContext);
        int dp = dp(dialogContext, 24);
        frameLayout.setPadding(dp, dp(dialogContext, 8), dp, dp(dialogContext, 4));
        frameLayout.addView(input, new FrameLayout.LayoutParams(-1, -2));
        AlertDialog dialog = new AlertDialog.Builder(dialogContext).setTitle(node.getTitle()).setView(frameLayout).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: icu.nullptr.polyglot.youtube.settings.NativeSettingsPage$$ExternalSyntheticLambda9
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                NativeSettingsPage.openTextInputDialog$lambda$17(TextSettingsNode.this, input, this, dialogInterface, i);
            }
        }).setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null).show();
        input.requestFocus();
        Window window = dialog.getWindow();
        if (window != null) {
            window.setSoftInputMode(4);
        }
    }

    static final void openTextInputDialog$lambda$17(TextSettingsNode $node, EditText $input, NativeSettingsPage this$0, DialogInterface dialogInterface, int i) {
        Function1<String, Unit> onSubmitted = $node.getOnSubmitted();
        Editable text = $input.getText();
        String obj = text != null ? text.toString() : null;
        if (obj == null) {
            obj = "";
        }
        onSubmitted.invoke(obj);
        this$0.rebuildCurrentScreen();
    }

    private final void handleSettingsAction(SettingsAction action) {
        if (WhenMappings.$EnumSwitchMapping$0[action.ordinal()] != 1) {
            throw new NoWhenBranchMatchedException();
        }
        testConnectivity();
    }

    private final void testConnectivity() {
        if (!this.connectivityTestRunning.compareAndSet(false, true)) {
            Toast.makeText(dialogContext(), ModuleEntryKt.getModule().getRes().getString(icu.nullptr.polyglot.R.string.connectivity_test_running), 0).show();
            return;
        }
        Toast.makeText(dialogContext(), ModuleEntryKt.getModule().getRes().getString(icu.nullptr.polyglot.R.string.connectivity_test_running), 0).show();
        Thread thread = new Thread(new Runnable() { // from class: icu.nullptr.polyglot.youtube.settings.NativeSettingsPage$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                NativeSettingsPage.testConnectivity$lambda$19(NativeSettingsPage.this);
            }
        }, CONNECTIVITY_TEST_THREAD_NAME);
        thread.setDaemon(true);
        thread.start();
    }

    static final void testConnectivity$lambda$19(final NativeSettingsPage this$0) {
        final ConnectivityTestResult result = ConnectivityTester.INSTANCE.testCurrentProvider();
        this$0.mainHandler.post(new Runnable() { // from class: icu.nullptr.polyglot.youtube.settings.NativeSettingsPage$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                NativeSettingsPage.testConnectivity$lambda$19$lambda$18(NativeSettingsPage.this, result);
            }
        });
    }

    static final void testConnectivity$lambda$19$lambda$18(NativeSettingsPage this$0, ConnectivityTestResult $result) {
        this$0.connectivityTestRunning.set(false);
        this$0.showConnectivityTestResult($result);
    }

    private final void showConnectivityTestResult(ConnectivityTestResult result) {
        Pair pair;
        if (result instanceof ConnectivityTestResult.Success) {
            String compactForDialog = compactForDialog(((ConnectivityTestResult.Success) result).getResponse());
            if (StringsKt.isBlank(compactForDialog)) {
                compactForDialog = "OK";
            }
            String response = compactForDialog;
            pair = TuplesKt.to(ModuleEntryKt.getModule().getRes().getString(icu.nullptr.polyglot.R.string.connectivity_test_success_title), ModuleEntryKt.getModule().getRes().getString(icu.nullptr.polyglot.R.string.connectivity_test_success_message, response));
        } else {
            if (!(result instanceof ConnectivityTestResult.Failure)) {
                throw new NoWhenBranchMatchedException();
            }
            pair = TuplesKt.to(ModuleEntryKt.getModule().getRes().getString(icu.nullptr.polyglot.R.string.connectivity_test_failed_title), compactForDialog(((ConnectivityTestResult.Failure) result).getMessage()));
        }
        Object component1 = pair.component1();
        Intrinsics.checkNotNullExpressionValue(component1, "component1(...)");
        String title = (String) component1;
        Object component2 = pair.component2();
        Intrinsics.checkNotNullExpressionValue(component2, "component2(...)");
        String message = (String) component2;
        new AlertDialog.Builder(dialogContext()).setTitle(title).setMessage(message).setPositiveButton(R.string.ok, (DialogInterface.OnClickListener) null).show();
    }

    private final String compactForDialog(String $this$compactForDialog) {
        String obj = StringsKt.trim((CharSequence) new Regex("\\s+").replace($this$compactForDialog, " ")).toString();
        if (obj.length() > 1000) {
            return StringsKt.take(obj, MAX_CONNECTIVITY_RESULT_LENGTH) + "...";
        }
        return obj;
    }

    private final void rebuildCurrentScreen() {
        SettingsScreenNode node;
        ScreenState screen;
        ScreenState screenState = this.currentScreen;
        if (screenState == null || (node = screenState.getNode()) == null || (screen = renderScreen(node)) == null) {
            return;
        }
        this.currentScreen = screen;
        if (this.adapter.showPreferenceScreen(this.fragment, screen.getScreen())) {
            this.toolbarTitle.show(screen.getNode().getTitle());
            refreshSummaries();
        }
    }

    private final Context dialogContext() {
        Activity activity = this.activity;
        return activity != null ? activity : this.context;
    }

    private final int dp(Context $this$dp, int value) {
        return MathKt.roundToInt(value * $this$dp.getResources().getDisplayMetrics().density);
    }

    private final void registerSystemBackCallback() {
        Object m10constructorimpl;
        Activity hostActivity = this.activity;
        if (Build.VERSION.SDK_INT < 33 || hostActivity == null || this.systemBackCallback != null) {
            return;
        }
        OnBackInvokedCallback callback = new OnBackInvokedCallback() { // from class: icu.nullptr.polyglot.youtube.settings.NativeSettingsPage$$ExternalSyntheticLambda3
            public final void onBackInvoked() {
                NativeSettingsPage.this.navigateBack();
            }
        };
        try {
            Result.Companion companion = Result.INSTANCE;
            hostActivity.getOnBackInvokedDispatcher().registerOnBackInvokedCallback(SettingsConstantsKt.BACK_CALLBACK_PRIORITY, callback);
            this.systemBackCallback = callback;
            LoggerKt.logD$default(TAG, "Registered PolyglotYT native settings system back callback", null, 4, null);
            m10constructorimpl = Result.m10constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m10constructorimpl = Result.m10constructorimpl(ResultKt.createFailure(th));
        }
        Throwable m13exceptionOrNullimpl = Result.m13exceptionOrNullimpl(m10constructorimpl);
        if (m13exceptionOrNullimpl != null) {
            LoggerKt.logW(TAG, "Unable to register PolyglotYT native settings system back callback", m13exceptionOrNullimpl);
        }
    }

    private final void unregisterSystemBackCallback() {
        Object m10constructorimpl;
        OnBackInvokedCallback callback = this.systemBackCallback;
        if (callback == null) {
            return;
        }
        Activity hostActivity = this.activity;
        this.systemBackCallback = null;
        if (Build.VERSION.SDK_INT < 33 || hostActivity == null) {
            return;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            NativeSettingsPage nativeSettingsPage = this;
            hostActivity.getOnBackInvokedDispatcher().unregisterOnBackInvokedCallback(callback);
            LoggerKt.logD$default(TAG, "Unregistered PolyglotYT native settings system back callback", null, 4, null);
            m10constructorimpl = Result.m10constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m10constructorimpl = Result.m10constructorimpl(ResultKt.createFailure(th));
        }
        Throwable m13exceptionOrNullimpl = Result.m13exceptionOrNullimpl(m10constructorimpl);
        if (m13exceptionOrNullimpl != null) {
            LoggerKt.logW(TAG, "Unable to unregister PolyglotYT native settings system back callback", m13exceptionOrNullimpl);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: NativeSettingsPage.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0001\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0001HÆ\u0003J\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0003J-\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Licu/nullptr/polyglot/youtube/settings/NativeSettingsPage$ScreenState;", "", "node", "Licu/nullptr/polyglot/youtube/settings/SettingsScreenNode;", "screen", "rows", "", "Licu/nullptr/polyglot/youtube/settings/NativeSettingsPage$RenderedRow;", "<init>", "(Licu/nullptr/polyglot/youtube/settings/SettingsScreenNode;Ljava/lang/Object;Ljava/util/List;)V", "getNode", "()Licu/nullptr/polyglot/youtube/settings/SettingsScreenNode;", "getScreen", "()Ljava/lang/Object;", "getRows", "()Ljava/util/List;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    static final /* data */ class ScreenState {
        private final SettingsScreenNode node;
        private final List<RenderedRow> rows;
        private final Object screen;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ScreenState copy$default(ScreenState screenState, SettingsScreenNode settingsScreenNode, Object obj, List list, int i, Object obj2) {
            if ((i & 1) != 0) {
                settingsScreenNode = screenState.node;
            }
            if ((i & 2) != 0) {
                obj = screenState.screen;
            }
            if ((i & 4) != 0) {
                list = screenState.rows;
            }
            return screenState.copy(settingsScreenNode, obj, list);
        }

        /* renamed from: component1, reason: from getter */
        public final SettingsScreenNode getNode() {
            return this.node;
        }

        /* renamed from: component2, reason: from getter */
        public final Object getScreen() {
            return this.screen;
        }

        public final List<RenderedRow> component3() {
            return this.rows;
        }

        public final ScreenState copy(SettingsScreenNode node, Object screen, List<RenderedRow> rows) {
            Intrinsics.checkNotNullParameter(node, "node");
            Intrinsics.checkNotNullParameter(screen, "screen");
            Intrinsics.checkNotNullParameter(rows, "rows");
            return new ScreenState(node, screen, rows);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ScreenState)) {
                return false;
            }
            ScreenState screenState = (ScreenState) other;
            return Intrinsics.areEqual(this.node, screenState.node) && Intrinsics.areEqual(this.screen, screenState.screen) && Intrinsics.areEqual(this.rows, screenState.rows);
        }

        public int hashCode() {
            return (((this.node.hashCode() * 31) + this.screen.hashCode()) * 31) + this.rows.hashCode();
        }

        public String toString() {
            return "ScreenState(node=" + this.node + ", screen=" + this.screen + ", rows=" + this.rows + ")";
        }

        public ScreenState(SettingsScreenNode node, Object screen, List<RenderedRow> rows) {
            Intrinsics.checkNotNullParameter(node, "node");
            Intrinsics.checkNotNullParameter(screen, "screen");
            Intrinsics.checkNotNullParameter(rows, "rows");
            this.node = node;
            this.screen = screen;
            this.rows = rows;
        }

        public final SettingsScreenNode getNode() {
            return this.node;
        }

        public final Object getScreen() {
            return this.screen;
        }

        public final List<RenderedRow> getRows() {
            return this.rows;
        }
    }

    /* compiled from: NativeSettingsPage.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0001¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0001HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0001HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Licu/nullptr/polyglot/youtube/settings/NativeSettingsPage$RenderedRow;", "", "node", "Licu/nullptr/polyglot/youtube/settings/SettingsNode;", HostPreferenceAdapter.PREFERENCE_DEFAULT_LAYOUT, "<init>", "(Licu/nullptr/polyglot/youtube/settings/SettingsNode;Ljava/lang/Object;)V", "getNode", "()Licu/nullptr/polyglot/youtube/settings/SettingsNode;", "getPreference", "()Ljava/lang/Object;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final /* data */ class RenderedRow {
        private final SettingsNode node;
        private final Object preference;

        public static /* synthetic */ RenderedRow copy$default(RenderedRow renderedRow, SettingsNode settingsNode, Object obj, int i, Object obj2) {
            if ((i & 1) != 0) {
                settingsNode = renderedRow.node;
            }
            if ((i & 2) != 0) {
                obj = renderedRow.preference;
            }
            return renderedRow.copy(settingsNode, obj);
        }

        /* renamed from: component1, reason: from getter */
        public final SettingsNode getNode() {
            return this.node;
        }

        /* renamed from: component2, reason: from getter */
        public final Object getPreference() {
            return this.preference;
        }

        public final RenderedRow copy(SettingsNode node, Object preference) {
            Intrinsics.checkNotNullParameter(node, "node");
            Intrinsics.checkNotNullParameter(preference, "preference");
            return new RenderedRow(node, preference);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof RenderedRow)) {
                return false;
            }
            RenderedRow renderedRow = (RenderedRow) other;
            return Intrinsics.areEqual(this.node, renderedRow.node) && Intrinsics.areEqual(this.preference, renderedRow.preference);
        }

        public int hashCode() {
            return (this.node.hashCode() * 31) + this.preference.hashCode();
        }

        public String toString() {
            return "RenderedRow(node=" + this.node + ", preference=" + this.preference + ")";
        }

        public RenderedRow(SettingsNode node, Object preference) {
            Intrinsics.checkNotNullParameter(node, "node");
            Intrinsics.checkNotNullParameter(preference, "preference");
            this.node = node;
            this.preference = preference;
        }

        public final SettingsNode getNode() {
            return this.node;
        }

        public final Object getPreference() {
            return this.preference;
        }
    }

    /* compiled from: NativeSettingsPage.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Licu/nullptr/polyglot/youtube/settings/NativeSettingsPage$Companion;", "", "<init>", "()V", "TAG", "", "CONNECTIVITY_TEST_THREAD_NAME", "MAX_CONNECTIVITY_RESULT_LENGTH", "", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
