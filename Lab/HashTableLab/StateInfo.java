import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;


public class StateInfo {
    private String name, abbreviation, population, capital;
    private Image image1, image2;

    public StateInfo(State s, String population, String capital) {
        this.population = population;
        this.capital = capital;
        
        String[] words = s.toString().toCharArray();
        name = words[0];
        abbreviation = words[1];

        /* MAKE IMAGE CODE */
    }

    public Image image1() {
        return image1;
    }

    public Image image2() {
        return image2;
    }

    public String toString() {
        return "Name: " + name + "\nAbbreviation: " + abbreviation + "\nPopulation: " + population + "\nCapital: " + capital;
    }
}
