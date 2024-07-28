package lib

import constants.ProgramConstants

class Note(private var header: String,private var text: String) {

    fun setHeader(header: String){this.header=header}
    fun setText(text: String){this.text=text}

    fun getHeader():String =this.header
    fun getText():String =this.text

    fun changeNote(header:String, text: String ){
        setHeader(header)
        setText(text)
    }

    fun equalsHeader(header: String): Boolean {
        if (this.header.equals(header)) return true
        return false
    }

    fun likeHeader(header: String): Boolean {
        if (this.header.contains(header)) return true
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
        return "-".repeat(ProgramConstants.LEN) +
                "\nИмя: $header" +
                "\n${"-".repeat(ProgramConstants.LEN)}" +
                "\nТекс заметки:" +
                "\n${"-".repeat(ProgramConstants.LEN)}" +
                "\n$text\n\n"
    }
}