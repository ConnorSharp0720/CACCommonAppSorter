import java.util.ArrayList;

public class CacEvent
{
    /** Event title */
    private String title;
    
    /** Array of applicants */
    private ArrayList<Applicant> applicantList = new ArrayList<Applicant>();
    
    /** Number of alternates the event needs */
    private int alternatesNum;
    
    
    
    public CacEvent(String title)
    {
        this.title = title;
        this.alternatesNum = 0;
    }
    
    public void addApplicant(Applicant applicant)
    {
        applicantList.add(applicant);
    }
    
    public boolean doesApplicantExist(Applicant applicant)
    {
        boolean exists = false;
        for(int i = 0; i < applicantList.size(); i++)
        {
            if(applicantList.get(i).getIdNum() == applicant.getIdNum())
            {
                exists = true;
            }
        }
        return exists;
    }
    
    public boolean doesApplicantMatch(Applicant applicant)
    {
        if(applicant.getEvent().compareTo(title) == 0)
        {
            return true;
        }
        else 
        {
            return false;
        }
    }
    
    public void incrementAlternatesNeeded()
    {
        this.alternatesNum++;
    }

    /**
     * @return the title
     */
    public String getTitle()
    {
        return title;
    }



    /**
     * @return the applicantList
     */
    public ArrayList<Applicant> getApplicantList()
    {
        return applicantList;
    }



    /**
     * @return the alternatesNum
     */
    public int getAlternatesNum()
    {
        return alternatesNum;
    }



    /**
     * @param title the title to set
     */
    public void setTitle(String title)
    {
        this.title = title;
    }



    /**
     * @param applicantList the applicantList to set
     */
    public void setApplicantList(ArrayList<Applicant> applicantList)
    {
        this.applicantList = applicantList;
    }



    /**
     * @param alternatesNum the alternatesNum to set
     */
    public void setAlternatesNum(int alternatesNum)
    {
        this.alternatesNum = alternatesNum;
    }



    public String toString()
    {
        String output = "";
        for(Applicant applicant: applicantList)
        {
            output = output + "\n" + applicant;
        }
        return output;
    }
    
    public ArrayList<Integer> selectAlternates(ArrayList<Integer> alreadyPlaced)
    {
        ArrayList<Integer> placedIds = alreadyPlaced;
        double highScore;
        int alternateIndex;
        for(int i = 0; i < alternatesNum; i ++)
        {
            alternateIndex = 0;
            highScore = -10000.0;
            for(int k = 0; k < applicantList.size(); k++)
            {
                if(!placedIds.contains(applicantList.get(k).getIdNum()) && applicantList.get(k).isAlternate() && applicantList.get(k).getScore() > highScore)
                {
                    highScore = applicantList.get(k).getScore();
                    alternateIndex = k;
                }
            }
            applicantList.get(alternateIndex).setAccepted(true);
            placedIds.add(applicantList.get(alternateIndex).getIdNum());
        }
        
        return placedIds;
    }
    
    public ArrayList<Integer> selectAlternatesOption2(ArrayList<Integer> alreadyPlaced)
    {
        
        
        ArrayList<Integer> placedIds = alreadyPlaced;
        double highScore;
        int alternateIndex;
        for(int i = 0; i < alternatesNum; i ++)
        {
            alternateIndex = 0;
            highScore = -10000.0;
            for(int k = 0; k < applicantList.size(); k++)
            {
                if(!placedIds.contains(applicantList.get(k).getIdNum()) && applicantList.get(k).isAlternate() && applicantList.get(k).getScore() > highScore)
                {
                    highScore = applicantList.get(k).getScore();
                    alternateIndex = k;
                }
            }
            applicantList.get(alternateIndex).setAccepted(true);
            placedIds.add(applicantList.get(alternateIndex).getIdNum());
        }
        
        return placedIds;
    }
    
}
