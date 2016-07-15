**********************************************************************************
********                             README                               ********
**********************************************************************************

Usage: Console application

This program calculate the evolution of datas from the file AgentTest.csv, through 
a simulation of 15 years of execution.

The program will ask a serie of inputs from the user:
_________________________________________________________________________________

1. "Do you want to run the 15 years with one unique brandfactor? (y/n):"

If the user says yes, the program will ask for one unique Brand Factor value 
and will run 15 years with this value.

If the user says no, the program will ask for one Brand Factor value, every year.
So, 15 times.

_________________________________________________________________________________
2. "Please, choose a brand factor between 0.1 and 2.9:"

The user has to choose a number between 0.1 and 2.9, if he enters something 
else, the program will re-ask the question.

If the user said 'no' to the previous question, the question would be:
"Please, choose a brand factor between 0.1 and 2.9 for year 1:"
And it will be ask 15 times, each time displaying the appropriate output.

Example:

Breed_NC	| Breed_C	| Breed_C Gained	| Breed_C Lost	| Breed_C Regained
--------------------------------------------------------------------------------
3806		| 5031		| 2096				| 922			| 0

_________________________________________________________________________________

3. Outputs: eventually, the program will display the full outputs for 15 years.

Example:

Synthesis:
Breed_NC	| Breed_C	| Breed_C Gained	| Breed_C Lost	| Breed_C Regained
--------------------------------------------------------------------------------
3806		| 5031		| 2096				| 922			| 0
3175		| 5662		| 2727				| 2096			| 922
2705		| 6132		| 3197				| 1728			| 2096
3073		| 5764		| 2829				| 2096			| 1728
2705		| 6132		| 3197				| 1728			| 2096
3073		| 5764		| 2829				| 2096			| 1728
2705		| 6132		| 3197				| 1728			| 2096
4791		| 4046		| 1111				| 3814			| 10
2414		| 6423		| 3488				| 1437			| 2387
3364		| 5473		| 2538				| 2387			| 1437
2414		| 6423		| 3488				| 1437			| 2387
4510		| 4327		| 1392				| 3533			| 291
2092		| 6745		| 3810				| 1115			| 2709
3686		| 5151		| 2216				| 2709			| 1115
2092		| 6745		| 3810				| 1115			| 2709

_________________________________________________________________________________

4. "Do you want to start again? (y/n):"

If the user says yes ('y'), the program will restart from teh beginning.
Else, the program will stop, displaying: "Goodbye!"
_________________________________________________________________________________
