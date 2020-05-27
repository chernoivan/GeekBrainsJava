package com.company.samurai.lesson3;

import java.util.*;

public class PhoneBook {
    private Map<String, Set<String>> phoneBook = new TreeMap<>();

    public void add(String lastName, String phone) {
        Set<String> entry = phoneBook.getOrDefault(lastName, new LinkedHashSet<>());
        entry.add(phone);
        phoneBook.put(lastName, entry);
    }

    public Set<String> get(String lastName) {
        return phoneBook.getOrDefault(lastName, new HashSet<>());
    }

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("Alpha", "111-11-11");
        phoneBook.add("Beta", "222-22-22");
        phoneBook.add("Charlie", "222-22-22");
        phoneBook.add("Delta", "333-33-33");
        System.out.println("Alpha: "+phoneBook.get("Alpha"));
        System.out.println("Beta: "+phoneBook.get("Beta"));
        System.out.println("Charlie: "+phoneBook.get("Charlie"));
        System.out.println("Echo: "+phoneBook.get("Echo"));
    }
}
