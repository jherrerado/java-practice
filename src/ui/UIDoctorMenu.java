package ui;

import model.Doctor;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class UIDoctorMenu {

    public static ArrayList<Doctor> doctorsAvalibleAppoinments = new ArrayList<>();

    public static void showDoctorMenu(){
        int response = 0;
        do {
            System.out.println("\n\n");
            System.out.println("Doctor");
            System.out.println("Bienvenido" + UIMenu.doctorLogged.getName());
            System.out.println("1. Agrgar fecha disponible");
            System.out.println("2. Mi agenda");
            System.out.println("0. Salir del sistema");

            Scanner sc = new Scanner(System.in);
            response = Integer.valueOf(sc.nextLine());

            switch (response) {
                case 1:
                    showAddAvailableAppointmentsMenu();
                    break;
                case 2:

                    break;
                case 0:
                    UIMenu.showMenu();
                    break;
            }
        }while (response != 0);
    }

    private static void showAddAvailableAppointmentsMenu(){
        int response = 0;
        do {
            System.out.println("::Agrega una fecha del paciente");
            System.out.println("::Selecciona un mes");

            for (int i = 0; i < 3; i++) {
                int j = i + 1;
                System.out.println(j + ". " + UIMenu.MOTHS[i]);
            }
            System.out.println("0. regresar");

            Scanner sc = new Scanner(System.in);
            response = Integer.valueOf(sc.nextLine());

            if(response > 0 && response < 4){
                int mothSelected = response;
                System.out.println(mothSelected + ". " + UIMenu.MOTHS[mothSelected - 1]);
                System.out.println("Inserta la fecha libre: [dd/mm/yyyy] ");
                String date =sc.nextLine();

                System.out.println("Fecha que seleccionaste: " + date + "\n 1. Correcto \n 2. Denegado");
                int responseDate = Integer.valueOf(sc.nextLine());
                if(responseDate == 2) continue;

                int responseTime = 0;
                String time = "";
                do {
                    System.out.println("Inserta la hora de la cita para esta fecha: " + date + "[16:00]");
                    time = sc.nextLine();
                    System.out.println("Hora que seleccionaste: " + time + "\n 1. Correcto \n 2. Denegado");
                    responseTime = Integer.valueOf(sc.nextLine());


                }while (responseTime == 2);

                UIMenu.doctorLogged.addAvailableAppoinment(date, time);
                checkDoctorAvailableAppointment(UIMenu.doctorLogged);

            } else if (response == 0) {
                showDoctorMenu();
            }
        }while (response != 0);
    }

    private static void checkDoctorAvailableAppointment(Doctor doctor){
        if(doctor.getAvailableAppoinments().size() > 0
                && !doctorsAvalibleAppoinments.contains(doctor)){
            doctorsAvalibleAppoinments.add(doctor);
        }
    }
}
