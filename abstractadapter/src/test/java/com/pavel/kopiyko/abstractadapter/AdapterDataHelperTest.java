package com.pavel.kopiyko.abstractadapter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class AdapterDataHelperTest {
    private AdapterDataHelper helper;

    @Before
    public void setUp() throws Exception {
        helper = new AdapterDataHelper();
    }

    @After
    public void tearDown() throws Exception {
        helper.clear();
        helper = null;
    }

    @Test
    public void size() throws Exception {
        helper.clear();

        assertEquals(0, helper.size());

        helper.add(new ViewHolderData(0, 0, "0"));
        assertEquals(1, helper.size());

        helper.clear();
        assertEquals(0, helper.size());
    }

    @Test
    public void setData() throws Exception {
        helper.clear();

        List<ViewHolderData> toSend = new LinkedList<>();
        toSend.add(new ViewHolderData(1, 1, "1"));
        toSend.add(new ViewHolderData(2, 2, "2"));

        helper.setData(toSend);

        assertEquals(2, helper.size());
        assertEquals("1", helper.get(0).key);
    }

    @Test
    public void isEmpty() throws Exception {
        helper.clear();

        assertTrue(helper.isEmpty());
        helper.add(new ViewHolderData(1, 1, "1"));
        assertFalse(helper.isEmpty());

        helper.clear();
    }

    @Test
    public void clear() throws Exception {
        helper.clear();

        assertEquals(0, helper.size());
        helper.add(new ViewHolderData(1, 1, "1"));
        assertEquals(1, helper.size());

        helper.clear();

        assertTrue(helper.isEmpty());
    }


}