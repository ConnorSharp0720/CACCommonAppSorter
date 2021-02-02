import java.util.ArrayList;

public class MultiPrefApplicant
{
    private int idNum;
    private String firstName;
    private String lastName;
    /** Array of Preferences */
    private ArrayList<String> preferenceList = new ArrayList<String>();
    
    
    public MultiPrefApplicant(String firstName, String lastName, int idNum, ArrayList<String> preferenceList)
    {
        this.idNum = idNum;
        this.firstName = firstName;
        this.lastName = lastName;
        this.preferenceList = preferenceList;
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
     * @return the preferenceList
     */
    public ArrayList<String> getPreferenceList()
    {
        return preferenceList;
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
     * @param lastName the lastName to set
     */
    public void setPreferenceList(ArrayList<String> preferenceList)
    {
        this.preferenceList = preferenceList;
    }
    
    public String toString()
    {
        String output = "Multi Preference Applicant";
        return output;
    }
}
