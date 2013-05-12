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
        return testTopScore(topScore, 1);
    }

    public final boolean isValidExceptionalTopScore(int topScore) {
        return testTopScore(topScore, 2);
    }

    private boolean testTopScore(int topScore, int spread) {
        final int lower = topScore > 0 ? topScore - spread : 0;
        final int remainder = score - topScore;

        if (remainder < 0) return false;

        for (int outer = lower; outer <= topScore; outer++) {
            for (int inner = outer; inner <= topScore; inner++) {
                if (remainder == inner + outer) return true;
            }
        }

        return false;
    }
}
