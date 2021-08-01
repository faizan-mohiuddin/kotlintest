import kotlin.random.Random

var numberOfItems = 0

open class Entity {

    open val name = "Entity"

}

class Player : Entity() {


}

class Monster : Entity() {



}

open class Item : Entity() {

    init {
        numberOfItems += 1
    }

    override val name = "Item"

    val itemID = numberOfItems
    open val rarity = 0 // rarities: 0 = unknown, 1 = common, 2 = rare, 3 = epic, 4 = legendary

}

class Weapon(override val name: String) : Item() {

    override val rarity = Random.nextInt(1, 5) // random rarity between and including 1 and 4

    var attackPower = 0

    init {

        attackPower = when (rarity) {

            1 -> Random.nextInt(1, 3)
            2 -> Random.nextInt(4, 6)
            3 -> Random.nextInt(7, 9)
            4 -> Random.nextInt(10, 12)
            else -> throw Exception("Error initialising attack power of Weapon.")

        }
    }

}

fun main() {

    for (i in 1..20) {

        val weapon1 = Weapon("Sword")
        println("Weapon name: ${weapon1.name}")
        println("Weapon AP: ${weapon1.attackPower}")
        println("Weapon rarity: ${weapon1.rarity}")
        println("Weapon ID: ${weapon1.itemID}")
        println("")

    }
}
