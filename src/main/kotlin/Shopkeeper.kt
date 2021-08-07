
val listOfShopkeepers = mutableListOf<Shopkeeper>(WeaponShopkeeper(), ArmourShopkeeper(), ConsumableShopkeeper())

open class Shopkeeper: Entity() {

    override var name = "The Shopkeeper"

    fun randomShopkeeper(): Shopkeeper {

        return when((0..listOfWeapons.size).random()) {
            0 -> WeaponShopkeeper()
            1 -> ArmourShopkeeper()
            else -> ConsumableShopkeeper()
        }
    }

    open fun printShopkeeperGoods() {

    }

}

class WeaponShopkeeper: Shopkeeper() {

    override var name = "Weapons Shopkeeper"
    val shopkeeperItems = mutableListOf<Weapon>()

    init {

        for (weapon in listOfWeapons) {
            shopkeeperItems.add(weapon)
        }

    }

    override fun printShopkeeperGoods() {
        for (weapon in shopkeeperItems) {
            println(weapon.name)
        }
    }


}

class ArmourShopkeeper: Shopkeeper() {

    override var name = "Armours Shopkeeper"
    val shopkeeperItems = mutableListOf<Armour>()

    init {

        for (armour in listOfArmours) {
            shopkeeperItems.add(armour)
        }

    }

    override fun printShopkeeperGoods() {
        for (armour in shopkeeperItems) {
            println(armour.name)
        }
    }

}

class ConsumableShopkeeper: Shopkeeper() {

    override var name = "Consumables Shopkeeper"
    val shopkeeperItems = mutableListOf<Consumable>()

    init {

        for (consumable in listOfConsumables) {
            shopkeeperItems.add(consumable)
        }

    }

    override fun printShopkeeperGoods() {
        for (consumable in shopkeeperItems) {
            println(consumable.name)
        }
    }

}