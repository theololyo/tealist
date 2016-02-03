package teaIO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import tealist.Tea;

/**
 * parses tea information from a xml file.
 *
 * @author Markovic
 */
public class XmlParser extends TeaParser {

    /**
     * parses tea information from a xml file. The xml file must have elements
     * named tea containing sub elements with the tags category, name, price and
     * description.
     *
     * @param fileName Name of the file
     * @return A list of the tea in the file
     * @throws IOException If there was a I/O error
     */
    @Override
    protected List<Tea> parseFile(File file) throws IOException {
        List<Tea> teaList = new ArrayList<Tea>();
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName(Tea.TEA);

            for (int i = 0; i < nodeList.getLength(); i++) {

                Tea tea = new Tea();
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    tea.category = getTagValue(Tea.CATEGORY, element);
                    tea.name = getTagValue(Tea.NAME, element);
                    tea.price = Integer.valueOf(getTagValue(Tea.PRICE, element));
                    tea.description = getTagValue(Tea.DESCRIPTION, element);
                }
                teaList.add(tea);
            }
        } catch (Exception e) {
            throw new IOException("Input file (" + file.getName() + ") not correct format");
        }
        return teaList;
    }

    /**
     * Gets a value from an element
     *
     * @param tag The tag we are looking for
     * @param element The current element
     * @return The value of the tag
     */
    private String getTagValue(String tag, Element element) {
        NodeList nlList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);

        return nValue.getNodeValue();
    }

}
