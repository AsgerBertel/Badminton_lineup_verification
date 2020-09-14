package io

object OS {
    val current: OSType
    val appdataPath: String

    init {
        val os = System.getProperty("os.name").toLowerCase()

        current = when {
            os.contains("win") -> OSType.WIN
            os.contains("mac") || os.contains("darwin") -> OSType.MAC
            os.contains("nux") || os.contains("nix") || os.contains("aix") -> OSType.LINUX
            else -> OSType.OTHER
        }

        appdataPath = when (current) {
            OSType.WIN -> System.getenv("APPDATA") + "\\TeamMatchVerify\\"
            OSType.MAC -> System.getProperty("user.home") + "/Library/Application Support/TeamMatchVerify"
            OSType.LINUX -> System.getProperty("user.home") + "/.TeamMatchVerify"
            OSType.OTHER -> System.getProperty("user.home") + "/.TeamMatchVerify"
        }
    }
}

enum class OSType { LINUX, MAC, WIN, OTHER }