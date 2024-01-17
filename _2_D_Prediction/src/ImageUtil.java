import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
public class ImageUtil {
    public BufferedImage readImage(String inputImagePath) throws IOException {
        // Load the image
        return  ImageIO.read(new File(inputImagePath));
    }

    public int[][] convertImageTo2DArray(String imagePath) {
        try {
            // Load the image using ImageIO
            BufferedImage image = ImageIO.read(new File(imagePath));
            if (image == null) {
                System.err.println("Failed to read the image.");
                return null;
            }

            // Get the dimensions of the image
            int width = image.getWidth();
            int height = image.getHeight();

            // Convert the image to a 2D array
            int[][] result = new int[height][width];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    result[i][j] = image.getRGB(j, i) & 0xFF; // Get the grayscale value
                }
            }

            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void writeArrayListToFile(int[][] data, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int[] row : data) {
                StringBuilder rowString = new StringBuilder();
                for (int value : row) {
                    rowString.append(value).append(" ");
                }
                // Trim the trailing space and write the row to the file
                writer.write(rowString.toString().trim());
                // Move to the next line
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public BufferedImage generateCompressedImage(int [][] decoded) {
        int height = decoded.length;
        int width = decoded[0].length;

        BufferedImage compressedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int intensity = decoded[i][j];
                compressedImage.setRGB(j, i, intensity << 16 | intensity << 8 | intensity);
            }
        }

        return compressedImage;
    }
    public void saveCompressedImage(BufferedImage compressedImage, String outputImagePath) throws IOException {
        File outputImageFile = new File(outputImagePath);
        ImageIO.write(compressedImage, "jpg", outputImageFile);
    }
//    read from file

}