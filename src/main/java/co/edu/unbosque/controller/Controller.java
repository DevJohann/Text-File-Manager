package co.edu.unbosque.controller;

import co.edu.unbosque.model.*;
import co.edu.unbosque.view.MainFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class Controller implements ActionListener{

    private MainFrame frame;
    private FileAdmin FA;
    private String actualFile = "";
    public Controller() {
        initComponents();
        setListeners();
        execute();
    }
    /**
     * Inicializa los componentes
     */
    public void initComponents(){
        frame = new MainFrame();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        FA = new FileAdmin();
    }
    /**
     * Agrega los listeners a cada boton de la vista
     */
    public void setListeners(){
        frame.getMP().getCreateFileBtn().addActionListener(this);
        frame.getMP().getCreateFileBtn().setActionCommand("createFileEvent");
        
        frame.getMP().getOpenFileBtn().addActionListener(this);
        frame.getMP().getOpenFileBtn().setActionCommand("openFileEvent");
        
        frame.getMP().getWriteFileBtn().addActionListener(this);
        frame.getMP().getWriteFileBtn().setActionCommand("writeFileEvent");
        
        frame.getMP().getCleanFileBtn().addActionListener(this);
        frame.getMP().getCleanFileBtn().setActionCommand("cleanFileEvent");
    }
    /**
     * Deprecated
     */
    public void execute(){
       
    }

    /**
     * Administrador de los eventos de los botones
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "createFileEvent":
                String src = JOptionPane.showInputDialog("El archivo quedará guardado en TextFileManager/files\nIngrese el nombre del archivo (y su extensión[txt]):");
                FA.createFile(src);
                break;
            case "openFileEvent":
                actualFile = JOptionPane.showInputDialog("Su archivo debe estar ubicado en la carpeta files\nIngrese el nombre del archivo");
                FA.readDataFromFile(actualFile, frame.getMP().getFileContentArea());
                //frame.getMP().getFileContentArea().setText(FA.readDataFromFile(actualFile));
                frame.getMP().getFileContentArea().update(frame.getMP().getFileContentArea().getGraphics());
                break;
            case "writeFileEvent":
                //String data = JOptionPane.showInputDialog("Ingrese el texto a añadir:");
                if(actualFile.equals("")){
                   JOptionPane.showMessageDialog(null, "Primero debe abrir un archivo");
                   break;
                }
                FA.writeDataToFile(actualFile, frame.getMP().getFileContentArea());
                break;
            case "cleanFileEvent":if(actualFile.equals("")){
                   JOptionPane.showMessageDialog(null, "Primero debe abrir un archivo");
                   break;
                }
                FA.cleanData(actualFile, frame.getMP().getFileContentArea());
                break;
        }
    }
    
    
}
