import java.util.Scanner;
class demo{
    public boolean isPrime(int n)
    {
        int count = 0;

        // 0, 1 negative numbers are not prime
        if (n < 2)
            return false;

        // checking the number of divisors b/w 1 and the number n-1
        for (int i = 2; i < n; i++)
        {
            if (n % i == 0)
                return false;
        }

        // if reached here then must be true
        return true;
    }}
    class PrimeNumber {
        public static void main(String[] args) {
            String ch;
            do {
                System.out.println("Enter the number to check prime or not : ");
                Scanner sc = new Scanner(System.in);
                int n = sc.nextInt();
                demo d = new demo();
                if (d.isPrime(n)) {
                    System.out.println("the number " + n + " is prime ");
                } else {
                    System.out.println("The number " + n + " is not prime ");
                }
                System.out.println("do you want to continue : yes/no?");
                ch=sc.next();
            }while(ch.equals("yes"));
        }
    }