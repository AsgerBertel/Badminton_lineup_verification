package io

import model.Player
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.File
import java.io.FileWriter
import java.lang.reflect.Type


class JsonFileHandler {
    companion object {
        private val gson = Gson()

        fun saveJsonPlayerFile(players: List<Player>) {
            FileWriter("src/main/resources/PlayerList.json").use { writer ->
                val gson = GsonBuilder().create()
                gson.toJson(players, writer)
            }
        }

        fun loadPlayerFile(pathToFile: String):List<Player> {
            val bufferedReader: BufferedReader = File(pathToFile).bufferedReader()
            val playerString = bufferedReader.use { it.readText() }
            val collectionType: Type? = object : TypeToken<List<Player?>?>() {}.type
            return gson.fromJson(playerString, collectionType)
        }
    }
}