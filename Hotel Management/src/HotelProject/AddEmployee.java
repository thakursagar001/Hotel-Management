package HotelProject;


import HotelProject.*;
import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AddEmployee implements ActionListener {

    private JFrame f;
    private JLabel empId,empName,fName,permanentAdd,title,contactNo,proof,password;
    private JTextField empIdText,empNameText,fNameText,permanentAddText,passwordText,contactText;
    private JButton submit,back;
    private JComboBox proofCombo;
    static Connection conn=null;


    AddEmployee(){
        //frame
        f=new JFrame();
        f.setContentPane(new JLabel(new ImageIcon("F:\\SagarProject\\src\\HotelProject\\bgimage1.jpg")));
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        f.setBounds(0, 0, screenSize.width, screenSize.height);
        f.setResizable(false);
        f.setTitle("GRAPHIC ERA HOTEL AND RESTAURANT");
        f.setLayout(null);
        f.setVisible(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        conn=SQLITE.dbconnector();
        //Labels
        title=new JLabel("Employee Details");
        title.setFont(new Font("Arial",Font.BOLD,50));
        title.setForeground(Color.cyan);
        title.setBounds(200,100,500,50);

        //employee details lABEL
        Font labelFont=new Font("Arial",Font.ITALIC,30);
        empId=new JLabel("Emp Id");
        empId.setBounds(200,180,190,50);
        empId.setForeground(Color.WHITE);
        empId.setFont(labelFont);
        empName=new JLabel("Emp Name");
        empName.setBounds(200,250,190,50);
        empName.setForeground(Color.WHITE);
        empName.setFont(labelFont);
        fName=new JLabel("Father Name");
        fName.setBounds(200,320,190,50);
        fName.setForeground(Color.WHITE);
        fName.setFont(labelFont);
        permanentAdd=new JLabel("Permanent Address");
        permanentAdd.setBounds(200,390,300,50);
        permanentAdd.setForeground(Color.WHITE);
        permanentAdd.setFont(labelFont);
        contactNo=new JLabel("Contact NO.");
        contactNo.setBounds(200,460,190,50);
        contactNo.setForeground(Color.WHITE);
        contactNo.setFont(labelFont);
        proof=new JLabel("Proof");
        proof.setBounds(200,530,190,50);
        proof.setForeground(Color.WHITE);
        proof.setFont(labelFont);
        password=new JLabel("Password");
        password.setBounds(200,600,190,50);
        password.setForeground(Color.WHITE);
        password.setFont(labelFont);


        //employee details Textfield
        empIdText=new JTextField();
        empIdText.setBounds(500,190,150,30);
        empNameText=new JTextField();
        empNameText.setBounds(500,260,150,30);
        fNameText=new JTextField();
        fNameText.setBounds(500,330,150,30);
        permanentAddText=new JTextField();
        permanentAddText.setBounds(500,400,150,30);
        contactText=new JTextField();
        contactText.setBounds(500,470,150,30);
        passwordText=new JTextField();
        passwordText.setBounds(500,610,150,30);


        //proof Combo button
        String [] names={"Adhar_Card","Pan_Card","Driving_License","Voter_Id"};
        proofCombo=new JComboBox(names);
         proofCombo.setSelectedIndex(0);
        proofCombo.setBounds(500,540,150,30);



        //submit Button and back
        submit=new JButton("Submit");
        submit.setBounds(200,670,150,45);
        submit.setFont(new Font("Arial",Font.BOLD,25));
        back=new JButton();
        back.setBounds(120,50,90,50);
        ImageIcon backimg=new ImageIcon("F:\\SagarProject\\src\\HotelProject\\back.png");
        back.setIcon(backimg);


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

        submit.addActionListener(this);
        back.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==back){
            try {
                conn.close();
            }
            catch(Exception e1){

            }
            new AdminAdd();
            f.setVisible(false);
        }
        if(e.getSource()==submit){
           if((empIdText.getText().equals("")) || (empNameText.getText().equals("")) || (fNameText.getText().equals("")) || (permanentAddText.getText().equals(""))
                                            || (passwordText.getText().equals("")) || (contactText.getText().equals("")) )
                JOptionPane.showMessageDialog(f,"Enter all the details","Error",JOptionPane.ERROR_MESSAGE);
            else{
                try {

                    String query = "insert into Emp(emp_id,emp_name,father_name,permanent_address,contact_No,proof,password)" +
                            "values(?,?,?,?,?,?,?)";
                    PreparedStatement pst = conn.prepareStatement(query);

                        pst.setString(1, empIdText.getText());
                        pst.setString(2, empNameText.getText());
                        pst.setString(3, fNameText.getText());
                        pst.setString(4, permanentAddText.getText());
                        pst.setString(5, contactText.getText());
                        pst.setString(6, proofCombo.getSelectedItem() + "");
                        pst.setString(7, passwordText.getText());
                         pst.executeUpdate();
                        conn.close();
                        new AdminAdd();
                        f.setVisible(false);
                }
                catch(Exception e1){
                    JOptionPane.showMessageDialog(null,e1.getMessage(),"error",JOptionPane.ERROR_MESSAGE);
                }
           }

        }

    }
}
