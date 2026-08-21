package icu.nullptr.polyglot.youtube;

import kotlin.Metadata;
import org.luckypray.dexkit.DexKitBridge;

/* compiled from: BaseHook.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\rÀ\u0006\u0003"}, d2 = {"Licu/nullptr/polyglot/youtube/BaseHook;", "", "name", "", "getName", "()Ljava/lang/String;", "totalHooks", "", "getTotalHooks", "()I", "install", "dexkit", "Lorg/luckypray/dexkit/DexKitBridge;", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public interface BaseHook {
    String getName();

    int getTotalHooks();

    int install(DexKitBridge dexkit);
}
