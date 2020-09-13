package model.lineupError

abstract class LineupVerdict(val message:String) {
    override fun toString(): String {
        return message
    }
}