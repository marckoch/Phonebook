package stage1

import java.io.File

fun main() {
    val directory = File("./src/main/resources/directory.txt").readLines()
    val wantedNames = File("./src/main/resources/find.txt").readLines()

    println("Start searching...")

    val start = System.currentTimeMillis()
    var found = 0
    for (name in wantedNames) {
        if (search(name, directory)) {
            found++
        }
    }
    val end = System.currentTimeMillis()

    val taken = String.format("%1\$tM min. %1\$tS sec. %1\$tL ms.", end - start)

    println("Found $found / 500 entries. Time taken: $taken")
}

fun search(name: String, list: List<String>): Boolean {
    for (entry in list) {
        if (entry.endsWith(name)) {
            return true
        }
    }
    return false
}