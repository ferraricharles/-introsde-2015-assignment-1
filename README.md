For testing the software proceed with the following procedure:

the instruction [folder] represents the folder where you pasted the source

##############
For testing JSon Creation:
##############
Make sure you have ant installed on your machine and then, type on console 
the following instructions

  cd [folder]/charles 
  ant compile
  ant execute.HPJson


##############
For testing XML file creation
##############
Type the following instructions

  cd [folder]/charles/src
  javac HealthProfileWriter.java
  java HealthProfileWriter


##############
For testing the navegation on the executed file
##############

Type the following instructions
  cd [folder]/charles/src
  javac HealthProfileReader.java
  java HealthProfileReader

Now that you are executing the program, you just need to follow the main menu 
instructions in order to try out the Profile Reader funcions

##############
Useful information
##############

-The IDs go from 1 to 20
- 

