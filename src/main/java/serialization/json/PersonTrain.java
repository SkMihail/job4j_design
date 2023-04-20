package serialization.json;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
class PersonTrain {
    String name;
    private int age;
    private int levelOfTraining;

    public PersonTrain(String name, int age, int levelOfTraining) {
        this.name = name;
        this.age = age;
        this.levelOfTraining = levelOfTraining;
    }

    public PersonTrain() {
    }

    @Override
    public String toString() {
        return "Person{"
                + "name='" + name + '\''
                + ", age=" + age
                + ", levelOfTraining=" + levelOfTraining
                + '}';
    }
}
