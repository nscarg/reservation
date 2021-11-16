package nsc.gui.cg.button;  


import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JTable;

public abstract class ListTableActionInOutListener 
                      implements ActionListener {
	
    protected JTable tableIn;
    protected List listIn;
	protected JTable tableOut;
    protected List listOut;
	
    public void setListIn(List listIN) {
        this.listIn = listIN;
    }

    public void setTableIn(JTable itemTableIN) {
		
        this.tableIn = itemTableIN;
    }
	
	public void setListOut(List listOUT) {
	    this.listOut = listOUT;
    }

    public void setTableOut(JTable itemTableOUT) {
        this.tableOut = itemTableOUT;
    }
}