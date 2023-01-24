package com.example.anamnesedrapp.usuario.repository.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.anamnesedrapp.usuario.entity.UsuarioEntity

@Dao
interface UsuarioDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(usuario: UsuarioEntity)

    @Delete
    suspend fun delete(usuario: UsuarioEntity)

    @Query("SELECT * FROM usuario")
    suspend fun getAllUsuario(): List<UsuarioEntity>

    @Query("SELECT * FROM usuario WHERE id = :id")
    suspend fun findById(id: Long): UsuarioEntity

    @Query("SELECT * FROM usuario WHERE nomeUsuario = :nomeUsuario AND senha = :senha")
    suspend fun getLogin(nomeUsuario: String, senha: String): UsuarioEntity
}