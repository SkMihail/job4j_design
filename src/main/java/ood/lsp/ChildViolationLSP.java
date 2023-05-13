package ood.lsp;

import java.util.NoSuchElementException;

/**
 * На методе charging() продемонстрированы основные нарушения LSP:
 * - нарушение постусловия: изменено минимальное время заряда ниже которого не будет заряжено устройство
 * - усиление предусловия: добавлено новое условие и изменено существующее.
 * - в конструкции if использована instanceof
 * - нарушение свойств типизации: возвращает другой тип данных.
 * - объект наследника может генерировать исключение не предусмотренное базовым классом.
 */
class ChildViolationLSP extends ViolationLSP {
    public ChildViolationLSP(int powerCapacity) {
        super(powerCapacity);
    }

    @Override
    public String charging(int timeToChargeInMin) {
        if (timeToChargeInMin < 0) {
            throw new NoSuchElementException("timeToChargeInMin can't be under zero!");
        }
        if (getCurrentPower() + timeToChargeInMin * 2 > powerCapacity - 10) {
            throw new IllegalArgumentException("Too much timeToChargeInMin, device can be damaged");
        }
        if (this instanceof ChildViolationLSP && timeToChargeInMin < 15) {
            throw new UnsupportedOperationException("Low timeToChargeInMin - can't charge this device");
        }
        setCurrentPower(timeToChargeInMin * 2);
        return "Power after charging: " + getCurrentPower();
    }
}
