// Classwork: 
// Write code to do the following with your partner.

// Use the built-in HashSet to do the following.

// Given the Original List of a b c d e f g h a b c d e f g h i j k
// String[] letters = "a b c d e f g h a b c d e f g h i j k".split(" ");
// Output a list of Uniques : [a, b, c, d, e, f, g, h, i, j, k]
// Output a list of Duplicates : [a, b, c, d, e, f, g, h]
  
// Given the Original List of one two three one two three six seven one two
// Output a list of Uniques : [one, seven, six, three, two]
// Output a list of Duplicates : [one, three, two]
 
// Given the Original List of 3 5 4 7 5 17 29 17 4 6 56 72 6
// Output a list of ODDS without duplicates : [3, 5, 7, 17, 29]
// Output a list of EVENS without duplicates : [4, 6, 56, 72]

// Given List1 of 1 5 9 4 6 8 12 1
// Given List2 of 6 5 8 9 11 7 10 11
// Output a list of duplicates between the two sets : [5, 6, 8, 9]


// Hint: Use String[] words = "abc cde fgh ijk".split(" "); to create a list from a string separated by a space.

import java.util.HashSet;
import java.util.Set;

public class Runner {
    public static void main(String[] args) {
        String[] letters = "a b c d e f g h a b c d e f g h i j k".split(" ");
        printUniqueAndDuplicates(letters);

        String[] words = "one two three one two three six seven one two".split(" ");
        printUniqueAndDuplicates(words);

        String[] numbers = "3 5 4 7 5 17 29 17 4 6 56 72 6".split(" ");
        HashSet<Integer> odds = new HashSet<>();
        HashSet<Integer> evens = new HashSet<>();

        for (String number : numbers) {
            int value = Integer.parseInt(number);
            if (value % 2 == 0) {
                evens.add(value);
            } else {
                odds.add(value);
            }
        }
        
        System.out.println("Odds without duplicates: " + odds);
        System.out.println("Evens without duplicates: " + evens);

        String[] list1 = "1 5 9 4 6 8 12 1".split(" ");
        String[] list2 = "6 5 8 9 11 7 10 11".split(" ");

        HashSet<Integer> firstSet = toIntegerSet(list1);
        HashSet<Integer> secondSet = toIntegerSet(list2);
        
        Set<Integer> duplicates = new HashSet<>();

        for (int value : firstSet) {
            if (secondSet.contains(value)) {
                duplicates.add(value);
            }
        }

        System.out.println("Duplicates between the two sets: " + duplicates);
    }

    private static void printUniqueAndDuplicates(String[] values) {
        HashSet<String> seen = new HashSet<>();
        HashSet<String> uniques = new HashSet<>();
        HashSet<String> duplicates = new HashSet<>();

        for (String value : values) {
            uniques.add(value);
            if (!seen.add(value)) {
                duplicates.add(value);
            }
        }

        System.out.println("Uniques: " + uniques);
        System.out.println("Duplicates: " + duplicates);
    }

    private static HashSet<Integer> toIntegerSet(String[] values) {
        HashSet<Integer> result = new HashSet<>();
        for (String value : values) {
            result.add(Integer.parseInt(value));
        }
        return result;
    }
}
