// Juan Andres Romero Trujillo
package com.example.calculadoraa;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Calculadora extends Activity implements View.OnClickListener {

    Button[] btnDigitos = new Button[10];

    boolean pintarPunto = true;

    EditText pantalla;

    double op1, op2, res;

    String operacion = "";

    Button btnSuma;
    Button btnResta;
    Button btnMultiplicacion;
    Button btnDivision;
    Button btnPunto;
    Button btnIgual;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);

        LinearLayout panelPrincipal = new LinearLayout(this);
        panelPrincipal.setBackgroundColor(Color.BLACK);
        panelPrincipal.setOrientation(LinearLayout.VERTICAL);

        LinearLayout panelPantalla = new LinearLayout(this);
        panelPantalla.setOrientation(LinearLayout.VERTICAL);
        panelPantalla.setBackgroundColor(Color.GRAY);
        panelPantalla.setMinimumHeight(200);

        TextView cuadro = new TextView(this);
        cuadro.setBackgroundColor(Color.BLUE);
        cuadro.setText("CALCULADORA");
        cuadro.setTextColor(Color.WHITE);
        cuadro.setMinimumHeight(50);

        pantalla = new EditText(this);
        pantalla.setTextColor(Color.WHITE);
        pantalla.setTextSize(40);
        pantalla.setMaxLines(1);
        pantalla.setTextAlignment(EditText.TEXT_ALIGNMENT_TEXT_END);

        panelPantalla.addView(cuadro);
        panelPantalla.addView(pantalla);

        LinearLayout panelControles = new LinearLayout(this);
        panelControles.setBackgroundColor(Color.RED);

        LinearLayout panelBotones1 = new LinearLayout(this);
        panelBotones1.setBackgroundColor(Color.WHITE);
        panelBotones1.setOrientation(LinearLayout.VERTICAL);

        LinearLayout panelBotones2 = new LinearLayout(this);
        panelBotones2.setOrientation(LinearLayout.VERTICAL);
        panelBotones2.setBackgroundColor(Color.GREEN);

        LinearLayout li1 = new LinearLayout(this);
        LinearLayout li2 = new LinearLayout(this);
        LinearLayout li3 = new LinearLayout(this);
        LinearLayout li4 = new LinearLayout(this);

        // BOTONES NUMÉRICOS
        for (int i = 0; i <= 9; i++) {

            btnDigitos[i] = new Button(this);

            btnDigitos[i].setText(String.valueOf(i));

            btnDigitos[i].setOnClickListener(this);

            switch (i) {

                case 0:
                    li4.addView(btnDigitos[i]);
                    break;

                case 1:
                case 2:
                case 3:
                    li3.addView(btnDigitos[i]);
                    break;

                case 4:
                case 5:
                case 6:
                    li2.addView(btnDigitos[i]);
                    break;

                case 7:
                case 8:
                case 9:
                    li1.addView(btnDigitos[i]);
                    break;
            }
        }

        // BOTÓN PUNTO
        btnPunto = new Button(this);
        btnPunto.setText(".");
        btnPunto.setOnClickListener(this);

        // BOTÓN IGUAL
        btnIgual = new Button(this);
        btnIgual.setText("=");
        btnIgual.setOnClickListener(this);

        li4.addView(btnPunto);
        li4.addView(btnIgual);

        // BOTÓN SUMA
        btnSuma = new Button(this);
        btnSuma.setText("+");
        btnSuma.setOnClickListener(this);

        // BOTÓN RESTA
        btnResta = new Button(this);
        btnResta.setText("-");
        btnResta.setOnClickListener(this);

        // BOTÓN MULTIPLICACIÓN
        btnMultiplicacion = new Button(this);
        btnMultiplicacion.setText("*");
        btnMultiplicacion.setOnClickListener(this);

        // BOTÓN DIVISIÓN
        btnDivision = new Button(this);
        btnDivision.setText("/");
        btnDivision.setOnClickListener(this);

        panelBotones1.addView(li1);
        panelBotones1.addView(li2);
        panelBotones1.addView(li3);
        panelBotones1.addView(li4);

        panelBotones2.addView(btnSuma);
        panelBotones2.addView(btnResta);
        panelBotones2.addView(btnMultiplicacion);
        panelBotones2.addView(btnDivision);

        panelPrincipal.addView(panelPantalla);

        panelControles.addView(panelBotones1);
        panelControles.addView(panelBotones2);

        panelPrincipal.addView(panelControles);

        setContentView(panelPrincipal);
    }

    @Override
    public void onClick(View v) {

        // NÚMEROS
        for (int i = 0; i <= 9; i++) {

            if (v.equals(btnDigitos[i])) {

                pantalla.setText(
                        pantalla.getText() + String.valueOf(i)
                );
            }
        }

        // PUNTO DECIMAL
        if (v.equals(btnPunto)) {

            if (pintarPunto) {

                pantalla.setText(
                        pantalla.getText() + "."
                );

                pintarPunto = false;
            }
        }

        // SUMA
        if (v.equals(btnSuma)) {
            guardarOperacion("+");
        }

        // RESTA
        if (v.equals(btnResta)) {
            guardarOperacion("-");
        }

        // MULTIPLICACIÓN
        if (v.equals(btnMultiplicacion)) {
            guardarOperacion("*");
        }

        // DIVISIÓN
        if (v.equals(btnDivision)) {
            guardarOperacion("/");
        }

        // IGUAL
        if (v.equals(btnIgual)) {

            if (pantalla.getText().toString().equals("")) {
                return;
            }

            pintarPunto = true;

            op2 = Double.parseDouble(
                    pantalla.getText().toString()
            );

            if (operacion.equals("+")) {
                res = op1 + op2;
            }

            if (operacion.equals("-")) {
                res = op1 - op2;
            }

            if (operacion.equals("*")) {
                res = op1 * op2;
            }

            if (operacion.equals("/")) {

                if (op2 == 0) {

                    pantalla.setText("Error");

                    return;

                } else {

                    res = op1 / op2;
                }
            }

            pantalla.setText(String.valueOf(res));
        }
    }

    public void guardarOperacion(String op) {

        if (pantalla.getText().toString().equals("")) {
            return;
        }

        pintarPunto = true;

        op1 = Double.parseDouble(
                pantalla.getText().toString()
        );

        operacion = op;

        pantalla.setText("");
    }
}
