package icu.nullptr.polyglot.translate.providers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import icu.nullptr.polyglot.ModuleEntryKt;
import icu.nullptr.polyglot.translate.TranslationRequest;
import icu.nullptr.polyglot.translate.TranslationResult;
import icu.nullptr.polyglot.translate.Translator;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.io.ConstantsKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlin.text.Typography;

/* compiled from: MicrosoftTranslator.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0016\u0010\r\u001a\u00020\u00052\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\u000fH\u0002J\u0016\u0010\u0010\u001a\u00020\u00052\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\u000fH\u0002J\u0016\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u000f2\u0006\u0010\u0012\u001a\u00020\u0005H\u0002J,\u0010\u0013\u001a\u00020\b2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00050\u000f2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00050\u000f2\u0006\u0010\u0016\u001a\u00020\u0005H\u0002J\u0014\u0010\u0017\u001a\u00020\u0005*\u00020\u00182\u0006\u0010\u0016\u001a\u00020\u0005H\u0002J,\u0010\u0019\u001a\u0002H\u001a\"\u0004\b\u0000\u0010\u001a*\u00020\u00182\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u0002H\u001a0\u001cH\u0082\b¢\u0006\u0002\u0010\u001dJ\u0010\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020\u0005H\u0002J\u0010\u0010 \u001a\u00020\u00052\u0006\u0010!\u001a\u00020\u0005H\u0002J\u0010\u0010\"\u001a\u00020\u00052\u0006\u0010#\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Licu/nullptr/polyglot/translate/providers/MicrosoftTranslator;", "Licu/nullptr/polyglot/translate/Translator;", "<init>", "()V", "EDGE_ENDPOINT", "", "DEFAULT_AZURE_ENDPOINT", "translate", "Licu/nullptr/polyglot/translate/TranslationResult;", "request", "Licu/nullptr/polyglot/translate/TranslationRequest;", "translateWithEdge", "translateWithAzure", "buildEdgeRequestBody", "texts", "", "buildAzureRequestBody", "parseTranslations", "body", "mergeTranslations", "originals", "translations", "label", "readBodyOrThrow", "Ljava/net/HttpURLConnection;", "use", "T", "block", "Lkotlin/Function1;", "(Ljava/net/HttpURLConnection;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "microsoftLanguage", "language", "azureTranslateEndpoint", "configuredEndpoint", "urlEncode", "value", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class MicrosoftTranslator implements Translator {
    public static final String DEFAULT_AZURE_ENDPOINT = "https://api.cognitive.microsofttranslator.com/translate";
    public static final String EDGE_ENDPOINT = "https://edge.microsoft.com/translate/translatetext";
    public static final MicrosoftTranslator INSTANCE = new MicrosoftTranslator();

    private MicrosoftTranslator() {
    }

    @Override // icu.nullptr.polyglot.translate.Translator
    public TranslationResult translate(TranslationRequest request) {
        Intrinsics.checkNotNullParameter(request, "request");
        try {
            return translateWithEdge(request);
        } catch (Exception edgeError) {
            if (StringsKt.isBlank(ModuleEntryKt.getModule().getConfig().getMicrosoftApiKey())) {
                throw new IllegalStateException("Microsoft Edge translate failed", edgeError);
            }
            try {
                return translateWithAzure(request);
            } catch (Exception azureError) {
                ExceptionsKt.addSuppressed(azureError, edgeError);
                throw azureError;
            }
        }
    }

    private final TranslationResult translateWithEdge(TranslationRequest request) {
        Iterable texts = request.getTexts();
        Collection arrayList = new ArrayList();
        for (Object obj : texts) {
            if (!StringsKt.isBlank((String) obj)) {
                arrayList.add(obj);
            }
        }
        List nonBlankTexts = (List) arrayList;
        if (nonBlankTexts.isEmpty()) {
            return new TranslationResult(request.getTexts());
        }
        StringBuilder sb = new StringBuilder();
        sb.append("from=").append(INSTANCE.urlEncode(INSTANCE.microsoftLanguage(request.getSourceLanguage())));
        sb.append("&to=").append(INSTANCE.urlEncode(INSTANCE.microsoftLanguage(request.getTargetLanguage())));
        sb.append("&isEnterpriseClient=false");
        String query = sb.toString();
        URLConnection openConnection = new URL("https://edge.microsoft.com/translate/translatetext?" + query).openConnection();
        Intrinsics.checkNotNull(openConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
        HttpURLConnection connection = (HttpURLConnection) openConnection;
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setConnectTimeout(request.getTimeoutMs());
        connection.setReadTimeout(request.getTimeoutMs());
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Content-Type", "application/json");
        OutputStream outputStream = connection.getOutputStream();
        try {
            byte[] bytes = INSTANCE.buildEdgeRequestBody(nonBlankTexts).getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            outputStream.write(bytes);
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(outputStream, null);
            try {
                List translations = INSTANCE.parseTranslations(INSTANCE.readBodyOrThrow(connection, "Microsoft Edge translate"));
                connection.disconnect();
                return mergeTranslations(request.getTexts(), translations, "Microsoft Edge translate");
            } catch (Throwable th) {
                connection.disconnect();
                throw th;
            }
        } finally {
        }
    }

    private final TranslationResult translateWithAzure(TranslationRequest request) {
        Iterable texts = request.getTexts();
        Collection arrayList = new ArrayList();
        for (Object obj : texts) {
            if (!StringsKt.isBlank((String) obj)) {
                arrayList.add(obj);
            }
        }
        List nonBlankTexts = (List) arrayList;
        if (!nonBlankTexts.isEmpty()) {
            String sourceLanguage = microsoftLanguage(request.getSourceLanguage());
            String targetLanguage = microsoftLanguage(request.getTargetLanguage());
            StringBuilder sb = new StringBuilder();
            sb.append("api-version=3.0");
            if (sourceLanguage.length() > 0) {
                sb.append("&from=").append(INSTANCE.urlEncode(sourceLanguage));
            }
            sb.append("&to=").append(INSTANCE.urlEncode(targetLanguage));
            sb.append("&textType=plain");
            String query = sb.toString();
            String endpoint = azureTranslateEndpoint(ModuleEntryKt.getModule().getConfig().getMicrosoftEndpoint());
            char separator = StringsKt.contains$default((CharSequence) endpoint, '?', false, 2, (Object) null) ? Typography.amp : '?';
            URLConnection openConnection = new URL(endpoint + separator + query).openConnection();
            Intrinsics.checkNotNull(openConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
            HttpURLConnection connection = (HttpURLConnection) openConnection;
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setConnectTimeout(request.getTimeoutMs());
            connection.setReadTimeout(request.getTimeoutMs());
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setRequestProperty("Ocp-Apim-Subscription-Key", ModuleEntryKt.getModule().getConfig().getMicrosoftApiKey());
            String microsoftRegion = ModuleEntryKt.getModule().getConfig().getMicrosoftRegion();
            if (StringsKt.isBlank(microsoftRegion)) {
                microsoftRegion = null;
            }
            if (microsoftRegion != null) {
                connection.setRequestProperty("Ocp-Apim-Subscription-Region", microsoftRegion);
            }
            OutputStream outputStream = connection.getOutputStream();
            try {
                byte[] bytes = INSTANCE.buildAzureRequestBody(nonBlankTexts).getBytes(Charsets.UTF_8);
                Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
                outputStream.write(bytes);
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(outputStream, null);
                try {
                    List translations = INSTANCE.parseTranslations(INSTANCE.readBodyOrThrow(connection, "Microsoft Azure translate"));
                    connection.disconnect();
                    return mergeTranslations(request.getTexts(), translations, "Microsoft Azure translate");
                } catch (Throwable th) {
                    connection.disconnect();
                    throw th;
                }
            } finally {
            }
        } else {
            return new TranslationResult(request.getTexts());
        }
    }

    private final String buildEdgeRequestBody(List<String> texts) {
        JsonArray jsonArray = new JsonArray();
        Iterator it = texts.iterator();
        while (it.hasNext()) {
            jsonArray.add((String) it.next());
        }
        String jsonArray2 = jsonArray.toString();
        Intrinsics.checkNotNullExpressionValue(jsonArray2, "toString(...)");
        return jsonArray2;
    }

    private final String buildAzureRequestBody(List<String> texts) {
        JsonArray jsonArray = new JsonArray();
        for (String str : texts) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("Text", str);
            jsonArray.add(jsonObject);
        }
        String jsonArray2 = jsonArray.toString();
        Intrinsics.checkNotNullExpressionValue(jsonArray2, "toString(...)");
        return jsonArray2;
    }

    private final List<String> parseTranslations(String body) {
        Iterable asJsonArray = JsonParser.parseString(body).getAsJsonArray();
        Intrinsics.checkNotNullExpressionValue(asJsonArray, "getAsJsonArray(...)");
        Iterable iterable = asJsonArray;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        Iterator<JsonElement> it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(((JsonElement) it.next()).getAsJsonObject().get("translations").getAsJsonArray().get(0).getAsJsonObject().get("text").getAsString());
        }
        return (List) arrayList;
    }

    private final TranslationResult mergeTranslations(List<String> originals, List<String> translations, String label) {
        int expectedCount;
        List<String> list = originals;
        if ((list instanceof Collection) && list.isEmpty()) {
            expectedCount = 0;
        } else {
            expectedCount = 0;
            Iterator it = list.iterator();
            while (it.hasNext()) {
                if (!StringsKt.isBlank((String) it.next()) && (expectedCount = expectedCount + 1) < 0) {
                    CollectionsKt.throwCountOverflow();
                }
            }
        }
        if (!(translations.size() == expectedCount)) {
            throw new IllegalStateException((label + " returned " + translations.size() + " results for " + expectedCount + " texts").toString());
        }
        int translatedIndex = 0;
        List<String> list2 = originals;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        for (String str : list2) {
            if (!StringsKt.isBlank(str)) {
                str = translations.get(translatedIndex);
                translatedIndex++;
            }
            arrayList.add(str);
        }
        return new TranslationResult((List) arrayList);
    }

    private final String readBodyOrThrow(HttpURLConnection $this$readBodyOrThrow, String label) {
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
            throw new IllegalStateException(label + " failed: HTTP " + $this$readBodyOrThrow.getResponseCode() + " " + $this$readBodyOrThrow.getResponseMessage() + " " + errorBody);
        }
    }

    private final <T> T use(HttpURLConnection $this$use, Function1<? super HttpURLConnection, ? extends T> function1) {
        try {
            return function1.invoke($this$use);
        } finally {
            $this$use.disconnect();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x0030, code lost:
    
        if (r0.equals("zh-cn") == false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x005d, code lost:
    
        return "zh-Hans";
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0045, code lost:
    
        if (r0.equals("zh") == false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004e, code lost:
    
        if (r0.equals("zh-hant") == false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005a, code lost:
    
        if (r0.equals("zh-hans") == false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x001e, code lost:
    
        if (r0.equals("zh-tw") == false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0051, code lost:
    
        return "zh-Hant";
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0027, code lost:
    
        if (r0.equals("zh-hk") == false) goto L29;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.lang.String microsoftLanguage(java.lang.String r3) {
        /*
            r2 = this;
            java.util.Locale r0 = java.util.Locale.ROOT
            java.lang.String r1 = "ROOT"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            java.lang.String r0 = r3.toLowerCase(r0)
            java.lang.String r1 = "toLowerCase(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            int r1 = r0.hashCode()
            switch(r1) {
                case -371515459: goto L54;
                case -371515458: goto L48;
                case 3886: goto L3f;
                case 3005871: goto L33;
                case 115814250: goto L2a;
                case 115814402: goto L21;
                case 115814786: goto L18;
                default: goto L17;
            }
        L17:
            goto L60
        L18:
            java.lang.String r1 = "zh-tw"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L51
            goto L17
        L21:
            java.lang.String r1 = "zh-hk"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L51
            goto L17
        L2a:
            java.lang.String r1 = "zh-cn"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L5d
            goto L17
        L33:
            java.lang.String r1 = "auto"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L3c
            goto L17
        L3c:
            java.lang.String r0 = ""
            goto L61
        L3f:
            java.lang.String r1 = "zh"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L5d
            goto L17
        L48:
            java.lang.String r1 = "zh-hant"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L51
            goto L17
        L51:
            java.lang.String r0 = "zh-Hant"
            goto L61
        L54:
            java.lang.String r1 = "zh-hans"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L5d
            goto L17
        L5d:
            java.lang.String r0 = "zh-Hans"
            goto L61
        L60:
            r0 = r3
        L61:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: icu.nullptr.polyglot.translate.providers.MicrosoftTranslator.microsoftLanguage(java.lang.String):java.lang.String");
    }

    private final String azureTranslateEndpoint(String configuredEndpoint) {
        String str = configuredEndpoint;
        if (StringsKt.isBlank(str)) {
            str = DEFAULT_AZURE_ENDPOINT;
        }
        String endpoint = StringsKt.trimEnd(str, '/');
        URI uri = new URI(endpoint);
        String path = uri.getPath();
        Intrinsics.checkNotNullExpressionValue(path, "getPath(...)");
        String path2 = StringsKt.trimEnd(path, '/');
        if (StringsKt.endsWith(path2, "/translate", true)) {
            return endpoint;
        }
        String host = uri.getHost();
        if (host == null) {
            host = "";
        }
        if (!StringsKt.endsWith(host, "cognitiveservices.azure.com", true)) {
            return endpoint + "/translate";
        }
        return endpoint + "/translator/text/v3.0/translate";
    }

    private final String urlEncode(String value) {
        String encode = URLEncoder.encode(value, Charsets.UTF_8.name());
        Intrinsics.checkNotNullExpressionValue(encode, "encode(...)");
        return encode;
    }
}
