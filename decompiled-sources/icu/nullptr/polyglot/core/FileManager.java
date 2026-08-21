package icu.nullptr.polyglot.core;

import android.content.Context;
import java.io.File;
import kotlin.Metadata;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FileManager.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\n¨\u0006\r"}, d2 = {"Licu/nullptr/polyglot/core/FileManager;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "filesDir", "Ljava/io/File;", "configDir", "getConfigDir", "()Ljava/io/File;", "dexKitDir", "getDexKitDir", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class FileManager {
    private final File configDir;
    private final File dexKitDir;
    private final File filesDir;

    public FileManager(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        File filesDir = context.getFilesDir();
        Intrinsics.checkNotNullExpressionValue(filesDir, "getFilesDir(...)");
        this.filesDir = FilesKt.resolve(filesDir, "polyglotyt");
        this.configDir = FilesKt.resolve(this.filesDir, "config");
        this.dexKitDir = FilesKt.resolve(this.filesDir, "dexkit");
        this.configDir.mkdirs();
        this.dexKitDir.mkdirs();
    }

    public final File getConfigDir() {
        return this.configDir;
    }

    public final File getDexKitDir() {
        return this.dexKitDir;
    }
}
