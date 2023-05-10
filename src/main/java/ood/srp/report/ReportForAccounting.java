package ood.srp.report;

import ood.srp.currency.Currency;
import ood.srp.currency.CurrencyConverter;
import ood.srp.formatter.DateTimeParser;
import ood.srp.model.Employee;
import ood.srp.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class ReportForAccounting implements Report {

    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    private final CurrencyConverter converter;
    private final Currency target;

    public ReportForAccounting(Store store, DateTimeParser<Calendar> dateTimeParser,
                               CurrencyConverter converter, Currency target) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.converter = converter;
        this.target = target;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(" ")
                    .append(dateTimeParser.parse(employee.getHired())).append(" ")
                    .append(dateTimeParser.parse(employee.getFired())).append(" ")
                    .append(Currency.RUB.equals(target)
                            ? employee.getSalary()
                            : converter.convert(Currency.RUB, employee.getSalary(), target))
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
