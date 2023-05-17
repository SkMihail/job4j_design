package ood.lsp.controlquality;



public class Warehouse extends AbstractStore {
    public Warehouse() {
        super();
        acceptor = new AcceptorByFresh(a -> a >= 75);
    }
}
