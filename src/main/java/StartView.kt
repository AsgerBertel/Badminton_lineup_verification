import javafx.beans.Observable
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.value.ObservableValue
import javafx.geometry.Pos
import javafx.scene.Parent
import tornadofx.*

class StartView: View()  {
    override val root = vbox()
    init {
        with(root) {
            this.prefHeight = 100.0
            this.prefWidth = 300.0
            hbox(alignment = Pos.CENTER) {
                this += button("Update Players") {
                    runAsync {
                        action {
                            replaceWith(ProgressBarView())
                        }
                    }
                }
            }
        }
    }
}