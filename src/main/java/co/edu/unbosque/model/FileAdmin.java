package co.edu.unbosque.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class FileAdmin {

    public FileAdmin() {
    }
    /**
     * Crea un archivo nuevo, luego prueba que si exista con el PrintWriter
     * @param filename 
     */
    public void createFile(String filename){
        //Agregar con "carpeta/nombre.txt"
        File newFile = new File("files/" + filename);
        try{
            PrintWriter PW = new PrintWriter(newFile);
            //PW.write("test");
            PW.close();
            //System.out.println("Archivo creado correctamente");
            JOptionPane.showMessageDialog(null, "Archivo creado");
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
    /**
     * Crea el Stream, luego usando una propiedad del JTextArea carga lo escrito en el BufferedWriter y este lo escribe
     * al archivo, luego se cierra el Stream y se recarga el JTextArea
     * @param src
     * @param target 
     */
    public void writeDataToFile(String src, JTextArea target){
        try{
            /*
            PrintWriter PW = new PrintWriter(new FileWriter("files/" + src, true));
            PW.write(data + "\n");
            PW.close();
            */
            BufferedWriter BW = new BufferedWriter(new FileWriter("files/" + src));
            target.write(BW);
            BW.close();
            //target.setText("");
            target.requestFocus();
            JOptionPane.showMessageDialog(null, "Cambios realizados");
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException ex){
            ex.printStackTrace();
        }
       
    }
    /**
     * Se requiere el TextArea para cargar directamente los datos ahí con target.read();
     * Se crea el Stream, luego se usa una propiedad del JTextArea para cargar lo del archivo en el TextArea,
     * luego se recarga el TextArea
     * 
     * @param src
     * @param target
     * @return 
     */
    public void readDataFromFile(String src, JTextArea target){
        try{
            System.out.println("Leyendo archivo");
            BufferedReader BR = new BufferedReader(new FileReader("files/" + src));
            target.read(BR, null);
            BR.close();
            target.requestFocus();
          
            System.out.println("Finalizado");
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    /**
     * Antes confirma la eleccion, abre un Stream, luego borra el contenido del JTextArea 
     * y carga ese contenido en blanco al Stream que lo escribe al archivo.
     * @param src
     * @param target 
     */
    public void cleanData(String src, JTextArea target){
        try{
            int allowed = JOptionPane.showConfirmDialog(null, "Está seguro que quiere eliminar todo el contenido?");
            //System.out.println(allowed);
            if(allowed == 0){
                BufferedWriter BW = new BufferedWriter(new FileWriter("files/" + src));
                target.setText("");
                target.write(BW);
                JOptionPane.showMessageDialog(null, "Contenido eliminado");
            }else{
                return;
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
