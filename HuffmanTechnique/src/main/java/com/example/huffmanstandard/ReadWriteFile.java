package com.example.huffmanstandard;
import java.io.*;
import java.util.ArrayList;
public class ReadWriteFile {


    public String readTextFromFileToCompress(String filePath) {
        try {
            FileReader file = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(file);
            String line, text = "";
            while ((line = reader.readLine()) != null) {
                text += line;
            }
            return text;
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    public String readFromCompressedFile(String fileName) {
        String binary = "";
        StringBuilder binaryStringData = new StringBuilder();
        try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
            byte[] bytes = new byte[fileInputStream.available()];
            fileInputStream.read(bytes);
            for (byte b : bytes) {
                binary = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
                binaryStringData.append(binary);
            }
        } catch (IOException e) {
            return e.getMessage();
        }
        return binaryStringData.toString();
    }

    public String writeDecompressedText(String filePath, String Text) {
        try {
            // Create a FileWriter object to write to the file
            FileWriter writer = new FileWriter(filePath);
            writer.write(Text);
            writer.close();
            return null;
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    void writeToFileCompressedText(String fileName, byte[] dataBinaryStream) {
        try (FileOutputStream fileWriter = new FileOutputStream(fileName)) {
            for (byte item : dataBinaryStream) {
                fileWriter.write(item);
            }
        } catch (IOException e) {
            System.out.println("error occurred in file writing" + e.getMessage());
        }
    }
}
