import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Main {
    static Set<String> wordList = new HashSet<>();
    public static void main(String[] args) {
        if(args.length < 1) {
            System.out.println("Usage: program <lettersjumble (no spaces)> [dictionary file to use, defaults to en_US.dic in the working directory]");
        }
        String dictionaryFile = "en_US.dic";
        if(args.length > 1) {
            dictionaryFile = args[1];
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(dictionaryFile));
            String line = null;
            while((line = reader.readLine()) != null) {
                line = line.trim();
                if(!line.equals("")) {
                    wordList.add(line.split("/")[0].toUpperCase());
                    //only supporting the basic locale currently, the entire concept of word jumble kindof falls apart
                    //for the languages that this basic locale really doesn't work for, so this should be good enough.
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Error: Unable to load dictionary file.  Please check to make sure that the file " + dictionaryFile + " exists and is accessible.");
        }
        catch (IOException e) {
            System.out.println("Error: exception while reading dictionary file. " + e.toString());
            e.printStackTrace();
        }
        generateWords(args[0]);
        System.out.println("Generated: " + Integer.toString(generatedWords.size()));
        for(String s:generatedWords) {
            System.out.println(s);
        }
    }
    static Set<String> generatedWords = new HashSet<>();
    static void generateWords(String inputCharacters) {
        StringBuilder wordInProgress = new StringBuilder();
        for(int length = 2; length <= inputCharacters.length(); length++) {
            for (int i = 0; i < inputCharacters.length(); i++) {
                wordInProgress.setLength(0);
                wordInProgress.append(inputCharacters.charAt(i));
                //not the most efficient, but it will do for the small strings we are likely to be dealing with here
                String remainingCharacters = inputCharacters.substring(0,i) + inputCharacters.substring(i+1);
                generateWords(wordInProgress, remainingCharacters, length);
            }
        }
    }
    static void generateWords(StringBuilder partial, String inputCharacters, int wordLength) {
        int currentLength = partial.length();
        for (int i = 0; i < inputCharacters.length(); i++) {
            //erase any other characters added
            partial.setLength(currentLength);
            partial.append(inputCharacters.charAt(i));
            if(partial.length() == wordLength) {
                String curWord = partial.toString().toUpperCase();
                if(wordList.contains(curWord)) {
                    generatedWords.add(curWord);
                }
            }
            else {
                //not the most efficient, but it will do for the small strings we are likely to be dealing with here
                String remainingCharacters = inputCharacters.substring(0, i) + inputCharacters.substring(i + 1);
                generateWords(partial, remainingCharacters, wordLength);
            }
        }
    }
}
