
val listOfArmours = mutableListOf<Armour>(Cloth(), Leather(), Chain(), Plated())

open class Armour: Item() {

    // TODO change item rarity to not just be random but instead make sure higher rarities come up less often
    override val rarity = 0 // random rarity between and including 1 and 4
    open var defPower = 0
    override var name: String = ""

    fun randomArmour(): Armour {

        return when((0..listOfArmours.size).random()) {
            0 -> Cloth()
            1 -> Leather()
            2 -> Chain()
            else -> Plated()
        }
    }
}

class Cloth: Armour() {

    override val rarity = 1
    override var defPower = (1..3).random()
    override var name = "Cloth Armour"

}

class Leather: Armour() {

    override val rarity = 2
    override var defPower = (4..6).random()
    override var name = "Leather Armour"

}

class Chain: Armour() {

    override val rarity = 3
    override var defPower = (7..9).random()
    override var name = "Chain Armour"

}

class Plated: Armour() {

    override val rarity = 4
    override var defPower = (10..12).random()
    override var name = "Plated Armour"

}
