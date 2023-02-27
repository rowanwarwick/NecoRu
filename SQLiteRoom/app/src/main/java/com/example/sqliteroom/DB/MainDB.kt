package com.example.sqliteroom.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sqliteroom.DAO.DAO
import com.example.sqliteroom.DAO.Item

@Database(entities = [Item::class], version = 1)
abstract class MainDB: RoomDatabase() {
    abstract fun getData(): DAO
    companion object {
        fun getDB(context: Context) : MainDB {
            return Room.databaseBuilder(context.applicationContext, MainDB::class.java, "test.db").build()
        }
    }
}