package io

object OSHandler {
    val current: OS
    val appdataPath: String

    init {
        val os = System.getProperty("os.name").toLowerCase()

        current = when {
            os.contains("win") -> OS.WIN
            os.contains("mac") || os.contains("darwin") -> OS.MAC
            os.contains("nux") || os.contains("nix") || os.contains("aix") -> OS.LINUX
            else -> OS.OTHER
        }

        appdataPath = when(current) {
            OS.WIN -> System.getenv("APPDATA") + "\\TeamMatchVerify\\"
            OS.MAC -> System.getProperty("user.home") + "/Library/Application Support/TeamMatchVerify"
            OS.LINUX -> System.getProperty("user.home") + "/.TeamMatchVerify"
            OS.OTHER -> System.getProperty("user.home") + "/.TeamMatchVerify"
        }
    }
}