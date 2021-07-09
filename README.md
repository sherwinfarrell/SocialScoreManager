# SocialScoreManager
This project is a CLI based UrL Social Score Manager that processes commands from the CLI and performs the required functions.

The main file of the Project is:  
`src/main/scala/MainSocialScoreService.scala`  
This file contains the code that initiates a Stream that Listens to SystemIn inputs and process the commands using that. To run this project, the MainSocialScoreService needs to be run. This project uses sbt for dependency management and a list of dependencies need for this project will found there. 

The following commands are Recognized:

`ADD` -> The add command is used to add a Url and a corresponding score to the systems internal state.  
Eg: ADD https://www.google.com/2ad3 20 where 20 is the score associated with the URL.     

`REMOVE` -> The remove command removes any Url that may be present in the system's state.   
Eg: REMOVE https://www.google.com/2ad3  

`EXPORT` -> Displayes the Current State in the form of a CSV. 

`EXIT` -> Exits the Program.
