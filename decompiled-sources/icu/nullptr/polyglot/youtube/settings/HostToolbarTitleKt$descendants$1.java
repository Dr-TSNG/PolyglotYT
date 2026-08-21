package icu.nullptr.polyglot.youtube.settings;

import android.view.View;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequenceScope;

/* JADX INFO: Add missing generic type declarations: [T] */
/* compiled from: HostToolbarTitle.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "T", "Landroid/view/View;", "Lkotlin/sequences/SequenceScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "icu.nullptr.polyglot.youtube.settings.HostToolbarTitleKt$descendants$1", f = "HostToolbarTitle.kt", i = {0, 0, 0, 1, 1}, l = {96, 100}, m = "invokeSuspend", n = {"$this$sequence", "it\\1", "$i$a$-let-HostToolbarTitleKt$descendants$1$1\\1\\96\\0", "$this$sequence", "index"}, s = {"L$0", "L$1", "I$0", "L$0", "I$0"})
/* loaded from: classes11.dex */
final class HostToolbarTitleKt$descendants$1<T> extends RestrictedSuspendLambda implements Function2<SequenceScope<? super T>, Continuation<? super Unit>, Object> {
    final /* synthetic */ View $this_descendants;
    final /* synthetic */ Class<T> $type;
    int I$0;
    int I$1;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HostToolbarTitleKt$descendants$1(Class<T> cls, View view, Continuation<? super HostToolbarTitleKt$descendants$1> continuation) {
        super(2, continuation);
        this.$type = cls;
        this.$this_descendants = view;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        HostToolbarTitleKt$descendants$1 hostToolbarTitleKt$descendants$1 = new HostToolbarTitleKt$descendants$1(this.$type, this.$this_descendants, continuation);
        hostToolbarTitleKt$descendants$1.L$0 = obj;
        return hostToolbarTitleKt$descendants$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(SequenceScope<? super T> sequenceScope, Continuation<? super Unit> continuation) {
        return ((HostToolbarTitleKt$descendants$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x006e  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:10:0x0094 -> B:7:0x0097). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) {
        /*
            r9 = this;
            java.lang.Object r0 = r9.L$0
            kotlin.sequences.SequenceScope r0 = (kotlin.sequences.SequenceScope) r0
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r9.label
            r3 = 1
            switch(r2) {
                case 0: goto L2a;
                case 1: goto L20;
                case 2: goto L16;
                default: goto Le;
            }
        Le:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L16:
            int r2 = r9.I$1
            int r4 = r9.I$0
            kotlin.ResultKt.throwOnFailure(r10)
            r5 = r9
            goto L97
        L20:
            int r2 = r9.I$0
            java.lang.Object r4 = r9.L$1
            android.view.View r4 = (android.view.View) r4
            kotlin.ResultKt.throwOnFailure(r10)
            goto L58
        L2a:
            kotlin.ResultKt.throwOnFailure(r10)
            java.lang.Class<T> r2 = r9.$type
            android.view.View r4 = r9.$this_descendants
            boolean r2 = r2.isInstance(r4)
            if (r2 == 0) goto L59
            java.lang.Class<T> r2 = r9.$type
            android.view.View r4 = r9.$this_descendants
            java.lang.Object r2 = r2.cast(r4)
            android.view.View r2 = (android.view.View) r2
            if (r2 == 0) goto L59
            r4 = r2
            r2 = 0
            r9.L$0 = r0
            java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r4)
            r9.L$1 = r5
            r9.I$0 = r2
            r9.label = r3
            java.lang.Object r5 = r0.yield(r4, r9)
            if (r5 != r1) goto L58
            return r1
        L58:
        L59:
            android.view.View r2 = r9.$this_descendants
            boolean r2 = r2 instanceof android.view.ViewGroup
            if (r2 == 0) goto L99
            r2 = 0
            android.view.View r4 = r9.$this_descendants
            android.view.ViewGroup r4 = (android.view.ViewGroup) r4
            int r4 = r4.getChildCount()
            r5 = r4
            r4 = r2
            r2 = r5
            r5 = r9
        L6c:
            if (r4 >= r2) goto L9a
            android.view.View r6 = r5.$this_descendants
            android.view.ViewGroup r6 = (android.view.ViewGroup) r6
            android.view.View r6 = r6.getChildAt(r4)
            java.lang.String r7 = "getChildAt(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r7)
            java.lang.Class<T> r7 = r5.$type
            kotlin.sequences.Sequence r6 = icu.nullptr.polyglot.youtube.settings.HostToolbarTitleKt.access$descendants(r6, r7)
            r7 = r5
            kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7
            r5.L$0 = r0
            r8 = 0
            r5.L$1 = r8
            r5.I$0 = r4
            r5.I$1 = r2
            r8 = 2
            r5.label = r8
            java.lang.Object r6 = r0.yieldAll(r6, r7)
            if (r6 != r1) goto L97
            return r1
        L97:
            int r4 = r4 + r3
            goto L6c
        L99:
            r5 = r9
        L9a:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: icu.nullptr.polyglot.youtube.settings.HostToolbarTitleKt$descendants$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
