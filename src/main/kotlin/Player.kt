import kotlin.random.Random

class Player(override val name: String) : Entity() {

    // PLAYER NUMBERS
    var playerHP = 100
    var playerExperience = 0
    var playerLevel = 0

    // PLAYER EQUIPMENT
    var playerWeapon: Weapon? = null
    var playerArmour: Armour? = null

    fun printPlayerDetails() {
        println("PLAYER DETAILS: ")
        println("HP: ${this.playerHP}")
        println("Weapon: ${this.playerWeapon?.name} Armour: ${this.playerArmour}")
        println("Level: ${this.playerLevel} (EXP: ${this.playerExperience})")
    }

    fun loot() {
        if (Random.nextInt(0, 2) == 0) {

            this.playerHP -= Random.nextInt(1, 11) // 1-10 damage

            println("You were not able to loot an item!")
            println("HP: ${this.playerHP}")

        } else {

            val weapon = Weapon()
            playerWeapon = weapon

            println("You equipped ${weapon.name} with attack power ${weapon.attackPower} (Grade: ${weapon.rarity})")
        }
    }


}