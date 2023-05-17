package ood.lsp.controlquality;

import java.time.LocalDateTime;
import java.util.function.Predicate;

import static java.time.temporal.ChronoUnit.SECONDS;

public class AcceptorByFresh implements StorageAcceptor {
    Predicate<Integer> tester;

    public AcceptorByFresh(Predicate<Integer> tester) {
        this.tester = tester;
    }

    @Override
    public boolean check(Food food) {
        return tester.test(freshnessInPercent(food));
    }
    private int freshnessInPercent(Food food) {
        LocalDateTime now = LocalDateTime.now();
        long foodExist = SECONDS.between(food.getCreateDate(), now);
        long foodCanExist = SECONDS.between(food.getCreateDate(), food.getExpiryDate());
        return (int) ((foodCanExist - foodExist) * 100 / foodCanExist);
    }
}
