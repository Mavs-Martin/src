package com.caresoft.clinicapp;

import java.util.ArrayList;
import java.util.Date;

public class AdminUser extends User implements HIPAACompliantUser, HIPAACompliantAdmin {
    private Integer employeeID;
    private String role;
    private ArrayList<String> securityIncidents;

    public AdminUser(Integer id, String role) {
        super(id);
        this.employeeID = id;
        this.role = role;
        this.securityIncidents = new ArrayList <> ();
    }

    @Override
    public boolean assignPin(int pin) {
        // Pin must be 6 digits or more
        if (pin >= 100000) {
            setPin(pin);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean accessAuthorized(Integer confirmedAuthID) {
        // Compares the ids, creates an incident report if not a match
        if (!getId().equals(confirmedAuthID)) {
            authIncident();
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<String> reportSecurityIncidents() {
        return securityIncidents;
    }

    public void newIncident(String notes) {
        String report = String.format(
        "Datetime Submitted: %s \n,  Reported By ID: %s\n Notes: %s \n",
        new Date(), getId(), notes
    );
        securityIncidents.add(report);
    }

    public void authIncident() {
        String report = String.format(
        "Datetime Submitted: %s \n,  ID: %s\n Notes: %s \n",
        new Date(), getId(), "AUTHORIZATION ATTEMPT FAILED FOR THIS USER"
    );
        securityIncidents.add(report);
    }

    // Getters and Setters
    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public ArrayList<String> getSecurityIncidents() {
        return securityIncidents;
    }

    public void setSecurityIncidents(ArrayList<String> securityIncidents) {
    this.securityIncidents = securityIncidents;
}
}