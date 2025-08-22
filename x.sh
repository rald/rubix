#!/bin/bash

rm -rf bin obj gen output game.apk

mkdir bin
mkdir obj
mkdir gen
mkdir output

aapt package -f -m \
	-J gen \
	-M AndroidManifest.xml \
	-S res \
	-I android-30.jar

javac -cp android-30.jar -d obj $(find src -name '*.java')

d8 $(find obj -name '*.class') --lib android-30.jar --output output

aapt package -f -m \
	-J gen \
    -S res \
    -M AndroidManifest.xml \
    -I android-30.jar \
    -F bin/game.apk \
    output

zipalign -v 4 bin/game.apk bin/game-aligned.apk

apksigner sign --ks android.keystore --ks-key-alias android --ks-pass pass:android --key-pass pass:android bin/game-aligned.apk

cp bin/game-aligned.apk game.apk
