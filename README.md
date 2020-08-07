# CSX42: Assignment 5
**Name:Krupa Sawant
**B-Number:B00 814013

-----------------------------------------------------------------------

Following are the commands and the instructions to run ANT on your project.

Note: build.xml is present in (./textdecorators/src/) folder.

## Instruction to clean:

```commandline
ant -buildfile textdecorators/src/build.xml clean
```

Description: It cleans up all the .class files that were generated when you
compiled your code.

## Instructions to compile:

```commandline
ant -buildfile textdecorators/src/build.xml all
```
The above command compiles your code and generates .class files inside the BUILD folder.

## Instructions to run:

```commandline
ant -buildfile src/build.xml run -Dinput="<path/to/inputfile>" -Dmisspelled="<path to misspelled file>" -Dkeywords="<path/to/keywordsfile>" 
-Doutput="<path/to/outputfile>" -Ddebug= debuglevel

```
Note: Arguments accept the absolute path of the files.


## Description:
## Assumptions
    1. Input line contains valid characters [a-zA-Z0-9\.,\s]
    2. Program doesn't check for commas. 
    3. For the debug part the debug levels are levels 
        debugvalue=0 for Driver class,             debugvalue=1 for Constructor,
        debugvalue=2 for Fileprocessor class  ,    debugvalue=3 for MostFrequentWordsDecorator class,
        debugvalue=4 for KeywordDecorator class , debugvalue=5 for SpellCheckDecorator class
        debugvalue=6 forSentenceDecorator class            
     4. ant -buildfile src/build.xml run -Dinput="input.txt" -Dmisspelled="misspelled.txt"-Dkeywords="keywords.txt" -Doutput="output.txt" -Ddebug=7
    
## Program FLow:
 1. Driver code accepts command line argument, creates instances of decorators and inputdetails.
 2. Input Deatils accepts input using file processor and passes it to decorator MostFrequentDecorator.
 3. In the same way, after every added string keyword to current input it passes through remaining decorators KeywordDecorator,SpellCheckDecorator and SentenceDecorator
    in sequence 
 4. All decorators implement the Abstract Text Decorator and override the method processInputDetails().
 5. Main function of InputDetails.java is to store input in array list , contain various method to retrieve and update array list and print final output to console and file.
 6. Every decorator has access to arraylist of InputDetails.
 
 
 ## Use of ArrayList for DataStructure
 1.Major benefit of using arraylist is it is dynamic in size. 
 2.Array List are the simplest structure to store sentences and operations like adding and updating become easy
 
## Academic Honesty statement:

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: [8/07/2020]


