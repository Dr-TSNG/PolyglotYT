package icu.nullptr.polyglot.translate.providers;

import android.os.Build;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import icu.nullptr.polyglot.ModuleEntryKt;
import icu.nullptr.polyglot.translate.TranslationRequest;
import icu.nullptr.polyglot.translate.TranslationResult;
import icu.nullptr.polyglot.translate.Translator;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.io.ConstantsKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* compiled from: YouTubeCommentTranslator.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002./B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0018\u0010\u000e\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0018\u0010\u000f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0005H\u0002J\u0018\u0010\u0011\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0005H\u0002J\u0010\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0005H\u0002J\f\u0010\u0014\u001a\u00020\u0005*\u00020\u0015H\u0002J\u0010\u0010\u0016\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0005H\u0002J\u0010\u0010\u0017\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0005H\u0002J,\u0010\u0018\u001a\u0002H\u0019\"\u0004\b\u0000\u0010\u0019*\u00020\u00152\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u0002H\u00190\u001bH\u0082\b¢\u0006\u0002\u0010\u001cJ\"\u0010\u001d\u001a\u00020\u001e2\u0017\u0010\u001a\u001a\u0013\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020 0\u001b¢\u0006\u0002\b!H\u0082\bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020+X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020+X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020+X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Licu/nullptr/polyglot/translate/providers/YouTubeCommentTranslator;", "Licu/nullptr/polyglot/translate/Translator;", "<init>", "()V", "ENDPOINT", "", "TRANSLATE_ACTION_TYPE", "", "translate", "Licu/nullptr/polyglot/translate/TranslationResult;", "request", "Licu/nullptr/polyglot/translate/TranslationRequest;", "translateOneWithFallback", "text", "translateOne", "buildRequestBody", "targetLanguage", "encodeTranslateAction", "parseTranslation", "body", "readBodyOrThrow", "Ljava/net/HttpURLConnection;", "sanitizeForCommentTranslation", "normalizeRejectedPunctuation", "use", "T", "block", "Lkotlin/Function1;", "(Ljava/net/HttpURLConnection;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "protobuf", "", "Licu/nullptr/polyglot/translate/providers/YouTubeCommentTranslator$ProtoWriter;", "", "Lkotlin/ExtensionFunctionType;", "ANDROID_CLIENT_ID", "TAG", "PLACEHOLDER_ID", "WIRE_TYPE_VARINT", "WIRE_TYPE_LENGTH_DELIMITED", "ERROR_BODY_PREVIEW_LENGTH", "INVALID_ARGUMENT_MESSAGE", "RETRY_MARKER_PREFIX", "UNSUPPORTED_INPUT_CHARACTERS", "Lkotlin/text/Regex;", "WHITESPACE", "RETRY_MARKER", "ProtoWriter", "InvalidArgumentException", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class YouTubeCommentTranslator implements Translator {
    private static final String ANDROID_CLIENT_ID = "3";
    public static final String ENDPOINT = "https://youtubei.googleapis.com/youtubei/v1/comment/perform_comment_action?prettyPrint=false";
    private static final int ERROR_BODY_PREVIEW_LENGTH = 300;
    private static final String INVALID_ARGUMENT_MESSAGE = "Request contains an invalid argument";
    private static final String PLACEHOLDER_ID = " ";
    private static final String RETRY_MARKER_PREFIX = "[0] ";
    private static final String TAG = "YouTubeCommentTranslator";
    public static final int TRANSLATE_ACTION_TYPE = 22;
    private static final int WIRE_TYPE_LENGTH_DELIMITED = 2;
    private static final int WIRE_TYPE_VARINT = 0;
    public static final YouTubeCommentTranslator INSTANCE = new YouTubeCommentTranslator();
    private static final Regex UNSUPPORTED_INPUT_CHARACTERS = new Regex("[^\\p{L}\\p{N}\\p{P}\\p{Z}]");
    private static final Regex WHITESPACE = new Regex("\\s+");
    private static final Regex RETRY_MARKER = new Regex("[\\[［【]\\s*0\\s*[\\]］】]");

    private YouTubeCommentTranslator() {
    }

    @Override // icu.nullptr.polyglot.translate.Translator
    public TranslationResult translate(TranslationRequest request) {
        Intrinsics.checkNotNullParameter(request, "request");
        Iterable<String> texts = request.getTexts();
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(texts, 10));
        for (String str : texts) {
            if (!StringsKt.isBlank(str)) {
                str = INSTANCE.translateOneWithFallback(str, request);
            }
            arrayList.add(str);
        }
        return new TranslationResult((List) arrayList);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0073 A[Catch: InvalidArgumentException -> 0x00b3, TryCatch #3 {InvalidArgumentException -> 0x00b3, blocks: (B:17:0x004f, B:19:0x0073, B:21:0x00ab, B:22:0x00b2), top: B:16:0x004f }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00ab A[Catch: InvalidArgumentException -> 0x00b3, TryCatch #3 {InvalidArgumentException -> 0x00b3, blocks: (B:17:0x004f, B:19:0x0073, B:21:0x00ab, B:22:0x00b2), top: B:16:0x004f }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.lang.String translateOneWithFallback(java.lang.String r17, icu.nullptr.polyglot.translate.TranslationRequest r18) {
        /*
            Method dump skipped, instructions count: 271
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: icu.nullptr.polyglot.translate.providers.YouTubeCommentTranslator.translateOneWithFallback(java.lang.String, icu.nullptr.polyglot.translate.TranslationRequest):java.lang.String");
    }

    private final String translateOne(String text, TranslationRequest request) {
        String clientVersion = ModuleEntryKt.getModule().getHostVersionName();
        URLConnection openConnection = new URL(ENDPOINT).openConnection();
        Intrinsics.checkNotNull(openConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
        HttpURLConnection connection = (HttpURLConnection) openConnection;
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setConnectTimeout(request.getTimeoutMs());
        connection.setReadTimeout(request.getTimeoutMs());
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("X-YouTube-Client-Name", ANDROID_CLIENT_ID);
        connection.setRequestProperty("X-YouTube-Client-Version", clientVersion);
        connection.setRequestProperty("User-Agent", "com.google.android.youtube/" + clientVersion + " (Linux; U; Android " + Build.VERSION.RELEASE + ") gzip");
        OutputStream outputStream = connection.getOutputStream();
        try {
            byte[] bytes = INSTANCE.buildRequestBody(text, request.getTargetLanguage()).getBytes(Charsets.UTF_8);
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

    private final String buildRequestBody(String text, String targetLanguage) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("clientName", "ANDROID");
        jsonObject.addProperty("clientVersion", ModuleEntryKt.getModule().getHostVersionName());
        jsonObject.addProperty("androidSdkVersion", Integer.valueOf(Build.VERSION.SDK_INT));
        jsonObject.addProperty("hl", Locale.getDefault().toLanguageTag());
        JsonObject jsonObject2 = new JsonObject();
        JsonObject jsonObject3 = new JsonObject();
        jsonObject3.add("client", jsonObject);
        Unit unit = Unit.INSTANCE;
        jsonObject2.add("context", jsonObject3);
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(INSTANCE.encodeTranslateAction(text, targetLanguage));
        Unit unit2 = Unit.INSTANCE;
        jsonObject2.add("actions", jsonArray);
        String jsonObject4 = jsonObject2.toString();
        Intrinsics.checkNotNullExpressionValue(jsonObject4, "toString(...)");
        return jsonObject4;
    }

    private final String encodeTranslateAction(String text, String targetLanguage) {
        ProtoWriter protoWriter = new ProtoWriter();
        protoWriter.string(1, text);
        byte[] comment = protoWriter.toByteArray();
        ProtoWriter protoWriter2 = new ProtoWriter();
        protoWriter2.message(1, comment);
        byte[] params = protoWriter2.toByteArray();
        ProtoWriter protoWriter3 = new ProtoWriter();
        protoWriter3.string(2, PLACEHOLDER_ID);
        protoWriter3.message(3, params);
        protoWriter3.string(4, targetLanguage);
        byte[] translateParams = protoWriter3.toByteArray();
        ProtoWriter protoWriter4 = new ProtoWriter();
        protoWriter4.int32(1, 22);
        protoWriter4.int32(2, 2);
        protoWriter4.string(3, PLACEHOLDER_ID);
        protoWriter4.string(5, PLACEHOLDER_ID);
        protoWriter4.string(23, PLACEHOLDER_ID);
        protoWriter4.message(31, translateParams);
        byte[] action = protoWriter4.toByteArray();
        String encode = URLEncoder.encode(Base64.getEncoder().encodeToString(action), Charsets.UTF_8.name());
        Intrinsics.checkNotNullExpressionValue(encode, "encode(...)");
        return encode;
    }

    private final String parseTranslation(String body) {
        JsonObject asJsonObject;
        JsonElement jsonElement;
        JsonObject asJsonObject2;
        JsonElement jsonElement2;
        JsonArray mutations;
        JsonObject asJsonObject3;
        JsonElement jsonElement3;
        JsonObject asJsonObject4;
        JsonElement jsonElement4;
        JsonObject asJsonObject5;
        JsonElement jsonElement5;
        JsonElement jsonElement6 = JsonParser.parseString(body).getAsJsonObject().get("frameworkUpdates");
        if (jsonElement6 == null || (asJsonObject = jsonElement6.getAsJsonObject()) == null || (jsonElement = asJsonObject.get("entityBatchUpdate")) == null || (asJsonObject2 = jsonElement.getAsJsonObject()) == null || (jsonElement2 = asJsonObject2.get("mutations")) == null || (mutations = jsonElement2.getAsJsonArray()) == null) {
            throw new IllegalStateException("YouTube comment translation response contained no mutations");
        }
        Iterator<JsonElement> it = mutations.iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        while (it.hasNext()) {
            JsonElement mutation = it.next();
            JsonElement jsonElement7 = mutation.getAsJsonObject().get("payload");
            String content = null;
            if (jsonElement7 != null && (asJsonObject3 = jsonElement7.getAsJsonObject()) != null && (jsonElement3 = asJsonObject3.get("commentEntityPayload")) != null && (asJsonObject4 = jsonElement3.getAsJsonObject()) != null && (jsonElement4 = asJsonObject4.get("translatedContent")) != null && (asJsonObject5 = jsonElement4.getAsJsonObject()) != null && (jsonElement5 = asJsonObject5.get("content")) != null) {
                if (jsonElement5.isJsonNull()) {
                    jsonElement5 = null;
                }
                if (jsonElement5 != null) {
                    content = jsonElement5.getAsString();
                }
            }
            String str = content;
            if (!(str == null || StringsKt.isBlank(str))) {
                return content;
            }
        }
        throw new IllegalStateException("YouTube comment translation response contained no translated text");
    }

    private final String readBodyOrThrow(HttpURLConnection $this$readBodyOrThrow) {
        BufferedReader bufferedReader;
        int statusCode = $this$readBodyOrThrow.getResponseCode();
        boolean z = false;
        if (200 <= statusCode && statusCode < ERROR_BODY_PREVIEW_LENGTH) {
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
            if (statusCode == 400 && StringsKt.contains((CharSequence) errorBody, (CharSequence) INVALID_ARGUMENT_MESSAGE, true)) {
                throw new InvalidArgumentException("YouTube comment translation returned INVALID_ARGUMENT");
            }
            throw new IllegalStateException("YouTube comment translation failed: HTTP " + statusCode + PLACEHOLDER_ID + $this$readBodyOrThrow.getResponseMessage() + PLACEHOLDER_ID + StringsKt.take(new Regex("\\s+").replace(errorBody, PLACEHOLDER_ID), ERROR_BODY_PREVIEW_LENGTH));
        }
    }

    private final String sanitizeForCommentTranslation(String text) {
        return StringsKt.trim((CharSequence) WHITESPACE.replace(UNSUPPORTED_INPUT_CHARACTERS.replace(text, ""), PLACEHOLDER_ID)).toString();
    }

    private final String normalizeRejectedPunctuation(String text) {
        return new Regex("[–—]").replace(new Regex("[“”]").replace(new Regex("[‘’]").replace(StringsKt.replace$default(text, "…", "...", false, 4, (Object) null), "'"), "\""), "-");
    }

    private final <T> T use(HttpURLConnection $this$use, Function1<? super HttpURLConnection, ? extends T> function1) {
        try {
            return function1.invoke($this$use);
        } finally {
            $this$use.disconnect();
        }
    }

    private final byte[] protobuf(Function1<? super ProtoWriter, Unit> block) {
        ProtoWriter protoWriter = new ProtoWriter();
        block.invoke(protoWriter);
        return protoWriter.toByteArray();
    }

    /* compiled from: YouTubeCommentTranslator.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tJ\u0016\u0010\u000b\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\fJ\u0016\u0010\r\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000eJ\u0006\u0010\u000f\u001a\u00020\u000eJ\u0018\u0010\u0010\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\tH\u0002J\u0010\u0010\u0012\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0013H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Licu/nullptr/polyglot/translate/providers/YouTubeCommentTranslator$ProtoWriter;", "", "<init>", "()V", "output", "Ljava/io/ByteArrayOutputStream;", "int32", "", "fieldNumber", "", "value", "string", "", "message", "", "toByteArray", "tag", "wireType", "varint", "", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class ProtoWriter {
        private final ByteArrayOutputStream output = new ByteArrayOutputStream();

        public final void int32(int fieldNumber, int value) {
            tag(fieldNumber, 0);
            varint(value);
        }

        public final void string(int fieldNumber, String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            byte[] bytes = value.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            message(fieldNumber, bytes);
        }

        public final void message(int fieldNumber, byte[] value) {
            Intrinsics.checkNotNullParameter(value, "value");
            tag(fieldNumber, 2);
            varint(value.length);
            this.output.write(value);
        }

        public final byte[] toByteArray() {
            byte[] byteArray = this.output.toByteArray();
            Intrinsics.checkNotNullExpressionValue(byteArray, "toByteArray(...)");
            return byteArray;
        }

        private final void tag(int fieldNumber, int wireType) {
            varint((fieldNumber << 3) | wireType);
        }

        private final void varint(long value) {
            long remaining = value;
            while (true) {
                long j = (-128) & remaining;
                ByteArrayOutputStream byteArrayOutputStream = this.output;
                if (j != 0) {
                    byteArrayOutputStream.write((int) ((127 & remaining) | 128));
                    remaining >>>= 7;
                } else {
                    byteArrayOutputStream.write((int) remaining);
                    return;
                }
            }
        }
    }

    /* compiled from: YouTubeCommentTranslator.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Licu/nullptr/polyglot/translate/providers/YouTubeCommentTranslator$InvalidArgumentException;", "Ljava/lang/IllegalStateException;", "Lkotlin/IllegalStateException;", "message", "", "<init>", "(Ljava/lang/String;)V", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class InvalidArgumentException extends IllegalStateException {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public InvalidArgumentException(String message) {
            super(message);
            Intrinsics.checkNotNullParameter(message, "message");
        }
    }
}
