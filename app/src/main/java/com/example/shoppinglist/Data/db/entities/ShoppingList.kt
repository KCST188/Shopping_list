package com.example.shoppinglist.Data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Shopping_list")
class ShoppingList(
    @ColumnInfo(name = "list_name")
    var ListName: String,
    @ColumnInfo(name = "list_check")
    var isArchived: Boolean = false,
    )
{
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}