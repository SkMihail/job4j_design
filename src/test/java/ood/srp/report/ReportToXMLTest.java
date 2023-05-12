package ood.srp.report;

import ood.srp.model.Employee;
import ood.srp.store.MemStore;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

class ReportToXMLTest {

    @Test
    public void whenReportGeneratedXML() throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Fedor", now, now, 300);
        store.add(worker);
        store.add(worker2);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        String formattedDate = dateFormat.format(now.getTime());
        Report engine = new ReportToXML(store);
        String expect = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                + "<employees>\n"
                + "    <employee>\n"
                + "        <name>" + worker.getName() + "</name>\n"
                + "        <hired>" + formattedDate + "</hired>\n"
                + "        <fired>" + formattedDate + "</fired>\n"
                + "        <salary>" + worker.getSalary() + "</salary>\n"
                + "    </employee>\n"
                + "    <employee>\n"
                + "        <name>" + worker2.getName() + "</name>\n"
                + "        <hired>" + formattedDate + "</hired>\n"
                + "        <fired>" + formattedDate + "</fired>\n"
                + "        <salary>" + worker2.getSalary() + "</salary>\n"
                + "    </employee>\n"
                + "</employees>\n";
        assertThat(engine.generate(em -> true)).isEqualTo(expect);
    }
}