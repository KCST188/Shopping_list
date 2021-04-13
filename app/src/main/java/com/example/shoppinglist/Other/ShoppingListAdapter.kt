package com.example.shoppinglist.Other

import android.content.Intent
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.Data.db.entities.ShoppingItem
import com.example.shoppinglist.Data.db.entities.ShoppingList
import com.example.shoppinglist.R
import com.example.shoppinglist.UI.shoppinglist.ListsViewModel
import com.example.shoppinglist.UI.shoppinglist.ShoppingActivity

class ShoppingListAdapter(
    var Lists: List<ShoppingList>,
    private val viewModel: ListsViewModel
    ):RecyclerView.Adapter<ShoppingListAdapter.ShoppingViewHolder>() {
        inner class ShoppingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(
                R.layout.list_name,
                parent,
                false)
            return ShoppingViewHolder(view)
        }

        private fun toggleStrikeThrough (ltName: TextView, isChecked: Boolean) {

            if(isChecked)
            {
                ltName.paintFlags = ltName.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            }
            else
            {
                ltName.paintFlags = ltName.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }

        }

        override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
            val curShoppingList = Lists[position]
            holder.itemView.apply {
                findViewById<TextView>(R.id.ltName).text = curShoppingList.ListName
                findViewById<CheckBox>(R.id.isArchived).isChecked = curShoppingList.isArchived
                toggleStrikeThrough(findViewById(R.id.ltName), curShoppingList.isArchived)
                findViewById<CheckBox>(R.id.isArchived).setOnCheckedChangeListener { _, isChecked ->
                    toggleStrikeThrough(findViewById(R.id.ltName), isChecked)
                    curShoppingList.isArchived = !curShoppingList.isArchived
                }
            }

                holder.itemView.findViewById<ImageView>(R.id.ltDelete).setOnClickListener{
                    viewModel.deleteList(curShoppingList)
                }

            }

        override fun getItemCount(): Int {
            return Lists.size
        }
}