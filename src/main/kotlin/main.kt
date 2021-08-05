import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import com.github.doyaaaaaken.kotlincsv.dsl.csvWriter

// TODO dont use global variable to keep track of number of items
var numberOfEntities = 0
var numberOfItems = 0

fun main() {

    println("Your name: ")
    val playerNameInput = readLine().toString()

    val player1 = Player(playerNameInput)

    // TODO in-game commands and game commands
    // println("COMMANDS: ")

    while (true) {

        // main menu

        while (player1.playerHP > 0) {

            // play the game

            println("What will you do? (loot, fight, inventory, item, stats, leaderboard, exit)")
            val getInput = readLine().toString()

            when (getInput) {

                "exit" -> {
                    println("EXITING GAME")
                    break
                }

                "stats" -> {
                    player1.printPlayerDetails()
                }

                "loot" -> {

                    player1.loot()

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

                        println("What item in your inventory would you like to use?")
                        val attemptUseItem = readLine().toString().toIntOrNull()
                        when (attemptUseItem) {
                            null -> {
                                println("Please type a valid integer.")
                            }
                            else -> {
                                try {
                                    player1.playerInventory[attemptUseItem]?.useConsumable(player1)
                                } catch (e: IndexOutOfBoundsException) {
                                    println("This item does not exist in your inventory.")
                                }
                            }
                        }
                    }
                }

                // TODO move leaderboard to main menu once implemented, leaderboard isnt sorted
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

            val attemptSave = readLine().toString().toLowerCase()

            when (attemptSave) {

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
