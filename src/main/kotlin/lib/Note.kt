package lib

class Note(val header: String, val text : String) {

    fun equalsHeader(header: String): Boolean{
        if(this.header.equals(header)) return true
        return false
    }

    fun likeHeader(header: String): Boolean{
        if(this.header.contains(header)) return true
        return false
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Note

        if (header != other.header) return false
        if (text != other.text) return false

        return true
    }

    override fun hashCode(): Int {
        var result = header.hashCode()
        result = 31 * result + text.hashCode()
        return result
    }

    override fun toString(): String {
        return "Тема: $header\nТекс заметки\n$text"
    }
}