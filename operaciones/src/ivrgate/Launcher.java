package ivrgate;


import ivrgate.servicio.IvrQueue;
import nsc.gui.cg.MainFrame;

import org.springframework.context.support.ClassPathXmlApplicationContext;
  
public class Launcher {
private MainFrame mainframei;
private IvrQueue servicioIVR;
    public void launch() {
        String[] contextPaths = new String[] {"ivrgate/config/gui-context.xml",
        		 							  "config/servicios.xml",
        		 							  "config/ivr-gui.xml"
        									         									  
        									 };
  
        ClassPathXmlApplicationContext cl=new ClassPathXmlApplicationContext(contextPaths);
        mainframei=(MainFrame)cl.getBean("mainFrameI");
        mainframei.setTitle("HAC - IVRGATE IMAGENES:   IvrQueue");
        mainframei.repaint();
        servicioIVR=(IvrQueue) cl.getBean("ivrQueue");
        servicioIVR.start();
        
    }
}