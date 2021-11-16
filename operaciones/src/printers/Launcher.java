package printers;


import nsc.gui.cg.MainFrame;


import org.springframework.context.support.ClassPathXmlApplicationContext;

import printers.servicio.LeerPrinterQueue;
  
public class Launcher {
private MainFrame mainframe;
private LeerPrinterQueue servicioPrint;


    public void launch() {
    	
    	
    
    	
        String[] contextPaths = new String[] {"config/servicios.xml",
        									  "config/mail-gui.xml",
        									  "printers/config/gui-context.xml"
        								        									 
        									 };
    
        ClassPathXmlApplicationContext cl=new ClassPathXmlApplicationContext(contextPaths);
       
        mainframe=(MainFrame)cl.getBean("mainFrame");
        mainframe.setTitle("HAC - IMAGENES:   Leer PrinterQueue");
        mainframe.repaint();
        servicioPrint=(LeerPrinterQueue) cl.getBean("leerPrinterQueue");
        servicioPrint.start();
  
        
    }
}