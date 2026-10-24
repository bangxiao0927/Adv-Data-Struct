/** Stores the information associated with a state key. */
public class Info {
    private final String name;
    private final String capital;
    private final long population;

    public Info(String name, String capital, long population) {
        this.name = name;
        this.capital = capital;
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public long getPopulation() {
        return population;
    }

    @Override
    public String toString() {
        return "State: " + name
                + "\nCapital: " + capital
                + "\nPopulation: " + population;
    }
}
