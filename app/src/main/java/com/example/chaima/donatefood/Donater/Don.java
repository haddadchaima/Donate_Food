package com.example.chaima.donatefood.Donater;

import android.graphics.Bitmap;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.File;

@IgnoreExtraProperties
public class Don {

     private String idDon ;
     private String title ;
     private String description ;
     private String healthCondition ;
     private String adresse ;
     private String numberOfPerson ;
     private String  timePickup ;
     private Boolean cancelAssingment ;
     private String imageUrl ;




    public Don(String iddon ,String titleDon, String descDon, String healthConditionDon, String adresseDonater, String numberOfPersonDon, String timePickupDon, Boolean cancelAssingment) {
        title = titleDon;
        description = descDon;
        healthCondition = healthConditionDon;
        adresse = adresseDonater;
        numberOfPerson = numberOfPersonDon;
        timePickup = timePickupDon;
        this.cancelAssingment = cancelAssingment;
        this.idDon = iddon;

    }

    public Don(String idDon, String titleDon, String descriptionDon, String healthConditionDon, String adresseDon, String numberOfPersonDon, String timePickupDon, Boolean cancelAssingment, String imageUrlDon) {
        this.idDon = idDon;
        this.title = titleDon;
        this.description = descriptionDon;
        this.healthCondition = healthConditionDon;
        this.adresse = adresseDon;
        this.numberOfPerson = numberOfPersonDon;
        this.timePickup = timePickupDon;
        this.cancelAssingment = cancelAssingment;
        this.imageUrl = imageUrlDon;
    }

    public Don(String idDon, String titleDon, String descriptionDon, String healthConditionDon) {
        this.idDon = idDon;
        this.title = titleDon;
        this.description = descriptionDon;
        this.healthCondition = healthConditionDon;

    }

    public Don() {
    }



    public String getIdDon() {
        return idDon;
    }

    public void setIdDon(String idDon) {
        this.idDon = idDon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHealthCondition() {
        return healthCondition;
    }

    public void setHealthCondition(String healthCondition) {
        this.healthCondition = healthCondition;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNumberOfPerson() {
        return numberOfPerson;
    }

    public void setNumberOfPerson(String numberOfPerson) {
        this.numberOfPerson = numberOfPerson;
    }

    public String getTimePickup() {
        return timePickup;
    }

    public void setTimePickup(String timePickup) {
        this.timePickup = timePickup;
    }

    public Boolean getCancelAssingment() {
        return cancelAssingment;
    }

    public void setCancelAssingment(Boolean cancelAssingment) {
        this.cancelAssingment = cancelAssingment;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void updateDon(String idDon, String titleDon, String descriptionDon, String healthConditionDon, String adresseDon, String numberOfPersonDon, String timePickupDon, Boolean cancelAssingment, String imageUrlDon){
        DatabaseReference reference;
        reference = FirebaseDatabase.getInstance().getReference("Don");
       // reference.child(idDon).setValue(titleDon, descriptionDon,  healthConditionDon,  adresseDon,  numberOfPersonDon, timePickupDon,cancelAssingment,imageUrlDon);
    }


}
