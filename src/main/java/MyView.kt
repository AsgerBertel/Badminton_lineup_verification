import tornadofx.*

class MyView: View()  {
    override val root = vbox {
        val downloadButton = button("Download Players")
    }
}