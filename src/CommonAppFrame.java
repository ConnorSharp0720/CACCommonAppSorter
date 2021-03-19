import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class CommonAppFrame extends JFrame
{
    /** Menu bar */
    private FileMenuBar fileMenuBarCom;
    /** Menu bar */
    private FileMenuBar fileMenuBarApp;
    /** Parameter panel */
    private ParameterPanel parameters;
    /** Statistics panel */
    private AlternatesPanel alternates;
    /** Button panel */
    private JPanel buttonPanel;
    /** Calculate button */
    private JButton sortButton;
    /** exit button */
    private JButton exitButton;
    /** output panel */
    private OutputPanel outputPanel;
    
    
    /** serial number */
    private static final long serialVersionUID = 1L;
    
    public CommonAppFrame()
    {
        super("Common App Calculator");
        // styling for frame 
        
        
        setLayout(new BorderLayout());
        setSize(800,400);
        setResizable(true);
        buildButtonPanel();
        
        
        //creates all the necessary panels
        fileMenuBarCom = new FileMenuBar("Committee Preferences");
        fileMenuBarApp = new FileMenuBar("Applicant Preferences");
        outputPanel = new OutputPanel();
        parameters = new ParameterPanel();
        alternates = new AlternatesPanel();
        
        JPanel header = new JPanel();
        header.setLayout(new GridLayout(1,2));
        header.add(fileMenuBarCom);
        header.add(fileMenuBarApp);
        
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(2,1));
        center.add(parameters);
        center.add(outputPanel);
        add(header, BorderLayout.PAGE_START);
        //add(fileMenuBarCom, BorderLayout.LINE_START);
        add(center, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.PAGE_END);
        add(alternates, BorderLayout.LINE_START);
        //add(outputPanel, BorderLayout.PAGE_END);
        
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //pack();
        
    }
    
    private void buildButtonPanel()
    {   
        buttonPanel = new JPanel();
        //buttonPanel.setLayout(new GridLayout(2,1));
        
        sortButton = new JButton("Sort Applicants");
        exitButton = new JButton("Exit");
        // Register the action listeners
        exitButton.addActionListener(new ExitButtonListner());
        sortButton.addActionListener(new SortButtonListner());
        // adds the buttons
        buttonPanel.add(sortButton);
        buttonPanel.add(exitButton);
        
        buttonPanel.setBackground(new Color(25, 126, 204));
    }
    
    private class ExitButtonListner implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            // exits the frame
            System.exit(0);
        }
    }

    /**
     * 
     * @author connorsharp
     *
     * calc button listener class
     */
    private class SortButtonListner implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            CacEvent placeholder; 
            ArrayList<String> committeeFiles = fileMenuBarCom.getFileList();
            ArrayList<String> applicantFiles = fileMenuBarApp.getFileList();
            ArrayList<CacEvent> eventList = new ArrayList<CacEvent>();
            ArrayList<String> params = parameters.getParamIds();
            String altMethod = alternates.getAlternateType();
            
            for(String paramType: params)
            {
                placeholder = new CacEvent(paramType);
                eventList.add(placeholder);
            }
            
            SortCalculator data = new SortCalculator(committeeFiles.toArray(new String[committeeFiles.size()]), applicantFiles.toArray(new String[applicantFiles.size()]), eventList);
            data.sortApplicants();
            data.selectAlts(altMethod);
            String output = "";
            String fileName;
            for(CacEvent event: eventList)
            {
                fileName = event.getTitle() + System.currentTimeMillis() + ".csv";
                try 
                {
                    File myObj = new File(fileName);
                    if (myObj.createNewFile()) 
                    {
                        output += "File created: " + myObj.getName() + "\n";
                        outputPanel.updateData(output);
                        System.out.println("File created: " + myObj.getName());
                    } 
                    else 
                    {
                        output += "File already exists. \n";
                        outputPanel.updateData(output);
                        System.out.println("File already exists.");
                    }
                }
                catch (IOException f) 
                {
                    output += "An error occurred \n" + f.toString() + "\n";
                    outputPanel.updateData(output);
                    System.out.println("An error occurred.");
                    f.printStackTrace();
                }
                
                try 
                {
                    FileWriter myWriter = new FileWriter(fileName);
                    myWriter.write(event.toString());
                    myWriter.close();
                    output += "Successfully wrote to the file. \n";
                    outputPanel.updateData(output);
                    System.out.println("Successfully wrote to the file.");
                } 
                catch (IOException g) 
                {
                    output += "An error occurred \n" + g.toString() + "\n";
                    outputPanel.updateData(output);
                    System.out.println("An error occurred.");
                    g.printStackTrace();
                }
                
                //output = output + "\n" + event;
                System.out.println(event.getTitle() + " : " + event.getAlternatesNum());
            }
            //outputPanel.updateData(output);
            
        }

    }
    
    ///////////////////////////////////////////////////////////////////
    /**
     * 
     * @author CS2334, modified by ???
     * @version 2018-x-x
     * 
     *          Menu bar that provides file loading and program exit capabilities.
     *
     */
    @SuppressWarnings("serial")
    public class FileMenuBar extends JMenuBar
    {
        // Menu on the menu bar
        private JMenu menu;

        // Two options for the menu
        private JMenuItem menuOpen;
        private JMenuItem menuExit;

        // Reference to a file chooser pop-up
        private JFileChooser fileChooser;

        private ArrayList<String> listOfFiles;

        /**
         * Constructor: fully assemble the menu bar and attach the necessary action
         * listeners.
         */
        public FileMenuBar(String lable)
        {
            
            listOfFiles = new ArrayList<>();
            // Create the menu and add it to the menu bar
            menu = new JMenu(lable);
            add(menu);

            // Create the menu items and add them to the menu
            menuOpen = new JMenuItem("Open Data File");
            menuOpen.setName("Menu Open");
            menuExit = new JMenuItem("Exit");
            menu.add(menuOpen);
            menu.add(menuExit);

            // Action listener for exit
            menuExit.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    System.exit(0);
                }
            });

            // Create the file chooser
            fileChooser = new JFileChooser(new File("./data/mesonet"));
            fileChooser.setMultiSelectionEnabled(true);

            // Action listener for file open
            menuOpen.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    String output = "";
                    // Ask for files
                    int returnVal = fileChooser.showOpenDialog(menuOpen);
                    // Did we get one?
                    if (returnVal == JFileChooser.APPROVE_OPTION)
                    {
                        // Yes
                        File[] files = fileChooser.getSelectedFiles();
                        // System.out.println(files.length);
                        try
                        {
                            for (File file : files)
                            {
                                String fileName = file.toString();
                                System.out.println(fileName);
                                output += "\n Opened file: " + fileName;
                                outputPanel.updateData(output);
                                listOfFiles.add(fileName);
                            }
                        }
                        catch (Exception e2)
                        {
                            // Catch all other exceptions
                            JOptionPane.showMessageDialog(fileChooser, "File load error");
                            CommonAppFrame.this.setCursor(null);
                        }
                    }
                    else
                    {
                        System.out.println("No files.");
                    }
                }
            });

        }

        @SuppressWarnings("unchecked")
        public ArrayList<String> getFileList()
        {
            return (ArrayList<String>) listOfFiles.clone();
        }
    }

}

