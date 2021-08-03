import java.security.KeyStore
import kotlin.random.Random

class Player(override val name: String) : Entity() {

    // PLAYER NUMBERS
    var playerHP: Float = 100F
    var playerExperience: Float = 0F
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

            // TODO make amour an uncommon bonus when looting instead of guaranteed
            val armour = Armour()
            playerArmour = armour

            println("You equipped ${armour.name} with defense power ${armour.defPower} (Grade: ${armour.rarity})")
        }
    }

    fun playerAttack (monster: Monster): Float {

        var playerAttackValue: Float = 0F

        // TODO incorporate an element of randomness so that the attack value isnt always the same
        playerAttackValue = this.playerWeapon!!.attackPower.toFloat() * 3

        return playerAttackValue

    }

    fun fight(monster: Monster) {

        println("You are fighting a ${monster.name} (HP: ${monster.monsterHP}, AP: ${monster.monsterAttackPower})")

        while (this.playerHP > 0 && monster.monsterHP > 0) {

            println("FIGHTING: What will you do? (attack, defend, item, stats, run")
            val getFightInput = readLine().toString().toLowerCase()

            when (getFightInput) {

                "attack" ->  {

                    // player attacks monster
                    monster.monsterHP -= this.playerAttack(monster)

                    // monster attacks player
                    this.playerHP -= monster.monsterAttack(this)
                    print("attack system")
                }

                "defend" -> {
                    print("defend system")
                }

                "item" -> {
                    print("item use")
                }

                // TODO view stats without skipping turn
//                "stats" -> {
//                    this.printPlayerDetails()
//                }

                "run" -> {
                    // TODO running mechanics
                    val runningRandomNum = Random.nextInt(0, 2) // 50-50 chance of running away
                    print("run")
                }

                else -> {
                    println("Please enter a valid input.")
                }

            }

            println("Your HP: ${this.playerHP}")
            println("Monster HP: ${monster.monsterHP}")


        }

        // TODO add rewards when player wins
        if (this.playerHP <= 0) {

            println("The monster has won. You have died.")

        } else {

            println("You have defeated the monster.")

        }

    }


}