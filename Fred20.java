import java.awt.Color;
import java.awt.GridLayout;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Fred20 extends JFrame {

    JButton casillas[] = new JButton[4];
    int[] secuencia = new int[6];
    Random r = new Random();

    // Colores asignados a cada botón
    Color[] colores = { Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW };

    public Fred20() {
        setTitle("Fred20");
        setSize(300, 300);
        setDefaultCloseOperation(3);
        setLayout(new GridLayout(2, 2));

        for (int i = 0; i < casillas.length; i++) {
            int index = i; // necesario para lambda

            casillas[i] = new JButton();
            casillas[i].setBackground(Color.LIGHT_GRAY);

            // Evento al hacer clic
            casillas[i].addActionListener(e -> encenderBoton(index));

            add(casillas[i]);
        }

        crearSecuencia();
        mostrarSecuencia();
    }

    public void crearSecuencia() {
        for (int i = 0; i < secuencia.length; i++) {
            secuencia[i] = r.nextInt(4);
        }

        for (int x : secuencia) {
            System.out.print(x + " ");
        }
    }

    public void mostrarSecuencia() {

        Thread hilo = new Thread(() -> {

            for (int i = 0; i < secuencia.length; i++) {
                int indice = secuencia[i];
                encenderBoton(indice);

                try {
                    Thread.sleep(800);
                } catch (Exception e) {
                }
            }

        });

        hilo.start();
    }

    // Método reutilizable para encender cualquier botón
    public void encenderBoton(int index) {

        new Thread(() -> {
            try {
                casillas[index].setBackground(colores[index]);
                Thread.sleep(600);
                casillas[index].setBackground(Color.LIGHT_GRAY);
            } catch (Exception e) {
            }
        }).start();
    }

    public static void main(String[] args) {
        Fred20 f = new Fred20();
        f.setVisible(true);
    }
}