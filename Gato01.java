import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Gato01 extends JFrame implements ActionListener {

    JButton botones[] = new JButton[9];
    JButton btnReinciar;
    boolean turnoX = true;
    Font fuente = new Font("Arial", 1, 60);
    JPanel pJuego, pOpciones;
    String letra;

    public Gato01() {
        setTitle("Gato");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pJuego = new JPanel(new GridLayout(3, 3));

        for (int i = 0; i < botones.length; i++) {
            botones[i] = new JButton("");
            botones[i].setFont(fuente);
            botones[i].addActionListener(this);
            pJuego.add(botones[i]);
        }

        add(pJuego, BorderLayout.CENTER);

        btnReinciar = new JButton("Reiniciar Juego");
        pOpciones = new JPanel();

        btnReinciar.addActionListener(e -> {
            for (int i = 0; i < botones.length; i++) {
                botones[i].setText("");
                botones[i].setEnabled(true);
            }
            turnoX = true;
        });

        pOpciones.add(btnReinciar);
        add(pOpciones, BorderLayout.SOUTH);
    }

    public static void main(String a[]) {
        new Gato01().setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        letra = turnoX ? "X" : "O";
        turnoX = !turnoX;

        for (int i = 0; i < botones.length; i++) {
            if (e.getSource() == botones[i]) {
                botones[i].setText(letra);
                botones[i].setEnabled(false);
            }
        }

        // primero validar ganador
        if (hayGanador()) {
            JOptionPane.showMessageDialog(this, "Ganó " + letra);
            desactivarTablero();
            return;
        }

        // luego validar empate (solo si no ganó nadie)
        if (hayEmpate()) {
            JOptionPane.showMessageDialog(this, "Empate");
            desactivarTablero();
        }
    }

    public boolean hayGanador() {

        String b0 = botones[0].getText();
        String b1 = botones[1].getText();
        String b2 = botones[2].getText();
        String b3 = botones[3].getText();
        String b4 = botones[4].getText();
        String b5 = botones[5].getText();
        String b6 = botones[6].getText();
        String b7 = botones[7].getText();
        String b8 = botones[8].getText();

        if (!b0.equals("") && b0.equals(b1) && b1.equals(b2)) return true;
        if (!b3.equals("") && b3.equals(b4) && b4.equals(b5)) return true;
        if (!b6.equals("") && b6.equals(b7) && b7.equals(b8)) return true;

        if (!b0.equals("") && b0.equals(b3) && b3.equals(b6)) return true;
        if (!b1.equals("") && b1.equals(b4) && b4.equals(b7)) return true;
        if (!b2.equals("") && b2.equals(b5) && b5.equals(b8)) return true;

        if (!b0.equals("") && b0.equals(b4) && b4.equals(b8)) return true;
        if (!b2.equals("") && b2.equals(b4) && b4.equals(b6)) return true;

        return false;
    }

    public boolean hayEmpate() {
        for (int i = 0; i < botones.length; i++) {
            if (botones[i].getText().equals("")) return false;
        }
        return true;
    }

    public void desactivarTablero() {
        for (int i = 0; i < botones.length; i++) {
            botones[i].setEnabled(false);
        }
    }
}