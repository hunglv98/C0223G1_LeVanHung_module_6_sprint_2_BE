package com.example.module_6_sprint_2.model;

import javax.persistence.*;

@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSeat;
    private String nameSeat;

    private boolean flagPayment;
    @ManyToOne
    @JoinColumn(name = "id_type_seat")
    private TypeSeat typeSeat;
    @ManyToOne
    @JoinColumn(name = "id_schedule")
    private Schedule schedule;

    public Seat() {
    }

    public Seat(int idSeat, String nameSeat, boolean flagPayment, TypeSeat typeSeat, Schedule schedule) {
        this.idSeat = idSeat;
        this.nameSeat = nameSeat;
        this.flagPayment = flagPayment;
        this.typeSeat = typeSeat;
        this.schedule = schedule;
    }

    public int getIdSeat() {
        return idSeat;
    }

    public void setIdSeat(int idSeat) {
        this.idSeat = idSeat;
    }

    public String getNameSeat() {
        return nameSeat;
    }

    public void setNameSeat(String nameSeat) {
        this.nameSeat = nameSeat;
    }

    public boolean isFlagPayment() {
        return flagPayment;
    }

    public void setFlagPayment(boolean flagPayment) {
        this.flagPayment = flagPayment;
    }

    public TypeSeat getTypeSeat() {
        return typeSeat;
    }

    public void setTypeSeat(TypeSeat typeSeat) {
        this.typeSeat = typeSeat;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
