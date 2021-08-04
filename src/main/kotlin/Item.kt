open class Item : Entity() {

    init {
        numberOfItems += 1
    }

    override var name = "Item"

    val itemID = numberOfItems
    open val rarity = 0 // rarities: 0 = unknown, 1 = common, 2 = rare, 3 = epic, 4 = legendary

}