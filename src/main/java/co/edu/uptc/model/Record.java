package co.edu.uptc.model;

public class Record {
    private String name;
    private int correctChoices;

    public Record(String name, int correctChoices) {
        this.name = name;
        this.correctChoices = correctChoices;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCorrectChoices() {
        return correctChoices;
    }

    public void setCorrectChoices(int correctChoices) {
        this.correctChoices = correctChoices;
    }

    @Override
    public String toString() {
        return "Record{" +
                "name='" + name + '\'' +
                ", correctChoices=" + correctChoices +
                '}';
    }
}