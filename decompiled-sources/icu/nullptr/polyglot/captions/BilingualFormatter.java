package icu.nullptr.polyglot.captions;

import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.style.AlignmentSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import icu.nullptr.polyglot.ModuleEntryKt;
import icu.nullptr.polyglot.captions.CaptionCue;
import icu.nullptr.polyglot.core.ConfigManager;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* compiled from: BilingualFormatter.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ \u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0010\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005H\u0002J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\bH\u0002¨\u0006\u0013"}, d2 = {"Licu/nullptr/polyglot/captions/BilingualFormatter;", "", "<init>", "()V", "format", "", "original", "translated", "", "buildBilingual", "firstLine", "secondLine", "translationLineFirst", "", "centerAligned", "text", "translationColor", "", "preset", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class BilingualFormatter {
    public static final BilingualFormatter INSTANCE = new BilingualFormatter();

    private BilingualFormatter() {
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    public final CharSequence format(CharSequence original, String translated) {
        CaptionCue.Companion companion = CaptionCue.INSTANCE;
        String obj = original != null ? original.toString() : null;
        if (obj == null) {
            obj = "";
        }
        String originalText = companion.normalize(obj);
        String translatedText = CaptionCue.INSTANCE.normalize(translated == null ? "" : translated);
        if (StringsKt.isBlank(translatedText) || Intrinsics.areEqual(translatedText, originalText)) {
            return original == null ? "" : original;
        }
        String subtitleMode = ModuleEntryKt.getModule().getConfig().getSubtitleMode();
        switch (subtitleMode.hashCode()) {
            case -1684673414:
                if (subtitleMode.equals(ConfigManager.SUBTITLE_TRANSLATION_ONLY)) {
                    return translatedText;
                }
                break;
            case -693723230:
                if (subtitleMode.equals(ConfigManager.SUBTITLE_TRANSLATION_FIRST)) {
                    return buildBilingual(translatedText, originalText, true);
                }
                break;
            case 1917411010:
                if (subtitleMode.equals(ConfigManager.SUBTITLE_ORIGINAL_FIRST)) {
                    return buildBilingual(originalText, translatedText, false);
                }
                break;
        }
        return translatedText;
    }

    private final CharSequence buildBilingual(String firstLine, String secondLine, boolean translationLineFirst) {
        int valueOf;
        Integer valueOf2;
        if (!ModuleEntryKt.getModule().getConfig().getSubtitleStyleEnabled()) {
            return centerAligned(firstLine + "\n" + secondLine);
        }
        String separator = ModuleEntryKt.getModule().getConfig().getSubtitleSeparator();
        SpannableStringBuilder builder = new SpannableStringBuilder();
        if (translationLineFirst) {
            if (separator.length() > 0) {
                builder.append((CharSequence) separator).append(' ');
            }
        }
        builder.append((CharSequence) firstLine);
        int firstEnd = builder.length();
        builder.append('\n');
        int secondStart = builder.length();
        if (!translationLineFirst) {
            if (separator.length() > 0) {
                builder.append((CharSequence) separator).append(' ');
            }
        }
        builder.append((CharSequence) secondLine);
        int secondEnd = builder.length();
        if (translationLineFirst) {
            valueOf = 0;
            valueOf2 = Integer.valueOf(firstEnd);
        } else {
            valueOf = Integer.valueOf(secondStart);
            valueOf2 = Integer.valueOf(secondEnd);
        }
        Pair pair = TuplesKt.to(valueOf, valueOf2);
        int styledStart = ((Number) pair.component1()).intValue();
        int styledEnd = ((Number) pair.component2()).intValue();
        float scale = RangesKt.coerceIn(ModuleEntryKt.getModule().getConfig().getSubtitleTranslationScale(), 0.5f, 1.0f);
        if (scale < 1.0f) {
            builder.setSpan(new RelativeSizeSpan(scale), styledStart, styledEnd, 33);
        }
        builder.setSpan(new ForegroundColorSpan(translationColor(ModuleEntryKt.getModule().getConfig().getSubtitleTranslationColor())), styledStart, styledEnd, 33);
        return centerAligned(builder);
    }

    private final CharSequence centerAligned(CharSequence text) {
        if (text instanceof SpannableStringBuilder) {
            ((SpannableStringBuilder) text).setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER), 0, ((SpannableStringBuilder) text).length(), 51);
            return text;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
        spannableStringBuilder.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER), 0, spannableStringBuilder.length(), 51);
        return spannableStringBuilder;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterative(DepthRegionTraversal.java:31)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visit(SwitchOverStringVisitor.java:60)
     */
    private final int translationColor(String preset) {
        switch (preset.hashCode()) {
            case 99464:
                if (!preset.equals("dim")) {
                }
                break;
            case 97193300:
                if (!preset.equals("faint")) {
                }
                break;
            case 113101865:
                if (!preset.equals("white")) {
                }
                break;
        }
        return -1275068417;
    }
}
