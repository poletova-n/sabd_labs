package lab1;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.*;
import java.util.List;

public class EmployeeParser {
    private static boolean obfuscate;

    private static List<Employee> xmlToClasses(File file) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        EmployeeHandler handler = new EmployeeHandler();
        saxParser.parse(file, handler);
        return handler.getEmpList();
    }

    private static String change(String str) {
        return obfuscate ? EmployeeObfuscator.obfuscate(str) : EmployeeObfuscator.deobfuscate(str);
    }

    public static void makeFile(File inputFile, File outputFile) throws IOException, SAXException, ParserConfigurationException, XMLStreamException {
        List<Employee> employees = xmlToClasses(inputFile);

        StringWriter stringWriter = new StringWriter();

        XMLOutputFactory xMLOutputFactory = XMLOutputFactory.newInstance();
        XMLStreamWriter xMLStreamWriter =
                xMLOutputFactory.createXMLStreamWriter(stringWriter);

        xMLStreamWriter.writeStartDocument();
        xMLStreamWriter.writeStartElement("employees");
        employees.forEach(element -> {
            try {
                xMLStreamWriter.writeStartElement("employee");
                xMLStreamWriter.writeAttribute("id", change(element.getId()));

                xMLStreamWriter.writeStartElement("firstName");
                xMLStreamWriter.writeCharacters(change(element.getFirstName()));
                xMLStreamWriter.writeEndElement();

                xMLStreamWriter.writeStartElement("lastName");
                xMLStreamWriter.writeCharacters(change(element.getLastName()));
                xMLStreamWriter.writeEndElement();

                xMLStreamWriter.writeStartElement("location");
                xMLStreamWriter.writeCharacters(change(element.getLocation()));
                xMLStreamWriter.writeEndElement();

                xMLStreamWriter.writeEndElement();
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }
        });
        xMLStreamWriter.writeEndElement();
        xMLStreamWriter.writeEndDocument();

        xMLStreamWriter.flush();
        xMLStreamWriter.close();

        BufferedWriter bwr = new BufferedWriter(new FileWriter(outputFile));
        bwr.write(stringWriter.toString());
        stringWriter.close();
        bwr.flush();
        bwr.close();
    }

    public static void setObfuscate(boolean obfuscate) {
        EmployeeParser.obfuscate = obfuscate;
    }
}
