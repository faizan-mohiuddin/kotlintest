import kotlin.random.Random

val arrayOfMonsterNames = arrayOf("Slime", "Rat", "Beast", "Pillager")

class Monster : Entity() {

    override val name = arrayOfMonsterNames[Random.nextInt(1, 4)]
    val monsterAttackPower = Random.nextInt(1,5)

}