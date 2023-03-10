import java.util.Objects;

public class Toy {

    private int toyId;
    private String toyTitle;
    private int toyVictoryFrequency;

    public Toy(int toyId, String toyTitle, int toyVictoryFrequency) {
        this.toyId = toyId;
        this.toyTitle = toyTitle;
        this.toyVictoryFrequency = toyVictoryFrequency;
    }

    public int getToyId() {
        return toyId;
    }

    public void setToyId(int toyId) {
        this.toyId = toyId;
    }

    public String getToyTitle() {
        return toyTitle;
    }

    public void setToyTitle(String toyTitle) {
        this.toyTitle = toyTitle;
    }

    public int getToyVictoryFrequency() {
        return toyVictoryFrequency;
    }

    public void setToyVictoryFrequency(int toyVictoryFrequency) {
        this.toyVictoryFrequency = toyVictoryFrequency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Toy toy = (Toy) o;
        return toyTitle.equals(toy.toyTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(toyTitle);
    }
}
