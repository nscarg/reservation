package printers.listeners; 

import java.awt.event.ActionEvent;
import nsc.gui.cg.button.ListTableActionListener;
import printers.servicio.LeerPrinterQueue;




public class StopButtonActionListener 
             extends ListTableActionListener {
	
	LeerPrinterQueue servicio;
	
	public LeerPrinterQueue getServicio() {
	        return this.servicio;
	}
	    
	public void setServicio (LeerPrinterQueue servicio_p) {
	        this.servicio = servicio_p;
	}
	
	
    public void actionPerformed(ActionEvent e) {
	
		servicio.stop();
		System.exit(0);
	
		
    }
}