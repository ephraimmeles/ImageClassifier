public class MultiPerceptron {
    private int m; // number of perceptrons
    private int n; // length of feature vector
    private Perceptron[] perceptrons; // array of perceptrons

    // Creates a multi-perceptron object with m classes and n inputs.
    // It creates an array of m perceptrons, each with n inputs.
    public MultiPerceptron(int m, int n) {
        this.m = m;
        this.n = n;
        perceptrons = new Perceptron[m];
        for (int i = 0; i < m; i++)
            perceptrons[i] = new Perceptron(n);
    }

    // Returns the number of classes m.
    public int numberOfClasses() {
        return m;
    }

    // Returns the number of inputs n (length of the feature vector).
    public int numberOfInputs() {
        return n;
    }

    // Returns the predicted label (between 0 and m-1) for the given input.
    public int predictMulti(double[] x) {
        double max = Double.NEGATIVE_INFINITY;
        int predictedLabel = -1;

        for (int i = 0; i < perceptrons.length; i++) {
            double psum = perceptrons[i].weightedSum(x);

            if (psum > max) {
                max = psum;
                predictedLabel = i;
            }
        }

        return predictedLabel;
    }

    // Trains this multi-perceptron on the labeled (between 0 and m-1) input.
    public void trainMulti(double[] x, int label) {
        int target;
        for (int i = 0; i < perceptrons.length; i++) {
            if (i == label) {
                target = 1;
            }
            else {
                target = -1; // Set target label
            }
            perceptrons[i].train(x, target);
        }
    }

    // Returns a String representation of this MultiPerceptron, with
    // the string representations of the perceptrons separated by commas
    // and enclosed in parentheses.
    // Example with m = 2 and n = 3: ((2.0, 0.0, -2.0), (3.0, 4.0, 5.0))
    public String toString() {
        String pstring = "(";

        for (int i = 0; i < m; i++) {
            pstring += perceptrons[i].toString(); // Access weights directly
            if (i < m - 1) {
                pstring += ", ";
            }
        }
        pstring += ")";
        return pstring;
    }


    // Tests this class by directly calling all instance methods.
    public static void main(String[] args) {
        int m = 2;
        int n = 3;

        double[] training1 = { 3.0, 4.0, 5.0 };  // class 1
        double[] training2 = { 2.0, 0.0, -2.0 };  // class 0
        double[] training3 = { -2.0, 0.0, 2.0 };  // class 1
        double[] training4 = { 5.0, 4.0, 3.0 };  // class 0


        MultiPerceptron perceptron = new MultiPerceptron(m, n);
        StdOut.println(perceptron);
        StdOut.println(perceptron.predictMulti(training1));
        StdOut.println(perceptron.numberOfClasses());
        StdOut.println(perceptron.numberOfInputs());
        perceptron.trainMulti(training1, 1);
        StdOut.println(perceptron);
        perceptron.trainMulti(training2, 0);
        StdOut.println(perceptron);
        perceptron.trainMulti(training3, 1);
        StdOut.println(perceptron);
        perceptron.trainMulti(training4, 0);
        StdOut.println(perceptron);
    }
}
