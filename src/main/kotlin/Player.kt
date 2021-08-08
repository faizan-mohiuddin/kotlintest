import kotlin.math.floor
import kotlin.random.Random

class Player(override val name: String) : Entity() {

    // PLAYER NUMBERS
    var playerHP: Float = 100F
    var playerExperience: Float = 0F
    var playerLevel = 0 // TODO not private because player level will be used out of class eventually
    var playerGold = 0F

    // PLAYER EQUIPMENT
    var playerWeapon: Weapon? = null
    var playerArmour: Armour? = null
    var playerInventory = mutableListOf<Item?>() // TODO change this to store more than just consumables


    fun printPlayerDetails() {
        println("PLAYER DETAILS: ")
        println("HP: ${this.playerHP}")
        println("Weapon: ${this.playerWeapon?.name} (${this.playerWeapon?.attackPower} AP) Armour: ${this.playerArmour?.name} (${this.playerArmour?.defPower} DP) ")
        println("Level: ${this.playerLevel} (EXP: ${this.playerExperience})")
        println("Gold: ${this.playerGold}")
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

            val weapon = Weapon().randomWeapon()
            val armour = Armour().randomArmour()

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

    // TODO kept monster as a parameter once fight mechanics are impvoed
    private fun playerAttack(monster: Monster): Float {

        // TODO incorporate an element of randomness so that the attack value isn't always the same

        // 1/5 chance to land a critical attack
        return if ((0..4).random() == 4 ){

            println("You landed a critical strike!")
            playerWeapon!!.attackPower.toFloat() * 5

        } else {

            playerWeapon!!.attackPower.toFloat() * 3

        }
    }

    private fun updateLevel() {

        // TODO could be better levelling system?
        // for every 100 experience, player level increases
        this.playerLevel = floor((this.playerExperience / 100)).toInt()

    }

    fun fight(monster: Monster) {

        println("You are fighting a ${monster.name} (HP: ${monster.monsterHP}, AP: ${monster.monsterAttackPower})")

        while (this.playerHP > 0 && monster.monsterHP > 0) {

            println("FIGHTING: What will you do? (attack, defend, item, stats, run)")

            when (readLine().toString().toLowerCase()) {

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

            // calculate gold and exp rewards for player
            val monsterEXP = monster.calculateMonsterOnDeathExperience()
            val monsterGold = monster.calculateMonsterOnDeathGold()
            this.playerGold += monsterGold
            this.playerExperience += monsterEXP
            this.updateLevel()

            // chance of consumable reward, maybe other items later
            val consumableReward = Consumable().randomConsumable()
            this.playerInventory.add(consumableReward)

            println("You have defeated the monster.")
            println("==AWARDED==")
            println("EXP: ${String.format("%.2f", monsterEXP)} GOLD: ${String.format("%.2f", monsterGold)}")
            // TODO more rewards, randomise them to be more rare
            println("1x ${consumableReward.typeOfConsumable}: ${consumableReward.name}")

        }

    }


}