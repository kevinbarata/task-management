package com.project.task.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailVerifier {

    public static boolean isValidEmail(String email) {
        // Pola regex untuk memverifikasi format email
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        // Membuat pattern dari regex
        Pattern pattern = Pattern.compile(regex);

        // Membuat matcher dari email
        Matcher matcher = pattern.matcher(email);

        // Mengembalikan true jika email sesuai dengan pola regex, dan false jika tidak
        return matcher.matches();
    }

}
