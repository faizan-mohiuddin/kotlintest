
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
                val monster = Monster()
                println(monster.name)
            }

        }
    }

}
