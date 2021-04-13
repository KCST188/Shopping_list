package com.example.shoppinglist.UI.shoppinglist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shoppinglist.Data.repositories.ListsRepository


class ListsViewModelFactory (
    private val repository1: ListsRepository
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ListsViewModel(repository1) as T
    }
}