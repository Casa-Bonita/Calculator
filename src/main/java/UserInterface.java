import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.*;

public class UserInterface {

    private Dimension dimensionFrame;
    String tempOperation = "";
    String operationType = "";
    int temp1 = 0;
    int temp2 = 0;
    int number1 = 0;
    int number2 = 0;
    String result = "";
    public JTextField textField = null;

    public UserInterface(Dimension dimensionFrame) {
        this.dimensionFrame = dimensionFrame;
    }

    public void createUI(){

        MigLayout layout = new MigLayout(
                "", // Layout Constraints
                "[]10[]10[]10[]", // Column constraints
                "[]10[]10[]10[]10[]"); // Row constraints

        JFrame frame = new JFrame("Калькулятор");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(dimensionFrame));
        frame.setMinimumSize(new Dimension(dimensionFrame));
        frame.setMaximumSize(new Dimension(dimensionFrame));
        frame.setPreferredSize(new Dimension(dimensionFrame));
        frame.setLayout(new MigLayout());

        JPanel panel = new JPanel();
        panel.setLayout(layout);


        Font font = new Font("Calibri", Font.BOLD, 30);

        textField = new JTextField();
        textField.setText("");
        textField.setFont(font);

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");

        JMenu calculatorItem = new JMenu("Calculator");

        JMenuItem classic = new JMenuItem("Classic");
        calculatorItem.add(classic);
        calculatorItem.addSeparator();
        JMenuItem color = new JMenuItem("Color");
        calculatorItem.add(color);

        fileMenu.add(calculatorItem);
        fileMenu.addSeparator();

        JMenuItem openMenu = new JMenuItem("Open");
        fileMenu.add(openMenu);
        fileMenu.addSeparator();

        JMenuItem saveMenu = new JMenuItem("Save");
        fileMenu.add(saveMenu);
        fileMenu.addSeparator();

        JMenuItem exitMenu = new JMenuItem("Exit");
        fileMenu.add(exitMenu);

        menuBar.add(fileMenu);

        JMenu infoMenu = new JMenu("Info");

        JMenuItem licenseItem = new JMenuItem("License");
        infoMenu.add(licenseItem);
        infoMenu.addSeparator();

        JMenuItem aboutItem = new JMenuItem("About");
        infoMenu.add(aboutItem);

        menuBar.add(infoMenu);


        JButton buttonTotal = new JButton("=");
        buttonTotal.setFont(font);
        buttonTotal.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                number1 = Integer.valueOf(temp1);
                number2 = Integer.valueOf(temp2);
                operationType = tempOperation;
                try{
                    Operations operations = new Operations();
                    result = String.valueOf(operations.calculate(number1, operationType, number2));
                    textField.setText(result);
                }
                catch(ArithmeticException ex){
                    System.out.println("Can not divide by zero ");
                    ex.printStackTrace();
                    textField.setText("ERROR");
                }
            }
        });


        JButton buttonClearAll = new JButton("C");
        buttonClearAll.setFont(font);
        buttonClearAll.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                textField.setText("");
                tempOperation = "";
                operationType = "";
                temp1 = 0;
                temp2 = 0;
                number1 = 0;
                number2 = 0;
                result = "";
            }
        });


        JButton buttonDivision = new JButton("/");
        buttonDivision.setFont(font);
        buttonDivision.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                textField.setText("");
                tempOperation = "/";
            }
        });


        JButton buttonMultiplication = new JButton("*");
        buttonMultiplication.setFont(font);
        buttonMultiplication.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                textField.setText("");
                tempOperation = "*";
            }
        });


        JButton buttonSubtraction = new JButton("-");
        buttonSubtraction.setFont(font);
        buttonSubtraction.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                textField.setText("");
                tempOperation = "-";
            }
        });


        JButton buttonAddition = new JButton("+");
        buttonAddition.setFont(font);
        buttonAddition.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                textField.setText("");
                tempOperation = "+";
            }
        });


        JButton buttonDeleteLast = new JButton("<-");
        buttonDeleteLast.setFont(font);
//        buttonDeleteLast.addActionListener(new ActionListener(){
//            @Override
//            public void actionPerformed(ActionEvent actionEvent){
//                bDeleteLast = ((JButton)actionEvent.getSource()).getText();
//                textField.setText(bDeleteLast);
//            }
//        });


        JButton buttonPlusMinus = new JButton("-+");
        buttonPlusMinus.setFont(font);
