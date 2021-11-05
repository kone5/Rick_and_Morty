package com.example.consumoapirickandmorty.model.dataClass

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tableFavorite")
data class Favorite (
    @PrimaryKey var id: Int?,
    var status: String?,
    var species: String?,
    var nombre: String?,
    var image: String?
)
