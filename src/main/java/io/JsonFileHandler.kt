package io

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import model.Player
import java.io.File
import java.lang.reflect.Type


class JsonFileHandler {
    companion object {
        private val gson = Gson()

        fun saveJsonPlayerFile(players: List<Player>) {
            val gson = GsonBuilder().create()

            if (!File(OSHandler.appdataPath).exists())
                File(OSHandler.appdataPath).mkdir()

            File("${OSHandler.appdataPath}//playerslist.json").writeText(gson.toJson(players))
        }

        fun loadPlayerFile(): List<Player> {
            val playerString = File(OSHandler.appdataPath + "/playerslist.json").readText()
            val collectionType: Type? = object : TypeToken<List<Player?>?>() {}.type

            // If local string is empty, return empty list
            if (playerString.isEmpty())
                return listOf()
            return gson.fromJson(playerString, collectionType)
        }
    }
}
