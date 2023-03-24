package hash;

import java.util.Objects;

public class HashDemo {

    private volatile int hashCode;
    private byte byteField;
    private short shortField;
    private short charField;
    private int intField;
    private boolean booleanField;
    private long longField;
    private float floatField;
    private double doubleField;
    private String stringField;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HashDemo hashDemo = (HashDemo) o;
        return byteField == hashDemo.byteField
                && shortField == hashDemo.shortField
                && charField == hashDemo.charField
                && intField == hashDemo.intField
                && booleanField == hashDemo.booleanField
                && longField == hashDemo.longField
                && Float.compare(hashDemo.floatField, floatField) == 0
                && Double.compare(hashDemo.doubleField, doubleField) == 0
                && Objects.equals(stringField, hashDemo.stringField);
    }

    @Override
    public int hashCode() {
        return Objects.hash(byteField, shortField, charField, intField, booleanField, longField, floatField, doubleField, stringField);
    }
}
