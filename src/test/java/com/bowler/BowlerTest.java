package com.bowler;

import static org.junit.Assert.assertTrue;
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
    public void bowlRegularFrames()
    {
        int score1 = Bowler.calcScore("[1 3] [4 2] [5 2]");
        assertEquals(score1, 17);
    }

    // Third Run: added score2 and score3 to test functionality for all same values or all zero values
    @Test
    public void bowlAllSameFrames()
    {
        int score2 = Bowler.calcScore("[1 1] [1 1] [1 1]");
        assertEquals(score2, 6);
    }

    @Test
    public void bowlAllZeroes()
    {
        int score3 = Bowler.calcScore("[0 0] [0 0] [0 0]");
        assertEquals(score3, 0);
    }

    // Fourth Run: wanted to test strikes and spares by creating exceptional cases for character inputs

    @Test
    public void bowlStrike()
    {
        int score4 = Bowler.calcScore("[X] [2 4] [1 1]");
        assertEquals(score4, 27);
    }

}
