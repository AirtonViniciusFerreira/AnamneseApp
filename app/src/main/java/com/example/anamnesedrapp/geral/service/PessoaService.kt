package com.example.anamnesedrapp.geral.service

import com.example.anamnesedrapp.geral.entity.PessoaEntity
import com.example.anamnesedrapp.geral.service.dto.PessoaDto

interface PessoaService {
    suspend fun create(pessoaDto: PessoaDto): Result<PessoaDto>

    suspend fun update(pessoaDto: PessoaDto): Result<PessoaDto>

    suspend fun delete(pessoaDto: PessoaDto): Result<Boolean>

    suspend fun findAll(): Result<List<PessoaDto>>
}