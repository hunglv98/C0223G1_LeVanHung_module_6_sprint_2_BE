package com.example.module_6_sprint_2.model;

import javax.persistence.*;

@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSchedule;
    private String dateDeparture;
    private String timeDeparture;
    @ManyToOne
    @JoinColumn(name = "id_ship")
    private Ship ship;

    public Schedule() {
    }

    public Schedule(int idSchedule, String dateDeparture, String timeDeparture, Ship ship) {
        this.idSchedule = idSchedule;
        this.dateDeparture = dateDeparture;
        this.timeDeparture = timeDeparture;
        this.ship = ship;
    }

    public int getIdSchedule() {
        return idSchedule;
    }

    public void setIdSchedule(int idSchedule) {
        this.idSchedule = idSchedule;
    }

    public String getDateDeparture() {
        return dateDeparture;
    }

    public void setDateDeparture(String dateDeparture) {
        this.dateDeparture = dateDeparture;
    }

    public String getTimeDeparture() {
        return timeDeparture;
    }

    public void setTimeDeparture(String timeDeparture) {
        this.timeDeparture = timeDeparture;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }
}
