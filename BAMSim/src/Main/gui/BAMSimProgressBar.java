package Main.gui;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.beans.*;
import java.util.Random;
 
public class BAMSimProgressBar extends JFrame {
 
    private JProgressBar progressBar;
    private JButton cancelButton;
    private JTextArea taskOutput;
 
    public BAMSimProgressBar() {

 
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setPreferredSize(new Dimension (800, 50));
 
        //Call setStringPainted now so that the progress bar height
        //stays the same whether or not the string is shown.
        progressBar.setStringPainted(true); 
        
        //super(new BorderLayout());
        
        //Create the demo's UI.
    	cancelButton = new JButton("Cancel");
       // startButton.setActionCommand("start");
        //startButton.addActionListener(this);
        
    	cancelButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
 
        taskOutput = new JTextArea(50, 100);
        taskOutput.setMargin(new Insets(5,5,5,5));
        taskOutput.setEditable(false);
 
        JPanel panel = new JPanel();

        panel.add(progressBar);
        panel.add(cancelButton);
 
        add(panel, BorderLayout.PAGE_START);
        add(new JScrollPane(taskOutput), BorderLayout.CENTER);
       // setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        progressBar.setIndeterminate(false);
        /*progressBar.setValue(10);
        taskOutput.append(String.format(
                    "Completed %d%% of task.\n", 10));*/
        

    }
    
    public void setProgress(int value){
		progressBar.setValue(value);
	   // taskOutput.append(String.format(
	   //         "Completed %d%% of task.\n", progressBar.getValue()));
    }
    public void setIncrementProgress(int increment){
		progressBar.setValue(progressBar.getValue()+increment);
	    taskOutput.append(String.format(
	            "Completed %d%% of task.\n", progressBar.getValue()));
    }
    public void setStatus(String text){
    
    	taskOutput.append(text+"\n");
   }
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
		BAMSimProgressBar pb =new BAMSimProgressBar();
    }
}