import java.io.File

interface InputFileReader {
    fun loadInput(filename: String): List<String> {
        val lines = mutableListOf<String>()
        File(filename).useLines { it.forEach { l -> lines.add(l) } }

        return lines.toList()
    }
}
