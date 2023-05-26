package exam.leitner;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class LeitnerData {
    private final Map<String, String> dictionary;

    public LeitnerData(Path source) throws IOException {
        try (Scanner scanner = new Scanner(source)) {
            dictionary = new HashMap<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split(" ");
                dictionary.put(words[0], words[1]);
            }
        }
    }

    public Map<String, String> getDictionary() {
        return dictionary;
    }
}
