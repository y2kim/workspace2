package snakegame;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Snake extends JFrame {
   Snake self = this;
   public Snake() {
      initUI();
   }

   private void initUI() {

      add(new Board(self));

      setResizable(false);
      pack();

      setTitle("Bunnake");
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }

   public static void main(String[] args) {

      EventQueue.invokeLater(() -> {
         JFrame ex = new Snake();
         ex.setVisible(true);
      });
   }
}