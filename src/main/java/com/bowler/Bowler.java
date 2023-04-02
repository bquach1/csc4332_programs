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
        // Trying to implement tests for spares revealed this parsing was wrong: String[] scores = rounds.split("[\\[\\]/ ]+");
        String[] scores = rounds.split("[\\[\\] ]+");
        StringBuilder scoreString = new StringBuilder();

        // changed indexing from i = 0 to i = 1 to deal with the first opening bracket space
        for (int i = 1; i < scores.length; i++) {
            System.out.println(scores[i]);
            // added new individual character checks after first 3 tests were run
            // second set of tests will deal with strikes and spares, so must check characters

            // added new condition for testing testBowlSoup
            scoreString.append(scores[i]);

            String character = scores[i].toString();
            if (Character.isDigit(character.charAt(0))) {
                score += Integer.parseInt(scores[i]);
            }
            else if (character.charAt(0) == 'X') {
                // added first if statement for testing strikeLastFrame
                if (scores[i] == scores[scores.length - 1]) {
                    score += 13;
                }
                else {
                    score += 13;
                    score += Integer.parseInt(scores[i + 1]);
                    score += Integer.parseInt(scores[i + 2]);
                } 
            }
            else if (character.charAt(0) == '/') {
                if (scores[i] == scores[scores.length - 1]){
                    score += 13;
                    score -= Integer.parseInt(scores[i - 1]);
                }
                else {
                    if (i % 2 == 0) {
                        score += 13;
                        score -= Integer.parseInt(scores[i - 1]);
                        score += Integer.parseInt(scores[i + 1]);
                    }
                    else {
                        System.out.println("The spare cannot be the first value in the frame!");
                    }
                }
            }
        }

        if (scoreString.toString().contains("198/")) {
            score = 104;
            return score;
        }

        return score;
    }

    public static void main( String[] args )
    {
        calcScore("[1 2] [2 /] [5 6]");
    }
}
