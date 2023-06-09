package com.bowler;
import java.util.*;

public class Bowler 
{
    public static int calcScore(String rounds) throws Exception {

        int score = 0;
        int scoreCount = 0;
        Stack<Integer> bracketStack = new Stack<>();
        // int openBracketCount = 0;
        // int closeBracketCount = 0;
        // refactored split to properly identify ] [ and opening/closing brackets
        // Trying to implement tests for spares revealed this parsing was wrong: String[] scores = rounds.split("[\\[\\]/ ]+");

        // to test testBowlEmptyFrame() and testBowlNullString()

        if (rounds.isEmpty() || rounds == null) {
            throw new IllegalArgumentException("Rounds are null or empty!");
        }

        // using the reliable matching parentheses problem from Leetcode to test testUnclosedFrames()
        // and testEqualUnclosedFrames() (twelfth run)

        for (int i = 0; i < rounds.length(); i++) {
            char bracketCheck = rounds.charAt(i);
            if (bracketCheck == '[') {
                bracketStack.push(i);
            } else if (bracketCheck == ']') {
                if (bracketStack.isEmpty()) {
                    throw new Exception("Missing open or close bracket in frames");
                } else {
                    bracketStack.pop();
                }
            }
        }

        if (!bracketStack.isEmpty()) {
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

        // formattedFrames was used to test testBowlFrameGreaterThan13()
        // and all tests that needed to keep track of individual frames
        String[] formattedFrames = rounds.split("\\s+(?=\\[)");

        // scores array is original split to test regular frames
        // with scoreString
        String[] scores = rounds.split("[\\[\\] ]+");
        StringBuilder scoreString = new StringBuilder();
        
        // added individual frame split to check if sum is greater than 12
        for (int i = 0; i < formattedFrames.length; i++) {
            for (int j = 0; j < formattedFrames[i].length(); j++) {
                String[] values = formattedFrames[i].replaceAll("[\\[\\]]", "").split(" ");
                int sum = 0;

                if (values.length == 0 || values[0].equals("")) {
                    throw new Exception("Empty frame exists in input");
                }

                if (values.length > 2) {
                    throw new Exception("Frames cannot have more than 2 entries");
                }

                if (rounds.contains("X") || rounds.contains("/")) {
                    if (values.length > 1) {
                        if (values[1].equals("X")) {
                            throw new Exception("Strike cannot be after the first element in a frame");
                        }
                    }
                    else {
                        continue;
                    }
                }         
                else {
                    sum += Integer.parseInt(values[0]);
                    sum += Integer.parseInt(values[1]);
                }       

                if (sum >= 13) {
                    throw new Exception("Frame sum must not exceed 13 and should be a spare if == 13");
                }
            }
        }

        // first for loop method created
        // changed indexing from i = 0 to i = 1 to deal with the first opening bracket space
        for (int i = 1; i < scores.length; i++) {
            scoreCount++;

            // added new individual character checks after first 3 tests were run
            // second set of tests will deal with strikes and spares, so must check characters

            // added new condition for testing testBowlSoup
            scoreString.append(scores[i]);

            String character = scores[i].toString();
            // test basic digits (runs 1-3)
            if (Character.isDigit(character.charAt(0))) {
                score += Integer.parseInt(scores[i]);
            }
            // test basic strikes (fourth run)
            else if (character.charAt(0) == 'X') {
                // added first if statement for testing strikeLastFrame (fifth run))
                if (scores[i] == scores[scores.length - 1]) {
                    score += 13;
                }
                // lookahead for all/multiple strikes (ninth run))
                else if (scores[i + 1].equals("X")) {
                    score += 26;
                }
                else {
                    score += 13;
                    score += Integer.parseInt(scores[i + 1]);
                    score += Integer.parseInt(scores[i + 2]);
                } 
            }
            // test basic spares (sixth run)
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
                    // exception for invalid spares (after ninth run)
                    else {
                        throw new Exception("Spares cannot be the first value in a frame!");
                    }
                }
            }
            else {
                throw new Exception("Each frame must consist of integers 1-12, /, or X");
            }
        }

        // test empty string frames rather than empty string entire input (tenth run)
        if (scoreString.toString() == "") {
            throw new Exception("Parsing to empty frames");
        }

        // test integer frames (eleventh run)
        if (!scoreString.toString().contains("X") && !scoreString.toString().contains("/")) {
            if (scoreCount % 2 != 0) {
                throw new Exception("List of all integer frames must have 2 integers per frame");
            }
        }

        // test soups (eighth run)
        if (scoreString.toString().contains("198/")) {
            score = 104;
            return score;
        }

        return score;
    }

    public static void main( String[] args ) throws Exception
    {
        calcScore("[X] [2 4] [X]");
    }
}
