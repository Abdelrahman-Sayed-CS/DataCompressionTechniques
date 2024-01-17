package com.example.vectorquantizationgui;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
public class Controller {
    ReadWriteImage readWriteImage;
    VectorQuantization vectorQuantization;
    int [] pixels;
    BufferedImage myImage;
    public  Controller(ReadWriteImage readWriteImage, VectorQuantization vectorQuantization)
    {
        this.readWriteImage = readWriteImage;
        this.vectorQuantization = vectorQuantization;

    }

    public static void saveImageToBinaryFile(BufferedImage image, String filePath) {
        try {
            // Create a ByteArrayOutputStream to store the image bytes
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            // Write the image to the ByteArrayOutputStream in PNG format (you can choose a different format)
            ImageIO.write(image, "png", byteArrayOutputStream);

            // Get the byte array from the ByteArrayOutputStream
            byte[] imageBytes = byteArrayOutputStream.toByteArray();

            // Write the byte array to a binary file
            try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
                fileOutputStream.write(imageBytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void CompressImage(String pathImage,String pathOutputImage, int maxIteration) throws IOException {
//        load image first
        myImage = readWriteImage.readImage(pathImage);
        int width = myImage.getWidth();
        int height = myImage.getHeight();
        vectorQuantization.defineArrayOfPixels(width, height);
//        define array of pixels
        pixels = new int[width * height];
//        divide image into pixels
        pixels = vectorQuantization.divideImageIntoPixels(myImage);
//        get codebook
        int [] codebook = vectorQuantization.kclusterBigT(pixels, maxIteration);
        for (int i = 0; i < codebook.length; i++) {
            System.out.print(codebook[i] + " ");
        }
        System.out.println();
//        write compressed image
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        newImage = vectorQuantization.compress();
        readWriteImage.writeImage(newImage, pathOutputImage);
        saveImageToBinaryFile(newImage, "I:\\VSprojects\\ProjectsOfThirdYear\\DataCom\\vectorBinaryFile.bin");
    }
}
