package io

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import model.Player
import java.io.FileWriter
import java.lang.reflect.Type


class JsonFileHandler {
    companion object {
        private val gson = Gson()

        fun saveJsonPlayerFile(players: List<Player>) {
            FileWriter(this::class.java.classLoader.getResource("PlayerList.json")!!.path).use { writer ->
                val gson = GsonBuilder().create()
                gson.toJson(players, writer)
            }
        }

        fun loadPlayerFile(): List<Player> {
            val playerString = this::class.java.classLoader.getResource("PlayerList.json")?.readText()
            val collectionType: Type? = object : TypeToken<List<Player?>?>() {}.type
            return gson.fromJson(playerString, collectionType) ?: listOf()
        }
    }
}