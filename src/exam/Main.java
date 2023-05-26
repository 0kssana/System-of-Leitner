package exam;

import exam.leitner.LeitnerData;
import exam.leitner.LeitnerSystem;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            LeitnerData leitnerData = new LeitnerData(Path.of("dict.lth"));
            LeitnerSystem leitnerSystem = new LeitnerSystem(leitnerData);
            try (Scanner scanner = new Scanner(System.in)) {
                while (true) {
                   String original = leitnerSystem.getRandomWord();
                   System.out.println("Need to translate \"" + original + "\"");
                   String line = scanner.nextLine();
                   if ("".equals(line)) {
                       System.out.println("Game is finished");
                       leitnerSystem.saveState();
                       break;
                   } else {
                       System.out.println("Okay, let's check");
                       boolean result = leitnerSystem.checkWord(original, line);
                       if (result) {
                           System.out.println("It's right");
                       } else {
                           System.out.println("You are wrong");
                       }
                   }
                }
           }
        } catch (IOException e) {
            System.err.println("IOError: " + e.getMessage());
        }
    }
}
