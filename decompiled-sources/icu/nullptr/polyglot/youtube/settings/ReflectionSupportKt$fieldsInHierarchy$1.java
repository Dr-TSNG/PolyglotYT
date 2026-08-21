package icu.nullptr.polyglot.youtube.settings;

import java.lang.reflect.Field;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequenceScope;

/* compiled from: ReflectionSupport.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", "Ljava/lang/reflect/Field;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "icu.nullptr.polyglot.youtube.settings.ReflectionSupportKt$fieldsInHierarchy$1", f = "ReflectionSupport.kt", i = {0, 0}, l = {28}, m = "invokeSuspend", n = {"$this$sequence", "clazz"}, s = {"L$0", "L$1"})
/* loaded from: classes11.dex */
final class ReflectionSupportKt$fieldsInHierarchy$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super Field>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Class<?> $this_fieldsInHierarchy;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ReflectionSupportKt$fieldsInHierarchy$1(Class<?> cls, Continuation<? super ReflectionSupportKt$fieldsInHierarchy$1> continuation) {
        super(2, continuation);
        this.$this_fieldsInHierarchy = cls;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ReflectionSupportKt$fieldsInHierarchy$1 reflectionSupportKt$fieldsInHierarchy$1 = new ReflectionSupportKt$fieldsInHierarchy$1(this.$this_fieldsInHierarchy, continuation);
        reflectionSupportKt$fieldsInHierarchy$1.L$0 = obj;
        return reflectionSupportKt$fieldsInHierarchy$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(SequenceScope<? super Field> sequenceScope, Continuation<? super Unit> continuation) {
        return ((ReflectionSupportKt$fieldsInHierarchy$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0026  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:10:0x0043 -> B:7:0x0046). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            java.lang.Object r0 = r7.L$0
            kotlin.sequences.SequenceScope r0 = (kotlin.sequences.SequenceScope) r0
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r7.label
            switch(r2) {
                case 0: goto L1e;
                case 1: goto L15;
                default: goto Ld;
            }
        Ld:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L15:
            java.lang.Object r2 = r7.L$1
            java.lang.Class r2 = (java.lang.Class) r2
            kotlin.ResultKt.throwOnFailure(r8)
            r3 = r7
            goto L46
        L1e:
            kotlin.ResultKt.throwOnFailure(r8)
            java.lang.Class<?> r2 = r7.$this_fieldsInHierarchy
            r3 = r7
        L24:
            if (r2 == 0) goto L4b
            java.lang.reflect.Field[] r4 = r2.getDeclaredFields()
            java.lang.String r5 = "getDeclaredFields(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r5)
            java.lang.Object[] r4 = (java.lang.Object[]) r4
            kotlin.sequences.Sequence r4 = kotlin.collections.ArraysKt.asSequence(r4)
            r5 = r3
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r3.L$0 = r0
            r3.L$1 = r2
            r6 = 1
            r3.label = r6
            java.lang.Object r4 = r0.yieldAll(r4, r5)
            if (r4 != r1) goto L46
            return r1
        L46:
            java.lang.Class r2 = r2.getSuperclass()
            goto L24
        L4b:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: icu.nullptr.polyglot.youtube.settings.ReflectionSupportKt$fieldsInHierarchy$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
