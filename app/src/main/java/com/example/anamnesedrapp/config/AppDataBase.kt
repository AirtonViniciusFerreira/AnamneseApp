package com.example.anamnesedrapp.config

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.anamnesedrapp.usuario.repository.dao.UsuarioDao
import com.example.anamnesedrapp.usuario.entity.UsuarioEntity

@Database(
    entities = [UsuarioEntity::class],
    version = 2,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(from = 1, to = 2,),
    ]
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao


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

