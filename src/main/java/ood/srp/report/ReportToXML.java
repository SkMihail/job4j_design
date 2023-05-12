package ood.srp.report;

import ood.srp.model.Employee;
import ood.srp.store.MemStore;
import ood.srp.store.Store;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.function.Predicate;

public class ReportToXML implements Report {
    private final Store store;
    private final Marshaller marshaller;

    public ReportToXML(Store store) throws JAXBException {
        this.store = store;
        JAXBContext context = JAXBContext.newInstance(Employee.class, EmployeeListWrapper.class, MemStore.class);
        marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(new EmployeeListWrapper(store.findBy(filter)), writer);
            xml = writer.getBuffer().toString();
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return xml;
    }

    @XmlRootElement(name = "employees")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class EmployeeListWrapper {
        @XmlElement(name = "employee")
        private List<Employee> employees;

        public EmployeeListWrapper(List<Employee> list) {
            employees = list;
        }

        public EmployeeListWrapper() {
        }


        public List<Employee> getEmployees() {
            return employees;
        }

        public void setEmployees(List<Employee> employees) {
            this.employees = employees;
        }
    }
}
