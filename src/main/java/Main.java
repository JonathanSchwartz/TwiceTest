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

    }
    static Set<String> generatedWords = new HashSet<>();
    static void generateWords(String inputCharacters) {
        StringBuffer wordInProgress = new StringBuffer();
        for(int length = 2; length < inputCharacters.length(); length++) {
            for (int i = 0; i < inputCharacters.length(); i++) {
                wordInProgress.setLength(0);
                wordInProgress.append(inputCharacters.charAt(i));

            }
        }
    }
    static void generateWords(String inputCharacters, int wordLength) {

    }
}
