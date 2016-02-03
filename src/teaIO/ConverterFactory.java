package teaIO;

import java.util.HashMap;
import java.util.Map;
import tealist.FileFormats;

/**
 * Factory responsible for supplying client with a requested parser or writer
 *
 * @author Markovic
 */
public class ConverterFactory {

    private Map<String, TeaParser> _parserMap;
    private final Map<String, TeaWriter> _writerMap;
    
    /**
     * Constructs a new ConverterFactory and sets avaliable parsers and writers
     */
    public ConverterFactory() {

        _parserMap = new HashMap<>();
        _parserMap.put(FileFormats.TEXT.toString(), new TextParser());
        _parserMap.put(FileFormats.XML.toString(), new XmlParser());

        _writerMap = new HashMap<>();
        _writerMap.put(FileFormats.TEXT.toString(), new TextWriter());
        _writerMap.put(FileFormats.XML.toString(), new XmlWriter());

    }

    /**
     * Gets a parser if the parser is present in the map
     *
     * @param fileFormat
     * @return
     */
    public TeaParser getTeaParser(String fileFormat) {
        return _parserMap.get(fileFormat);
    }

    /**
     * Gets a writer if the parser is present in the map
     *
     * @param fileFormat
     * @return
     */
    public TeaWriter getTeaWriter(String fileFormat) {
        return _writerMap.get(fileFormat);
    }
}
