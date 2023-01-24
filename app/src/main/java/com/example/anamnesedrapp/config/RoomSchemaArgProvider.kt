//package com.example.anamnesedrapp.config
//
//import java.io.File
//
//class RoomSchemaArgProvider(
//    @get:InputDirectory''
//    @get:PathSensitive(PathSensitivity.RELATIVE)
//    val schemaDir: File
//): CommandLineArgumentProvider {
//    override fun asArguments(): Iterable<String> {
//        // Note: If you're using KSP, you should change the line below to return
//        // listOf("room.schemaLocation=${schemaDir.path}")
//        return listOf("-Aroom.schemaLocation=${schemaDir.path}")
//    }
//}