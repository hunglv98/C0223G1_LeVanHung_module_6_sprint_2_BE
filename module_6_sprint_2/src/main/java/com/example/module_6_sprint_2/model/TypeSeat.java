package com.example.module_6_sprint_2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TypeSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTypeSeat;
    private String nameTypeSeat;
    private double priceSeat;

    public TypeSeat() {
    }

    public TypeSeat(int idTypeSeat, String nameTypeSeat, double priceSeat) {
        this.idTypeSeat = idTypeSeat;
        this.nameTypeSeat = nameTypeSeat;
        this.priceSeat = priceSeat;
    }

    public int getIdTypeSeat() {
        return idTypeSeat;
    }

    public void setIdTypeSeat(int idTypeSeat) {
        this.idTypeSeat = idTypeSeat;
    }

    public String getNameTypeSeat() {
        return nameTypeSeat;
    }

    public void setNameTypeSeat(String nameTypeSeat) {
        this.nameTypeSeat = nameTypeSeat;
    }

    public double getPriceSeat() {
        return priceSeat;
    }

    public void setPriceSeat(double priceSeat) {
        this.priceSeat = priceSeat;
    }
}
