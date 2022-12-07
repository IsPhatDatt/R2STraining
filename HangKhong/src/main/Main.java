package main;

import repository.AircraftRepository;
import repository.CertificationRepository;
import repository.FlightRepository;
import repository.StaffRepository;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        StaffRepository staffRepository = new StaffRepository();
        FlightRepository flightRepository = new FlightRepository();
        CertificationRepository certificationRepository = new CertificationRepository();
        AircraftRepository aircraftRepository = new AircraftRepository();

        staffRepository.listStaffs();
        flightRepository.listFlights();
        certificationRepository.listCertifications();
        aircraftRepository.listAircrafts();
        staffRepository.createStaff();

        flightRepository.listFlightsRequest1();
        aircraftRepository.listAircraftsRequest2();
        staffRepository.listStaffsRequest3();
        flightRepository.listFlightsRequest4();
        flightRepository.listFlightsRequest5();
        System.out.println("Counting flights departing from Saigon: " + flightRepository.countFlightsRequest6());
        System.out.println("Number of Boeing aircraft: " + aircraftRepository.countAircraftsRequest7());
        System.out.println("Total salary to be paid to staffs: " + staffRepository.sumStaffsRequest8());
        certificationRepository.listCertificationsRequest9();
        certificationRepository.listCertificationsRequest10();
        certificationRepository.listCertificationsRequest11();
        certificationRepository.listCertificationsRequest12();
        aircraftRepository.listAircraftsRequest13();
        flightRepository.listFlightsRequest14();
        certificationRepository.listCertificationsRequest15();
    }
}