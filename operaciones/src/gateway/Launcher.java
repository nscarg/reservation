package gateway;

import gateway.servicio.GatewayQueue;

import  org.springframework.context.support.ClassPathXmlApplicationContext;

import  nsc.gui.cg.MainFrame;
  
public class Launcher {
private MainFrame mainframeg;
private GatewayQueue servicioGate;
    public void launch() {
        String[] contextPaths = new String[] {"gateway/config/gui-context.xml",
        									  "config/servicios.xml",
        		 							  "config/gatew-gui.xml",
        									       		 							  
        									         									  
        									 };
  
        ClassPathXmlApplicationContext cl=new ClassPathXmlApplicationContext(contextPaths);
        mainframeg=(MainFrame)cl.getBean("mainFrameG");
        mainframeg.setTitle("HAC - GATEWAY IMAGENES:   GatewayQueue");
        mainframeg.repaint();
        servicioGate=(GatewayQueue) cl.getBean("gatewayQueue");
        servicioGate.start();
      
        
        
    }
}