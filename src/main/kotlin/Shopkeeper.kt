
val listOfShopkeepers = mutableListOf<Shopkeeper>(WeaponShopkeeper(), ArmourShopkeeper(), ConsumableShopkeeper())

open class Shopkeeper: Entity() {

    override var name = "The Shopkeeper"
    // shop structure: mutableListOf<Array<Any> which looks like [[Weapon, Int], [Weapon, Int],....] where Int is cost
    var theShop = mutableListOf<Pair<Item, Float>>()

    fun randomShopkeeper(): Shopkeeper {

        return when((0..listOfWeapons.size).random()) {
            0 -> WeaponShopkeeper()
            1 -> ArmourShopkeeper()
            else -> ConsumableShopkeeper()
        }
    }

    open fun printShopkeeperGoods() {

        println("${this.name}'s store")

        for (item in theShop) {
            println("(ID: " + item.first.itemID + ")  1x " + item.first.name + " costs " + item.second + " gold.")
        }

    }

    open fun buyItemFromShopkeeper(player: Player, itemID: Int) {

        var failedToFindItem = true

        for (item in theShop) {

            if (item.first.itemID == itemID) {

                if (player.playerGold - item.second <= 0) {
                    println("You dont have enough gold to buy this item.")
                } else {
                    player.playerGold -= item.second
                    player.playerInventory.add(item.first)
                    println("Item ${item.first.name} added to your inventory. (-${item.second} gold)")
                    this.theShop.remove(item)
                    failedToFindItem = false
                }
            }

        }

        if (failedToFindItem) {
            println("Please enter a valid itemID.")
        }


    }

    fun hasItems(): Boolean {

        return theShop.size > 0

    }

}

class WeaponShopkeeper: Shopkeeper() {

    override var name = "Weapons Shopkeeper"

    init {

        for (weapon in listOfWeapons) {

            this.theShop.add(Pair(weapon, weapon.getPrice()))

        }

    }



}

class ArmourShopkeeper: Shopkeeper() {

    override var name = "Armours Shopkeeper"

    init {

        for (armour in listOfArmours) {

            this.theShop.add(Pair(armour, armour.getPrice()))

        }

    }

}

class ConsumableShopkeeper: Shopkeeper() {

    override var name = "Consumables Shopkeeper"

    init {

        for (consumable in listOfConsumables) {

            this.theShop.add(Pair(consumable, consumable.getPrice()))

        }

    }
}

class GeneralShopkeeper: Shopkeeper() {

    override var name = "Persistent General Shopkeeper"

    init {
        // persistent shopkeeper always sells random things. "shop" in commands
        for (i in 0..4) {
            when ((0..2).random()) {
                0 -> {
                    val consumable = Consumable().randomConsumable()
                    this.theShop.add(Pair(consumable, consumable.getPrice()))
                }
                1 -> {
                    val armour = Armour().randomArmour()
                    this.theShop.add(Pair(armour, armour.getPrice()))
                }
                2 -> {
                    val weapon = Weapon().randomWeapon()
                    this.theShop.add(Pair(weapon, weapon.getPrice()))
                }
            }
        }
    }
}
