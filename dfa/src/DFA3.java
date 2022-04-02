// Created by Peter Arias

public class DFA3 {
    /* Language L = {ε, 00, 11, 01, 000, 1111, ...}
     * Accepts a string in which every 0 is never followed by 11.
     */
    public DFA3(){

    }

    private enum Alphabet {
        ZERO('0'), ONE('1');

        char input;

        Alphabet(char input){
            this.input = input;
        }
    }

    private enum States {

        // These enums represent the states included in the formal definition.
        Q0(true), Q1(true), Q2(true), Q3(false);

        // Instance variables that represent symbols in the alphabet.
        States zero;
        States one;
        final boolean accept;

        static {
            Q0.zero = Q1;
            Q0.one = Q0;
            Q1.zero = Q1;
            Q1.one = Q2;
            Q2.zero = Q1;
            Q2.one = Q3;
            Q3.zero = Q3;
            Q3.one = Q3;
        }

        // Constructor for sate.
        // Every state is either accepting or not.
        States(boolean accept) {
            this.accept = accept;
        }

        States transition(Alphabet symbol) {
            switch (symbol) {
                case ZERO :
                    return this.zero;
                case ONE:
                    return this.one;
                default:
                    return null;
            }
        }
    }

    // Check if the string is accepted by the DFA and return a true or false.
    public boolean accept(String string) {
        States currState = States.Q0;
        States nextState;
        try {
            for (int i = 0; i < string.length(); i++) {
                Alphabet symbol = convertCharToSymbol(string.charAt(i));
                nextState = currState.transition(symbol);
                System.out.println(currState + " -> read " + symbol.input + " -> " + nextState);
                currState = nextState;
            }
        } catch(InvalidSymbolException ex){
            System.out.println(ex.getMessage());
            return false;
        }
        return currState.accept;
    }

    // Convert a character from the string into enum type Alphabet.
    private Alphabet convertCharToSymbol(char c) throws InvalidSymbolException{
        if (c == Alphabet.ZERO.input){
            return Alphabet.ZERO;
        } else if (c == Alphabet.ONE.input){
            return Alphabet.ONE;
        } else {
            // throw exception
            throw new InvalidSymbolException("Symbol not in alphabet!");
        }
    }
}
