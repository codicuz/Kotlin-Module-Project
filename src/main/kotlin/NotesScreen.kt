import java.util.Scanner

class NotesScreen(private val archive: Archive) {
    private val scanner = Scanner(System.`in`)

    fun show() {
        while (true) {
            printNotesMenu()
            val input = scanner.nextLine()

            when {
                input == "0" -> createNote()
                input == (archive.notes.size + 1).toString() -> return
                else -> openNote(input)
            }
        }
    }

    private fun printNotesMenu() {
        println("\nСписок заметок в архиве '${archive.name}':")
        println("0. Создать заметку")

        archive.notes.forEachIndexed { index, note ->
            println("${index + 1}. ${note.title}")
        }

        println("${archive.notes.size + 1}. Назад")
        print("Выберите пункт меню: ")
    }

    private fun createNote() {
        print("Введите название заметки: ")
        val title = scanner.nextLine().trim()

        if (title.isEmpty()) {
            println("Название заметки не может быть пустым")
            return
        }

        print("Введите текст заметки: ")
        val content = scanner.nextLine().trim()

        if (content.isEmpty()) {
            println("Текст заметки не может быть пустым")
            return
        }

        archive.notes.add(Note(title, content))
        println("Заметка '$title' успешно создана")
    }

    private fun openNote(input: String) {
        try {
            val index = input.toInt() - 1
            if (index in archive.notes.indices) {
                NoteScreen(archive.notes[index]).show()
            } else {
                println("Ошибка: нет заметки с номером $input")
            }
        } catch (e: NumberFormatException) {
            println("Ошибка: введите число")
        }
    }
}
