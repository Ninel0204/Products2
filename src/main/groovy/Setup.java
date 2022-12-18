import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Setup {
    private String lEnabled;
    private String lFileName;
    private String lFormat;
    private String sEnabled;
    private String sFileName;
    private String sFormat;
    private String loEnabled;
    private String loFileName;

    public Setup(File setupfile) {
        importXMLElements(setupfile);
    }
    public String[] getElement(Document document, String getElementsByTagName) {
        String[] elements = new String[3];
        NodeList nodeList = document.getElementsByTagName(getElementsByTagName);
        Node node = nodeList.item(0);
        if (Node.ELEMENT_NODE == node.getNodeType()) {
            Element element = (Element) node;
            elements[0] = element.getElementsByTagName("enabled").item(0).getTextContent();
            elements[1] = element.getElementsByTagName("fileName").item(0).getTextContent();
            if (element.getChildNodes().item(5) != null) {
                elements[2] = element.getElementsByTagName("format").item(0).getTextContent();
            }
        }
        return elements;
    }

    public String[][] importXMLElements(File file) {
        String[][] elementsArray = new String[3][];
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = (Document) documentBuilder.parse(file);

            elementsArray[0] = getElement(document, "load");
            elementsArray[1] = getElement(document, "save");
            elementsArray[2] = getElement(document, "log");

            lEnabled = elementsArray[0][0];
            lFileName = elementsArray[0][1];
            lFormat = elementsArray[0][2];
            sEnabled = elementsArray[1][0];
            sFileName = elementsArray[1][1];
            sFormat = elementsArray[1][2];
            loEnabled = elementsArray[2][0];
            loFileName = elementsArray[2][1];

        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
        return elementsArray;
    }

    public String getLEnabled() {
        return lEnabled;
    }

    public void setLEnabled(String lEnabled) {
        this.lEnabled = lEnabled;
    }

    public String getLFileName() {
        return lFileName;
    }

    public void setLFileName(String lFileName) {
        this.lFileName = lFileName;
    }

    public String getLFormat() {
        return lFormat;
    }

    public void setLFormat(String lFormat) {
        this.lFormat = lFormat;
    }

    public String getSEnabled() {
        return sEnabled;
    }

    public void setSEnabled(String sEnabled) {
        this.sEnabled = sEnabled;
    }

    public String getSFileName() {
        return sFileName;
    }

    public void setSaveFileName(String sFileName) {
        this.sFileName = sFileName;
    }

    public String getSFormat() {
        return sFormat;
    }

    public void setSFormat(String sFormat) {
        this.sFormat = sFormat;
    }

    public String getLoEnabled() {
        return loEnabled;
    }

    public void setLogEnabled(String loEnabled) {
        this.loEnabled = loEnabled;
    }

    public String getLoFileName() {
        return loFileName;
    }

    public void setLoFileName(String loFileName) {
        this.loFileName = loFileName;
    }
}

