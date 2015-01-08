import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class GUI extends JFrame implements ActionListener{
    
    private Student player;
    private Container window;
    private JPanel pane1, pane2, pane3, pane4;
    private JLabel stress, knowledge, energy;

    public GUI(){
	this.setTitle("Stuyvesant Finals Week Simulator");
	this.setSize(800, 800);
	this.setLocation(100, 100);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	window = this.getContentPane();
	window.setLayout(new GridBagLayout());

	pane1 = new JPanel(new BorderLayout());
	pane1.setBorder(BorderFactory.createLoweredBevelBorder());
	pane2 = new JPanel(new BorderLayout());
	pane2.setBorder(BorderFactory.createLoweredBevelBorder());
	pane3 = new JPanel(new BorderLayout());
	pane3.setBorder(BorderFactory.createLoweredBevelBorder());

	//window.add(pane1);
	//window.add(pane2);
	//window.add(pane3);
	//window.add(pane4);
    }

    public void initialize(Student s){
	player = s;
	stress = new JLabel("stress: " + player.getStress());
	knowledge = new JLabel("knowledge: " + player.getKnow());
	energy = new JLabel("energy: " + player.getEnergy());
	pane1.add(stress);
	pane2.add(knowledge);
	pane3.add(energy);
	window.add(pane1);
	window.add(pane2);
	window.add(pane3);
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

    public void actionPerformed(ActionEvent e){
	String action = e.getActionCommand();
    }

}
