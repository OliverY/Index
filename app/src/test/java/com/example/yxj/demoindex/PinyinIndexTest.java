package com.example.yxj.demoindex;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yxj on 16/4/6.
 */
public class PinyinIndexTest {

    private PinyinIndex mPinyinIndex;

    @Before
    public void setUp() throws Exception{
        mPinyinIndex = new PinyinIndex("严旭珺");
    }


    @Test
    public void testInit() throws Exception{
        String[] results = {""};
//        assertEquals(mPinyinIndex);
    }

}