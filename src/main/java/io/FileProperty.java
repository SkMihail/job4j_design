package io;

record FileProperty(long size, String name) {

    @Override
    public String toString() {
        return "Файл: "
                + "name='" + name + '\''
                + " size=" + size + "B";
    }
}
