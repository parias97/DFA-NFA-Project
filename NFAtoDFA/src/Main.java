// Created by Peter Arias

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        NFA nfa = new NFA();
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter a string for the NFA to consume:");
        String word = scan.nextLine();
        System.out.println("NFA is reading string: " + word + '\n');
        boolean accept = nfa.accept(word);
        System.out.println("\nIs the word accepted? " + accept);

        // Create DFA from NFA.
        DFA dfa = convertNFAToDFA(nfa);
        System.out.println("\nDFA is reading string: " + word + '\n');
        accept = dfa.accept(word);
        System.out.println("\nIs the word accepted? " + accept);
    }

    public static DFA convertNFAToDFA(NFA nfa){
        DFA dfa = new DFA();
        nfa.convertToDFA();
        return dfa;
    }
}
