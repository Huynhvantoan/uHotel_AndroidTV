-dontobfuscate
-dontoptimize
-ignorewarnings
-keepattributes SourceFile,LineNumberTable,Exceptions
-keepnames class * extends java.lang.Throwable


-keepclassmembers class fqcn.of.javascript.interface.for.webview {
   public *;
}

# ----------------------------------------
# Retrolambda
# ----------------------------------------
-dontwarn java.lang.invoke.*


# ----------------------------------------
# Parceler rules
# ----------------------------------------
-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}
-keep class org.parceler.Parceler$$Parcels


# ----------------------------------------
# Realm
# ----------------------------------------
-keepnames public class * extends io.realm.RealmObject
-keep class io.realm.annotations.RealmModule
-keep @io.realm.annotations.RealmModule class *
-keep class io.realm.internal.Keep
-keep @io.realm.internal.Keep class *
-dontwarn io.realm.**

# ----------------------------------------
# Glide
# ----------------------------------------
-dontwarn jp.co.cyberagent.android.gpuimage.**
-keep class com.kct.box.data.glidemodule.OkHttpGlideModule
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
}


# ----------------------------------------
# GSON
# ----------------------------------------
-keepattributes Signature
-dontwarn org.androidannotations.api.rest.**
-keepattributes *Annotation*
-keepattributes EnclosingMethod
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
-keep class com.google.gson.FieldNamingStrategy { *; }
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer


# ----------------------------------------
# MINA
# ----------------------------------------
-dontobfuscate
-keepnames class !android.support.v7.internal.view.menu.**,android.support.v7.** {*;}
-dontwarn org.spongycastle.**
-dontwarn org.apache.sshd.**
-dontwarn org.apache.mina.**
-dontwarn org.slf4j.**
-dontwarn io.netty.**
-keepattributes SourceFile,LineNumberTable,Signature,*Annotation*
-keep class org.spongycastle.** {*;}
-keep class org.apache.mina.** {*;}
-keep class org.kde.kdeconnect.** {*;}
-keep class org.apache.http.**

-keep interface org.apache.http.**

-keep class org.slf4j.** { *; }
-keep public class * extends org.apache.mina.*
-keepclassmembers class * implements org.apache.mina.core.service.IoProcessor {
    public <init>(java.util.concurrent.ExecutorService);
    public <init>(java.util.concurrent.Executor);
    public <init>();
}
-dontwarn org.apache.**
-dontwarn org.slf4j.**

# ----------------------------------------
# Libs
# ----------------------------------------
-keep class org.farng.mp3.** { *;}
-dontwarn org.farng.mp3.**
-keep class com.un4seen.bass.** { *;}
-dontwarn com.un4seen.bass.**
-keep class com.daimajia.slider.library.** { *;}
-dontwarn com.daimajia.slider.library.**
-keep class org.farng.mp3.** { *; }
-keep class be.tarsos.dsp.** { *; }
-keep class org.alljoyn.bus.** { *; }
-dontwarn org.alljoyn.bus.**
-keep class net.glxn.qrgen.android.** { *;}
-dontwarn net.glxn.qrgen.android.**
-keep class com.google.android.exoplayer2.** { *; }
-keep class jp.wasabeef.glide.transformations.** { *; }
-keep class com.kct.box.lib.** { *; }
-dontwarn jp.wasabeef.glide.transformations.**
-keep class com.kctbox.socket.** { *;}
-dontwarn com.kctbox.socket.**
-keep class com.kctbox.service.** { *;}
-dontwarn com.kctbox.service.**
-keep class com.kct.box.mvp.model.** { *; }
-keep class com.kctbox.socket.model.** { *; }
-keep class okio.** { *; }
-keep class com.kctbox.dto.** { *; }
-keep class com.google.gson.**{ *; }
-keep class com.github.moduth.blockcanary.**{ *; }
-dontwarn com.github.moduth.blockcanary.**
-keep class com.google.zxing.**{ *; }
-keep class okio.**{ *; }
-dontwarn okio.**
-keep class rx.**{ *; }
-dontwarn rx.**
-keep class net.ossrs.yasea.**{*;}
-keep class net.ossrs.yasea.SrsEncoder{*;}
#Fresco
-keep,allowobfuscation @interface com.facebook.common.internal.DoNotStrip

# Do not strip any method/class that is annotated with @DoNotStrip
-keep @com.facebook.common.internal.DoNotStrip class *
-keepclassmembers class * {
    @com.facebook.common.internal.DoNotStrip *;
}

# Keep native methods
-keepclassmembers class * {
    native <methods>;
}

-dontwarn okio.**
-dontwarn com.squareup.okhttp.**
-dontwarn okhttp3.**
-dontwarn javax.annotation.**
-dontwarn com.android.volley.toolbox.**

# Works around a bug in the animated GIF module which will be fixed in 0.12.0
-keep class com.facebook.imagepipeline.animated.factory.AnimatedFactoryImpl {
    public AnimatedFactoryImpl(com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory,com.facebook.imagepipeline.core.ExecutorSupplier);
}
#Stetho
-keep class com.facebook.stetho.** { *; }
-keep class com.uphyca.** { *; }
-dontwarn com.facebook.stetho.**

#ReactiveNetwork
-dontwarn com.github.pwittchen.reactivenetwork.library.ReactiveNetwork
-dontwarn io.reactivex.functions.Function
-dontwarn rx.internal.util.**
-dontwarn sun.misc.Unsafe