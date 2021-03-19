import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 * 
 * @author connorsharp
 * @version 5-2-2018
 * 
 * StatisticsPanel Class 
 */
public class AlternatesPanel extends JPanel
{
    /** serial number */
    private static final long serialVersionUID = -5778130703074619169L;
    /** Max button String indicator */
    
    
    public final static String SCORE_BUTTON = "SCORE";
    
    public final static String POSITION_BUTTON = "POSITION";
    
    private JRadioButton scoreButton;
    
    private JRadioButton positionButton;
    
    private ButtonGroup bg;
    
    /**
     * Constructor for StatisticsPanel that 
     * initializes its main functionality
     */
    public  AlternatesPanel()
    {
        // message for user
        System.out.println("Building Statistics panel");
        
        // creates a new button group
        bg = new ButtonGroup();
        setLayout(new GridLayout(2,1));
        // creates buttons 
        scoreButton = new JRadioButton(SCORE_BUTTON);
        positionButton = new JRadioButton(POSITION_BUTTON);
        // adds buttons to group and panel 
        bg.add(scoreButton);
        bg.add(positionButton);
        add(scoreButton);
        add(positionButton);
        // sets the default button selected
        scoreButton.setSelected(true);
        //styles the panel
        setBackground(new Color(240, 206, 0));
        TitledBorder titledBorder = new TitledBorder("Alternates");
        titledBorder.setTitleColor(new Color(0, 27, 55));
        Border whiteline = BorderFactory.createLineBorder(new Color(0, 27, 55));
        titledBorder.setBorder(whiteline);
        setBorder(titledBorder);
    }
    
    /**
     * returns the statistic button that is selected 
     * 
     * @return string of either max or min 
     */
    public String getAlternateType()
    {
        if(scoreButton.isSelected())
        {
            return SCORE_BUTTON;
        }
        else
        {
            return POSITION_BUTTON;
        }
    }
}
