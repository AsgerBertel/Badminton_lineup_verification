package model.lineupError

import model.Player

class TooFewOccurrencesWarning(p: Player) : LineupWarning("${p.name} only occurred once.")