using System;
using System.Linq;

// https://www.codewars.com/kata/descending-order
namespace Dev
{
    class Program
    {
        static void Main(string[] args)
        {
            
            int n = 21445;

            int m = Int32.Parse(string.Concat(n.ToString().OrderByDescending(c => c)));
            
            Console.WriteLine(m);
        }

    }
}
