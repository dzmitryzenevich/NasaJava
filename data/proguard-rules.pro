
# RxJava
-dontwarn java.util.concurrent.Flow

# Dagger
-keep class com.google.errorprone.annotations.** { *; }
-dontwarn com.google.errorprone.annotations.**

# Retrofit2
-keepclassmembers,allowshrinking,allowobfuscation interface * { @retrofit2.http.* <methods>; }
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
-dontwarn javax.annotation.**
-dontwarn kotlin.Unit
-dontwarn retrofit2.KotlinExtensions
-dontnote retrofit2.Platform
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface <1>
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
-dontwarn org.codehaus.mojo.animal_sniffer.**
-dontwarn okhttp3.internal.platform.ConscryptPlatform
-dontwarn org.conscrypt.ConscryptHostnameVerifier
-dontwarn okio.**

# Room
-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class *
-dontwarn androidx.room.paging.**
-dontwarn android.arch.util.paging.CountedDataSource
-dontwarn android.arch.persistence.room.paging.LimitOffsetDataSource

# Models
-dontwarn com.dzenlab.nasajava.data.database.models.**
-keep class com.dzenlab.nasajava.data.database.models.** { *; }
-dontwarn com.dzenlab.nasajava.data.network.models.**
-keep class com.dzenlab.nasajava.data.network.models.** { *; }
-dontwarn com.dzenlab.nasajava.data.sharepref.models.**
-keep class com.dzenlab.nasajava.data.sharepref.models.** { *; }

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