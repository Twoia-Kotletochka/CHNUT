;*** Програма до розрахунково-графічної роботи (стенд EV8031) *****

.include "m8515def.inc"	;підключення модуля контролера ATmega8515

;*** Призначення символічних імен регістрів ***

.def diode = r16		;регістр, який зберігає стан світлодіодним лінійки
.def temp = r17		;регістр тимчасового зберігання
.def encoder_data = r18	;лічильник імпульсів енкодера

;Регістр, що зберігає попередній стан інкрементального енкодера
.def last_encoder_state = r19
;Регістр, що зберігає наступний стан інкрементального енкодера
.def next_encoder_state = r20

.def attribute = r23	; регистр 

.def count_encoder_forward = r24
.def count_encoder_back = r25
.def gray_data = r15

;*** Призначення констант ***

.EQU led_line = 0xA006; адреса лінійки світлодіодів в стенді

;***** Початок програми *****

.CSEG		;визначаємо початок сегмента коду
.ORG 0x0000	;визначаємо адресу початку сегмента коду в пам'яті програм

;*** Таблиця векторів переривань контролера ***

	rjmp Init; Reset Handler (вектор переривання по скиданню)
	reti; rjmp EXT_INT0; IRQ0 Handler
	reti; rjmp EXT_INT1; IRQ1 Handler
	reti; rjmp TIM1_CAPT; Timer1 Capture Handler
	rjmp TIM1_COMPA; Timer1 Compare A Handler
;тобто, адреса переходу на обробник переривання по співпадінню A таймера/лічильника T1
	reti; rjmp TIM1_COMPB; Timer1 Compare B Handler
	reti; rjmp TIM1_OVF; Timer1 Overflow Handler
	reti; rjmp TIM0_OVF; Timer0 Overflow Handler
	reti; rjmp SPI_STC; SPI Transfer Complete Handler
	reti; rjmp USART_RXC; USART RX Complete Handler
	reti; rjmp USART_UDRE; UDR0 Empty Handler
	reti; rjmp USART_TXC; USART TX Complete Handler
	reti; rjmp ANA_COMP; Analog Comparator Handler
	reti; rjmp EXT_INT2; IRQ2 Handler
	reti; rjmp TIM0_COMP; Timer0 Compare Handler
	reti; rjmp EE_RDY; EEPROM Ready Handler
	reti; rjmp SPM_RDY; Store Program memory Ready

;*** Початкова ініціалізація контролера ***

Init:
	ldi temp, low (RAMEND)
	out SPL, temp
	ldi temp, high (RAMEND)
	out SPH, temp	;установка SP на останню адресу в SRAM
	sbi ACSR, 7		;відключення живлення аналогового компаратора

	ldi temp, 0b10000000	;дозволяємо роботу із зовнішньою пам'яттю
	out MCUCR, temp

	;Встановлюємо вказівник Z на адресу лінійки світлодіодів в стенді
	ldi ZL, low (led_line)
	ldi ZH, high (led_line)

	clr diode	;очищаємо регістр стану світлодіодної лінійки (гасимо всі діоди)
	clr encoder_data	;очищаємо регістр лічби імпульсів, що надійшли з енкодера

	in last_encoder_state, PINB	;читаємо поточний стан каналів енкодера

;Отримуємо стан бітів порту, що відповідають за поточний стан енкодера
	andi last_encoder_state, 0b10010000

;*** Налаштування переривань ***

	ldi temp, (1 << OCIE1A)	;записуємо маску в регістр temp
	out TIMSK, temp		;записуємо в регістр TIMSK (дозволяємо переривання
					;за збігом A таймера/лічильника T1)

;*** Налаштування та пуск таймера/лічильника T1 ***

;Завантажуємо в пару регістрів OCR1AH: OCR1AL число 0x0006, тобто задаємо
;значення, при досягненні якого таймером/лічильником T1 буде
;згенеровано переривання по співпадінню A
	ldi temp, 0x00
	out OCR1AH, temp
	ldi temp, 0x06
	out OCR1AL, temp
	ldi temp, (1 << PSR10)	;скидаємо переддільник, записуючи 1 в біт PSR10,
	out SFIOR, temp		;фактично в цей момент відбувається початок відліку						;(скидання предделителя)

