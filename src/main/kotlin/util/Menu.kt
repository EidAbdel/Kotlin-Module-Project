package util

interface Menu:Check {
    fun mainMenu(): Int {
        return inputCheck(
            mutableListOf(
                "0. Создать архив",
                "1. Список архивов",
                "2. Выход"
            ))
    }

    fun listMenu():Int{
        return inputCheck(
            mutableListOf(
                "0. Создать",
                "1. Посмотреть",
            ))
    }

}