package polynomial;

/************************************* 
* Student Name : Chintan Puri
* Student ID  : 916541358
* Email  : cpuri@mail.sfsu.edu
*************************************/ 

public class Polynomial {

    private int[] terms;
    private int maxPower;

    // default constructor
    public Polynomial() {

    }

    public Polynomial(int max) {
        maxPower = max;
        terms = new int[maxPower + 1];
    }

    /* Sets coefficients to the variables of the Polynomial.
     * @param coefficient
     * @param power
     * @throws InvalidPowerException (throws exception if the entered power exceeds the size of the Polynomial)
     */
    public void setTerm(int coefficient, int power) throws InvalidPowerException {
        if (power <= maxPower) {
            terms[power] = coefficient;
        } else {
            throw new InvalidPowerException("");
        }
    }

    /* Calculates the value of the Polynomial by taking 'x' as the input  
     * @param x
     */
    public int evaluate(int x) {

        int result = 0;
        if (terms != null) {
            for (int i = 0; i <= maxPower; i++) {
                result = (int) (result + terms[i] * (Math.pow(x, i)));
            }
            return (result);
        } else {
            return (0);
        }
    }

    public static void main(String[] args) {

        try {

            Polynomial p = new Polynomial();
            System.out.println(p);
            System.out.println(p.evaluate(3));

            Polynomial q = new Polynomial(2);
            q.setTerm(2, 0);
            q.setTerm(0, 1);
            q.setTerm(3, 2);
            System.out.println(q);
            System.out.println(q.evaluate(3));

            Polynomial z = new Polynomial(2);
            z.setTerm(-2, 0);
            z.setTerm(1, 1);
            z.setTerm(3, 2);
            System.out.println(z);
            System.out.println(z.evaluate(3));

            Polynomial t = new Polynomial(2);
            t.setTerm(2, 0);
            t.setTerm(-1, 1);
            t.setTerm(-3, 2);
            System.out.println(t);
            System.out.println(t.evaluate(3));

            Polynomial r = new Polynomial(1);
            r.setTerm(2, 0);
            r.setTerm(-1, 1);
            r.setTerm(-3, 2);
            System.out.println(r);
            System.out.println(r.evaluate(3));

        } catch (InvalidPowerException ipe) {
            System.out.println("You got an Invalid power exception");

        }
    }

    // Displays the Polynomial
    public String toString() {

        if (terms != null) {

            /* Prints the term with the highest power */
            if (terms[maxPower] > 0) {
                if (terms[maxPower] == 1) {
                    System.out.print("x^" + maxPower);
                } else {
                    System.out.print(terms[maxPower] + "x^" + maxPower);
                }
            } else if (terms[maxPower] == -1) {
                System.out.print("-x^" + maxPower);
            } else {
                System.out.print(terms[maxPower] + "x^" + maxPower);
            }

            /* Prints all the terms between the highest power term and the term with degree 1 */
            for (int i = maxPower - 1; i >= 2; i--) {

                if (terms[i] > 0) {

                    if (terms[i] == 1) {
                        System.out.print(" + x^" + i);
                    } else {
                        System.out.print(" + " + (terms[i]) + "x^" + i);
                    }
                } else if (terms[i] == -1) {
                    System.out.print(" - x^" + i);
                } else {
                    System.out.print(" - " + (terms[i]) + "x^" + i);
                }

            }

            /* Prints the term with degree 1 */
            if (terms[1] > 0) {
                if (terms[1] == 1) {
                    System.out.print(" + x");
                } else {
                    System.out.print(" + " + terms[1] + "x");
                }
            } else if (terms[1] < 0) {
                if (terms[1] == -1) {
                    System.out.print(" - x");
                } else {
                    System.out.print(" - " + terms[1] + "x");
                }
            }

            /* Prints the constant term */
            if (terms[0] != 0) {
                if (terms[0] > 0) {
                    System.out.print(" + " + (terms[0]));
                } else {
                    System.out.print(" - " + Math.abs(terms[0]));
                }
            }
        } else {
            System.out.print(0);
        }

        return ("");
    }

 
    /* User-defined Exception Class */
    public class InvalidPowerException extends Exception {

        public InvalidPowerException(String message) {
            super(message);
        }

    }
}
