package HotelProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddFood implements ActionListener {
    private JFrame f;
    private JLabel title, type, name, price;
    private JTextField typeText, nameText, priceText;
    private JButton back, add;
    static Connection conn = null;

    AddFood() {
        conn = SQLITE.dbconnector();
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


        //title Labels
        title = new JLabel("Add Food Items");
        title.setFont(new Font("Arial", Font.BOLD, 50));
        title.setForeground(Color.cyan);
        title.setBounds(200, 100, 500, 50);


        //other labels
        Font labelFont = new Font("Arial", Font.ITALIC, 30);
        type = new JLabel("TYPE");
        type.setBounds(200, 240, 190, 50);
        type.setForeground(Color.WHITE);
        type.setFont(labelFont);
        name = new JLabel("NAME");
        name.setBounds(200, 330, 190, 50);
        name.setForeground(Color.WHITE);
        name.setFont(labelFont);
        price = new JLabel("PRICE");
        price.setBounds(200, 420, 190, 50);
        price.setForeground(Color.WHITE);
        price.setFont(labelFont);


        //textfields
        typeText = new JTextField();
        typeText.setBounds(500, 240, 150, 30);
        nameText = new JTextField();
        nameText.setBounds(500, 330, 150, 30);
        priceText = new JTextField();
        priceText.setBounds(500, 420, 150, 30);

        //back and add button
        back = new JButton();
        back.setBounds(120, 50, 90, 50);
        ImageIcon backimg = new ImageIcon("F:\\SagarProject\\src\\HotelProject\\back.png");
        back.setIcon(backimg);
        add = new JButton("ADD");
        add.setBounds(450, 510, 150, 45);
        add.setFont(new Font("Arial", Font.BOLD, 25));


        f.add(title);
        f.add(type);
        f.add(name);
        f.add(price);
        f.add(typeText);
        f.add(nameText);
        f.add(priceText);
        f.add(add);
        f.add(back);
        f.setVisible(true);


        back.addActionListener(this);
        add.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            try {
                conn.close();
            }
            catch(Exception e1){

            }
            new AdminAdd();
            f.setVisible(false);
        }
        if (e.getSource() == add) {
            if (typeText.getText().equals("") || nameText.getText().equals("") || priceText.getText().equals("")) {
                JOptionPane.showMessageDialog(f, "Enter all the details", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    String query = "insert into Food(type,name,price)" +
                            "values(?,?,?)";
                    PreparedStatement pst = conn.prepareStatement(query);
                    pst.setString(1, typeText.getText());
                    pst.setString(2, nameText.getText());
                    pst.setString(3, priceText.getText());
                    pst.executeUpdate();
                    conn.close();
                    new AdminAdd();
                    f.setVisible(false);
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

}