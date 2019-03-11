/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proy_Progra2;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import static java.awt.Frame.CROSSHAIR_CURSOR;
import static java.awt.Frame.DEFAULT_CURSOR;
import static java.awt.Frame.HAND_CURSOR;
import static java.awt.Frame.MOVE_CURSOR;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Denuar
 */
public class Lienzo extends JPanel implements MouseListener, MouseMotionListener, ActionListener{//The listener interface for receiving "interesting" mouse events (press, release, click, enter, and exit) on a component. (To track mouse moves and mouse drags, use the MouseMotionListener.)
    ArrayList <Circulo> listaC = new ArrayList();//lista de circulos que voy a guardar en el lienzo
    
    ArrayList <Linea> lineas = new ArrayList();
    Point p1=null, p2=null;
    
    JLabel jl_estadoMouse = new JLabel("Estado Memoria");
    JLabel jl_coordenadas = new JLabel();
    int posX, posY, posX2, posY2;
    
    JButton btn_mover = new JButton("Mover");
    JButton btn_gImagen  = new JButton("Guardar Captura");
    JComboBox fuentes = new JComboBox();
    JComboBox colores = new JComboBox();
    
    Circulo cir= new Circulo();
    
    //mover circulo:
    Circulo cirMov = new Circulo();
    int icir, mover=0, dibujar=0;
    
    int posicion=0;
    int estadoMouse=0;
    
    //constructor:
    public Lienzo() {
        setLayout(null);//IMPORTANTE!!  Habilita que pueda definir la posicion de los elementos en el panel
        //setListaC(new ArrayList <>());
        addMouseListener(this);//Implementa el metodo que va a detectar cuando haga click en el panel
        jl_estadoMouse.setBounds(0, 100, 140, 20);//OJO::::::(0, 680, 140, 20)
        jl_coordenadas.setBounds(0,20,100,40);
        jl_coordenadas.setVisible(true);
        add(jl_coordenadas);
        add(jl_estadoMouse);
        addMouseMotionListener(this);
        
        //agregar botones
        //boton mover:
        btn_mover.setBounds(0, 40, 100, 20);        
        btn_mover.setVisible(true);
        btn_mover.setCursor(new Cursor(HAND_CURSOR));
        add(btn_mover);
        btn_mover.addActionListener(this);
        
        //boton Guardar Imagen:
        btn_gImagen.setBounds(0, 80, 130, 20);        
        btn_gImagen.setVisible(true);
        btn_gImagen.setCursor(new Cursor(HAND_CURSOR));
        add(btn_gImagen);
        btn_gImagen.addActionListener(this);
        
        //comboBox Fuentes:
        fuentes.setBounds(0, 140, 120, 20);
        fuentes.setVisible(true);
        fuentes.setCursor(new Cursor(HAND_CURSOR));
        add(fuentes);
        fuentes.addItem("Times new Roman");
        fuentes.addItem("Agency FB");
        fuentes.addItem("Courier");
        fuentes.addActionListener(this);
        
        
        //comboBox colores:
        colores.setBounds(0, 180, 80, 20);
        colores.setVisible(true);
        colores.setCursor(new Cursor(HAND_CURSOR));
        add(colores);
        colores.addItem("Negro");
        colores.addItem("Rojo");
        colores.addItem("Azul");
        colores.addItem("Verde");
        colores.addItem("Amarillo");
        colores.addActionListener(this);
        
    }//fin constructor

    public ArrayList<Circulo> getListaC() {
        return listaC;
    }

