FinalProject
============

<h2>
Michaela Papallo &amp; Emily Redler, period 6 APCS
</h2>

<h3>
Project Description
</h3>
Our project is a finals week simulator. Choose your age (difficulty level), freshman-senior. After that you will be taken through a week (Monday-Friday) in the life of a Stuyvesant student during finals week. You can choose whether you want to study or sleep, cut class or pass notes, and more. Random events will occur to undermine (or help) you--some may ask you to make more decisions. Energy, knowledge, and stress will be affected by your decisions and are taken into account for your test grade and final grade. At the end of the week, you take your finals. During the test, you can choose to cheat (with a chance of being caught), sleep, take the test normally, or skip it all together. At the end of the week, you will be given a final grade based on your choices. 

<h3>
Daily Logs
</h3>

<h4>
12/22/14
</h4>
* possible ideas:
    * solar system creator
    * finals week simulator

<h4>
1/5/15
</h4>
* start work on finals week simulator
* features discussed:
    * multiple slots in the day for choices (mon-fri) --> ex: during study hall, choose to sleep/talk/study
    * make abstract student class then instances for each grade level
    * beginning stress level increases as grade level increases
    * efficiency of study increases?
    * difficulty of finals/classes increases
    * can choose to cheat on finals with chance of getting caught based on sleep, preparedness, etc
created Student class and subclasses


<h4>
1/6/15
</h4>
* github problems with forked repository
* work on parent and subclasses


<h4>
1/7/15
</h4>
* mapping out our plan on paper
* started GUI creation and Driver, initializing player


<h4>
1/8/15
</h4>
* added random events 
* work on GUI display


<h4>
1/9/15
</h4>
* fixed formatting in readme
* added more methods in events
* moved initialization of character from terminal to GUI


<h4>
1/10/15
</h4>
* finished GUI setup
* initialization of character process is complete
* made Student abstract

<h4>
1/12/15
</h4>
* got rid of Events class, added to Students
* incorporating the story into the gui

<h4>
1/13/15
</h4>
* progress with incorporating events into the gui function that asks for user input

<h4>
1/14/15
</h4>
* added morning and after school
* continued user response methods

<h4>
1/15/15
</h4>
* inSchool function working

<h4>
1/16/15
</h4>
* finished morning
* btw the popQuiz function calculates the score weirdly- never above 50- fix that // FIXED 1/18/15

<h4>
1/17/15
</h4>
* added fonts, prettified GUI
* added energy, knowledge, stress bars
* made time am and pm

<h4>
1/18/15
</h4>
* finished after school
* made three class periods instead of two and fixed going from one period to another (was infinitely going to "last class of the day")
* changed so the next function (after school, in school, morning, etc.) is based on a string returned by previous function
* added homework (for some reason it got deleted or never saved before)
* fixed pop quiz scoring (just added 25 pts to score)
* FIX: after school: study, homework, facebook don't change the time (each should add 2 hrs) //fixed!
* FIX: after school: after time is 7am (when you sleep or when you get to that time by doing things), does one more cycle of after school --> maybe it isn't returning "morn" until time has already been 7? maybe time isn't updating? //fixed!
* fixed the above two problems by cycling through the days based on the time, not a returned string

<h4>
1/19/15
</h4>
* added finals day function
* added conclusion
* calculated final grade
* added quit and try again buttons 
* FIX: try again button doesn't work //fixed 1/20/15
* fixed final grade scoring (was subtracting too much if you doze off, cut class, etc)
* fixed other minor bugs

<h4>
1/20/15
</h4>
* fixed the try again button
* added a Responses class so that the same string is not always returned when an event occurs

<h3>
To do list
</h3>

<h4>
Most important
</h4>

<h4>
Extra stuff
</h4>
* array with teacher names, can choose a random one to incorporate
* array with classes, can choose a random one to incorporate
* can have different text for same events, randomly chosen
* add more text specific to difficulty level (ex. seniors, something about college recs) --> make the different levels more different in general
* prettify more --> maybe add pictures, graphics, etc? make energy, etc. bars colored? add delay?

