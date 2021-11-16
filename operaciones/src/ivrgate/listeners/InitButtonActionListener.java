package ivrgate.listeners;

import ivrgate.servicio.IvrQueue;

import java.awt.event.ActionEvent;

import nsc.gui.cg.button.ListTableActionListener;

public class InitButtonActionListener extends ListTableActionListener {
	
	IvrQueue servicioIvr;
	
	public IvrQueue getServicioIvr() {
	        return this.servicioIvr;
	}
	    
	public void setServicioIvr (IvrQueue servicioIvr_p) {
	        this.servicioIvr = servicioIvr_p;
			
	}
	
    public void actionPerformed(ActionEvent e){
    	servicioIvr.setFlag("");
    	servicioIvr.start();
		
    }
}
