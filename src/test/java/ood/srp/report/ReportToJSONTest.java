package ood.srp.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ood.srp.model.Employee;
import ood.srp.store.MemStore;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ReportToJSONTest {

    @Test
    public void whenReportGeneratedGSON() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Fedor", now, now, 300);
        List<Employee> list = new ArrayList<>();
        store.add(worker);
        store.add(worker2);
        list.add(worker);
        list.add(worker2);
        Report engine = new ReportToJSON(store);
        Gson gson = new GsonBuilder().create();
        String expect = gson.toJson(list);
        assertThat(engine.generate(em -> true)).isEqualTo(expect);
    }
}