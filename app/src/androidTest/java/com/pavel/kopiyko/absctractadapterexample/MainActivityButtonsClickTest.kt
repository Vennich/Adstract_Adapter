package com.pavel.kopiyko.absctractadapterexample

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.pavel.kopiyko.absctractadapterexample.adapter.factories.ViewHolderOneFactory
import com.pavel.kopiyko.absctractadapterexample.adapter.factories.ViewHolderThreeFactory
import com.pavel.kopiyko.absctractadapterexample.adapter.factories.ViewHolderTwoFactory
import com.pavel.kopiyko.abstractadapter.AbstractAdapter
import com.pavel.kopiyko.abstractadapter.HolderFactory
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
public open class MainActivityButtonsClickTest {

    @get:Rule
    public val activityTestRule : ActivityTestRule<MainActivity> = ActivityTestRule<MainActivity>(MainActivity::class.java)

    private lateinit var adapter: AbstractAdapter

    @Before
    fun setUp() {
        adapter = (activityTestRule.activity.findViewById<RecyclerView>(R.id.recycler).adapter) as AbstractAdapter
    }

    @Test
    fun addItemTypeOneTest() {
        assert(adapter.itemCount == 0)

        onView(withId(R.id.add_item_1))
                .perform(click())

        assert(adapter.itemCount == 1)
        assert(adapter.get(0).viewType == ViewHolderOneFactory.VIEW_TYPE)

        onView(withId(R.id.recycler))
                .perform(RecyclerViewActions
                        .actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))



        assert(adapter.itemCount == 0)
    }

    @Test
    fun addItemTypeTwoTest() {
        assert(adapter.itemCount == 0)

        onView(withId(R.id.add_item_2))
                .perform(click())


        assert(adapter.itemCount == 1)
        assert(adapter.get(0).viewType == ViewHolderTwoFactory.VIEW_TYPE)

        onView(withId(R.id.recycler))
                .perform(RecyclerViewActions
                        .actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))


        assert(adapter.itemCount == 0)
    }

    @Test
    fun addItemTypeThreeTest() {
        assert(adapter.itemCount == 0)

        onView(withId(R.id.add_item_3))
                .perform(click())

        assert(adapter.itemCount == 1)
        assert(adapter.get(0).viewType == ViewHolderThreeFactory.VIEW_TYPE)

        onView(withId(R.id.recycler))
                .perform(RecyclerViewActions
                        .actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))


        assert(adapter.itemCount == 0)
    }

    @Test
    fun manyItemAddingTest() {
        val adapter = adapter as AbstractAdapter
        assert(adapter.itemCount == 0)

        onView(withId(R.id.add_item_1))
                .perform(click())

        assert(adapter.get(0).viewType == ViewHolderOneFactory.VIEW_TYPE)

        onView(withId(R.id.add_item_3))
                .perform(click())
                .perform(click())
                .perform(click())

        assert(adapter.get(1).viewType == ViewHolderThreeFactory.VIEW_TYPE)

        onView(withId(R.id.add_item_2))
                .perform(click())
        assert(adapter.get(4).viewType == ViewHolderTwoFactory.VIEW_TYPE)


        assert(adapter.itemCount == 5)
    }

}