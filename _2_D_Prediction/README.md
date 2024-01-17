# 2_D_Prediction
## Performing 2D prediction involves predicting pixel values in an image or video based on the values of neighboring pixels. The specific steps may vary depending on the type of prediction method used, but here is a general outline for a basic predictive coding scheme:

1) Choose a Prediction Model:<br>
Select a prediction model that defines how the predicted value of a pixel is calculated based on its neighboring pixels. Common models include linear prediction, median prediction, or adaptive models based on machine learning.<br>

2) Select a Prediction Block Size:<br>
Divide the image or video into blocks or macroblocks. The prediction will be applied to each block independently. The size of the block may depend on the specific compression standard or application.<br>

3) Apply Prediction:<br>
For each block, apply the chosen prediction model to predict the pixel values within the block. The prediction may involve neighboring pixels in the same block or may extend to neighboring blocks.<br>

4) Calculate Prediction Residuals:<br>
Calculate the prediction residuals by subtracting the predicted values from the actual pixel values. These residuals represent the differences between the predicted and actual pixel values.<br>

5) Quantization:<br>
Quantize the prediction residuals to reduce the precision of the values. This step helps to further reduce the amount of data that needs to be transmitted or stored. The quantization step is a trade-off between compression and the quality of the reconstructed image.<br>

6) Entropy Coding:<br>
Apply entropy coding to encode the quantized residuals. Common entropy coding techniques include Huffman coding or arithmetic coding. This step reduces the redundancy in the data and achieves further compression.<br>

7) Transmission or Storage:<br>
Transmit or store the encoded residuals along with any necessary side information (e.g., information about the prediction model used). This constitutes the compressed representation of the image or video.<br>

8) Decoding:<br>
At the receiver or during playback, decode the transmitted or stored data. Reverse the entropy coding to obtain the quantized residuals.<br>

9) Inverse Quantization:<br>
Apply inverse quantization to recover the quantized residuals. This step restores some of the precision lost during quantization.<br>

10) Inverse Prediction:<br>
Apply the inverse of the prediction model to the predicted values and add the inverse quantized residuals to obtain the reconstructed pixel values.<br>