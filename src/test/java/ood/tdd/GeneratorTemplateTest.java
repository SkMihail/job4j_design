package ood.tdd;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@Disabled("Тесты отключены. Удалить аннотацию после реализации всех методов по заданию.")
class GeneratorTemplateTest {

    @Test
    public void whenKeysIsMissed() {
        GeneratorTemplate gen = new GeneratorTemplate();
        Map<String, String> args = new HashMap<>();
        String template = "I am a ${name}, Who are ${subject}? ";
        assertThatThrownBy(() -> gen.produce(template, args))
                .hasMessageContaining("Number of keys in arg is 0, must be 2");
    }

    @Test
    public void whenKeysIsOtherInTemplate() {
        GeneratorTemplate gen = new GeneratorTemplate();
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr");
        args.put("subject", "you");
        String template = "I am a ${nickname}, Who are ${subject}? ";
        assertThatThrownBy(() -> gen.produce(template, args))
                .hasMessageContaining("There is no such available keys");
    }

    @Test
    public void whenNumberOfKeysIsMoreThenNeedIt() {
        GeneratorTemplate gen = new GeneratorTemplate();
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr");
        args.put("nickname", "Petr");
        args.put("subject", "you");
        String template = "I am a ${nickname}, Who are ${subject}? ";
        assertThatThrownBy(() -> gen.produce(template, args))
                .hasMessageContaining("Number of keys in arg is 3, must be 2");
    }
}