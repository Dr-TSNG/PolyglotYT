package icu.nullptr.polyglot.dex;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import org.luckypray.dexkit.DexKitCacheBridge;

/* compiled from: DexKitFileCache.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\b\u0004\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001c\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\nH\u0016J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\nH\u0016J(\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u00112\u0006\u0010\u000b\u001a\u00020\n2\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0011H\u0016J\u001e\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\n2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\u0011H\u0016J\u0010\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\u000e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\n0\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u000eH\u0016J\b\u0010\u0017\u001a\u00020\u000eH\u0002R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Licu/nullptr/polyglot/dex/DexKitFileCache;", "Lorg/luckypray/dexkit/DexKitCacheBridge$Cache;", "directory", "Ljava/io/File;", "<init>", "(Ljava/io/File;)V", "file", "properties", "Ljava/util/Properties;", "getString", "", "key", "default", "putString", "", "value", "getStringList", "", "putStringList", "remove", "getAllKeys", "", "clearAll", "flush", "Companion", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes8.dex */
public final class DexKitFileCache implements DexKitCacheBridge.Cache {
    private static final Companion Companion = new Companion(null);

    @Deprecated
    public static final String LIST_PREFIX = "l.";

    @Deprecated
    public static final String LIST_SEPARATOR = "\u001f";

    @Deprecated
    public static final String STRING_PREFIX = "s.";
    private final File file;
    private final Properties properties;

    public DexKitFileCache(File directory) {
        Object m10constructorimpl;
        Intrinsics.checkNotNullParameter(directory, "directory");
        this.file = new File(directory, "dexkit-cache.properties");
        this.properties = new Properties();
        if (!this.file.exists()) {
            return;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            DexKitFileCache dexKitFileCache = this;
            InputStream fileInputStream = new FileInputStream(dexKitFileCache.file);
            try {
                dexKitFileCache.properties.load(fileInputStream);
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(fileInputStream, null);
                m10constructorimpl = Result.m10constructorimpl(Unit.INSTANCE);
            } finally {
            }
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m10constructorimpl = Result.m10constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m13exceptionOrNullimpl(m10constructorimpl) == null) {
            return;
        }
        this.properties.clear();
    }

    @Override // org.luckypray.dexkit.DexKitCacheBridge.Cache
    public synchronized String getString(String key, String r5) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.properties.getProperty(STRING_PREFIX + key, r5);
    }

    @Override // org.luckypray.dexkit.DexKitCacheBridge.Cache
    public synchronized void putString(String key, String value) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        this.properties.setProperty(STRING_PREFIX + key, value);
        flush();
    }

    @Override // org.luckypray.dexkit.DexKitCacheBridge.Cache
    public synchronized List<String> getStringList(String key, List<String> r10) {
        Intrinsics.checkNotNullParameter(key, "key");
        String propertyKey = LIST_PREFIX + key;
        if (!this.properties.containsKey(propertyKey)) {
            return r10;
        }
        String raw = this.properties.getProperty(propertyKey, "");
        Intrinsics.checkNotNull(raw);
        if (raw.length() == 0) {
            return CollectionsKt.emptyList();
        }
        return StringsKt.split$default((CharSequence) raw, new String[]{LIST_SEPARATOR}, false, 0, 6, (Object) null);
    }

    @Override // org.luckypray.dexkit.DexKitCacheBridge.Cache
    public synchronized void putStringList(String key, List<String> value) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        this.properties.setProperty(LIST_PREFIX + key, CollectionsKt.joinToString$default(value, LIST_SEPARATOR, null, null, 0, null, null, 62, null));
        flush();
    }

    @Override // org.luckypray.dexkit.DexKitCacheBridge.Cache
    public synchronized void remove(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        this.properties.remove(STRING_PREFIX + key);
        this.properties.remove(LIST_PREFIX + key);
        flush();
    }

    @Override // org.luckypray.dexkit.DexKitCacheBridge.Cache
    public synchronized Collection<String> getAllKeys() {
        Set keySet;
        keySet = this.properties.keySet();
        Intrinsics.checkNotNullExpressionValue(keySet, "<get-keys>(...)");
        return SequencesKt.toSet(SequencesKt.mapNotNull(SequencesKt.map(CollectionsKt.asSequence(keySet), new Function1() { // from class: icu.nullptr.polyglot.dex.DexKitFileCache$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                String obj2;
                obj2 = obj.toString();
                return obj2;
            }
        }), new Function1() { // from class: icu.nullptr.polyglot.dex.DexKitFileCache$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DexKitFileCache.getAllKeys$lambda$3((String) obj);
            }
        }));
    }

    static final String getAllKeys$lambda$3(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        if (StringsKt.startsWith$default(key, STRING_PREFIX, false, 2, (Object) null)) {
            return StringsKt.removePrefix(key, (CharSequence) STRING_PREFIX);
        }
        if (StringsKt.startsWith$default(key, LIST_PREFIX, false, 2, (Object) null)) {
            return StringsKt.removePrefix(key, (CharSequence) LIST_PREFIX);
        }
        return null;
    }

    @Override // org.luckypray.dexkit.DexKitCacheBridge.Cache
    public synchronized void clearAll() {
        this.properties.clear();
        flush();
    }

    private final void flush() {
        FileOutputStream fileOutputStream = new FileOutputStream(this.file);
        try {
            this.properties.store(fileOutputStream, "PolyglotYT DexKit cache");
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(fileOutputStream, null);
        } finally {
        }
    }

    /* compiled from: DexKitFileCache.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Licu/nullptr/polyglot/dex/DexKitFileCache$Companion;", "", "<init>", "()V", "STRING_PREFIX", "", "LIST_PREFIX", "LIST_SEPARATOR", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
