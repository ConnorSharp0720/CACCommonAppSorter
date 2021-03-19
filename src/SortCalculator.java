import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * @author connorsharp
 * @version 2018-30-3
 *
 * Class that represents a DaysStatistics object which is a 
 * whole set of data from multiple days worth of data including 
 * min max and average values
 * 
 */
public class SortCalculator
{
    /** Array Strings of file names */
    private ArrayList<String> committeeFiles;
    /** Array Strings of file names */
    private ArrayList<String> applicantFiles;
    
    /** Array Strings of file names */
    private ArrayList<CacEvent> eventList;
    
    private ArrayList<Integer> alreadyPlaced = new ArrayList<Integer>();
    
    
    /** Array of applicants */
    private ArrayList<Applicant> applicantList = new ArrayList<Applicant>();
    
    /** Array of multi preference applicants  */
    private ArrayList<MultiPrefApplicant> multiPrefApplicantList = new ArrayList<MultiPrefApplicant>();
    
    
    

    /**
     * Constructor for DaysStatistics 
     * creates an arraylist of file locations
     * @param files array of files 
     */
    public SortCalculator(String[] comfiles, String[] appfiles, ArrayList<CacEvent> eventList)
    {
        this.committeeFiles = new ArrayList<String>(Arrays.asList(comfiles));
        this.applicantFiles = new ArrayList<String>(Arrays.asList(appfiles));
        this.eventList = eventList;
        try 
        {
            parseFiles();
        }
        catch (IOException |  ParseException e1)
        {
            e1.printStackTrace();
        }
    }

    
    
    // parses the committee files that are read into from the program
    public void parseFiles() throws IOException, ParseException
    {
        for (String fileName : applicantFiles)
        {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String strg;
            // 3 read lines to get rid of the 3 leading header lines 
            strg = br.readLine();
            strg = br.readLine();
            strg = br.readLine();
            
            strg = br.readLine();
            while (strg != null)
            {
                parseAppLine(strg);
                strg = br.readLine();
            }
            br.close();
        }
        
        for (String fileName : committeeFiles)
        {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String strg;
            strg = br.readLine();
            strg = br.readLine();
            while (strg != null)
            {
                parseCommLine(strg);
                strg = br.readLine();
            }
            br.close();
            
        }
        System.out.println(applicantList.size());
    }
    
    // parses the line from the committee file that are read into from the program
    public void parseCommLine(String line)
    {
        if (line != null)
        {
            String[] input = line.trim().split(",");
            int id = Integer.parseInt(input[3]);
            String firstName = input[1];
            String lastName = input[2];
            

            String event = input[0];
            String committee = input[4];
            double score = Double.parseDouble(input[5]);
            boolean isAccepted; 
            boolean isAlternate;
            if(input[6].compareTo("A") == 0) 
            {
                isAccepted = true;
                isAlternate = false;
            }
            else if(input[6].compareTo("M") == 0)
            {
                isAccepted = false;
                isAlternate = true;
            }
            else 
            {
                isAccepted = false;
                isAlternate = false;
            }
            Applicant applicant = new Applicant(event, firstName, lastName, id, committee, score, isAccepted, isAlternate);
            applicantList.add(applicant);
        }
    }
    
    // parses the line from the applicant file that are read into from the program
    public void parseAppLine(String line)
    {
        if (line != null)
        {
            String[] input = line.trim().split(",");
            String firstName = input[4];
            String lastName = input[5];
            int id = Integer.parseInt(input[14]);
            
            ArrayList<String> prefList = new ArrayList<String>();
            
            for (int i = 20; i < input.length; i++)
            {
                if(input[i].compareTo("") != 0)
                {
                    prefList.add(input[i]);
                }
            }
            MultiPrefApplicant applicant = new MultiPrefApplicant(firstName, lastName, id, prefList);
            multiPrefApplicantList.add(applicant);
        }
    }
    
    
    public void sortApplicants()
    {     
        int multiPref; 
        String roundPref;
        
        for(int round = 0; round < 15; round++)
        {
            for(Applicant applicant: applicantList)
            {
                if(!applicant.isAccepted())
                {
                    applicant.setAccepted(false);
                    for(CacEvent event: eventList)
                    {
                          if(event.doesApplicantMatch(applicant) && !event.doesApplicantExist(applicant))
                          {
                              event.addApplicant(applicant);
                          }
                    }
                }
                else if (alreadyPlaced.contains(applicant.getIdNum()))
                {
                    for(CacEvent event: eventList)
                    {
                        if(event.doesApplicantMatch(applicant) && !event.doesApplicantExist(applicant))
                        {
                            applicant.setAccepted(false);
                            applicant.setAlternate(false);
                            event.addApplicant(applicant);
                            System.out.println("test");
                            event.incrementAlternatesNeeded();
                            event.addAltCommittee(applicant.getCommittee());
                        }
                    }
                }
                else 
                {
                    multiPref = applicantHasMultiPref(applicant);
                    if(multiPref == -1)
                    {
                        for(CacEvent event: eventList)
                        {
                              if(event.doesApplicantMatch(applicant) && !event.doesApplicantExist(applicant))
                              {
                                  alreadyPlaced.add(applicant.getIdNum());
                                  event.addApplicant(applicant);
                              }
                        }
                    }
                    else
                    {   
    
                        roundPref = multiPrefApplicantList.get(multiPref).getPreferenceList().get(round).trim();
                        for(CacEvent event: eventList)
                        {
                            if(event.doesApplicantMatch(applicant))
                            {
                                if((!alreadyPlaced.contains(applicant.getIdNum()) && roundPref.compareTo(event.getTitle()) == 0))
                                {
                                    alreadyPlaced.add(applicant.getIdNum());
                                    event.addApplicant(applicant);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    public int applicantHasMultiPref(Applicant applicant)
    {
        int idNum = applicant.getIdNum();
        int foundInList = -1;
        
        for(int i = 0; i < multiPrefApplicantList.size(); i++)
        {
            if(idNum == multiPrefApplicantList.get(i).getIdNum())
            {
                foundInList = i;
            }
        }
        return foundInList;
    }
    
    public int applicantExists(int id)
    {
        int foundInList = -1;
        for(int i = 0; i < applicantList.size(); i++)
        {
            if(id == applicantList.get(i).getIdNum())
            {
                foundInList = i;
            }
        }
        return foundInList;
    }

    
    public void selectAlts(String method)
    {
        if (method.compareTo("SCORE") == 0)
        {
            for(CacEvent event: eventList)
            {
                alreadyPlaced = event.selectAlternatesScore(alreadyPlaced);
            }
        }
        else 
        {
            for(CacEvent event: eventList)
            {
                alreadyPlaced = event.selectAlternatesPosition(alreadyPlaced);
            }
        }
    }

}
