import java.util.Scanner

class Menu(private val title: String, private val items: MutableList<MenuItem>) {
    private val scanner = Scanner(System.`in`)

    fun show() {
        while (true) {
            println(title)
            items.forEachIndexed { index, item ->
                println("$index. ${item.name}")
            }

            print("Выберите пункт меню: ")
            val input = scanner.nextLine()

            try {
                val choice = input.toInt()
                if (choice in items.indices) {
                    if (items[choice].action()) {
                        return
                    }
                } else {
                    println("Ошибка: нет пункта с номером $choice")
                }
            } catch (e: NumberFormatException) {
                println("Ошибка: введите число")
            }
        }
    }
}

class MenuItem(val name: String, val action: () -> Boolean)
