package serialization.json;

class PersonTrain {
    String name;
    private int age;
    private int levelOfTraining;

    public PersonTrain(String name, int age, int levelOfTraining) {
        this.name = name;
        this.age = age;
        this.levelOfTraining = levelOfTraining;
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
