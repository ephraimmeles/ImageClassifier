public class ImageClassifier {

    // Creates a feature vector (1D array) from the given picture.
    public static double[] extractFeatures(Picture picture) {
        int width = picture.width();
        int height = picture.height();
        double[] gray = new double[height * width];
        int index = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int grayscaleValue = picture.get(j, i).getRed(); //
                gray[index++] = grayscaleValue;
            }
        }
        return gray;
    }

    // See below.
    public static void main(String[] args) {
        String trainingDataFile = args[0];
        String testingDataFile = args[1];

        // Step 1: Read and Print Information from Training Data
        In trainingInput = new In(trainingDataFile);
        int m = trainingInput.readInt();
        int width = trainingInput.readInt();
        int height = trainingInput.readInt();

        while (!trainingInput.isEmpty()) {
            String filename = trainingInput.readString();
            int label = trainingInput.readInt();
        }

        trainingInput.close();

        // Step 2: Display Training Images
        trainingInput = new In(trainingDataFile);
        trainingInput.readInt(); // Skip m
        trainingInput.readInt(); // Skip width
        trainingInput.readInt(); // Skip height

        while (!trainingInput.isEmpty()) {
            String filename = trainingInput.readString();
            int label = trainingInput.readInt();

            Picture pic = new Picture(filename);
            pic.show();
        }

        trainingInput.close();

        // Step 3: Read and Display Testing Images
        In testingInput = new In(testingDataFile);
        m = testingInput.readInt(); // Read m again
        width = testingInput.readInt(); // Read width again
        height = testingInput.readInt(); // Read height again


        while (!testingInput.isEmpty()) {
            String filename = testingInput.readString();
            int label = testingInput.readInt();

            Picture pic = new Picture(filename);
            pic.show();
        }

        testingInput.close();
        // Part II: Classifying Images
        MultiPerceptron perceptron = new MultiPerceptron(m, width * height);

        // Training
        trainingInput = new In(trainingDataFile);
        trainingInput.readInt(); // Skip m
        trainingInput.readInt(); // Skip width
        trainingInput.readInt(); // Skip height

        while (!trainingInput.isEmpty()) {
            String filename = trainingInput.readString();
            int label = trainingInput.readInt();
            Picture pic = new Picture(filename);
            double[] features = extractFeatures(pic);
            perceptron.trainMulti(features, label);
        }
        trainingInput.close();


        // Testing and Calculating Error Rate
        testingInput = new In(testingDataFile);
        testingInput.readInt(); // Skip m
        testingInput.readInt(); // Skip width
        testingInput.readInt(); // Skip height

        int misclassified = 0;
        int total = 0;

        while (!testingInput.isEmpty()) {
            String filename = testingInput.readString();
            int trueLabel = testingInput.readInt();
            Picture pic = new Picture(filename);

            double[] features = extractFeatures(pic);
            int predictedLabel = perceptron.predictMulti(features);

            if (predictedLabel != trueLabel) {
                System.out.println(
                        filename + ", label = " + trueLabel + ", predict = " +
                                predictedLabel);
                misclassified++;
            }

            total++;
        }

        testingInput.close();

        double errorRate = (double) misclassified / total;
        System.out.println("test error rate = " + errorRate);
    }
}
