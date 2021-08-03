import kotlin.random.Random

class Weapon() : Item() {

    // TODO change item rarity to not just be random but instead make sure higher rarities come up less often
    override val rarity = Random.nextInt(1, 5) // random rarity between and including 1 and 4

    var attackPower = 0
    override var name: String = ""

    init {

        when (rarity) {

            1 -> {attackPower = Random.nextInt(1, 3)
                this.name = "Fists"}

            2 -> {attackPower = Random.nextInt(3, 5)
                this.name = "Dagger"}

            3 -> {attackPower = Random.nextInt(5, 8)
                this.name = "Sword"}

            4 -> {attackPower = Random.nextInt(8, 11)
                this.name = "Greatsword"}

            else -> throw Exception("Error initialising attack power of Weapon.")

        }

    }

}