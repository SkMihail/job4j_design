package assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere")
                .doesNotContain("Unknown")
                .matches("Sphere");
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(20, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object")
                .contains(" ")
                .hasSizeGreaterThan(11);
    }

    @Test
    void vertexNumberIsMinus1() {
        Box box = new Box(20, 10);
        int result = box.getNumberOfVertices();
        assertThat(result).isEqualTo(-1)
                .isLessThan(0)
                .isNegative();
    }
    @Test
    void vertexNumberIs8() {
        Box box = new Box(8, 10);
        int result = box.getNumberOfVertices();
        assertThat(result).isEqualTo(8)
                .isEven()
                .isPositive();
    }

    @Test
    void isExist() {
        Box box = new Box(8, 10);
        boolean result = box.isExist();
        assertThat(result).isEqualTo(true);
    }

    @Test
    void isNotExist() {
        Box box = new Box(20, 10);
        boolean result = box.isExist();
        assertThat(result).isEqualTo(false);
    }

    @Test
    void areaOf10TetrahedronIs175Dot205() {
        Box box = new Box(4, 10);
        double result = box.getArea();
        assertThat(result).isEqualTo(173.205, withPrecision(0.001))
                .isCloseTo(200, Percentage.withPercentage(15))
                .isGreaterThan(0D);
    }
    @Test
    void areaOf4TetrahedronIs27() {
        Box box = new Box(4, 4);
        double result = box.getArea();
        assertThat(result).isEqualTo(27, withPrecision(1.0))
                .isGreaterThan(20D)
                .isNotCloseTo(1000, Percentage.withPercentage(50));

    }
}