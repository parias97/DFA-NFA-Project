// Created by Peter Arias

public class DFA2 {
    /* Language L = {ε, a, b, aa, bb, aba, bab, ababa, aabba, aaabbba,...}
     * string should start with an 'a' and end with an 'a'.
     */
    public DFA2(){

    }

    private enum Alphabet {
        A('a'), B('b');

        char input;

        Alphabet(char input){
            this.input = input;
        }
    }

    private enum States {
        // These enums represent the states included in the formal definition.
        Q0(true), Q1(true), Q2(false), Q3(true), Q4(false);

        // Instance variables that represent symbols in the alphabet
        States a;
        States b;
        final boolean accept;

        /* This static block represents the transitions for the DFA. For example,
         * Q0 is an object of type enum, States a is a handle to a enum object upon
         * transition: Q0.a ==> States a = Q1 (Q1 is a States object). */
        static {
            Q0.a = Q1;
            Q0.b = Q3;
            Q1.a = Q1;
            Q1.b = Q2;
            Q2.a = Q1;
            Q2.b = Q2;
            Q3.a = Q4;
            Q3.b = Q3;
            Q4.a = Q4;
            Q4.b = Q3;
        }

        /* Constructor for sate.
         * Every state is either accepting or not.
         */
        States(boolean accept) {
            this.accept = accept;
        }

        States transition(Alphabet symbol) {
            switch (symbol) {
                case A:
                    return this.a;
                case B:
                    return this.b;
                default:
                    return null;
            }
        }
    }

    // Check if the string is accepted or rejected.
    public boolean accept(String string){
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

    // Convert a character from the string into enum type Alphabet
    private Alphabet convertCharToSymbol(char c) throws InvalidSymbolException{
        if (c == Alphabet.A.input){
            return Alphabet.A;
        } else if (c == Alphabet.B.input){
            return Alphabet.B;
        } else {
            // throw exception
            throw new InvalidSymbolException("Symbol not in alphabet!");
        }
    }
}
