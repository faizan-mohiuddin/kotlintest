import kotlin.random.Random

class Armour: Item() {

    // TODO change item rarity to not just be random but instead make sure higher rarities come up less often
    override val rarity = Random.nextInt(1, 5) // random rarity between and including 1 and 4

    var defPower = 0
    override var name: String = ""

    init {

        when (rarity) {

            1 -> {defPower = Random.nextInt(1, 3)
                this.name = "Cloth armour"}

            2 -> {defPower = Random.nextInt(3, 5)
                this.name = "Leather armour"}

            3 -> {defPower = Random.nextInt(5, 8)
                this.name = "Chain armour"}

            4 -> {defPower = Random.nextInt(8, 11)
                this.name = "Plated armour"}

            else -> throw Exception("Error initialising defence power of Armour.")

        }

    }
}