package icu.nullptr.polyglot.translate;

import icu.nullptr.polyglot.ModuleEntryKt;
import icu.nullptr.polyglot.translate.ConnectivityTestResult;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: ConnectivityTester.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Licu/nullptr/polyglot/translate/ConnectivityTester;", "", "<init>", "()V", "TEST_TEXT", "", "TEST_CONTEXT", "TEST_SOURCE_LANGUAGE", "TEST_TIMEOUT_MS", "", "testCurrentProvider", "Licu/nullptr/polyglot/translate/ConnectivityTestResult;", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ConnectivityTester {
    public static final ConnectivityTester INSTANCE = new ConnectivityTester();
    public static final String TEST_CONTEXT = "PolyglotYT connectivity test";
    public static final String TEST_SOURCE_LANGUAGE = "en";
    public static final String TEST_TEXT = "This is a test message for PolyglotYT connectivity.";
    public static final int TEST_TIMEOUT_MS = 15000;

    private ConnectivityTester() {
    }

    public final ConnectivityTestResult testCurrentProvider() {
        Object m10constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            ConnectivityTester connectivityTester = this;
            m10constructorimpl = Result.m10constructorimpl(new ConnectivityTestResult.Success(TranslationManager.INSTANCE.translateForConnectivityTest(TEST_TEXT, TEST_CONTEXT, TEST_SOURCE_LANGUAGE, RangesKt.coerceIn(ModuleEntryKt.getModule().getConfig().getRequestTimeoutMs(), TranslationManager.MIN_TIMEOUT_MS, TEST_TIMEOUT_MS))));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m10constructorimpl = Result.m10constructorimpl(ResultKt.createFailure(th));
        }
        Throwable m13exceptionOrNullimpl = Result.m13exceptionOrNullimpl(m10constructorimpl);
        if (m13exceptionOrNullimpl != null) {
            String message = m13exceptionOrNullimpl.getMessage();
            if (message == null) {
                message = m13exceptionOrNullimpl.getClass().getSimpleName();
            }
            Intrinsics.checkNotNull(message);
            m10constructorimpl = new ConnectivityTestResult.Failure(message);
        }
        return (ConnectivityTestResult) m10constructorimpl;
    }
}
