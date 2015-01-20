import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
abstract class Student{

    /////////////////////////
    private String name;
    private static int energy, stress, knowledge;
    private double grade;
    public static int time;
    private static boolean homework;
    Random r = new Random();
    /////////////////////////
    public Student(String n, int e, int s, int k){
	setName(n);
	setEnergy(e);
	setStress(s);
	setKnow(k);
	setGrade(80);
	time = 9;
    }
    public Student(String n){
	this(n, 80, 50, 30);
    }
    public Student(){
	this("Student", 80, 50, 30);
    }

    /////////////////////////////
    /// variable set and gets ///
    /////////////////////////////
    public void setName(String n){
	name = n;
    }
    public String getName(){
	return name;
    }
    public static void setEnergy(int e){
	energy = e;
    }
    public static int getEnergy(){
	return energy;
    }
    public static void setStress(int s){
	stress = s;
    }
    public static int getStress(){
	return stress;
    }
    public static void setKnow(int k){
	knowledge = k;
    }
    public static int getKnow(){
	return knowledge;
    }
    public void setGrade(double g){
	grade = g;
    }
    public double getGrade(){
	return grade;
    }
    public static void setHomework(boolean h){
	homework = h;
    }
    public static boolean getHomework(){
	return homework;
    }

    abstract String getLevel();

    public String toString(){
	return this.getName();
    }

    /////////////////////////
    public void sleep(int hrs){
        setEnergy(getEnergy() + hrs * 10);
    }

    public void study(int hrs){
	setKnow(getKnow() + hrs*5);
	setStress(getStress() + hrs*3);
	setEnergy(getEnergy() - hrs*5);
	time += hrs;
    }

    public void socialize(int hrs){
	setStress(getStress() - hrs*5);
	setEnergy(getEnergy() - hrs*4);
	time += hrs;
    }

    /////////////////////////
    ///    check stats    ///
    /////////////////////////
    public void checkStats(){
	if (getKnow() > 100){
	    setKnow(100);
	} else if (getKnow() < 0) {
	    setKnow(0);
	}
	if (getStress() > 100){
	    setStress(100);
	} else if (getStress() < 0) {
	    setStress(0);
	}
	if (getEnergy() > 100){
	    setEnergy(100);
	} else if (getEnergy() < 0) {
	    setEnergy(0);
	}
    }

    public void checkTime(){
	if (time > 24){
	    time = 1;
	}
    }

    /////////////////////////
    public int calculateChancePos(){
	int chance = getKnow() + 100 - getStress() + getEnergy();
	return chance / 3;
    }
    public int calculateChanceNeg(){
	int chance = 100 - getKnow() + getStress() + 100 - getEnergy();
	return chance / 3;
    }

    /////////////////////////
    ///   morning stuff   ///
    /////////////////////////
    public String sickDay(String ans){
	if (ans == "stay home"){
	    time+=24;
	    setGrade(getGrade() - 10);
	    setEnergy(100);
	    setStress(getStress() - 10);
	    return "<html>Instead of going to school, you lay in bed and marathoned all 6 Lord of the Rings movies. <br>24 hours well spent.</html>";
	}else{
	    //there should still be a chance that you're too sick to go to school
	    return "<html>The sacrifices you make for your education are truly heroic. <br>On the other hand, none of your classmates will sit within 10 feet of you.</html>";
	}
    }

    public String coffeeSpill(){
	setKnow(getKnow() - 20);
	time += 2;
	return "<html>You got a free coffee from Starbucks by pretending it was your birthday. You congratulate yourself on your cunning, but while you're sneaking the drink into school, it spills all over your notes! </html>";
    }
    
    public String eatenHomework(){
	setGrade(getGrade() - 5);
	time += 2;
	return "<html>Oh snap! <br>Just as you're about to run out the door, you realize that your piranha ate your homework last night! Your homework grade is going down the toilet... along with that piranha.</html>";
    }
    
     
    public String subwayDelay(){
	setGrade(getGrade() - 5);
	time += 4;
	return "<html>A dead rat on the subway tracks caused a major delay and you end up missing your first class.<br> Thanks, MTA...</html>";	
    }

    /////////////////////////
    ///  in school stuff  ///
    /////////////////////////
    public String fireDrill(){
	time += 2;
	setStress(getStress() - 5);
	setEnergy(getEnergy() - 5);
	return "<html>FIIRREEEEE (drill)! <br>The microwave in the chemistry department office goes up in flames, so you miss an entire period of class time.</html>";
    }

    public String brokenEscalator(String ans){
	time += 2;
	if (ans == "climb up the stairs"){
	    setEnergy(getEnergy() - 15);
	    return "<html>It's a physical struggle that makes you feel like one of those guys who just free-climbed El Capitan, but you bear the pain for the sake of learning.<br> How inspiring.</html>";
	} else {
	    setGrade(getGrade() - 8);
	    setStress(getStress() - 10);
	    return "<html>'Sweating' is not in your lexicon... <br>The stairs win this round, you're not even going to try getting to class.</html>";
	}
    }

