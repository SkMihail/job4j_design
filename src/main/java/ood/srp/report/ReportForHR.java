package ood.srp.report;

import ood.srp.model.Employee;
import ood.srp.store.Store;

import java.util.Comparator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ReportForHR implements Report {

    private final Store store;
    private final Comparator<Employee> comparator = (o1, o2) -> Double.compare(o2.getSalary(), o1.getSalary());

    public ReportForHR(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {

        StringBuilder text = new StringBuilder();
        text.append("Name; Salary")
                .append(System.lineSeparator())
                .append(store.findBy(filter).stream()
                        .sorted(comparator)
                        .map(employee -> employee.getName() + " " + employee.getSalary())
                        .collect(Collectors.joining(System.lineSeparator())))
                .append(System.lineSeparator());
        return text.toString();
    }
}
