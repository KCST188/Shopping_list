package com.example.shoppinglist.UI.shoppinglist

import androidx.lifecycle.ViewModel
import com.example.shoppinglist.Data.db.entities.ShoppingList
import com.example.shoppinglist.Data.repositories.ListsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListsViewModel (
    private val repository1: ListsRepository
    ): ViewModel() {

        fun upsertList(item: ShoppingList) = CoroutineScope(Dispatchers.IO).launch {
            repository1.upsertList(item)
        }

        fun deleteList(item: ShoppingList) = CoroutineScope(Dispatchers.IO).launch {
            repository1.deleteList(item)
        }

        fun getAllShoppingLists() = repository1.getAllShoppingLists()
}