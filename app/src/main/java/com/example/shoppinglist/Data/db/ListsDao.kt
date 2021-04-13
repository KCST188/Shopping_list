package com.example.shoppinglist.Data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shoppinglist.Data.db.entities.ShoppingList

@Dao
interface ListsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun upsertList(item: ShoppingList)

    @Delete
    suspend fun deleteList(item: ShoppingList)

    @Query("SELECT * FROM shopping_list")
    fun getAllShoppingLists(): LiveData<List<ShoppingList>>
}