/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proy_Progra2;

import static java.awt.Frame.MAXIMIZED_BOTH;
import javax.swing.JFrame;

/**
 *
 * @author Denuar
 */
public class proy_Progra2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame ventana = new JFrame();
        Lienzo objLienzo = new Lienzo();
        ventana.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);//cierra la ejecucion del programa en memoria
        ventana.setSize(480, 480);//Fija tamaño de la ventana, tamaño maximo = 1379*730
//        ventana.setExtendedState(MAXIMIZED_BOTH);//maximiza la ventana al tamaño del monitor
        ventana.setResizable(false);//Evito que el usuario modifique el tamaño de la ventana
        ventana.add(objLienzo);
        ventana.setVisible(true);
        
    }
    
}
