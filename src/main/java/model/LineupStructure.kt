package model

import model.lineupError.LineupError

abstract class LineupStructure {
    abstract fun verify(): List<LineupError>
}