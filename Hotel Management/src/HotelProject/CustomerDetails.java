package HotelProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class CustomerDetails implements ActionListener {
    
    private JFrame f;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5,textField_6,customerSearchText;
    private JButton Submit,search,logOut;
    private JComboBox comboBox;
    static Connection conn=null;
    
    CustomerDetails(){
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

        JLabel lblGuestInfo = new JLabel("NEW GUEST INFO");
        lblGuestInfo.setForeground(Color.cyan);
        lblGuestInfo.setFont(new Font("Times New Roman", Font.BOLD, 30));
        lblGuestInfo.setBounds(800, 60, 295, 30);
        f.add(lblGuestInfo);

        Font ff=new Font("Arial",Font.ITALIC,20);
        JLabel lblFirstName = new JLabel("FIRST NAME");
        lblFirstName.setBounds(800, 120, 150, 30);
        lblFirstName.setFont(ff);
        lblFirstName.setForeground(Color.white);
        f.add(lblFirstName);

        JLabel lblLastName = new JLabel("LAST NAME");
        lblLastName.setBounds(800, 170,150, 30);
        lblLastName.setFont(ff);
        lblLastName.setForeground(Color.white);
        f.add(lblLastName);

        JLabel lblAdd = new JLabel("ADDRESS");
        lblAdd.setBounds(800, 220, 150, 30);
        lblAdd.setForeground(Color.white);
        lblAdd.setFont(ff);
        f.add(lblAdd);

        JLabel lblCity = new JLabel("CITY");
        lblCity.setBounds(800, 270, 150, 30);
        lblCity.setFont(ff);
        lblCity.setForeground(Color.white);
        f.add(lblCity);

        JLabel lblState = new JLabel("STATE");
        lblState.setBounds(800, 320, 150, 30);
        lblState.setForeground(Color.white);
        lblState.setFont(ff);
        f.add(lblState);

        JLabel lblCountry = new JLabel("COUNTRY");
        lblCountry.setBounds(800,370, 150, 30);
        lblCountry.setFont(ff);
        lblCountry.setForeground(Color.white);
        f.add(lblCountry);

        JLabel lblCusId = new JLabel("CUSTOMER ID");
        lblCusId.setBounds(800,420, 150, 30);
        lblCusId.setFont(ff);
        lblCusId.setForeground(Color.white);
        f.add(lblCusId);



        textField = new JTextField();
        textField.setBounds(1000, 120, 130, 25);
        f.add(textField);


        textField_1 = new JTextField();
        textField_1.setBounds(1000, 170, 130, 25);
        f.add(textField_1);

        textField_2 = new JTextField();
        textField_2.setBounds(1000, 220, 130, 25);
        f.add(textField_2);

        textField_3 = new JTextField();
        textField_3.setBounds(1000, 270, 130, 25);
        f.add(textField_3);

        textField_4 = new JTextField();
        textField_4.setBounds(1000, 320, 130, 25);
        f.add(textField_4);

        textField_5 = new JTextField();
        textField_5.setBounds(1000, 370, 130, 25);
        f.add(textField_5);

        textField_6 = new JTextField();
        textField_6.setBounds(1000, 420, 130, 25);
        f.add(textField_6);

        JLabel lblIdentification = new JLabel("IDENTIFICATION");
        lblIdentification.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
        lblIdentification.setBounds(800, 500, 300, 30);
        lblIdentification.setForeground(Color.cyan);
        f.add(lblIdentification);

        JLabel lblNewLabel_1 = new JLabel("ID PROFF");
        lblNewLabel_1.setBounds(800, 560, 150, 30);
        lblNewLabel_1.setForeground(Color.white);
        lblNewLabel_1.setFont(ff);
        f.add(lblNewLabel_1);

        String [] names={"Adhar_Card","Pan_Card","Driving_License","Voter_Id"};
        comboBox = new JComboBox(names);
        comboBox.setSelectedIndex(0);
        comboBox.setBounds(1000, 560, 150, 30);
        f.add(comboBox);

        JLabel customerSearch=new JLabel("Customer ID");
        customerSearch.setBounds(200,200, 150, 30);
        customerSearch.setFont(new Font("Times NEW ROMAN",Font.BOLD,25));
        customerSearch.setForeground(Color.white);
        f.add(customerSearch);


        JLabel title=new JLabel("REGISTERED CUSTOMER");
        title.setFont(new Font("Arial",Font.BOLD,30));
        title.setBounds(200,130,400,40);
        title.setForeground(Color.cyan);
        f.add(title);

        customerSearchText = new JTextField();
        customerSearchText.setBounds(400,200, 150,30);
        f.add(customerSearchText);

        Submit=new JButton("SUBMIT");
        Submit.setForeground(Color.BLACK);
        Submit.setBounds(950,610,100,40);
        f.add(Submit);

        search=new JButton("Search");
        search.setForeground(Color.BLACK);
        search.setBounds(400,270,100,40);
        f.add(search);

        //buttons
        logOut=new JButton("LOGOUT");
        logOut.setFont(new Font("Arial",Font.BOLD,20));
        logOut.setBounds(120,50,200,50);
        f.add(logOut);

        f.setVisible(true);

        logOut.addActionListener(this);
        Submit.addActionListener(this);
        search.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==logOut){
            try {
                conn.close();
            }
            catch(Exception e1){

            }
            new LoginPage();
            f.setVisible(false);
        }
        if(e.getSource()==Submit){
            if(textField_6.getText().equals("") ||textField.getText().equals("")|| textField_1.getText().equals("")
                    || textField_2.getText().equals("")|| textField_3.getText().equals("")|| textField_4.getText().equals("") ||textField_5.getText().equals("")){

                JOptionPane.showMessageDialog(null,"enter all details","error",JOptionPane.ERROR_MESSAGE);
            }
            else{
                try {

                    String query = "insert into Customer(cus_Id,first_Name,last_Name,address,city,state,country,id_proof)" +
                            "values(?,?,?,?,?,?,?,?)";
                    PreparedStatement pst = conn.prepareStatement(query);

                    pst.setString(1, textField_6.getText());
                    pst.setString(2, textField.getText());
                    pst.setString(3, textField_1.getText());
                    pst.setString(4, textField_2.getText());
                    pst.setString(5, textField_3.getText());
                    pst.setString(6, textField_4.getText());
                    pst.setString(7, textField_5.getText());
                    pst.setString(8, comboBox.getSelectedItem()+"");
                    pst.executeUpdate();
                    conn.close();
                    new detail(textField_6.getText());
                    f.setVisible(false);
                }
                catch (Exception e1){
                    JOptionPane.showMessageDialog(null,e1.getMessage(),"error",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        if(e.getSource()==search){
            if(customerSearchText.getText().equals("")){
                JOptionPane.showMessageDialog(null,"customer id not entered","error",JOptionPane.ERROR_MESSAGE);
            }
            else{
                try{
                    String query="select * from Customer where cus_Id=?";
                    PreparedStatement pst = conn.prepareStatement(query);
                    pst.setString(1,customerSearchText.getText());
                    ResultSet rst=pst.executeQuery();
                    if(rst.next()){
                        conn.close();
                        new detail(customerSearchText.getText());
                        f.setVisible(false);
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"customer Id not found","error",JOptionPane.ERROR_MESSAGE);
                    }
                }
                catch (Exception e1){
                    JOptionPane.showMessageDialog(null,e1.getMessage(),"error",JOptionPane.ERROR_MESSAGE);
                }
            }
        }

    }
}
