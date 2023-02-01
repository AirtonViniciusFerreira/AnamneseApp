package com.example.anamnesedrapp.geral.repository

import com.example.anamnesedrapp.geral.entity.PessoaEntity

interface PessoaRepository {
    suspend fun create(pessoa: PessoaEntity): Result<PessoaEntity>

    suspend fun update(pessoa: PessoaEntity): Result<PessoaEntity>

    suspend fun delete(pessoa: PessoaEntity): Result<Int>

    suspend fun findAll(): Result<List<PessoaEntity>>
}