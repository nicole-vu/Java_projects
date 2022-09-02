/* Names: Nicole Vu, Gina Yi
 x500s: vu000166, yi000058
 */

/*
COMMENTS:
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
 */

import java.io.*;
import java.util.Scanner;

public class HashTable<T> {
    // Constructor
    public HashTable(int size, String type) {
        table = new NGen[size];
        this.type = type;
    }

    // add() function adds an item into the hash table
    // call the hash function according to the type
    public void add(T item) {
        if (item instanceof String) {
            int location;
            // Call hash function according to the type
            if (type == "general1")
                location = hash1(item);
            else if (type == "general2")
                location = hash2(item);
            else if (type == "general3")
                location = hash3(item);
            else
                location = hash4(item);

            // if there is collision
            if (table[location] != null) {
                boolean duplicate = false;
                NGen<T> ptr = table[location];
                //checks to see if the item already exists in hash function
                while (ptr != null) {
                    if(ptr.getData().equals(item)){
                        duplicate = true;
                        break;
                    }
                    ptr = ptr.getNext();
                }
                if (!duplicate) {
                    NGen<T> temp = table[location].getNext();
                    table[location].setNext(new NGen<>(item, temp));
                }
            }
            else
                table[location] = new NGen(item, null);
        }
    }

    // General: Hash function 1
    // Set location by adding the first letter with most priority (*29), last letter with second priority (*13), length of word with last priority
    // mod the sum by the length of the table
    // Average collision length: gettysburg.txt: 1.6428572, canterbury.txt: 1.9122807, proverbs.txt: 2.168, that_bad.txt: 2.1452992
    private int hash1(T key){
        int location;
        String keyStr = key.toString();
        if (keyStr.length() == 1)
            location = (keyStr.charAt(0)*(table.length + 29)) % table.length ;
        else
            location = (keyStr.charAt(0)*29 + keyStr.charAt(keyStr.length()-1)*13 + keyStr.length()) % table.length;
        return location;
    }

    // General: Hash function 2
    // Set location by adding the first letter with most priority (*31), second letter with second priority (*31), last letter with last priority (*3)
    // mod the sum by the length of the table
    // Average collision length: gettysburg.txt: 1.7127659, canterbury.txt: 2.0566037, proverbs.txt: 2.2213116, that_bad.txt: 2.1092436
    private int hash2(T key){
        int location;
        String keyStr = key.toString();
        if (keyStr.length() == 1)
            location = (keyStr.charAt(0)*(table.length + 29)) % table.length ;
        else
            location = (keyStr.charAt(0)*131 + keyStr.charAt(1) * 31 + keyStr.charAt(keyStr.length()-1)*3) % table.length;
        return location;
    }

    // General: Hash function 3
    // Set location by adding the first letter with second priority (*97), middle letter with last priority (*31), length of word with most priority (*131)
    // mod the sum by the length of the table
    // Average collision length: gettysburg.txt: 1.6428572, canterbury.txt: 1.9122807, proverbs.txt: 2.3162394, that_bad.txt: 2.1452992
    private int hash3(T key){
        int location;
        String keyStr = key.toString();
        location = (keyStr.charAt(0) * 97 + keyStr.charAt(keyStr.length()/2) * 31 + (keyStr.length()) * 131) % table.length;
        return location;
    }

    // Specific:
    // Set location by adding the first letter, second letter multiplied by 101, length multiplied by 31
    // mod the sum by the length of the table
    // Longest collision length: 2
    private int hash4(T key) {
        int location;
        String keyStr = key.toString();
        if (keyStr.length() == 1)
            location = (keyStr.charAt(0) * (table.length + 29)) % table.length;
        else
        location = (keyStr.charAt(0) + keyStr.charAt(1) * 101 + keyStr.length() * 31) % table.length;
        return location;
    }

    // display() function displays the hash table along with the stats about the hash
    public void display() {
        int count, empty = 0, longest = 0, nonEmpty = 0, unique = 0;
        for (int i = 0; i < table.length; i++) {
            NGen<T> ptr = table[i];
            System.out.print(i + ": ");
            // if the location is not empty
            if (table[i] != null) {
                nonEmpty++;
                count = 1;
                unique++;
                while (ptr.getNext() != null) {
                    ptr = ptr.getNext();
                    count++;
                    unique++;
                }
                if (count > longest){
                    longest = count;
                }
                System.out.println(count);
            }
            else { // If the location is empty
                empty++;
                System.out.println(0);
            }
        }
        System.out.println("Average collision length: " + ((float)unique/(nonEmpty)));
        System.out.println("longest chain: " + longest);
        System.out.println("unique tokens: " + unique);
        System.out.println("empty indices: " + empty);
        System.out.println("non-empty indices: " + nonEmpty);
    }

    public void displayTest() {
        int count = 0, empty = 0, longest = 0, nonEmpty = 0, unique = 0;
        for (int i = 0; i < table.length; i++) {
            NGen<T> ptr = table[i];
            // if the location is not empty
            if (table[i] != null) {
                nonEmpty++;
                count = 1;
                unique++;
                while (ptr.getNext() != null) {
                    ptr = ptr.getNext();
                    count++;
                    unique++;
                }
                if (count > longest){
                    longest = count;
                }
            }
            else { // If the location is empty
                empty++;
            }
        }
        System.out.println("Average collision length: " + ((float)unique/(nonEmpty)));
        System.out.println("longest chain: " + longest);
        System.out.println("unique tokens: " + unique);
        System.out.println("empty indices: " + empty);
        System.out.println("non-empty indices: " + nonEmpty);
    }

    // Adapted from TextScan.java
    public void readToken(String myFile) {
        File f = new File(myFile);
        Scanner readFile = null;
        String s;
        int count = 0;

        System.out.println();
        System.out.println("Attempting to read from file: " + myFile);
        try {
            readFile = new Scanner(f);
        }
        catch (FileNotFoundException e) {
            System.out.println("File: " + myFile + " not found");
            System.exit(1);
        }
        System.out.println("Connection to file: " + myFile + " successful");
        System.out.println();
        while (readFile.hasNext()) {
            s = readFile.next();
            add((T)s);
            count++;

        }
        System.out.println();
        System.out.println(count + " Tokens found");
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        // general1: hash function 1, general2: hash function 2, general3: hash function 3
        HashTable<String> tableGen = new HashTable<String>(149, "general1");
        HashTable<String> tableSpec = new HashTable<String>(127, "specific");
        tableGen.readToken("gettysburg.txt");
        tableGen.display();
        tableSpec.readToken("keywords.txt");
        tableSpec.display();
    }

        // Initialize variables
    private NGen<T>[] table;
    public String type;

}
