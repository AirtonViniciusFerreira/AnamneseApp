package com.example.anamnesedrapp.tutor.repository

import com.example.anamnesedrapp.geral.entity.PessoaEntity
import com.example.anamnesedrapp.tutor.entity.TutorEntity

interface TutorRepository {
    suspend fun create(tutor: TutorEntity): Result<TutorEntity>

    suspend fun update(tutor: TutorEntity): Result<TutorEntity>

    suspend fun delete(tutor: TutorEntity): Result<Int>

    suspend fun findAll(): Result<List<TutorEntity>>

    suspend fun findAllWithPessoa(): Result<Map<TutorEntity, List<PessoaEntity>>>

    suspend fun findByIdWithPessoa(tutorId: Long): Result<Map<TutorEntity, List<PessoaEntity>>>
}