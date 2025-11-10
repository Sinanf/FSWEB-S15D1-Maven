package org.example.models;

import java.util.*;

public class Grocery {

    // Liste public static
    public static ArrayList<String> groceryList = new ArrayList<>();

    public static void startGrocery() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Grocery App'e hoş geldiniz!");
        System.out.println("Komutlar: 0=Çıkış, 1=Ekle, 2=Sil");

        boolean running = true;
        while (running) {
            System.out.print("Komut giriniz (0/1/2): ");
            String line = sc.nextLine().trim();

            if (line.equals("0")) {
                running = false;
            } else if (line.equals("1")) {
                System.out.print("Ekle(tek ya da virgülle ayrılmış): ");
                String input = sc.nextLine();
                addItems(input);
                printSorted();
            } else if (line.equals("2")) {
                System.out.print("Sil(tek ya da virgülle ayrılmış): ");
                String input = sc.nextLine();
                removeItems(input);
                printSorted();
            } else {
                System.out.println("Geçersiz komut. 0/1/2 kullanınız.");
            }
        }

        System.out.println("Uygulama sonlandırıldı.");
    }


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
    }


    public static void removeItems(String input) {
        if (input == null || input.isBlank()) return;

        String[] tokens = input.split(",");
        for (String raw : tokens) {
            String item = raw.trim();
            if (item.isEmpty()) continue;

            // case-insensitive arayıp sil
            int index = indexOfIgnoreCase(item);
            if (index >= 0) {
                groceryList.remove(index);
                System.out.println("Silindi: " + item);
            } else {
                System.out.println("Listede yok (atlandı): " + item);
            }
        }
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
            if (groceryList.get(i).equalsIgnoreCase(target)) {
                return i;
            }
        }
        return -1;
    }
}
