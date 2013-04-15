package com.ddamiani.codejam;

import java.io.*;
import java.util.*;

/**
 * Tr
 */
public class Translation {
    private InputStream normalStream;
    private InputStream mutatedStream;
    private Map<Character, Character> letterMapping;
    private Map<Character, Character> revLetterMapping;

    public Translation(InputStream normalStream, InputStream mutatedStream) {
        this.normalStream = normalStream;
        this.mutatedStream = mutatedStream;
        letterMapping = null;
        revLetterMapping = null;
    }

    public Character getMutatedVersion(Character normChar) {
        if(letterMapping == null) {
            try {
                loadLetterMap();
            } catch (IOException e) {
                return null;
            }
        }

        if(isInvalidChar(normChar)) return normChar;

        return letterMapping.get(normChar);
    }

    public Character getNormalVersion(Character muteChar) {
        if(revLetterMapping == null) {
            try {
                loadLetterMap();
            } catch (IOException e) {
                return null;
            }
        }

        if(isInvalidChar(muteChar)) return muteChar;

        return revLetterMapping.get(muteChar);
    }

    private void loadLetterMap() throws IOException {
        Reader normal = new BufferedReader(new InputStreamReader(normalStream));
        Reader mutated = new BufferedReader(new InputStreamReader(mutatedStream));
        letterMapping = new HashMap<Character, Character>();
        revLetterMapping = new HashMap<Character, Character>();

        int norm;
        int mute;
        while((norm = normal.read()) != -1 && (mute = mutated.read()) != -1) {
            char normChar = (char) norm;
            char muteChar = (char) mute;
            if(isInvalidChar(normChar) || isInvalidChar(muteChar)) continue;

            letterMapping.put(normChar, muteChar);
            revLetterMapping.put(muteChar, normChar);
        }
        normal.close();
        mutated.close();
    }

    private boolean isInvalidChar(char letter) {
        return letter == ' ' || letter == '\n';
    }
}
