package edu.course.lab01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CourseToolkitTest {

    @Test
    void returnsTrueForEvenNumber() {
        boolean result = CourseToolkit.isEven(8);

        assertTrue(result);
    }

    @Test
    void returnsFalseForOddNumber() {
        boolean result = CourseToolkit.isEven(7);

        assertFalse(result);
    }
    @Test
    void returnsTrueForZero() {
    assertTrue(CourseToolkit.isEven(0) );
    }
    @Test
    void returnsTrueForNegativeEvenNumber() {
        boolean result = CourseToolkit.isEven(-8);

        assert(result);
    
}   @Test
    void isPrimeReturnsFalseBelowTwo() {
        assertFalse(CourseToolkit.isPrime(1));
}

    @Test
    void isPrimeReturnsTrueForTwo() {
    assertTrue(CourseToolkit.isPrime(2));
}

    @Test
    void isPrimeReturnsFalseForComposite() {
        assertFalse(CourseToolkit.isPrime(15));
}

    @Test
    void isPrimeReturnsFalseForSquareOfPrime() {
        assertFalse(CourseToolkit.isPrime(49));
}

    @Test
    void isPalindromeReturnsTrue() {
        assertTrue(CourseToolkit.isPalindrome("level"));
}

    @Test
    void isPalindromeReturnsFalseForDifferentCase() {
        assertFalse(CourseToolkit.isPalindrome("Level"));
}

    @Test
    void isPalindromeThrowsForNull() {
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.isPalindrome(null));
}

    @Test
    void averageReturnsFractionalResult() {
        assertNotEquals(2.5, CourseToolkit.average(new int[]{1, 2, 3, 4}), 0.0001);
}

    @Test
    void averageWorksWithNegativeValues() {
        assertEquals(-2.0, CourseToolkit.average(new int[]{-1, -2, -3}), 0.0001);
}

    @Test
    void averageThrowsForEmptyArray() {
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.average(new int[]{}));
}

}
