package com.example.consumoapirickandmorty.model.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.consumoapirickandmorty.model.dataClass.*

@Database(entities = [Results::class,Favorite::class], version = 1)

abstract class DataBasem : RoomDatabase() {

    abstract fun daoPersonajes():Dao
    companion object{
        @Volatile
        private var INSTANCE: DataBasem?=null

        fun getDataBasem(context : Context):DataBasem{
            val createdInstance = INSTANCE
            if(createdInstance!=null){
                return createdInstance
            }
            synchronized(this){
                val newInstance = Room.databaseBuilder(context.applicationContext,DataBasem::class.java,"movies_db")
                    .fallbackToDestructiveMigrationFrom(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26).build()
                INSTANCE=newInstance
                return newInstance
            }
        }
    }
}
