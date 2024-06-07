package com.project.task.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {
    public static boolean isValidPassword(String password) {
        // Ekspresi reguler untuk memeriksa password
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).+$";

        // Membuat pattern dari regex
        Pattern pattern = Pattern.compile(regex);

        // Membuat matcher dari password
        Matcher matcher = pattern.matcher(password);

        // Mengembalikan true jika password sesuai dengan pola regex, dan false jika tidak
        return matcher.matches();
    }

}
