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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getLevelOfTraining() {
        return levelOfTraining;
    }

    public void setLevelOfTraining(int levelOfTraining) {
        this.levelOfTraining = levelOfTraining;
    }
}
