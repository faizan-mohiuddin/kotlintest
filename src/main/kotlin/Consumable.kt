import kotlin.random.Random

val listOfConsumables  = mutableListOf<Consumable>(HealthPotion(), ExpPotion())

open class Consumable: Item() {

    override val rarity = Random.nextInt(1, 5)
    open val typeOfConsumable = ""

    open fun useConsumable(player: Player){
        // gets overridden by sub-class function - better way to do this?
    }

    open fun removeItemFromInventory(player: Player) {

        for (item in player.playerInventory) {

            if (item?.itemID == this.itemID) {

                player.playerInventory.remove(item)
                break

            }
        }

    }

    fun randomConsumable(): Consumable {


        val randomNum = (0..listOfConsumables.size).random()
        var theConsumable: Consumable

        when (randomNum) {
            0 -> {
                return listOfConsumables[0]
            }
            1 -> {
                return listOfConsumables[1]
            }
            else -> {
                return HealthPotion()
            }
        }

    }

}

// abstract class for a potion consumable, used as 'intermediate' class
open class Potion: Consumable() {

    override val typeOfConsumable = "Potion"

}

class HealthPotion: Potion() {

    override val typeOfConsumable = "Health Potion"

    var healingPower = 0

    init {

        when (this.rarity) {

            1 -> {
                this.name = "Common Healing Potion"
                this.healingPower = 10
            }

            2 -> {
                this.name = "Rare Healing Potion"
                this.healingPower = 20
            }

            3 -> {
                this.name = "Epic Healing Potion"
                this.healingPower = 30
            }

            4 -> {
                this.name = "Legendary Healing Potion"
                this.healingPower = 50
            }

            else -> {
                throw Exception("Error creating Healing Potion.")
            }

        }
    }

    // use and remove the healing potion from inventory
    override fun useConsumable(player: Player) {

        player.playerHP += this.healingPower

        println("You have used a ${this.name}. ${this.healingPower} HP restored.")

        this.removeItemFromInventory(player)

    }


}

class ExpPotion: Potion() {

    override val typeOfConsumable = "EXP Potion"

    var experiencePower = 0

    init {

        when (this.rarity) {

            1 -> {
                this.name = "Common EXP Potion"
                this.experiencePower = 100
            }

            2 -> {
                this.name = "Rare EXP Potion"
                this.experiencePower = 200
            }

            3 -> {
                this.name = "Epic EXP Potion"
                this.experiencePower = 400
            }

            4 -> {
                this.name = "Legendary EXP Potion"
                this.experiencePower = 1000
            }

            else -> {
                throw Exception("Error creating Healing Potion.")
            }

        }

    }

    // use and remove the exp potion from inventory
    override fun useConsumable(player: Player) {

        player.playerExperience += this.experiencePower

        println("You have used a ${this.name}. ${this.experiencePower} EXP gained.")

        this.removeItemFromInventory(player)

    }
}

class Gift: Consumable() {

    override val typeOfConsumable = "Gift"
}