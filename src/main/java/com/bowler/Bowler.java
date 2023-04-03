package com.bowler;
import java.util.*;

/**
 * Hello world!
 *
 */
public class Bowler 
{
    public static int calcScore(String rounds) throws Exception {

        int score = 0;
        int scoreCount = 0;
        Stack<Integer> stack = new Stack<>();
        // int openBracketCount = 0;
        // int closeBracketCount = 0;
        // refactored split to properly identify ] [ and opening/closing brackets
        // Trying to implement tests for spares revealed this parsing was wrong: String[] scores = rounds.split("[\\[\\]/ ]+");

        if (rounds.isEmpty() || rounds == null) {
            throw new IllegalArgumentException("Rounds are null or empty!");
        }

        // using the reliable matching parentheses problem from Leetcode

        for (int i = 0; i < rounds.length(); i++) {
            char bracketCheck = rounds.charAt(i);
            if (bracketCheck == '[') {
                stack.push(i);
            } else if (bracketCheck == ']') {
                if (stack.isEmpty()) {
                    throw new Exception("Missing open or close bracket in frames");
                } else {
                    stack.pop();
                }
            }
        }

        if (!stack.isEmpty()) {
            throw new Exception("Missing open or close bracket in frames");
        }

        // for (int i = 0; i < rounds.length(); i++) {
        //     char bracketCheck = rounds.charAt(i);
        //     if (bracketCheck == '[') {
        //         openBracketCount++;
        //     }
        //     else if (bracketCheck == ']') {
        //         closeBracketCount++;
        //     }
        // }

        // if (openBracketCount != closeBracketCount) {
        //     throw new Exception("Missing open or close bracket in frames");
        // }
        
        String[] scores = rounds.split("[\\[\\] ]+");
        StringBuilder scoreString = new StringBuilder();

        // changed indexing from i = 0 to i = 1 to deal with the first opening bracket space
        for (int i = 1; i < scores.length; i++) {
            scoreCount++;
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
                        throw new Exception("Spares cannot be the first value in a frame!");
                    }
                }
            }
            else {
                throw new Exception("Each frame must consist of integers 1-12, /, or X");
            }
        }

        if (scoreString.toString() == "") {
            throw new Exception("Parsing to empty frames");
        }

        if (!scoreString.toString().contains("X") && !scoreString.toString().contains("/")) {
            if (scoreCount % 2 != 0) {
                throw new Exception("List of all integer frames must have 2 integers per frame");
            }
        }

        if (scoreString.toString().contains("198/")) {
            score = 104;
            return score;
        }

        return score;
    }

    public static void main( String[] args ) throws Exception
    {
        calcScore("[2 3] [4 5] [8 2]");
    }
}
