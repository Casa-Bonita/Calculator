import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class UserInterface {

    private Dimension dimensionFrame;
    String operationType = "";
    String result = "";
    public JTextField textField = null;
    List<String> listData = new ArrayList<>();
    String calculatorType = "";

    Operations operations = new Operations();

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
        Font fontTextArea = new Font("Calibri", Font.PLAIN, 20);

        textField = new JTextField();
        textField.setText("");
        textField.setFont(font);

        JMenuBar menuBar = new JMenuBar();

        JMenu mainMenu = new JMenu("Main");

        JMenu calculatorItem = new JMenu("Calculator");

        JMenuItem classic = new JMenuItem("Classic");
        calculatorItem.add(classic);
        calculatorItem.addSeparator();
        classic.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                calculatorType = "classic";
            }
        });


        JMenuItem color = new JMenuItem("Color");
        calculatorItem.add(color);
        color.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                calculatorType = "color";
            }
        });

        mainMenu.add(calculatorItem);
        mainMenu.addSeparator();

        JMenuItem exitMenu = new JMenuItem("Exit");
        mainMenu.add(exitMenu);
        exitMenu.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                System.exit(0);
            }
        });

        menuBar.add(mainMenu);

        JMenu infoMenu = new JMenu("Info");

        JMenuItem licenseItem = new JMenuItem("License");
        infoMenu.add(licenseItem);
        infoMenu.addSeparator();
        licenseItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                Dimension dimensionTextFrame = new Dimension(500, 500);
                JFrame frame = new JFrame("Лицензионное соглашение");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(new Dimension(dimensionTextFrame));
                frame.setMinimumSize(new Dimension(dimensionTextFrame));
                frame.setMaximumSize(new Dimension(dimensionTextFrame));
                frame.setPreferredSize(new Dimension(dimensionTextFrame));

                String line = "";
                String pathFile = "src/main/java/license.txt";
                BufferedReader br = null;
                List<String> listLicense = new ArrayList<>();
                try{
                    br = new BufferedReader(new FileReader(pathFile));
                    line = br.readLine();
                    while(line != null){
                        listLicense.add(line);
                        line = br.readLine();
                    }
                    br.close();
                }catch (IOException ex){
                    ex.printStackTrace();
                }

                line = "";
                for (int i = 0; i < listLicense.size(); i++) {
                    line = line + listLicense.get(i);
                }

                JTextArea textArea = new JTextArea(line, 17,28);

                textArea.setFont(fontTextArea);
                textArea.setTabSize(10);
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                JPanel textAreaPanel = new JPanel();
                textAreaPanel.add(textArea);
                textAreaPanel.add(new JScrollPane(textArea));
                textAreaPanel.setSize(480,480);
                frame.add(textAreaPanel);
                textAreaPanel.setVisible(true);
                frame.setResizable(false);
                frame.setVisible(true);
            }
        });

        JMenuItem aboutItem = new JMenuItem("About");
        infoMenu.add(aboutItem);
        aboutItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                Dimension dimensionTextFrame = new Dimension(300, 180);
                JFrame frame = new JFrame("О программе");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(new Dimension(dimensionTextFrame));
                frame.setMinimumSize(new Dimension(dimensionTextFrame));
                frame.setMaximumSize(new Dimension(dimensionTextFrame));
                frame.setPreferredSize(new Dimension(dimensionTextFrame));
                JTextArea textArea = new JTextArea
                        ("Здесь располагается информация об авторских правах", 3, 10);
                textArea.setFont(font);
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                JPanel textAreaPanel = new JPanel();
                textAreaPanel.add(textArea);
                textAreaPanel.add(new JScrollPane(textArea));
                textAreaPanel.setSize(180,120);
                frame.add(textAreaPanel);
                textAreaPanel.setVisible(true);
                frame.setResizable(false);
                frame.setVisible(true);
            }
        });

        menuBar.add(infoMenu);


        JButton buttonTotal = new JButton("=");
        buttonTotal.setFont(font);
        buttonTotal.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                try{
                    result = operations.calculate(listData, operationType);
                    textField.setText(result);

                    // очистка списка после вывода результата и занесение в него результата, если с результатом будут дальнейшие операции
                    operationType = "";
                    for (int i = 0; i < listData.size(); i++) {
                        listData.remove(i);
                    }
                    listData.add(result);
                    result = "";
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
                clearAll();
            }
        });


        JButton buttonDivision = new JButton("/");
        buttonDivision.setFont(font);
        buttonDivision.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                textField.setText("");
                operationType = "/";
                listData.add(operationType);
            }
        });


        JButton buttonMultiplication = new JButton("*");
        buttonMultiplication.setFont(font);
        buttonMultiplication.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                textField.setText("");
                operationType = "*";
                listData.add(operationType);
            }
        });


        JButton buttonSubtraction = new JButton("–");
        buttonSubtraction.setFont(font);
        buttonSubtraction.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                textField.setText("");
                operationType = "-";
                listData.add(operationType);
            }
        });


        JButton buttonAddition = new JButton("+");
        buttonAddition.setFont(font);
        buttonAddition.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                textField.setText("");
                operationType = "+";
                listData.add(operationType);
            }
        });


        JButton buttonDeleteLast = new JButton("<-");
        buttonDeleteLast.setFont(font);
        buttonDeleteLast.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                String temp = listData.get(listData.size() - 1);
                temp = temp.substring(0, temp.length() - 1);
                listData.remove(listData.size() - 1);
                listData.add(temp);
                textField.setText(temp);
            }
        });


        JButton buttonPlusMinus = new JButton("-+");
        buttonPlusMinus.setFont(font);
        buttonPlusMinus.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                double sign = Double.parseDouble(listData.get(listData.size() - 1));
                sign = -1 * sign;
                String temp = Double.toString(sign);
                listData.remove(listData.size() - 1);
                listData.add(temp);
                textField.setText(listData.get(listData.size() - 1));
            }
        });


        JButton buttonDigit = new JButton(".");
        buttonDigit.setFont(font);
        buttonDigit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                textField.setText(textField.getText() + ".");
            }
        });


        JButton button0 = new JButton("0");
        button0.setFont(font);
        button0.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                if(textField.getText().equals("ERROR")){
                    clearAll();
                }
                textField.setText(textField.getText() + 0);
                listData.add(textField.getText());
            }
        });


        JButton button1 = new JButton("1");
        button1.setFont(font);
        button1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                if(textField.getText().equals("ERROR")){
                    clearAll();
                }
                textField.setText(textField.getText() + 1);
                listData.add(textField.getText());
            }
        });


        JButton button2 = new JButton("2");
        button2.setFont(font);
        button2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                if(textField.getText().equals("ERROR")){
                    clearAll();
                }
                textField.setText(textField.getText() + 2);
                listData.add(textField.getText());
            }
        });


        JButton button3 = new JButton("3");
        button3.setFont(font);
        button3.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                if(textField.getText().equals("ERROR")){
                    clearAll();
                }
                textField.setText(textField.getText() + 3);
                listData.add(textField.getText());
            }
        });


        JButton button4 = new JButton("4");
        button4.setFont(font);
        button4.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                if(textField.getText().equals("ERROR")){
                    clearAll();
                }
                textField.setText(textField.getText() + 4);
                listData.add(textField.getText());
            }
        });


        JButton button5 = new JButton("5");
        button5.setFont(font);
        button5.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                if(textField.getText().equals("ERROR")){
                    clearAll();
                }
                textField.setText(textField.getText() + 5);
                listData.add(textField.getText());
            }
        });


        JButton button6 = new JButton("6");
        button6.setFont(font);
        button6.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                if(textField.getText().equals("ERROR")){
                    clearAll();
                }
                textField.setText(textField.getText() + 6);
                listData.add(textField.getText());
            }
        });


        JButton button7 = new JButton("7");
        button7.setFont(font);
        button7.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                if(textField.getText().equals("ERROR")){
                    clearAll();
                }
                textField.setText(textField.getText() + 7);
                listData.add(textField.getText());
            }
        });


        JButton button8 = new JButton("8");
        button8.setFont(font);
        button8.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                if(textField.getText().equals("ERROR")){
                    clearAll();
                }
                textField.setText(textField.getText() + 8);
                listData.add(textField.getText());
            }
        });


        JButton button9 = new JButton("9");
        button9.setFont(font);
        button9.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                if(textField.getText().equals("ERROR")){
                    clearAll();
                }
                textField.setText(textField.getText() + 9);
                listData.add(textField.getText());
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

    public void clearAll (){
        textField.setText("");
        operationType = "";
        result = "";
        for (int i = 0; i < listData.size(); i++) {
            listData.remove(i);
        }
    }
}