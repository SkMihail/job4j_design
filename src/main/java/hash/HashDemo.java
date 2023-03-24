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
                && intField == hashDemo.intField
                && booleanField == hashDemo.booleanField
                && longField == hashDemo.longField
                && charField == hashDemo.charField
                && Float.compare(hashDemo.floatField, floatField) == 0
                && Double.compare(hashDemo.doubleField, doubleField) == 0
                && Objects.equals(stringField, hashDemo.stringField);
    }

    @Override
    public int hashCode() {
        if (hashCode == 0) {
            int result = 1;
            result = 31 * result + stringField.hashCode();
            result = 31 * result + (booleanField ? 1 : 0);
            result = 31 * result + intField;
            result = 31 * result + byteField;
            result = 31 * result + shortField;
            result = 31 * result + charField;
            result = 31 * result + (int) (longField >>> 32);
            result = 31 * result + Float.floatToIntBits(floatField);
            result = 31 * (int) (result + Double.doubleToLongBits(doubleField) >>> 32);
            hashCode = result;
        }
        return hashCode;
    }
}
