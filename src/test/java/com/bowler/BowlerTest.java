package com.bowler;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BowlerTest
{
    // comments with a * in front of them denote test cases that required changed functionality in main Bowler calcScore method

    // first test case written; should simply add all the frames
    // * First Run: getting unclosed character class issue with split method, need to refactor to prevent regex interpretation
    // * Second Run: getting numberformatexception for input string "", need to deal with parsing problems with spaces
        // needed to index at 1 in calcScore
    @Test
    public void testBowlRegularFrames()
    {
        int score1 = Bowler.calcScore("[1 3] [4 2] [5 2]");
        assertEquals(score1, 17);
    }

    // Third Run: added score2 and score3 to test functionality for all same values or all zero values
    @Test
    public void testBowlAllSameFrames()
    {
        int score2 = Bowler.calcScore("[1 1] [1 1] [1 1]");
        assertEquals(score2, 6);
    }

    @Test
    public void testBowlAllZeroes()
    {
        int score3 = Bowler.calcScore("[0 0] [0 0] [0 0]");
        assertEquals(score3, 0);
    }

    // * Fourth Run: wanted to test strikes and spares by creating exceptional cases for character inputs

    @Test
    public void testBowlStrike()
    {
        int score4 = Bowler.calcScore("[X] [2 4] [1 1]");
        int score5 = Bowler.calcScore("[2 2] [X] [4 5]");

        assertEquals(score4, 27);
        assertEquals(score5, 35);
    }

    // * Fifth Run: first implementation will generate out of bounds exception if strike is the last frame, needed to change
    @Test    
    public void testBowlStrikeLastFrame()
    {
        int score6 = Bowler.calcScore("[3 4] [8 2] [X]");
        assertEquals(score6, 30);
    }

    // * Sixth Run: adding spares

    @Test
    public void testBowlSpare()
    {
        int score7 = Bowler.calcScore("[2 /] [3 4] [5 3]");
        int score8 = Bowler.calcScore("[3 4] [5 /] [8 2]");

        assertEquals(score7, 31);
        assertEquals(score8, 38);
    }

    // * Seventh Run: just like bowlStrikeLastFrame, first implementation will generate out of bounds exception if strike is the last frame, needed to change
    @Test
    public void testBowlSpareLastFrame()
    {
        int score9 = Bowler.calcScore("[1 9] [1 1] [8 /]");
        assertEquals(score9, 25);
    }

    @Test
    public void testBowlSoup()
    {
        int score10 = Bowler.calcScore("[3 7] [1 9] [8 /]");
        assertEquals(score10, 104);
    }

}
