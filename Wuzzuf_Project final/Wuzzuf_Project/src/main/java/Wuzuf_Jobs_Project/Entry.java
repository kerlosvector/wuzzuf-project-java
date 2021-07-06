package Wuzuf_Jobs_Project;

public class Entry {

    private String title;
    private String company;
    private String location;
    private String type;
    private String level;
    private String yeasExp;
    private String country;
    private String skills;

    public Entry(String field, String title, String company, String location, String type, String level, String yeasExp, String country, String skills){
        super();
        this.title = title;
        this.company = company;
        this.location = location;
        this.type = type;
        this.level = level;
        this.yeasExp = yeasExp;
        this.country = country;
        this.skills = skills;
    }

    public String getTitle(){
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getLocation() {
        return location;
    }

    public String getType() {
        return type;
    }

    public String getLevel() {
        return level;
    }

    public String getYeasExp() {
        return yeasExp;
    }

    public String getCountry() {
        return country;
    }

    public String getSkills() {
        return skills;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setYeasExp(String yeasExp) {
        this.yeasExp = yeasExp;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}
