package com.pawel;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static Locations locations = new Locations();

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        // LOCATIONS
        Map<String, String> vocabulary = new HashMap<String, String>();

        vocabulary.put("NORTH", "N");
        vocabulary.put("SOUTH", "S");
        vocabulary.put("WEST", "W");
        vocabulary.put("EAST", "E");
        vocabulary.put("NORTHEAST", "NE");
        vocabulary.put("NORTHWEST", "NW");
        vocabulary.put("SOUTHEAST", "SE");
        vocabulary.put("SOUTHWEST", "SW");
        vocabulary.put("UP", "U");
        vocabulary.put("DOWN", "D");
        vocabulary.put("QUIT", "Q");

        Location currentLocation = locations.getLocation(1);

        while(true) {
            System.out.println(currentLocation.getDescription());

            if (currentLocation.getLocationID() == 0) {
                break;
            }

            Map<String, Integer> exits = currentLocation.getExits();
            System.out.print("Available exits are ");
            for (String exit : exits.keySet()) {
                for (String def : vocabulary.keySet()) {
                    if (exit.equals(vocabulary.get(def))) {
                        exit = def + "(" + exit + ")";
                        break;
                    }

                }
                System.out.print(exit + ", ");
            }
            System.out.println();

            String direction = scanner.nextLine().toUpperCase();

            if (direction.length() > 1) {
                String[] words = direction.split(" ");
                for (String s : words) {
                    if (vocabulary.containsKey(s)) {
                        direction = vocabulary.get(s);
                        break;
                    }
                }
            }

            if (exits.containsKey(direction)) {
                currentLocation = locations.getLocation(currentLocation.getExits().get(direction));
            } else {
                System.out.println("You cannot go in that direction");
            }
        }

        locations.close();
    }
}
