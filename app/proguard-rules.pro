-dontwarn io.github.libxposed.annotation.**
-adaptresourcefilecontents META-INF/xposed/java_init.list
-keep,allowoptimization,allowobfuscation public class * extends io.github.libxposed.api.XposedModule {
    public <init>();
}
-assumenosideeffects class icu.nullptr.polyglot.util.LoggerKt {
    public static void logV(...);
    public static void logD(...);
}
