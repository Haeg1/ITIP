package edu.course.lab01;

import java.util.Arrays;

/**
 * Небольшие методы для первой лабораторной работы.
 */
public final class CourseToolkit {

    private CourseToolkit() {
        // Утилитарный класс не должен иметь экземпляров.
    }

    /**
     * Возвращает true, если число четное.
     */
    public static boolean isEven(int number) {
        return number % 2 == 0;
    }

    public static boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }
        for (int divisor = 2; divisor * divisor <= number; divisor++) {
            if (number % divisor == 0) {
                return false;
            }
        }

        return true;
    }

    public static boolean isPalindrome(String text) {
        if (text == null) {
        throw new IllegalArgumentException();
    }
        char[] charArray = text.toCharArray();

        for (int i = 0; i < charArray.length / 2; i++) {
            char temp = charArray[i];
            charArray[i] = charArray[charArray.length - 1 - i];
            charArray[charArray.length - 1 - i] = temp;
        }
        String reversed = new String(charArray);

        return reversed.equals(text);
    }

    public static double average(int[] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException();
        }

        return Arrays.stream(values).sum() / values.length;
    }
    public static int min(int[] values) {
    if (values.length == 0) {
        throw new IllegalArgumentException("values is empty");
    }

    int result = values[0];

    for (int i = 0; i < values.length; i++) {
        if (values[i] < result) {
            result = values[i];
        }
    }
    return result;
}

public static int max(int[] values) {
    if (values == null || values.length == 0) {
        throw new IllegalArgumentException("values is empty\"");
    }

    int result = values[0];

    for (int i = 0; i < values.length; i++) {
        if (values[i] > result) {
            result = values[i];
        }
    }
    return result;
}
}

