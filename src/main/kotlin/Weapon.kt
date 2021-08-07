
val listOfWeapons = mutableListOf<Weapon>(Fists(), Dagger(), Sword(), Greatsword())

open class Weapon : Item() {

    // TODO change item rarity to not just be random but instead make sure higher rarities come up less often
    override val rarity = 0 // random rarity between and including 1 and 4
    open var attackPower = 0
    override var name: String = ""

    fun randomWeapon(): Weapon {

        return when((0..listOfWeapons.size).random()) {
            0 -> Fists()
            1 -> Dagger()
            2 -> Sword()
            else -> Greatsword()
        }
    }

}

class Fists: Weapon() {

    override val rarity = 1
    override var attackPower = (1..3).random()
    override var name = "Fists"

}

class Dagger: Weapon() {

    override val rarity = 1
    override var attackPower = (4..6).random()
    override var name = "Dagger"

}

class Sword: Weapon() {

    override val rarity = 1
    override var attackPower = (7..9).random()
    override var name = "Sword"

}

class Greatsword: Weapon() {

    override val rarity = 1
    override var attackPower = (10..12).random()
    override var name = "Greatsword"

}