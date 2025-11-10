// org.example.models.Grocery
package org.example.models;

import java.util.ArrayList;

public class Grocery {
    public static ArrayList<String> groceryList = new ArrayList<>();

    public static void addItems(String input) {
        if (input == null || input.isBlank()) return;
        String[] tokens = input.split(",");
        for (String raw : tokens) {
            String item = raw.trim();
            if (item.isEmpty()) continue;
            if (!checkItemIsInList(item)) {
                groceryList.add(item);
            } else {
                System.out.println("Zaten listede: " + item);
            }
        }
        groceryList.sort(String.CASE_INSENSITIVE_ORDER);

    }



    public static void removeItems(String input) {
        if (input == null || input.isBlank()) return;
        String[] tokens = input.split(",");
        for (String raw : tokens) {
            String item = raw.trim();
            if (item.isEmpty()) continue;
            int idx = indexOfIgnoreCase(item);
            if (idx >= 0) {
                groceryList.remove(idx);
                System.out.println("Silindi: " + item);
            } else {
                System.out.println("Listede yok (atlandı): " + item);
            }
        }
        groceryList.sort(String.CASE_INSENSITIVE_ORDER);

    }

    public static boolean checkItemIsInList(String product) {
        return indexOfIgnoreCase(product) >= 0;
    }

    public static void printSorted() {

        groceryList.sort(String.CASE_INSENSITIVE_ORDER);
        System.out.println("---- Sıralı Liste ----");
        for (int i = 0; i < groceryList.size(); i++) {
            System.out.println((i + 1) + ". " + groceryList.get(i));
        }
        System.out.println("----------------------");
    }

    private static int indexOfIgnoreCase(String target) {
        if (target == null) return -1;
        for (int i = 0; i < groceryList.size(); i++) {
            if (groceryList.get(i).equalsIgnoreCase(target)) return i;
        }
        return -1;
    }
}
