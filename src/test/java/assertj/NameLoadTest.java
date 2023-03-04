package assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("collection contains no data");
    }

    @Test
    void parseEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Names array is empty");
    }

    @Test
    void parseHasNoSymbol() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("key:value"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: key:value does not contain the symbol \"=\"");
    }
    @Test
    void parseHasKey() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("=key:value"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: =key:value does not contain a key");
    }

    @Test
    void parseHasValue() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("key:value="))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: key:value= does not contain a value");
    }

}