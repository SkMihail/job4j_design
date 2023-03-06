package generics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RoleStoreTest {
    private RoleStore store;

    @BeforeEach
    public void setUp() {
        store = new RoleStore();
    }

    @Test
    void whenAddAndFindThenRoleIsHR() {
        store.add(new Role("1", "HR"));
        Role result = store.findById("1");
        assertThat(result.getRole()).isEqualTo("HR");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        store.add(new Role("1", "HR"));
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRoleIsHR() {
        store.add(new Role("1", "HR"));
        store.add(new Role("1", "Programmer"));
        Role result = store.findById("1");
        assertThat(result.getRole()).isEqualTo("HR");
    }

    @Test
    void whenReplaceThenRoleIsProgrammer() {
        store.add(new Role("1", "HR"));
        store.replace("1", new Role("1", "Programmer"));
        Role result = store.findById("1");
        assertThat(result.getRole()).isEqualTo("Programmer");
    }

    @Test
    void whenNoReplaceRoleThenNoChangeRole() {
        store.add(new Role("1", "HR"));
        store.replace("10", new Role("10", "Programmer"));
        Role result = store.findById("1");
        assertThat(result.getRole()).isEqualTo("HR");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        store.add(new Role("1", "HR"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenRoleIsHR() {
        store.add(new Role("1", "HR"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRole()).isEqualTo("HR");
    }

    @Test
    void whenReplaceOkThenTrue() {
        store.add(new Role("1", "HR"));
        boolean rsl = store.replace("1", new Role("1", "Programmer"));
        assertThat(rsl).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        store.add(new Role("1", "HR"));
        boolean rsl = store.replace("10", new Role("10", "Programmer"));
        assertThat(rsl).isFalse();
    }

    @Test
    void whenDeleteOkThenTrue() {
        store.add(new Role("1", "HR"));
        boolean rsl = store.delete("1");
        assertThat(rsl).isTrue();
    }

    @Test
    void whenDeleteNotOkThenFalse() {
        store.add(new Role("1", "HR"));
        boolean rsl = store.delete("2");
        assertThat(rsl).isFalse();
    }
}