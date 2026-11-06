// The value of the map: the facts about a state plus all of its pictures.
// Pictures are stored as URL strings, as the lab requires; Screen loads them for display.
public class StateInfo {
    private String name, abbreviation, population, capital;
    private DLList<String> images;

    public StateInfo(State s, String population, String capital) {
        this.population = population;
        this.capital = capital;
        name = s.getName();
        abbreviation = s.getAbbreviation();
        images = new DLList<String>();
    }

    public StateInfo(String population, String capital) {
        this.population = population;
        this.capital = capital;
        images = new DLList<String>();
    }

    public void addImage(String url) {
        images.add(url);
    }

    public DLList<String> images() {
        return images;
    }

    public String getCapital() {
        return capital;
    }

    public String getPopulation() {
        return population;
    }

    public String toString() {
        return "State: " + name + " (" + abbreviation + ")\nCapital: " + capital + "\nPopulation: " + population;
    }
}
