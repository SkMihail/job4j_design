package ood.lsp.isp;

class Jigsaw extends Saw {
    public Jigsaw() {
        sawingPart = "jigsaw for wood";
    }

    @Override
    public void sharpenChain(Saw chain) {
        throw new UnsupportedOperationException("Sorry, this is no Chainsaw");
    }

    @Override
    public void changeJigsaw(Saw jigsaw) {
        System.out.println("jigsaw changed successful");
    }

    @Override
    public void alignTeeth(Saw hacksaw) {
        throw new UnsupportedOperationException("Sorry, this is no Hacksaw");
    }

    @Override
    public void changeDiskSaw(Saw disk) {
        throw new UnsupportedOperationException("Sorry, this is no Circular saw");
    }

    @Override
    public void cutWoodWithChainsaw(Saw chainsaw) {
        throw new UnsupportedOperationException("Sorry, this is no Chainsaw");
    }
}
