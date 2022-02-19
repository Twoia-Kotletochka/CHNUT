using System;

namespace Laba1_Csh
{
    class Program
    {
        const int n = 5;
        public static string str = "";

        public static int[] keycolms = new int[n] { 0, 1, 4, 3, 2 };
        public static int[] keyrows = new int[n] { 1, 0, 3, 2, 4 };

        public static string[,] Encryptio = new string[n, n];
        public static string[,] Decryptio = new string[n, n];

       public static void encryption(string[,] table)
        {

            for (int i = 0; i < 5; i++)
            {
                for (int j = 0; j < 5; j++)
                {
                    str += (table[keyrows[j], keycolms[i]]);
                    Encryptio[i, j] += table[keyrows[i], keycolms[j]];
                }
            }

            for (int i = 0; i < 5; i++)
            {
                for (int j = 0; j < 5; j++)
                {
                    Console.Write(Convert.ToString(Encryptio[i, j] + "  "));
                }
                Console.WriteLine();
            }
        }

        public static void decryption()
        {
            str = "";
            for (int i = 0; i < 5; i++)
            {
                for (int j = 0; j < 5; j++)
                {
                    str += (Encryptio[keyrows[i],keycolms[j]]);
                    Decryptio[i,j] += (Encryptio[keyrows[i], keycolms[j]]);
                }
            }

            for (int i = 0; i < 5; i++)
            {
                for (int j = 0; j < 5; j++)
                {
                    Console.Write(Convert.ToString(Decryptio[i, j] + "  "));
                }
                Console.WriteLine();
            }
        }

        static void Main(string[] args)
        {
            string[,] table = new string[n, n] {
                {"Г","Р","У","З","И"},
                {"Т","Е"," ","А","П"},
                {"Е","Л","Ь","С","И"},
                {"Н","Ы"," ","В"," "},
                {"Я","Щ","И","К","И"}
            };

            Console.WriteLine("Оригинал: ");
            for (int i = 0; i < 5; i++)
            {
                for (int j = 0; j < 5; j++)
                {
                    Console.Write(Convert.ToString(table[i, j] + "  "));
                }
                Console.WriteLine();
            }
            Console.WriteLine();
            Console.WriteLine("Зашифровано: ");
            encryption(table);
            Console.WriteLine();
            Console.WriteLine("Расшифровано: ");
            decryption();
            Console.ReadLine();
        }
    }
}
