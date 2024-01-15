package LZWTechnique;

import LZWTechnique.Controller;
import LZWTechnique.HandlingBinary;
import LZWTechnique.ReadWriteFiles;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
//        lzw
        Controller c = new Controller(new LZW(), new HandlingBinary(), new ReadWriteFiles());
//        c.convertTextFromDecompressToCompress(pathForInputFile, pathForOutputFile);
//        c.convertTextFromCompressToDecompress(pathForInputFile, pathForOutputFile);
    }
}
//  ArrayList<Integer> a = new ArrayList<Integer>();
// a.add(65);
// a.add(66);
// a.add(65);
// a.add(128);
// a.add(128);
// a.add(129);
// a.add(131);
// a.add(134);
// a.add(130);
// a.add(129);
// a.add(66);
// a.add(138);
// a.add(139);
// a.add(138);

//  s3 = "ABAABABBAABAABAAAABABBBBBBBB";
// s2 -> 65 66 65 128 128 129 131 134 130 129 66 138 139 138 

// String binaryString = "10"; // 
//         byte decimalInteger = (byte)Integer.parseInt(binaryString, 10);

// System.out.println("dfdfdf" +  "1    fddfdf");
// System.out.println("s2 : " + s2);
// s2 ->65 66 65 128 128 129 131 134 130 129 66 138 139 138
// System.out.println(Math.log(139));
//a 1
//b 010
//c 00
//d 011
