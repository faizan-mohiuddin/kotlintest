import java.text.DecimalFormat
import kotlin.math.floor
import kotlin.random.Random

class Player(override val name: String) : Entity() {

    // PLAYER NUMBERS
    var playerHP: Float = 100F
    var playerExperience: Float = 0F
    var playerLevel = 0

    // PLAYER EQUIPMENT
    var playerWeapon: Weapon? = null
    var playerArmour: Armour? = null
    var playerInventory = mutableListOf<Item?>() // TODO change this to store more than just consumables


    fun printPlayerDetails() {
        println("PLAYER DETAILS: ")
        println("HP: ${this.playerHP}")
        println("Weapon: ${this.playerWeapon?.name} (${this.playerWeapon?.attackPower} AP) Armour: ${this.playerArmour?.name} (${this.playerArmour?.defPower} DP) ")
        println("Level: ${this.playerLevel} (EXP: ${this.playerExperience})")
    }

    fun equipWeapon(weapon: Weapon) {

        if (this.playerWeapon == null) {

            for (item in this.playerInventory) {

                if (item?.itemID == weapon.itemID) {

                    this.playerWeapon = item as Weapon?
                    println("You have equipped ${item.name}.")
                    break

                }
            }

        } else {

            var counter = 0

            for (item in this.playerInventory) {

                if (item?.itemID == weapon.itemID) {

                    val copyPlayerWeapon: Weapon = this.playerWeapon!! // copyPlayerWeapon is a dupe of the player's current weapon (can't be null!!)
                    this.playerWeapon = this.playerInventory[counter] as Weapon
                    println("You have swapped ${copyPlayerWeapon.name} for ${this.playerInventory[counter]?.name}.")
                    this.playerInventory[counter] = copyPlayerWeapon
                    break

                }

                counter += 1

            }

        }
    }

    fun playerUseConsumable(consumable: Consumable) {
        consumable.useConsumable(this)
    }

    fun equipArmour(armour: Armour) {

        if (this.playerArmour == null) {

            for (item in this.playerInventory) {

                if (item?.itemID == armour.itemID) {

                    this.playerArmour = item as Armour?
                    println("You have equipped ${item.name}.")
                    break

                }
            }

        } else {

            var counter = 0

            for (item in this.playerInventory) {

                if (item?.itemID == armour.itemID) {

                    val copyPlayerArmour: Armour = this.playerArmour!! // copyPlayerWeapon is a dupe of the player's current weapon (can't be null!!)
                    this.playerArmour = this.playerInventory[counter] as Armour
                    println("You have swapped ${copyPlayerArmour.name} for ${this.playerInventory[counter]?.name}.")
                    this.playerInventory[counter] = copyPlayerArmour
                    break

                }

                counter += 1

            }

        }
    }

    fun printPlayerInventory() {

        var counter = 0

        for (item in this.playerInventory) {

            println("($counter): ${item?.name}")

            counter += 1
        }
    }

    fun loot() {
        if (Random.nextInt(0, 2) == 0) {

            this.playerHP -= Random.nextInt(1, 11) // 1-10 damage

            println("You were not able to loot an item!")
            println("HP: ${this.playerHP}")

        } else {

            val weapon = Weapon()
            val armour = Armour()

            if (this.playerWeapon == null) {

                this.playerWeapon = weapon

                println("You equipped ${weapon.name} with attack power ${weapon.attackPower} (Grade: ${weapon.rarity})")

            } else {

                this.playerInventory.add(weapon)

                println("You added ${weapon.name} with attack power ${weapon.attackPower} (Grade: ${weapon.rarity}) to your inventory.")

            }

            if (this.playerArmour == null) {

                // TODO make amour an uncommon bonus when looting instead of guaranteed - armour is OP
                this.playerArmour = armour

                println("You equipped ${armour.name} with defense power ${armour.defPower} (Grade: ${armour.rarity})")

            } else {

                this.playerInventory.add(armour)

                println("You added ${armour.name} with defense power ${armour.defPower} (Grade: ${armour.rarity}) to your inventory.")

            }
        }
    }

    fun playerAttack (monster: Monster): Float {

        var playerAttackValue: Float = 0F

        // TODO incorporate an element of randomness so that the attack value isnt always the same
        playerAttackValue = this.playerWeapon!!.attackPower.toFloat() * 3

        return playerAttackValue

    }

    fun updateLevel() {

        // TODO could be better levelling system?
        // for every 100 experience, player level increases
        this.playerLevel = floor((this.playerExperience / 100)).toInt()

    }

    fun fight(monster: Monster) {

        println("You are fighting a ${monster.name} (HP: ${monster.monsterHP}, AP: ${monster.monsterAttackPower})")

        while (this.playerHP > 0 && monster.monsterHP > 0) {

            println("FIGHTING: What will you do? (attack, defend, item, stats, run)")
            val getFightInput = readLine().toString().toLowerCase()

            when (getFightInput) {

                "attack" ->  {

                    // player attacks monster
                    monster.monsterHP -= this.playerAttack(monster)

                    // monster attacks player
                    this.playerHP -= monster.monsterAttack(this)

                }

                "defend" -> {

                    // TODO better defence mechanics, maybe take into account the player's armour
                    // 1 in 4 chance to block an attack from the monster
                    val attemptBlock = Random.nextInt(0, 4)

                    if (attemptBlock == 1) {
                        println("Successfully blocked the ${monster.name}'s attack!")
                    } else {
                        println("Failed to block ${monster.name}'s attack!")
                        this.playerHP -= monster.monsterAttack(this)
                    }

                }

                // TODO use consumable items mid-fight
//                "item" -> {
//                    print("item use")
//                }

                "stats" -> {
                    this.printPlayerDetails()
                }

                "run" -> {
                    // TODO better running mechanics

                    // 50-50 chance of running away
                    val attemptRun = Random.nextInt(0, 2)

                    if (attemptRun == 0) {

                        println("Successfully ran away from the ${monster.name}!")
                        break

                    } else {
                        this.playerHP -= monster.monsterAttack(this)
                        println("Failed to run away!")
                    }

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

        } else if (monster.monsterHP <= 0) {

            val monsterEXP = monster.calculateMonsterOnDeathExperience()
            this.playerExperience += monsterEXP
            this.updateLevel()

            val consumableReward = Consumable().randomConsumable()
            this.playerInventory.add(consumableReward)

            println("You have defeated the monster.")
            println("==AWARDED==")
            println("EXP: ${String.format("%.2f", monsterEXP)}")
            // TODO more rewards, randomise them to be more rare
            println("1x ${consumableReward.typeOfConsumable}: ${consumableReward.name}")

        }

    }


}