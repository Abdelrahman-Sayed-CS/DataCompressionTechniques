package com.example.huffmanstandard;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class Controller {
    HandlingBinary HB;
    HuffmanAlgo huffmanAlgo;
    ReadWriteFile readWriteFile;
    public Controller(HandlingBinary h, HuffmanAlgo hA, ReadWriteFile rWF){
        HB = h;
        huffmanAlgo = hA;
        readWriteFile = rWF;
    }
    void convertTextFromDecompressToCompress(String filePathR, String filePathW) {
        String text = readWriteFile.readTextFromFileToCompress(filePathR);
//        return map--> char , freq(int)
//        ArrayList<String>compressData = new ArrayList<>();
        Map<Character, Integer> freqMap = huffmanAlgo.compress(text);
//            System.out.println("map");

//        overhead ---> numChar(1 byte) + sizeOfStream(1 byte) + (char(1 byte) + freq (1 byte)) + stream
        StringBuilder overhead = new StringBuilder();
        overhead.append(HB.convertIntToBinary(freqMap.size())); //numChar(1 byte)
        overhead.append(HB.convertIntToBinary(huffmanAlgo.getCompressedText().length())); //sizeOfStream(1 byte)
        for (Map.Entry<Character, Integer> entry: freqMap.entrySet()) {
            String pairCharFreq = "";
//             (char(1 byte) + freq (1 byte))
            System.out.println(entry.getKey() + " " + entry.getValue());
            pairCharFreq += HB.convertCharToBinary(entry.getKey());
            pairCharFreq += HB.convertIntToBinary(entry.getValue());
            overhead.append(pairCharFreq);
        }
//     append stream after make it multiple of 8
        overhead.append(HB.makeBinaryMultipleOf_8(huffmanAlgo.getCompressedText()));

//       convert binary to bytes;
        byte[] bytes = HB.toBytes(overhead.toString());
        System.out.println(HB.makeBinaryMultipleOf_8(huffmanAlgo.getCompressedText()));
        System.out.println("compressed : " + overhead);
        readWriteFile.writeToFileCompressedText(filePathW, bytes);
    }
    void convertTextFromCompressToDecompress(String filePathR, String filePathW) {
        String binary = readWriteFile.readFromCompressedFile(filePathR);
        //  overhead ---> numChar(1 byte) + sizeOfStream(1 byte) + (char(1 byte) + freq (1 byte))
        //  compressed   ----> overhead + stream
        int numDiffChar = HB.convertBinaryIntoInteger(binary.substring(0, 8));
        int sizeOfStream = HB.convertBinaryIntoInteger(binary.substring(8, 16));
        System.out.println("num char  : " + numDiffChar);
        System.out.println("stream size  : " + sizeOfStream);
        Map<Character, Integer>freqMap = new HashMap<>();

        for (int i = 1; i < numDiffChar+1; i++) {
            String charWithFreq = binary.substring(i*16, (i+1) * 16);
            char c = HB.binaryStringToChar(charWithFreq.substring(0, 8));
            int freq = HB.binaryStringToChar(charWithFreq.substring(8, 16));
            freqMap.put(c, freq);
        }
        int startPosForStream = 16 * (numDiffChar+1);
        String stream = binary.substring(startPosForStream, startPosForStream + sizeOfStream);
        String originalText = huffmanAlgo.decompress(freqMap, stream);
        System.out.println("stream : " +  stream);
        System.out.println("original text : " + originalText);
        readWriteFile.writeDecompressedText(filePathW, originalText);
    }
}
