package com.example.shoppinglist.UI.shoppinglist

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.shoppinglist.Data.db.entities.ShoppingList
import com.example.shoppinglist.R
import java.util.*

class AddShoppingListDialog(context: Context, var addDialogListenerList: AddDialogListenerList): AppCompatDialog(context){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_new_list)

        findViewById<TextView>(R.id.ltAdd)?.setOnClickListener {
            val name = findViewById<TextView>(R.id.ltItem)?.text.toString()

            if(name.isEmpty())
            {
                Toast.makeText(context, "Enter all information", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val item = ShoppingList(name)
            addDialogListenerList.onAddButtonClickedList(item)
            dismiss()

        }

        findViewById<TextView>(R.id.ltCancel)?.setOnClickListener {
            cancel()
        }
    }
}