package com.pavel.kopiyko.abstractadapter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class AdapterDataHelperBaseMethodsTest {
    private AdapterDataHelper helper;

    private ViewHolderData item0;
    private ViewHolderData item1;
    private ViewHolderData item2;
    private ViewHolderData item3;

    @Before
    public void setUp() throws Exception {
        helper = new AdapterDataHelper();

        item0 = new ViewHolderData(0, 0, "0");
        item1 = new ViewHolderData(1, 1, "1");
        item2 = new ViewHolderData(2, 2, "2");
        item3 = new ViewHolderData(3, 3, "3");
    }

    @After
    public void tearDown() throws Exception {
        helper.clear();
        helper = null;
    }

    @Test
    public void addAll() throws Exception {
        helper.clear();

        List<ViewHolderData> toSet = new LinkedList<>();
        toSet.add(item0);
        toSet.add(item1);
        toSet.add(item2);
        toSet.add(item3);

        assertEquals(0, helper.size());
        helper.addAll(toSet);
        assertEquals(toSet.size(), helper.size());
        helper.addAll(toSet);
        assertEquals(toSet.size() * 2, helper.size());

        assertEquals("0", helper.get(0).key);
        assertEquals("2", helper.get(2).key);

        helper.clear();
    }

    @Test
    public void add() throws Exception {
        helper.clear();

        helper.add(item0);
        assertEquals(item0, helper.get(0));

        helper.add(0, item1);
        assertEquals(item1, helper.get(0));

        assertEquals(2, helper.size());

        helper.clear();
        helper.add(0, item0);
        assertEquals(1, helper.size());


        helper.clear();
    }

    @Test
    public void set() throws Exception {
        helper.clear();

        helper.add(item0);

        assertEquals("0", helper.get(0).key);
        helper.set(0, item3);

        assertEquals("3", helper.get(0).key);

        helper.clear();
    }

    @Test
    public void removeItem() throws Exception {
        helper.clear();

        helper.add(item0);
        assertEquals(1, helper.size());

        helper.removeItem(item0.key);
        assertEquals(0, helper.size());

        helper.add(item0);
        helper.add(item1);
        helper.add(item2);

        helper.removeItem(1);
        assertEquals(item2, helper.get(1));

        helper.clear();

        helper.add(item0);
        helper.add(item1);
        helper.add(item2);

        helper.removeItem("0");
        helper.removeItem("2");
        assertEquals(item1, helper.get(0));

        helper.clear();
    }

    @Test
    public void getTest() throws Exception {
        helper.clear();

        helper.add(item0);
        helper.add(item1);

        assertEquals(item0, helper.get(0));
        assertEquals(item0, helper.get(item0.key));

        assertNull(helper.get(item3.key));
        helper.clear();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getTextExceptionText() {
        helper.clear();

        helper.get(0);
    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void setExceptionText() {
        helper.clear();

        helper.set(0, item0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addExceptionText() {
        helper.clear();
        helper.add(1, item0);
    }
}