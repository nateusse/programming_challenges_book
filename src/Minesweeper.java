import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Minesweeper {

    private static final String ASTERISK = "*";

    public static void main(String[] args) throws IOException {
        Minesweeper.problem();
    }

    private static void problem() throws IOException {
        Path file = Path.of("resources/minesweeper");
        List<String> lines = Files.readAllLines(file);
        int fieldsCount = 0;

        String principalLine;
        String currentLine;
        String nextLine;
        String previousLine;
        String[] currentLineSplit;
        String[] nextLineSplit;
        String[] previousLineSplit;

        String currentValue;
        String leftTop;
        String leftCenter;
        String leftBottom;
        String topCenter;
        String bottomCenter;
        String rightTop;
        String rightCenter;
        String rightBottom;

        for (int fieldsIterator = 0; fieldsIterator < lines.size(); fieldsIterator++) {
            principalLine = lines.get(fieldsIterator);
            String[] lineValues = principalLine.split(" ");
            int verticalValues = Integer.parseInt(lineValues[0]);
            int horizontalValues = Integer.parseInt(lineValues[1]);

            if (verticalValues == 0 && horizontalValues == 0) {
                break;
            }

            fieldsCount++;
            print("Field #" + fieldsCount + ":");

            for (int verticalIterator = 0; verticalIterator < verticalValues; verticalIterator++) {
                fieldsIterator++;
                currentLine = lines.get(fieldsIterator);
                currentLineSplit = currentLine.split("");
                int resultCounter;
                StringBuilder horizontalResult = new StringBuilder();

                for (int horizontalIterator = 0; horizontalIterator < horizontalValues; horizontalIterator++) {
                    currentValue = currentLineSplit[horizontalIterator];

                    if (currentValue.equals(ASTERISK)) {
                        horizontalResult.append(ASTERISK);
                        finalIteration(horizontalValues, horizontalResult, horizontalIterator);
                        continue;
                    }

                    resultCounter = 0;

                    if (verticalIterator == 0) {
                        nextLine = lines.get(fieldsIterator + 1);
                        nextLineSplit = nextLine.split("");

                        if (horizontalIterator == 0) {
                            rightCenter = currentLineSplit[horizontalIterator + 1];
                            resultCounter += evaluateAdjacent(rightCenter);

                            rightBottom = nextLineSplit[horizontalIterator + 1];
                            resultCounter += evaluateAdjacent(rightBottom);

                            bottomCenter = nextLineSplit[horizontalIterator];
                            resultCounter += evaluateAdjacent(bottomCenter);

                            horizontalResult.append(resultCounter);
                        } else if (horizontalIterator < horizontalValues - 1) {
                            rightCenter = currentLineSplit[horizontalIterator + 1];
                            resultCounter += evaluateAdjacent(rightCenter);

                            rightBottom = nextLineSplit[horizontalIterator + 1];
                            resultCounter += evaluateAdjacent(rightBottom);

                            bottomCenter = nextLineSplit[horizontalIterator];
                            resultCounter += evaluateAdjacent(bottomCenter);

                            leftBottom = nextLineSplit[horizontalIterator - 1];
                            resultCounter += evaluateAdjacent(leftBottom);

                            leftCenter = currentLineSplit[horizontalIterator - 1];
                            resultCounter += evaluateAdjacent(leftCenter);

                            horizontalResult.append(resultCounter);
                        } else {
                            bottomCenter = nextLineSplit[horizontalIterator];
                            resultCounter += evaluateAdjacent(bottomCenter);

                            leftBottom = nextLineSplit[horizontalIterator - 1];
                            resultCounter += evaluateAdjacent(leftBottom);

                            leftCenter = currentLineSplit[horizontalIterator - 1];
                            resultCounter += evaluateAdjacent(leftCenter);

                            horizontalResult.append(resultCounter);
                        }
                    } else if (verticalIterator < verticalValues - 1) {
                        previousLine = lines.get(fieldsIterator - 1);
                        previousLineSplit = previousLine.split("");

                        nextLine = lines.get(fieldsIterator + 1);
                        nextLineSplit = nextLine.split("");

                        if (horizontalIterator == 0) {
                            topCenter = previousLineSplit[horizontalIterator];
                            resultCounter += evaluateAdjacent(topCenter);

                            rightTop = previousLineSplit[horizontalIterator + 1];
                            resultCounter += evaluateAdjacent(rightTop);

                            rightCenter = currentLineSplit[horizontalIterator + 1];
                            resultCounter += evaluateAdjacent(rightCenter);

                            rightBottom = nextLineSplit[horizontalIterator + 1];
                            resultCounter += evaluateAdjacent(rightBottom);

                            bottomCenter = nextLineSplit[horizontalIterator];
                            resultCounter += evaluateAdjacent(bottomCenter);

                            horizontalResult.append(resultCounter);
                        } else if (horizontalIterator < horizontalValues - 1) {
                            topCenter = previousLineSplit[horizontalIterator];
                            resultCounter += evaluateAdjacent(topCenter);

                            rightTop = previousLineSplit[horizontalIterator + 1];
                            resultCounter += evaluateAdjacent(rightTop);

                            rightCenter = currentLineSplit[horizontalIterator + 1];
                            resultCounter += evaluateAdjacent(rightCenter);

                            rightBottom = nextLineSplit[horizontalIterator + 1];
                            resultCounter += evaluateAdjacent(rightBottom);

                            bottomCenter = nextLineSplit[horizontalIterator];
                            resultCounter += evaluateAdjacent(bottomCenter);

                            leftBottom = nextLineSplit[horizontalIterator - 1];
                            resultCounter += evaluateAdjacent(leftBottom);

                            leftCenter = currentLineSplit[horizontalIterator - 1];
                            resultCounter += evaluateAdjacent(leftCenter);

                            leftTop = previousLineSplit[horizontalIterator - 1];
                            resultCounter += evaluateAdjacent(leftTop);

                            horizontalResult.append(resultCounter);
                        } else {
                            topCenter = previousLineSplit[horizontalIterator];
                            resultCounter += evaluateAdjacent(topCenter);

                            bottomCenter = nextLineSplit[horizontalIterator];
                            resultCounter += evaluateAdjacent(bottomCenter);

                            leftBottom = nextLineSplit[horizontalIterator - 1];
                            resultCounter += evaluateAdjacent(leftBottom);

                            leftCenter = currentLineSplit[horizontalIterator - 1];
                            resultCounter += evaluateAdjacent(leftCenter);

                            leftTop = previousLineSplit[horizontalIterator - 1];
                            resultCounter += evaluateAdjacent(leftTop);

                            horizontalResult.append(resultCounter);
                        }
                    } else {
                        previousLine = lines.get(fieldsIterator - 1);
                        previousLineSplit = previousLine.split("");

                        if (horizontalIterator == 0) {
                            topCenter = previousLineSplit[horizontalIterator];
                            resultCounter += evaluateAdjacent(topCenter);

                            rightTop = previousLineSplit[horizontalIterator + 1];
                            resultCounter += evaluateAdjacent(rightTop);

                            rightCenter = currentLineSplit[horizontalIterator + 1];
                            resultCounter += evaluateAdjacent(rightCenter);

                            horizontalResult.append(resultCounter);
                        } else if (horizontalIterator < horizontalValues - 1) {
                            topCenter = previousLineSplit[horizontalIterator];
                            resultCounter += evaluateAdjacent(topCenter);

                            rightTop = previousLineSplit[horizontalIterator + 1];
                            resultCounter += evaluateAdjacent(rightTop);

                            rightCenter = currentLineSplit[horizontalIterator + 1];
                            resultCounter += evaluateAdjacent(rightCenter);

                            leftCenter = currentLineSplit[horizontalIterator - 1];
                            resultCounter += evaluateAdjacent(leftCenter);

                            leftTop = previousLineSplit[horizontalIterator - 1];
                            resultCounter += evaluateAdjacent(leftTop);

                            horizontalResult.append(resultCounter);
                        } else {
                            topCenter = previousLineSplit[horizontalIterator];
                            resultCounter += evaluateAdjacent(topCenter);

                            leftCenter = currentLineSplit[horizontalIterator - 1];
                            resultCounter += evaluateAdjacent(leftCenter);

                            leftTop = previousLineSplit[horizontalIterator - 1];
                            resultCounter += evaluateAdjacent(leftTop);

                            horizontalResult.append(resultCounter);
                        }
                    }

                    finalIteration(horizontalValues, horizontalResult, horizontalIterator);
                }

                if (verticalIterator == verticalValues - 1) {
                    print("");
                }
            }
        }
    }

    private static void finalIteration(int horizontalValues, StringBuilder horizontalResult, int horizontalIterator) {
        if (horizontalIterator == horizontalValues - 1) {
            print(horizontalResult.toString());
            horizontalResult.setLength(0);
        }
    }

    private static int evaluateAdjacent(String value) {
        if (value.equals(ASTERISK)) {
            return 1;
        }

        return 0;
    }

    private static void print(String value) {
        System.out.println(value);
    }

}