import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Ventana extends JFrame implements ActionListener {
    int ancho, alto;
    JTextField txtEdad;
    JButton btnAceptar;
    JPanel panelBotones;

    public Ventana() {
        ancho = 400;
        alto = 200;

        setTitle("Validar Edad");
        setSize(ancho, alto);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelBotones = new JPanel();

        txtEdad = new JTextField(10);
        btnAceptar = new JButton("Validar edad");

        btnAceptar.addActionListener(this);

        panelBotones.add(txtEdad);
        panelBotones.add(btnAceptar);

        add(panelBotones);
    }

    public static void main(String[] args) {
        Ventana v = new Ventana();
        v.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            // 1. Obtener texto
            String edadTexto = txtEdad.getText();

            // 2. Convertir a número
            int edad = Integer.parseInt(edadTexto);

            // 3. Validar edad
            if (edad >= 18) {
                JOptionPane.showMessageDialog(this, "Eres mayor de edad");
            } else {
                JOptionPane.showMessageDialog(this, "Eres menor de edad");
            }

        } catch (NumberFormatException ex) {
            // 4. Manejo de error (input inválido)
            JOptionPane.showMessageDialog(this, "Por favor ingresa un número válido");
        }
    }
}   