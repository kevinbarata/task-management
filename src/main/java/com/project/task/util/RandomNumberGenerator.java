package com.project.task.util;

import java.util.Random;

public class RandomNumberGenerator {
    public static int sixNumber() {
        Random random = new Random();
        int sixDigitNumber = 0;

        // Menghasilkan 6 digit nomor acak
        for (int i = 0; i < 6; i++) {
            int digit = random.nextInt(10); // rentang: 0 hingga 9
            sixDigitNumber = sixDigitNumber * 10 + digit;
        }
        return sixDigitNumber;
    }
}
