open class Shopkeeper: Entity() {

    override val name = "The Shopkeeper"
    open lateinit var shopkeeperItems: List<Item>

    fun createShopKeeper(): Shopkeeper {

        val typeOfShopKeeper = (0..2).random()

        when (typeOfShopKeeper) {
            0 -> return ConsumableShopkeeper()
//            1 -> return WeaponShopkeeper()
//            2 -> return ArmourShopkeeper()
            else -> return ConsumableShopkeeper() // this never will be reached
        }

    }

    fun printShopkeeperItems() {

        for (item in shopkeeperItems) {

            println("Keeper has ${item.name} .")

        }

    }

}

class ConsumableShopkeeper: Shopkeeper() {

    override var shopkeeperItems = listOfConsumables as List<Item>

}

// TODO rest of shopkeepers

class WeaponShopkeeper: Shopkeeper() {

    val weaponShopkeeperItems = mutableListOf<Weapon>()

}

class ArmourShopkeeper: Shopkeeper() {

    val armourShopkeeperItems = mutableListOf<Armour>()

}