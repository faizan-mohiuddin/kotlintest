
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

        for (item in theShop) {
            println("1x " + item.first.name + " (ID: " + item.first.itemID + ") costs " + item.second + " gold.")
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