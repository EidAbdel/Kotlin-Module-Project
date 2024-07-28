package util

import constants.ProgramConstants
import java.util.Scanner

interface Check {
    fun inputCheck(menuList: MutableList<String> = mutableListOf()): Int {
        var input = Scanner(System.`in`)
        if (!menuList.contains("2. Выход")) menuList.add(
            "-".repeat(ProgramConstants.LEN) +
                    "\n${menuList.size}. Назад" +
                    "\n${"-".repeat(ProgramConstants.LEN)}"
        )
        menuList.forEach { point -> println(point) }
        var chose = input.nextLine()
        while (true) {
            if (chose.toIntOrNull() == null) {
                println(ProgramConstants.NOT_DIGITAL)
            } else if (menuList.getOrNull(chose.toInt()) != null) {
                break
            } else {
                println(ProgramConstants.OUT_OF_RANGE)
            }
            menuList.forEach { point -> println(point) }
            chose = input.nextLine()

        }
        return chose.toInt()
    }

    fun getName(printMSG: () -> Unit): String {
        printMSG()
        val input = Scanner(System.`in`)
        var chose = input.nextLine()
        while (chose.isEmpty()) {
            println(ProgramConstants.EMPTY_NAME)
            chose = input.nextLine()
        }
        return chose
    }



}