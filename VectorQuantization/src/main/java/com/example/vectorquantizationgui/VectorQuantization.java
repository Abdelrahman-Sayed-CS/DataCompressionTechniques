package com.example.vectorquantizationgui;

import java.awt.image.BufferedImage;
public class VectorQuantization {
    private int[] codebook;
    private int[] pixels;
    private int width;
    private int height;
    private int codebookSize;
    public VectorQuantization(int codebookSize)
    {
        this.codebookSize = codebookSize;
        codebook = new int[codebookSize];
    }
    public void defineArrayOfPixels(int width, int height){
        pixels = new int[width * height];
        this.width = width;
        this.height = height;
    }
    private void defineCodebook(int []pixels){
        for (int i = 0; i < codebookSize; i++) {
            codebook[i] = pixels[i];
        }
    }
//    define codebook
//    find least distance from my pixel to codebook pixels
//    codebook(p1 , p2 , p3 , p4)  , myPixel --->
//    for example (0, 34, 56, 90)  ,  10
//    10 --> 0 min distance
    private int findNeareatColor(int pixelValue, int[] codebook) {
        int min = Integer.MAX_VALUE;
        int index = 0;
        for (int i = 0; i < codebook.length; i++) {
            int distance = Math.abs(pixelValue - codebook[i]);
            if (distance < min) {
                min = distance;
                index = i;
            }
        }
        return index;
    }
    public int[] divideImageIntoPixels(BufferedImage image)
    {
        int index = 0;
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                int pixel = image.getRGB(x, y);
                int grayscale = (pixel >> 16) & 0xFF;
                pixels[index++] = grayscale;
            }
        }
        return pixels;
    }
    public  int[] kclusterBigT(int[] pixels, int maxIterations) {
//      define codebook
        defineCodebook(pixels);
        for (int nextI = 0; nextI < maxIterations; nextI++) {
//          count ---> freq
            int[] count = new int[codebookSize];
            int[] cSum = new int[codebookSize]; // clustering
//            iterate over all pixels
            for (int pixel : pixels) {
//                same distance(pixelCodebook, pixelInArrayPixels) between pixels
                int nearestIndex = findNeareatColor(pixel, codebook);
//                freq for each index in array count
                count[nearestIndex]++;
//                use this cSum array to get average
                cSum[nearestIndex] += pixel;
            }
//          index --> 3 + 23 12 4
//         to update codebook with closlest pixels
            for (int i = 0; i < codebookSize; i++) {
                if (count[i] != 0) {
                    codebook[i] = cSum[i] / count[i];
                }
            }
        }
        return codebook;
    }

    public BufferedImage compress() {
        BufferedImage compressedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        int index = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int grayscale = pixels[index];
                int nearestColorIndex = findNeareatColor(grayscale, codebook);
                int newPixelValue = codebook[nearestColorIndex];
                compressedImage.setRGB(x, y, (newPixelValue << 16) | (newPixelValue << 8) | newPixelValue);
                index++;
            }
        }
        return compressedImage;
    }
}
