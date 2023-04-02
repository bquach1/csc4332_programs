package com.bowler;

/**
 * Hello world!
 *
 */
public class Bowler 
{
    public static int calcScore(String rounds) {
        int score = 0;
        // refactored split to properly identify ] [ and opening/closing brackets
        String[] scores = rounds.split("[\\[\\]/ ]+");

        // changed indexing from i = 0 to i = 1 to deal with the first opening bracket space
        for (int i = 1; i < scores.length; i++) {
            score += Integer.parseInt(scores[i]);
        }

        return score;
    }

    public static void main( String[] args )
    {
        calcScore("[1 2] [3 4] [5 6]");
    }
}
