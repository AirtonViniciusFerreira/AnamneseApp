package com.example.anamnesedrapp.geral.repository.impl

import com.example.anamnesedrapp.geral.entity.PessoaEntity
import com.example.anamnesedrapp.geral.repository.PessoaRepository
import com.example.anamnesedrapp.geral.repository.dao.PessoaDao
import com.example.anamnesedrapp.usuario.entity.UsuarioEntity
import com.example.anamnesedrapp.usuario.repository.UsuarioRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import kotlin.properties.Delegates

class PessoaRepositoryImpl @Inject constructor(
    private val pessoaDao: PessoaDao
) : PessoaRepository {
    override suspend fun create(pessoa: PessoaEntity): Result<PessoaEntity> {
        var id by Delegates.notNull<Long>()
        try {
            runBlocking {
                launch {
                     id = pessoaDao.save(pessoa)
                }
            }
        } catch (ex: Exception) {
            return Result.failure(ex)
        }
        return Result.success(pessoa.copy(id = id))
    }

    override suspend fun update(pessoa: PessoaEntity): Result<PessoaEntity> {
        var id by Delegates.notNull<Long>()
        try {
            runBlocking {
                launch {
                    id = pessoaDao.save(pessoa)
                }
            }
        } catch (ex: Exception) {
            return Result.failure(ex)
        }
        return Result.success(pessoa)
    }

    override suspend fun delete(pessoa: PessoaEntity): Result<Int> {
        var count by Delegates.notNull<Int>()
        try {
            runBlocking {
                launch {
                    count = pessoaDao.delete(pessoa)
                }
            }
        } catch (ex: Exception) {
            return Result.failure(ex)
        }
        return Result.success(count)
    }

    override suspend fun findAll(): Result<List<PessoaEntity>> {
        lateinit var pessoas: List<PessoaEntity>
        try {
            runBlocking {
                launch {
                    pessoas = pessoaDao.getAllPessoa()
                }
            }
        } catch (ex: Exception) {
            return Result.failure(ex)
        }
        return Result.success(pessoas)
    }

}