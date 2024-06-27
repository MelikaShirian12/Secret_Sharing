import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


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