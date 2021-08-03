import java.lang.Exception
import kotlin.random.Random

// TODO is there a better way to store all possible monsters?
val arrayOfMonsterNames = arrayOf("Slime", "Rat", "Beast", "Pillager")

class Monster : Entity() {

    // TODO make this non-random so that slimes are most common, pillagers are most rare
    override val name = arrayOfMonsterNames[Random.nextInt(0, arrayOfMonsterNames.size)]

    var monsterAttackPower = 0
    var monsterDefPower: Float = 1F
    var monsterHP:Float = 0F

    init {

        when (this.name) {
            "Slime" -> {
                this.monsterAttackPower = Random.nextInt(1,2)
                this.monsterHP = 10F
            }
            "Rat" -> {
                this.monsterAttackPower = Random.nextInt(2, 4)
                this.monsterHP = 20F
            }
            "Beast" -> {
                this.monsterAttackPower = Random.nextInt(4, 6)
                this.monsterHP = 30F
            }
            "Pillager" -> {
                this.monsterAttackPower = Random.nextInt(6, 11)
                this.monsterHP = 50F
            }
            else -> throw Exception("Error occurred when rolling Monster name.")
        }

    }

    fun monsterAttack(player: Player): Float {

        var monsterAttackValue: Float = 0F
        // TODO incorporate an element of randomness so that the attack value isnt always the same

        if (player.playerArmour == null) {

            monsterAttackValue = (this.monsterAttackPower.toFloat() * 3) / (1F)

        } else {

            monsterAttackValue = (this.monsterAttackPower.toFloat() * 3) / (player.playerArmour!!.defPower)

        }
        return monsterAttackValue

    }

    fun calculateMonsterOnDeathExperience(): Float {
        val randomMultiplier = Random.nextFloat()
        return ((this.monsterAttackPower * 10) / randomMultiplier)
    }
}