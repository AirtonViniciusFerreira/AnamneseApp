package com.example.anamnesedrapp.tutor.service.impl

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.anamnesedrapp.geral.entity.toPessoaDto
import com.example.anamnesedrapp.geral.service.PessoaService
import com.example.anamnesedrapp.geral.service.dto.PessoaDto
import com.example.anamnesedrapp.geral.service.dto.toPessoaEntity
import com.example.anamnesedrapp.tutor.entity.toTutorDto
import com.example.anamnesedrapp.tutor.entity.toTutorPessoaDto
import com.example.anamnesedrapp.tutor.repository.TutorRepository
import com.example.anamnesedrapp.tutor.service.TutorService
import com.example.anamnesedrapp.tutor.service.dto.TutorDto
import com.example.anamnesedrapp.tutor.service.dto.TutorPessoaDto
import com.example.anamnesedrapp.tutor.service.dto.toTutorEntity
import com.example.anamnesedrapp.util.addOrRemove
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import java.security.InvalidParameterException
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.streams.toList

@Singleton
class TutorServiceImpl @Inject constructor(
    private val tutorRepository: TutorRepository,
    private val pessoaService: PessoaService
) : TutorService {
    override suspend fun create(tutorDto: TutorPessoaDto): Result<TutorPessoaDto> {
//        var result: Result<TutorPessoaDto> = Result.failure(InvalidParameterException("Comando não processado"))
        if (tutorDto.pessoa.id != 0L)
            return Result.failure(InvalidParameterException("pessoa invalida!"))

        var pessoaDto: PessoaDto = pessoaService.create(tutorDto.pessoa).getOrThrow()

        return tutorRepository.create(tutorDto.copy(pessoa = pessoaDto).toTutorEntity())
            .mapCatching { it.toTutorPessoaDto() }
    }

    override suspend fun update(tutorDto: TutorPessoaDto): Result<TutorPessoaDto> {
        if (tutorDto.pessoa.id == 0L)
            return Result.failure(InvalidParameterException("pessoa não cadastrada!"))
        if (tutorDto.id == 0L)
            return Result.failure(InvalidParameterException("tutor não cadastrado!"))

        var pessoaDto: PessoaDto = pessoaService.update(tutorDto.pessoa).getOrThrow()

        return tutorRepository.update(tutorDto.toTutorEntity())
            .mapCatching { it.toTutorPessoaDto() }
    }

    override suspend fun delete(tutorDto: TutorDto): Result<Boolean> {
        var result: Result<Boolean> =
            Result.failure(InvalidParameterException("Nenhum dado foi deletado!"))
        tutorRepository
            .delete(tutorDto.toTutorEntity())
            .fold(
                onSuccess = {
                    if (it > 0)
                        result = Result.success(true)
                    else
                        result =
                            Result.failure(InvalidParameterException("Nenhum dado foi deletado!"))
                },
                onFailure = {
                    result = Result.failure(it)
                }
            )
        return result
    }

    override suspend fun findAll(): Result<List<TutorDto>> {
        return tutorRepository.findAll()
            .mapCatching { lst -> lst.map { item -> item.toTutorDto() } }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override suspend fun findAllWithPessoa(): Result<List<TutorPessoaDto>> {
        return tutorRepository.findAllWithPessoa()
            .mapCatching { mapTutor ->
                mapTutor
                    .keys
                    .stream()
                    .map {
                        mapTutor.get(it)?.map { it.toPessoaDto() }
                            ?.let { it1 -> it.toTutorPessoaDto(it1.first()) }
                    }
                    .toList()
            }
    }

    override suspend fun findByIdWithPessoa(tutorId: Long): Result<TutorPessoaDto> {
        return tutorRepository.findByIdWithPessoa(tutorId)
            .mapCatching { mapSucess ->
                mapSucess
                    .keys
                    .first()
                    .let { tutorEntity ->
                        tutorEntity.toTutorPessoaDto(
                            mapSucess.get(tutorEntity)
                                ?.first()
                                ?.let { pessoaEntity -> pessoaEntity.toPessoaDto() } ?: PessoaDto()
                        )
                    }
            }
    }

    private val tutorSelecionado = MutableStateFlow(setOf<TutorPessoaDto>())
    override suspend fun mudarTutorSelecionado(tutorPessoaDto: TutorPessoaDto) {
        tutorSelecionado.update {
            it.addOrRemove(tutorPessoaDto)
        }
    }

    override suspend fun mudarTutorSelecionado(tutorId: Long) {
        tutorRepository.findByIdWithPessoa(tutorId)
            .onSuccess { mapSucess ->
                val tutorPessoaDto: TutorPessoaDto = mapSucess
                    .keys
                    .first()
                    .let { tutorEntity ->
                        tutorEntity.toTutorPessoaDto(
                            mapSucess.get(tutorEntity)
                                ?.first()
                                ?.let { pessoaEntity -> pessoaEntity.toPessoaDto() } ?: PessoaDto()
                        )
                    }
                tutorSelecionado.update {
                    it.addOrRemove(tutorPessoaDto)
                }
            }
    }

    override fun observeTutorSelecionado(): Flow<Set<TutorPessoaDto>> = tutorSelecionado
}