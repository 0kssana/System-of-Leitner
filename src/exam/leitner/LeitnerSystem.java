package exam.leitner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeitnerSystem {
    private final LeitnerState state;
    private final LeitnerData data;

    public LeitnerSystem(LeitnerData data) {
        this.data = data;
        state = new LeitnerState();
        try {
            state.load();
        } catch (IOException e) {
            System.err.println("State loading is failed: " + e.getMessage());
        }
    }

    public String getRandomWord() {
        List<String> words = new ArrayList<>();
        List<Double> rawProbs = new ArrayList<>();
        double probsSum = 0.0;
        for (String original : data.getDictionary().keySet()) {
            words.add(original);
            double prob = Math.pow(1.5, -state.getBucket(original));
            rawProbs.add(prob);
            probsSum += prob;
        }

        List<Double> probs = new ArrayList<>();
        for (double prob : rawProbs) {
            probs.add(prob / probsSum);
        }

        double rand = Math.random();
        double cur = 0.0;
        int wordIndex = 0;
        for (int i = 0; i < probs.size(); i++) {
            cur += probs.get(i);
            if (cur > rand) {
                wordIndex = i;
                break;
            }
        }
        return words.get(wordIndex);
    }

    public boolean checkWord(String original, String prediction) {
        if (data.getDictionary().get(original).equals(prediction)) {
            state.incBucket(original);
            return true;
        } else {
            state.resetBucket(original);
            return false;
        }
    }

    public void saveState() throws IOException {
        state.save();
    }
}
