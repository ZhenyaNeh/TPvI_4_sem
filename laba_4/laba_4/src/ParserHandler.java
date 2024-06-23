import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ParserHandler extends DefaultHandler {
    @Override
    public void startDocument() throws SAXException {
        System.out.println("\nНачало парсинга");
    }

    @Override
    public void endDocument() throws SAXException {

        System.out.println("\nКонец парсинга");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        String str = "";
        // получение и вывод информации об атрибутах элемента
        for (int i = 0; i < attributes.getLength(); i++) {
            str += " " + attributes.getLocalName(i) + " = " + attributes.getValue(i);
            System.out.print(str.trim());
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.print(" " + localName);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        System.out.print(new String(ch, start, length));
    }
}
