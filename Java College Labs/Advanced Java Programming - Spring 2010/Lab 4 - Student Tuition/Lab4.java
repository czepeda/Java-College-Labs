import java.sql.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;


public class Lab4 extends JFrame implements ActionListener
{
	Connection connection;
	Statement statement;
	ResultSet rs;
	Graduate[] student;
	int size = 0;
	int ArrayPtr = 0;
	Container pane = getContentPane();
	JLabel title = new JLabel("Student Tuition Report");
	JLabel stno = new JLabel("Student No.");
	JLabel stname = new JLabel("Name");
	JLabel stcredit = new JLabel("Credits");
	JLabel ststanding = new JLabel("Standing");
	JLabel tuition = new JLabel("Tuition");
	JTextField stnoField = new JTextField(6);
	JTextField stnameField = new JTextField(8);
	JTextField stcreditField = new JTextField(4);
	JTextField ststandingField = new JTextField(8);
	JTextField tuitionField = new JTextField(6);
	JButton show = new JButton("Show Students");
	JButton start = new JButton("<<");
	JButton prev = new JButton("<");
	JButton next = new JButton(">");
	JButton last = new JButton(">>");

	public Lab4()
	{
		setTitle("Academia College");
		setSize(600, 400);

			//Declarations
			pane.setLayout(null);
			//Title Label
			title.setFont(new Font("Dialog", Font.BOLD, 20));
			title.setSize(title.getPreferredSize());
			title.setLocation(190, 90);
			pane.add(title);
			//Student Number Label
			stno.setSize(stno.getPreferredSize());
			stno.setLocation(60, 160);
			pane.add(stno);
			//Name Label
			stname.setSize(stname.getPreferredSize());
			stname.setLocation(150, 160);
			getContentPane().add(stname);
			//Credits Label
			stcredit.setSize(stcredit.getPreferredSize() );
			stcredit.setLocation(265, 160);
			getContentPane().add(stcredit);
			//Standing Label
			ststanding.setSize(ststanding.getPreferredSize() );
			ststanding.setLocation(338, 160);
			getContentPane().add(ststanding);
			//Tuition Label
			tuition.setSize(tuition.getPreferredSize() );
			tuition.setLocation(450, 160);
			getContentPane().add(tuition);



		 	//Student Number Text Field
			stnoField.setSize(stnoField.getPreferredSize());
			stnoField.setLocation(60,180);
			getContentPane().add(stnoField);
			//Name Text Field
			stnameField.setSize(stnameField.getPreferredSize());
			stnameField.setLocation(150,180);
			getContentPane().add(stnameField);
			//Credits Text Field
			stcreditField.setSize(stcreditField.getPreferredSize());
			stcreditField.setLocation(265,180);
			getContentPane().add(stcreditField);
			//Standing Text Field
			ststandingField.setSize(ststandingField.getPreferredSize());
			ststandingField.setLocation(338,180);
			getContentPane().add(ststandingField);
			//Tuition Text Field
			tuitionField.setSize(tuitionField.getPreferredSize());
			tuitionField.setLocation(450,180);
			pane.add(tuitionField);

			//Buttons
			//Show Button
			show.setSize(show.getPreferredSize());
			show.setLocation(230, 250);
			pane.add(show);
			show.addActionListener(this);
			//Start Button
			start.setSize(start.getPreferredSize());
			start.setLocation(180, 300);
			getContentPane().add(start);
			start.addActionListener(this);
			start.setEnabled(false);
			//Previous Button
			prev.setSize(prev.getPreferredSize());
			prev.setLocation(245, 300);
			getContentPane().add(prev);
			prev.addActionListener(this);
			prev.setEnabled(false);
			//Next Button
			next.setSize(next.getPreferredSize());
			next.setLocation(300, 300);
			getContentPane().add(next);
			next.addActionListener(this);
			next.setEnabled(false);
			//Last Button
			last.setSize(last.getPreferredSize());
			last.setLocation(357, 300);
			getContentPane().add(last);
			last.addActionListener(this);
			last.setEnabled(false);

			setVisible(true);
	        setDefaultCloseOperation(EXIT_ON_CLOSE);

try
{
	loadDriver();
	makeConnection();
	buildStatement();
	executeQuery();
}
catch (Exception e)
{
	System.out.println("error");
}
}

public void loadDriver() throws ClassNotFoundException
{
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
}
public void makeConnection() throws SQLException
{
	connection = DriverManager.getConnection("jdbc:odbc:students");
}
public void buildStatement() throws SQLException
{
statement= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
}
public void executeQuery() throws SQLException
{
	boolean foundResults;
	foundResults = statement.execute("SELECT * FROM Stable");
	if(foundResults)
	{
		rs = statement.getResultSet();
		rs.last();
		size = rs.getRow();
		student = new Graduate[size];
	}
}

public void actionPerformed (ActionEvent e)
{
	if(e.getActionCommand().equals("Show Students"))
	{
		try
		{
			//Event Handler
			populateArray();
			start.setEnabled(true);
			last.setEnabled(true);
		}
		catch(Exception err)
		{
		System.out.println("error");
	    }
	ArrayPtr = 0;
	displaycurrentrecord(ArrayPtr);
	UpdateButtons();
	show.setEnabled(false);
}




//Action Performed for Buttons
if(e.getActionCommand().equals("<<"))
{
	ArrayPtr = 0;
	displaycurrentrecord(ArrayPtr);
	UpdateButtons();
}
if(e.getActionCommand().equals(">>"))
{
	ArrayPtr = size - 1;
	displaycurrentrecord(ArrayPtr);
	UpdateButtons();
}
if(e.getActionCommand().equals("<"))
{
	ArrayPtr = ArrayPtr - 1;
	displaycurrentrecord(ArrayPtr);
	UpdateButtons();
}
if(e.getActionCommand().equals(">"))
{
	ArrayPtr = ArrayPtr + 1;
	displaycurrentrecord(ArrayPtr);
	UpdateButtons();
}
}

public void populateArray() throws SQLException
{
	String standing;
	rs.beforeFirst();
	while(rs.next())
	{
		standing = rs.getString("Standing");
		if((standing.equals("Graduate")))
		{
			Graduate Gstudent = new Graduate();
			Gstudent.ststanding = "Graduate";
			Gstudent.stno = rs.getString("stno");
			Gstudent.stname = rs.getString("stname");
			Gstudent.stcredits = rs.getInt("stcredits");
			student[ArrayPtr] = Gstudent;
			ArrayPtr = ArrayPtr + 1;
		}
		else
		{
			//Undergraduate Object
			Undergraduate Ustudent = new Undergraduate();
			Ustudent.ststanding = "Undergraduate";
			Ustudent.stno = rs.getString("stno");
			Ustudent.stname = rs.getString("stname");
			Ustudent.stcredits = rs.getInt("stcredits");
			student[ArrayPtr] = Ustudent;
			ArrayPtr = ArrayPtr + 1;
		}
	}
}

public void displaycurrentrecord(int ptr)
{
	stnoField.setText(student[ptr].stno);
	stnameField.setText(student[ptr].stname);
	stcreditField.setText(String.valueOf(student[ptr].stcredits));
	ststandingField.setText(student[ptr].ststanding);
	tuitionField.setText(String.format("$%,.2f",student[ptr].tuition()));
}
public void UpdateButtons()
{
	if(ArrayPtr == 0)
	prev.setEnabled(false);
	else
	prev.setEnabled(true);
	if(ArrayPtr == size-1)
	next.setEnabled(false);
	else
	next.setEnabled(true);
}

public static void main(String [] args)
	{
	Lab4 frame = new Lab4();
	}
}











