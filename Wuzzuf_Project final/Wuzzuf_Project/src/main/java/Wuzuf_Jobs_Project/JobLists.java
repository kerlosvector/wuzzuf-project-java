package Wuzuf_Jobs_Project;

import java.util.Collections;
import java.util.List;

public class JobLists {
    private String Title;
    private String Company;
    private String Location;
    private String Type;
    private String Level;
    private String YearsExp;
    private String Country;
    private List Skills;


    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String Company) {
        this.Company = Company;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getLevel() {
        return Level;
    }

    public void setLevel(String Level) {
        this.Level = Level;
    }

    public String getYearsExp() {
        return YearsExp;
    }

    public void setYearsExp(String YearsExp) {
        this.YearsExp = YearsExp;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public List getSkills() {return Collections.singletonList( Skills );}

    public void setSkills(List Skills) {
        this.Skills = Skills;
    }



    @Override
    public String toString() {
        return " Title: " + Title+
                " , Company: " +Company+
                " , Location: " + Location+
                " , Type: " + Type+
                " , level: " + Level+
                " , YearsExp: " + YearsExp+
                " , Country: " + Country+
                " , Skills: " + Skills+"\n";
    }
}
