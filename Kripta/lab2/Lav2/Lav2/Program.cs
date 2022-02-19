using System;

namespace Lav2
{
    public class CaesarCipher
    {
        //символи української абетки
        const string alfabet = "АБВГҐДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩЬЮЯ";

        private string CodeEncode(string text, int k)
        {
            //додаємо до алфавіту малі літери
            var fullAlfabet = alfabet + alfabet.ToLower();
            var letterQty = fullAlfabet.Length;
            var retVal = "";
            for (int i = 0; i < text.Length; i++)
            {
                var c = text[i];
                var index = fullAlfabet.IndexOf(c);
                if (index < 0)
                {
                    //якщо літеру не знайдено, додаємо її незмінною
                    retVal += c.ToString();
                }
                else
                {
                    var codeIndex = (letterQty + index + k) % letterQty;
                    retVal += fullAlfabet[codeIndex];
                }
            }

            return retVal;
        }
        //шифрування тексту
        public string Encrypt(string plainMessage, int key)
            => CodeEncode(plainMessage, key);

        //дешифрування тексту
        public string Decrypt(string encryptedMessage, int key)
            => CodeEncode(encryptedMessage, -key);
    }
    class Program
    {
        static void Main(string[] args)
        {
            var cipher = new CaesarCipher();
            Console.Write("Введіть текст повідомлення: ");
            var message = Console.ReadLine();
            Console.Write("Введіть ключ: ");
            var secretKey = Convert.ToInt32(Console.ReadLine());
            var encryptedText = cipher.Encrypt(message, secretKey);
            Console.WriteLine("Зашифроване повідомлення: {0}", encryptedText);
            Console.WriteLine("Розшифроване повідомлення: {0}", cipher.Decrypt(encryptedText, secretKey));
            Console.ReadLine();
        }
    }
}
