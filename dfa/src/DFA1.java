// Created by Peter Arias

public class DFA1 {

    // L = {a^nb^mc^l | n,m,l ≥ 1} Given: Input alphabet, Σ={a, b, c}
    public DFA1(){

    }

    private enum Alphabet {
        A('a'), B('b'), C('c');

        char input;

        Alphabet(char input){
            this.input = input;
        }
    }

    private enum States {
        // These enums represent the states included in the formal definition.
        Q0(false), Q1(false), Q2(false), Q3(true), Q4(false);

        // Instance variables that represent symbols in the alphabet
        States a;
        States b;
        States c;
        final boolean accept;

        /* This static block represents the transitions for the DFA. For example,
         * Q0 is an object of type enum, States a is a handle to a enum object upon
         * transition: Q0.a ==> States a = Q1 (Q1 is a States object).
         */
        static {
            Q0.a = Q1;
            Q0.b = Q4;
            Q0.c = Q4;
            Q1.a = Q1;
            Q1.b = Q2;
            Q1.c = Q4;
            Q2.a = Q4;
            Q2.b = Q2;
            Q2.c = Q3;
            Q3.a = Q4;
            Q3.b = Q4;
            Q3.c = Q3;
            Q4.a = Q4;
            Q4.b = Q4;
            Q4.c = Q4;
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
                case C:
                    return this.c;
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

    private Alphabet convertCharToSymbol(char c) throws InvalidSymbolException{
        if (c == Alphabet.A.input){
            return Alphabet.A;
        } else if (c == Alphabet.B.input){
            return Alphabet.B;
        } else if (c == Alphabet.C.input) {
            return Alphabet.C;
        } else {
            throw new InvalidSymbolException("Symbol not in alphabet!");
        }
    }
}
