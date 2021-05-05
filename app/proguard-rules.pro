# Rules for Toothpick (get from https://github.com/stephanenicolas/toothpick/blob/master/smoothie/proguard-rules.txt)
-keepattributes *Annotation*
-keepclasseswithmembernames class * { @javax.inject.Inject <init>(...); }
-keepnames @toothpick.InjectConstructor class *
-keepclasseswithmembernames class * { @javax.inject.Inject <fields>; }
-keepclasseswithmembernames class * { @javax.inject.Inject <methods>; }
-keepclasseswithmembernames class * { toothpick.ktp.delegate.* *; }
-keep class javax.inject.**
-keep class javax.annotation.**
-keep class **__Factory { *; }
-keep class **__MemberInjector { *; }
-keepclassmembers class * {
	@javax.inject.Inject <init>(...);
	@javax.inject.Inject <init>();
	@javax.inject.Inject <fields>;
	public <init>(...);
    toothpick.ktp.delegate.* *;
}
-keep class toothpick.** { *; }
-keep @javax.inject.Singleton class *

# Additional proguard rules
# TODO [class-loader-dex-problems] You need to keep names of 'Experiment' classes
#   to have the ability for collecting a list of all experiments
#   across the codebase in minified builds.
-keepnames class * implements ru.hh.android.core.experiments.models.Experiment
