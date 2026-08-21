package icu.nullptr.polyglot.translate.providers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import icu.nullptr.polyglot.ModuleEntryKt;
import icu.nullptr.polyglot.captions.CaptionSession;
import icu.nullptr.polyglot.translate.TranslationRequest;
import icu.nullptr.polyglot.translate.TranslationResult;
import icu.nullptr.polyglot.translate.Translator;
import icu.nullptr.polyglot.util.LoggerKt;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.io.ConstantsKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* compiled from: OpenAICompatibleTranslator.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J$\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00050\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00050\u00172\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0018\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J$\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00050\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00050\u00172\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0018\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u001e\u0010\u001d\u001a\u00020\u00052\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00050\u00172\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0010\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020\u0005H\u0002J\u001e\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00050\u00172\u0006\u0010\u001f\u001a\u00020\u00052\u0006\u0010!\u001a\u00020\fH\u0002J\f\u0010\"\u001a\u00020\u0005*\u00020#H\u0002J,\u0010$\u001a\u0002H%\"\u0004\b\u0000\u0010%*\u00020#2\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u0002H%0'H\u0082\b¢\u0006\u0002\u0010(R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n \u000f*\u0004\u0018\u00010\u000e0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Licu/nullptr/polyglot/translate/providers/OpenAICompatibleTranslator;", "Licu/nullptr/polyglot/translate/Translator;", "<init>", "()V", "DEFAULT_ENDPOINT", "", "DEFAULT_MODEL", "THINK_BLOCK", "Lkotlin/text/Regex;", "getTHINK_BLOCK", "()Lkotlin/text/Regex;", "FALLBACK_CONCURRENCY", "", "FALLBACK_EXECUTOR", "Ljava/util/concurrent/ExecutorService;", "kotlin.jvm.PlatformType", "FALLBACK_THREAD_COUNTER", "Ljava/util/concurrent/atomic/AtomicInteger;", "translate", "Licu/nullptr/polyglot/translate/TranslationResult;", "request", "Licu/nullptr/polyglot/translate/TranslationRequest;", "translateFallback", "", "texts", "translateOne", "text", "translateBatch", "buildRequestBody", "buildBatchRequestBody", "parseTranslation", "body", "parseNumberedTranslations", "expected", "readBodyOrThrow", "Ljava/net/HttpURLConnection;", "use", "T", "block", "Lkotlin/Function1;", "(Ljava/net/HttpURLConnection;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "TAG", "NUMBERED_LINE_PREFIX", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class OpenAICompatibleTranslator implements Translator {
    public static final String DEFAULT_ENDPOINT = "https://api.openai.com/v1/chat/completions";
    public static final String DEFAULT_MODEL = "gpt-4o-mini";
    private static final int FALLBACK_CONCURRENCY = 4;
    private static final String TAG = "OpenAICompatibleTranslator";
    public static final OpenAICompatibleTranslator INSTANCE = new OpenAICompatibleTranslator();
    private static final Regex THINK_BLOCK = new Regex("^<think>[\\s\\S]*?</think>");
    private static final ExecutorService FALLBACK_EXECUTOR = Executors.newFixedThreadPool(4, new ThreadFactory() { // from class: icu.nullptr.polyglot.translate.providers.OpenAICompatibleTranslator$$ExternalSyntheticLambda0
        @Override // java.util.concurrent.ThreadFactory
        public final Thread newThread(Runnable runnable) {
            return OpenAICompatibleTranslator.FALLBACK_EXECUTOR$lambda$1(runnable);
        }
    });
    private static final AtomicInteger FALLBACK_THREAD_COUNTER = new AtomicInteger(0);
    private static final Regex NUMBERED_LINE_PREFIX = new Regex("^\\s*(\\d{1,3})[.、．:：]\\s*(.*)$");

    private OpenAICompatibleTranslator() {
    }

    public final Regex getTHINK_BLOCK() {
        return THINK_BLOCK;
    }

    static final Thread FALLBACK_EXECUTOR$lambda$1(Runnable runnable) {
        Thread thread = new Thread(runnable, "PolyglotYT-OpenAI-Fallback-" + FALLBACK_THREAD_COUNTER.incrementAndGet());
        thread.setDaemon(true);
        return thread;
    }

    @Override // icu.nullptr.polyglot.translate.Translator
    public TranslationResult translate(TranslationRequest request) {
        TranslationResult translationResult;
        Intrinsics.checkNotNullParameter(request, "request");
        List texts = request.getTexts();
        if (texts.size() <= 1) {
            List<String> list = texts;
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            for (String str : list) {
                if (!StringsKt.isBlank(str)) {
                    str = INSTANCE.translateOne(str, request);
                }
                arrayList.add(str);
            }
            return new TranslationResult((List) arrayList);
        }
        try {
            List translated = translateBatch(texts, request);
            if (translated.size() == texts.size()) {
                translationResult = new TranslationResult(translated);
            } else {
                LoggerKt.logW$default(TAG, "OpenAI batch returned " + translated.size() + "/" + texts.size() + " lines, retrying per-line", null, 4, null);
                translationResult = new TranslationResult(translateFallback(texts, request));
            }
            return translationResult;
        } catch (Exception e) {
            LoggerKt.logW(TAG, "OpenAI batch translation failed, retrying per-line", e);
            return new TranslationResult(translateFallback(texts, request));
        }
    }

    private final List<String> translateFallback(List<String> texts, final TranslationRequest request) {
        if (texts.size() <= 1) {
            List<String> list = texts;
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            for (String str : list) {
                if (!StringsKt.isBlank(str)) {
                    str = INSTANCE.translateOne(str, request);
                }
                arrayList.add(str);
            }
            return (List) arrayList;
        }
        try {
            ExecutorService executorService = FALLBACK_EXECUTOR;
            List<String> list2 = texts;
            Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
            for (final String str2 : list2) {
                arrayList2.add(new Callable() { // from class: icu.nullptr.polyglot.translate.providers.OpenAICompatibleTranslator$$ExternalSyntheticLambda1
                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        return OpenAICompatibleTranslator.translateFallback$lambda$5$lambda$4(str2, request);
                    }
                });
            }
            Iterable invokeAll = executorService.invokeAll((List) arrayList2);
            Intrinsics.checkNotNullExpressionValue(invokeAll, "invokeAll(...)");
            Iterable iterable = invokeAll;
            Collection arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            Iterator it = iterable.iterator();
            while (it.hasNext()) {
                arrayList3.add((String) ((Future) it.next()).get());
            }
            return (List) arrayList3;
        } catch (Exception e) {
            LoggerKt.logW(TAG, "OpenAI parallel fallback failed", e);
            List<String> list3 = texts;
            Collection arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list3, 10));
            for (String str3 : list3) {
                if (!StringsKt.isBlank(str3)) {
                    str3 = INSTANCE.translateOne(str3, request);
                }
                arrayList4.add(str3);
            }
            return (List) arrayList4;
        }
    }

    static final String translateFallback$lambda$5$lambda$4(String $text, TranslationRequest $request) {
        if (StringsKt.isBlank($text)) {
            return $text;
        }
        try {
            return INSTANCE.translateOne($text, $request);
        } catch (Throwable th) {
            return "";
        }
    }

    private final String translateOne(String text, TranslationRequest request) {
        String apiKey = ModuleEntryKt.getModule().getConfig().getOpenAiApiKey();
        if (StringsKt.isBlank(apiKey)) {
            throw new IllegalArgumentException("OpenAI-compatible API key is not configured".toString());
        }
        String openAiEndpoint = ModuleEntryKt.getModule().getConfig().getOpenAiEndpoint();
        if (StringsKt.isBlank(openAiEndpoint)) {
            openAiEndpoint = DEFAULT_ENDPOINT;
        }
        URLConnection openConnection = new URL(openAiEndpoint).openConnection();
        Intrinsics.checkNotNull(openConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
        HttpURLConnection connection = (HttpURLConnection) openConnection;
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setConnectTimeout(request.getTimeoutMs());
        connection.setReadTimeout(request.getTimeoutMs());
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + apiKey);
        String body = buildRequestBody(text, request);
        OutputStream outputStream = connection.getOutputStream();
        try {
            byte[] bytes = body.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            outputStream.write(bytes);
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(outputStream, null);
            try {
                return INSTANCE.parseTranslation(INSTANCE.readBodyOrThrow(connection));
            } finally {
                connection.disconnect();
            }
        } finally {
        }
    }

    private final List<String> translateBatch(List<String> texts, TranslationRequest request) {
        String apiKey = ModuleEntryKt.getModule().getConfig().getOpenAiApiKey();
        if (StringsKt.isBlank(apiKey)) {
            throw new IllegalArgumentException("OpenAI-compatible API key is not configured".toString());
        }
        String openAiEndpoint = ModuleEntryKt.getModule().getConfig().getOpenAiEndpoint();
        if (StringsKt.isBlank(openAiEndpoint)) {
            openAiEndpoint = DEFAULT_ENDPOINT;
        }
        URLConnection openConnection = new URL(openAiEndpoint).openConnection();
        Intrinsics.checkNotNull(openConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
        HttpURLConnection connection = (HttpURLConnection) openConnection;
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setConnectTimeout(request.getTimeoutMs());
        connection.setReadTimeout(request.getTimeoutMs());
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + apiKey);
        String body = buildBatchRequestBody(texts, request);
        OutputStream outputStream = connection.getOutputStream();
        try {
            byte[] bytes = body.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            outputStream.write(bytes);
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(outputStream, null);
            try {
                String response = INSTANCE.readBodyOrThrow(connection);
                connection.disconnect();
                return parseNumberedTranslations(response, texts.size());
            } catch (Throwable th) {
                connection.disconnect();
                throw th;
            }
        } finally {
        }
    }

    private final String buildRequestBody(String text, TranslationRequest request) {
        String systemPrompt = ModuleEntryKt.getModule().getConfig().getOpenAiSystemPrompt();
        String userPrompt = StringsKt.replace$default(StringsKt.replace$default(ModuleEntryKt.getModule().getConfig().getOpenAiUserPrompt(), "{{to}}", request.getTargetLanguage(), false, 4, (Object) null), "{{origin}}", text, false, 4, (Object) null);
        JsonArray jsonArray = new JsonArray();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("role", "system");
        jsonObject.addProperty("content", systemPrompt);
        jsonArray.add(jsonObject);
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.addProperty("role", "user");
        jsonObject2.addProperty("content", userPrompt);
        jsonArray.add(jsonObject2);
        JsonObject jsonObject3 = new JsonObject();
        String openAiModel = ModuleEntryKt.getModule().getConfig().getOpenAiModel();
        if (StringsKt.isBlank(openAiModel)) {
            openAiModel = DEFAULT_MODEL;
        }
        jsonObject3.addProperty("model", openAiModel);
        jsonObject3.addProperty("temperature", Double.valueOf(0.3d));
        jsonObject3.addProperty("max_tokens", Integer.valueOf(CaptionSession.MAX_FORMATTED_TEXTS));
        jsonObject3.add("messages", jsonArray);
        String jsonObject4 = jsonObject3.toString();
        Intrinsics.checkNotNullExpressionValue(jsonObject4, "toString(...)");
        return jsonObject4;
    }

    private final String buildBatchRequestBody(List<String> texts, TranslationRequest request) {
        String systemPrompt = ModuleEntryKt.getModule().getConfig().getOpenAiSystemPrompt();
        List<String> list = texts;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        int i = 0;
        for (Object obj : list) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            arrayList.add((i + 1) + ". " + ((String) obj));
            i = i2;
        }
        String numbered = CollectionsKt.joinToString$default((List) arrayList, "\n", null, null, 0, null, null, 62, null);
        StringBuilder sb = new StringBuilder();
        sb.append(StringsKt.replace$default(StringsKt.replace$default(ModuleEntryKt.getModule().getConfig().getOpenAiUserPrompt(), "{{to}}", request.getTargetLanguage(), false, 4, (Object) null), "{{origin}}", numbered, false, 4, (Object) null));
        StringsKt.contains$default((CharSequence) ModuleEntryKt.getModule().getConfig().getOpenAiUserPrompt(), (CharSequence) "{{origin}}", false, 2, (Object) null);
        sb.append("\n\nEach numbered subtitle line above must be translated separately. ");
        sb.append("Reply with ONLY the translations, one per line, each prefixed with its number, ");
        sb.append("e.g. \"1. ...\", \"2. ...\". Never skip or merge lines. If no translation is needed, repeat the original:\n");
        sb.append(numbered);
        String userPrompt = sb.toString();
        JsonArray jsonArray = new JsonArray();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("role", "system");
        jsonObject.addProperty("content", systemPrompt);
        jsonArray.add(jsonObject);
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.addProperty("role", "user");
        jsonObject2.addProperty("content", userPrompt);
        jsonArray.add(jsonObject2);
        JsonObject jsonObject3 = new JsonObject();
        String openAiModel = ModuleEntryKt.getModule().getConfig().getOpenAiModel();
        if (StringsKt.isBlank(openAiModel)) {
            openAiModel = DEFAULT_MODEL;
        }
        jsonObject3.addProperty("model", openAiModel);
        jsonObject3.addProperty("temperature", Double.valueOf(0.3d));
        jsonObject3.addProperty("max_tokens", (Number) 2500);
        jsonObject3.add("messages", jsonArray);
        String jsonObject4 = jsonObject3.toString();
        Intrinsics.checkNotNullExpressionValue(jsonObject4, "toString(...)");
        return jsonObject4;
    }

    private final String parseTranslation(String body) {
        String content = JsonParser.parseString(body).getAsJsonObject().get("choices").getAsJsonArray().get(0).getAsJsonObject().get("message").getAsJsonObject().get("content").getAsString();
        Intrinsics.checkNotNull(content);
        return StringsKt.trim((CharSequence) THINK_BLOCK.replace(content, "")).toString();
    }

    private final List<String> parseNumberedTranslations(String body, int expected) {
        String asString = JsonParser.parseString(body).getAsJsonObject().get("choices").getAsJsonArray().get(0).getAsJsonObject().get("message").getAsJsonObject().get("content").getAsString();
        Intrinsics.checkNotNullExpressionValue(asString, "getAsString(...)");
        String content = StringsKt.trim((CharSequence) THINK_BLOCK.replace(asString, "")).toString();
        ArrayList result = new ArrayList();
        int currentNumber = 0;
        for (String rawLine : StringsKt.lineSequence(content)) {
            String line = StringsKt.trim((CharSequence) rawLine).toString();
            if (!(line.length() == 0)) {
                MatchResult match = Regex.find$default(NUMBERED_LINE_PREFIX, line, 0, 2, null);
                if (match != null) {
                    Integer intOrNull = StringsKt.toIntOrNull(match.getGroupValues().get(1));
                    if (intOrNull != null) {
                        int number = intOrNull.intValue();
                        if (number == currentNumber + 1) {
                            currentNumber = number;
                            result.add(StringsKt.trim((CharSequence) match.getGroupValues().get(2)).toString());
                        }
                    }
                }
                if (!result.isEmpty()) {
                    result.set(result.size() - 1, CollectionsKt.last((List) result) + " " + line);
                }
            }
        }
        if (result.size() == expected) {
            return result;
        }
        throw new IllegalStateException("OpenAI batch returned " + result.size() + " lines, expected " + expected);
    }

    private final String readBodyOrThrow(HttpURLConnection $this$readBodyOrThrow) {
        BufferedReader bufferedReader;
        int responseCode = $this$readBodyOrThrow.getResponseCode();
        boolean z = false;
        if (200 <= responseCode && responseCode < 300) {
            z = true;
        }
        String errorBody = null;
        if (z) {
            InputStream inputStream = $this$readBodyOrThrow.getInputStream();
            Intrinsics.checkNotNullExpressionValue(inputStream, "getInputStream(...)");
            Reader inputStreamReader = new InputStreamReader(inputStream, Charsets.UTF_8);
            bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, ConstantsKt.DEFAULT_BUFFER_SIZE);
            try {
                String readText = TextStreamsKt.readText(bufferedReader);
                CloseableKt.closeFinally(bufferedReader, null);
                return readText;
            } finally {
            }
        } else {
            InputStream errorStream = $this$readBodyOrThrow.getErrorStream();
            if (errorStream != null) {
                Reader inputStreamReader2 = new InputStreamReader(errorStream, Charsets.UTF_8);
                bufferedReader = inputStreamReader2 instanceof BufferedReader ? (BufferedReader) inputStreamReader2 : new BufferedReader(inputStreamReader2, ConstantsKt.DEFAULT_BUFFER_SIZE);
                try {
                    String readText2 = TextStreamsKt.readText(bufferedReader);
                    CloseableKt.closeFinally(bufferedReader, null);
                    errorBody = readText2;
                } finally {
                    try {
                        throw th;
                    } finally {
                    }
                }
            }
            if (errorBody == null) {
                errorBody = "";
            }
            throw new IllegalStateException("OpenAI-compatible translate failed: HTTP " + $this$readBodyOrThrow.getResponseCode() + " " + $this$readBodyOrThrow.getResponseMessage() + " " + errorBody);
        }
    }

    private final <T> T use(HttpURLConnection $this$use, Function1<? super HttpURLConnection, ? extends T> function1) {
        try {
            return function1.invoke($this$use);
        } finally {
            $this$use.disconnect();
        }
    }
}
