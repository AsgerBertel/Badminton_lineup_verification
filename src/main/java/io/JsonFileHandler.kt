package io

import model.Player
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.File
import java.io.FileWriter
import java.lang.reflect.Type
import java.nio.file.Files
import java.nio.file.Path


class JsonFileHandler {
    companion object {
        private val gson = Gson()

        private val appdataPath
            get() = run {
                val os = System.getProperty("os.name").toLowerCase()
                when {
                    os.contains("win") -> "%APPDATA%\\TeamMatchVerify\\"
                    os.contains("mac") || os.contains("darwin") -> "~/Library/Application Support/TeamMatchVerify"
                    os.contains("nux") || os.contains("nix") || os.contains("aix") -> System.getProperty("user.home") + "/.TeamMatchVerify"
                    else -> ""
                }
            }

        fun saveJsonPlayerFile(players: List<Player>) {
            val gson = GsonBuilder().create()

            if (!File(appdataPath).exists())
                File(appdataPath).mkdir()

            File("$appdataPath//playerslist.json").writeText(gson.toJson(players))
        }

        fun loadPlayerFile(): List<Player> {
            val playerString = File(appdataPath + "/playerslist.json").readText()
            val collectionType: Type? = object : TypeToken<List<Player?>?>() {}.type

            // If local string is empty, return empty list
            if (playerString.isEmpty())
                return listOf()
            return gson.fromJson(playerString, collectionType)
        }
    }
}
