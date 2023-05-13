package ood.lsp;

import java.util.NoSuchElementException;

/** Есть несколько наиболее распространенных нарушений принципа подстановки Liskov:
 1. Нарушение инвариантности: класс-наследник изменяет предусловия или постусловия методов базового класса.
 2. Нарушение постусловий: класс-наследник не выполняет все постусловия методов базового класса.
 3. Нарушение предусловий: класс-наследник требует более сильных предусловий, чем базовый класс.
 4. Нарушение свойств типизации: класс-наследник возвращает объекты других типов, чем базовый класс.
 5. Нарушение поведения: класс-наследник изменяет поведение метода базового класса без явного переопределения этого метода.
 6. Производные классы не должны генерировать исключения, не описанные базовым классом.
 */
public class ViolationLSP {
    protected int powerCapacity;
    protected int currentPower;
    public int getCurrentPower() {
        return currentPower;
    }
    public int setCurrentPower(int power) {
        this.currentPower = getCurrentPower() + power;
        return getCurrentPower();
    }
    public ViolationLSP(int powerCapacity) {
        this.powerCapacity = powerCapacity;
    }
    public int charging(int timeToChargeInMin) {
        if (getCurrentPower() + timeToChargeInMin * 2 > powerCapacity) {
            throw new IllegalArgumentException("Too much timeToChargeInMin, device can be damaged");
        }
        if (timeToChargeInMin < 5) {
            throw new UnsupportedOperationException("Low timeToChargeInMin - can't charge this device");
        }
        setCurrentPower(timeToChargeInMin * 2);
        return getCurrentPower();
    }
}
