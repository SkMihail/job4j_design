package ood.lsp.controlquality;


public class Trash extends AbstractStore {
    public Trash() {
        super();
        acceptor = new AcceptorByFresh((a) -> a <= 0);
    }
}
