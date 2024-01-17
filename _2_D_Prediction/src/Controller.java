import java.awt.image.BufferedImage;
import java.io.IOException;
public class Controller {
    ImageUtil readImageUtil;
    Prediction vectorPrediction;
    int [][] pixels;
    BufferedImage myImage;
    public  Controller(ImageUtil _readImage, Prediction vectorP)
    {
        this.readImageUtil = _readImage;
        this.vectorPrediction = vectorP;

    }
    //  load image first

    public int[][] CompressImage(String pathImage,String pathOutputBinary) throws IOException {
//      load image first
        myImage = readImageUtil.readImage(pathImage);
        int width = myImage.getWidth();
        int height = myImage.getHeight();
//      define array of pixels
        pixels = new int[height][width];
//       divide image into pixels
        pixels = readImageUtil.convertImageTo2DArray(pathImage);
//       set values to prediction class
        vectorPrediction.setDetails(width, height, pixels);
        vectorPrediction.getPrediction_2_D();
        vectorPrediction.getDifference();
//      quantize
        vectorPrediction.quantize();
//      Todo store in binary file
        readImageUtil.writeArrayListToFile(vectorPrediction.getQuantized(), pathOutputBinary);
       return vectorPrediction.getQuantized();
    }

    public void decompress(int [][] quantized, String outputImagePath)
    {
        int [][] decode = new int[quantized.length][quantized[0].length];
//        System.out.println("quantized : " + quantized[0].length + " " + quantized.length);
        vectorPrediction.setDetails(quantized[0].length, quantized.length, quantized);
//        get predicted
        vectorPrediction.getPrediction_2_D();
//        get deQuan
        vectorPrediction.deQuantize(quantized);
        decode = vectorPrediction.decode();
        try {
            BufferedImage compressedImage = readImageUtil.generateCompressedImage(decode);
            readImageUtil.saveCompressedImage(compressedImage, outputImagePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}