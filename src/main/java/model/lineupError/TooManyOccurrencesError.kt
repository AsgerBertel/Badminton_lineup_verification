package model.lineupError

import model.Player

class TooManyOccurrencesError(p: Player, count: Int) : LineupError("${p.name} occurred too many times ($count)")