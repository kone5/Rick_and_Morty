package com.example.consumoapirickandmorty.model.dataBase

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.example.consumoapirickandmorty.model.dataClass.Favorite
import com.example.consumoapirickandmorty.model.dataClass.Results

@Dao
interface Dao {
    //Preguntar por que este debe tener onConflict, este sirve para mantener actualizado los nombres
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPersonajes(mPersonajes: List<Results>)

    //cuando agregas livedata no se requiere suspend
    @Query("SELECT * FROM tableresults" )
    fun getAllPersonajes(): LiveData<List<Results>>

    // para favoritos

    @Query("SELECT * FROM tableresults where id=:idfavorite" )
    fun getDBfavorite(idfavorite: Int): Results

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertfavorite(mFavorite: Favorite)

    @Query("SELECT * FROM tableFavorite" )
    fun getallfavorite(): LiveData<List<Favorite>>

    @Query("SELECT * FROM tableFavorite WHERE id =:idfavorite")
    fun getCachedFavoriteiddelete(idfavorite: Int):Favorite

    @Delete
    fun deleteFavoriteobjeto(favDeleted:Favorite)
}