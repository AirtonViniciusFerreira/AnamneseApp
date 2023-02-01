package com.example.anamnesedrapp.config

import android.content.Context
import androidx.room.*
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.anamnesedrapp.geral.entity.PessoaEntity
import com.example.anamnesedrapp.geral.entity.TipoPessoaEnum
import com.example.anamnesedrapp.geral.repository.dao.PessoaDao
import com.example.anamnesedrapp.tutor.entity.TutorEntity
import com.example.anamnesedrapp.tutor.repository.dao.TutorDao
import com.example.anamnesedrapp.usuario.repository.dao.UsuarioDao
import com.example.anamnesedrapp.usuario.entity.UsuarioEntity

@Database(
    entities = [
        UsuarioEntity::class,
        PessoaEntity::class,
        TutorEntity::class
    ],
    version = 2,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
//        AutoMigration(from = 2, to = 3),
//        AutoMigration(from = 3, to = 4, spec = AppDataBase.PessoaAlterColum::class),
    ]
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao
    abstract fun tutorDao(): TutorDao
    abstract fun pessoaDao(): PessoaDao


//    companion object {
//        @Volatile
//        private var INSTANCE: AppDataBase? = null
//
//        fun getDatabase(context: Context): AppDataBase {
//            val tempInstance = INSTANCE
//            if (tempInstance != null)
//                return tempInstance
//
//            synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    AppDataBase::class.java,
//                    ""
//                ).build()
//                INSTANCE = instance
//                return instance
//            }
//        }
//    }
}

