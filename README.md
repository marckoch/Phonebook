# Jetbrains Academy - Phone Book

My solutions for the Jetbrains Academy Problem Phone Book

https://hyperskill.org/projects/86

The solution is build up step by step over several stages. 
Stage 1 is the first and simple one. The following stages 
build up on the previous stages and get more and more advanced.
There are six stages in total.

Because each stage is completely independent of the previous one,
IntelliJ might show some warnings about duplicated code between 
the stages.

## Stage 1

[click here to see description @ JetBrains Academy](https://hyperskill.org/projects/86/stages/476/implement)

We search 500 entries in a list of over 1 million entries 
using a basic linear search.

just execute this:

    gradle -PmainClass=stage1.MainKt run --console=plain

    Start searching...
    Found 500 / 500 entries. Time taken: 00 min. 02 sec. 749 ms.

## Stage 2

[click here to see description @ JetBrains Academy](https://hyperskill.org/projects/86/stages/477/implement)

We sort the list and search with a jump search.

just execute this:

    gradle -PmainClass=stage2.MainKt run --console=plain

    Start searching (linear search)...
    Found 500 / 500 entries. Time taken: 00 min. 11 sec. 207 ms.
    
    Start searching (bubble sort + jump search)...
    Found 500 / 500 entries. Time taken: 00 min. 02 sec. 275 ms.
    Sorting time: 00 min. 02 sec. 206 ms.
    Searching time:: 00 min. 00 sec. 069 ms.
    

## Stage 3

[click here to see description @ JetBrains Academy](https://hyperskill.org/projects/86/stages/478/implement)

We sort bby quick sort and search by binary search.

just execute this:

    gradle -PmainClass=stage3.MainKt run --console=plain

    Start searching (linear search)...
    Found 500 / 500 entries. Time taken: 00 min. 11 sec. 054 ms.
    
    Start searching (bubble sort + jump search)...
    Found 500 / 500 entries. Time taken: 00 min. 02 sec. 258 ms.
    Sorting time: 00 min. 02 sec. 187 ms.
    Searching time:: 00 min. 00 sec. 071 ms.
    
    Start searching (quick sort + binary search)...
    Found 500 / 500 entries. Time taken: 00 min. 03 sec. 209 ms.
    Sorting time: 00 min. 03 sec. 204 ms.
    Searching time:: 00 min. 00 sec. 005 ms.
    

## Stage 4

[click here to see description @ JetBrains Academy](https://hyperskill.org/projects/86/stages/479/implement)

We create a hashtable of the entries and query the hash table.

just execute this:

    gradle -PmainClass=stage4.MainKt run --console=plain
    
    Start searching (linear search)...
    Found 500 / 500 entries. Time taken: 00 min. 11 sec. 037 ms.
    
    Start searching (bubble sort + jump search)...
    Found 500 / 500 entries. Time taken: 00 min. 02 sec. 218 ms.
    Sorting time: 00 min. 02 sec. 150 ms.
    Searching time:: 00 min. 00 sec. 068 ms.
    
    Start searching (quick sort + binary search)...
    Found 500 / 500 entries. Time taken: 00 min. 03 sec. 094 ms.
    Sorting time: 00 min. 03 sec. 089 ms.
    Searching time:: 00 min. 00 sec. 005 ms.
    
    Start searching (hash table)...
    Found 500 / 500 entries. Time taken: 00 min. 00 sec. 397 ms.
    Creating time: 00 min. 00 sec. 396 ms.
    Searching time:: 00 min. 00 sec. 001 ms.
