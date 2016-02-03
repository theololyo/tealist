package teaIO;

import java.util.List;
import tealist.Tea;

/**
 * Writes information about tea to various data sources
 *
 * @author Markovic
 */
public interface TeaWriter {

    /**
     * Writes information about tea to various data sources
     *
     * @param teaList the tea objects to write
     * @param fileName the desired filename
     * @throws Exception if something goes wrong with writing the file
     */
    public void writeFile(List<Tea> teaList, String fileName) throws Exception;

}
