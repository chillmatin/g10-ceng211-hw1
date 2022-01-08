import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;


/*
Main purpose is to be able to produce:
1) 1D array that has each line as String elements
2) 2D array that has each line split(',') string elements inside of array elements
 */
public class FileIO {
    private String pathName;
    private String text = "";
    private String[] textLines;
    private String[][] splitLines;

    FileIO(String pathName) {
        setPathName(pathName);
        setTextLines(text);
        setSplitLines(textLines);
    }


    /**
     * This method reads text and assigns
     * String value into the text variable
     *
     * @param pathName : relative path to the file
     */
    private static String readText(String pathName) {
        String text = null;
        try {
            BufferedReader inStream = new BufferedReader(new FileReader(pathName));
            String line = inStream.readLine();
            text = "";
            while (line != null) {
                text = text + line + "\n";
                line = inStream.readLine();
            }
            inStream.close();

        } catch (IOException e) {
            System.out.println("Error!");
            e.printStackTrace();

        }
        return text;
    }

    public static String concatTextsInFiles(String... pathNames) {
        String[] texts = new String[pathNames.length];
        String concatText;
        for (int i = 0; i < pathNames.length; i++) {
            texts[i] = readText(pathNames[i]);
        }

        concatText = String.join("", texts);
        return concatText;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
        setText(readText(pathName));
        setTextLines(text);
        setSplitLines(textLines);

    }

    private void setText(String text) {
        this.text = text;
    }

    /**
     * Splits raw text data with respect to \n
     * Thus creating an array of strings
     *
     * @param text : whole content of the file
     */
    private void setTextLines(String text) {
        textLines = text.split("\n");
    }

    /**
     * PRECONDITION: splitLines variable should carry value other than null or empty array
     * It should consist of array of all-same fixed size arrays
     *
     * @return : array of string arrays which can be used as arguments for instantiating class objects
     */
    public String[][] getSplitLines() {
        String[][] copiedSplitLines = new String[splitLines.length][splitLines[0].length];
        System.arraycopy(splitLines, 0, copiedSplitLines, 0, splitLines.length);
        return copiedSplitLines;

    }

    /**
     * PRECONDITIONS: textLines should consist of fixed number comma separated strings
     * i.e: [["7", "2802", "CS4", "8-Sep-15", "17-Sep-15"], ["2", 2723, "CS5", "9-Nov-18", "1-Sep-11"]
     *
     * @param textLines : array of comma separated strings
     */
    private void setSplitLines(String[] textLines) {
        int parametersPerLine = textLines[0].split(",").length;
        splitLines = new String[textLines.length][parametersPerLine];
        for (int i = 0; i < splitLines.length; i++) {
            splitLines[i] = textLines[i].split(",");
        }
    }

    @Override
    public String toString() {

        return "FileIO{" +
                "pathName='" + pathName + '\'' +
                "\ntext='" + text + '\'' +
                "\ntextLines=" + Arrays.toString(textLines) +
                "\nsplitLines=" + Arrays.deepToString(splitLines) +
                '}';
    }
}





