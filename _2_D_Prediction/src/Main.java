import java.io.IOException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws IOException {
        Controller c = new Controller(new ImageUtil(), new Prediction());

//        c.CompressImage("I:\\VSprojects\\ProjectsOfThirdYear\\DataCom\\a.jpg", "I:\\VSprojects\\ProjectsOfThirdYear\\DataCom\\binary.bin");
        c.decompress(c.CompressImage("I:\\VSprojects\\ProjectsOfThirdYear\\DataCom\\a.jpg", "I:\\VSprojects\\ProjectsOfThirdYear\\DataCom\\binary.bin"),
        "I:\\VSprojects\\ProjectsOfThirdYear\\DataCom\\aaa.png");
    }
}