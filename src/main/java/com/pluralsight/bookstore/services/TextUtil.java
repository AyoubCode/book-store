package com.pluralsight.bookstore.services;

public class TextUtil {
    public String sanitize(String textToSanitize){
        return textToSanitize.replaceAll("\\s+", " ");
    }
}
