package com.example.yxj.demoindex;

import android.util.Log;

import junit.framework.TestCase;

/**
 * Created by yxj on 16/4/6.
 */
public class PersonTest extends TestCase {

    private Person person1;
    private Person person2;

    public void setUp() throws Exception {
        super.setUp();
        person1 = new Person("www","关咏荷");
        person2 = new Person("www","张家辉");
    }

    public void testCompareTo() throws Exception {
        int i = person1.compareTo(person2);
        assertEquals(i>0,i);
        Log.e("test",person1.pinyinIndex.getWholePinyin());
        Log.e("test", person1.pinyinIndex.getFirstLetter());
    }
}