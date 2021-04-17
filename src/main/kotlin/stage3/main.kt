package stage3


import java.io.File
import kotlin.math.sqrt

fun main() {
    val directory = File("./src/main/resources/directory.txt").readLines()
    val wantedNames = File("./src/main/resources/find.txt").readLines()

    linearSearch(directory, wantedNames)

    sortAndJumpSearch(directory, wantedNames)

    quickSortAndBinarySearch(directory, wantedNames)
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

    var found = 0
    for (name in wantedNames) {
        if (jumpSearch(name, sortedDirectory)) {
            found++
        }
    }
    val end = System.currentTimeMillis()

    println("Found $found / 500 entries. Time taken: ${taken(end - startTotal)}")
    println("Sorting time: ${taken(endSort - startTotal)}")
    println("Searching time:: ${taken(end - endSort)}")

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

fun partition(list: MutableList<String>, _start: Int, _end: Int): Int {
    var start = _start
    var end = _end

    while (start < end) {
        while (start < end) {
            if (name(list[start]) > name(list[end])) {
                val swap = list[start]
                list[start] = list[end]
                list[end] = swap
                break
            }
            end -= 1
        }
        while (start < end) {
            if (name(list[start]) > name(list[end])) {
                val swap = list[start]
                list[start] = list[end]
                list[end] = swap
                break
            }
            start += 1
        }
    }
    return start
}

// https://fr.wikibooks.org/wiki/Impl%C3%A9mentation_d%27algorithmes_classiques/Algorithmes_de_tri/Tri_rapide#Kotlin
fun quicksort(list: MutableList<String>, _start: Int = -1, _end: Int = -1) {
    var start = _start
    var end = _end

    if (start == -1) start = 0
    if (end == -1) end = list.size

    if (start < end) {
        val i = partition(list, start, end - 1)
        quicksort(list, start, i)
        quicksort(list, i + 1, end)
    }
}


fun quickSortAndBinarySearch(directory: List<String>, wantedNames: List<String>) {
    println("Start searching (quick sort + binary search)...")

    val startTotal = System.currentTimeMillis()
    val mutDir = directory.toMutableList()
    quicksort(mutDir)
    val sortedDirectory = mutDir

    val endSort = System.currentTimeMillis()

    var found = 0
    for (name in wantedNames) {
        if (binarySearch(name, sortedDirectory)) {
            found++
        }
    }
    val end = System.currentTimeMillis()

    println("Found $found / 500 entries. Time taken: ${taken(end - startTotal)}")
    println("Sorting time: ${taken(endSort - startTotal)}")
    println("Searching time:: ${taken(end - endSort)}")

    println()
}

fun binarySearch(name: String, list: List<String>): Boolean {
    if (list.isEmpty()) {
        return false
    } else if (list.size == 1) {
        return name(list[0]) == name
    }

    val middle = list.size / 2

    if (name(list[middle]) == name) {
        return true
    } else if (name(list[middle]) < name) {
        // look in right half
        return binarySearch(name, list.subList(middle + 1, list.size))
    } else {
        // look in left half
        return binarySearch(name, list.subList(0, middle))
    }
}