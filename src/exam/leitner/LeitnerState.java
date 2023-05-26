package exam.leitner;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.*;

public class LeitnerState {
    private static final Path defaultSavePath = Path.of("save.lth");
    private static final int minBucket = 1;
    private static final int maxBucket = 10;

    private final Map<String, Integer> bucket;

    public LeitnerState() {
        bucket = new HashMap<>();
    }

    public void load() throws IOException {
        load(defaultSavePath);
    }

    public void load(Path file) throws IOException {
        try (Scanner scanner = new Scanner(file)) {
            bucket.clear();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split(" ");
                bucket.put(words[0], Integer.parseInt(words[1]));
            }
        }
    }

    public void save() throws IOException {
        save(defaultSavePath);
    }

    public void save(Path file) throws IOException {
        try (PrintWriter pw = new PrintWriter(file.toFile())) {
            for (Map.Entry<String, Integer> e : bucket.entrySet()) {
                pw.println(e.getKey() + " " + e.getValue());
            }
        }
    }

    public int getBucket(String word) {
        return bucket.getOrDefault(word, minBucket);
    }

    public void setBucket(String word, int bucketId) {
        bucket.put(word, bucketId);
    }

    public void incBucket(String word) {
        setBucket(word, Math.min(getBucket(word) + 1, maxBucket));
    }

    public void resetBucket(String word) {
        setBucket(word, minBucket);
    }
}
