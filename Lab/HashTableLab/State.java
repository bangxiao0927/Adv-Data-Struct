// The key of the map: two letters are the whole identity of a state.
public class State {
    private String abbreviation, name;

    public State (String name, String abbreviation) {
        this.name = name;
        this.abbreviation = abbreviation.trim().toLowerCase();
        if (this.abbreviation.length() > 2) {
            throw new IllegalArgumentException("Invalid abbreviation.length() in State(String, String)");
        }
    }

    public boolean equals(Object o) {
        if (!(o instanceof State)) {
            return false;
        }
        State s = (State) o;
        return s.getAbbreviation().equals(abbreviation);
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getName() {
        return name;
    }

    // 26 * 26 = 676 buckets, one for every pair of letters.
    public int hashCode() {
        char[] chars = abbreviation.toCharArray();
        if (chars.length == 0) {
            return 0;
        }
        int first = chars[0] - 'a';
        int second = 0;
        if (chars.length > 1) {
            second = chars[1] - 'a';
        }
        if (first < 0 || first > 25 || second < 0 || second > 25) {
            return 0;
        }
        return first * 26 + second;
    }

    public String toString() {
        return name + " " + abbreviation;
    }
}
