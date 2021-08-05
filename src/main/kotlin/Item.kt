open class Item : Entity() {

    init {
        numberOfItems += 1
    }

    override var name = "Item"

    val itemID = numberOfItems
    open val rarity = 0 // rarities: 0 = unknown, 1 = common, 2 = rare, 3 = epic, 4 = legendary

    open fun useConsumable(player: Player){
        // gets overridden by sub-class function - better way to do this?
    }

    open fun removeItemFromInventory(player: Player) {

        for (item in player.playerInventory) {

            if (item?.itemID == this.itemID) {

                player.playerInventory.remove(item)
                break

            }
        }

    }


}