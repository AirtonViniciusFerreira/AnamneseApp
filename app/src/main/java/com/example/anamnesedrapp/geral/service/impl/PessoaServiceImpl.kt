package com.example.anamnesedrapp.geral.service.impl

import com.example.anamnesedrapp.geral.entity.toPessoaDto
import com.example.anamnesedrapp.geral.repository.PessoaRepository
import com.example.anamnesedrapp.geral.service.PessoaService
import com.example.anamnesedrapp.geral.service.dto.PessoaDto
import com.example.anamnesedrapp.geral.service.dto.toPessoaEntity
import java.security.InvalidParameterException
import javax.inject.Inject


class PessoaServiceImpl @Inject constructor(
    private val pessoaRepository: PessoaRepository
) : PessoaService {
    override suspend fun create(pessoaDto: PessoaDto): Result<PessoaDto> {
        var sRetorno = validarPessoa(pessoaDto)
        if (sRetorno.isNotBlank())
            return Result.failure(InvalidParameterException("pessoa com erros: ${sRetorno}"))
        return pessoaRepository.create(pessoaDto.toPessoaEntity()).mapCatching { it.toPessoaDto() }
    }

    override suspend fun update(pessoaDto: PessoaDto): Result<PessoaDto> {
        if (pessoaDto.id == 0L)
            return Result.failure(InvalidParameterException("pessoa não cadastrada"))
        var sRetorno = validarPessoa(pessoaDto)
        if (sRetorno.isNotBlank())
            return Result.failure(InvalidParameterException("pessoa com erros: ${sRetorno}"))
        return pessoaRepository.update(pessoaDto.toPessoaEntity()).mapCatching { it.toPessoaDto() }
    }

    override suspend fun delete(pessoaDto: PessoaDto): Result<Boolean> {
        var result: Result<Boolean> =
            Result.failure(InvalidParameterException("Nenhum dado foi deletado!"))
        pessoaRepository
            .delete(pessoaDto.toPessoaEntity())
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

    override suspend fun findAll(): Result<List<PessoaDto>> {
        return pessoaRepository
            .findAll()
            .mapCatching {
                it.map { item -> item.toPessoaDto() }
            }

    }

    private fun validarPessoa(pessoaDto: PessoaDto): String {
        if (pessoaDto.nomeCompletoRazaoSocial.isBlank())
            return "Nome não pode ser vazio!"
        if (pessoaDto.apelidoNomeFantasia.isBlank())
            return "Sobrenome não pode ser vazio!"
        if (pessoaDto.cpfCnpj.isBlank())
            return "Cpf ou Cnpj não pode ser vazio!"
        return ""
    }
}