;Завантажуємо в пару регістрів TCCR1A: TCCR1B число 0x000C
	ldi temp, 0b00000000
	out TCCR1A, temp	;задаємо режим роботи CTC і власне запускаємо
	ldi temp, (1 << WGM12) | (1 << CS12) | (1 << CS10)	;таймер/лічильник T1, переддільник встановлено на 1024
	out TCCR1B, temp		;!у цьому місці саме відбувається пуск таймера, хоча переддільник вже працює!

	ldi attribute, 0x00;
	ldi count_encoder_forward, 0x00;
	ldi count_encoder_back, 0x00;
;*** Дозволяємо роботу замаскованих переривань ***


	ldi diode, 0x80



	sei	;встановлюємо прапорець глобального дозволу переривань
		;працюватиме тільки переривання TIM1_COMPA),
		;тобто переривання по співпадінню A таймера/лічильника Т1


;Вважаємо, що в первісному стані енкодер ніхто не використовує, тобто
	;в кожному з його каналів (канал B - PB4; канал A - PB7) утримується "0"
	;*** Переходимо в нескінченний цикл опитування стану енкодера ***

Infinite_loop:; нескінченний цикл

	cpi attribute, 0xFF;
	breq Check_Encoder;
	nop
	st Z, diode; виводимо значення лічильника на світлодіодну лінійку
	nop
	rjmp Infinite_loop; назад до початку нескінченного циклу

Check_Encoder:
	in next_encoder_state, PINB	;читаємо поточний стан каналів енкодера

;Отримуємо стан бітів порту, що відповідають за поточний стан енкодера
	andi next_encoder_state, 0b10010000

	cp next_encoder_state, last_encoder_state
	breq Next_iterate	;якщо стан енкодера не змінився - продовжуємо його читати

	cpi last_encoder_state, 0b00000000	;якщо в каналі В енкодера не було "0" і в каналі А теж не було "0"
	brne Next_iterate; переходимо до подальших дій

	cpi next_encoder_state, 0b00010000	;якщо в каналі A енкодера залишився "0", а в каналі B з'явилася "1", то енкодер повернули проти годинниковою стрілкою
	breq Decr_state		;переходимо до зменшення стану енкодера

	cpi next_encoder_state, 0b10000000	;якщо в каналі A енкодера з'явилася "1", а в каналі B залишився "0", то енкодер повернули за годинниковою стрілкою
	breq Incr_state		;переходимо до збільшення стану енкодера

	rjmp Next_iterate	;якщо в жодному з каналів не стався фронт - йдемо далі

Incr_state:
	ldi encoder_counter_r, 0b00000000
	inc encoder_data	;інкремент кількості імпульсів, отриманих від енкодера

	rjmp Next_iterate	;переходимо до подальших дій

Increment:
	inc encoder_counter_r
	ldi encoder_counter_l, 0b00000000

	cpi encoder_counter_r, 0b00000010	;якщо в каналі A енкодера з'явилася "1", а в каналі B залишився "0", то енкодер повернули за годинниковою стрілкою
	BRGE Incr_state

Decr_state:
	ldi encoder_counter_l, 0b00000000
	dec encoder_data	;декремент кількості імпульсів, отриманих від енкодера

Decrement:
	inc encoder_counter_l
	ldi encoder_counter_r, 0b00000000

	cpi encoder_counter_l, 0b00000100	;якщо в каналі A енкодера залишився "0", а в каналі B з'явилася "1", то енкодер повернули проти годинниковою стрілкою
	BRGE Decr_state		;переходимо до зменшення стану енкодера

Next_iterate:	;перехід на цю мітку здійсниться в будь-якому випадку!
	in temp, PINB	;читаємо поточний стан каналів енкодера

	;Отримуємо стан біта порту, що відповідає за натискання енкодера як кнопки
	andi temp, 0b00100000

	tst temp	;якщо енкодер натиснуто як кнопку, то обнуляємо лічильник імпульсів, отриманих від нього
	brne Led_action		;а якщо ні - йдемо далі
	//ldi temp, 0x88
	//mov gray_data, temp

Led_action:
	mov diode, gray_data	;оновлюємо вміст регістра стану лінійки світлодіодів
	mov last_encoder_state, next_encoder_state	;підготовка до наступної ітерації опитування стану енкодера
	;Присвоюємо попереднього стану енкодера його поточний стан
	ldi attribute, 0x00;
	rjmp Infinite_loop; назад до початку нескінченного циклу

TIM1_COMPA:		;обробник зовнішнього переривання TIM1_COMPA (при вході I = 0)
	
	ldi attribute, 0xFF;
	reti	;повернення із переривання
		;при цьому автоматично встановлюється прапорець I
		;з метою подальшого дозволу переривань


.EXIT; кінець програми
