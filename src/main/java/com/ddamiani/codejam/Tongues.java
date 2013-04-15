package com.ddamiani.codejam;

import java.io.*;
import java.util.*;

/**
 * Project A of google code jam 2012
 */
public class Tongues {
    private static void printOutputLine(int iteration, String result) {
        final String templateStr = "Case #%d: %s\n";
        System.out.format(templateStr, iteration, result);
    }

    public static void main(String[] args) {
        Translation trans = new Translation(Tongues.class.getResourceAsStream("/normal_letters.txt"),
                Tongues.class.getResourceAsStream("/mutated_letters.txt"));

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int num_line = 0;
        List<String> lines = new ArrayList<String>();

        try {
            num_line = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            System.err.println("Can't read from standard in");
            System.exit(1);
        } catch (NumberFormatException ne) {
            System.err.println("Invalid number of initial line: " + ne.getMessage());
            System.exit(1);
        }

        try {
            for(int i=1; i<=num_line; i++) {
                String line = reader.readLine();

                StringBuilder translatedStr = new StringBuilder(line.length());
                for (char letter : line.toCharArray()) {
                    translatedStr.append(trans.getNormalVersion(letter));
                }

                lines.add(translatedStr.toString());
            }

            reader.close();
        } catch (IOException e) {
            System.err.println("Failed to read the expected lines");
            System.exit(1);
        }

        for(int i=0;i<lines.size();i++) {
            printOutputLine(i, lines.get(i));
        }
    }
}
