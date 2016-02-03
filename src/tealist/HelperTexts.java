package tealist;

import java.util.StringJoiner;

/**
 * Contains instructions for how to use the program
 * 
 * @author Markovic
 */
public class HelperTexts {

    /**
     * Returns a string with avaliable commands and how to use them
     * 
     * @return currently avaliable help and commands
     */
    public String getHelp() {
        StringJoiner sj = new StringJoiner("\n");
        sj.add("Usage: java -jar TeaList.jar [OPTION][VALUE]");
        sj.add("Options can be given in any order");
        sj.add("");
        sj.add("Input/output format");
        sj.add("-f from file format (requires value after)");
        sj.add("-t to file format (require value after)");
        sj.add("");
        sj.add("Input/output file");
        sj.add("-i input file (requires value after)");
        sj.add("-o output file (if no value is given standard output will be used)");
        sj.add("");
        sj.add("Information");
        sj.add("-l list avaliable file formats");
        sj.add("-h print help");
        sj.add("");
        sj.add("Example of valid options and values");
        sj.add("java -jar TeaList.jar -h");
        sj.add("(prints help)");
        sj.add("java -jar TeaList.jar -f text -t xml -i tea.txt");
        sj.add("(reads a tealist in text format in tea.txt and writes it as xml to standard output)");
        sj.add("java -jar TeaList.jar -o tea.txt -i tea.xml -t txt -f xml");
        sj.add("(reads a tealist in xml format in tea.xml and writes it as text to tea.txt)");
        return sj.toString();
    }

    /**
     * Returns a list of avaliable file formats
     * 
     * @return currently avaliable filre formats
     */
    public String getFileFormats() {
        StringJoiner sj = new StringJoiner("\n");
        sj.add("TeaList 0.1 supports the following file formats");
        sj.add("text - Text (txt) file where fields are separated with ;");
        sj.add("xml - Xml (xml) file");
        return sj.toString();
    }
}
