/**
 * Author: Lon Smith, Ph.D.
 * Description: This is the framework for the database program. Additional requirements and functionality
 *    are to be built by you and your group.
 */

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.List;

public class EmployeeSearchFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDatabase;
	private JList<String> lstDepartment;
	private DefaultListModel<String> department = new DefaultListModel<String>();
	private JList<String> lstProject;
	private DefaultListModel<String> project = new DefaultListModel<String>();
	private JTextArea textAreaEmployee;
	private JCheckBox chckbxNotDept;
	private JCheckBox chckbxNotProject;
	private DatabaseConnection db = new DatabaseConnection();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeSearchFrame frame = new EmployeeSearchFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EmployeeSearchFrame() {
		setTitle("Employee Search");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Database:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel.setBounds(21, 23, 59, 14);
		contentPane.add(lblNewLabel);
		
		txtDatabase = new JTextField();
		txtDatabase.setBounds(90, 20, 193, 20);
		contentPane.add(txtDatabase);
		txtDatabase.setColumns(10);
		
		JButton btnDBFill = new JButton("Fill");
		/**
		 * The btnDBFill should fill the department and project JList with the 
		 * departments and projects from your entered database name.
		 */
		btnDBFill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillLists();
			}
		});
		
		btnDBFill.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnDBFill.setBounds(307, 19, 68, 23);
		contentPane.add(btnDBFill);
		
		JLabel lblDepartment = new JLabel("Department");
		lblDepartment.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblDepartment.setBounds(52, 63, 89, 14);
		contentPane.add(lblDepartment);
		
		JLabel lblProject = new JLabel("Project");
		lblProject.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblProject.setBounds(255, 63, 47, 14);
		contentPane.add(lblProject);
		
		lstProject = new JList<String>(new DefaultListModel<String>());
		lstProject.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lstProject.setModel(project);
		lstProject.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		JScrollPane scrollProject = new JScrollPane(lstProject);
		scrollProject.setBounds(225, 84, 150, 80);
		contentPane.add(scrollProject);
		
		chckbxNotDept = new JCheckBox("Not");
		chckbxNotDept.setBounds(71, 170, 59, 23);
		contentPane.add(chckbxNotDept);
		
		chckbxNotProject = new JCheckBox("Not");
		chckbxNotProject.setBounds(270, 170, 59, 23);
		contentPane.add(chckbxNotProject);
		
		lstDepartment = new JList<String>(new DefaultListModel<String>());
		lstDepartment.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lstDepartment.setModel(department);
		lstDepartment.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		JScrollPane scrollDepartment = new JScrollPane(lstDepartment);
		scrollDepartment.setBounds(36, 84, 172, 80);
		contentPane.add(scrollDepartment);
		
		JLabel lblEmployee = new JLabel("Employee");
		lblEmployee.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblEmployee.setBounds(52, 205, 89, 14);
		contentPane.add(lblEmployee);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchEmployees();
			}
		});
		btnSearch.setBounds(80, 345, 89, 23);
		contentPane.add(btnSearch);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textAreaEmployee.setText("");
				lstDepartment.clearSelection();
				lstProject.clearSelection();
				chckbxNotDept.setSelected(false);
				chckbxNotProject.setSelected(false);
			}
		});
		btnClear.setBounds(236, 345, 89, 23);
		contentPane.add(btnClear);
		
		textAreaEmployee = new JTextArea();
		textAreaEmployee.setEditable(false);
		JScrollPane scrollEmployee = new JScrollPane(textAreaEmployee);
		scrollEmployee.setBounds(36, 223, 339, 110);
		contentPane.add(scrollEmployee);
	}

	/**
	 * Resets the department and project lists and loads them again from the
	 * database typed in the text box. If the database can't be opened we show
	 * a pop-up and leave both lists empty.
	 */
	private void fillLists() {
		String name = txtDatabase.getText().trim();

		department.clear();
		project.clear();

		try {
			db.open(name);
			List<String> depts = db.getDepartments();
			List<String> prjs = db.getProjects();
			for (String d : depts) {
				department.addElement(d);
			}
			for (String p : prjs) {
				project.addElement(p);
			}
		} catch (SQLException ex) {
			db.close();
			department.clear();
			project.clear();
			JOptionPane.showMessageDialog(this,
					"The database \"" + name + "\" could not be opened.\n\n" + ex.getMessage(),
					"Database Error", JOptionPane.ERROR_MESSAGE);
		}
	}

/**
 * Gets the selected departments/projects and the Not checkboxes,
 * runs the query and shows the employee names in the text area.
 */

private void searchEmployees() {
    if (!db.isOpen()) {
        JOptionPane.showMessageDialog(this,
                "Load a database with the Fill button first.",
                "No Database", JOptionPane.WARNING_MESSAGE);
        return;
    }

    List<String> depts = lstDepartment.getSelectedValuesList();
    List<String> prjs = lstProject.getSelectedValuesList();

    if (depts.isEmpty() && prjs.isEmpty()) {
        JOptionPane.showMessageDialog(this,
                "Select at least one department or project.",
                "Nothing Selected", JOptionPane.WARNING_MESSAGE);
        return;
    }

    try {
        List<String> names = EmployeeQuery.search(db.getConnection(),
                depts, chckbxNotDept.isSelected(),
                prjs, chckbxNotProject.isSelected());

        if (names.isEmpty()) {
            textAreaEmployee.setText("No employees found.");
        } else {
            textAreaEmployee.setText(String.join("\n", names));
        }

        textAreaEmployee.setCaretPosition(0);

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this,
                "Search failed.\n\n" + ex.getMessage(),
                "Database Error", JOptionPane.ERROR_MESSAGE);
    }
}


}
