package nsc.gui.cg;


import java.awt.Component;
import java.util.Iterator;
import java.util.List;
import javax.swing.BoxLayout; 
import javax.swing.JPanel;


public class BoxLayoutPanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * We can't use "components" as the property name,
     * because it conflicts with an existing property
     * on the Component superclass.
     */
    private List panelComponents;
    private int axis;

    public void setAxis(int axis) {
        this.axis = axis;
    }

    public void setPanelComponents(List panelComponents) {
        this.panelComponents = panelComponents;
    }

    public void init() {
        setLayout(new BoxLayout(this, axis));

        for (Iterator iter = panelComponents.iterator();
             iter.hasNext();) {
            Component component = (Component) iter.next();
            add(component);
        }
    }
}