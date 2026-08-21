package icu.nullptr.polyglot.translate;

import icu.nullptr.polyglot.ModuleEntryKt;
import icu.nullptr.polyglot.captions.CaptionCue;
import icu.nullptr.polyglot.translate.TranslationManager;
import icu.nullptr.polyglot.util.LoggerKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* compiled from: TranslationManager.kt */
@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002=>B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0085\u0001\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u001f\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u00052\u0006\u0010!\u001a\u00020\u00052\b\b\u0002\u0010\"\u001a\u00020\u000726\u0010#\u001a2\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b%\u0012\b\b&\u0012\u0004\b\b('\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b%\u0012\b\b&\u0012\u0004\b\b((\u0012\u0004\u0012\u00020)0$2#\b\u0002\u0010*\u001a\u001d\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b%\u0012\b\b&\u0012\u0004\b\b('\u0012\u0004\u0012\u00020)0+J\u0010\u0010,\u001a\u00020)2\u0006\u0010-\u001a\u00020\u0007H\u0002J\u0016\u0010.\u001a\u00020)2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u001100H\u0002J$\u00101\u001a\b\u0012\u0004\u0012\u00020\u0005002\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u0011002\u0006\u00102\u001a\u00020\u0007H\u0002J$\u00103\u001a\b\u0012\u0004\u0012\u00020\u0005002\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u0011002\u0006\u00102\u001a\u00020\u0007H\u0002J&\u00104\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u00052\u0006\u0010!\u001a\u00020\u00052\u0006\u00102\u001a\u00020\u0007J(\u00105\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u00052\u0006\u0010!\u001a\u00020\u00052\u0006\u00102\u001a\u00020\u0007H\u0002J\b\u00106\u001a\u000207H\u0002J\u0010\u00108\u001a\u00020\t2\u0006\u00109\u001a\u00020\u0007H\u0002J\u0018\u0010:\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020\u00052\u0006\u0010!\u001a\u00020\u0005H\u0002J\u0010\u0010;\u001a\u00020\u00052\u0006\u0010<\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\u0013\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00110\u0014j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0011`\u0015X\u0082\u0004¢\u0006\u0002\n\u0000RN\u0010\u0016\u001aB\u0012\f\u0012\n \u0018*\u0004\u0018\u00010\u00050\u0005\u0012\f\u0012\n \u0018*\u0004\u0018\u00010\u00190\u0019 \u0018* \u0012\f\u0012\n \u0018*\u0004\u0018\u00010\u00050\u0005\u0012\f\u0012\n \u0018*\u0004\u0018\u00010\u00190\u0019\u0018\u00010\u00170\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001c\u001a\n \u0018*\u0004\u0018\u00010\u001d0\u001dX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006?"}, d2 = {"Licu/nullptr/polyglot/translate/TranslationManager;", "", "<init>", "()V", "TAG", "", "MIN_TIMEOUT_MS", "", "BASE_RETRY_DELAY_MS", "", "PRIORITY_VISIBLE", "PRIORITY_UPCOMING", "PRIORITY_BACKGROUND", "WORKER_COUNT", "VISIBLE_REQUEST_TIMEOUT_CAP_MS", "pendingQueue", "Ljava/util/concurrent/PriorityBlockingQueue;", "Licu/nullptr/polyglot/translate/TranslationManager$QueuedTranslation;", "queueLock", "queuedByKey", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "inFlight", "Ljava/util/concurrent/ConcurrentHashMap$KeySetView;", "kotlin.jvm.PlatformType", "", "sequenceCounter", "Ljava/util/concurrent/atomic/AtomicLong;", "executor", "Ljava/util/concurrent/ExecutorService;", "enqueue", "text", "context", "sourceLanguage", "priority", "onTranslated", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "original", "translated", "", "onFailed", "Lkotlin/Function1;", "workerLoop", "index", "dispatchBatch", "batch", "", "translateWithRetry", "timeoutMs", "translateBatch", "translateForConnectivityTest", "translateOnce", "translator", "Licu/nullptr/polyglot/translate/Translator;", "retryDelayMs", "attempt", "requestKey", "normalizedSourceLanguage", "language", "QueuedTranslation", "ThreadFactory", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TranslationManager {
    public static final long BASE_RETRY_DELAY_MS = 750;
    public static final TranslationManager INSTANCE = new TranslationManager();
    public static final int MIN_TIMEOUT_MS = 5000;
    public static final int PRIORITY_BACKGROUND = 2;
    public static final int PRIORITY_UPCOMING = 1;
    public static final int PRIORITY_VISIBLE = 0;
    public static final String TAG = "TranslationManager";
    private static final int VISIBLE_REQUEST_TIMEOUT_CAP_MS = 15000;
    private static final int WORKER_COUNT = 4;
    private static final ExecutorService executor;
    private static final ConcurrentHashMap.KeySetView<String, Boolean> inFlight;
    private static final PriorityBlockingQueue<QueuedTranslation> pendingQueue;
    private static final Object queueLock;
    private static final HashMap<String, QueuedTranslation> queuedByKey;
    private static final AtomicLong sequenceCounter;

    private TranslationManager() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: TranslationManager.kt */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0082\b\u0018\u00002\u00020\u0001B\u008a\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00126\u0010\n\u001a2\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00100\u000b\u0012!\u0010\u0011\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u00100\u0012¢\u0006\u0004\b\u0013\u0010\u0014J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0007HÆ\u0003J\t\u0010%\u001a\u00020\tHÆ\u0003J9\u0010&\u001a2\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00100\u000bHÆ\u0003J$\u0010'\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u00100\u0012HÆ\u0003J\u009a\u0001\u0010(\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t28\b\u0002\u0010\n\u001a2\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00100\u000b2#\b\u0002\u0010\u0011\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u00100\u0012HÆ\u0001J\u0013\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010,\u001a\u00020\u0007HÖ\u0001J\t\u0010-\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cRA\u0010\n\u001a2\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00100\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR,\u0010\u0011\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u00100\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 ¨\u0006."}, d2 = {"Licu/nullptr/polyglot/translate/TranslationManager$QueuedTranslation;", "", "text", "", "context", "sourceLanguage", "priority", "", "sequence", "", "onTranslated", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "original", "translated", "", "onFailed", "Lkotlin/Function1;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IJLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;)V", "getText", "()Ljava/lang/String;", "getContext", "getSourceLanguage", "getPriority", "()I", "getSequence", "()J", "getOnTranslated", "()Lkotlin/jvm/functions/Function2;", "getOnFailed", "()Lkotlin/jvm/functions/Function1;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    static final /* data */ class QueuedTranslation {
        private final String context;
        private final Function1<String, Unit> onFailed;
        private final Function2<String, String, Unit> onTranslated;
        private final int priority;
        private final long sequence;
        private final String sourceLanguage;
        private final String text;

        public static /* synthetic */ QueuedTranslation copy$default(QueuedTranslation queuedTranslation, String str, String str2, String str3, int i, long j, Function2 function2, Function1 function1, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = queuedTranslation.text;
            }
            if ((i2 & 2) != 0) {
                str2 = queuedTranslation.context;
            }
            if ((i2 & 4) != 0) {
                str3 = queuedTranslation.sourceLanguage;
            }
            if ((i2 & 8) != 0) {
                i = queuedTranslation.priority;
            }
            if ((i2 & 16) != 0) {
                j = queuedTranslation.sequence;
            }
            if ((i2 & 32) != 0) {
                function2 = queuedTranslation.onTranslated;
            }
            if ((i2 & 64) != 0) {
                function1 = queuedTranslation.onFailed;
            }
            long j2 = j;
            String str4 = str3;
            int i3 = i;
            return queuedTranslation.copy(str, str2, str4, i3, j2, function2, function1);
        }

        /* renamed from: component1, reason: from getter */
        public final String getText() {
            return this.text;
        }

        /* renamed from: component2, reason: from getter */
        public final String getContext() {
            return this.context;
        }

        /* renamed from: component3, reason: from getter */
        public final String getSourceLanguage() {
            return this.sourceLanguage;
        }

        /* renamed from: component4, reason: from getter */
        public final int getPriority() {
            return this.priority;
        }

        /* renamed from: component5, reason: from getter */
        public final long getSequence() {
            return this.sequence;
        }

        public final Function2<String, String, Unit> component6() {
            return this.onTranslated;
        }

        public final Function1<String, Unit> component7() {
            return this.onFailed;
        }

        public final QueuedTranslation copy(String text, String context, String sourceLanguage, int priority, long sequence, Function2<? super String, ? super String, Unit> onTranslated, Function1<? super String, Unit> onFailed) {
            Intrinsics.checkNotNullParameter(text, "text");
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(sourceLanguage, "sourceLanguage");
            Intrinsics.checkNotNullParameter(onTranslated, "onTranslated");
            Intrinsics.checkNotNullParameter(onFailed, "onFailed");
            return new QueuedTranslation(text, context, sourceLanguage, priority, sequence, onTranslated, onFailed);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof QueuedTranslation)) {
                return false;
            }
            QueuedTranslation queuedTranslation = (QueuedTranslation) other;
            return Intrinsics.areEqual(this.text, queuedTranslation.text) && Intrinsics.areEqual(this.context, queuedTranslation.context) && Intrinsics.areEqual(this.sourceLanguage, queuedTranslation.sourceLanguage) && this.priority == queuedTranslation.priority && this.sequence == queuedTranslation.sequence && Intrinsics.areEqual(this.onTranslated, queuedTranslation.onTranslated) && Intrinsics.areEqual(this.onFailed, queuedTranslation.onFailed);
        }

        public int hashCode() {
            return (((((((((((this.text.hashCode() * 31) + this.context.hashCode()) * 31) + this.sourceLanguage.hashCode()) * 31) + Integer.hashCode(this.priority)) * 31) + Long.hashCode(this.sequence)) * 31) + this.onTranslated.hashCode()) * 31) + this.onFailed.hashCode();
        }

        public String toString() {
            return "QueuedTranslation(text=" + this.text + ", context=" + this.context + ", sourceLanguage=" + this.sourceLanguage + ", priority=" + this.priority + ", sequence=" + this.sequence + ", onTranslated=" + this.onTranslated + ", onFailed=" + this.onFailed + ")";
        }

        /* JADX WARN: Multi-variable type inference failed */
        public QueuedTranslation(String text, String context, String sourceLanguage, int priority, long sequence, Function2<? super String, ? super String, Unit> onTranslated, Function1<? super String, Unit> onFailed) {
            Intrinsics.checkNotNullParameter(text, "text");
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(sourceLanguage, "sourceLanguage");
            Intrinsics.checkNotNullParameter(onTranslated, "onTranslated");
            Intrinsics.checkNotNullParameter(onFailed, "onFailed");
            this.text = text;
            this.context = context;
            this.sourceLanguage = sourceLanguage;
            this.priority = priority;
            this.sequence = sequence;
            this.onTranslated = onTranslated;
            this.onFailed = onFailed;
        }

        public final String getText() {
            return this.text;
        }

        public final String getContext() {
            return this.context;
        }

        public final String getSourceLanguage() {
            return this.sourceLanguage;
        }

        public final int getPriority() {
            return this.priority;
        }

        public final long getSequence() {
            return this.sequence;
        }

        public final Function2<String, String, Unit> getOnTranslated() {
            return this.onTranslated;
        }

        public final Function1<String, Unit> getOnFailed() {
            return this.onFailed;
        }
    }

    static {
        final Function2 function2 = new Function2() { // from class: icu.nullptr.polyglot.translate.TranslationManager$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Integer.valueOf(TranslationManager.pendingQueue$lambda$0((TranslationManager.QueuedTranslation) obj, (TranslationManager.QueuedTranslation) obj2));
            }
        };
        pendingQueue = new PriorityBlockingQueue<>(64, new Comparator() { // from class: icu.nullptr.polyglot.translate.TranslationManager$$ExternalSyntheticLambda2
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int intValue;
                intValue = ((Number) Function2.this.invoke(obj, obj2)).intValue();
                return intValue;
            }
        });
        queueLock = new Object();
        queuedByKey = new HashMap<>();
        inFlight = ConcurrentHashMap.newKeySet();
        sequenceCounter = new AtomicLong(0L);
        executor = Executors.newFixedThreadPool(4, ThreadFactory.INSTANCE);
        for (int i = 0; i < 4; i++) {
            final int i2 = i;
            executor.execute(new Runnable() { // from class: icu.nullptr.polyglot.translate.TranslationManager$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    TranslationManager.INSTANCE.workerLoop(i2);
                }
            });
        }
    }

    static final int pendingQueue$lambda$0(QueuedTranslation a, QueuedTranslation b) {
        int byPriority = Intrinsics.compare(a.getPriority(), b.getPriority());
        return byPriority != 0 ? byPriority : Intrinsics.compare(a.getSequence(), b.getSequence());
    }

    public static /* synthetic */ boolean enqueue$default(TranslationManager translationManager, String str, String str2, String str3, int i, Function2 function2, Function1 function1, int i2, Object obj) {
        int i3;
        Function1 function12;
        if ((i2 & 8) == 0) {
            i3 = i;
        } else {
            i3 = 2;
        }
        if ((i2 & 32) == 0) {
            function12 = function1;
        } else {
            function12 = new Function1() { // from class: icu.nullptr.polyglot.translate.TranslationManager$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return TranslationManager.enqueue$lambda$4((String) obj2);
                }
            };
        }
        return translationManager.enqueue(str, str2, str3, i3, function2, function12);
    }

    static final Unit enqueue$lambda$4(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    public final boolean enqueue(String text, String context, String sourceLanguage, int priority, Function2<? super String, ? super String, Unit> onTranslated, Function1<? super String, Unit> onFailed) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sourceLanguage, "sourceLanguage");
        Intrinsics.checkNotNullParameter(onTranslated, "onTranslated");
        Intrinsics.checkNotNullParameter(onFailed, "onFailed");
        String original = CaptionCue.INSTANCE.normalize(text);
        if (original.length() == 0) {
            return false;
        }
        String requestKey = requestKey(original, sourceLanguage);
        if (inFlight.contains(requestKey)) {
            return false;
        }
        QueuedTranslation item = new QueuedTranslation(original, context, sourceLanguage, priority, sequenceCounter.incrementAndGet(), onTranslated, onFailed);
        synchronized (queueLock) {
            try {
                QueuedTranslation queuedTranslation = queuedByKey.get(requestKey);
                if (queuedTranslation == null) {
                    queuedByKey.put(requestKey, item);
                    Boolean.valueOf(pendingQueue.offer(item));
                } else {
                    try {
                        if (priority < queuedTranslation.getPriority()) {
                            queuedByKey.put(requestKey, item);
                            pendingQueue.remove(queuedTranslation);
                            Boolean.valueOf(pendingQueue.offer(item));
                        } else {
                            Unit unit = Unit.INSTANCE;
                        }
                    } catch (Throwable th) {
                        th = th;
                        throw th;
                    }
                }
                return true;
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0050, code lost:
    
        if (r2.equals("openai-compatible") != false) goto L14;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0094 A[Catch: all -> 0x00b7, InterruptedException -> 0x00d8, TryCatch #2 {InterruptedException -> 0x00d8, all -> 0x00b7, blocks: (B:2:0x0003, B:3:0x0034, B:5:0x0053, B:11:0x0071, B:12:0x0087, B:13:0x008e, B:15:0x0094, B:17:0x009e, B:19:0x00ab, B:22:0x00af, B:28:0x0038, B:32:0x0041, B:35:0x004a), top: B:1:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void workerLoop(int r15) {
        /*
            r14 = this;
        L1:
            java.util.concurrent.PriorityBlockingQueue<icu.nullptr.polyglot.translate.TranslationManager$QueuedTranslation> r0 = icu.nullptr.polyglot.translate.TranslationManager.pendingQueue     // Catch: java.lang.Throwable -> Lb7 java.lang.InterruptedException -> Ld8
            java.lang.Object r0 = r0.take()     // Catch: java.lang.Throwable -> Lb7 java.lang.InterruptedException -> Ld8
            icu.nullptr.polyglot.translate.TranslationManager$QueuedTranslation r0 = (icu.nullptr.polyglot.translate.TranslationManager.QueuedTranslation) r0     // Catch: java.lang.Throwable -> Lb7 java.lang.InterruptedException -> Ld8
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch: java.lang.Throwable -> Lb7 java.lang.InterruptedException -> Ld8
            r1.<init>()     // Catch: java.lang.Throwable -> Lb7 java.lang.InterruptedException -> Ld8
            r1.add(r0)     // Catch: java.lang.Throwable -> Lb7 java.lang.InterruptedException -> Ld8
            icu.nullptr.polyglot.ModuleEntry r2 = icu.nullptr.polyglot.ModuleEntryKt.getModule()     // Catch: java.lang.Throwable -> Lb7 java.lang.InterruptedException -> Ld8
            icu.nullptr.polyglot.core.ConfigManager r2 = r2.getConfig()     // Catch: java.lang.Throwable -> Lb7 java.lang.InterruptedException -> Ld8
            java.lang.String r2 = r2.getProvider()     // Catch: java.lang.Throwable -> Lb7 java.lang.InterruptedException -> Ld8
            java.util.Locale r3 = java.util.Locale.ROOT     // Catch: java.lang.Throwable -> Lb7 java.lang.InterruptedException -> Ld8
            java.lang.String r4 = "ROOT"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)     // Catch: java.lang.Throwable -> Lb7 java.lang.InterruptedException -> Ld8
            java.lang.String r2 = r2.toLowerCase(r3)     // Catch: java.lang.Throwable -> Lb7 java.lang.InterruptedException -> Ld8
            java.lang.String r3 = "toLowerCase(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)     // Catch: java.lang.Throwable -> Lb7 java.lang.InterruptedException -> Ld8
            int r3 = r2.hashCode()     // Catch: java.lang.Throwable -> Lb7 java.lang.InterruptedException -> Ld8
            r4 = 1
            switch(r3) {
                case -1412271217: goto L4a;
                case -1349088399: goto L41;
                case -1010579470: goto L38;
                default: goto L37;
            }     // Catch: java.lang.Throwable -> Lb7 java.lang.InterruptedException -> Ld8
        L37:
            goto L53
        L38:
            java.lang.String r3 = "openai"
            boolean r3 = r2.equals(r3)     // Catch: java.lang.Throwable -> Lb7 java.lang.InterruptedException -> Ld8
            if (r3 != 0) goto L52
            goto L37
        L41:
            java.lang.String r3 = "custom"
            boolean r3 = r2.equals(r3)     // Catch: java.lang.Throwable -> Lb7 java.lang.InterruptedException -> Ld8
            if (r3 != 0) goto L52
            goto L37
        L4a:
            java.lang.String r3 = "openai-compatible"
            boolean r3 = r2.equals(r3)     // Catch: java.lang.Throwable -> Lb7 java.lang.InterruptedException -> Ld8
            if (r3 == 0) goto L37
        L52:
            goto L53
        L53:
            icu.nullptr.polyglot.ModuleEntry r3 = icu.nullptr.polyglot.ModuleEntryKt.getModule()     // Catch: java.lang.Throwable -> Lb7 java.lang.InterruptedException -> Ld8
            icu.nullptr.polyglot.core.ConfigManager r3 = r3.getConfig()     // Catch: java.lang.Throwable -> Lb7 java.lang.InterruptedException -> Ld8
            int r3 = r3.getTranslationBatchSize()     // Catch: java.lang.Throwable -> Lb7 java.lang.InterruptedException -> Ld8
            r5 = 64
            int r3 = kotlin.ranges.RangesKt.coerceIn(r3, r4, r5)     // Catch: java.lang.Throwable -> Lb7 java.lang.InterruptedException -> Ld8
            int r5 = r0.getPriority()     // Catch: java.lang.Throwable -> Lb7 java.lang.InterruptedException -> Ld8
            r6 = 0
            if (r5 == 0) goto L86
            if (r3 != r4) goto L71
            goto L86
        L71:
            icu.nullptr.polyglot.ModuleEntry r4 = icu.nullptr.polyglot.ModuleEntryKt.getModule()     // Catch: java.lang.Throwable -> Lb7 java.lang.InterruptedException -> Ld8
            icu.nullptr.polyglot.core.ConfigManager r4 = r4.getConfig()     // Catch: java.lang.Throwable -> Lb7 java.lang.InterruptedException -> Ld8
            int r4 = r4.getTranslationBatchWindowMs()     // Catch: java.lang.Throwable -> Lb7 java.lang.InterruptedException -> Ld8
            r5 = 0
            r8 = 2000(0x7d0, float:2.803E-42)
            int r4 = kotlin.ranges.RangesKt.coerceIn(r4, r5, r8)     // Catch: java.lang.Throwable -> Lb7 java.lang.InterruptedException -> Ld8
            long r4 = (long) r4     // Catch: java.lang.Throwable -> Lb7 java.lang.InterruptedException -> Ld8
            goto L87
        L86:
            r4 = r6
        L87:
            long r8 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> Lb7 java.lang.InterruptedException -> Ld8
            long r8 = r8 + r4
        L8e:
            int r10 = r1.size()     // Catch: java.lang.Throwable -> Lb7 java.lang.InterruptedException -> Ld8
            if (r10 >= r3) goto Laf
            long r10 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> Lb7 java.lang.InterruptedException -> Ld8
            long r10 = r8 - r10
            int r12 = (r10 > r6 ? 1 : (r10 == r6 ? 0 : -1))
            if (r12 <= 0) goto Laf
            java.util.concurrent.PriorityBlockingQueue<icu.nullptr.polyglot.translate.TranslationManager$QueuedTranslation> r12 = icu.nullptr.polyglot.translate.TranslationManager.pendingQueue     // Catch: java.lang.Throwable -> Lb7 java.lang.InterruptedException -> Ld8
            java.util.concurrent.TimeUnit r13 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch: java.lang.Throwable -> Lb7 java.lang.InterruptedException -> Ld8
            java.lang.Object r12 = r12.poll(r10, r13)     // Catch: java.lang.Throwable -> Lb7 java.lang.InterruptedException -> Ld8
            icu.nullptr.polyglot.translate.TranslationManager$QueuedTranslation r12 = (icu.nullptr.polyglot.translate.TranslationManager.QueuedTranslation) r12     // Catch: java.lang.Throwable -> Lb7 java.lang.InterruptedException -> Ld8
            if (r12 != 0) goto Lab
            goto Laf
        Lab:
            r1.add(r12)     // Catch: java.lang.Throwable -> Lb7 java.lang.InterruptedException -> Ld8
            goto L8e
        Laf:
            r6 = r1
            java.util.List r6 = (java.util.List) r6     // Catch: java.lang.Throwable -> Lb7 java.lang.InterruptedException -> Ld8
            r14.dispatchBatch(r6)     // Catch: java.lang.Throwable -> Lb7 java.lang.InterruptedException -> Ld8
            goto L1
        Lb7:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Translation worker "
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r1 = r1.append(r15)
            java.lang.String r2 = " crashed"
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "TranslationManager"
            icu.nullptr.polyglot.util.LoggerKt.logW(r2, r1, r0)
            goto L1
        Ld8:
            r0 = move-exception
            java.lang.Thread r1 = java.lang.Thread.currentThread()
            r1.interrupt()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: icu.nullptr.polyglot.translate.TranslationManager.workerLoop(int):void");
    }

    private final void dispatchBatch(List<QueuedTranslation> batch) {
        int timeoutMs;
        Object m10constructorimpl;
        ArrayList claimed = new ArrayList(batch.size());
        synchronized (queueLock) {
            for (QueuedTranslation queuedTranslation : batch) {
                String requestKey = INSTANCE.requestKey(queuedTranslation.getText(), queuedTranslation.getSourceLanguage());
                if (queuedByKey.remove(requestKey, queuedTranslation) && inFlight.add(requestKey)) {
                    claimed.add(queuedTranslation);
                }
            }
            Unit unit = Unit.INSTANCE;
        }
        if (claimed.isEmpty()) {
            return;
        }
        ArrayList arrayList = claimed;
        boolean containsVisible = false;
        if (!(arrayList instanceof Collection) || !arrayList.isEmpty()) {
            Iterator it = arrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if ((((QueuedTranslation) it.next()).getPriority() == 0 ? 1 : null) != null) {
                    containsVisible = true;
                    break;
                }
            }
        }
        if (!containsVisible) {
            timeoutMs = RangesKt.coerceAtMost(RangesKt.coerceAtLeast(ModuleEntryKt.getModule().getConfig().getRequestTimeoutMs(), MIN_TIMEOUT_MS), 20000);
        } else {
            timeoutMs = RangesKt.coerceAtMost(RangesKt.coerceAtLeast(ModuleEntryKt.getModule().getConfig().getRequestTimeoutMs(), MIN_TIMEOUT_MS), 8000);
        }
        try {
            List translated = translateWithRetry(claimed, timeoutMs);
            for (Pair pair : CollectionsKt.zip(claimed, translated)) {
                QueuedTranslation item = (QueuedTranslation) pair.component1();
                String result = (String) pair.component2();
                if (!StringsKt.isBlank(result)) {
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        TranslationManager translationManager = this;
                        item.getOnTranslated().invoke(item.getText(), result);
                        m10constructorimpl = Result.m10constructorimpl(Unit.INSTANCE);
                    } catch (Throwable th) {
                        Result.Companion companion2 = Result.INSTANCE;
                        m10constructorimpl = Result.m10constructorimpl(ResultKt.createFailure(th));
                    }
                    Throwable m13exceptionOrNullimpl = Result.m13exceptionOrNullimpl(m10constructorimpl);
                    if (m13exceptionOrNullimpl != null) {
                        LoggerKt.logW(TAG, "Translation callback failed", m13exceptionOrNullimpl);
                    }
                }
            }
        } catch (Throwable e) {
            try {
                LoggerKt.logW(TAG, "Caption translation failed, batchSize=" + claimed.size(), e);
                Iterator it2 = claimed.iterator();
                Intrinsics.checkNotNullExpressionValue(it2, "iterator(...)");
                while (it2.hasNext()) {
                    Object next = it2.next();
                    Intrinsics.checkNotNullExpressionValue(next, "next(...)");
                    QueuedTranslation item2 = (QueuedTranslation) next;
                    try {
                        Result.Companion companion3 = Result.INSTANCE;
                        item2.getOnFailed().invoke(item2.getText());
                        Result.m10constructorimpl(Unit.INSTANCE);
                    } catch (Throwable th2) {
                        Result.Companion companion4 = Result.INSTANCE;
                        Result.m10constructorimpl(ResultKt.createFailure(th2));
                    }
                }
                Iterator it3 = claimed.iterator();
                Intrinsics.checkNotNullExpressionValue(it3, "iterator(...)");
                while (it3.hasNext()) {
                    Object next2 = it3.next();
                    Intrinsics.checkNotNullExpressionValue(next2, "next(...)");
                    QueuedTranslation item3 = (QueuedTranslation) next2;
                    inFlight.remove(requestKey(item3.getText(), item3.getSourceLanguage()));
                }
            } finally {
                Iterator it4 = claimed.iterator();
                Intrinsics.checkNotNullExpressionValue(it4, "iterator(...)");
                while (it4.hasNext()) {
                    Object next3 = it4.next();
                    Intrinsics.checkNotNullExpressionValue(next3, "next(...)");
                    QueuedTranslation item4 = (QueuedTranslation) next3;
                    inFlight.remove(requestKey(item4.getText(), item4.getSourceLanguage()));
                }
            }
        }
    }

    private final List<String> translateWithRetry(List<QueuedTranslation> batch, int timeoutMs) {
        Throwable th = null;
        int maxRetries = RangesKt.coerceAtLeast(ModuleEntryKt.getModule().getConfig().getMaxRetries(), 0);
        Iterator<QueuedTranslation> it = batch.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            if (it.next().getPriority() == 0) {
                maxRetries = Math.min(maxRetries, 1);
                break;
            }
        }
        for (int attempt = 0; attempt <= maxRetries; attempt++) {
            try {
                Result.Companion companion = Result.INSTANCE;
                return translateBatch(batch, timeoutMs);
            } catch (Throwable th2) {
                Result.Companion companion2 = Result.INSTANCE;
                Throwable m13exceptionOrNullimpl = Result.m13exceptionOrNullimpl(Result.m10constructorimpl(ResultKt.createFailure(th2)));
                if (m13exceptionOrNullimpl != null) {
                    th = m13exceptionOrNullimpl;
                    if (attempt < maxRetries) {
                        Thread.sleep(INSTANCE.retryDelayMs(attempt));
                    }
                }
            }
        }
        if (th == null) {
            throw new IllegalStateException("Translation cancelled");
        }
        throw th;
    }

    private final List<String> translateBatch(List<QueuedTranslation> batch, int timeoutMs) {
        List<QueuedTranslation> list = batch;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((QueuedTranslation) it.next()).getText());
        }
        List texts = (List) arrayList;
        TranslationRequest request = new TranslationRequest(texts, normalizedSourceLanguage(((QueuedTranslation) CollectionsKt.first((List) batch)).getSourceLanguage()), ModuleEntryKt.getModule().getConfig().getTargetLanguage(), ((QueuedTranslation) CollectionsKt.first((List) batch)).getContext(), timeoutMs);
        TranslationResult result = translator().translate(request);
        if (result.getTexts().size() != texts.size()) {
            throw new IllegalStateException("Provider returned " + result.getTexts().size() + " results for " + texts.size() + " texts");
        }
        return result.getTexts();
    }

    public final String translateForConnectivityTest(String text, String context, String sourceLanguage, int timeoutMs) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sourceLanguage, "sourceLanguage");
        return translateOnce(CaptionCue.INSTANCE.normalize(text), context, sourceLanguage, RangesKt.coerceAtLeast(timeoutMs, MIN_TIMEOUT_MS));
    }

    private final String translateOnce(String text, String context, String sourceLanguage, int timeoutMs) {
        if (StringsKt.isBlank(text)) {
            return "";
        }
        TranslationRequest request = new TranslationRequest(CollectionsKt.listOf(text), normalizedSourceLanguage(sourceLanguage), ModuleEntryKt.getModule().getConfig().getTargetLanguage(), context, timeoutMs);
        String str = (String) CollectionsKt.firstOrNull((List) translator().translate(request).getTexts());
        return str == null ? "" : str;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0041, code lost:
    
        if (r0.equals("custom") == false) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x004a, code lost:
    
        if (r0.equals("openai-compatible") != false) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0038, code lost:
    
        if (r0.equals(icu.nullptr.polyglot.core.ConfigManager.PROVIDER_OPENAI) == false) goto L18;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final icu.nullptr.polyglot.translate.Translator translator() {
        /*
            r3 = this;
            icu.nullptr.polyglot.ModuleEntry r0 = icu.nullptr.polyglot.ModuleEntryKt.getModule()
            icu.nullptr.polyglot.core.ConfigManager r0 = r0.getConfig()
            java.lang.String r0 = r0.getProvider()
            java.util.Locale r1 = java.util.Locale.ROOT
            java.lang.String r2 = "ROOT"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            java.lang.String r0 = r0.toLowerCase(r1)
            java.lang.String r1 = "toLowerCase(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            int r1 = r0.hashCode()
            switch(r1) {
                case -1412271217: goto L44;
                case -1349088399: goto L3b;
                case -1010579470: goto L32;
                case -94228242: goto L24;
                default: goto L23;
            }
        L23:
            goto L51
        L24:
            java.lang.String r1 = "microsoft"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L2d
            goto L23
        L2d:
            icu.nullptr.polyglot.translate.providers.MicrosoftTranslator r0 = icu.nullptr.polyglot.translate.providers.MicrosoftTranslator.INSTANCE
            icu.nullptr.polyglot.translate.Translator r0 = (icu.nullptr.polyglot.translate.Translator) r0
            goto L55
        L32:
            java.lang.String r1 = "openai"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L4c
            goto L23
        L3b:
            java.lang.String r1 = "custom"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L4c
            goto L23
        L44:
            java.lang.String r1 = "openai-compatible"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L23
        L4c:
            icu.nullptr.polyglot.translate.providers.OpenAICompatibleTranslator r0 = icu.nullptr.polyglot.translate.providers.OpenAICompatibleTranslator.INSTANCE
            icu.nullptr.polyglot.translate.Translator r0 = (icu.nullptr.polyglot.translate.Translator) r0
            goto L55
        L51:
            icu.nullptr.polyglot.translate.providers.YouTubeCommentTranslator r0 = icu.nullptr.polyglot.translate.providers.YouTubeCommentTranslator.INSTANCE
            icu.nullptr.polyglot.translate.Translator r0 = (icu.nullptr.polyglot.translate.Translator) r0
        L55:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: icu.nullptr.polyglot.translate.TranslationManager.translator():icu.nullptr.polyglot.translate.Translator");
    }

    private final long retryDelayMs(int attempt) {
        return (attempt + 1) * 750;
    }

    private final String requestKey(String text, String sourceLanguage) {
        return CollectionsKt.joinToString$default(CollectionsKt.listOf((Object[]) new String[]{ModuleEntryKt.getModule().getConfig().getProvider(), normalizedSourceLanguage(sourceLanguage), ModuleEntryKt.getModule().getConfig().getTargetLanguage(), text}), "\n", null, null, 0, null, null, 62, null);
    }

    private final String normalizedSourceLanguage(String language) {
        String obj = StringsKt.trim((CharSequence) language).toString();
        if (obj.length() == 0) {
            obj = "auto";
        }
        return obj;
    }

    /* compiled from: TranslationManager.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Licu/nullptr/polyglot/translate/TranslationManager$ThreadFactory;", "Ljava/util/concurrent/ThreadFactory;", "<init>", "()V", "counter", "Ljava/util/concurrent/atomic/AtomicInteger;", "newThread", "Ljava/lang/Thread;", "runnable", "Ljava/lang/Runnable;", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class ThreadFactory implements java.util.concurrent.ThreadFactory {
        public static final ThreadFactory INSTANCE = new ThreadFactory();
        private static final AtomicInteger counter = new AtomicInteger(0);

        private ThreadFactory() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Intrinsics.checkNotNullParameter(runnable, "runnable");
            Thread thread = new Thread(runnable, "PolyglotYT-Translator-" + counter.incrementAndGet());
            thread.setDaemon(true);
            return thread;
        }
    }
}
