package gui.viewModel

import model.StandardLineupStructure
import tornadofx.ItemViewModel

class StandardLineupStructureModel : ItemViewModel<StandardLineupStructure>() {
    val XD1 = bind(StandardLineupStructure::XD1)
    val XD2 = bind(StandardLineupStructure::XD2)

    val WS1 = bind(StandardLineupStructure::WS1)
    val WS2 = bind(StandardLineupStructure::WS2)

    val MS1 = bind(StandardLineupStructure::MS1)
    val MS2 = bind(StandardLineupStructure::MS2)
    val MS3 = bind(StandardLineupStructure::MS3)
    val MS4 = bind(StandardLineupStructure::MS4)

    val WD1 = bind(StandardLineupStructure::WD1)
    val WD2 = bind(StandardLineupStructure::WD2)

    val MD1 = bind(StandardLineupStructure::MD1)
    val MD2 = bind(StandardLineupStructure::MD2)
    val MD3 = bind(StandardLineupStructure::MD3)
}