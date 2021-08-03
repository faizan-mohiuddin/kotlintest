
// TODO dont use global variable to keep track of number of items
var numberOfEntities = 0
var numberOfItems = 0

fun main() {

//    println("Your name: ")
//    val playerNameInput = readLine().toString().toLowerCase()

    val player1 = Player("Faizan")

    // TODO in-game commands and game commands
    // println("COMMANDS: ")

    while (true) {

        // main menu

        while (player1.playerHP > 0) {

            // play the game

            println("What will you do?")
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

                else -> {
                    println("Please enter a valid input.")
                }

            }
        }

    }


}
