package stage2

import java.io.File
import kotlin.math.sqrt

fun main() {
    val directory = File("./src/main/resources/directory.txt").readLines()
    val wantedNames = File("./src/main/resources/find.txt").readLines()

    linearSearch(directory, wantedNames)

    sortAndJumpSearch(directory, wantedNames)
}

fun linearSearch(directory: List<String>, wantedNames: List<String>) {
    println("Start searching (linear search)...")

    val start = System.currentTimeMillis()
    var found = 0
    for (name in wantedNames) {
        if (linearSearch(name, directory)) {
            found++
        }
    }
    val end = System.currentTimeMillis()

    println("Found $found / 500 entries. Time taken: ${taken(end - start)}")
    println()
}

fun linearSearch(name: String, list: List<String>): Boolean {
    for (entry in list) {
        if (name(entry) == name) {
            return true
        }
    }
    return false
}

fun sortAndJumpSearch(directory: List<String>, wantedNames: List<String>) {
    println("Start searching (bubble sort + jump search)...")

    val startTotal = System.currentTimeMillis()
    val sortedDirectory = internalSort(directory)
    val endSort = System.currentTimeMillis()

    val startSearch = System.currentTimeMillis()
    var found = 0
    for (name in wantedNames) {
        if (jumpSearch(name, sortedDirectory)) {
            found++
        }
    }
    val end = System.currentTimeMillis()

    println("Found $found / 500 entries. Time taken: ${taken(end - startTotal)}")
    println("Sorting time: ${taken(endSort - startTotal)}")
    println("Searching time:: ${taken(end - startSearch)}")

    println()
}

fun jumpSearch(name: String, list: List<String>): Boolean {
    val intervalLength = sqrt(list.size.toDouble()).toInt()
    for (index in 0..list.size step intervalLength) {
        val entry = list[index]
        if (name(entry) == name) {
            return true
        } else if (name(entry) < name) {
            continue // jump to next interval
        } else {
            // we jumped too far and start a linear search back into previous interval
            var j = index
            while (name(list[j]) > name && j >= 0) {
                j--
            }
            return true
        }
    }
    return false
}

fun internalSort(list: List<String>): List<String> {
    // bubble sort is slow as f...!!
    // return bubbleSort(list);
    // to speed things up use this sort:
    return list.sortedBy { name(it) }
}

fun bubbleSort(list: List<String>): List<String> {
    val start = System.currentTimeMillis()
    val m = list.toMutableList()
    do {
        var switched = false
        for (i in 0 until m.size - 1) {
            if (name(m[i]) > name(m[i + 1])) {
                switched = true
                val temp = m[i]
                m[i] = m[i + 1]
                m[i + 1] = temp
            }
        }
    } while (switched)
    val end = System.currentTimeMillis()

    println("Sorting time: ${taken(end - start)}")

    return m.toList()
}

fun name(line: String): String {
    return line.substringAfter(" ")
}

fun taken(duration: Long): String {
    return String.format("%1\$tM min. %1\$tS sec. %1\$tL ms.", duration)
}