import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class GUI extends JFrame implements ActionListener{
    
    private Student player;
    private Container window;
    private JSplitPane pane;
    private JPanel stats, interact;
    private JLabel stress, knowledge, energy, time, story, q;
    private JTextField llamo;
    private JButton b;

    public GUI(){
	player = new Freshman();

	this.setTitle("Stuyvesant Finals Week Simulator");
	this.setSize(700, 400);
	this.setLocation(100, 100);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	window = this.getContentPane();
	window.setLayout(new BorderLayout());
	
	setupStatsPanel();
	setupInteractPanel();
	
	Dimension minSize = new Dimension(100,100);
	stats.setMinimumSize(minSize);
	interact.setMinimumSize(minSize);

	pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, stats, interact);
	pane.setOneTouchExpandable(true);
	pane.setResizeWeight(0.5);
	
	window.add(pane, BorderLayout.CENTER);
    }

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
	time.setText("time: " + t);
    }

    public void setupStatsPanel(){
	stats = new JPanel();
	BoxLayout b = new BoxLayout(stats, BoxLayout.Y_AXIS);
	stats.setLayout(b);
	stats.setBorder(BorderFactory.createCompoundBorder(
	  BorderFactory.createTitledBorder("Stats"),
	  BorderFactory.createRaisedBevelBorder()));

	stress = new JLabel("stress: ");
	stress.setBorder(BorderFactory.createLoweredBevelBorder());
	stats.add(stress);
	knowledge = new JLabel("knowledge: ");
	knowledge.setBorder(BorderFactory.createLoweredBevelBorder());
	stats.add(knowledge);
	energy = new JLabel("energy: ");	
	energy.setBorder(BorderFactory.createLoweredBevelBorder());
	stats.add(energy);
	time = new JLabel("time: ");
	time.setBorder(BorderFactory.createLoweredBevelBorder());
	stats.add(time);
    }
    public void setupInteractPanel(){
	interact = new JPanel();
	BoxLayout b = new BoxLayout(interact, BoxLayout.Y_AXIS);
	interact.setLayout(b);
	interact.setBorder(BorderFactory.createCompoundBorder(
	  BorderFactory.createTitledBorder("Story"),
	  BorderFactory.createRaisedBevelBorder()));

	JLabel intro = new JLabel("<html><center>Welcome to the Stuyvesant Finals Week Simulator!<br>Please enter your name and choose a difficulty:</center></html>");
	llamo = new JTextField("Harry Potter");
	llamo.setSize(20, 10);
	interact.add(intro);
	interact.add(llamo);
	addDifficultyOptions();
	JButton next = new JButton("Next");
	begin.setActionCommand("next");
	begin.addActionListener(this);
	interact.add(next);
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
    }

    public void initializePlayerAndStats(String name){
	player.setName(name);
	updateStress(player.getStress());
	updateKnowledge(player.getKnow());
	updateEnergy(player.getEnergy());
	updateTime(player.time);
    }

    public void startGame(){
	interact.removeAll();
	story = new JLabel("<html><left>Hi, " + player + "! So you're a " + player.getLevel() + " at Stuyvesant, and it's finally time for the week everyone dreads...<br>Will you die in 5 days, or emerge victorious? It all depends on your choices...</left></html>");
	interact.add(story);
	b = new JButton("Begin");
	b.setActionCommand("begin");
	b.addActionListener(this);
	interact.add(b);
	interact.revalidate();
	window.repaint();
    }
    
    public void day(String d){
	interact.removeAll();

	story.setText("Today is " + d);
	interact.add(story);
	interact.add(q);
	classTime();
	

	interact.revalidate();
	window.repaint();
    }

    public void classTime(){
	int chance = 100 - getStress() + getEnergy() + getKnow();
	chance /= 3;
	if (r.nextInt(100) < chance){
	    int e = r.nextInt(2);
	    switch (e) {
	    case 0: q.setText(player.popQuiz(popQuizResponse()));
		break;
	    case 1: q.setText(player.fireDrill());
		break;
	    case 2: q.setText(player.brokenEscalator(brokenEscalatorResponse()));
		break;
	    }
	} else {
	    player.time += 2;
	    q.setText(player.goToClass(classTimeResponse()));
	}
    }
    
    public String popQuizResponse(){
	
    }
    public String brokenEscalatorResponse(){

    }
    public String classTimeResponse(){
		
    }

    public void actionPerformed(ActionEvent e){
	String action = e.getActionCommand();
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
	if (action.equals("begin")){
	    String[] days = new String[4];
	    days[0] = "Monday";
	    days[1] = "Tuesday";
	    days[2] = "Wednesday";
	    days[3] = "Thursday";
	    
	    day(days[0]);		
	    //it will be a for loop, need to call day for each day in the array
	}
    }
    
}
