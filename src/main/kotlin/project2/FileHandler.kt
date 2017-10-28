package project2

import java.io.*

class FileHandler {

    fun getReader(path: String): Reader {
        return InputStreamReader(this.javaClass.getResourceAsStream(path),"UTF-8")
    }

    fun getWriter(path: String): Writer {
        val file = File(path)
        return FileWriter(file)
    }
}