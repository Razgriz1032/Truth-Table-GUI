import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TruthGUI implements ActionListener, Runnable
{
	JFrame frame = null;
	JPanel main = null;
	JPanel tablePanel = null;
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
	int maxVars = 4;
	
	private void createGUI()  //sets up the frame when the program is run
	{
		frame = new JFrame("Multi-Variable Truth Table");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setJMenuBar(menubar);
		frame.add(main);
		frame.setPreferredSize(new Dimension(762, 300));
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
		//variableTable = new JTable(8, 2);
		//resultTable = new JTable(8, 10);
		//variableTable.setAutoResizeMode(0);
		//resultTable.setAutoResizeMode(0);
		//to actually create the tables, remove these and just 
		//use the generateTables() method
		
		generateTables();
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
		int columns = variables;
		int rows = (int)Math.pow(2, variables);
		
		//setup the variable table
		variableTable = new JTable(rows, columns);
		for (int c = columns - 1; c >= 0; c--)
			for (int r = 0; r < rows; r++)
				variableTable.setValueAt((r/(int)Math.pow(2, c))%2, r, c);
		
		//setup the result table
		resultTable = generateResultTable();
		
		//make sure columns are formatted correctly
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		variableTable.setDefaultRenderer(Object.class, centerRenderer);
		resultTable.setDefaultRenderer(Object.class, centerRenderer);
		
		//refresh tables
		variableTable.setRowSelectionAllowed(false);
		resultTable.setRowSelectionAllowed(false);
		//variableTable.setAutoResizeMode(0);
		//resultTable.setAutoResizeMode(0);
	}
	
	public JTable generateResultTable()
	{
		String[] columnNames = {"AND", "OR", "NOR", "XOR","NAND"};
		Object[][] rowData = null;
		
		// determines what column names and rowData we are using based on # of vars
		if (variables == 2)
		{			
			Object[][] data =
				{
						 {0, 0, 1, 0, 1},
						 {0, 1, 0, 1, 1},
						 {0, 1, 0, 1, 1},
						 {1, 1, 0, 0, 0}
				};
			rowData = data;
		}
		else if (variables == 3)
		{
			Object[][] data =
				{
						 {0, 0, 1, 0, 1},
						 {0, 1, 0, 1, 1},
						 {0, 1, 0, 1, 1},
						 {0, 1, 0, 0, 1},
						 {0, 1, 0, 1, 1},
						 {0, 1, 0, 0, 1},
						 {0, 1, 0, 0, 1},
						 {1, 1, 0, 0, 0}
				};
			rowData = data;
		}
		else if (variables == 4)
		{
			Object[][] data =
				{
						 {0, 0, 1, 0, 1},
						 {0, 1, 0, 1, 1},
						 {0, 1, 0, 1, 1},
						 {0, 1, 0, 0, 1},
						 {0, 1, 0, 1, 1},
						 {0, 1, 0, 0, 1},
						 {0, 1, 0, 0, 1},
						 {0, 1, 0, 0, 1},
						 {0, 1, 0, 1, 1},
						 {0, 1, 0, 1, 1},
						 {0, 1, 0, 1, 1},
						 {0, 1, 0, 0, 1},
						 {0, 1, 0, 1, 1},
						 {0, 1, 0, 0, 1},
						 {0, 1, 0, 0, 1},
						 {1, 1, 0, 0, 0}
				};
			rowData = data;
		}
		
		
		JTable table = new JTable(rowData, columnNames);
		return table;
	}

	public void actionPerformed(ActionEvent e)  //events for clicking items
	{
		Object source = e.getSource();
		
		if (source == addVar)
		{
			if (variables < maxVars)
			{
				variables++;
				generateTables();
			}
		}
		else if (source == removeVar)
		{
			if (variables > 0)
			{
				variables--;
				generateTables();
			}
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
