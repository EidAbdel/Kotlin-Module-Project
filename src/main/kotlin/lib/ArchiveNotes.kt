package lib

import constants.ProgramConstants

class ArchiveNotes(val name: String) {
    private var archiveList: MutableList<Note> = mutableListOf()

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
        val headerList: MutableList<String> =
            mutableListOf(archiveList.forEachIndexed { index, note -> "$index. ${note.getHeader()}" }
                .toString())
        return headerList
    }

    fun findNoteByIndex(index: Int) {
        if (archiveList.getOrNull(index) != null) {
            println(archiveList[index])
        } else {
            println(ProgramConstants.OUT_OF_RANGE)
        }
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

        other as ArchiveNotes

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
