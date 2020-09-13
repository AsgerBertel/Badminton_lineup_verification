import model.Player
import model.Sex
import model.StandardLineupStructure

class FakeData {
    companion object {
        fun getLineup(): StandardLineupStructure {
            val lineup = StandardLineupStructure()

            val P1 = Player("Frederik Bjergen", 1, 1)
            P1.sex = Sex.MALE
            lineup.XD1.spot1.player = P1
            val P2 = Player("Sofie Røjkjær", 1, 1)
            P2.sex = Sex.FEMALE
            lineup.XD1.spot2.player = P2
            val P3 = Player("Lasse Sonnesen", 1, 1)
            P3.sex = Sex.MALE
            lineup.XD2.spot1.player = P3
            val P4 = Player("Jillie Knorborg", 1, 1)
            P4.sex = Sex.FEMALE
            lineup.XD2.spot2.player = P4

            val P5 = Player("Nikoline Søndergaard Laugesen", 1, 1)
            P5.sex = Sex.FEMALE
            lineup.WS1.spot.player = P5
            val P6 = Player("Ida Janum Riis", 1, 1)
            P6.sex = Sex.FEMALE
            lineup.WS2.spot.player = P6

            val P7 = Player("Frederik Bjergen", 1, 1)
            P7.sex = Sex.MALE
            lineup.MS1.spot.player = P7
            val P8 = Player("Christoffer Geisler", 1, 1)
            P8.sex = Sex.MALE
            lineup.MS2.spot.player = P8
            val P9 = Player("Kristian Ødum Nielsen", 1, 1)
            P9.sex = Sex.MALE
            lineup.MS3.spot.player = P9
            val P10 = Player("Philip Valentin Bak", 1, 1)
            P10.sex = Sex.MALE
            lineup.MS4.spot.player = P10

            lineup.WD1.spot1.player = P2
            lineup.WD1.spot2.player = P5
            lineup.WD2.spot1.player = P4
            lineup.WD2.spot2.player = P6

            lineup.MD1.spot1.player = P3
            lineup.MD1.spot2.player = P7
            lineup.MD2.spot1.player = P9
            lineup.MD2.spot2.player = P10
            lineup.MD3.spot1.player = P1
            lineup.MD3.spot2.player = P8

            return lineup
        }

        fun getPlayers(): List<Player> {
            val P1 = Player("Nicklas", 2, 2)
            P1.sex = Sex.MALE
            P1.singlesPoints = 500

            val P2 = Player("Anders", 2, 2)
            P2.sex = Sex.MALE
            P2.singlesPoints = 1000

            val P3 = Player("Ingrid", 3, 3)
            P3.sex = Sex.FEMALE
            P3.singlesPoints = 1500

            val P4 = Player("Hans", 3, 3)
            P4.sex = Sex.MALE
            P4.singlesPoints = 1500

            val P5 = Player("Niels", 3, 3)
            P5.sex = Sex.MALE
            P5.singlesPoints = 2000



            return listOf(P1, P2, P3, P4, P5)
        }
    }
}