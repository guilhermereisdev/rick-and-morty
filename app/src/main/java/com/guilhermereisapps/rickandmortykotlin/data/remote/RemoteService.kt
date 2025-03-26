package com.guilhermereisapps.rickandmortykotlin.data.remote

import com.guilhermereisapps.rickandmortykotlin.data.model.character.Character
import com.guilhermereisapps.rickandmortykotlin.data.model.character.Characters
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RemoteService {

    @GET("character")
    suspend fun getAllCharacters(
        @Query("name") name: String? = null,
        @Query("status") status: String? = null,
        @Query("species") species: String? = null,
        @Query("gender") gender: String? = null,
        @Query("page") page: Int? = null
    ): Response<Characters>

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): Response<Character>
}
