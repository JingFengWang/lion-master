package org.lion.awt;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class AreaTest extends JFrame implements ActionListener
{
    JTextArea txt1=new JTextArea(7,35);///创建文本区对象，要确定文本区的大小
    JTextField txt2=new JTextField(35);///创建文本框对象，同时也要指出大小
    String str="窗外飘起蒙蒙细雨，\n平添一夜寒意，"+"\n多少的思绪藏在心底，";
    public AreaTest() {

        setSize(400,300);
        setVisible(true);
        setTitle("文本组件示例");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());///设置浮动布局
        txt1.setText(str);///将str设置为文本区的内容
        add(txt1);///将文本区添加到窗体中
        add(txt2);///将文本框添加到窗体中
        validate();///设置窗体中的组件可显
        txt2.addActionListener(this);
        // TODO Auto-generated constructor stub
    }
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        String s=txt2.getText();///获取文本框中的内容
        txt1.append("\n"+s);///将文本框中的内容追加的文本区
    }
}

public class Window {
    public static void main(String[] args)
    {
        new AreaTest();
    }
}
