package model

import exception.WrongSexException
import function.IllegalityVerdict
import model.lineupError.LineupError
import model.lineupError.LineupVerdict

class PositionSpot(val sexReq:Sex?) {
    var player:Player = Player()
        set(value) {
            if (value.badmintonId != 0 && sexReq != null && value.sex != sexReq)
                throw WrongSexException("Wrong sex of player. Expected $sexReq but got ${player.sex}.")
            field = value
        }

    var verdict = IllegalityVerdict.UNKNOWN

    val errors = mutableListOf<LineupVerdict>()
}