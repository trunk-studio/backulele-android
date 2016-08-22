mkdir:
	adb shell mkdir /storage/sdcard0/ukulele/

push:
	adb push ukulele /storage/sdcard0/ukulele/
	# find . -name '*.mp3' -exec adb push {} /storage/sdcard0/ukulele/ \;
	# for i in *.wav; do ffmpeg -i $i -acodec libmp3lame $(basename "${i/.wav}").mp3; done
