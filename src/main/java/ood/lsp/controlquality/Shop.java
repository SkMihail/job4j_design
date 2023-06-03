package ood.lsp.controlquality;


public class Shop extends AbstractStore {

    public Shop() {
        super();
        acceptor = new AcceptorByFresh(a -> a < 75 && a > 25);
    }

    @Override
    public boolean check(Food food) {
        boolean result = false;
        if (super.check(food)) {
            result = true;
        } else if (new AcceptorByFresh((a) -> a <= 25 && a > 0).check(food)) {
            result = true;
            food.setDiscount(50);
        }
        return result;
    }
}
