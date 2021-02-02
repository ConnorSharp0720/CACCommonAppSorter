import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

/**
 * 
 * @author connorsharp
 * @version 5-2-2018
 * 
 * ParameterPanel Class
 */
public class ParameterPanel extends JPanel
{
    /** serial number */
    private static final long serialVersionUID = -5778130703074619169L;
    
    /** Final variables for CAC Committees */
    public final String COBO = "College Bowl";
    public final String CONCERTSERIES = "Concert Series";
    public final String FILMSERIES = "Film Series";
    public final String HSLC = "High School Leadership Conference";
    public final String OUDM = "University of Oklahoma Dance Marathon";
    public final String SCANDALS = "Scandals";
    public final String SPARK = "Spark";
    public final String SFW = "Spring Family Weekend";
    public final String SPEAKERSBUREAU = "Speakers Bureau";
    public final String PRCOMM = "CAC PR Committee";
    public final String FFW = "Fall Family Weekend";
    public final String WELCOMEWEEK = "Welcome Week";
    public final String USING = "University Sing";
    public final String HOCO = "Homecoming";
    
    
    // Check boxes for the available parameters
    private JCheckBox cobo;
    private JCheckBox concertSeries;
    private JCheckBox filmSeries;
    private JCheckBox hslc;
    private JCheckBox oudm;
    private JCheckBox scandals;
    private JCheckBox spark;
    private JCheckBox springFamilyWeekend;
    private JCheckBox speakersBureau;
    private JCheckBox prComm;
    private JCheckBox fallFamilyWeekend;
    private JCheckBox welcomeWeek;
    private JCheckBox using;
    private JCheckBox hoco;
    
    /**
     * Constructor for the parameter panel
     * that initializes its functionality
     */
    public ParameterPanel()
    {
        // user output 
        System.out.println("Building Parameter panel");

        // Create a GridLayout Manager
        setLayout(new GridLayout(5,3));
        
        // initializes check boxes
        cobo = new JCheckBox(COBO);
        concertSeries = new JCheckBox(CONCERTSERIES);
        filmSeries = new JCheckBox(FILMSERIES);
        hslc = new JCheckBox(HSLC);
        oudm = new JCheckBox(OUDM);
        scandals = new JCheckBox(SCANDALS);
        spark = new JCheckBox(SPARK);
        springFamilyWeekend = new JCheckBox(SFW);
        speakersBureau = new JCheckBox(SPEAKERSBUREAU);
        prComm = new JCheckBox(PRCOMM);
        fallFamilyWeekend = new JCheckBox(FFW);
        welcomeWeek = new JCheckBox(WELCOMEWEEK);
        using = new JCheckBox(USING);
        hoco = new JCheckBox(HOCO);

        
        // adds check boxes
        add(cobo);
        add(concertSeries);
        add(filmSeries);
        add(hslc);
        add(scandals);
        add(spark);
        add(springFamilyWeekend);
        add(speakersBureau);
        add(prComm);
        add(fallFamilyWeekend);
        add(welcomeWeek);
        add(using);
        add(hoco);
        
        
        
        // styling for panel
        setBackground(new Color(153, 204, 210));
        setBorder(BorderFactory.createTitledBorder("Param"));
    }
    
    /**
     * adds to the paramID list depending on selected boxes
     * 
     * @return paramIds list of params
     */
    public ArrayList<String> getParamIds()
    {
        // create ArrayList<String> to hold selected parameters
        ArrayList<String> paramIds = new ArrayList<String>();
        // adds to the ArrayList
        if(cobo.isSelected())
        {
            paramIds.add(COBO);
        }
        if(concertSeries.isSelected())
        {
            paramIds.add(CONCERTSERIES);
        }
        if(filmSeries.isSelected())
        {
            paramIds.add(FILMSERIES);
        }
        if(hslc.isSelected())
        {
            paramIds.add(HSLC);
        }
        if(oudm.isSelected())
        {
            paramIds.add(OUDM);
        }
        if(scandals.isSelected())
        {
            paramIds.add(SCANDALS);
        }
        if(spark.isSelected())
        {
            paramIds.add(SPARK);
        }
        if(springFamilyWeekend.isSelected())
        {
            paramIds.add(SFW);
        }
        if(speakersBureau.isSelected())
        {
            paramIds.add(SPEAKERSBUREAU);
        }
        if(prComm.isSelected())
        {
            paramIds.add(PRCOMM);
        }
        if(fallFamilyWeekend.isSelected())
        {
            paramIds.add(FFW);
        }
        if(using.isSelected())
        {
            paramIds.add(USING);
        }
        if(hoco.isSelected())
        {
            paramIds.add(HOCO);
        }
        if(welcomeWeek.isSelected())
        {
            paramIds.add(WELCOMEWEEK);
        }
        return paramIds;
    }

}
