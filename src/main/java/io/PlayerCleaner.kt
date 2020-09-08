package io

import model.Player

class PlayerCleaner() {
    fun cleanPlayerList(players: List<Player>):List<Player>{
        for (player in players){
            if(player.name.endsWith("(EU)")){
                player.name.removeSuffix("(EU)")
            }
        }
        return players
    }
}