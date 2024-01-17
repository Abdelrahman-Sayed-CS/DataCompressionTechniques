package com.example.huffmanstandard;
public class HuffmanNode implements Comparable<HuffmanNode>{
    char data;
    int frequency;
    HuffmanNode left, right;
    public HuffmanNode(char data, int frequency) {
        this.data = data;
        this.frequency = frequency;
    }
    @Override
    public int compareTo(HuffmanNode other) {
        return this.frequency - other.frequency;
    }
}