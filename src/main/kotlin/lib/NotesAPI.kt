package lib

import Check
import constants.ProgramConstants


class NotesAPI() : Check {
    val archiveList: MutableList<ArchiveNotes> = mutableListOf()

    fun start() {
        mainMenu()
    }

    private fun mainMenu() {
        println(ProgramConstants.ARCHIVE_LIST)
        when (inputCheck(
            mutableListOf(
                "0. Создать архив",
                "1. Это мой уже созданный архив",
                "2. Выход"
            )
        )) {
            0 -> createArchive()
            1 -> showArchiveList()
        }
    }


    private fun createArchive() {
        val name = getName({ println("Введите имя архива:") })
        println("Архив '$name' создан:")
        var notes = ArchiveNotes(name)
        archiveList.add(notes)
        when (inputCheck(mutableListOf("0. Добавить Заметку", "1. Создать другой архив"))) {
            0 -> {
                createNote(notes)
                menuArchiveNotes(archiveList.indexOf(notes))
            }

            1 -> createArchive()
            else -> mainMenu()
        }

    }


    private fun showArchiveList() {
        if (archiveList.isEmpty()) {
            println(ProgramConstants.NO_DATA)
            if (inputCheck(mutableListOf()) == 0) mainMenu()
        } else {
            val archineListHeader: MutableList<String> = mutableListOf()
            archiveList.forEachIndexed { index, point -> archineListHeader.add("$index. ${point.name}") }
            println(ProgramConstants.ARCHIVE_NAME)
            val index = inputCheck(archineListHeader)
            when (index) {
                archineListHeader.size - 1 -> mainMenu()
                else -> menuArchiveNotes(index)
            }
        }

    }

    private fun menuArchiveNotes(int: Int) {
        println("Архив заметок ${archiveList[int]}:")
        when (inputCheck(
            mutableListOf(
                "0. Добавить заметку",
                "1. Вывести список всех заметок",
            )
        )) {
            0 -> {
                createNote(archiveList[int])
                menuArchiveNotes(int)
            }

            1 -> showNotesList(archiveList[int], int)
            else -> showArchiveList()
        }


    }

    private fun createNote(archiveNotes: ArchiveNotes) {
        archiveNotes.addNote(
            Note(
                header = getName({ println("Заголовок заметки:") }),
                text = getName({ println("Текст заметки:") })
            )
        )
        println("заметка успешно создана")
    }

    private fun showNotesList(archiveNotes: ArchiveNotes, index: Int) {
        if (archiveNotes.isEmpty()) {
            println(ProgramConstants.NO_DATA)
            if (inputCheck(mutableListOf()) == 0) menuArchiveNotes(index)
        } else {
            when (inputCheck(archiveNotes.getNotesHeader())) {
                archiveNotes.getNotesHeader().size -> menuArchiveNotes(index)
                else -> TODO() // TODO: show menu notes
            }
        }
    }


}