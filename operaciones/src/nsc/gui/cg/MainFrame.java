package nsc.gui.cg;

import gateway.servicio.GatewayQueue;
import ivrgate.servicio.IvrQueue;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import printers.servicio.LeerPrinterQueue;




public class MainFrame extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4469973511874675951L;

	/* private LeerPrinterQueue servicioPrint;
	 private IvrQueue servicioIvr;
	 private GatewayQueue servicioGate;*/
		
		
		

	public void init() {
       	setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(new Dimension(400, 200));
	
        setVisible(true);
        setState(Frame.NORMAL);
      //  show();
    }
	
/*	public LeerPrinterQueue getServicioPrint() {
        return this.servicioPrint;
}
    
    public void setServicioPrint (LeerPrinterQueue servicioPrint_p) {
        this.servicioPrint = servicioPrint_p;
		
    }
	public GatewayQueue getServicioGate() {
        return this.servicioGate;
    }
    
    public void setServicioGate (GatewayQueue servicioGate_p) {
        this.servicioGate = servicioGate_p;
		
    }
    
    public void setServicioIvr (IvrQueue servicioIvr_p) {
        this.servicioIvr = servicioIvr_p;
		
    }

    public IvrQueue getServicioIvr() {
        return this.servicioIvr;
    }*/
    
    public void actionPerformed(ActionEvent e){
    /*	servicioPrint.setFlag("");
    	servicioGate.setFlag("");
    	servicioIvr.setFlag("");*/
   
    	
    	//servicioPrint.start();
	
   }
}