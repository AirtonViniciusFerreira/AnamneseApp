package com.example.anamnesedrapp.geral.repository.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.anamnesedrapp.geral.entity.PessoaEntity

@Dao
interface PessoaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(pessoa: PessoaEntity): Long

    @Delete
    suspend fun delete(pessoa: PessoaEntity): Int

    @Query("SELECT * FROM pessoa")
    suspend fun getAllPessoa(): List<PessoaEntity>
}