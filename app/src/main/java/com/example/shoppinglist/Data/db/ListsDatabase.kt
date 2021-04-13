package com.example.shoppinglist.Data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shoppinglist.Data.db.entities.ShoppingList
import com.example.shoppinglist.UI.shoppinglist.ShoppingListActivity

@Database(
    entities = [ShoppingList::class],
    version = 1
)

abstract class ListsDatabase: RoomDatabase() {

    abstract fun getListsDao(): ListsDao

    companion object {
        @Volatile
        private var instance: ListsDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: ShoppingListActivity) = instance ?: synchronized(LOCK){
            createListDatabase(context).also { instance = it }
      }

        private  fun createListDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                ListsDatabase::class.java, "ListsDB.db").build()
    }
}