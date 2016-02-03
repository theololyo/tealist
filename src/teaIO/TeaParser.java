package teaIO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import tealist.Tea;

/**
 * Reads and parses various file formats containing information about tea
 *
 * @author Markovic
 */
public abstract class TeaParser {

    protected abstract List<Tea> parseFile(File file) throws IOException;

    /**
     * Extracts information about teas from various data sources
     *
     * @param fileName
     * @return a list of read tea objects from the data source
     * @throws IOException if file reading goes wrong
     */
    public List<Tea> readFile(String fileName) throws IOException {
        return parseFile(loadFile(fileName));
    }

    private File loadFile(String fileName) throws IOException {

        List<Tea> teaList = new ArrayList<Tea>();
        File file = new File(fileName);

        if (!file.exists() || !file.isFile()) {
            throw new IOException("The file " + fileName + " does not exist");
        }

        return file;
    }
}
