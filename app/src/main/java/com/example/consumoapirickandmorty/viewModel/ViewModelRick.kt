package com.example.consumoapirickandmorty.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.consumoapirickandmorty.model.dataClass.Favorite
import com.example.consumoapirickandmorty.model.dataClass.Results
import com.example.consumoapirickandmorty.model.dataClass.RickandMorty
import com.example.consumoapirickandmorty.model.repository.RepositoryRick
import kotlinx.coroutines.launch

class ViewModelRick(application: Application): AndroidViewModel(application) {

    private val mRepositoryVM=RepositoryRick(application)

    fun InsertDataVM(page:Int){
        mRepositoryVM.insertdataDB(page)
    }

    fun getRicky(): LiveData<List<Results>> {
        return mRepositoryVM.getPersonajesFromDB()
    }

    fun insertfavVM(id: Int) {
        mRepositoryVM.saveFavoriteRepo(id)

    }
    fun getallVMrepo(): LiveData<List<Favorite>>{
        return  mRepositoryVM.getAllFavDao()
    }

    fun deleteFav(id:Int){
        mRepositoryVM.deleteFavoriterepo(id)
    }
}