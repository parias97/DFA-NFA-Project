// Created by Peter Arias

public class DFA {

    public DFA() {

    }

    private enum States {

        Q0Q2(true), Q1(false), Q1Q2(false), Q2(false), Q0Q1Q2(true), EMPTYQ(false);

        final boolean accept;

        States a;
        States b;

        // This static block represents the transitions of the DFA.
        static {
            Q0Q2.a = Q0Q2;
            Q0Q2.b = Q1;
            Q1.a = Q1Q2;
            Q1.b = Q2;
            Q2.a = Q0Q2;
            Q2.b = EMPTYQ;
            Q1Q2.a = Q0Q1Q2;
            Q1Q2.b = Q2;
            Q0Q1Q2.a = Q0Q1Q2;
            Q0Q1Q2.b = Q1Q2;
        }

        // Constructor for a state.
        States(boolean accept) {
            this.accept = accept;
        }

        // Transition to the next state depending on input being consumed.
        States transition(char ch) {
            switch (ch) {
                case 'a':
                    return this.a;
                case 'b':
                    return this.b;
                default:
                    return null;
            }
        }
    }

    // Returns if string is accepted or rejected by the DFA.
    public boolean accept(String string) {
        States currState = States.Q0Q2;
        States nextState;

        try {
            for (int i = 0; i < string.length(); i++) {
                NFA.Alphabet symbol = NFA.convertCharToSymbol(string.charAt(i));
                nextState = currState.transition(string.charAt(i));
                System.out.println(currState + " -> read " + symbol.input + " -> " + nextState);
                currState = nextState;

                if (currState == null) {
                    return false;
                }
            }
        } catch (InvalidSymbolException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return currState.accept;
    }

    /* Helper method to convert a character from the string into enum type Alphabet.
     * This method references the NFAs alphabet because the DFA created from converting
     * an NFA must have the same alphabet.
     */
    private NFA.Alphabet convertCharToSymbol(char c) throws InvalidSymbolException {
        if (c == NFA.Alphabet.A.input) {
            return NFA.Alphabet.A;
        } else if (c == NFA.Alphabet.B.input) {
            return NFA.Alphabet.B;
        } else {
            // throw exception
            throw new InvalidSymbolException("Symbol not in alphabet!");
        }
    }
}
