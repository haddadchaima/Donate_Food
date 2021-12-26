package com.example.chaima.donatefood.TransporterVolunteer;

import com.example.chaima.donatefood.Annoncer.Needy;
import com.example.chaima.donatefood.Donater.Don;

public class DonAffected {

    //private String idDonAffected ;
    private Don idDon ;
    private Needy idNeedy ;

    public DonAffected(Don idDon, Needy idNeedy) {
        this.idDon = idDon;
        this.idNeedy = idNeedy;
    }

    public Don getIdDon() {
        return idDon;
    }

    public void setIdDon(Don idDon) {
        this.idDon = idDon;
    }

    public Needy getIdNeedy() {
        return idNeedy;
    }

    public void setIdNeedy(Needy idNeedy) {
        this.idNeedy = idNeedy;
    }
}
