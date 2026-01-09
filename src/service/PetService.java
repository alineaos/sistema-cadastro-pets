package service;

import models.Pet;
import repository.PetRepository;

import java.util.Map;
import java.util.Scanner;

public class PetService {
    private static final Scanner sc = new Scanner(System.in);
    public static void createPet(){
        System.out.println("Para cadastrar um pet, responda as seguintes perguntas:");
        PetRepository.createPet();
    }

    public static void listPet(){
        System.out.println("Lista dos pets cadastrados no sistema:");
        System.out.println("--------------------------------------");
        PetRepository.listPet();
    }

    public static Map<Integer, Pet> listPetWithFilter(){
        return PetRepository.listPetWithFilter();
    }

    public static void updatePet(){
        Map<Integer, Pet> filteredList = listPetWithFilter();
        if (filteredList.isEmpty()) {
            return;
        }

        System.out.println("De qual pet da lista acima você deseja alterar os dados? Digite o número: ");
        System.out.println("Digite 0 para voltar ao menu inicial");
        int id = sc.nextInt();
        if (id == 0) {
            System.out.println("Retornando para o Menu Inicial...");
            return;
        }

        Pet petToUpdate = null;
        for (Map.Entry<Integer, Pet> petEntry : filteredList.entrySet()) {
            if (petEntry.getKey() == id) {
                petToUpdate = petEntry.getValue();
                break;
            }
        }

        String newData;
        Integer option = updatePetMenu();
        if (option == null || option == 6) return;
        sc.nextLine();
        System.out.println("Digite o novo dado: ");
        newData = sc.nextLine();
        assert petToUpdate != null;
        PetRepository.updatePet(option, petToUpdate, newData);
    }

    private static Integer updatePetMenu() {
        int option;
        do {
            System.out.println("Qual dado do pet você deseja alterar?");
            System.out.println("[1] Nome");
            System.out.println("[2] Idade");
            System.out.println("[3] Peso");
            System.out.println("[4] Raça");
            System.out.println("[5] Endereço");
            System.out.println("[6] Voltar para o menu inicial");
            option = sc.nextInt();
            if (option == 6) return null;
        } while (option < 0 || option > 6);
        return option;
    }

    public static void deletePet(){
        PetRepository.deletePet();
    }
}
