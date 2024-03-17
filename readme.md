Comandos para buildar e instalar o aplicativo no dispositivo

```bash
SDK="${HOME}/android-sdk-linux"
BUILD_TOOLS="${SDK}/build-tools/25.0.0"
PLATFORM="${SDK}/platforms/android-16"
mkdir -p build/gen build/obj build/apk

"${BUILD_TOOLS}/aapt" package -f -m -J build/gen/ -S res \
-M AndroidManifest.xml -I "${PLATFORM}/android.jar"

javac -source 1.7 -target 1.7 -bootclasspath "${JAVA_HOME}/jre/lib/rt.jar" \
-classpath "${PLATFORM}/android.jar" -d build/obj \
build/gen/advinhaNome/main/R.java advinhaNome/main/AdvinhaNome.java

 "${BUILD_TOOLS}/dx" --dex --output=build/apk/classes.dex build/obj/  
        
 "${BUILD_TOOLS}/aapt" package -f -M AndroidManifest.xml -S res/ \
-I "${PLATFORM}/android.jar" \
-F build/Hello.unsigned.apk build/apk/

"${BUILD_TOOLS}/zipalign" -f -p 4 \
build/Hello.unsigned.apk build/Hello.aligned.apk

"${BUILD_TOOLS}/apksigner" sign --ks keystore.jks \
--ks-key-alias androidkey --ks-pass pass:android \
--key-pass pass:android --out build/Hello.apk \
build/Hello.aligned.apk

 "${SDK}/platform-tools/adb" install -r build/Hello.apk
```