
open class Spell {

    open val spellName = "Spell"
    open val spellCost = 0

    open fun castSpell(player: Player): Float {
        // gets overridden
        return 0F
    }

    open fun canPlayerCastSpell(player: Player): Boolean {

        return player.playerMana - this.spellCost > 0

    }

}

open class Buff: Spell() {

}

open class InstantSpell: Spell() {

}

    class Fireball(player: Player): InstantSpell() {

        override val spellName = "Fireball"
        override val spellCost = 20

        override fun castSpell(player: Player): Float {

            if (canPlayerCastSpell(player)) {

                println("You casted ${this.spellName}! (-${this.spellCost}")

                player.playerMagicExperience += 20
                player.playerMana -= this.spellCost

                return if (player.playerWeapon != null) {

                    val damageMultiplier =
                        ((player.playerWeapon!!.attackPower / 2) + (player.playerMagicExperience / 100))

                    (10 * (damageMultiplier)).toFloat()

                } else {
                    (10 * (player.playerMagicExperience / 100)).toFloat()
                }

            } else {

                println("You do not have enough Mana to cast ${this.spellName}!")
                return 0F

            }

        }

    }

    class InstantHeal(player: Player): InstantSpell() {

    }


