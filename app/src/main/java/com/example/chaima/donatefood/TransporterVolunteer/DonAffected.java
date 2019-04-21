package com.example.chaima.donatefood.TransporterVolunteer;

public class DonAffected {

    private String idDonAffected ;
    private String idDon ;
    private String idNeedy ;

    public DonAffected(String idDonAffected, String idDon, String idNeedy) {
        this.idDonAffected = idDonAffected;
        this.idDon = idDon;
        this.idNeedy = idNeedy;
    }

    public String getIdDonAffected() {
        return idDonAffected;
    }

    public void setIdDonAffected(String idDonAffected) {
        this.idDonAffected = idDonAffected;
    }

    public String getIdDon() {
        return idDon;
    }

    public void setIdDon(String idDon) {
        this.idDon = idDon;
    }

    public String getIdNeedy() {
        return idNeedy;
    }

    public void setIdNeedy(String idNeedy) {
        this.idNeedy = idNeedy;
    }
}
