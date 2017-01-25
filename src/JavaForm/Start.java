package JavaForm;
import Distance.*;
import Distance.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

/**
 * Created by Никита on 21.01.2017.
 */
public class Start extends JFrame{
    JButton btnCalculate;
    JLabel lDistance, lAnswer;
    JTextField tPOD, tPOA;
    String adress1, adress2, query1, query2;

    e handler = new e();

    public Start(String s){
        super(s);
        setLayout(null);
        btnCalculate = new JButton("Рассчитать расстояние");
        btnCalculate.setBounds(10,10,190,20);
        tPOD = new JTextField(10);
        tPOD.setBounds(210,10,100,20);
        tPOA = new JTextField(10);
        tPOA.setBounds(320,10,100,20);
        lDistance = new JLabel("Distance");
        lDistance.setBounds(10,40,100,20);
        lAnswer = new JLabel();
        lAnswer.setBounds(100,40,100,20);

        add(btnCalculate);
        add(tPOD);
        add(tPOA);
        add(lDistance);
        add(lAnswer);
        btnCalculate.addActionListener(handler);
    }

    public class e implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            adress1 = tPOD.getText();
            adress2 = tPOA.getText();
            query1 = "http://maps.google.com/maps/api/geocode/xml?address=" + adress1 + "&sensor=false";
            query2 = "http://maps.google.com/maps/api/geocode/xml?address=" + adress2 + "&sensor=false";
            if (e.getSource() == btnCalculate){
                Calculate calculate = new Calculate();
                Map map = new Map();
                calculate.getXML(query1, "Page1.xml");
                calculate.getXML(query2, "Page2.xml");
                lAnswer.setText(String.valueOf(calculate.calculateTheDistance(map.getPoint("Page1.xml"), map.getPoint("Page2.xml"))));
            }
        }
    }
}
