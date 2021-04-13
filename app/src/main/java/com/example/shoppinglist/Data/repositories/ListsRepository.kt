package com.example.shoppinglist.Data.repositories

import com.example.shoppinglist.Data.db.ListsDatabase
import com.example.shoppinglist.Data.db.entities.ShoppingList

class ListsRepository (
    private val dbl: ListsDatabase)
    {
        suspend fun upsertList(item: ShoppingList) = dbl.getListsDao().upsertList(item)

        suspend fun deleteList(item: ShoppingList) = dbl.getListsDao().deleteList(item)

        fun getAllShoppingLists() = dbl.getListsDao().getAllShoppingLists()
    }
