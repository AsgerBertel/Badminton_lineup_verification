package model.lineupError

import model.Player

class TooManyOccurencesInSameCategoryError(p: Player) : LineupError("$p occurred too many times in a category.")