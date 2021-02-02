import javax.swing.JLabel;
import javax.swing.JPanel;

public class CommonAppMainPanel extends JPanel
{
    /** serial number */
    private static final long serialVersionUID = 6224309422787783370L;

    /**
     * Constructor for MesonetMainPanel that initializes it functionality
     */
    public CommonAppMainPanel() 
    {   
        JLabel greetingLabel = new JLabel("Common App Calculator");
        
        // Add greeting to this panel
        add(greetingLabel);
    }
}
