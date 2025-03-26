# --- [ GERAL ] --- #
# Otimiza o código, remove classes e métodos não utilizados
-dontoptimize
-dontpreverify
-dontobfuscate

# Mantém nomes de classes e métodos usados por reflexão
-keepattributes *Annotation*

# Mantém todas as classes dentro do package da aplicação (evita ofuscação completa)
-keep public class com.guilhermereisapps.rickandmortykotlin.** { *; }

# --- [ RETROFIT + GSON ] --- #
# Evita que o ProGuard remova classes model usadas na serialização/deserialização
-keep class com.google.gson.** { *; }
-keep class retrofit2.** { *; }
-keep class okhttp3.** { *; }

# Mantém classes de modelos JSON (entidades do Retrofit/Gson)
-keepclassmembers class * {
    @com.google.gson.annotations.SerializedName <fields>;
}

# --- [ ROOM DATABASE ] --- #
# Evita que o ProGuard remova entidades e DAOs do Room
-keep class androidx.room.** { *; }
-keep interface androidx.room.** { *; }

# Garante que as anotações @Entity, @Dao e @Database sejam preservadas
-keepattributes *Annotation*

# Mantém todos os métodos DAO do Room
-keepclassmembers class * {
    @androidx.room.* <methods>;
}

# --- [ COROUTINES + FLOW ] --- #
# Evita que ProGuard remova ou renomeie classes do Coroutines e Flow
-keep class kotlinx.coroutines.** { *; }

# --- [ TESTES E LOGS ] --- #
# Remove logs de Debug (para não expor informações sensíveis em builds de produção)
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
}

# --- [ REGRAS PADRÃO ] --- #
# Carrega as regras padrão do ProGuard (geralmente já presentes no projeto)
-include proguard-android-optimize.txt
