import java.util.Scanner

class ArchivesScreen {
    private val archives = mutableListOf<Archive>()
    private val scanner = Scanner(System.`in`)

    fun show() {
        while (true) {
            printArchivesMenu()

            when (val input = scanner.nextLine()) {
                "0" -> createArchive()
                (archives.size + 1).toString() -> return
                else -> openArchive(input)
            }
        }
    }

    private fun printArchivesMenu() {
        println("\nСписок архивов:")
        println("0. Создать архив")

        archives.forEachIndexed { index, archive ->
            println("${index + 1}. ${archive.name}")
        }

        println("${archives.size + 1}. Выход")
        print("Выберите пункт меню: ")
    }

    private fun createArchive() {
        print("Введите название архива: ")
        val name = scanner.nextLine().trim()

        when {
            name.isEmpty() -> println("Название архива не может быть пустым")
            archives.any {
                it.name.equals(
                    name, ignoreCase = true
                )
            } -> println("Архив с таким именем уже существует")

            else -> {
                archives.add(Archive(name))
                println("Архив '$name' успешно создан")
            }
        }
    }

    private fun openArchive(input: String) {
        try {
            val index = input.toInt() - 1
            if (index in archives.indices) {
                NotesScreen(archives[index]).show()
            } else {
                println("Ошибка: нет архива с номером $input")
            }
        } catch (e: NumberFormatException) {
            println("Ошибка: введите число")
        }
    }
}
