package com.example.anamnesedrapp.config

import android.content.ContentValues
import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppDataBaseModule {
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDataBase {
        return Room
            .databaseBuilder(
                context.applicationContext,
                AppDataBase::class.java,
                "DrApp"
            )
            .addMigrations(MIGRATION_1_2)
            .fallbackToDestructiveMigration()
            .build()
    }
}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("INSERT INTO usuario(nomeUsuario, senha, criadoPor, criadoData, modificadoPor, modificadoData) " +
                "SELECT 'admin', 'admin23', 'system', 0, 'system', 0 ")
    }
}