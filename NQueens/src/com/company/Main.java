package com.company;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;


public class Main {

    public static void main(String[] args) {
        // Utility Variables
	    Scanner in = new Scanner(System.in);
	    Random rand = new Random();

	    // Consistent Variables
        boolean success = false;
        int queens = 0;
        ArrayList<ArrayList> vertArray = new ArrayList<>(); // Array of rows

        while(true) {
            // Get amount of queens
            success = false;
            System.out.println("How many queens would you like to generate a table for?");
            queens = Integer.parseInt(in.nextLine());

            mainloop:
            while (!success) {
                // Temporary Variables
                vertArray.clear();
                ArrayList<Integer> possibleColumns = new ArrayList<>(); // Array of columns that could hold a 'Q' value
                ArrayList<Integer> possiblePoses = new ArrayList<>(); // Array of columns that could hold a 'Q' value, which will be subtracted by restrictions from the position of 'Q' in the previous row
                int qPos = 0; // Column position of q

                // Populate possibleColumns with initial values
                for (int i = 0; i < queens; i++) {
                    possibleColumns.add(i);
                }

                // Create arrays for vertArrays
                for (int y = 0; y < queens; y++) {
                    // Create array and add to vertArray
                    ArrayList<Character> newRow = new ArrayList<Character>(queens);
                    vertArray.add(newRow);

                    // Decide where Q can go
                    if (y == 0) {
                        qPos = rand.nextInt(queens - 1) + 1;

                    } else if (y == (queens - 1)) {
                        // Clear possiblePoses
                        possiblePoses.clear();

                        // Populate possiblePoses with possibleColumns values
                        for (int i = 0; i < possibleColumns.size(); i++) {

                            // Adds all possible columns as long as they are not withing range of 1 below or above qPos
                            if (possibleColumns.get(i) < (qPos - 1) || possibleColumns.get(i) > (qPos + 1)) {
                                if (possibleColumns.get(i) != 0 && possibleColumns.get(i) != queens - 1) {
                                    possiblePoses.add(possibleColumns.get(i));
                                }
                            }
                        }

                        try {
                            int randIntFromArray = rand.nextInt(possiblePoses.size());
                            qPos = possiblePoses.get(randIntFromArray);
                        } catch (Exception e) {
                            continue mainloop;
                        }

                    } else {
                        // Clear possiblePoses
                        possiblePoses.clear();

                        // Populate possiblePoses with possibleColumns values
                        for (int i = 0; i < possibleColumns.size(); i++) {
                            // Adds all possible columns as long as they are not withing range of 1 below or above qPos
                            if (possibleColumns.get(i) < (qPos - 1) || possibleColumns.get(i) > (qPos + 1)) {
                                possiblePoses.add(possibleColumns.get(i));
                            }
                        }

                        // Try to generate a random int from the size of possiblePoses...
                        // ...and if that is not possible, catch the exception and restart the while loop
                        try {
                            int randIntFromArray = rand.nextInt(possiblePoses.size());
                            qPos = possiblePoses.get(randIntFromArray);
                        } catch (Exception e) {
                            continue mainloop;
                        }
                    }

                    // Remove qPos (position of q in the current array) from possibleColumns (array of columns that a q...
                    // ... could be placed in)
                    for (int i = 0; i < possibleColumns.size(); i++) {
                        if (possibleColumns.get(i) == qPos) {
                            possibleColumns.remove(i);
                        }
                    }

                    // Populate new array (row)
                    for (int x = 0; x < queens; x++) {
                        ArrayList<Character> thisArray = vertArray.get(y);
                        if (x == qPos) {
                            thisArray.add('Q');
                        } else {
                            thisArray.add('-');
                        }
                    }
                }

                // Print out the successful array
                for (int i = 0; i < vertArray.size(); i++) {
                    System.out.println(vertArray.get(i));
                }
                success = true;
            }
        }
    }
}