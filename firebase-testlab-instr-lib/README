Firebase Test Lab Instrumentation Library
--------------------------------

This library includes an ScreenShotProcessor (named FirebaseScreenCaptureProcessor) that should be
included in your project and named in the test manifest before running through Firebase Test Lab
(See 'Steps to incorporate into project' below).

This allows all screenshots taken with ATSL ScreenCapture to be processed with this processor
which saves the screenshots to the Firebase Test Lab specific directory on the device. Screenshots
saved in this directory will be pulled displayed in the results of a Fiirebase Test Lab test run.

See:
https://developer.android.com/reference/android/support/test/runner/screenshot/ScreenCapture.html
https://developer.android.com/reference/android/support/test/runner/AndroidJUnitRunner.html

Steps to incorporate into project
---------------------------------
1. Add google() as a repository.
    - google() is only available in Gradle 4+.
    - For prior versions use:
        maven {
            url 'https://maven.google.com'
        }
2. Add androidTestCompile 'com.google.firebase:testlab-instr-lib:0.1' to app build.gradle file.
3. Add FirebaseScreenCaptureProcessor inside the instrumentation element in your tests
AndroidManifest.xml file:
...
 <instrumentation
    android:name="android.support.test.runner.AndroidJUnitRunner"
    android:targetPackage="com.your.package.name">
    <meta-data
        android:name="screenCaptureProcessors"
        android:value="com.google.firebase.testlab.screenshot.FirebaseScreenCaptureProcessor" />
</instrumentation>
...
(See: https://developer.android.com/guide/topics/manifest/instrumentation-element.html for
explaination of instrumentation tag)
4. This library requires at least version 1.0.0 of AndroidJUnitRunner. Please ensure that your
test infrastructure is prepared to work with ATSL 1.0.
(See: https://android-developers.googleblog.com/2017/07/android-testing-support-library-10-is.html)
