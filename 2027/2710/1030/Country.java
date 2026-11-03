/** A map key identified by a two-letter lowercase country abbreviation. */
public class Country {
    private final String name;
    private final String abbreviation;

    public Country(String name, String abbreviation) {
        if (name == null || name.trim().length() == 0) {
            throw new IllegalArgumentException("A country name is required.");
        }
        if (abbreviation == null) {
            throw new IllegalArgumentException("A country abbreviation is required.");
        }

        String normalized = abbreviation.trim().toLowerCase();
        if (normalized.length() != 2
                || normalized.charAt(0) < 'a' || normalized.charAt(0) > 'z'
                || normalized.charAt(1) < 'a' || normalized.charAt(1) > 'z') {
            throw new IllegalArgumentException("Enter a two-letter country abbreviation.");
        }
        this.name = name.trim();
        this.abbreviation = normalized;
    }

    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    /** Converts the two letters to a base-26 number, so aa is 0 and zz is 675. */
    @Override
    public int hashCode() {
        int firstLetter = abbreviation.charAt(0) - 'a';
        int secondLetter = abbreviation.charAt(1) - 'a';
        return firstLetter * 26 + secondLetter;
    }

    /** Two countries are the same country when their abbreviations match. */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Country)) {
            return false;
        }

        Country other = (Country) object;
        return abbreviation.equals(other.abbreviation);
    }

    @Override
    public String toString() {
        return name + " (" + abbreviation + ")";
    }
}
