import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TruthGUI implements ActionListener, Runnable
{
	JFrame frame = null;
	JPanel main = null;
	JScrollPane variablePane = null;
	JScrollPane resultPane = null;
	JTable variableTable = null;  //columns will be "A", "B", etc.
	JTable resultTable = null;  //columns will be "A or B", "A and B", "A implies B", etc.
	JMenuBar menubar = null;
	JMenu menu = null;
	JMenuItem addVar = null;
	JMenuItem removeVar = null;
	Font text = null;
	
	int variables = 2;
	
	private void createGUI()  //sets up the frame when the program is run
	{
		frame = new JFrame("Multi-Variable Truth Table");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setJMenuBar(menubar);
		frame.add(main);
		frame.setPreferredSize(new Dimension(750, 500));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	public TruthGUI()  //primary constructor
	{
		text = new Font("SansSerif",Font.PLAIN, 18);
		main = new JPanel(new GridLayout(1, 2));
		
		//these lines are just initializers for testing
		variableTable = new JTable(8, 2);
		resultTable = new JTable(8, 10);
		variableTable.setAutoResizeMode(0);
		resultTable.setAutoResizeMode(0);
		//to actually create the tables, remove these and just 
		//use the generateTables() method
		
		variablePane = new JScrollPane(variableTable);
		resultPane = new JScrollPane(resultTable);
		menubar = new JMenuBar();
		menu = new JMenu("Menu");
		menu.setFont(text);
		addVar = new JMenuItem("Add Variable");
		addVar.setFont(text);
		addVar.addActionListener(this);
		removeVar = new JMenuItem("Remove Variable");
		removeVar.setFont(text);
		removeVar.addActionListener(this);
		
		menubar.add(menu);
			menu.add(addVar);
			menu.add(removeVar);
		
		main.add(variablePane);
		main.add(resultPane);
	}
	
	private void generateTables()
	{
		/*
		 * this method should be used to generate the "variables"
		 * and "results" tables and update the display to show them
		 */
		variableTable.setAutoResizeMode(0);
		resultTable.setAutoResizeMode(0);
	}
	
	public void actionPerformed(ActionEvent e)  //events for clicking items
	{
		Object source = e.getSource();
		
		if (source == addVar)
		{
			variables++;
			generateTables();
		}
		else if (source == removeVar)
		{
			variables--;
			generateTables();
		}
	}
	
	public void run()  //called when the program gets run
	{
		createGUI();
	}
	
	public static void main(String[] args)  //runs the program
	{
		TruthGUI ttgui = new TruthGUI();
		EventQueue.invokeLater(ttgui);
	}
}