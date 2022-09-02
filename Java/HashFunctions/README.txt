– Group member’s names and x500s
	Nicole Vu - vu000166
	Gina Yi - yi000058
– Contributions of each partner (if applicable)
	Both worked on HashTable.java
– Any assumptions
	None
– Additional features that your project had (if applicable)
	None
– Any known bugs or defects in the program
	None
– Credit ALL outside references used in completing this project both in the README
and within the code that utilizes the referenced material.
	+ Adapted codes from TestScan.java for readToken() function
– Academic Integrity statement
"I certify that the information contained in this README file is complete and accurate.
I have both read and followed the course policies in the 'Academic Integrity - Course Policy'
section of the course syllabus"
Nicole Vu, Gina Yi

--------------------------
HASH FUNCTIONS DESCRIPTION:
- hash1() for general
    Set location by adding the first letter with most priority (*29), last letter with second priority (*13), length of word with last priority
    and mod the sum by the length of the table
    Average collision length: gettysburg.txt: 1.6428572, canterbury.txt: 1.9122807, proverbs.txt: 2.168, that_bad.txt: 2.1452992
- hash2() for general
    Set location by adding the first letter with most priority (*31), second letter with second priority (*31), last letter with last priority (*3)
    and mod the sum by the length of the table
    Average collision length: gettysburg.txt: 1.7127659, canterbury.txt: 2.0566037, proverbs.txt: 2.2213116, that_bad.txt: 2.1092436
- hash3() for general
    Set location by adding the first letter with second priority (*97), middle letter with last priority (*31), length of word with most priority (*131)
    and mod the sum by the length of the table
    Average collision length: gettysburg.txt: 1.6428572, canterbury.txt: 1.9122807, proverbs.txt: 2.3162394, that_bad.txt: 2.1452992
- hash4() for specific
    Set location by adding the first letter, second letter multiplied by 101, length multiplied by 31
    and mod the sum by the length of the table
    Longest collision length: 2

--------------------------
PROGRAM COMMENTS: 
General:
- How does the performance of each hash function differ?
    hash1() is the most effective for all 4 general case files,
    hash2() works better with that_bad.txt than other hash functions but worse with other files,
    hash3() runs approximately as effective as the first, but worse in proverbs.txt
    hash4() is for specific case and only works well with keywords.txt
- Which one works best for the general case and which one works best for the specific case?
    hash1() is the best for general case and hash4() is the best for specific case
- Do even, odd, or prime number table lengths make a difference with the same hash function?
    Yes. The functions work best with prime number, and better with odd number than even number.
Specific:
- What is the smallest your hash table can be before the collisions start to collect on a single index?
    When our hash table length is around 64, the longest chain increases to 4
    Length is around 50, the longest chain is 5
    Length is around 25, the longest chain is 6
    Only when length is 1 that all the items collect on a single index
- What happens when you size the table very near to the number of keywords?
    When the size of the table very near to the number of keywords, there are more collisions, like mentioned above
- Can you keep the collision somewhat evenly distributed?
    We think that our collision rate is somewhat evenly distributed. Because when we have a small array below the number
    of items in the files, the average distribution lengths are close to (the number of items/array length)
    and the number of non-empty indices are greater than the number of empty indices.