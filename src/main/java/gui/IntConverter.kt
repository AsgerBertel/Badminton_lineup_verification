package gui



import javafx.util.StringConverter
import model.Player

class IntConverter : StringConverter<Int>() {
    override fun toString(p0: Int): String = p0.toString()
    override fun fromString(p0: String): Int = p0.toInt()
}