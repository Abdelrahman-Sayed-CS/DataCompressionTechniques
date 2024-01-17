package com.example.huffmanstandard;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
public class HuffmanAlgo {
    private Map<Character, String> huffmanCodes;
    private Map<String, Character>huffmanCodesCompressed;
    private  Map<Character, Integer>frequencyMap;
    private String compressedText;
    public HuffmanAlgo()
    {
        compressedText = "";
        frequencyMap = new HashMap<>();
        huffmanCodes = new HashMap<>();
    }
    public HuffmanAlgo(String s)
    {
        compressedText = s;
        huffmanCodesCompressed = new HashMap<>();
    }
    public String getCompressedText(){
        return compressedText;
    }
    //  to get freq of each character in text
    private Map<Character, Integer> buildFrequencyMap(String input) {
        for (char c : input.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }
        for (Map.Entry<Character, Integer> entry: frequencyMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        return frequencyMap;
    }
    //    to build huffman tree
    private  HuffmanNode buildHuffmanTree(Map<Character, Integer> frequencyMap) {
        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            priorityQueue.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        for (HuffmanNode element : priorityQueue) {
            System.out.println(element.data + " : " + element.frequency);
        }
//        5- > left 9 -> right
        while (priorityQueue.size() > 1) {
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();
            HuffmanNode internalNode = new HuffmanNode('\0', left.frequency + right.frequency);
            internalNode.left = left;
            internalNode.right = right;
            priorityQueue.add(internalNode);
        }
//       this return root of huffman tree
        return priorityQueue.poll();
    }
    private void buildHuffmanCodes(HuffmanNode root, String code, Map<Character, String>huffmanCodes){
        if(root == null)return;
        if(root.data != '\0'){
            huffmanCodes.put(root.data, code);
        }
//        one call for left
        buildHuffmanCodes(root.left, code+"0", huffmanCodes);
//        one call for right
        buildHuffmanCodes(root.right, code+"1", huffmanCodes);
    }
    public Map<Character, Integer> compress(String t) {
        frequencyMap = buildFrequencyMap(t);
        HuffmanNode root = buildHuffmanTree(frequencyMap);
        buildHuffmanCodes(root, "", huffmanCodes);
        for (int i = 0; i < t.length(); i++) {
            compressedText += huffmanCodes.get(t.charAt(i));
        }
        for (Map.Entry<Character, String> entry:huffmanCodes.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        return frequencyMap;
    }
    public  String decompress(Map<Character, Integer>freqMap, String stream){

//        first build tree
        frequencyMap = freqMap;
        buildHuffmanCodes(buildHuffmanTree(frequencyMap), "", huffmanCodes);
        int l = 0, r = 1;
        StringBuilder originalText = new StringBuilder();
        Map<String, Character>huffmanCodeKey = new HashMap<>();
        for (Map.Entry<Character, String> entry :huffmanCodes.entrySet()) {
            huffmanCodeKey.put(entry.getValue(), entry.getKey());
        }
        while(l < stream.length() && r < stream.length()){
            if(huffmanCodeKey.containsKey(stream.substring(l, r))) {
                originalText.append(huffmanCodeKey.get(stream.substring(l, r)));
                l = r;
                r++;
            }
            else r++;
        }
        if(huffmanCodeKey.containsKey(stream.substring(l, r)))
            originalText.append(huffmanCodeKey.get(stream.substring(l, r)));
        return originalText.toString();
    }
}
