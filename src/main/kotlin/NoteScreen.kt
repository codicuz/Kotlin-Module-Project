import java.util.Scanner

class NoteScreen(private val note: Note) {
    private val scanner = Scanner(System.`in`)

    fun show() {
        println("\nЗаметка: ${note.title}")
        println(note.content)
        println("\n0. Назад")
        print("Выберите пункт меню: ")

        while (true) {
            when (scanner.nextLine()) {
                "0" -> return
                else -> println("Ошибка: введите 0 для возврата")
            }
        }
    }
}
