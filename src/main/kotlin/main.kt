import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import com.github.doyaaaaaken.kotlincsv.dsl.csvWriter

// TODO dont use global variable to keep track of number of items
var numberOfEntities = 0
var numberOfItems = 0

fun main() {

    println("Your name: ")
    val playerNameInput = readLine().toString()

    val player1 = Player(playerNameInput)
    val persistentShopkeeper = GeneralShopkeeper() // persistent shopkeeper

    // TODO in-game commands and game commands
    // println("COMMANDS: ")

    while (true) {

        // main menu

        while (player1.playerHP > 0) {

            // play the game

            println("What will you do? (loot, fight, inventory, item, shop, stats, leaderboard, exit)")

            when (readLine().toString()) {

                "exit" -> {
                    println("EXITING GAME")
                    break
                }

                "stats" -> {
                    player1.printPlayerDetails()
                }

                "loot" -> {

                    if ((0..12).random() != 0) {
                        player1.loot()
                    } else {

                        val travellingShopkeeper = Shopkeeper().randomShopkeeper()
                        println("You found a travelling ${travellingShopkeeper.name}!")
                        println("Would you like to visit their store? (Y/N)")

                        var wantsToVisit = false
                        var hasDecided = false

                        while (!hasDecided) {

                            val visitShop = readLine()

                            when (visitShop?.toLowerCase()) {
                                "y", "yes" -> {
                                    wantsToVisit = true
                                    hasDecided = true
                                }
                                "n", "no" -> {
                                    hasDecided = true
                                }
                                else -> {
                                    println("Please enter a valid input")
                                }
                            }
                        }

                        if (wantsToVisit) {

                            while (true) {

                                travellingShopkeeper.printShopkeeperGoods()

                                println("What itemID would you like to buy from the shopkeeper? (command 'exit' to leave)")
                                val getItemID = readLine()

                                if (getItemID == "exit") {
                                    println("You have left the travelling shopkeeper's shop.")
                                    break
                                }

                                if (getItemID?.toIntOrNull() == null) {
                                    println("Please enter a valid input")
                                } else {
                                    travellingShopkeeper.buyItemFromShopkeeper(player1, getItemID.toInt())
                                }

                            }

                        }
                    }

                }

                "fight" -> {

                    if (player1.playerWeapon == null) {
                        println("You need a weapon before you can fight. Find one by looting.")
                    } else {
                        val monster = Monster() // create a monster
                        player1.fight(monster) // enter fight sequence
                    }
                }

                "inventory" -> {
                    //player1.playerInventory.add(Consumable().randomConsumable()) // adds a random USABLE consumable to inventory
                    player1.printPlayerInventory()
                }

                "item" -> {

                    if (player1.playerInventory.isEmpty()) {
                        println("You currently have no items in your inventory.")
                    } else {

                        println("What item in your inventory would you like to interact with? (ID)")
                        when (val attemptUseItem = readLine().toString().toIntOrNull()) {
                            null -> {
                                println("Please type a valid integer.")
                            }
                            else -> {
                                try { // replace try catch with if number is < 0 or > size of inventory
                                    when (val getItem = player1.playerInventory[attemptUseItem]) {
                                        is Consumable -> {
                                            player1.playerUseConsumable(getItem)
                                                //player1.playerInventory[attemptUseItem]?.useConsumable(player1)
                                        }
                                        is Weapon -> {
                                            player1.equipWeapon(getItem)
                                        }
                                        is Armour -> {
                                            player1.equipArmour(getItem)
                                        }
                                        else -> {
                                            throw Exception("Error interacting with item.")
                                        }
                                    }
                                } catch (e: IndexOutOfBoundsException) {
                                    println("This item does not exist in your inventory.")
                                }
                            }
                        }
                    }
                }

                // TODO move leaderboard to main menu once implemented, leaderboard isn't sorted
                "leaderboard" -> {

                    csvReader().open("src/main/resources/leaderboard.csv") {
                        readAllAsSequence().forEach { row ->
                            println(row.toString()
                                .replace(",", "")
                                .replace("[", "")
                                .replace("]", "")
                                .trim())
                        }
                    }

                }

                "shop" -> {

                    if (persistentShopkeeper.hasItems()) {

                        println("You visit the shopkeeper.")

                        persistentShopkeeper.printShopkeeperGoods()

                        println("What itemID would you like to buy from the shopkeeper? (command 'exit' to leave)")
                        val getItemID = readLine()

                        if (getItemID?.toIntOrNull() == null) {
                            println("Please enter a valid input")
                        } else {
                            persistentShopkeeper.buyItemFromShopkeeper(player1, getItemID.toInt())
                        }

                    } else {
                        println("The shopkeeper has no items to sell.")
                    }
                }

                else -> {
                    println("Please enter a valid input.")
                }

            }
        }

        println("GAME OVER.")
        println("${player1.name}: ${player1.playerExperience} (EXP)")
        println("Save? (Y/N)")

        var hasDecided = false

        while (!hasDecided) {

            when (readLine().toString().toLowerCase()) {

                "y", "yes" -> {
                    println("Saving to file.")
                    val rows = listOf(listOf(player1.name, " " + player1.playerExperience.toInt()))
                    csvWriter().writeAll(rows, "src/main/resources/leaderboard.csv", append = true)
                    hasDecided = true
                }
                "n", "no" -> {
                    println("END.")
                    hasDecided = true
                }
                else -> println("Enter a valid input")
            }

        }
        break

    }


}
