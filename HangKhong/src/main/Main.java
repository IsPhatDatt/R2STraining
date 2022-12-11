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

        staffRepository.getListStaff();
        flightRepository.getListFlight();
        certificationRepository.getListCertification();
        aircraftRepository.getListAircraft();
        staffRepository.createStaff();

        flightRepository.getListFlightFromDaLat();
        aircraftRepository.getListTypeOfAircraftFlightRangeGreaterThan10000Km();
        staffRepository.getListOfSalaryStaffLessThan10000();
        flightRepository.getListFlightLenghtLessThan10000KmAndGreaterThan8000Km();
        flightRepository.getListFlightFromSaiGonToBanMeThuoc();
        System.out.println("Counting flights departing from Saigon: " + flightRepository.countFlightsStartSaiGon());
        System.out.println("Number of Boeing aircraft: " + aircraftRepository.countTypeOfAircraftBoeing());
        System.out.println("Total salary to be paid to staffs: " + staffRepository.sumSalaryOfStaff());
        certificationRepository.getListOfCodeOfBoeingPilot();
        certificationRepository.getListOfStaffCanFlyAirCraftCode747();
        certificationRepository.getListOfCodeAirCraftForStaffLastNameNguyen();
        certificationRepository.getListOfCodeStaffCanFlyAirCraftBoeingAndAirbus();
        aircraftRepository.getListTypeOfAirCraftCanMakeFlightVN280();
        flightRepository.getListOfFlightsCanBePerformedByAirbusA320();
        certificationRepository.getListOfFullNameStaffFlyAirCraftBoeing();
        certificationRepository.getListStaffCanFlyAircraft();
        flightRepository.getListOfFlightFromStationAToBBackA();
        flightRepository.getListOfStationAndNumberOfFlightOfStation();
        flightRepository.getListOfStationAndSumPriceFlightOfStation();
        flightRepository.getListOfFlightBefore12PM();
        certificationRepository.getListOfCodeStaffOnlyFlyThreeAircraft();
        certificationRepository.getListOfStaffCanFlyGreaterThan3AirCraft();
        certificationRepository.getListOfStaffNumberOfAircraftCanFly();
        staffRepository.getListOfStaffNoPilot();
        staffRepository.getListOfStaffHaveHighestSalary();
        staffRepository.getListOfPilotTotalSalaryPayments();
    }
}