package uir.java.VendorInterfaces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VendorHistorySalesInterface {
    public VendorHistorySalesInterface() {
        final JFrame frame = new JFrame("VendorHistorySalesInterface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton goToInterface3Button = new JButton("Add Sale");
        goToInterface3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close current frame
                new AddSaleInterface(); // Open Interface 3
            }
        });

        frame.getContentPane().add(goToInterface3Button, BorderLayout.CENTER);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);
    }
}
