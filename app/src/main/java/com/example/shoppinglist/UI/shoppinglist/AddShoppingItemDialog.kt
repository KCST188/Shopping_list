package com.example.shoppinglist.UI.shoppinglist

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.shoppinglist.Data.db.entities.ShoppingItem
import com.example.shoppinglist.R


class AddShoppingItemDialog(context: Context, var addDialogListener: AddDialogListener): AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_new_item)

        findViewById<TextView>(R.id.tvAdd)?.setOnClickListener {
            val name =  findViewById<TextView>(R.id.tvItem)?.text.toString()
            val amount =  findViewById<TextView>(R.id.tvamount)?.text.toString()

            if(name.isEmpty() or amount.isEmpty())
            {
                Toast.makeText(context, "Enter all information", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val item = ShoppingItem(name, amount.toInt())
            addDialogListener.onAddButtonClicked(item)
            dismiss()
        }

        findViewById<TextView>(R.id.tvCancel)?.setOnClickListener {
            cancel()
        }
    }
}