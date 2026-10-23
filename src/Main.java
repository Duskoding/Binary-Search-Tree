import java.util.ArrayList;

public class Main{
    public static void main(String[] args) {
        WordHamster hamster = new WordHamster();
        BinarySearchTree<String> tree = new BinarySearchTree<>();

        if(hamster.gatherWords("Money.txt")){
            ArrayList<String> words = hamster.exposeWords();

            for(String word : words){
                tree.insert(word);
            }

            System.out.println("run:");
            System.out.println("word count: " + tree.getTotalWordCount());
            tree.printTree();
            System.out.printf("\nTTR ratio is: %.4f", tree.getTtr());
        }else{
            System.out.println("cant find Money.txt");
        }
    }
}