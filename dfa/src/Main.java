// Created by Peter Arias

import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        DFA1 dfa = new DFA1();
        //DFA2 dfa = new DFA2();
        //DFA3 dfa = new DFA3();

        System.out.println("Enter a string for the DFA:");
        String word = scan.nextLine();
        System.out.println("DFA is consuming word: " + word);
        boolean isAccepted = dfa.accept(word);
        System.out.println("Is it accepted? " + isAccepted);
    }
}
