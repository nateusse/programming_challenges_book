import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class The3n1 {

    public static void main(String[] args) throws IOException {
        The3n1.problem();
    }

    private static void problem() throws IOException {
        Path file = Path.of("resources/3n+1");
        List<String> lines = Files.readAllLines(file);

        for (String line : lines) {
            String[] lineValues = line.split(" ");
            int start = Integer.parseInt(lineValues[0]);
            int end = Integer.parseInt(lineValues[1]);
            int accumulator = 0;

            for (; start <= end; start++) {
                int currentAccumulator = execRecursive(start, 0);

                if (currentAccumulator > accumulator) {
                    accumulator = currentAccumulator;
                }
            }

            System.out.println(line + " " + accumulator);
        }
    }

    private static int execRecursive(int value, int result) {
        if (value == 1) {
            return ++result;
        }

        if (value % 2 == 0) {
            return execRecursive(value / 2, ++result);
        } else {
            return execRecursive((value * 3) + 1, ++result);
        }
    }

}
