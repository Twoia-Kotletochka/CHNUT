import cv2

# Завантажити зображення, перетворити на градації сірого, розмиття за Гауссом, поріг Оцу
image = cv2.imread('1.jpg')
#image = cv2.imread('2.png')
gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
blur = cv2.GaussianBlur(gray, (3, 3), 0)
thresh = cv2.threshold(
    blur, 0, 255, cv2.THRESH_BINARY_INV + cv2.THRESH_OTSU)[1]

# Фільтрація за допомогою контурної області та видаляйте шум за розміров вказаної в пікселях
cnts = cv2.findContours(thresh, cv2.RETR_TREE, cv2.CHAIN_APPROX_SIMPLE)
cnts = cnts[0] if len(cnts) == 2 else cnts[1]
for c in cnts:
    area = cv2.contourArea(c)
    if area < 100:  # площа в пікселях
        cv2.drawContours(thresh, [c], -1, (0, 0, 0), -1)

# Морфінг, закриття та інвертувати зображення
kernel = cv2.getStructuringElement(cv2.MORPH_RECT, (5, 5))
close = 255 - cv2.morphologyEx(thresh, cv2.MORPH_CLOSE, kernel, iterations=2)

cv2.imshow('thresh', thresh)
cv2.imshow('close', close)
cv2.waitKey()
