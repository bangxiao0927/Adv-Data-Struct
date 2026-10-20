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
import java.util.TreeSet;

public class Runenr {
    public static void main(String[] args) {
        // Find the unique and repeated letters.
        String[] letters = "a b c d e f g h a b c d e f g h i j k".split(" ");
        printUniqueAndDuplicates(letters);

        // Find the unique and repeated words.
        String[] words = "one two three one two three six seven one two".split(" ");
        printUniqueAndDuplicates(words);

        // Put the numbers into odd and even sets.
        String[] numbers = "3 5 4 7 5 17 29 17 4 6 56 72 6".split(" ");
        HashSet<Integer> odds;
        odds = new HashSet<Integer>();
        HashSet<Integer> evens;
        evens = new HashSet<Integer>();

        for (String number : numbers) {
            int currentNumber = Integer.parseInt(number);
            if (currentNumber % 2 == 0) {
                evens.add(currentNumber);
            } else {
                odds.add(currentNumber);
            }
        }

        System.out.println("Odds without duplicates: " + new TreeSet<Integer>(odds));
        System.out.println("Evens without duplicates: " + new TreeSet<Integer>(evens));

        // Find the numbers that are in both lists.
        String[] list1 = "1 5 9 4 6 8 12 1".split(" ");
        String[] list2 = "6 5 8 9 11 7 10 11".split(" ");
        HashSet<Integer> firstSet = toIntegerSet(list1);
        HashSet<Integer> secondSet = toIntegerSet(list2);

        HashSet<Integer> commonNumbers;
        commonNumbers = new HashSet<Integer>();
        for (int number : firstSet) {
            if (secondSet.contains(number)) {
                commonNumbers.add(number);
            }
        }
        System.out.println("Duplicates between the two sets: " + new TreeSet<Integer>(commonNumbers));
    }

    private static void printUniqueAndDuplicates(String[] values) {
        HashSet<String> uniques;
        uniques = new HashSet<String>();
        HashSet<String> duplicates;
        duplicates = new HashSet<String>();

        for (String value : values) {
            if (uniques.contains(value)) {
                duplicates.add(value);
            } else {
                uniques.add(value);
            }
        }

        System.out.println("Uniques: " + new TreeSet<String>(uniques));
        System.out.println("Duplicates: " + new TreeSet<String>(duplicates));
    }

    private static HashSet<Integer> toIntegerSet(String[] values) {
        HashSet<Integer> result;
        result = new HashSet<Integer>();
        for (String value : values) {
            result.add(Integer.parseInt(value));
        }
        return result;
    }
}
