import kotlin.random.Random

open class Consumable: Item() {

    override val rarity = Random.nextInt(1, 5)
    open val typeOfConsumable = ""

    open fun useConsumable(player: Player){

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

        for (item in player.playerInventory) {

            if (item?.itemID == this.itemID) {

                player.playerInventory.remove(item)
                break
            }
        }
    }


}

class Gift: Consumable() {

    override val typeOfConsumable = "Gift"
}