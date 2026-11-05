public class State {
    private String abbreviation, name;

    public State (String name, String abbreviation) {
        this.name = name;
        this.abbreviation = abbreviation;
        if (abbreviation.length() > 2) {
            throw new IllegalArgumentException("Invalid abbreviation.length() in State(String, String)");
        }
    }

    public boolean equals(Object o) {
        State s = (State)(o);
        if (s.getAbbreviation() == abbreviation) {
            return true;
        }
        return false;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public int hashcode() {
        char[] chars = abbreviation.toCharArray();
        int hashcode = 0;
        for (int i = 0; i < chars.length; i++) {
            hashcode += Math.pow((int)(chars[i]) - 97, i); 
        }
        return hashcode;
    }

    public String toString() {
        return name + " " + abbreviation;
    }
}
