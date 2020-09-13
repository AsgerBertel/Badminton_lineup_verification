package model.lineupError

import model.Position

class PointDifferenceError(pos1: Position, pos2: Position) : LineupError("Too many points between $pos1 and $pos2")
