package com.ddamiani.codejam;

import java.io.*;
import java.util.*;

/**
 * Project A of google code jam 2012
 */
public class Tongues {
    private static String getFormattedOutputLine(int iteration, String result) {
        final String templateStr = "Case #%d: %s\n";
        return String.format(templateStr, iteration, result);
    }

    public static void main(String[] args) {
        if(args.length < 1) {
            System.err.println("No input file specified!");
            System.exit(1);
        }

        Translation trans = new Translation(Tongues.class.getResourceAsStream("/normal_letters.txt"),
                Tongues.class.getResourceAsStream("/mutated_letters.txt"));

        String inputFileName = args[0];
        List<String> lines = new ArrayList<String>();

        try {
            int num_line = 0;

            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFileName)));
            try {
                num_line = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException ne) {
                reader.close();
                System.err.println("Invalid number of initial line: " + ne.getMessage());
                System.exit(1);
            }

            for(int i=1; i<=num_line; i++) {
                String line = reader.readLine();

                StringBuilder translatedStr = new StringBuilder(line.length());
                for (char letter : line.toCharArray()) {
                    translatedStr.append(trans.getNormalVersion(letter));
                }

                lines.add(translatedStr.toString());
            }
            reader.close();
        } catch (FileNotFoundException fe) {
            System.err.println("Specified input file not found: " + inputFileName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Can't read from standard in");
            System.exit(1);
        }


        OutputStream outputStream = null;
        if(args.length >= 2) {
            String outputStreamName = args[1];
            try {
                outputStream = new FileOutputStream(outputStreamName);
            } catch (FileNotFoundException e) {
                System.err.println("Specified input file not found: " + inputFileName);
                System.exit(1);
            }
        } else {
            outputStream = System.out;
        }

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));

        try {
            for(int i=0;i<lines.size();i++) {
                writer.write(getFormattedOutputLine(i+1, lines.get(i)));
            }

            writer.close();
        } catch (IOException e) {
            System.err.println("Problem writing to the output stream: " + e.getMessage());
            System.exit(1);
        }
    }
}
