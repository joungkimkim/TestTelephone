package TelPhoneTest;

import javax.naming.Name;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
public class View {
    JFrame jframe = new JFrame();
    JPanel jpanel = new JPanel();
    JTextField t1 = new JTextField();
    JTextField t2 = new JTextField();
    JTextField t3 = new JTextField();
    JTextField t4 = new JTextField();
    JTextArea ta = new JTextArea();
    JButton btn1, btn2, btn3, btn4, btn5;
    JLabel ㅣ1 = new JLabel("이름 : ");
    JLabel ㅣ2 = new JLabel("주소 : ");
    JLabel ㅣ3 = new JLabel("전화번호 : ");
    JLabel ㅣ4 = new JLabel("검색내용 : ");

    View() {
        GUI_init();
    }

    public void GUI_init() {
        jframe.setTitle("회원관리");
        jframe.setBounds(50, 50, 480, 450);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setVisible(true);
        jpanel.setLayout(null);
        jframe.add(jpanel);

        t1.setBounds(75, 25, 70, 25);
        jpanel.add(t1);
        ㅣ1.setBounds(37, 21, 70, 30);
        jpanel.add(ㅣ1);

        t2.setBounds(213, 25, 70, 25);
        jpanel.add(t2);
        ㅣ2.setBounds(150, 21, 70, 30);
        jpanel.add(ㅣ2);

        t3.setBounds(352, 25, 80, 25);
        jpanel.add(t3);
        ㅣ3.setBounds(290, 21, 70, 30);
        jpanel.add(ㅣ3);

        t4.setBounds(213, 105, 80, 25);
        jpanel.add(t4);
        ㅣ4.setBounds(150, 100, 70, 30);
        jpanel.add(ㅣ4);

        JScrollPane jsp = new JScrollPane(ta);
        jsp.setBounds(23, 145, 420, 250);
        jpanel.add(jsp);

        jpanel.add(btn1 = new JButton("입력"));
        btn1.setBounds(40, 60, 80, 30);

        jpanel.add(btn2 = new JButton("출력"));
        btn2.setBounds(145, 60, 80, 30);

        jpanel.add(btn3 = new JButton("종료"));
        btn3.setBounds(250, 60, 80, 30);

        jpanel.add(btn4 = new JButton("삭제"));
        btn4.setBounds(350, 60, 80, 30);

        jpanel.add(btn5 = new JButton("검색"));
        btn5.setBounds(300, 100, 80, 30);

        Controller dao = new Controller();


        // 회원 추가
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                ta.setText("");

                String name = t1.getText();
                String addr = t2.getText();
                String tel = t3.getText();

                dao.insertMember(new Model(name, addr, tel));

                ta.append("입력 완료 \n");

                t1.setText("");
                t2.setText("");
                t3.setText("");
                t4.setText("");
            }
        });

        // 회원 목록 출력
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                ta.setText("");
                ArrayList<Model> arr = new ArrayList<Model>();
                arr = dao.readMember();

                ta.append("\t" + "name" + "\t" + "addr" + "\t" + "tel\n");
                ta.append("\t" + "------------------------------------------------------------\n");

                for (int i = 0; i < arr.size(); i++) {
                    ta.append("\t" + arr.get(i).getName() + " \t " + arr.get(i).getAddr() + " \t " + arr.get(i).getTel()
                            + "\n");
                }
            }
        });

        // 회원 수정에서 종료로 바꿈 -> 종료
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                ta.setText("");
                ta.append("프로그램을 종료합니다. \n");
                System.exit(1);
            }
        });

        // 회원 삭제
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                ta.setText("");
                String name = t1.getText();
                dao.deleteMember(name);
                ta.append("삭제 완료 \n");

                t1.setText("");
                t2.setText("");
                t3.setText("");
                t4.setText("");
            }
        });

        // 회원 검색
        btn5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                ta.setText("");
                String content = t4.getText();
                 ArrayList<Model> arr = new ArrayList<Model>();
                 arr = dao.searchMember(content);

                    ta.append(" \n");

                    ta.append("\t" + "name" + "\t" + "addr" + "\t" + "tel\n");
                    ta.append("\t" + "------------------------------------------------------------\n");


                    if (arr.isEmpty()) {

                        ta.append("\t테이블값이 비어있습니다.");
                    }
                    else{
                        for (int i = 0; i < arr.size(); i++) {
                            ta.append("\t" + arr.get(i).getName() + " \t " + arr.get(i).getAddr() + " \t " + arr.get(i).getTel()
                                    + "\n");

                            t1.setText("");
                            t2.setText("");
                            t3.setText("");
                            t4.setText("");
                        }

                    }
                }

        });
    }

}