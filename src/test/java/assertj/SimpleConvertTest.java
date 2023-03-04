package assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1))
                .allSatisfy(e -> assertThat(e).isLowerCase())
                .allMatch(e -> {
                    for (char ch : e.toCharArray()) {
                        if (Character.isDigit(ch)) {
                            return false;
                        }
                    }
                    return true;
                });
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list).hasSize(5)
                .contains("second")
                .contains("second", Index.atIndex(1))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("four", Index.atIndex(1))
                .containsExactly("first", "second", "three", "four", "five")
                .element(2).isNotNull().isEqualTo("three");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "second", "three", "four", "five");
        assertThat(set).hasSize(5)
                .filteredOn(Objects::nonNull)
                .contains("second")
                .contains("second", "three")
                .containsAnyOf("zero", "second", "six")
                .filteredOn(e -> e.length() > 5)
                .contains("second")
                .doesNotContain("four");


    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "second", "three", "four", "five");
        assertThat(map)
                .containsValue(2)
                .containsValues(1, 2, 3, 4, 0)
                .hasSize(5)
                .containsEntry("first", 0)
                .doesNotContainEntry("fir", 0);
    }

}