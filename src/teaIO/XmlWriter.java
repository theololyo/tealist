package teaIO;

import java.io.File;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import tealist.Tea;

/**
 * Writes the tea information to a xml file.
 *
 * @author Markovic
 */
public class XmlWriter implements TeaWriter {

    /**
     * Writes the tea information to a xml file. The xml file will be saved with
     * a root element named tealist and contains elements named tea with sub
     * elements that has the tags category, name, price and description.
     *
     * @param teaList A list of the tea
     * @param fileName Name of the file, if it is null the file will be written
     * to system.out
     * @throws Exception If there was an error while constructing or writing the
     * file
     */
    @Override
    public void writeFile(List<Tea> teaList, String fileName) throws Exception {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement(Tea.TEALIST);
        doc.appendChild(rootElement);

        for (Tea tea : teaList) {
            Element teaElement = doc.createElement(Tea.TEA);
            rootElement.appendChild(teaElement);

            Element categoryElement = doc.createElement(Tea.CATEGORY);
            categoryElement.appendChild(doc.createTextNode(tea.category));
            teaElement.appendChild(categoryElement);

            Element nameElement = doc.createElement(Tea.NAME);
            nameElement.appendChild(doc.createTextNode(tea.name));
            teaElement.appendChild(nameElement);

            Element priceElement = doc.createElement(Tea.PRICE);
            priceElement.appendChild(doc.createTextNode("" + tea.price));
            teaElement.appendChild(priceElement);

            Element descriptionElement = doc.createElement(Tea.DESCRIPTION);
            descriptionElement.appendChild(doc.createTextNode(tea.description));
            teaElement.appendChild(descriptionElement);
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result;

        if (fileName == null) {
            result = new StreamResult(System.out);
        } else {
            result = new StreamResult(new File(fileName));
        }

        transformer.transform(source, result);
    }

}
