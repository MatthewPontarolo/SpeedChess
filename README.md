# SpeedChess
<br>
Annie Bai, Matthew Pontarolo, Kevin Son, Glen Taggart, Shu Yang
<br>

# Project Description: <br>
A multiplayer game of chess, with some rules changed to make the game fast-paced with players having to utilize their predictive intuitions. Rather than alternating turns, players take turns and choose moves simultaneously, adding hidden information to the system of chess and requiring players to think a couple steps ahead of their opponents throughout the game.
<br>

# Rules: <br>
General rules follow the basic game of chess, but there are added special features where turns are executed simulataneously and such scenarios will be handled accordingly. Once a game has begun, players take Turns until a player wins, the game draws, or the players disconnect/quit
<br>
<br> Scenario #1: Space Occupancy Conflict ```<extends>``` Turn <br>
Given two game pieces and both move to occupy the same space in the same turn, when the game attempts to execute the turn, then the priority of the move will be determined by whomever inputted their move the quickest and the slower player’s game piece will be taken. <br>

<br> Scenario #2: Pawn Capture ```<extends>``` turn <br>
Given a pawn and another game piece and the piece is directly diagonal from the pawn, when the pawn attempts to capture that piece and is unsuccessful because it has moved away in that same turn, then it will be forced to move forward one space if that space is not occupied. If that space becomes occupied any time during that same turn, that player will end its turn with no moves. <br>

<br> Scenario #3: Swap Space ```<extends>``` turn <br>
Given two game pieces attempt to capture each other in the same move, the pieces will swap spaces on the board and neither will be taken. <br>

<br> Scenario #4: User Doesn’t Input a Move in Time ```<extends>``` turn <br>
Given the next turn timer runs and both users get the chance to input their next move, when either or both players don’t input their move within the time frame, then the program will randomly select a move for the player. <br>

<br> Scenario #5: User Doesn’t Know Their Next Move ```<extends>``` turn <br>
Given the user has trouble placing their next move or uncertain about it, when the timer starts for both players to input their move, then the user can click on a piece for the program to show which moves are available for that piece and the user will either know which move to input or validate their move. <br>




# How to Run <br>
Requirements: <br>
[Java 1.8+ Version](http://www.oracle.com/technetwork/java/javase/downloads/jdk10-downloads-4416644.html) <br>
Be able to run ```maven``` on terminal. <br>
<br>Our program can be started using [Maven](https://maven.apache.org/) from the command line, or using Intellij IDEA. To run from command line:<br> 
<br>clone<br>
```git clone https://github.com/MatthewPontarolo/SpeedChess.git```<br>
<br>Go into directory <br>
```cd SpeedChess``` <br>
<br>Execute the following to install dependencies, compile, build, and launch the program using spring boot: <br> 
```mvn spring-boot:run``` <br>
<br><br>If using IDEA, to set up, under the Maven tool (you may need to turn on viewing of "tool buttons" to get the maven button), press the green plus symbol "Add Maven Projects" and add the pom.xml in this directory. To run, select spring-boot:run under the spring boot plugin of our project under the Maven tool.
<br>

# How to Run Server with Heroku<br>
####Requirements: <br>
[Heroku](https://dashboard.heroku.com/login) account (free is sufficient)<br>

<br> Navigate to the Heroku Dashboard, create a new App, then navigate to it's settings --> buildpacks --> add buildpack --> "java"
<br> Clone the Server branch
<br> #####Now, we send this to Heroku. Many options: [Heroku CLI](https://devcenter.heroku.com/articles/heroku-cli)<br>, Intellij IDEA, and Github can all easily deploy the app. 
<br> For Github, push this repository to a Github repo, navigate to: the App --> Heroku Dashboard --> Deploy --> Deployment Method --> Github
<br> Connect the apppropriate repo and branch. The procfile will build the server and pass the correct port to operate on to the Server from Heroku's environment variables.
<br> To use the Client with a different server than default, change the variable "url" in ClientConnector to math the url of the Heroku deployment of the server.
