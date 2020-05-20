# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
### Retrofit
#https://github.com/square/retrofit/blob/master/retrofit/src/main/resources/META-INF/proguard/retrofit2.pro
# Retrofit does reflection on generic parameters. InnerClasses is required to use Signature and
# EnclosingMethod is required to use InnerClasses.
# Retrofit does reflection on generic parameters. InnerClasses is required to use Signature and
# EnclosingMethod is required to use InnerClasses.
#-keepattributes Signature, InnerClasses, EnclosingMethod
#
## Retrofit does reflection on method and parameter annotations.
#-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations
#
## Retain service method parameters when optimizing.
#-keepclassmembers,allowshrinking,allowobfuscation interface * {
#    @retrofit2.http.* <methods>;
#}
#
## Ignore annotation used for build tooling.
#-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
#
## Ignore JSR 305 annotations for embedding nullability information.
#-dontwarn javax.annotation.**
#
## Guarded by a NoClassDefFoundError try/catch and only used when on the classpath.
#-dontwarn kotlin.Unit
#
## Top-level functions that can only be used by Kotlin.
#-dontwarn retrofit2.KotlinExtensions
#-dontwarn retrofit2.KotlinExtensions$*
#
## With R8 full mode, it sees no subtypes of Retrofit interfaces since they are created with a Proxy
## and replaces all potential values with null. Explicitly keeping the interfaces prevents this.
#-if interface * { @retrofit2.http.* <methods>; }
#-keep,allowobfuscation interface <1>

### Dagger
#https://proguard-rules.blogspot.com/2017/05/dagger-2-proguard-rules.html

-keepattributes *Annotation*,EnclosingMethod
-keep @interface dagger.*,javax.inject.*
-keep @dagger.Module class *
-keepclassmembers class * {
    @javax.inject.* *;
    @dagger.* *;
    *();
}
-keepclasseswithmembernames class * {
  @javax.inject.* *;
}
-keep class javax.inject.** { *; }
-keep class **$$ModuleAdapter
-keep class **$$InjectAdapter
-keep class **$$StaticInjection

-keep class dagger.** { *; }
-keep class * extends dagger.** { *; }
-keep interface dagger.** {*;}
-dontwarn dagger.internal.codegen.**

### crashlytics
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable
-keep public class * extends java.lang.Exception

-keep class com.crashlytics.** { *; }
-dontwarn com.crashlytics.**

### Picasso
#-dontwarn com.squareup.okhttp.**
#-dontwarn com.google.errorprone.annotations.*

##GSON
#-keepattributes Signature
#-keepattributes *Annotation*
#-keep class sun.misc.Unsafe { *; }
#-keep class com.google.gson.examples.android.model.** { *; }
#
#-dontwarn com.github.* #moxy-community:moxy:1.0.13'
#-dontwarn com.squareup.* #picasso:picasso:2.5.2'
#-dontwarn com.google.* #code.gson:gson:2.8.5'
#-dontwarn com.google.dagger.* #:dagger:2.23.1'
#-dontwarn **
#-renamesourcefileattribute SourceFile
#-keepattributes SourceFile,LineNumberTable
## RxJava configuration
#-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
#  long producerIndex;
#  long consumerIndex;
#}
#-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
#  rx.internal.util.atomic.LinkedQueueNode producerNode;
#}
#-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
#  rx.internal.util.atomic.LinkedQueueNode consumerNode;
#}