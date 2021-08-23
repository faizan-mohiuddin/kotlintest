import kotlin.random.Random

val listOfConsumables  = mutableListOf<Consumable>(HealthPotion(), ExpPotion())

open class Consumable: Item() {

    override val rarity = Random.nextInt(1, 5)
    open val typeOfConsumable = ""

    // this is trash code
    fun randomConsumable(): Consumable {


        return when ((0..listOfConsumables.size).random()) {
            0 -> HealthPotion()
            else -> ExpPotion()
        }

    }

    // gets overridden
    open fun getPrice(): Float {
        return 0F
    }

}

// abstract class for a potion consumable, used as 'intermediate' class
open class Potion: Consumable() {

    override val typeOfConsumable = "Potion"

}

class HealthPotion: Potion() {

    override val typeOfConsumable = "Health Potion"

    private var healingPower = 0

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

    override fun getPrice(): Float {
        return (this.healingPower * 100).toFloat()
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

    private var experiencePower = 0

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

    override fun getPrice(): Float {
        return (this.experiencePower * 10).toFloat()
    }

    // use and remove the exp potion from inventory
    override fun useConsumable(player: Player) {

        player.playerExperience += this.experiencePower

        println("You have used a ${this.name}. ${this.experiencePower} EXP gained.")

        this.removeItemFromInventory(player)

    }
}

class GoldPotion: Potion() {

    override val typeOfConsumable = "Gold Potion"

    private var bonusGold = 0

    init {

        when (this.rarity) {

            1 -> {
                this.name = "Common Gold Potion"
                this.bonusGold = 100
            }

            2 -> {
                this.name = "Rare Gold Potion"
                this.bonusGold = 200
            }

            3 -> {
                this.name = "Epic Gold Potion"
                this.bonusGold = 400
            }

            4 -> {
                this.name = "Legendary Gold Potion"
                this.bonusGold = 1000
            }

            else -> {
                throw Exception("Error creating Gold Potion.")
            }

        }

    }

    override fun getPrice(): Float {
        return (this.bonusGold * 1.2).toFloat()
    }

    // use and remove the exp potion from inventory
    override fun useConsumable(player: Player) {

        player.playerGold += this.bonusGold

        println("You have used a ${this.name}. ${this.bonusGold} Gold gained.")

        this.removeItemFromInventory(player)

    }
}

class WeaponEnhancePotion: Potion() {

    override val typeOfConsumable = "Weapon Enhancing Potion"

    private var bonusAttackPower = 0


    init {

        when (this.rarity) {

            1 -> {
                this.name = "Common Weapon Potion"
                this.bonusAttackPower = 1
            }

            2 -> {
                this.name = "Rare Weapon Potion"
                this.bonusAttackPower = 2
            }

            3 -> {
                this.name = "Epic Weapon Potion"
                this.bonusAttackPower = 3
            }

            4 -> {
                this.name = "Legendary Weapon Potion"
                this.bonusAttackPower = 5
            }

            else -> {
                throw Exception("Error creating Weapon Enhancing Potion.")
            }

        }
    }

    override fun getPrice(): Float {
        return (this.bonusAttackPower * 2000).toFloat()
    }

    // use and remove the exp potion from inventory
    override fun useConsumable(player: Player) {

        player.playerWeapon!!.attackPower += this.bonusAttackPower

        println("You have used a ${this.name}. ${this.bonusAttackPower} AP gained on weapons ${player.playerWeapon!!.name}.")

        this.removeItemFromInventory(player)

    }

}
