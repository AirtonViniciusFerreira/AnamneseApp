package com.example.anamnesedrapp.usuario.entity

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.anamnesedrapp.conversores.TimestampConverter
import com.example.anamnesedrapp.usuario.service.dto.UsuarioDTO
import com.example.anamnesedrapp.usuario.ui.vp.RegistraVP
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

@Entity(tableName = "usuario")
@TypeConverters(TimestampConverter::class)
data class UsuarioEntity constructor(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var nomeUsuario: String,
    var senha: String,
    @ColumnInfo(defaultValue = "system")
    var criadoPor: String = "system",
    var criadoData: Date,
    @ColumnInfo(defaultValue = "system")
    var modificadoPor: String = "system",
    var modificadoData: Date
)

fun RegistraVP.toUsuarioEntity(): UsuarioEntity {
    return with(this) {
        UsuarioEntity(
            nomeUsuario = this.nomeUsuario,
            senha =  this.senha,
            criadoPor = this.criadoPor,
            criadoData = this.criadoData,
            modificadoPor = this.modificadoPor,
            modificadoData = this.modificadoData
        )
    }
}

fun UsuarioEntity.toUsuarioDTO(): UsuarioDTO {
    return UsuarioDTO(
        id = this.id.toString(),
        nomeUsuario = this.nomeUsuario,
        criadoPor = this.criadoPor,
        criadoData = this.criadoData,
        modificadoPor = this.modificadoPor,
        modificadoData = this.modificadoData
    )
}