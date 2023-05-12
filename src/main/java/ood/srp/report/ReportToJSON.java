package ood.srp.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ood.srp.model.Employee;
import ood.srp.store.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ReportToJSON implements Report {
    private final Store store;
    private final Gson gson;

    public ReportToJSON(Store store) {
        this.store = store;
        gson = new GsonBuilder().create();
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> result = new ArrayList<>(store.findBy(filter));
        return gson.toJson(result);
    }
}
