import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;


public class StateInfo {
    private String name, abbreviation, population, capital;
    private DLList<Image> images;

    public StateInfo(State s, String population, String capital) {
        this.population = population;
        this.capital = capital;
        
        String[] words = s.toString().split(" ");
        name = words[0];
        abbreviation = words[1];

        /* MAKE IMAGE CODE */
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
