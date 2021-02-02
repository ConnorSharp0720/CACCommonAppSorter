

public class Applicant
{
    private String event; 
    private int idNum;
    private String firstName;
    private String lastName;
    private String committee;
    private double score;
    private boolean isAlternate;
    private boolean isAccepted;
    private boolean isPlaced; 
    
    
    public Applicant(String event, String firstName, String lastName, int idNum, String committee, double score, boolean isAccepted, boolean isAlternate)
    {
        this.event = event;
        this.idNum = idNum;
        this.firstName = firstName;
        this.lastName = lastName;
        this.committee = committee;
        this.score = score;
        this.isAlternate = isAlternate;
        this.isAccepted = isAccepted;
        this.isPlaced = false; 
    }
    
    /**
     * @return the event
     */
    public String getEvent()
    {
        return event;
    }
    
    /**
     * @return the idNum
     */
    public int getIdNum()
    {
        return idNum;
    }

    /**
     * @return the firstName
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * @return the committee
     */
    public String getCommittee()
    {
        return committee;
    }

    /**
     * @return the score
     */
    public double getScore()
    {
        return score;
    }

    /**
     * @return the isAlternate
     */
    public boolean isAlternate()
    {
        return isAlternate;
    }

    /**
     * @return the isAccepted
     */
    public boolean isAccepted()
    {
        return isAccepted;
    }
    
    /**
     * @return the isPlaced
     */
    public boolean isPlaced()
    {
        return isPlaced;
    }

    /**
     * @param idNum the idNum to set
     */
    public void setIdNum(int idNum)
    {
        this.idNum = idNum;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    /**
     * @param committee the committee to set
     */
    public void setCommittee(String committee)
    {
        this.committee = committee;
    }

    /**
     * @param score the score to set
     */
    public void setScore(int score)
    {
        this.score = score;
    }

    /**
     * @param isAlternate the isAlternate to set
     */
    public void setAlternate(boolean isAlternate)
    {
        this.isAlternate = isAlternate;
    }

    /**
     * @param isAccepted the isAccepted to set
     */
    public void setAccepted(boolean isAccepted)
    {
        this.isAccepted = isAccepted;
    }
    
    /**
     * @param isPlaced the isPlaced to set
     */
    public void setPlaced(boolean isPlaced)
    {
        this.isPlaced = isPlaced;
    }

    public String toString()
    {
        String acceptanceLetter; 
        if(isAccepted)
        {
            acceptanceLetter = "A";
        }
        else if (isAlternate)
        {
            acceptanceLetter = "M";
        }
        else
        {
            acceptanceLetter = "D";
        }
        String output = event + "," + firstName + "," + lastName + "," + idNum + "," + committee + "," + score + "," + acceptanceLetter;
        return output;
    }
}
