package com.example.shoppinglist.UI.shoppinglist

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppinglist.Data.db.ListsDatabase
import com.example.shoppinglist.Data.db.entities.ShoppingList
import com.example.shoppinglist.Data.repositories.ListsRepository
import com.example.shoppinglist.Other.ShoppingListAdapter
import com.example.shoppinglist.R

class ShoppingListActivity : AppCompatActivity() {
    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val database = ListsDatabase(this)
        val repository = ListsRepository(database)
        val factory = ListsViewModelFactory(repository)

        val viewModelList = ViewModelProviders.of(this, factory).get(ListsViewModel::class.java)

        val listAdapter = ShoppingListAdapter(listOf(), viewModelList)

        findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.Shopping_lists).layoutManager = LinearLayoutManager(this)
        findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.Shopping_lists).adapter = listAdapter

        viewModelList.getAllShoppingLists().observe(this, Observer {
            listAdapter.Lists = it
            listAdapter.notifyDataSetChanged()
        })
        findViewById<TextView>(R.id.ltName)?.setOnClickListener {
            Intent(this, ShoppingActivity::class.java).also {
                startActivity(it)
            }
        }

        findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(R.id.ltAdd).setOnClickListener {
            AddShoppingListDialog(this, object : AddDialogListenerList {
                override fun onAddButtonClickedList (item: ShoppingList) {
                    viewModelList.upsertList(item)
                }
            }).show()
        }
    }
}