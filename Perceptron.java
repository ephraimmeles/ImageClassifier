public class Perceptron {
    private int n; // number of items
    private double[] weights; // making the array to store weights;

    // Creates a perceptron with n inputs. It should create an array
    // of n weights and initialize them all to 0.
    public Perceptron(int n) {
        this.n = n;
        weights = new double[n]; // initialize the length of weight array
    }

    // Returns the number of inputs n.
    public int numberOfInputs() {
        return n;
    }

    // Returns the weighted sum of the weight vector and x.
    public double weightedSum(double[] x) {
        double sum = 0.0;
        for (int i = 0; i < x.length; i++) {
            sum += weights[i] * x[i];
        }
        return sum;
    }

    // Predicts the label (+1 or -1) of input x. It returns +1
    // if the weighted sum is positive and -1 if it is negative (or zero).
    public int predict(double[] x) {
        if (weightedSum(x) > 0) {
            return +1;
        }
        return -1;
    }

    // Trains this perceptron on the labeled (+1 or -1) input x.
    // The weights vector is updated accordingly.
    public void train(double[] x, int label) {
        if (label < predict(x)) {
            for (int i = 0; i < x.length; i++) {
                weights[i] = weights[i] - x[i];
            }
        }
        else if (label > predict(x)) {
            for (int i = 0; i < x.length; i++) {
                weights[i] = weights[i] + x[i];
            }

        }
    }

    // Returns a String representation of the weight vector, with the
    // individual weights separated by commas and enclosed in parentheses.
    // Example: (2.0, 1.0, -1.0, 5.0, 3.0)
    public String toString() {
        String weightString = "(";

        for (int i = 0; i < weights.length; i++) {
            weightString += weights[i];

            if (i < weights.length - 1) {
                weightString += ", ";
            }
        }

        weightString += ")";

        return weightString;
    }

    // Tests this class by directly calling all instance methods.
    public static void main(String[] args) {
        int n = 3;

        double[] training1 = { 3.0, 4.0, 5.0 };  // yes
        double[] training2 = { 2.0, 0.0, -2.0 };  // no
        double[] training3 = { -2.0, 0.0, 2.0 };  // yes
        double[] training4 = { 5.0, 4.0, 3.0 };  // no


        Perceptron perceptron = new Perceptron(n);
        StdOut.println(perceptron);
        StdOut.println(perceptron.numberOfInputs());
        StdOut.println(perceptron.predict(training1));
        StdOut.println(perceptron.weightedSum(training1));
        perceptron.train(training1, +1);
        StdOut.println(perceptron);
        perceptron.train(training2, -1);
        StdOut.println(perceptron);
        perceptron.train(training3, +1);
        StdOut.println(perceptron);
        perceptron.train(training4, -1);
        StdOut.println(perceptron);
    }
}
