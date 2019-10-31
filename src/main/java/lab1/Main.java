package lab1;

import java.io.File;
import java.io.IOException;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;

public class Main {
    static String inputXml = "src/main/java/lab1/xml/text.xml";
    static String encryptedXml = "src/main/java/lab1/xml/encrypted_text.xml";
    static String decryptedXml = "src/main/java/lab1/xml/result_text.xml";

    public static void main(String[] args) throws SAXException, XMLStreamException, ParserConfigurationException, IOException {
        //Read xml file
        File file = new File(inputXml);
        File encryptedFile = new File(encryptedXml);
        File decryptedFile = new File(decryptedXml);

        EmployeeParser.setObfuscate(true);
        EmployeeParser.makeFile(file, encryptedFile);
        EmployeeParser.setObfuscate(false);
        EmployeeParser.makeFile(encryptedFile, decryptedFile);
    }
}
