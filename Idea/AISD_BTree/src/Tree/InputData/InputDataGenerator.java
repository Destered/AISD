package Tree.InputData;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class InputDataGenerator {
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String PUNCTUATION = "!@$%&*()_+-=[]|,./?><";

    private static HashSet<String> genStr(int count) {
        HashSet<String> word = new HashSet<>();
        for (int i = 0; i < count; i++) {

            while (true) {
                StringBuilder answer = new StringBuilder();
                for (int j = 0; j < 36; j++) {
                    int choose = (int) (Math.random() * 4);
                    int random;
                    switch (choose) {
                        case 0: {
                            random = (int) (Math.random() * LOWER.length());
                            answer.append(LOWER.charAt(random));
                            break;
                        }
                        case 1: {
                            random = (int) (Math.random() * UPPER.length());
                            answer.append(UPPER.charAt(random));
                            break;
                        }
                        case 2: {
                            random = (int) (Math.random() * DIGITS.length());
                            answer.append(DIGITS.charAt(random));
                            break;
                        }
                        case 3: {
                            random = (int) (Math.random() * PUNCTUATION.length());
                            answer.append(PUNCTUATION.charAt(random));
                            break;
                        }
                    }
                }
                if (word.add(answer + "")) break;
            }
        }
        return word;
    }

    public static void main(String[] args) throws IOException {
        HashMap<Integer, String> hm = new HashMap<>();
        for (int j = 1; j < 101; j++) {
            hm.clear();
            int count = (int) (Math.random() * 9901 + 100);
            HashSet<String> str = genStr(count);
            FileWriter fw = new FileWriter("./src/Tree/InputData/DataPack_" + j);
            for (String s : str) {
                int modifer = (int) (Math.random() * 70000) + 10000;
                while (hm.containsKey(modifer)) {
                    modifer = (int) (Math.random() * 70000) + 10000;
                }
                hm.put(modifer, s);
            }
            for (int key : hm.keySet()) {
                String write = key + "#" + hm.get(key);
                fw.write(write + "\n");
            }
            fw.close();
        }
    }
}
