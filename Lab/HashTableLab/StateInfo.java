import java.awt.Image;

// The value of the map: the facts about a state plus all of its pictures.
public class StateInfo {
    private String name, abbreviation, population, capital;
    private DLList<Image> images;

    public StateInfo(State s, String population, String capital) {
        this.population = population;
        this.capital = capital;
        name = s.getName();
        abbreviation = s.getAbbreviation();
        images = new DLList<Image>();
    }

    public void addImage(Image i) {
        images.add(i);
    }

    public DLList<Image> images() {
        return images;
    }

    public String toString() {
        return "Name: " + name + "\nAbbreviation: " + abbreviation + "\nPopulation: " + population + "\nCapital: " + capital;
    }
}
