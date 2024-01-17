package com.example.huffmanstandard;
import java.util.ArrayList;
import java.util.Map;
public class HandlingBinary {
    public String convertCharToBinary(Character character){
//        store this char in 1 byte
        return formatIntToBinaryString(Integer.toBinaryString(character), 8);
    }
    public String convertIntToBinary(int num){
//        store this num in 1 byte
        return formatIntToBinaryString(Integer.toBinaryString(num), 8);
    }
    public int convertBinaryIntoInteger(String binaryString){
      return Integer.parseInt(binaryString, 2);
    }

    public String formatIntToBinaryString(String compress, int numBits) {
//        String chunk = Integer.toBinaryString(number);
        compress = String.format("%" + numBits + "s", compress).replace(' ', '0');
        return compress;
    }
    public char binaryStringToChar(String binaryString) {
        // Ensure that the binary string has exactly 8 bits
        if (binaryString.length() != 8 || !binaryString.matches("[01]+")) {
            throw new IllegalArgumentException("Binary string must be of length 8 and contain only 0s and 1s");
        }
        // Parse the binary string as an integer
        int intValue = Integer.parseInt(binaryString, 2);
        // Cast the integer value to char
        return (char) intValue;
    }
    public String makeBinaryMultipleOf_8(String tag){
        int max  = tag.length();
        max += (8 - (max % 8 == 0 ? 8 : max % 8));// make it multiple of 8;

        StringBuilder tagBuilder = new StringBuilder(tag);
        tagBuilder.append("0".repeat(Math.max(0, max - tagBuilder.length())));
        return tagBuilder.toString();
    }
    protected byte[] toBytes(String tag) {
        tag = makeBinaryMultipleOf_8(tag);
        int max = (int)tag.length();
        int numBytes = max / 8;
        byte[] bytes = new byte[numBytes];
        for (int i = 0; i < numBytes; i++) {
            String byteString = tag.substring(i * 8, (i + 1) * 8);
            bytes[i] = (byte) Integer.parseInt(byteString, 2);
        }
        return bytes;
    }
}
