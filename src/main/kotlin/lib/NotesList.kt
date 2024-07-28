package lib

import constants.ProgramConstants

class NotesList(private var name: String) {
    private var archiveList: MutableList<Note> = mutableListOf()

    fun setName(name: String) = name.also { this.name = it }
    fun getName(): String = this.name

    fun copyNotes(list: List<Note>) {
        list.forEach { note -> archiveList.add(note) }
    }

    fun addNote(note: Note) {
        archiveList.add(note)
    }

    fun addNotes(vararg notes: Note) {
        notes.forEach { note ->
            archiveList.add(note)
        }
    }

    fun showAllNotes() {
        if (archiveList.isEmpty()) println("Архив $name пустой")
        else archiveList.forEach { note -> println("$note\n${"-".repeat(ProgramConstants.LEN)}") }
    }

    fun showNotesHeader() {
        archiveList.forEachIndexed { index, note -> println("$index. ${note.getHeader()}") }
    }

    fun getNotesHeader(): MutableList<String> {
        val headerList: MutableList<String> = mutableListOf()
        mutableListOf(archiveList.forEachIndexed { index, note -> headerList.add("$index. ${note.getHeader()}") }
            .toString())
        return headerList
    }

    fun findNoteByIndex(index: Int): Note? {
        if (archiveList.getOrNull(index) != null) return archiveList[index]
        return null
    }

    fun deleteNoteByIndex(index: Int) {
        archiveList.removeAt(index)
    }

    fun findNoteByHeader(header: String) {
        val headerList = archiveList.filter { note -> note.equalsHeader(header) }
        if (headerList.isEmpty()) {
            println(ProgramConstants.NOT_HEADER)
        } else {
            headerList.forEachIndexed { index, note -> println("$index) $note") }
        }
    }

    fun findNoteLikeHeader(header: String) {
        val headerList = archiveList.filter { note -> note.likeHeader(header) }
        if (headerList.isEmpty()) {
            println(ProgramConstants.NOT_HEADER)
        } else {
            headerList.forEachIndexed { index, note -> println("$index) $note") }
        }
    }

    fun isEmpty() = archiveList.isEmpty()


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as NotesList

        if (name != other.name) return false
        if (archiveList != other.archiveList) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + archiveList.hashCode()
        return result
    }

    override fun toString(): String {
        return name
    }

}
