package HotelProject;

import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class RemoveEmployee implements ActionListener{
    private JFrame f;
    private JLabel title,empName,empId;
    private JTextField empNameText,empIdText;
    private JButton removebtn,back;
    static Connection conn=null;
    RemoveEmployee(){
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


        //label
        title=new JLabel("Remove Employee");
        title.setFont(new Font("Times new Roman",Font.BOLD,50));
        title.setForeground(Color.orange);
        title.setBounds(250,150,500,50);

        empId=new JLabel("Employee ID");
        empId.setFont(new Font("Arial",Font.ITALIC,30));
        empId.setForeground(Color.WHITE);
        empId.setBounds(275,300,300,40);

        empName=new JLabel("Employee Name");
        empName.setFont(new Font("Arial",Font.ITALIC,30));
        empName.setForeground(Color.WHITE);
        empName.setBounds(275,380,300,40);

        //Textfield
        empIdText=new JTextField();
        empIdText.setBounds(550,310,150,30);
        empNameText=new JTextField();
        empNameText.setBounds(550,390,150,30);

        //Remove Button and back
        removebtn=new JButton();
        ImageIcon backimg=new ImageIcon("F:\\SagarProject\\src\\HotelProject\\removeBtn.jpg");
        removebtn.setIcon(backimg);
        removebtn.setBounds(520,460,150,50);
        back=new JButton();
        back.setBounds(120,50,90,50);
        ImageIcon backimg1=new ImageIcon("F:\\SagarProject\\src\\HotelProject\\back.png");
        back.setIcon(backimg1);


        //add
        f.add(title);
        f.add(empId);
        f.add(empName);
        f.add(empIdText);
        f.add(empNameText);
        f.add(removebtn);
        f.add(back);
        f.setVisible(true);


        removebtn.addActionListener(this);
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
        if(e.getSource()==removebtn){
            if(empNameText.getText().equals("") || empIdText.getText().equals("")){
                JOptionPane.showMessageDialog(f,"Enter both details","Error",JOptionPane.ERROR_MESSAGE);
            }
            else{
                try{
                    String query="delete from Emp where emp_id=? and emp_name=?";
                    PreparedStatement pst = conn.prepareStatement(query);
                    pst.setString(1,empIdText.getText());
                    pst.setString(2,empNameText.getText());
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
