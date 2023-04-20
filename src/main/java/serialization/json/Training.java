package serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "training")
@XmlAccessorType(XmlAccessType.FIELD)
public class Training {
    @XmlAttribute
    private boolean isStarted;
    @XmlAttribute
    private int minutes;
    @XmlElementWrapper
    @XmlElement(name = "exercise")
    private String[] exercises;
    @XmlElement
    private PersonTrain person;


    public Training(boolean isStarted, int minutes, String[] exercises, PersonTrain person) {
        this.isStarted = isStarted;
        this.minutes = minutes;
        this.exercises = exercises;
        this.person = person;
    }

    public Training() {
    }

    public static void main(String[] args) throws Exception {
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

        JAXBContext context = JAXBContext.newInstance(Training.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(weeklyTrain, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }

        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Training result = (Training) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }

        System.out.println("Преобразование в JSONObject и json строку с помощью org.json");
        JSONObject jsonPersonTrain = new JSONObject(
                "{\"name\":\"KungFuPanda\",\"age\":20,\"levelOfTraining\":10}");
        JSONObject jsonWeeklyTraining = new JSONObject(weeklyTrain);
        jsonWeeklyTraining.put("person", jsonPersonTrain);
        System.out.println(jsonPersonTrain);
        System.out.println(jsonWeeklyTraining);
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

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public String[] getExercises() {
        return exercises;
    }

    public void setExercises(String[] exercises) {
        this.exercises = exercises;
    }

    public PersonTrain getPerson() {
        return person;
    }

    public void setPerson(PersonTrain person) {
        this.person = person;
    }
}

