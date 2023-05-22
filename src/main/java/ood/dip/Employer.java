package ood.dip;


/**
 * Данный код демонстрирует нарушение принципов Dependency Inversion Principle
 * - Есть жесткая зависимость между классами Worker и Employer без использования абстракций,
 *  это нарушение известно как Concrete Dependency.
 * - класс Employer полностью контролирует жизненный цикл сущности Worker, это нарушение известно как Control Freak
 * - Зависимость между классами создается внутри конструктора Employer, а не через входной параметр,
 *  что так же нарушает DIP создавая жёсткую связь, даже если вывести Worker в абстракцию,
 *  всё равно будет создаваться жёсткая связь и нарушение будет называться DIP Violation by Abstraction
 */
class Employer {
    private Worker worker;

    public Employer() {
        this.worker = new Worker();
    }

    public void work() {
        worker.doWork();
    }

    public void paySalary() {
        worker.receiveSalary();
    }

    public void fireWorker() {
        worker = null;
    }

    public void getNewWorker() {
        worker = new Worker();
    }
}
