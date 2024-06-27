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

}