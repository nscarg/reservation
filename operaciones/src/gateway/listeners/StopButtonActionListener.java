package gateway.listeners; 

import gateway.servicio.GatewayQueue;

import java.awt.event.ActionEvent;

import nsc.gui.cg.button.ListTableActionListener;



public class StopButtonActionListener 
             extends ListTableActionListener {
	
	GatewayQueue servicioGate;
	
	public GatewayQueue getServicioGate() {
	        return this.servicioGate;
	}
	    
	public void setServicioGate (GatewayQueue servicioGate_p) {
	        this.servicioGate = servicioGate_p;
			
	}
	
    public void actionPerformed(ActionEvent e){
       	servicioGate.setFlag("");
    	servicioGate.stop();
    	System.exit(0);
		
    }
	
	
	

}