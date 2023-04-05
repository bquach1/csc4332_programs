package com.bowler;

import static org.junit.Assert.*;

import org.junit.Test;

public class BowlerTest
{
    // comments with a * in front of them denote test cases that required changed functionality in main Bowler calcScore method

    // first test case written; should simply add all the frames
    // * First Run: getting unclosed character class issue with split method, need to refactor to prevent regex interpretation
    // * Second Run: getting numberformatexception for input string "", need to deal with parsing problems with spaces
        // needed to index at 1 in calcScore
    @Test
    public void testBowlRegularFrames() throws Exception
    {
        int score1 = Bowler.calcScore("[1 3] [4 2] [5 2]");
        assertEquals(score1, 17);
    }

    // Third Run: added score2 and score3 to test functionality for all same values or all zero values
    @Test
    public void testBowlAllSameFrames() throws Exception
    {
        int score2 = Bowler.calcScore("[1 1] [1 1] [1 1]");
        assertEquals(score2, 6);
    }

    @Test
    public void testBowlAllZeroes() throws Exception
    {
        int score3 = Bowler.calcScore("[0 0] [0 0] [0 0]");
        assertEquals(score3, 0);
    }

    @Test
    public void testBowlTwoDigitFrame() throws Exception
    {
        int score4 = Bowler.calcScore("[10 2] [4 5] [6 6]");
        assertEquals(score4, 33);
    }

    // * Fourth Run: wanted to test strikes and spares by creating exceptional cases for character inputs

    @Test
    public void testBowlStrike() throws Exception
    {
        int score5 = Bowler.calcScore("[2 2] [X] [4 5]");

        assertEquals(score5, 35);
    }

    // * Fifth Run: first implementation will generate out of bounds exception if strike is the last frame, needed to change
    @Test    
    public void testBowlStrikeLastFrame() throws Exception
    {
        int score6 = Bowler.calcScore("[3 4] [8 2] [X]");
        assertEquals(score6, 30);
    }

    // * Sixth Run: adding spares

    @Test
    public void testBowlSpare() throws Exception
    {
        int score7 = Bowler.calcScore("[3 4] [5 /] [8 2]");

        assertEquals(score7, 38);
    }

    @Test
    public void testBowlAllSpares() throws Exception
    {
        int score8 = Bowler.calcScore("[3 /] [5 /] [8 /]");

        assertEquals(score8, 52);
    }

    // * Seventh Run: just like bowlStrikeLastFrame, first implementation will 
    // generate out of bounds exception if strike is the last frame, needed to change
    @Test
    public void testBowlSpareLastFrame() throws Exception
    {
        int score9 = Bowler.calcScore("[1 9] [1 1] [8 /]");
        assertEquals(score9, 25);
    }

    // * Eighth Run: test soup formatting
    @Test
    public void testBowlSoup() throws Exception
    {
        int score10 = Bowler.calcScore("[3 7] [1 9] [8 /]");
        assertEquals(score10, 104);
    }

    @Test
    public void testBowlSoupShortCircuit() throws Exception
    {
        int score11 = Bowler.calcScore("[1 9] [8 /]");
        assertEquals(score11, 104);
    }

    public void testBowlNonconsecutiveSoup() throws Exception
    {
        int score12 = Bowler.calcScore("[1 9] [5 4] [8 /]");
        assertEquals(score12, 27);
    }

    // * Ninth Run: Need to tweak lookahead to detect if multiple string inputs are
    // present rather than just add next frame if strike is detected (type error)

    @Test
    public void testBowlAllStrikes() throws Exception
    {
        int score13 = Bowler.calcScore("[X] [X] [X]");
        assertEquals(score13, 65);
    }

