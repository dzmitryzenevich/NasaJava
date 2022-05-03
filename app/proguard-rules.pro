
# RxJava
-dontwarn java.util.concurrent.Flow

# Dagger
-keep class com.google.errorprone.annotations.** { *; }
-dontwarn com.google.errorprone.annotations.**

# Models
-dontwarn com.dzenlab.nasajava.presentation.models.**
-keep class com.dzenlab.nasajava.presentation.models.** { *; }

# Other
-keepattributes Signature
-keepattributes Annotation
-keepattributes InnerClasses
-keepattributes EnclosingMethod
-keepattributes RuntimeVisibleAnnotations
-keepattributes RuntimeVisibleParameterAnnotations
-dontwarn io.**
-keep class io.** { *; }
-dontwarn com.google.gson.**
-keep class com.google.gson.** { *; }