package com.pluralsight.bookstore.services;

import java.util.Random;

public class IsbnGenerator implements NumberGenerator{
    @Override
    public String generateNumber() {
        return  "13-4376-"+ (10000+Math.abs(new Random().nextInt(90000)));

    }
}
