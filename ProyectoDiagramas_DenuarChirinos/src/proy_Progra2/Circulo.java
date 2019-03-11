/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proy_Progra2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author Denuar
 */
public class Circulo {
    private int x1=0, y1=0, diametro = 60, x2=0, y2=0, fuente=0, color=0;
    private String nombre="";
    
    public Circulo() {
    }

    public Circulo(int x1, int y1, int x2, int y2, String nombre, int fuente, int color) {//constructor que recibe las coordenadas x,y
        setX1(x1);
        setY1(y1);
        setX2(x2);
        setY2(y2);
        this.nombre = nombre;
        this.fuente = fuente;
        this.color = color;
    }    

    public int getFuente() {
        return fuente;
    }

    public void setFuente(int fuente) {
        this.fuente = fuente;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getDiametro() {
        return diametro;
    }

    public void setDiametro(int diametro) {
        this.diametro = diametro;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

   
    public void pintar(Graphics g){
        if (color==0){
            g.setColor(Color.black);
        }
        else if (color==1){
            g.setColor(Color.red);
        }
        else if (color==2){
            g.setColor(Color.blue);
        }
        else if (color==3){
            g.setColor(Color.green);
        }
        else if (color==4){
            g.setColor(Color.yellow);
        } 
        
        if (fuente==0){
            g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        }
        else if (fuente==1){
            g.setFont(new Font("Agency FB", Font.PLAIN, 20));
        }
        else if (fuente==2){
            g.setFont(new Font("Courier", Font.PLAIN, 20));
        }
        else if (fuente==3){
            g.setFont(new Font("Dialog", Font.PLAIN, 20));
        }
        else if (fuente==4){
            g.setFont(new Font("Monospaced", Font.PLAIN, 20));
        }

        g.drawOval(getX1(), getY1(), getX2(), getY2());
        g.drawString(nombre, x1 + x2/2, y1 + y2/2);
    }
    
    public void escribir(Graphics g){

    }
}
