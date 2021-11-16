package nsc.gui.cg.button; 



import java.awt.event.ActionListener;
import javax.swing.JButton;


public class ActionListenerButton extends JButton {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3720818305867720975L;
	private ActionListener actionListener;

    public void setActionListener(ActionListener actionListener) {
        this.actionListener = actionListener;
    }

    public void init() {
        this.addActionListener(actionListener);
    }
}