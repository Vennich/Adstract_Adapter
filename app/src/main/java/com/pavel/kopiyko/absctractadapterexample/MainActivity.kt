package com.pavel.kopiyko.absctractadapterexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.pavel.kopiyko.absctractadapterexample.adapter.factories.ViewHolderOneFactory
import com.pavel.kopiyko.absctractadapterexample.adapter.factories.ViewHolderThreeFactory
import com.pavel.kopiyko.absctractadapterexample.adapter.factories.ViewHolderTwoFactory
import com.pavel.kopiyko.abstractadapter.AbstractAdapter

public class MainActivity : AppCompatActivity(), RemoveItemListener, View.OnClickListener {

    private lateinit var recycler : RecyclerView

    private lateinit var adapter : AbstractAdapter

    private lateinit var factoryOne : ViewHolderOneFactory
    private lateinit var factoryTwo : ViewHolderTwoFactory
    private lateinit var factoryThree : ViewHolderThreeFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.add_item_1).setOnClickListener(this)
        findViewById<View>(R.id.add_item_2).setOnClickListener(this)
        findViewById<View>(R.id.add_item_3).setOnClickListener(this)

        recycler = findViewById(R.id.recycler)
        recycler.layoutManager = LinearLayoutManager(this)

        factoryOne = ViewHolderOneFactory(this)
        factoryTwo = ViewHolderTwoFactory(this)
        factoryThree = ViewHolderThreeFactory(this)

        adapter = AbstractAdapter.Builder(3)
                .setFactory(factoryOne)
                .setFactory(factoryTwo)
                .setFactory(factoryThree)
                .build()

        recycler.adapter = adapter
    }


    override fun onClick(view: View) {
        when (view.id) {
            R.id.add_item_1 -> adapter.add(factoryOne.createHolderData("For example", ""))
            R.id.add_item_2 -> adapter.add(factoryTwo.createHolderData("This text can print.", ""))
            R.id.add_item_3 -> adapter.add(factoryThree.createHolderData("But I don't wont", ""))
        }
    }

    override fun onRemove(position: Int) {
        adapter.removeItem(position)
    }
}
