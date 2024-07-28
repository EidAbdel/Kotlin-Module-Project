package app

import constants.ProgramConstants
import lib.Note
import lib.NotesList
import util.Check

class NotesAPP : Check {
    companion object {
        var archiveList: MutableList<NotesList> = mutableListOf()
    }

    fun start() {
        appMainMenu()
    }

    // главное меню
    private fun appMainMenu() {
        println(ProgramConstants.ARCHIVE_LIST)
        when (inputCheck(
            mutableListOf(
                "0. Создать архив",
                "1. Список архивов",
                "2. Выход"
            )
        )) {
            0 -> createArchive()    //  create archive
            1 -> showArchiveList()  // archive List
        }
    }

    //  показать список всех списков с заметками
    private fun showArchiveList() {
        if (archiveList.isEmpty()) {
            println(ProgramConstants.NO_DATA)
            if (inputCheck(mutableListOf()) == 0) appMainMenu()
        } else {
            val archineListHeader: MutableList<String> = mutableListOf()
            archiveList.forEachIndexed { index, point -> archineListHeader.add("$index. ${point.getName()}") }
            println(ProgramConstants.ARCHIVE_NAME)
            val index = inputCheck(archineListHeader)
            when (index) {
                archineListHeader.size - 1 -> appMainMenu()
                else -> noteListMenu(index)    // archive notes menu
            }

        }
    }

    // создание нового листа с заметками
    private fun createArchive() {
        val name = getName({ println("Введите имя архива:") })
        println("Архив '$name' создан:")
        val notes = NotesList(name)
        archiveList.add(notes)
        if (inputCheck(mutableListOf()) == 0) appMainMenu()
    }

    // Когда выберем название листа с заметками проваливаемся в его меню
    private fun noteListMenu(noteListIndex: Int) {
        println("Архив ${archiveList[noteListIndex].getName()}")
        when (inputCheck(
            mutableListOf(
                "0. Создать заменку",
                "1. Посмотреть все заметки",
                "2. Изменить название архива",
            )
        )) {
            0 -> createNote(noteListIndex)   //  create note
            1 -> noteListHeader(noteListIndex)  // note List
            2 -> changeNotesName(noteListIndex)  // change List name
            else -> showArchiveList()
        }
    }

    // создание заметки
    private fun createNote(noteListIndex: Int) {
        val name = getName({ println("Введите имя заметки:") })
        val note = Note(header = name, text = getName { println("Введите текст заметки") })
        archiveList[noteListIndex].addNote(note)
        println("Заметка '$name' создан:")
        if (inputCheck(mutableListOf()) == 0) noteListMenu(noteListIndex)
    }

    // Изменение имени Архива
    private fun changeNotesName(noteListIndex: Int) {
        val oldName = archiveList[noteListIndex].getName()
        val newName = getName { println("Введите новое имя заметки:") }
        archiveList[noteListIndex].setName(newName)
        println("Имя изменено с '$oldName' на '$newName'")
        if (inputCheck(mutableListOf()) == 0) noteListMenu(noteListIndex)
    }

    // Список названия всех заметок
    private fun noteListHeader(noteListIndex: Int) {
        if (archiveList[noteListIndex].isEmpty()) {
            println(ProgramConstants.NO_DATA)
            if (inputCheck(mutableListOf()) == 0) noteListMenu(noteListIndex)
        } else {
            val notesHeaders = archiveList[noteListIndex].getNotesHeader()
            println(ProgramConstants.Note_LIST)
            val noteIndex = inputCheck(notesHeaders)
            when (noteIndex) {
                notesHeaders.size - 1 -> noteListMenu(noteListIndex)
                else -> noteMenu(noteListIndex, noteIndex)    // note menu
            }
        }
    }

    private fun noteMenu(noteListIndex: Int, noteIndex: Int) {
        println("Заметка '${archiveList[noteListIndex].findNoteByIndex(noteIndex)!!.getHeader()}'")
        when (inputCheck(
            mutableListOf(
                "0. Посмотреть заметку",
                "1. Изменить имя заметки",
                "2. Изменить текст заметки",
                "3. Заменить заметку на новую",
                "4. Удалить заметку",
            )
        )) {
            0 -> {
                println(archiveList[noteListIndex].findNoteByIndex(noteIndex)!!.toString()) // TODO: show note
                if (inputCheck(mutableListOf()) == 0) noteMenu(noteListIndex, noteIndex)
            }

            1 -> {
                archiveList[noteListIndex].findNoteByIndex(noteIndex)!!
                    .setHeader(getName { println("Введите имя") })
                println("Имя заменено")
                if (inputCheck(mutableListOf()) == 0)  noteMenu(noteListIndex, noteIndex)   // TODO: change name

            }

            2 -> {
                archiveList[noteListIndex].findNoteByIndex(noteIndex)!!
                    .setText(getName { println("Введите текст") })
                println("Текст заменён")
                if (inputCheck(mutableListOf()) == 0) noteMenu(noteListIndex, noteIndex)   // TODO: change text
            }

            3 -> {
                archiveList[noteListIndex].findNoteByIndex(noteIndex)!!
                    .changeNote(
                        getName { println("Введите имя") },
                        getName { println("Введите текст") })
                println("Текст заменён")
                if (inputCheck(mutableListOf()) == 0) noteMenu(noteListIndex, noteIndex)   // TODO: change note
            }

            4 -> {
                archiveList[noteListIndex].deleteNoteByIndex(noteIndex)
                println("Заметка удалена")

                if (inputCheck(mutableListOf()) == 0) noteListHeader(noteListIndex)   // TODO: delete note
            }

            else -> {
                noteListHeader(noteListIndex)
            }
        }

    }


}




