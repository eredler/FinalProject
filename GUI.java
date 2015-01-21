import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.geom.*;
public class GUI extends JFrame implements ActionListener{

    /////////////////////////
    Random r = new Random();
    String[] days = new String[5];
    int dayi = 0;

    private Student player;
    private Container window;
    private JSplitPane pane;
    private JPanel stats, interact;
    private JLabel stress, knowledge, energy, time, day, story, q;
    private JTextField llamo;
    private boolean bcheat = false;
    private boolean bclimb = true;
    private boolean bstayHome = false;
    private boolean bhelp = false;
    private int inClass = 2;
    private int afSchool = 3;
    private int inFinal = 3;
    private String s = "";
    private JLabel extraSpace = new JLabel("<html><br><br><br></html>"); 
    private JLabel tPic = new JLabel();
    private JLabel dayNight = new JLabel();
    Font font = new Font("Optima", Font.PLAIN, 20);
    Font eventFont = new Font("Optima", Font.PLAIN, 16);
    Font statsFont = new Font("Optima", Font.PLAIN, 14);
    Font buttonFont = new Font("Optima", Font.BOLD, 14);
    Font resultFont = new Font("Optima", Font.BOLD, 50);
    /////////////////////////
    public GUI(){
	player = new Freshman();
	story = new JLabel();
	q = new JLabel();
	story.setFont(font);
	q.setFont(font);
	days[0] = "Monday";
	days[1] = "Tuesday";
	days[2] = "Wednesday";
	days[3] = "Thursday";
	days[4] = "Friday";
	interact = new JPanel();
	stats = new JPanel();

	this.setTitle("Stuyvesant Finals Week Simulator");
   	this.setSize(800, 600);
	this.setLocation(100, 100);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	window = this.getContentPane();
	window.setLayout(new BorderLayout());
	setupStatsPanel();
	setupInteractPanel();
	
	Dimension minSize = new Dimension(300,300);
	stats.setMinimumSize(minSize);
	interact.setMinimumSize(minSize);

	pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, stats, interact);
	pane.setOneTouchExpandable(true);
	pane.setResizeWeight(0.5);
	pane.setBackground(Color.WHITE);
	stats.setBackground(Color.WHITE);
	interact.setBackground(Color.WHITE);
	window.add(pane, BorderLayout.CENTER);
    }

    /////////////////////////
    ///       setup       ///
    /////////////////////////
    public void setupStatsPanel(){
	BoxLayout b = new BoxLayout(stats, BoxLayout.Y_AXIS);
	stats.setLayout(b);
	stats.setBorder(BorderFactory.createCompoundBorder(
           BorderFactory.createTitledBorder("Stats"),
           BorderFactory.createRaisedBevelBorder()));
	stats.setMinimumSize(new Dimension(100,100));
	stress = new JLabel("stress: ");
	stress.setBorder(BorderFactory.createEmptyBorder());
	stats.add(stress);
	knowledge = new JLabel("knowledge: ");
	knowledge.setBorder(BorderFactory.createEmptyBorder());
	stats.add(knowledge);
	energy = new JLabel("energy: ");
	energy.setBorder(BorderFactory.createEmptyBorder());
	stats.add(energy);
	//	time = new JLabel("time: ");
	//	time.setBorder(BorderFactory.createEmptyBorder());
	//stats.add(time);
	day = new JLabel("Today is ");
	day.setBorder(BorderFactory.createEmptyBorder());
	stats.add(day);

	stress.setFont(statsFont);
	energy.setFont(statsFont);
	day.setFont(statsFont);
	//	time.setFont(statsFont);
	knowledge.setFont(statsFont);
    }
    public void setupInteractPanel(){
	BoxLayout b = new BoxLayout(interact, BoxLayout.Y_AXIS);
	interact.setLayout(b);
	interact.setBorder(BorderFactory.createCompoundBorder(
           BorderFactory.createTitledBorder("Story"),
           BorderFactory.createRaisedBevelBorder()));
	JLabel intro = new JLabel("<html>Welcome to the Stuyvesant Finals Week Simulator!<br>Please enter your name and choose a difficulty.<br> </html>");
	llamo = new JTextField("Harry Potter");
	llamo.setSize(5, 10);
	interact.add(intro);
	interact.add(llamo);
	llamo.setFont(font);
	addDifficultyOptions();
	interact.add(extraSpace);
	JButton next = new JButton("Next");
	next.setActionCommand("next");
	next.addActionListener(this);
	interact.add(next);

	next.setFont(buttonFont);
	intro.setFont(eventFont);
	next.setHorizontalAlignment(JButton.CENTER);
	intro.setHorizontalAlignment(JLabel.CENTER);
    }

    public void addDifficultyOptions(){
	JRadioButton f = new JRadioButton("Freshman (Easy Street)");
	f.setActionCommand("Freshman");
	f.setSelected(true);
	f.addActionListener(this);
	JRadioButton s = new JRadioButton("Sophomore");
	s.setActionCommand("Sophomore");
	s.addActionListener(this);	
	JRadioButton jr = new JRadioButton("Junior");
	jr.setActionCommand("Junior");	
	jr.addActionListener(this);	
	JRadioButton sr = new JRadioButton("Senior (Hell Week)");
	sr.setActionCommand("Senior");
	sr.addActionListener(this);	
	ButtonGroup difficulty = new ButtonGroup();
	difficulty.add(f);
	difficulty.add(s);
	difficulty.add(jr);
	difficulty.add(sr);
        interact.add(f);
	interact.add(s);
	interact.add(jr);
	interact.add(sr);

	f.setFont(buttonFont);
	s.setFont(buttonFont);
	jr.setFont(buttonFont);
	sr.setFont(buttonFont);
    }

    /////////////////////////
    ///  updating stats   ///
    /////////////////////////
    public void updateStress(int s){
	stress.setText("stress: " + s);
    }
    public void updateKnowledge(int k){
	knowledge.setText("knowledge: " + k); 
    }
    public void updateEnergy(int e){
	energy.setText("energy: " + e);
    }
    public void updateTime(int t){
	if (t <= 12){
	    tPic.setIcon(new ImageIcon(GUI.class.getResource("/images/" + t + ".png")));
	    tPic.setHorizontalAlignment(JLabel.CENTER);
	} else {
	    tPic.setIcon(new ImageIcon(GUI.class.getResource("/images/" + (t-12) + ".png")));
	    tPic.setHorizontalAlignment(JLabel.CENTER);
	}
	if (t > 6 && t < 19) {
	    dayNight.setIcon(new ImageIcon(GUI.class.getResource("/images/sun.png")));
	    dayNight.setHorizontalAlignment(JLabel.CENTER);
	} else {	    
	    dayNight.setIcon(new ImageIcon(GUI.class.getResource("/images/moon.png")));
	    dayNight.setHorizontalAlignment(JLabel.CENTER);
	}
    }
    public void updateDay(String d){
	day.setText("Today is " + d + ".");
    }
    public void autoUpdate(){
	updateStress(player.getStress());
	updateKnowledge(player.getKnow());
	updateEnergy(player.getEnergy());
	updateTime(player.time);
	updateDay(days[dayi]);	
	stats.add(dayNight);
	stats.add(tPic);
	window.repaint();
    }
    public void reset(){
	interact.removeAll();
	interact.revalidate();
	window.repaint();
    }
    
    /////////////////////////
    ///       intro       ///
    /////////////////////////
    public void initializePlayerAndStats(String name){
	player.setName(name);
        updateStress(player.getStress());
	updateEnergy(player.getEnergy());
	updateKnowledge(player.getKnow());
    }

    public void startGame(){
        reset();
	story.setText("<html><center>Hi, " + player + "! So you're a " + player.getLevel() + " at Stuyvesant, and it's finally time for the week everyone dreads...<br><br>Will you die in 5 days, or emerge victorious? It all depends on your choices...</center><br><br><br> </html>");
	interact.add(story);
	JButton b = new JButton("Begin");
	b.setActionCommand("begin");
	b.addActionListener(this);
	interact.add(b);

	story.setHorizontalAlignment(JLabel.CENTER);
	b.setFont(buttonFont);
    }

    /////////////////////////
    /// user interaction  ///
    /////////////////////////

    ///// morning /////
    public void morning(){
	reset();
	dayi += 1;
	updateDay(days[dayi]);
	autoUpdate();
	player.setHomework(false);
	String z = "";
	if (dayi == 4){
	    z = "<html>The dreaded day has finally arrived: Finals day.<br><br> You have three finals to take today, which will count for 25% of your overall grade. No pressure.<br><br><br> </html>";
	} else {
	    z = "<html>It's a fresh, new day! <br>As usual, you wake up and instantly regret doing so. Time to get ready for school...<br><br><br> </html>";
	}
	story.setText(z);
	interact.add(story);

	int chance = player.calculateChanceNeg();
	chance += 5;
	if (r.nextInt(100) < chance && dayi != 4){
	    int x = r.nextInt(4);
	    switch (x) {
	    case 0: q.setText(player.eatenHomework());
		displayResponse();
		break;
	    case 1: q.setText(player.subwayDelay());
		displayResponse();
		break;
	    case 2: q.setText(player.coffeeSpill());
		displayResponse();
		break;
	    case 3: JLabel l = new JLabel("<html>Wait, wha... wh... AH-CHOO!<br>Guess you couldn't escape the flu forever... Should you stay or should you go (to school)?<br><br> </html>");
		l.setFont(eventFont);
		interact.add(l);
		sickDayResponse();
		break;
	    }
	}else{
	    player.time += 2;
	    if (dayi != 4){
		q.setText("<html>Thankfully, the morning is uneventful and you get to school in one piece.<br><br><br> </html>");
	    } else {
		q.setText("<html>You arrive at school on time and in one piece (although a part of you wishes you had 'accidentally' forgotten to set your alarm).<br><br><br> </html>");
	    }
	    displayResponse();  
	}
    }

    public void sickDayResponse(){
	JRadioButton stay = new JRadioButton("'What!? I can't go to school like this!'");
	stay.setActionCommand("stayHome");
	stay.addActionListener(this);
	JRadioButton go = new JRadioButton("'I'm a survivor, I can do it'");
	go.setActionCommand("goToSchool");
	go.addActionListener(this);
	ButtonGroup sickDay = new ButtonGroup();
	sickDay.add(stay);
	sickDay.add(go);
	interact.add(stay);
	interact.add(go);
	interact.add(extraSpace);
	
	JButton b = new JButton("Submit");
	b.setActionCommand("sickDayResponse");
	b.addActionListener(this);
	interact.add(b);
	

	
	go.setFont(eventFont);	
	stay.setFont(eventFont);
    }

    ///// in school /////
    public void inSchool(){
	reset();
	autoUpdate();
	if (player.time == 9){
	    story.setText("<html>You are in your first class of the day.<br><br> </html>");
	}else if (player.time == 11){
	    story.setText("<html>Another period of learning. At least it means lunch is closer.<br><br> </html>");
	}else{
	    story.setText("<html>One eternity later, you are finally in your last class of the day!<br><br> </html>");

	}
	story.setHorizontalAlignment(JLabel.LEFT);
	interact.add(story);

	int chance = player.calculateChanceNeg();
	if (r.nextInt(100) < chance){
	    int e = r.nextInt(3);
	    switch (e) {
	    case 0: JLabel l = new JLabel("<html>Your teacher decides to spring a pop quiz on your class!<br><br> </html>");
		l.setFont(eventFont);
		interact.add(l);
		popQuizResponse();
		break;
	    case 1: q.setText(player.fireDrill());
		displayResponse();
		break;
	    case 2: JLabel m = new JLabel("<html>What a surprise, the escalators leading up to your class are broken! Do you want to endure the climb up the stairs, or just cut class?<br><br> </html>");
		m.setFont(eventFont);
		interact.add(m);
		brokenEscalatorResponse();
		break;
	    }
	} else {
	    JLabel n = new JLabel("<html>Nothing special is happening. What should you do?<br><br> </html>");
	    n.setFont(eventFont);
	    interact.add(n);
	    classTimeResponse();
	}
    }
    
    public void popQuizResponse(){
	JRadioButton cheat = new JRadioButton("cheat off of the kid next to you");
	cheat.setActionCommand("cheat");
	cheat.addActionListener(this);
	JRadioButton quiz = new JRadioButton("honestly take the quiz");
	quiz.setActionCommand("takeQuiz");
	quiz.addActionListener(this);
	ButtonGroup quizGroup = new ButtonGroup();
	quizGroup.add(cheat);
	quizGroup.add(quiz);
	interact.add(cheat);
	interact.add(quiz);
	JButton b = new JButton("Submit");
	b.setActionCommand("popQuizResponse");
	b.addActionListener(this);
	interact.add(extraSpace);
	interact.add(b);

	cheat.setFont(eventFont);
	quiz.setFont(eventFont);
	b.setFont(buttonFont);
    }
    public void brokenEscalatorResponse(){
	JRadioButton climb = new JRadioButton("climb up the escalators");
	climb.setActionCommand("climb");
	climb.addActionListener(this);
	JRadioButton no = new JRadioButton("absolutely not, just cut class");
	no.setActionCommand("noClimb");
	no.addActionListener(this);
	ButtonGroup escalator = new ButtonGroup();
	escalator.add(climb);
	escalator.add(no);
	interact.add(climb);
	interact.add(no);
	JButton b = new JButton("Submit");
	b.setActionCommand("brokenEscalatorResponse");
	b.addActionListener(this);
	interact.add(extraSpace);
	interact.add(b);

	climb.setFont(eventFont);
	no.setFont(eventFont);	
	b.setFont(buttonFont);
    }
    public void classTimeResponse(){
	JRadioButton sleep = new JRadioButton("doze off");
	sleep.setActionCommand("nap");
	sleep.addActionListener(this);
	JRadioButton passNotes = new JRadioButton("whisper/ pass notes to your friends");
        passNotes.setActionCommand("passNotes");
	passNotes.addActionListener(this);
	JRadioButton learn  = new JRadioButton("actually pay attention");
	learn.setActionCommand("learn");
	learn.addActionListener(this);
	ButtonGroup classTime = new ButtonGroup();
	classTime.add(sleep);
        classTime.add(passNotes);
	classTime.add(learn);
	interact.add(sleep);
	interact.add(passNotes);
	interact.add(learn);
	interact.add(extraSpace);
	JButton b = new JButton("Submit");
	b.setActionCommand("classTimeResponse");
	b.addActionListener(this);
	interact.add(b);
	
	sleep.setFont(eventFont);
	passNotes.setFont(eventFont);
	learn.setFont(eventFont);
	b.setFont(buttonFont);
    }

    
    ///// after school /////
    public void afterSchool(){
	reset();
	player.checkStats();
	player.checkTime();
	autoUpdate();
	if (player.time == 15){
	    story.setText("<html>School's out for the day! Now you have some time to do whatever you want.<br><br> </html>");
	}else{
	    story.setText("<html>It sure is nice to be home.<br><br> </html>");
	}
	interact.add(story);

	int chance = player.calculateChanceNeg();
	chance -= 5;

	if ((r.nextInt(100) < chance) && (player.time == 3)) {
	    JLabel l = new JLabel("<html>Your friend asks you to help them rehearse for SING!. Of course, this will reduce your precious free time. What do you do?<br><br> </html>");
	    l.setFont(eventFont);
	    interact.add(l);
	    interact.add(extraSpace);
	    helpAFriendResponse();
	} else {
	    JLabel n = new JLabel("<html>Home sweet home! What do you want to do now?<br><br> </html>");
	    n.setFont(eventFont);
	    interact.add(n);
	    interact.add(extraSpace);
	    afterSchoolResponse();
	}
    }

    public void afterSchoolResponse(){
	JRadioButton study = new JRadioButton("study");
	study.setActionCommand("studyNotes");
	study.addActionListener(this);
	JRadioButton homework  = new JRadioButton("do your homework");
	homework.setActionCommand("doHomework");
	homework.addActionListener(this);
	
	JRadioButton facebook = new JRadioButton("go on Facebook");
	facebook.setActionCommand("onFacebook");
	facebook.addActionListener(this);
	JRadioButton sleep = new JRadioButton("go to sleep");
	sleep.setActionCommand("goToSleep");
	sleep.addActionListener(this);
	ButtonGroup afterSchoolGroup = new ButtonGroup();
	afterSchoolGroup.add(study);
	afterSchoolGroup.add(homework);
	afterSchoolGroup.add(facebook);
	afterSchoolGroup.add(sleep);
	interact.add(study);
	if (player.getHomework() == false){
	    interact.add(homework);
	}
	interact.add(facebook);
	interact.add(sleep);
	interact.add(extraSpace);
	JButton b = new JButton("Submit");
	b.setActionCommand("afterSchoolResponse");
	b.addActionListener(this);
	interact.add(b);

	study.setFont(eventFont);
	homework.setFont(eventFont);
	facebook.setFont(eventFont);
	sleep.setFont(eventFont);
	b.setFont(buttonFont);
    }

    public void helpAFriendResponse(){
	JRadioButton yes = new JRadioButton("help your friend");
	yes.setActionCommand("helpFriend");
	yes.addActionListener(this);
	JRadioButton no = new JRadioButton("let your friend rehearse alone");
	no.setActionCommand("dontHelp");
	no.addActionListener(this);
	ButtonGroup helpFriendGroup = new ButtonGroup();
	helpFriendGroup.add(yes);
	helpFriendGroup.add(no);
	interact.add(yes);
	interact.add(no);
	interact.add(extraSpace);
	JButton b = new JButton("Submit");
	b.setActionCommand("helpAFriendResponse");
	b.addActionListener(this);
	interact.add(b);

	yes.setFont(eventFont);
	no.setFont(eventFont);
	b.setFont(buttonFont);
    }
    
    ///// Friday, a.k.a. Finals Day /////
    public void finalsDay(){
	reset();
	autoUpdate();

	String finalNum = "";
	if (player.time == 9) {
	    finalNum = "first";
	} else if (player.time == 11) {
	    finalNum = "second";
	} else {
	    finalNum = "last";
	}

	JLabel l = new JLabel("<html>It's time for your " + finalNum + " final. All of your hard work (or not so hard work) comes down to this. What do you want to do?<br><br><br> </html>");

	l.setFont(eventFont);
	interact.add(l);
	finalsDayResponse();
       
    }

    public void finalsDayResponse() {
	JRadioButton skip = new JRadioButton("skip the final exam");
	skip.setActionCommand("skip");
	skip.addActionListener(this);
	JRadioButton cheat = new JRadioButton("cheat off of the kid next to you");
	cheat.setActionCommand("cheat");
	cheat.addActionListener(this);
       	JRadioButton sleep = new JRadioButton("sleep through the exam");
	sleep.setActionCommand("sleepThrough");
	sleep.addActionListener(this);
	JRadioButton take = new JRadioButton("actually take the final exam");
	take.setActionCommand("takeIt");
	take.addActionListener(this);
	ButtonGroup finalsDayButtons = new ButtonGroup();
	finalsDayButtons.add(skip);
	finalsDayButtons.add(cheat);
	finalsDayButtons.add(sleep);
	finalsDayButtons.add(take);
	interact.add(skip);
	interact.add(cheat);
	interact.add(sleep);
	interact.add(take);
	interact.add(extraSpace);
	JButton b = new JButton("Submit");
	b.setActionCommand("finalsDayResponse");
	b.addActionListener(this);
	interact.add(b);
	
	skip.setFont(eventFont);
	cheat.setFont(eventFont);
	sleep.setFont(eventFont);
	take.setFont(eventFont);
	b.setFont(buttonFont);
    }

    /////////////////////////
    ////// conclusion ///////
    /////////////////////////
    public void preConclusion(){
	reset();
	autoUpdate();
      
	story.setText("<html>You've survived finals week! It's been a tough few days, but (hopefully) it'll all pay off when you see your final grades.<br><br><br><br> </html>");
	interact.add(story);
	
	JButton result = new JButton("See your results");
	result.setActionCommand("result");
	result.addActionListener(this);
	interact.add(extraSpace);
	interact.add(result);

	result.setFont(buttonFont);
	result.setHorizontalAlignment(JLabel.CENTER);
    }

    public void conclusion() {
	reset();
	autoUpdate();
	JLabel l = new JLabel();
	JLabel z = new JLabel();
	JLabel e = new JLabel();

	if (player.getHomework() == true){
	    player.setGrade(player.getGrade() + r.nextInt(10));
	}
	double g = player.getGrade();

	if (g >= 100) {
	    z = new JLabel("<html>After all your blood, sweat, and tears, you have earned an...</html>");
	    l = new JLabel("<html>A+</html>");
	    e = new JLabel("<html>Congratulations! You truly are a star student! With these grades, you're a shoo-in for Harvard!</html>");
	} else if (g >= 95) {
	    z = new JLabel("<html>After all your blood, sweat, and tears, you have earned an...</html>");
	    l = new JLabel("<html>A</html>");
	    e = new JLabel("<html>Congratulations! You truly are a star student! All that studying paid off!</html>");
	} else if (g >= 92) {
	    z = new JLabel("<html>After all your blood, sweat, and tears, you have earned an...</html>");
	    l = new JLabel("<html>A-</html>");
	    e = new JLabel("<html>Congratulations! You're a great student! All that studying paid off!</html>");
	} else if (g >= 88) {
	    z = new JLabel("<html>After all your blood, sweat, and tears, you have earned a...</html>");
	    l = new JLabel("<html>B+</html>");
	    e = new JLabel("<html>Nice job! While you may not be a 'top' student, you're still pretty darn good!</html>");
	} else if (g >= 85) {
	    z = new JLabel("<html>After all your blood, sweat, and tears, you have earned a...</html>");
	    l = new JLabel("<html>B</html>");
	    e = new JLabel("<html>Congratulations! You're strictly average! You're in the middle of the pack: not the top or the bottom, and that's good enough for you!</html>");
	} else if (g >= 82) {
	    z = new JLabel("<html>After all your blood, sweat, and tears, you have earned a...</html>");
	    l = new JLabel("<html>B-</html>");
	    e = new JLabel("<html>Nice job! You're strictly average! You're in the middle of the pack, and that's good enough for you!<br><br>As Joseph Heller once said, 'Some people are born mediocre, some people achieve mediocrity, and some people have mediocrity thrust upon them.' You are all of the above!</html>");
	} else if (g >= 78) {
	    z = new JLabel("<html>After all your blood, sweat, and tears, you have earned a...</html>");
	    l = new JLabel("<html>C+</html>");
	    e = new JLabel("<html>Nice! You're about average, and while that may not please your parents, it's good enough for you!<br><br>As Joseph Heller once said, 'Some people are born mediocre, some people achieve mediocrity, and some people have mediocrity thrust upon them.' You are all of the above!</html>");
	} else if (g >= 75) {
	    z = new JLabel("<html>After all your blood, sweat, and tears, you have earned a...</html>");
	    l = new JLabel("<html>C</html>");
	    e = new JLabel("<html>Congratulations! You're slightly below average, and while that may not please your parents, it's good enough for you!</html>");
	} else if (g >= 70) {
	    z = new JLabel("<html>After all your blood, sweat, and tears, you have earned a...</html>");
	    l = new JLabel("<html>C-</html>");
	    e = new JLabel("<html>Mediocrity isn't quite your thing, and not in a good way. Hey, at least you passed!</html>");
	} else if (g >= 65) {
	    z = new JLabel("<html>After all your blood, sweat, and tears, you have earned a...</html>");
	    l = new JLabel("<html>D</html>");
	    e = new JLabel("<html>Hey, you may be well below average in your grades, but you're above average in...well...oh, who are we kidding? At least you didn't fail!</html>");
	} else {
	    z = new JLabel("<html>After all your blood, sweat, and tears, you have earned an...</html>");
	    l = new JLabel("<html>F</html>");
	    e = new JLabel("<html>Well...we're all bad at something. Maybe you'll do better when you repeat these classes next year?</html>");
	}
	
	JButton again = new JButton("Try Again");
	again.setActionCommand("playAgain");
	again.addActionListener(this);

	JButton quit = new JButton("Quit");
	quit.setActionCommand("quit");
	quit.addActionListener(this);
	
	z.setFont(eventFont);
	l.setFont(resultFont);
	e.setFont(eventFont);
	again.setFont(buttonFont);
	quit.setFont(buttonFont);

	z.setHorizontalAlignment(JLabel.CENTER);
	l.setHorizontalAlignment(JLabel.CENTER);
	e.setHorizontalAlignment(JLabel.CENTER);
	again.setHorizontalAlignment(JButton.CENTER);
	quit.setHorizontalAlignment(JButton.CENTER);

	interact.add(z);
	interact.add(l);
	interact.add(e);
	interact.add(extraSpace);
	interact.add(again);
	interact.add(extraSpace);
	interact.add(quit);

    }

    /////////////////////////
    public void displayResponse(){
	player.checkStats();
	player.checkTime();
	autoUpdate();
	interact.add(q);
	JButton next = new JButton("Continue");
	next.setActionCommand("cont");
	next.addActionListener(this);
	interact.add(next);
	
	next.setFont(buttonFont);
    }
    /////////////////////////
    public void paint(Graphics g){
	super.paint(g);
	g.drawRect(130,45,100,10);
	g.drawRect(130,65,100,10);
	g.drawRect(130,85,100,10);
	g.fillRect(130,45,Student.getStress(),10);
	g.fillRect(130,65,Student.getKnow(),10);
	g.fillRect(130,85,Student.getEnergy(),10);

    }

    /////////////////////////
    ///  action listener  ///
    /////////////////////////
    public void actionPerformed(ActionEvent e){
	repaint();
	String action = e.getActionCommand();
	////choose difficulty////
	if (action.equals("Freshman")){
	    player = new Freshman();
	}else if (action.equals("Sophomore")){
	    player = new Sophomore();
	}else if (action.equals("Junior")){
	    player = new Junior();
	}else if (action.equals("Senior")){
	    player = new Senior();
	}
	if (action.equals("next")){
	    initializePlayerAndStats(llamo.getText());
	    startGame();	    
	}
	///////start game////////
	if (action.equals("begin")){
	    reset();
	    updateDay(days[dayi]);
	    //y = inSchool("first");
	    inSchool();
	}
	////////sick day/////////
	if (action.equals("stayHome")){
	    bstayHome = true;
	}else if (action.equals("goToSchool")){
	    bstayHome = false;
	}
	if (action.equals("sickDayResponse")){
	    if (bstayHome){
		s = "stay home";
	    }else{
		s = "";
	    }
	    q.setText(player.sickDay(s));
	    reset();
	    displayResponse();
	}
	/////////pop quiz////////
	if (action.equals("cheat")){
	    bcheat = true;
	}else if (action.equals("takeQuiz")){
	    bcheat = false;
	}
	if (action.equals("popQuizResponse")){
	    if (bcheat){
		s = "cheat";
	    }else{
		s = "";
	    }
	    q.setText(player.popQuiz(s));
	    reset();
	    displayResponse();
	}
	////broken escalator/////
	if (action.equals("climb")){
	    bclimb = true;
	}else if(action.equals("noClimb")){
	    bclimb = false;
	}
	if (action.equals("brokenEscalatorResponse")){
	    if (bclimb){
		s = "climb up the stairs";
	    }else{
		s = "";
	    }
	    q.setText(player.brokenEscalator(s));
	    reset();
	    displayResponse();
	}
	//////regular class//////
	if (action.equals("nap")){
	    inClass = 0;
	}else if (action.equals("passNotes")){
	    inClass = 1;
	}else if (action.equals("learn")){
	    inClass = 2;
	}
	if (action.equals("classTimeResponse")){
	    if (inClass == 0){
	        s = "sleep";
	    }else if (inClass == 1){
	        s = "pass notes";
	    }else{
	        s ="";
	    }
	    q.setText(player.goToClass(s));
	    reset();
	    displayResponse();
	}
	///normal after school///
	if (action.equals("studyNotes")){
	    afSchool = 0;
	} else if (action.equals("doHomework")){
	    afSchool = 1;
	} else if (action.equals("onFacebook")){
	    afSchool = 2;
	} else if (action.equals("goToSleep")){
	    afSchool = 3;
	}
	if (action.equals("afterSchoolResponse")){
	    if (afSchool == 0){
		s = "study";
	    } else if (afSchool == 1){
		s = "homework";
	    } else if (afSchool == 2){
		s = "facebook";
	    } else {
		s = "";
	    }
	    q.setText(player.afterSchoolTime(s));
	    reset();
	    displayResponse();
	}
	//////help a friend//////
	if (action.equals("helpFriend")){
	    bhelp = true;
	} else if (action.equals("dontHelp")){
	    bhelp = false;
	}
	if (action.equals("helpAFriendResponse")){
	    if (bhelp) {
		s = "yes";
	    } else {
		s = "";
	    }
	    q.setText(player.helpAFriend(s));
	    reset();
	    displayResponse();
	}
	///////finals day///////
	if (action.equals("skip")){
	    inFinal = 0;
	} else if (action.equals("cheat")){
	    inFinal = 1;
	} else if (action.equals("sleepThrough")) {
	    inFinal = 2;
	} else if (action.equals("takeIt")){
	    inFinal = 3;
	}
	if (action.equals("finalsDayResponse")){
	    if (inFinal == 0){
		s = "skip";
	    } else if (inFinal == 1){
		s = "cheat";
	    } else if (inFinal == 2){
		s = "sleep";
	    } else {
		s = "";
	    }
	    q.setText(player.doFinal(s));
	    reset();
	    displayResponse();
	}
	////////continue////////
	if (action.equals("cont")){
	    if (dayi != 4){
		if (player.time == 7){
		    morning();
		}else if (player.time >= 9 && player.time <= 13){
		    inSchool();
		}else if (player.time >= 15 || player.time <= 7){
		    afterSchool();
		}
	    } else if (dayi == 4 && player.time < 15) {
		finalsDay();
	    } else if (dayi == 4 && player.time >= 15) {
		preConclusion();
	    }
	}

	////////results/////////
	if (action.equals("result")){
	    conclusion();
	}

	if (action.equals("playAgain")) {
	    reset();
	    player = new Freshman();
	    stats.removeAll();
	    stats.revalidate();
	    window.repaint();
	    setupStatsPanel();
	    setupInteractPanel();
	    autoUpdate();
	}

	if (action.equals("quit")) {
	    System.exit(0);
	}
    }
    
}
