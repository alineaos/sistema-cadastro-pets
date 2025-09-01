package application;


import menu.Menu;
import service.PetService;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int menuOption;
        do {
            Menu.homeMenu();
            try {
                menuOption = sc.nextInt();
                if (menuOption < 1 || menuOption > 6) {
                    System.out.println("Erro: Número inválido. Por favor, digite um número entre 1 e 6");
                }
                switch (menuOption) {
                    case 1 -> PetService.createPet();
                    case 2 -> PetService.listPet();
                    case 3 -> PetService.listPetWithFilter();
                    case 4 -> PetService.updatePet();
                    case 5 -> PetService.deletePet();
                    case 6 -> {return;}
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Letras e caracteres especiais não são aceitos. " +
                        "Por favor, digite um número entre 1 e 6");
                sc.nextLine();
            }


        } while (true);
    }


}
