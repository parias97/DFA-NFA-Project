// Created by Peter Arias

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class NFA {

    public NFA(){

    }

    // Alphabet accepted by NFA.
    public enum Alphabet {
        A('a'), B('b');

        char input;

        Alphabet(char input){
            this.input = input;
        }
    }

    private enum States {

        Q0(true), Q1(false), Q2(false);

        final boolean accept;

        /* Each set represents a set of states that a current state can transition to when
         * consuming 'a', 'b', or epsilon.
         */
        Set<States> a;
        Set<States> b;
        Set<States> epsilon;

        // This static block represents the transitions of the NFA.
        static {
            Q0.a = Collections.EMPTY_SET;
            Q0.b = new HashSet<>(Arrays.asList(Q1));
            Q0.epsilon = new HashSet<>(Arrays.asList(Q2));

            Q1.a = new HashSet<>(Arrays.asList(Q1, Q2));
            Q1.b = new HashSet<>(Arrays.asList(Q2));
            Q1.epsilon = Collections.EMPTY_SET;

            Q2.a = new HashSet<>(Arrays.asList(Q0));
            Q2.b = Collections.EMPTY_SET;
            Q2.epsilon = Collections.EMPTY_SET;
        }

        // Constructor for a state.
        States(boolean accept) {this.accept = accept;}

        // Transition to the next state depending on input being consumed.
        Set<States> transition(Alphabet symbol) {
            switch (symbol) {
                case A: return this.a;
                case B: return this.b;
                default: return null;
            }
        }

        /* Return a set of states that can be reached from the current state when
         * taking a path that leads to a different state on epsilon.
         * This method represents E-Closure.
         */
        Set<States> eClose() {
            Set<States> states = new HashSet<>();
            states.add(this);

            for(States e: this.epsilon) {
                states.addAll(e.eClose());
            }

            return states;
        }
    }

    // Returns if the string is accepted or rejected by the NFA.
    public boolean accept(String string){
        Set<States> currStates = new HashSet<>(States.Q0.eClose());
        Set<States> nextStates;

        try {
            for (int i = 0; i < string.length(); i++) {
                Alphabet symbol = convertCharToSymbol(string.charAt(i));
                nextStates = union(currStates, symbol);
                System.out.println(currStates + " -> read " + symbol.input + " -> " + nextStates);
                currStates = nextStates;

                if(currStates == null){
                    return false;
                }
            }
        } catch(InvalidSymbolException ex){
            System.out.println(ex.getMessage());
            return false;
        }

        return currStates.stream().anyMatch(s -> s.accept);
    }

    // Helper method which returns set of next states.
    private Set<States> union(Set<States> currStates, Alphabet symbol) {
        Set<States> nextStates = new HashSet<>();

        /* For the current state, find the e-closure of the state(s) transitioned to from reading the
         * symbol and add those to the hashset nextStates.
         */
        for (States cState : currStates) {
            for (States s : cState.transition(symbol)) {
                nextStates.addAll(s.eClose());
            }
        }
        return nextStates;
    }

    // Helper method to convert a character from the string into enum type Alphabet
    public static Alphabet convertCharToSymbol(char c) throws InvalidSymbolException {
            if (c == Alphabet.A.input) {
                return Alphabet.A;
            } else if (c == Alphabet.B.input) {
                return Alphabet.B;
            } else {
                // throw exception
                throw new InvalidSymbolException("Symbol not in alphabet!");
            }
    }

    protected void convertToDFA (){
        System.out.println("\nConverting NFA to DFA ...\n\nFinding all the states of DFA ...");
        System.out.println("Q' = P(Q) = {∅, {Q0}, {Q1}, {Q2}, {Q0,Q1} , {Q0,Q2}, {Q1,Q2}, {Q0,Q1,Q2}}");
        System.out.println("Initializing DFA start & final state(s) from NFA ...\nQ'0"  + " = E({" + States.Q0 + "}) = {" + States.Q0.eClose() + "}");
        System.out.println("F' = {{Q0}, {Q0,Q1}, {Q0,Q2}, {Q0,Q1,Q2}} ");
    }
}