//        buttonPlusMinus.addActionListener(actionListener);


        JButton buttonDigit = new JButton(",");
        buttonDigit.setFont(font);
        buttonDigit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                textField.setText(textField.getText() + ",");
            }
        });


        JButton button0 = new JButton("0");
        button0.setFont(font);
        button0.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                textField.setText(textField.getText() + 0);
                if (temp1 == 0){
                    temp1 = Integer.valueOf(textField.getText());
                }
                else{
                    temp2 = Integer.valueOf(textField.getText());
                }
            }
        });


        JButton button1 = new JButton("1");
        button1.setFont(font);
        button1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                textField.setText(textField.getText() + 1);
                if (temp1 == 0){
                    temp1 = Integer.valueOf(textField.getText());
                }
                else{
                    temp2 = Integer.valueOf(textField.getText());
                }
            }
        });


        JButton button2 = new JButton("2");
        button2.setFont(font);
        button2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                textField.setText(textField.getText() + 2);
                if (temp1 == 0){
                    temp1 = Integer.valueOf(textField.getText());
                }
                else{
                    temp2 = Integer.valueOf(textField.getText());
                }
            }
        });


        JButton button3 = new JButton("3");
        button3.setFont(font);
        button3.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                textField.setText(textField.getText() + 3);
                if (temp1 == 0){
                    temp1 = Integer.valueOf(textField.getText());
                }
                else{
                    temp2 = Integer.valueOf(textField.getText());
                }
            }
        });


        JButton button4 = new JButton("4");
        button4.setFont(font);
        button4.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                textField.setText(textField.getText() + 4);
                if (temp1 == 0){
                    temp1 = Integer.valueOf(textField.getText());
                }
                else{
                    temp2 = Integer.valueOf(textField.getText());
                }
            }
        });


        JButton button5 = new JButton("5");
        button5.setFont(font);
        button5.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                textField.setText(textField.getText() + 5);
                if (temp1 == 0){
                    temp1 = Integer.valueOf(textField.getText());
                }
                else{
                    temp2 = Integer.valueOf(textField.getText());
                }
            }
        });


        JButton button6 = new JButton("6");
        button6.setFont(font);
        button6.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                textField.setText(textField.getText() + 6);
                if (temp1 == 0){
                    temp1 = Integer.valueOf(textField.getText());
                }
                else{
                    temp2 = Integer.valueOf(textField.getText());
                }
            }
        });


        JButton button7 = new JButton("7");
        button7.setFont(font);
        button7.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                textField.setText(textField.getText() + 7);
                if (temp1 == 0){
                    temp1 = Integer.valueOf(textField.getText());
                }
                else{
                    temp2 = Integer.valueOf(textField.getText());
                }
            }
        });


        JButton button8 = new JButton("8");
        button8.setFont(font);
        button8.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                textField.setText(textField.getText() + 8);
                if (temp1 == 0){
                    temp1 = Integer.valueOf(textField.getText());
                }
                else{
                    temp2 = Integer.valueOf(textField.getText());
                }
            }
        });


        JButton button9 = new JButton("9");
        button9.setFont(font);
        button9.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                textField.setText(textField.getText() + 9);
                if (temp1 == 0){
                    temp1 = Integer.valueOf(textField.getText());
                }
                else{
                    temp2 = Integer.valueOf(textField.getText());
                }
            }
        });


        panel.add(textField, "cell 0 0, w 270!, h 60!, span, wrap");
        panel.add(buttonClearAll, "cell 0 1, w 60!, h 60!");
        panel.add(buttonDeleteLast, "cell 1 1, w 60!, h 60!");
        panel.add(buttonDivision, "cell 2 1, w 60!, h 60!");
        panel.add(buttonMultiplication, "cell 3 1, w 60!, h 60!, wrap");
        panel.add(button7, "cell 0 2, w 60!, h 60!");
        panel.add(button8, "cell 1 2, w 60!, h 60!");
        panel.add(button9, "cell 2 2, w 60!, h 60!");
        panel.add(buttonSubtraction, "cell 3 2, w 60!, h 60!, wrap");
        panel.add(button4, "cell 0 3, w 60!, h 60!");
        panel.add(button5, "cell 1 3, w 60!, h 60!");
        panel.add(button6, "cell 2 3, w 60!, h 60!");
        panel.add(buttonAddition, "cell 3 3, w 60!, h 60!, wrap");
        panel.add(button1, "cell 0 4, w 60!, h 60!");
        panel.add(button2, "cell 1 4, w 60!, h 60!");
        panel.add(button3, "cell 2 4, w 60!, h 60!");
        panel.add(buttonTotal, "cell 3 4, span 1 2, w 60!, h 130!, wrap");
        panel.add(buttonPlusMinus, "cell 0 5, w 60!, h 60!");
        panel.add(button0, "cell 1 5, w 60!, h 60!");
        panel.add(buttonDigit, "cell 2 5, w 60!, h 60!");

        frame.setJMenuBar(menuBar);
        frame.add(panel, "w 600!, h 600!, align center");

        frame.setResizable(false);
        frame.setVisible(true);


    }
}