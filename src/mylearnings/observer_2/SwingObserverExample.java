package mylearnings.observer_2;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class SwingObserverExample {
  public static void main(String[] args) {
    JFrame jFrame = new JFrame("HeMadeMeDoIt");
    JButton button = new JButton("Should I do it?");
    jFrame.getContentPane().add(button, BorderLayout.CENTER);
    button.addActionListener(new AngelListener());
    // Function Object
    button.addActionListener(e -> System.out.println("You cannot do it, Give uP"));
    jFrame.setSize(300, 300);
    jFrame.setVisible(true);
  }

  static class AngelListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      System.out.println("Come on, do it!");
    }

  }

  // static class DevilListener implements ActionListener {

  //   @Override
  //   public void actionPerformed(ActionEvent e) {
  //     System.out.println("You cannot do it, Give UP!");
  //   }

  // }
}
