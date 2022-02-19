@ECHO OFF
"C:\Program Files (x86)\Atmel\AVR Tools\AvrAssembler2\avrasm2.exe" -S "D:\CHNTU\AVR\RGR\rgr_asm\labels.tmp" -fI -W+ie -C V2E -o "D:\CHNTU\AVR\RGR\rgr_asm\rgr_asm.hex" -d "D:\CHNTU\AVR\RGR\rgr_asm\rgr_asm.obj" -e "D:\CHNTU\AVR\RGR\rgr_asm\rgr_asm.eep" -m "D:\CHNTU\AVR\RGR\rgr_asm\rgr_asm.map" "D:\CHNTU\AVR\RGR\rgr_asm\rgr_asm.asm"
