public class Prediction {
    private static final int NUM_CLASSES = 32;
    private static final int CLASS_RANGE = 16;
    private static final int MID_VALUE = CLASS_RANGE / 2;
    private  int width;
    private  int height;
    private int [][]pixels;
    private int[][] mPrediction;
    private int [][]difference;
    private int [] [] quantized;
    private int [] [] de_quantized;
    private int [][] decoded;
    public Prediction() {
    }
    void setDetails(int w, int h,int [][] _pixels){
        this.height = h;
        this.width = w;
        this.pixels = _pixels;
        this.difference = new int[height][width];
        this.mPrediction = new int[height][width];
        this.quantized = new int [height][width];
        this.de_quantized = new int [height][width];
        this.decoded = new int[height][width];
    }

    public int[][] getQuantized() {
        return quantized;
    }

    public void getPrediction_2_D(){
//        mPrediction = pixels;
        for(int i = 1;i < height;i++)
        {
            for (int j = 1; j < width; j++)
            {
                int left = mPrediction[i][j - 1], top = mPrediction[i - 1][j], diagonal = mPrediction[0][0];
                if (diagonal <= Math.min(left, top))
                    mPrediction[i][j] = Math.max(left, top);
                else if (diagonal >= Math.max(left, top))
                    mPrediction[i][j] = Math.min(left, top);
                else
                    mPrediction[i][j] = left + top - diagonal;
            }
        }
    }
    public void getDifference(){
        for (int i = 0;i < height;i++){
            for (int j = 0;j < width;j++){
                difference[i][j] = pixels[i][j] - mPrediction[i][j];
            }
        }
    }
//   use 2_D_difference --> to get QuantizedDifference
    public int [][] quantize() {
        quantized = pixels;
        for (int i = 1; i < height; i++) {
            for (int j = 1; j < width; j++) {
                int value = difference[i][j];
                // Quantize the value
                int quantizedValue = quantizeValue(value);
                // Update the difference array with quantized value
                quantized[i][j] = quantizedValue;
            }
        }
        return quantized;
    }
    private int quantizeValue(int value) {
        // Ensure that the value is within the range [-255, 255]
        value = Math.max(-255, Math.min(255, value));
        // Map the value to the corresponding quantization level
        int quantizationLevel = (value + 255) / CLASS_RANGE;
        // Ensure that the quantization level is within the range [0, NUM_CLASSES - 1]
        quantizationLevel = Math.max(0, Math.min(NUM_CLASSES - 1, quantizationLevel));
        // Map the quantization level back to the quantized value
        //int quantizedValue = quantizationLevel * CLASS_RANGE - 255;
        return quantizationLevel;
    }

//    0 31
//    32 63
//    64 and so on......
//    methods for decompress

    private int deQuantizeValue(int quantizationLevel) {
        // Map the quantized value back to the original range
        int deQuantizedValue = quantizationLevel * CLASS_RANGE + MID_VALUE - 255;
        return deQuantizedValue;
    }
    public void deQuantize(int[][] quantizedDifference) {
        for (int i = 1; i < quantizedDifference.length; i++) {
            for (int j = 1; j < quantizedDifference[0].length; j++) {
                int quantizedValue = quantizedDifference[i][j];
                // De quantize the value
                int deQuantizedValue = deQuantizeValue(quantizedValue);
                // Update the de quantized difference array with dequantized value
                de_quantized[i][j] = deQuantizedValue;
            }
        }
    }
    public int [][] decode(){
        System.out.println("width : " + width + " " + height);
        for (int i = 1; i < height; i++) {
            for (int j = 1; j < width; j++)
            {
                decoded[i][j] = mPrediction[i][j] + de_quantized[i][j];
            }
        }
        return decoded;
    }
    void print(){
        for(int i = 1;i < height;i++)
        {
            for (int j = 1; j < width; j++)
            {
                System.out.print(mPrediction[i][j] + " ");
            }
            System.out.println();
        }
    }
}