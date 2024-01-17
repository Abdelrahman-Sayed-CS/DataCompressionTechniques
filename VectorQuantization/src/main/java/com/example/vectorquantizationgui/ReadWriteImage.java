package com.example.vectorquantizationgui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;
public class ReadWriteImage {

    public ReadWriteImage(){

    }
    public BufferedImage readImage(String inputImagePath) throws IOException {
        // Load the image
        return  ImageIO.read(new File(inputImagePath));
    }

    public void writeImage(BufferedImage compressedImage, String outputImagePath) throws IOException {

            File compressedImageFile = new File(outputImagePath);
            ImageIO.write(compressedImage, "png", compressedImageFile);
    }

    // enter the size of vector in code book n , m;
    // enter the number of vectors in code book k
    // block --> vector 

    // first step -> convert image to blocks 
    // second step -> get code book
    // third step -> compress 
    //     For Compression Inputs will be :

    // 1- Image

    // 2- Code book Size

    // 3- Vector Dimensions (n*m)

    // output:

    // 1- compreesed image 

    // 2- Code book

    // For Decompression your inputs will be :

    // 1- compreesed image 

    // 2- Code book

    // Output :

    // The decompressed image
}