    public void setListaC(ArrayList<Circulo> listaC) {
        this.listaC = listaC;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        if (e.getButton() == 1){// 1 click izquierdo, 2 scroll, 3 click derecho
//            getListaC().add(new Circulo(e.getX(), e.getY()));
//            repaint();//cada vez que agrego un circulo a la lista
//        }
if (e.getButton()==2){
    System.out.println("scroll");
    for (int i = 0; i < listaC.size(); i++) {
        if (new Rectangle(Math.abs(listaC.get(i).getX1()), Math.abs(listaC.get(i).getY1()), 100, 100).contains(e.getPoint())){
            System.out.println("punto: " + e.getPoint());
        }
    }
}
        if (e.getButton()==3){//si hago click derecho..
            //UNDO:
            System.out.println("Mouse Clicked");
//            listaC.remove(listaC.size()-1);
//            System.out.println("tamaño listac:" + listaC.size());
//            repaint();

            for (int i = 0; i < listaC.size(); i++) {
                if (new Rectangle(listaC.get(i).getX1(), listaC.get(i).getY1(), 100, 100).contains(e.getPoint())){
                    if (p1==null){
                        p1 = new Point (listaC.get(i).getX1(), listaC.get(i).getY1());//p1 = punto inicial del circulo
                        System.out.println("p1 ready: " + p1);
                    }
                    else{
                        p2 = new Point (listaC.get(i).getX1(), listaC.get(i).getY1());
                        System.out.println("p2 ready: " + p2);
                        this.lineas.add( new Linea(p1.x, p1.y, p2.x, p2.y));
                        repaint();
                        p1=null;
                        p2=null;
                    }
                }
            }
        }//fick click derecho
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Mouse Pressed");
        if (mover==0){
            if (e.getButton() == 1){// 1 click izquierdo, 2 scroll, 3 click derecho
                posX= e.getX();
                posY = e.getY();            
            }

            estadoMouse=2;
        }
        
        //mover circulos:
        System.out.println("");
        if (mover==1){
            int in =0;
            for (int i = 0; i < listaC.size(); i++) {
                if (new Rectangle(Math.abs(listaC.get(i).getX1()), Math.abs(listaC.get(i).getY1()), 100, 100).contains(e.getPoint())){
                    cirMov = listaC.get(i);
                    icir=in;
                    break;                
                }
                in++;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.setCursor(new Cursor(DEFAULT_CURSOR));
        if (estadoMouse==1){
            String nombre = JOptionPane.showInputDialog(this, "Ingrese nombre: ");
            nombre+="";
            getListaC().add(new Circulo(posX, posY, posX2, posY2, nombre, fuentes.getSelectedIndex(), colores.getSelectedIndex()));
        }
        repaint();//cada vez que agrego un circulo a la lista
        jl_estadoMouse.setText("Memoria Libre");
        System.out.println("Mouse released");
        estadoMouse=0;
        
        //mover circulos:
        //reset:
        cirMov=null;
        icir=-1;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.setBackground(Color.lightGray);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setBackground(Color.white);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (mover==0){
            dibujar=1;
            this.setCursor(new Cursor(CROSSHAIR_CURSOR));
            
            posX2 = Math.abs(e.getX()-posX);
            posY2 = Math.abs(e.getY()-posY);
            cir = new Circulo(posX, posY, posX2, posY2, "" , fuentes.getSelectedIndex(), colores.getSelectedIndex());            
            System.out.println("Mouse Dragged");
            repaint();//LLama al metodo de pintar de este componente        
            jl_estadoMouse.setText("Memoria Ocupada");
            jl_coordenadas.setLocation(e.getX(), e.getY());
            jl_coordenadas.setText(e.getX() + "," + (e.getY()));
            estadoMouse=1;
        }
        
        //mover circulo:
        if (mover==1){
            dibujar=0;            
            if (cirMov != null){
//                System.out.println("icir: " + icir);
                this.setCursor(new Cursor(MOVE_CURSOR));
                this.listaC.set(icir, new Circulo(e.getX(), e.getY(), listaC.get(icir).getX2(), listaC.get(icir).getY2(), listaC.get(icir).getNombre(), listaC.get(icir).getFuente(), listaC.get(icir).getColor()));
                
                
                //mover lineas:
                int ie = 0;
                for (int i = 0; i < lineas.size(); i++) {
                    //caso 1 (cuando selecciono el punto 1):
                    if (new Rectangle(listaC.get(i).getX1(), listaC.get(i).getY1(), 100, 100).contains(e.getPoint())){
                        this.lineas.set(i, new Linea(e.getX(), e.getY(), lineas.get(i).getX2(), lineas.get(i).getY2()));
                        System.out.println("caso1");
                        System.out.println("X1: " + lineas.get(i).getX1());
                        System.out.println("Y1: " + lineas.get(i).getY1());
                        System.out.println("X2: " + lineas.get(i).getX2());
                        System.out.println("Y2: " + lineas.get(i).getY2());
                    }
                    //caso 2 (cuando selecciono el punto 2):
                    else if (new Rectangle(listaC.get(i).getX2(), listaC.get(i).getY2(), 100, 100).contains(e.getPoint())){
                        this.lineas.set(i, new Linea(listaC.get(i).getX1(), listaC.get(i).getY1(), e.getX(), e.getY()));
                        System.out.println("caso2");
                        System.out.println("X1: " + lineas.get(i).getX1());
                        System.out.println("Y1: " + lineas.get(i).getY1());
                        System.out.println("X2: " + lineas.get(i).getX2());
                        System.out.println("Y2: " + lineas.get(i).getY2());
                    }
                    ie++;
                }
            }
        }
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        jl_coordenadas.setLocation(e.getX(), e.getY());
        jl_coordenadas.setText(e.getX() + "," + (e.getY()));        
    }
    
    //pintar Figuras:
    @Override
    public void paint(Graphics g){
        super.paint(g);//le envio el objeto g al metodo paint de la superClase //refresca de forma optima cada vez que pintamos algo en el lienzo
        for (int i = 0; i < listaC.size(); i++) {
            listaC.get(i).pintar(g);
        }
        if (dibujar==1){
            cir.pintar(g);
        }
        
        for (int i = 0; i < lineas.size(); i++) {
            lineas.get(i).pintar(g);
            
        }
    }//fin pintar figuras

    //escuchar Acciones de los botones:
    @Override
    public void actionPerformed(ActionEvent e) {//metodo abstracto botones
        //boton mover:
        if(e.getSource()==btn_mover){
            if (mover==0){
                mover=1;
            }
            else if (mover==1){
                mover=0;
            }
            System.out.println("mover: " + mover);
        }
        
        //boton Guardar Imagen:
        if (e.getSource()==btn_gImagen){
            String nombreImagen = JOptionPane.showInputDialog(this, "Ingrese Nombre de la Captura a guardar:");
            System.out.println("imagen guardada");
            guardarImagen(nombreImagen , "png");
        }
        
        //comboBox Fuentes:
        if (e.getSource() == fuentes){
            System.out.println("fuentes: " + fuentes.getSelectedItem().toString());
        }
    }//fin escuchar acciones de los botones
    
    //Guardar captura
    public void guardarImagen(String nombre,String extension) {
        BufferedImage image = new BufferedImage(getWidth(),getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();
        paint(g2);
        try{
                ImageIO.write(image, extension, new File(nombre+"."+extension));
        } catch (Exception e) {
                e.printStackTrace();
        }
    }//fin guardar captura
}
