import cv2
number = 0
img = cv2.imread('1.png')

# перетворити зображення в градації сірого
gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)

# застосувати порогове значення для перетворення зображення у градаціях сірого на двійкове зображення
ret, thresh = cv2.threshold(gray, 50, 255, 0)

# знайти контури
contours, hierarchy = cv2.findContours(
    thresh, cv2.RETR_TREE, cv2.CHAIN_APPROX_SIMPLE)


for cnt in contours:
    approx = cv2.approxPolyDP(cnt, 0.01*cv2.arcLength(cnt, True), True)
    if len(approx) == 3:
        number += 1
        img = cv2.drawContours(img, [cnt], -1, (255, 255, 0), 3)

        # обчислення центр мас трикутника
        M = cv2.moments(cnt)
        if M['m00'] != 0.0:
            x = int(M['m10']/M['m00'])
            y = int(M['m01']/M['m00'])
        cv2.putText(img, '-', (x, y), cv2.FONT_HERSHEY_SIMPLEX,
                    0.6, (255, 255, 0), 2)
    elif len(approx) == 4:
        number += 1
        img = cv2.drawContours(img, [cnt], -1, (255, 255, 0), 3)

        # обчислення центр мас прямокутника
        M = cv2.moments(cnt)
        if M['m00'] != 0.0:
            x = int(M['m10']/M['m00'])
            y = int(M['m01']/M['m00'])
        cv2.putText(img, '+', (x, y),
                    cv2.FONT_HERSHEY_SIMPLEX, 0.6, (255, 255, 255), 2)

print("Знайдено потрібних нам об'єктів:", number)
cv2.imshow("Shapes", img)
cv2.waitKey(0)
cv2.destroyAllWindows()
