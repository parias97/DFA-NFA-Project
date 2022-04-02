Peter Arias
Programming Project
3/31/2020

I've included a zip file containing two different folders that contain the programs. One of them 
is for the DFA program and the other is for the NFA to DFA conversion program. In my TESTS.txt file,
I have included the different DFAs I've tested and also the NFA used for conversion along with the DFA 
created from the conversion. 

In the DFA program, the file Main.java is the driver of the program. You'll see three DFA objects: DFA1, DFA2,
and DFA3. I have commented out DFA2 and DF3, which if uncommented can be tested. There is one String variable named 
word that is assigned a string from user input that is going to be consumed by the DFA. This variable can
be changed to test different strings to check if it's accepted or rejected by the DFA. The program will display 
the word that the DFA is consuming, along with print outs of the transitions it takes and it will output whether 
the string is accepted or not.


In the NFAtoDFA program, the file Main.java is the driver of the program. The program creates an NFA and with that
NFA calls a method called convertNFAToDFA to create the DFA from the NFA. There is one String variable named word 
that is assigned a string from user input that is going to be consumed by the NFA. This variable can be changed to 
test different strings to check if it's accepted or rejected by both the NFA and the DFA created from converting the 
NFA. The program will display the word that the NFA and DFA are consuming, along with print outs of the transitions 
that both the NFA and DFA take and it will output whether the string is accepted or not by both the NFA and DFA.