package com.example.chaima.donatefood.Annoncer;

public class Needy {

    private String idNeedy ;
    private String description ;
    private String adresseNeedy ;
    private String healthConditionNeedy ;
    private String numberOfPersonNeedy ;
    private boolean temporary ;
    private boolean periodically ;
    private String tel ;

    public Needy(String idNeedy, String desc, String adrNeedy, String healthCondition, String numberOfPerson, boolean temporary, boolean periodically, String tel) {
        this.idNeedy = idNeedy;
        this.description = desc;
        this.adresseNeedy = adrNeedy;
        this.healthConditionNeedy = healthCondition;
        this.numberOfPersonNeedy = numberOfPerson;
        this.temporary = temporary;
        this.periodically = periodically;
        this.tel = tel;
    }
    public Needy(String idNeedy, String description, String adresseNeedy, String healthConditionNeedy, String numberOfPersonNeedy, boolean temporary, boolean periodically) {
        this.idNeedy = idNeedy;
        this.description = description;
        this.adresseNeedy = adresseNeedy;
        this.healthConditionNeedy = healthConditionNeedy;
        this.numberOfPersonNeedy = numberOfPersonNeedy;
        this.temporary = temporary;
        this.periodically = periodically;

    }

    public Needy(){

    }

    public String getIdNeedy() {
        return idNeedy;
    }

    public void setIdNeedy(String idNeedy) {
        this.idNeedy = idNeedy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdresseNeedy() {
        return adresseNeedy;
    }

    public void setAdresseNeedy(String adresseNeedy) {
        this.adresseNeedy = adresseNeedy;
    }

    public String getHealthConditionNeedy() {
        return healthConditionNeedy;
    }

    public void setHealthConditionNeedy(String healthConditionNeedy) {
        this.healthConditionNeedy = healthConditionNeedy;
    }

    public String getNumberOfPersonNeedy() {
        return numberOfPersonNeedy;
    }

    public void setNumberOfPersonNeedy(String numberOfPersonNeedy) {
        this.numberOfPersonNeedy = numberOfPersonNeedy;
    }

    public boolean isTemporary() {
        return temporary;
    }

    public void setTemporary(boolean temporary) {
        this.temporary = temporary;
    }

    public boolean isPeriodically() {
        return periodically;
    }

    public void setPeriodically(boolean periodically) {
        this.periodically = periodically;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
