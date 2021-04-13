package com.example.shoppinglist.UI.shoppinglist

import com.example.shoppinglist.Data.db.entities.ShoppingList


interface AddDialogListenerList {
    fun onAddButtonClickedList (item: ShoppingList)
}