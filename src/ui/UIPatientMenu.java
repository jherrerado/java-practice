package ui;

import model.Doctor;

import javax.print.Doc;
import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class UIPatientMenu {
    public static void showPatientMenu(){
        int response = 0;
        do {

            System.out.println("\n\n");
            System.out.println("Patient");
            System.out.println("Welcome: " + UIMenu.patinetLogged);
            System.out.println("1. Book an appointment");
            System.out.println("2. My Appointments");
            System.out.println("0. Logout");

            Scanner sc = new Scanner(System.in);
            response = Integer.valueOf(sc.nextLine());

            switch (response){
                case 1:
                    break;
                case 2:
                    break;
                case 0:
                    UIMenu.showMenu();
                    break;
            }


        }while (response!=0);
    }

    private static void showBookAppointmentMenu(){
        int response = 0;
        do {
            System.out.println("::Book an appointment");
            System.out.println(":: Select date: ");
            Map<Integer, Map<Integer, Doctor>> doctors = new TreeMap<>();

            int k = 0;
            for (int i = 0; i < UIDoctorMenu.doctorsAvalibleAppoinments.size(); i++) {
                ArrayList<Doctor.AvailableAppoinment> availableAppoinments
                        = UIDoctorMenu.doctorsAvalibleAppoinments.get(i).getAvailableAppoinments();

                Map<Integer, Doctor> doctorAppointments = new TreeMap<>();

                for (int j = 0; j < availableAppoinments.size(); j++) {
                    k++;
                    System.out.println(k + ". " + availableAppoinments.get(j).getDate());
                    doctorAppointments.put(Integer.valueOf(j),
                            UIDoctorMenu.doctorsAvalibleAppoinments.get(j));
                    doctors.put(Integer.valueOf(k), doctorAppointments);
                }
            }
            Scanner sc = new Scanner(System.in);
            int responseDateSelected = Integer.valueOf(sc.nextLine());

            Map<Integer, Doctor> doctorAvailableSelected = doctors.get(responseDateSelected);
            Integer indexDate = 0;
            Doctor doctorSelected = new Doctor("","");

            for (Map.Entry<Integer, Doctor> doc: doctorAvailableSelected.entrySet()) {
                indexDate = doc.getKey();
                doctorSelected = doc.getValue();
            }

            System.out.println(doctorSelected.getName() +
                    ". Date: " +
                    doctorSelected.getAvailableAppoinments().get(indexDate).getDate() +
                    ". Time: " +
                    doctorSelected.getAvailableAppoinments().get(indexDate).getTime());

            System.out.println("confima tu cita: \n1. si \n2. cambiar dato");
            response = Integer.valueOf(sc.nextLine());

            if(response == 1){
                UIMenu.patientLogged.addAppointmentDoctors(
                        doctorSelected,
                        doctorSelected.getAvailableAppoinments().get(indexDate).getDate(null),
                        doctorSelected.getAvailableAppoinments().get(indexDate).getTime()
                );

                showPatientMenu();
            }
        }while (response!= 0);
    }

    private static void showPatientMyAppointments(){
        int response = 0;
        do {
            System.out.println("::My Appointments");
            if (UIMenu.patientLogged.getAppointmentDoctors().size() == 0){
                System.out.println("Don't have appointments");
                break;
            }

            for (int i = 0; i < UIMenu.patientLogged.getAppointmentDoctors().size(); i++) {
                int j = i + 1;
                System.out.println(j + ". " +
                        "Date: " + UIMenu.patientLogged.getAppointmentDoctors().get(i).getDate() +
                        " Time: " + UIMenu.patientLogged.getAppointmentDoctors().get(i).getTime() +
                        "\n Doctor: " + UIMenu.patientLogged.getAppointmentDoctors().get(i).getDoctor().getName()
                );
            }

            System.out.println("0. Return");
        }while (response!=0);
    }
}
