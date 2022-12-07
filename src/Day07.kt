private data class Directory(val parent: Directory?, val name: String) {
    private var directories = mutableListOf<Directory>()

    private var sizeOnCurrentLevel = 0L

    companion object {
        fun createFromCdCommand(string: String, rootDirectory: Directory, currentDirectory: Directory): Directory {
            return when (val name = string.substringAfter("cd ")) {
                "/" -> rootDirectory
                ".." -> currentDirectory.parent ?: throw Error()
                else -> currentDirectory.getDirectories().first { it.name == name }
            }
        }
    }

    fun getSize(): Long = sizeOnCurrentLevel + directories.sumOf(Directory::getSize)

    fun getDirectories(): List<Directory> = directories

    fun collect(predicate: (Directory) -> Boolean): Sequence<Directory> = sequence {
        if (predicate(this@Directory))
            yield(this@Directory)

        yieldAll(directories.flatMap { it.collect(predicate) }.asSequence())
    }

    fun fillFromLsResult(string: String) {
        val (first, second) = string.split(" ")
        if (first == "dir")
            directories += Directory(this, second)
        else
            sizeOnCurrentLevel += first.toLong()
    }
}

fun main() {
    val rootDirectory = createRootDirectory()

    val part1 = rootDirectory
        .collect { it.getSize() < 100000 }
        .sumOf { it.getSize() }

    val part2 = rootDirectory
        .collect { it.getSize() > kotlin.math.abs(70000000 - rootDirectory.getSize() - 30000000) }
        .sortedBy { it.getSize() }
        .first()
        .getSize()

    println(part1)
    println(part2)
}

private fun createRootDirectory(): Directory {
    val input = readInput("Day07_test")

    val rootDirectory = Directory(null, "/")

    var currentDirectory = rootDirectory
    input.forEach {
        when {
            it.startsWith("\$ cd") -> currentDirectory =
                Directory.createFromCdCommand(it, rootDirectory, currentDirectory)

            !it.startsWith("\$") -> currentDirectory.fillFromLsResult(it)
        }
    }

    return rootDirectory
}