    // * at first, I thought my implementation would be a problem if the values 1, 9, 8, and / appeared
    // but not as two separate frames of [1 9] and [8 /]. However, that would result in a frame with value 17,
    // which is not valid as it is greater than 13 pins, and a spare / in an even-indexed location, which
    // is not valid as the spare has to be the last index in the frame. Dealing with those individually would
    // make my soup implementation valid.
    @Test(expected = Exception.class)
    public void testBowlInvalidSpare() throws Exception
    {
        Bowler.calcScore("[/ 1] [5 4] [6 2]");
    }

    @Test(expected = Exception.class)
    public void testBowlSoupString() throws Exception
    {
        Bowler.calcScore("198/");
    }

    @Test(expected = Exception.class)
    public void testBowlInvalidSpareSingleFrame() throws Exception
    {
        Bowler.calcScore("[6 1] [/] [6 2]");
    }

    @Test(expected = Exception.class)
    public void testBowlAllInvalidSpares() throws Exception
    {
        Bowler.calcScore("[/] [/] [/]");
    }

    // * Tenth Run: add exception handling for empty strings, null input,
    // improper character input, etc.

    @Test(expected = IllegalArgumentException.class)
    public void testBowlEmptyStringFrames() throws Exception
    {
        Bowler.calcScore("");
    }

    @Test(expected = NullPointerException.class)
    public void testBowlNullString() throws Exception
    {
        Bowler.calcScore(null);
    }
    
    @Test(expected = Exception.class)
    public void testBowlInvalidCharacters() throws Exception
    {
        Bowler.calcScore("[2 5] [H 3] [6 4]");
    }

    @Test(expected = Exception.class)
    public void testMixedDigitLetterInput() throws Exception
    {
        Bowler.calcScore("[2H 5] [3 2] [6 4]");
    }

    // Eleventh Run: thought of previous errors like if one score was recorded for a frame
    // (i.e. incomplete round), or no score recorded for frame.

    @Test(expected = Exception.class)
    public void testBowlOddIntegerFrames() throws Exception
    {
        Bowler.calcScore("[1] [2 5] [9 2]");
    }

    @Test(expected = Exception.class)
    public void testBowlMultipleOddIntegerFrames() throws Exception
    {
        Bowler.calcScore("[1] [2] [9 2]");
    }

    @Test(expected = Exception.class)
    public void testBowlEmptyFrame() throws Exception
    {
        Bowler.calcScore("[2 4] [] [6 3]");
    }

    // * Twelfth Run: Implemented stacks to see mismatched bracket inputs
    // or incorrect formatting for separators.

    @Test(expected = Exception.class)
    public void testUnclosedFrames() throws Exception
    {
        Bowler.calcScore("[2 3] [4 5] [8 2");
    }

    @Test(expected = Exception.class)
    public void testEqualUnclosedFrames() throws Exception
    {
        Bowler.calcScore("[2 3] 4 5] [8 2");
    }

    @Test(expected = Exception.class)
    public void testUnidentifiedBracketFrames() throws Exception
    {
        Bowler.calcScore("{2 3} {4 5} {8 2}");
    }

    @Test(expected = Exception.class)
    public void testNoBracketFrames() throws Exception
    {
        Bowler.calcScore("2 3 4 5 8 2");
    }

    @Test(expected = Exception.class)
    public void testRandomWordInput() throws Exception
    {
        Bowler.calcScore("Sweet");
    }

    @Test(expected = Exception.class)
    public void testRandomSpaceFrames() throws Exception
    {
        Bowler.calcScore("[       2 3] [4 5] [8 2]");
    }

    @Test(expected = Exception.class)
    public void testBowlStrikeInsteadOfSpare() throws Exception
    {
        Bowler.calcScore("[4 5] [5 X] [3 2]");
    }
    
    @Test(expected = Exception.class)
    public void testBowlFrameGreaterThan13() throws Exception
    {
        Bowler.calcScore("[9 8] [4 5] [3 2]");
    }

    @Test(expected = Exception.class) 
    public void testMoreThanTwoFrameEntries() throws Exception
    {
        Bowler.calcScore("[3 6] [2 4] [1 2 3 4]");
    }

}