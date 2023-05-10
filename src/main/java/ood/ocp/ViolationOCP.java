package ood.ocp;


/** Есть три основных нарушения OCP
 * 1. Fragile Base Class (хрупкий базовый класс)
 * 2. Incomplete Abstraction (неполное абстрагирование)
 * 3. “If/Switch Statements Smell” или “Switch Statements Anti-pattern”.
 * Данный код демонстрирует отсутствие абстракции, так как фигуры перечислены в Enum
 * и при потребности у наследника в новой фигуре нужно будет корректировать Enum,
 * Код оператора switch тоже потребуется корректировать, что является нарушением закрытости к изменениям.
 * Если мы изменим метод calculatePerimeter который будет использоваться в наследниках,
 * то это повлияет на результаты работы программы без явной декларации изменения,
 * это нарушение известно как Хрупкий базовый класс.
 */
public class ViolationOCP {
        public enum Type {
            CIRCLE, SQUARE, TRIANGLE
        }

        private Type type;

        public ViolationOCP(Type type) {
        }

        public void calculatePerimeter() {
            System.out.println("Площадь фигуры = ");
        }
        public void draw() {
            switch (type) { // Нарушение "Switch Statements Anti-pattern": использование оператора switch
                case CIRCLE:
                    System.out.println("Окружность");
                    break;
                case SQUARE:
                    System.out.println("Квадрат");
                    break;
                case TRIANGLE:
                    System.out.println("Треугольник");
                    break;
                default:
                    throw new IllegalArgumentException("Unknown shape type: " + type);
            }
        }
    }

