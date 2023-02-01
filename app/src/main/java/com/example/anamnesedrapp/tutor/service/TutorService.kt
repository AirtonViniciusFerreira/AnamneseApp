package com.example.anamnesedrapp.tutor.service

import com.example.anamnesedrapp.tutor.service.dto.TutorDto
import com.example.anamnesedrapp.tutor.service.dto.TutorPessoaDto
import kotlinx.coroutines.flow.Flow

interface TutorService {
    suspend fun create(tutorDto: TutorPessoaDto): Result<TutorPessoaDto>

    suspend fun  update(tutorDto: TutorPessoaDto): Result<TutorPessoaDto>

    suspend fun delete(tutorDto: TutorDto): Result<Boolean>

    suspend fun findAll(): kotlin.Result<List<TutorDto>>

    suspend fun  findAllWithPessoa(): Result<List<TutorPessoaDto>>

    suspend fun findByIdWithPessoa(tutorId: Long): Result<TutorPessoaDto>

    suspend fun mudarTutorSelecionado(tutorPessoaDto: TutorPessoaDto)
    suspend fun mudarTutorSelecionado(tutorId: Long)
    fun observeTutorSelecionado(): Flow<Set<TutorPessoaDto>>
}