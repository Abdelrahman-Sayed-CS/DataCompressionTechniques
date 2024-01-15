package LZWTechnique;

import java.util.ArrayList;

public class Controller {
    HandlingBinary handlingBinary;
    LZW lzwAlgo;
    ReadWriteFiles readWriteFiles;
    public ArrayList<Integer> data;

    public  Controller(LZW algo, HandlingBinary HB, ReadWriteFiles readWriteFiles){
        handlingBinary = HB;
        lzwAlgo = algo;
        this.readWriteFiles = readWriteFiles;
    }
    void convertTextFromDecompressToCompress(String filePathR, String filePathW) {
        // step1 : read from file(string--> decompress) -->about call this method ==> readTextFromFileToCompress(filePathR)
        // step2 : store compressed(in arrayList --> data) that contains compressed data when call method runCompress
        // step3 : write compressed text to file(filePathW)
        data = lzwAlgo.compress(readWriteFiles.readTextFromFileToCompress(filePathR));
        int numBits = Integer.toBinaryString(handlingBinary.maxElementsInIndexFromAnyTag(data)).length();
        // git binary text to store it in bin file to decrease storage --> HandleBinary.getBinary(data, numBits);
        // HandleBinary.convertBinaryStringToBytes(HandleBinary.getBinary(data, numBits));
        // HandleBinary.getBinary(data, numBits) this method return array of bytes to store it in file
        readWriteFiles.writeToFileCompressedText(filePathW, handlingBinary.getBinary(data, numBits));
    }
    void convertTextFromCompressToDecompress(String filePathR, String filePathW) {
        // step1 : read from file(compressed) -->about call this method ==> readTextFromFileToDecompress(filePathR)
        // step2 : store decompressed(string) when call method runDecompress
        // step3 : write deCompressed text to file(filePathW)
        String compressed = readWriteFiles.ReadFromCompressedFile(filePathR);
        int overhead = Integer.parseInt(compressed.substring(0, 8), 2);
        data = handlingBinary.getOrginal(compressed, overhead);
        String decompress = lzwAlgo.decompress(data);
        readWriteFiles.writeDecompressedText(filePathW, decompress);
    }
}
