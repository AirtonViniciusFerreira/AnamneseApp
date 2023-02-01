package com.example.anamnesedrapp.tutor.repository.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.anamnesedrapp.geral.entity.PessoaEntity
import com.example.anamnesedrapp.tutor.entity.TutorEntity

@Dao
interface TutorDao {
    @Insert(entity = TutorEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(tutor: TutorEntity): Long

    @Delete
    suspend fun delete(tutor: TutorEntity): Int

    @Query("SELECT * FROM  tutor")
    suspend fun finAll(): List<TutorEntity>

    @Query("SELECT " +
            " * " +
            " FROM tutor " +
            " JOIN pessoa on tutor.pessoaId = pessoa.id")
    suspend fun finAllWithPessoa(): Map<TutorEntity, List<PessoaEntity>>


    @Query("SELECT " +
            " * " +
            " FROM tutor " +
            " JOIN pessoa on tutor.pessoaId = pessoa.id " +
            " WHERE tutor.id = :tutorId ")
    suspend fun findByIdWithPessoa(tutorId: Long): Map<TutorEntity, List<PessoaEntity>>

//    @Query("SELECT * FROM tutor WHERE ")
//    fun findByPessoaCpfCnpj(cpfCnpj: String)
}