package com.example.shoppinglist.Other

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.Data.db.entities.ShoppingItem
import com.example.shoppinglist.R
import com.example.shoppinglist.UI.shoppinglist.ShoppingViewModel



class ShoppingItemAdapter (
    var items: List<ShoppingItem>,
    private val viewModel: ShoppingViewModel
    ):RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {
    inner class ShoppingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.shopping_item,
            parent,
            false)
        return ShoppingViewHolder(view)
    }

    private fun toggleStrikeThrough (tvName: TextView, isChecked: Boolean) {

        if(isChecked)
        {
            tvName.paintFlags = tvName.paintFlags or STRIKE_THRU_TEXT_FLAG
        }
        else
        {
            tvName.paintFlags = tvName.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }

    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val curShoppingItem = items[position]
        holder.itemView.apply {
            findViewById<TextView>(R.id.tvName).text = curShoppingItem.name
            findViewById<TextView>(R.id.tvAmount).text = "${curShoppingItem.amount}"
            findViewById<CheckBox>(R.id.isChecked).isChecked = curShoppingItem.isChecked
            toggleStrikeThrough(findViewById(R.id.tvName), curShoppingItem.isChecked)
            findViewById<CheckBox>(R.id.isChecked).setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(findViewById(R.id.tvName), isChecked)
                curShoppingItem.isChecked = !curShoppingItem.isChecked
            }
        }

        holder.itemView.findViewById<ImageView>(R.id.delete).setOnClickListener{
            viewModel.delete(curShoppingItem)
        }


    }

    override fun getItemCount(): Int {
        return items.size
    }



}