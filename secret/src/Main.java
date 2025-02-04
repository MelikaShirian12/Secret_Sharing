import java.util.*;


public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("s : the secret : ");
        int s = sc.nextInt();
        System.out.println("t : Curve degree :");
        int t = sc.nextInt();
        System.out.println("n : number of people : (it should be more than t)");
        int n = sc.nextInt();
        System.out.println("p : measure : (choose it wisely so you won't get problem on solving the equations" +
                "while you are calculating the inverses)");
        int p = sc.nextInt();

        SecretFunc secretFunc = new SecretFunc();

        try {
            secretFunc.makeFunction(s%p , t, n, p);
        } catch (IllegalAccessException e) {
        }

        int final_s = secretFunc.findSecret();
        System.out.println(final_s);

        if (final_s == secretFunc.s)
            System.out.println("calculations you were right !");


    }

}


class SecretFunc {

    int s;
    int t;
    int n;
    int p;
    ArrayList<Integer> coefficients = new ArrayList<>();

    Map<Integer, Integer> shareholder_secrets = new HashMap<>();


    public void makeFunction(int s , int t , int n , int p) throws IllegalAccessException {

        if (t >= n)
            throw new RuntimeException("wrong input !");

        this.s = s;
        this.t = t;
        this.n = n;
        this.p = p;

        this.coefficients.add(s);
        //making the function
        for (int i = 1 ; i <= t ; ++i){
            Random random = new Random();
            this.coefficients.add(random.nextInt(p-1));
        }

        //making the n dots
        for (int i = 1 ; i<= n ; ++i) {
            double y = 0;
            for (int j = 0; j < coefficients.size(); ++j)
                y += coefficients.get(j) * (Math.pow(i,j) % p );
            shareholder_secrets.put(i ,(int)y%p);
        }

    }


    public int findSecret() {
        int result = 0 ;
        for (int i = 1; i <= n; ++i) {
            int temp = shareholder_secrets.get(i);
            for (int j = 1; j <= n; ++j) {
                if (i == j) continue;

                int numerator = j;
                int temp1 = j-i;

                if (temp1 < 0) {
                    temp1 *= -1;
                    numerator *= -1;

                    while( numerator < 0)
                        numerator += p;
                }


                if ( ! gcd(p , temp1))
                    throw new RuntimeException("we don't have this inverse !");

                int denominator = (int) (Math.pow(temp1, prime(p)-1) % p);

                temp *= numerator * denominator;
                temp %= p;
            }

            result += temp % p;
        }

        return result % p;
    }

    public int prime(int number){

        if (number == 1) return 0;
        int prime_phi = 1;
        for (int i = 2; i <= number; i++) {
            int counter = 0;
            while (number % i == 0) {
                number /= i;
                ++counter;
            }
            int a = (int)Math.pow(i , counter);
            int b =(int) Math.pow(i , counter-1);

            prime_phi *= (a-b);
        }

        return prime_phi;
    }

    public boolean gcd(int a, int b)
    {
        int i;
        if (a < b)
            i = a;
        else
            i = b;
        for (i = i; i > 1; i--) {
            if (a % i == 0 && b % i == 0)
                return false;
        }
        return true;
    }


}