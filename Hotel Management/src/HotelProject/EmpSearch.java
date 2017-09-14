package HotelProject;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class EmpSearch {
    private JFrame f;
    private JLabel empId, empName, fName, permanentAdd, title, contactNo, proof, password;
    private JTextField empIdText, proofCombo, empNameText, fNameText, permanentAddText, passwordText, contactText;
    private JButton submit, back;
    static Connection conn = null;
    String emp_Id;

    EmpSearch(String s) {
        emp_Id = s;
        //frame
        f = new JFrame();
        f.setContentPane(new JLabel(new ImageIcon("F:\\SagarProject\\src\\HotelProject\\bgimage1.jpg")));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        f.setBounds(0, 0, screenSize.width, screenSize.height);
        f.setResizable(false);
        f.setTitle("GRAPHIC ERA HOTEL AND RESTAURANT");
        f.setLayout(null);
        f.setVisible(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        conn = SQLITE.dbconnector();
        //Labels
        title = new JLabel("Employee Details");
        title.setFont(new Font("Arial", Font.BOLD, 50));
        title.setForeground(Color.cyan);
        title.setBounds(200, 100, 500, 50);

        //employee details lABEL
        Font labelFont = new Font("Arial", Font.ITALIC, 30);
        empId = new JLabel("Emp Id");
        empId.setBounds(200, 180, 190, 50);
        empId.setForeground(Color.WHITE);
        empId.setFont(labelFont);
        empName = new JLabel("Emp Name");
        empName.setBounds(200, 250, 190, 50);
        empName.setForeground(Color.WHITE);
        empName.setFont(labelFont);
        fName = new JLabel("Father Name");
        fName.setBounds(200, 320, 190, 50);
        fName.setForeground(Color.WHITE);
        fName.setFont(labelFont);
        permanentAdd = new JLabel("Permanent Address");
        permanentAdd.setBounds(200, 390, 300, 50);
        permanentAdd.setForeground(Color.WHITE);
        permanentAdd.setFont(labelFont);
        contactNo = new JLabel("Contact NO.");
        contactNo.setBounds(200, 460, 190, 50);
        contactNo.setForeground(Color.WHITE);
        contactNo.setFont(labelFont);
        proof = new JLabel("Proof");
        proof.setBounds(200, 530, 190, 50);
        proof.setForeground(Color.WHITE);
        proof.setFont(labelFont);
        password = new JLabel("Password");
        password.setBounds(200, 600, 190, 50);
        password.setForeground(Color.WHITE);
        password.setFont(labelFont);


        //employee details Textfield
        empIdText = new JTextField();
        empIdText.setBounds(500, 190, 150, 30);
        empIdText.setEditable(false);
        empNameText = new JTextField();
        empNameText.setBounds(500, 260, 150, 30);
        empNameText.setEditable(false);
        fNameText = new JTextField();
        fNameText.setBounds(500, 330, 150, 30);
        fNameText.setEditable(false);
        permanentAddText = new JTextField();
        permanentAddText.setBounds(500, 400, 150, 30);
        permanentAddText.setEditable(false);
        contactText = new JTextField();
        contactText.setBounds(500, 470, 150, 30);
        contactText.setEditable(false);
        passwordText = new JTextField();
        passwordText.setBounds(500, 610, 150, 30);
        passwordText.setEditable(false);

        //proof Combo button
        proofCombo = new JTextField();
        proofCombo.setBounds(500, 540, 150, 30);
        proofCombo.setEditable(false);

        //submit Button and back
        submit = new JButton("Ok");
        submit.setBounds(200, 670, 150, 45);
        submit.setFont(new Font("Arial", Font.BOLD, 25));
        back = new JButton();
        back.setBounds(120, 50, 90, 50);
        ImageIcon backimg = new ImageIcon("F:\\SagarProject\\src\\HotelProject\\back.png");
        back.setIcon(backimg);

        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    conn.close();
                } catch (Exception e) {

                }
                new AdminAdd();
                f.setVisible(false);

            }
        });

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    conn.close();
                } catch (Exception e) {

                }
                new AdminAdd();
                f.setVisible(false);

            }
        });


        try{
            String query="select * from Emp where emp_id=?";
            PreparedStatement pst=conn.prepareStatement(query);
            pst.setString(1,emp_Id);
            ResultSet rst=pst.executeQuery();
            if(rst.next()){
                empIdText.setText(rst.getString("emp_id"));
                empNameText.setText(rst.getString("emp_name"));
                fNameText.setText(rst.getString("father_name"));
                permanentAddText.setText(rst.getString("permanent_address"));
                contactText.setText(rst.getString("contact_No"));
                proofCombo.setText(rst.getString("proof"));
                passwordText.setText(rst.getString("password"));
            }

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage(),"error",JOptionPane.ERROR_MESSAGE);
        }
        //add
        f.add(title);
        f.add(empId);
        f.add(empName);
        f.add(fName);
        f.add(permanentAdd);
        f.add(proof);
        f.add(contactNo);
        f.add(password);
        f.add(empIdText);
        f.add(empNameText);
        f.add(fNameText);
        f.add(permanentAddText);
        f.add(passwordText);
        f.add(contactText);
        f.add(proofCombo);
        f.add(submit);
        f.add(back);
        f.setVisible(true);

        //submit.addActionListener(this);

    }

    public static void main(String[] arg) {
        new EmpSearch("1");
    }
}