package lab1;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class EmployeeHandler extends DefaultHandler {

    private List<Employee> empList = null;
    private Employee emp = null;
    private StringBuilder data = null;

    List<Employee> getEmpList() {
        return empList;
    }

    private boolean bFirstName = false;
    private boolean bLastName = false;
    private boolean bLocation = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        if (qName.equalsIgnoreCase("Employee")) {
            String id = attributes.getValue("id");
            emp = new Employee();
            emp.setId(id);
            if (empList == null)
                empList = new ArrayList<>();
        } else if (qName.equalsIgnoreCase("FirstName")) {
            // set boolean values for fields, will be used in setting Employee variables
            bFirstName = true;
        } else if (qName.equalsIgnoreCase("LastName")) {
            bLastName = true;
        } else if (qName.equalsIgnoreCase("Location")) {
            bLocation = true;
        }
        // create the data container
        data = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (bFirstName) {
            emp.setFirstName(data.toString());
            bFirstName = false;
        } else if (bLastName) {
            emp.setLastName(data.toString());
            bLastName = false;
        } else if (bLocation) {
            emp.setLocation(data.toString());
            bLocation = false;
        }

        if (qName.equalsIgnoreCase("Employee")) {
            // add Employee object to list
            empList.add(emp);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) {
        data.append(new String(ch, start, length));
    }

}