    public String popQuiz(String ans){
	time += 2;
	if (ans == "cheat"){
	    setEnergy(getEnergy() - 10);
	    return "<html>You decide to cheat...<br>" + cheat(false) + "</html>";
	} else {
	    int score = calculateChancePos();
	    score += r.nextInt(25);
	    setGrade(getGrade() + score / 10);
	    setStress(getStress() + 5);
	    setEnergy(getEnergy() - 5);
	    return "<html>You take the pop quiz...<br>And score a " + score + ".</html>";
	}
    }
    
    public String cheat(boolean f){
	int chance = calculateChanceNeg();
	if (r.nextInt(100) < chance){
	    if (f) {
		setGrade(getGrade() + (100 * (0.25 / 3) ));
	    } else {
		setGrade(getGrade() + (100 - getKnow())/2);
		setStress(getStress() - 10);
	    }
	    return "You got away with it, you lucky duck...";	    
	} else if (f) {
	    setGrade(getGrade() * 0.75);
	    return "<html>Aw, shucks! You were caught. Your teacher gives you a zero on the final and you get a lecture about academic dishonesty. Again.</html>";
	}else {
	    setGrade(getGrade() - 15);
	    setStress(getStress() + 10);
	    return "D'oh! You were caught! <br>Your teacher gives you a zero on the quiz and lowered your grade by 10 points.";
	}
    }

    public String goToClass(String response){
	time += 2;
	if (response == "sleep"){
	    setGrade(getGrade() - 8);
	    setEnergy(getEnergy() + 20);
	    setStress(getStress() - 5);
	    return "<html>You slept like a baby for two hours and learned nothing.<br>Way to go, Sleeping Beauty.</html>";
	} else if (response == "pass notes"){
	    setGrade(getGrade() - 7);
	    setStress(getStress() - 10);
	    setEnergy(getEnergy() - 5);
	    return "<html>You passed notes all period. You didn't learn anything useful, but at least you're up to date with the latest season of Keeping Up With the Kardashians.</html>";
	} else {
	    setEnergy(getEnergy() - 10);
	    setKnow(getKnow() + 15);
	    setStress(getStress() + 5);
	    return "<html>You sat through class and learned like a good little student. <br>Hooray!</html> ";
	}
    }

    /////////////////////////
    ///after school stuff ///
    /////////////////////////
    public String afterSchoolTime(String response){
	if (response == "study"){
	    study(2);
	    return "<html>You studied your class materials, ignoring the temptation of your Wii and iPhone. Impressive.</html>";
	} else if (response == "homework"){
	    doHomework();
	    return "<html>You completed your homework. Even though your teacher won't check it, you're glad you did it. It feels nice to be a good student once in a while.</html>"; 
	} else if (response == "facebook"){
	    socialize(2);
	    return "<html>You surfed Facebook for a while, Facebook-stalking random aquaintances. Dang it! You accidentally liked a post from three years ago! Unlike, unlike, unlike!</html>";
	} else {
	    if (time > 6) {
		sleep(24-time);
	    } else {
		sleep(7-time);
	    }
	    time = 7;
	    return "<html>You decided to turn in for the night and hit the hay. Good for you!</html>";
	}
    }
   
    public String sing(){
	if (getLevel().equals("Freshman") || getLevel().equals("Sophomore")){
	    return "SophFrosh";
	} else {
	    return getLevel();
	}
    }

    public String helpAFriend(String ans){
	if (ans == "yes"){
	    setStress(getStress() - 15);
	    setEnergy(getEnergy() - 10);
	    time += 2;
	    return "<html>You decided to act like a good friend and help. Go " + sing() + " SING!</html>";
	} else {
	    return "<html>You ditched your friend and went home. Some friend you are...</html>";
	}
    }

    public void doHomework(){
	setHomework(true);
	setStress(getStress() + 7);
	time += 2;
    }

    ////////////////////////
    /// finals day stuff ///
    ////////////////////////
    public String doFinal(String response){
	time += 2;
	if (time == 9){
	    setGrade(getGrade() * 0.75);
	}
	if (response == "skip") {
	    return "<html>You skipped the final exam. All of that studying, wasted!</html>";
	} else if (response == "cheat") {
	    return cheat(true);
	} else if (response == "sleep") {
	    setEnergy(getEnergy() + 20);
	    return "<html>You slept through the final exam. At least you got some z's before you get your f. </html>";
	} else {
	    double g = calculateChancePos() + r.nextInt(25); //extra (up to) 25 pts b/c scoring can be a bit low...
	    String ans = "<html>You took your final exam. Three #2 pencils and two hours later, you score a " + g + ".</html>";
	    double gr = g * (0.25 / 3.0);
	    setGrade(getGrade() + gr);
	    return ans;
	}
    }

}
