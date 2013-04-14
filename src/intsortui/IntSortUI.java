/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package intsortui;

//程序的作用：通过UI与用户的交互，让用户输入n个整数，
//通过按按钮，然后冒泡,选择、插入中一个输出从小到大的排序
//作者：吴建杰 20102100035
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JScrollPane;
import java.awt.Image;
import java.awt.Toolkit;

//事件处理包
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;

public class IntSortUI extends JFrame {
    //构造函数

    public IntSortUI() {
        super("吴建杰20102100035的多选择排序算法小程序");
        //获得面板
        Container c = getContentPane();
        //布局为绝对坐标
        c.setLayout(null);

        //获得一个textField
        final JTextField textField = new JTextField(null, 30);

        //获得一个textArea
        final JTextArea textArea = new JTextArea(null, 5, 10);
        textArea.setLineWrap(true);
        JScrollPane scrollTextArea = new JScrollPane(textArea);

        JButton b = new JButton("开始排序");
        JButton bQuit = new JButton("退出程序");

        ////获得3个JRadioButton
        final JRadioButton[] r = {
            new JRadioButton("冒泡"),
            new JRadioButton("选择"),
            new JRadioButton("插入")
        };

        //把3个JRadioButton关联起来
        ButtonGroup rg = new ButtonGroup();
        r[0].setSelected(false);
        r[1].setSelected(false);
        r[2].setSelected(false);

        //d获得多个label
        JLabel[] labels = {
            new JLabel("欢迎使用本排序小程序！！"),
            new JLabel("使用方法为："),
            new JLabel("1、任意输入需要排序整型的数据(以空格隔开)。"),
            new JLabel("2、选择排序的算法按钮。"),
            new JLabel("3、按“开始排序”即进行排序并输出结果。"),
            new JLabel("输入整数："),
            new JLabel("输出结果："),
            new JLabel("算法选择：")
        };

        //设置每个组件的坐标与大小
        for (int i = 0; i < 5; i++) {
            labels[i].setBounds(50, 15 * (i + 1), 400, 20);
            c.add(labels[i]);
        }
        labels[5].setBounds(40, 110, 70, 20);
        labels[6].setBounds(40, 150, 70, 20);
        labels[7].setBounds(40, 255, 70, 20);
        textField.setBounds(110, 110, 300, 20);
        scrollTextArea.setBounds(110, 150, 300, 100);

        for (int i = 0; i < 3; i++) {
            r[i].setBounds(40 + i * 57, 280, 60, 20);
        }

        b.setBounds(220, 280, 90, 20);
        bQuit.setBounds(320,280,90,20);

        //把每个组件添加到面板上
        c.add(labels[5]);
        c.add(labels[6]);
        c.add(labels[7]);
        c.add(textField);
        c.add(scrollTextArea);

        for (int i = 0; i < 3; i++) {
            c.add(r[i]);
            rg.add(r[i]);
        }
        c.add(b);
        c.add(bQuit);

        //内部类，Button响应事件，“开始排序”
        b.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                JButton b = (JButton) e.getSource();
                //把用户输入的数据进行整数转换
                int temp;

                //用来判断用户是否输入整型数据
                String boolString = textField.getText();

                if (boolString.length() != 0) {
                    String str[] = (textField.getText()).split(" ");
                    int[] number = new int[str.length];
                    for (int i = 0; i < str.length; i++) {
                        number[i] = Integer.parseInt(str[i]);
                    }

                    //冒泡排序被选到，进行冒泡排序
                    if (r[0].isSelected()) {
                        for (int i = 0; i < str.length; i++) {
                            for (int j = i + 1; j < str.length; j++) {
                                if (number[i] > number[j]) {
                                    temp = number[i];
                                    number[i] = number[j];
                                    number[j] = temp;
                                }
                            }
                        }

                    }

                    //选择排序被选到，进行选择排序
                    if (r[1].isSelected()) {

                        for (int i = 0; i < str.length; i++) {
                            int k = i;
                            for (int j = i + 1; j < str.length; j++) {
                                if (number[k] > number[j]) {
                                    k = j;
                                }
                            }
                            if (k != i) {
                                temp = number[i];
                                number[i] = number[k];
                                number[k] = temp;
                            }
                        }
                    }

                    //插入排序被选到，进行插入排序
                    if (r[2].isSelected()) {

                        int j;
                        for (int i = 1; i < str.length; i++) {
                            temp = number[i];
                            j = i - 1;
                            while ((j >= 0) && (number[j] > temp)) {
                                number[j + 1] = number[j];
                                j = j - 1;
                            }
                            number[j + 1] = temp;
                        }
                    }

                    String s = " ";
                    for (int i = 0; i < str.length; i++) {
                        if (i == str.length - 1) {
                            s += number[i] + " ";
                        } else {
                            s += number[i] + " << ";
                        }
                    }

                    textArea.setText(s);
                } else {
                    textArea.setText("亲，你都没有输入怎样排序呢？请输入整数！！");
                }

            }
        });

        //退出程序响应
        bQuit.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }

        });
    }

    //主程序入口
    public static void main(String agrs[]) {

        IntSortUI app = new IntSortUI();

        //设置可以关闭窗口
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //改变小图标
        Image icon = Toolkit.getDefaultToolkit().getImage("apple.png");
        app.setIconImage(icon);

        //窗口出现在屏幕的位置
        app.setLocation(400, 150);

        //窗口的大小
        app.setSize(475, 375);

        //不能被用户改变窗口的大小
        app.setResizable(false);

        //窗口是可见的
        app.setVisible(true);
    }
}