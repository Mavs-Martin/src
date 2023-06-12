package com.caresoft.clinicapp;

import java.util.ArrayList;

public interface HIPAACompliantAdmin {

    ArrayList<String> reportSecurityIncidents();

    default void printSecurityIncidents() {
        System.out.println(reportSecurityIncidents());
    }

    default boolean adminQATest(ArrayList<String> expectedIncidents) {
        if (reportSecurityIncidents().equals(expectedIncidents)) {
            System.out.println("PASS");
        } else {
            System.out.println("FAIL");
        }
        return reportSecurityIncidents().equals(expectedIncidents);
    }

}
