@ECHO OFF
"C:\Program Files (x86)\Atmel\AVR Tools\AvrAssembler2\avrasm2.exe" -S "D:\CHNTU\AVR\LABA2\lab2\labels.tmp" -fI -W+ie -C V2E -o "D:\CHNTU\AVR\LABA2\lab2\lab2.hex" -d "D:\CHNTU\AVR\LABA2\lab2\lab2.obj" -e "D:\CHNTU\AVR\LABA2\lab2\lab2.eep" -m "D:\CHNTU\AVR\LABA2\lab2\lab2.map" "D:\CHNTU\AVR\LABA2\lab2\Lab2.asm"
