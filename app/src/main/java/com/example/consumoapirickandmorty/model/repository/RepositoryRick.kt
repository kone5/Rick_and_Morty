package com.example.consumoapirickandmorty.model.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.example.consumoapirickandmorty.model.dataBase.DataBasem
import com.example.consumoapirickandmorty.model.dataClass.Favorite
import com.example.consumoapirickandmorty.model.dataClass.Results
import com.example.consumoapirickandmorty.model.dataClass.RickandMorty
import com.example.consumoapirickandmorty.model.retrofit.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryRick(context: Context) {
    private val mRetrofit= RetrofitClient.retrofitInstance()
    private val mContext=context

    private val mDb=DataBasem.getDataBasem(context)
    private val dao=mDb.daoPersonajes()

    fun insertdataDB(page:Int){
        mRetrofit.getAllcharacterFromApi(page).enqueue(object : Callback<RickandMorty> {
            override fun onResponse(call: Call<RickandMorty>, response: Response<RickandMorty>) {
                Log.d("pame","llega de internet ${response.body()}")
                response.body()?.let {
                    CoroutineScope(IO).launch {
                        it.results.let { it -> dao.insertAllPersonajes(it) }
                    }
                }
            }

            override fun onFailure(call: Call<RickandMorty>, t: Throwable) {
                Toast.makeText(mContext,"Internet request failed", Toast.LENGTH_LONG).show()
                Log.d("pame","no llega de internet")
            }
        })
    }

    fun getPersonajesFromDB(): LiveData<List<Results>> {
        return dao.getAllPersonajes()
    }

    fun saveFavoriteRepo(id:Int){
        CoroutineScope(IO).launch {
            val obtener =dao.getDBfavorite(id)
            val setearfavoritos= Favorite(obtener.id,obtener.status,obtener.species,obtener.name,obtener.image)
            dao.insertfavorite(setearfavoritos)
        }
    }

    fun getAllFavDao(): LiveData<List<Favorite>>{
        return  dao.getallfavorite()
    }

    fun deleteFavoriterepo(id:Int){
        CoroutineScope(IO).launch {
            val favodeleterepo=dao.getCachedFavoriteiddelete(id)
            dao.deleteFavoriteobjeto(favodeleterepo)}
    }
}

