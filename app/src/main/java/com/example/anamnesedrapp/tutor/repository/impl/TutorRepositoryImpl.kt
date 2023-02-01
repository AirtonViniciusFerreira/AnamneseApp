package com.example.anamnesedrapp.tutor.repository.impl

import com.example.anamnesedrapp.geral.entity.PessoaEntity
import com.example.anamnesedrapp.tutor.entity.TutorEntity
import com.example.anamnesedrapp.tutor.repository.TutorRepository
import com.example.anamnesedrapp.tutor.repository.dao.TutorDao
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.properties.Delegates

@Singleton
class TutorRepositoryImpl @Inject constructor(
    private val tutorDao: TutorDao
) : TutorRepository {
    override suspend fun create(tutor: TutorEntity): Result<TutorEntity> {
        var id by Delegates.notNull<Long>()
        try {
            runBlocking {
                launch {
                    id = tutorDao.save(tutor)
                }
            }
        } catch (ex: Exception) {
            return Result.failure(ex)
        }
        return Result.success(tutor.copy(id = id))
    }

    override suspend fun update(tutor: TutorEntity): Result<TutorEntity> {
        var id by Delegates.notNull<Long>()
        try {
            runBlocking {
                launch {
                    id = tutorDao.save(tutor)
                }
            }
        } catch (ex: Exception) {
            return Result.failure(ex)
        }
        return Result.success(tutor.copy())
    }

    override suspend fun delete(tutor: TutorEntity): Result<Int> {
        var count by Delegates.notNull<Int>()
        try {
            runBlocking {
                launch {
                    count = tutorDao.delete(tutor)
                }
            }
        } catch (ex: Exception) {
            return Result.failure(ex)
        }
        return Result.success(count)
    }

    override suspend fun findAll(): Result<List<TutorEntity>> {
        var lstTutores: List<TutorEntity> = emptyList()
        try {
            runBlocking {
                launch {
                    lstTutores = tutorDao.finAll()
                }
            }
        } catch (ex: Exception) {
            return Result.failure(ex)
        }
        return Result.success(lstTutores)
    }

    override suspend fun findAllWithPessoa(): Result<Map<TutorEntity, List<PessoaEntity>>> {
        var lstTutorEntity: Map<TutorEntity, List<PessoaEntity>> = mapOf()
        try {
            runBlocking {
                launch {
                    lstTutorEntity = tutorDao.finAllWithPessoa()
                }
            }
        } catch (ex: Exception) {
            return Result.failure(ex)
        }

        return Result.success(lstTutorEntity)
    }

    override suspend fun findByIdWithPessoa(tutorId: Long): Result<Map<TutorEntity, List<PessoaEntity>>> {
        var lstTutorEntity: Map<TutorEntity, List<PessoaEntity>> = mapOf()
        try {
            runBlocking {
                launch {
                    lstTutorEntity = tutorDao.findByIdWithPessoa(tutorId)
                }
            }
        } catch (ex: Exception) {
            return Result.failure(ex)
        }
        return Result.success(lstTutorEntity)
    }
}