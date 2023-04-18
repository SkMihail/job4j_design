package serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Training {
    private boolean isStarted;
    private int minutes;
    private String[] exercises;
    private PersonTrain person;

    public Training(boolean isStarted, int minutes, String[] exercises, PersonTrain person) {
        this.isStarted = isStarted;
        this.minutes = minutes;
        this.exercises = exercises;
        this.person = person;
    }

    public static void main(String[] args) {
        Training weeklyTrain = new Training(true, 90,
                new String[]{"push-ups", "squats", "jump rope"},
                new PersonTrain("KungFuPanda", 20, 0));
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(weeklyTrain));

        final String trainFromJSON =
                "{\"isStarted\":true,"
                + "\"minutes\":300,"
                + "\"exercises\":[\"push-ups\",\"squats\",\"jump rope\",\"run\", \"sleep\"],"
                + "\"person\":{\"name\":\"KungFuPanda\",\"age\":20,\"levelOfTraining\":10}}";

        final Training specialTrain = gson.fromJson(trainFromJSON, Training.class);
        System.out.println(specialTrain);
    }

    @Override
    public String toString() {
        return "Training{"
                + "person=" + person
                + ", isStarted=" + isStarted
                + ", minutes=" + minutes
                + ", exercises=" + Arrays.toString(exercises)
                + '}';
    }
}

