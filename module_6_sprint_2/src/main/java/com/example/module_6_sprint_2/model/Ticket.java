package com.example.module_6_sprint_2.model;

import javax.persistence.*;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTicket;
    private String dateBooking;
    private boolean flagCancel;
    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;
    @OneToOne
    @JoinColumn(name = "id_seat")
    private Seat seat;

    public Ticket() {
    }

    public Ticket(int idTicket, String dateBooking, boolean flagCancel, Customer customer, Seat seat) {
        this.idTicket = idTicket;
        this.dateBooking = dateBooking;
        this.flagCancel = flagCancel;
        this.customer = customer;
        this.seat = seat;
    }

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public String getDateBooking() {
        return dateBooking;
    }

    public void setDateBooking(String dateBooking) {
        this.dateBooking = dateBooking;
    }

    public boolean isFlagCancel() {
        return flagCancel;
    }

    public void setFlagCancel(boolean flagCancel) {
        this.flagCancel = flagCancel;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }
}
