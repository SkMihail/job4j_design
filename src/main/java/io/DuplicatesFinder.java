package io;

import java.io.IOException;

public class DuplicatesFinder {

    public static void main(String[] args) throws IOException {
        new DuplicatesVisitor().printDup("c:\\projects");
    }
}
