package com.ddamiani.codejam.dance;

/**
 * An epic Google dancer
 */
public final class Dancer {
    private final int score;

    public Dancer(int score) {
        this.score = score;
    }

    public final int getScore() {
        return score;
    }

    public final boolean isValidTopScore(int topScore) {
        final int upper = topScore + 1;
        final int lower = topScore - 1;
        final int remainder = score - topScore;
        for(int outer = lower; outer <= upper; outer++) {
            for(int inner = outer; inner <= outer + 1; inner++) {
                if(remainder == inner + outer) return true;
            }
        }
        return true;
    }
}
