package uir.java.VendorInterfaces;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddSaleInterface {

    public AddSaleInterface() {

        final JFrame frame = new JFrame("Vendor Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.CENTER;

        // JLabels for the form
        JLabel vendoFromLabel = new JLabel("Here is the vendor form !");
        vendoFromLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel vendoFromCaptionLabel = new JLabel("Please fill it !");
        vendoFromCaptionLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel categoryLabel = new JLabel("Category:");
        JLabel productLabel = new JLabel("Product:");
        JLabel quantityLabel = new JLabel("Quantity:");
        JLabel dateLabel = new JLabel("Date:");

        // JComboBox for category and product selection
        JComboBox<String> categoryComboBox = new JComboBox<>(new String[]{"Phones", "Cars", "Clothes"});
        JComboBox<String> productComboBox = new JComboBox<>(new String[]{"iphone 12 ", "iphone 13", "Audi A5"});

        JTextField quantityField = new JTextField();

        // JCalendar for date input
        JDateChooser dateChooser = new JDateChooser();

        JButton goBackToInterface2Button = new JButton("Add to history");
        goBackToInterface2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close current frame
                new VendorHistorySalesInterface(); // Open Interface 2
            }
        });

        // Adding components to the panel

        // jlabel message addsale
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        panel.add(vendoFromLabel, constraints);
        // jlabel message fill it
        constraints.gridy = 1;
        panel.add(vendoFromCaptionLabel, constraints);

        // category jlabel
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        panel.add(categoryLabel, constraints);
        //category selectField
        constraints.gridx = 1;
        panel.add(categoryComboBox, constraints);

        // product jlabel
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(productLabel, constraints);
        //product selectField
        constraints.gridx = 1;
        panel.add(productComboBox, constraints);

        // quantity jlabel
        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(quantityLabel, constraints);
        //quantity textField
        constraints.gridx = 1;
        panel.add(quantityField, constraints);

        // date jlabel
        constraints.gridx = 0;
        constraints.gridy = 5;
        panel.add(dateLabel, constraints);
        //date JDateChooser
        constraints.gridx = 1;
        panel.add(dateChooser, constraints);

        // button jbutton
        constraints.gridy = 6;
        constraints.gridx = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(goBackToInterface2Button, constraints);

        frame.add(panel);
        frame.pack(); // Adjust frame size to fit components
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);
        frame.setSize(600, 400);
    }
}