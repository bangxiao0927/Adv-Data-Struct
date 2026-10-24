/** A map key identified by a two-letter U.S. state abbreviation. */
public class State {
    private final String abbreviation;

    public State(String abbreviation) {
        if (abbreviation == null) {
            throw new IllegalArgumentException("A state abbreviation is required.");
        }

        String normalized = abbreviation.trim().toUpperCase();
        if (normalized.length() != 2
                || !Character.isLetter(normalized.charAt(0))
                || !Character.isLetter(normalized.charAt(1))) {
            throw new IllegalArgumentException("Enter a two-letter state abbreviation.");
        }
        this.abbreviation = normalized;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    /** Converts the two letters to a base-26 number; it does not use String.hashCode(). */
    @Override
    public int hashCode() {
        int firstLetter = abbreviation.charAt(0) - 'A';
        int secondLetter = abbreviation.charAt(1) - 'A';
        return firstLetter * 26 + secondLetter;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof State)) {
            return false;
        }

        State other = (State) object;
        return abbreviation.equals(other.abbreviation);
    }

    @Override
    public String toString() {
        return abbreviation;
    }
}
