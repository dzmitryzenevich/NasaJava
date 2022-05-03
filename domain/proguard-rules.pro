
# RxJava
-dontwarn java.util.concurrent.Flow

# Models
-dontwarn com.dzenlab.nasajava.models.**
-keep class com.dzenlab.nasajava.models.** { *; }

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