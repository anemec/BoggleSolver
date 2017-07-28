import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by andrew on 3/19/17.
 */
public class Trie {

    private static TrieElem root = new TrieElem();
    private static Trie INSTANCE;

    /*
        Creates a new trie and loads it with words from the dictionary
     */

    public static Trie getInstance() {
        return INSTANCE;
    }

    private Trie() throws FileNotFoundException {
        createTrie();
    }
    static {
        try {
            INSTANCE = new Trie();
        } catch (FileNotFoundException exc) {

        }
    }

    private void createTrie() throws FileNotFoundException {

        String word;

        Scanner input = new Scanner(new File("bigDictionary.txt"));

        //String answer = input.nextLine();
        //System.out.println(answer);

        while (input.hasNext()) {

            word = input.nextLine();

            addWordToSubtrie(word);
            //System.out.println(word + " added to trie");
        }

    }

    private void addWordToSubtrie(String word) {

        TrieElem current = root;

        for (char c : word.toCharArray()) {

            if (current.find(c, false)) {
                current = current.trieElemAt(c);
            } else {
                current.addToSubtrie(c);
                current = current.trieElemAt(c);
            }
        }

        current.setEnd();


    }


    public static boolean findWord(String word) {
        word = word.toLowerCase();
        char[] wordArr = word.toCharArray();
        System.out.println("Found " + word);

        TrieElem current = root;

        /*
        for (char c : word.toCharArray()) {
            if (current.find(c, )) {
                current = current.trieElemAt(c);
            } else {
                return false;
            }
        }
        */

        for (int i = 0; i < wordArr.length; i++) {
            if (current.find(wordArr[i], i == wordArr.length - 1)) {
                current = current.trieElemAt(wordArr[i]);
            } else {
                return false;
            }
        }

        return true;

    }

    public static boolean findPartialWord(String word) {
        word = word.toLowerCase();
        char[] wordArr = word.toCharArray();
        System.out.println("Found partial word " + word);

        TrieElem current = root;

        for (int i = 0; i < wordArr.length; i++) {
            if (current.find(wordArr[i], false)) {
                current = current.trieElemAt(wordArr[i]);
            } else {
                return false;
            }
        }

        return true;

    }



}



