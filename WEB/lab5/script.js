//Оленченко Ілля КІт-211 02.05.2022 22:05

//Завдання 1
function think(num){
    var result = (num - 7) / 2;
    return result;
}

//Завдання 2
function digits(num){
    var digits = [];
    while (num > 0) {
        digits.push(num % 10);
        num = parseInt(num / 10);
    }
    console.log(digits);

    num = 0;

    digits.forEach(element => {
        num = + num + element;
    });

    return num;
}