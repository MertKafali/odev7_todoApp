package com.example.todoapp.room

import androidx.room.*
import com.example.todoapp.data.entity.Notlar

@Dao
interface NotlarDao {
    @Query("SELECT * FROM notlar")
    suspend fun tumNotlar() : List<Notlar>

    @Insert
    suspend fun notEkle(not:Notlar)

    @Update
    suspend fun notGuncelle(not:Notlar)

    @Delete
    suspend fun notSil(not:Notlar)

    @Query("SELECT * FROM notlar WHERE not_baslik like '%' || :aramaKelimesi || '%'")
    suspend fun notAra(aramaKelimesi:String) : List<Notlar>
}