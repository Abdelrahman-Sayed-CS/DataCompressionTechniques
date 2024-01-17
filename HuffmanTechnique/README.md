# GUI Huffman Technique

The Huffman algorithm is a widely used compression algorithm developed by David A. Huffman in 1952. It operates by assigning variable-length codes to input characters, with shorter codes for more frequent characters. The algorithm involves the following steps:

1. **Frequency Calculation:** Determine the frequency of each character in the input data.

2. **Priority Queue:** Create a priority queue (min-heap) based on character frequencies.

3. **Build Huffman Tree:** Create a tree by repeatedly combining the two nodes with the lowest frequencies until only one node remains.

4. **Assign Codes:** Traverse the tree to assign binary codes to each character.

5. **Compression:** Replace each character in the input data with its corresponding Huffman code.

6. **Generate Huffman Codes Table (Optional):** Create a table mapping characters to their Huffman codes for use during decompression.

This process results in a compressed representation of the input data, with shorter codes for more frequently occurring characters.

please change the directory of bin file in compress process and original file in decompress process in HelloApplication.java that contain GUI.
