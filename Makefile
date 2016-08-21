all:
	./gradlew installDebug
	adb shell am start -n com.trunksys.backulele/com.trunksys.backulele.MainActivity
