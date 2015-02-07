/* Copyright 2014 Zitian Li
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
 * Author: Zitian Li
 * Email: me@zitianli.com
 * License: Apache License, Version 2.0
 */
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
class Notepad extends Frame implements ItemListener, WindowListener {
    public static Notepad self;
    public static JTextArea text_area;
    private Checkbox auto_wrap;
    private MenuBar menu_bar;
    private Panel panel;
    private Choice font_choice, size_choice;
    public Notepad(String title) {
        self = this;
        setTitle(title);

        addWindowListener(this);
        setBounds(0, 0, 640, 480);
        text_area = new JTextArea();
        add(new JScrollPane(text_area), BorderLayout.CENTER);

        menu_bar = new MenuBar();
        setMenuBar(menu_bar);

        Menu file_menu = new Menu("文件");
        menu_bar.add(file_menu);
        MenuItem open = new MenuItem("打开");
        file_menu.add(open);
        open.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileFilter(new FileNameExtensionFilter("文本文件", "txt"));
                int returnVal = chooser.showOpenDialog(Notepad.self);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    int length = (int)chooser.getSelectedFile().length(), i, j;
                    byte[] data = null;
                    try {
                        FileInputStream fis = new FileInputStream(chooser.getSelectedFile());
                        data = new byte[length];
                        for(j = 0; (i = fis.read()) != -1 && j < length; j++)
                        data[j] = (byte)i;
                        if(j != length) {
                            System.err.println("I/O Error.");
                            System.exit(1);
                        }
                        fis.close();
                    } catch (IOException exception) {
                        System.exit(1);
                    }
                    Notepad.text_area.setText(new String(data));
                }
            }
        });
        MenuItem save = new MenuItem("保存");
        file_menu.add(save);
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileFilter(new FileNameExtensionFilter("文本文件", "txt"));
                int returnVal = chooser.showOpenDialog(Notepad.self);
                String s = Notepad.text_area.getText();
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                        FileOutputStream fos = new FileOutputStream(chooser.getSelectedFile());
                        fos.write(s.getBytes());
                        fos.close();
                    } catch (IOException exception) {
                        System.exit(1);
                    }
                }
            }
        });

        Menu about_menu = new Menu("关于");
        menu_bar.add(about_menu);
        MenuItem about = new MenuItem("关于");
        about_menu.add(about);
        about.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "关于此软件：\n作者：李子天\n电子邮件：me@zitianli.com", "关于此软件", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        panel = new Panel(new FlowLayout(FlowLayout.LEFT));
        add(panel, BorderLayout.NORTH);
        panel.add(new Label("字体"));
        font_choice = new Choice();
        panel.add(font_choice);
        panel.add(new Label("字号"));
        size_choice = new Choice();
        panel.add(size_choice);
        auto_wrap = new Checkbox("自动换行", false);
        panel.add(auto_wrap);
        auto_wrap.addItemListener(this);

        font_choice.addItemListener(this);
        for(String i : GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames())
            font_choice.add(i);

        size_choice.addItemListener(this);
        for(int i : new int[]{4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 22, 24, 26, 28, 32, 48, 64, 72, 80, 96, 128})
            size_choice.add(String.valueOf(i));
        size_choice.select("12");

        itemStateChanged(null);
    }
    public static void main(String[] args) {
        new Notepad("Notepad").setVisible(true);
    }
    public void itemStateChanged(ItemEvent e) {
        text_area.setFont(new Font(font_choice.getSelectedItem(), Font.PLAIN, Integer.parseInt(size_choice.getSelectedItem())));
        text_area.setLineWrap(auto_wrap.getState());
    }
    public void windowActivated(WindowEvent e) {}
    public void windowClosed(WindowEvent e) {}
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
    public void windowDeactivated(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowOpened(WindowEvent e) {}
}
