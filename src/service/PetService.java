package service;

import exceptions.PetValidateException;
import menu.Menu;
import models.Address;
import models.Pet;
import models.enums.PetSex;
import models.enums.PetType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PetService {
    public static final Scanner sc = new Scanner(System.in);
    public static final FileService files = new FileService();

    public static void createPet() {
        List<String> questions = files.readForm();
        String name = null, type = null, sex = null, breed = null;
        String street = null, number = null, city = null;
        Double age = null, weight = null;
        System.out.println("Para cadastrar um pet, responda as seguintes perguntas:");
        for (int i = 1; i <= (questions.size()); i++) {
            System.out.println(questions.get(i - 1));
            boolean isValid = false;
            switch (i) {
                case 1:
                    do {
                        try {
                            name = sc.nextLine();
                            isValid = Validate.validateName(name);
                            name = Validate.isEmpty(name);

                        } catch (PetValidateException e) {
                            System.out.println("Erro: " + e.getMessage());
                            System.out.println("Digite o nome do pet novamente.");
                        }
                    } while (!isValid);
                    break;

                case 2:
                    do {
                        type = sc.nextLine();
                        isValid = Validate.validateType(type);
                    } while (!isValid);
                    break;

                case 3:
                    do {
                        sex = sc.nextLine();
                        isValid = Validate.validateSex(sex);
                    } while (!isValid);
                    break;

                case 4:
                    do {
                        System.out.print("Digite a rua: ");
                        street = sc.nextLine();
                        isValid = Validate.validateStreet(street);
                    } while (!isValid);

                    System.out.print("Digite o número: ");
                    number = sc.nextLine();
                    number = Validate.isEmpty(number);

                    do {
                        System.out.print("Digite a cidade: ");
                        city = sc.nextLine();
                        isValid = Validate.validateCity(city);
                    } while (!isValid);
                    break;

                case 5:
                    do {
                        try {
                            String auxAge = sc.nextLine();
                            if (auxAge.isBlank()) break;
                            isValid = Validate.validateAge(auxAge);
                            age = Double.parseDouble(auxAge);
                        } catch (PetValidateException | NumberFormatException e) {
                            System.out.println("Erro: " + e.getMessage());
                            System.out.println("Digite a idade novamente.");
                        }
                    } while (!isValid);

                    if (age != null) {
                        String date;
                        System.out.println("A idade é em meses ou anos?");
                        do {
                            date = sc.nextLine();
                            if (date.isEmpty()) {
                                age = null;
                                break;
                            }
                            isValid = Validate.validateDate(date);
                            if (date.equalsIgnoreCase("meses")) age /= 12;
                        } while (!isValid);
                    }
                    break;

                case 6:
                    do {
                        try {
                            String auxWeight = sc.nextLine();
                            if (auxWeight.isBlank()) break;
                            isValid = Validate.validateWeight(auxWeight);
                            weight = Double.parseDouble(auxWeight);
                        } catch (PetValidateException | NumberFormatException e) {
                            System.out.println("Erro: " + e.getMessage());
                            System.out.println("Digite o peso novamente.");
                        }
                    } while (!isValid);
                    break;

                case 7:
                    do {
                        breed = sc.nextLine();
                        isValid = Validate.validateBreed(breed);
                        breed = Validate.isEmpty(breed);
                    } while (!isValid);
            }
        }

        Address address = new Address(street, number, city);
        PetType petType = PetType.selectType(type);
        PetSex petSex = PetSex.selectSex(sex);
        Pet petCreated = new Pet(name, petType, petSex, address, age, weight, breed);
        FileService.savePet(petCreated);
    }

    public static void listPet() {
        List<String> allPetsList = FileService.petsFileReader();
        int i = 1;

        System.out.println("Lista dos pets cadastrados no sistema:");
        System.out.println("--------------------------------------");
        for (String pet : allPetsList) {
            System.out.printf("%d. %s\n", i, pet);
            i++;
        }
    }

    public static Map<Integer, Pet> listPetWithFilter() {
        Map<String, String> parameters = Menu.searchPetWithFilterMenu();
        Map<Integer, Pet> filteredList = new HashMap<>();
        List<Pet> allPets = FileService.fileToPet();
        int i = 0;
        for (Pet pet : allPets) {
            boolean matchesAll = true;

            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                String criteriaKey = entry.getKey();
                String criteriaValue = entry.getValue();

                if (!Validate.petMatchesFilters(pet, criteriaKey, criteriaValue)) {
                    matchesAll = false;
                    break;
                }
            }

            if (matchesAll) {
                i++;
                filteredList.put(i, pet);
            }
        }
        if (!filteredList.isEmpty()) {
            System.out.println("Foram encontrados os seguintes pets com os critérios selecionados:");
            for (Map.Entry<Integer, Pet> petEntry : filteredList.entrySet()) {
                System.out.println(petEntry.getKey() + "- " + petEntry.getValue().petFilteredString());
            }
        } else {
            System.out.println("Nenhum pet encontrado com os critérios selecionados.");
        }

        return filteredList;
    }

    public static void updatePet() {
        Map<Integer, Pet> filteredList = listPetWithFilter();
        if (filteredList.isEmpty()) {
            return;
        }
        System.out.println("De qual pet da lista acima você deseja alterar os dados? Digite o número: ");
        System.out.println("Digite 0 para voltar ao menu inicial");
        int id = sc.nextInt();
        if (id == 0) return;
        Pet petToUpdate = null;
        boolean hasUpdatedName = false;

            for (Map.Entry<Integer, Pet> petEntry : filteredList.entrySet()) {
                if (petEntry.getKey() == id) {
                    petToUpdate = petEntry.getValue();
                    break;
                }
            }


        String newData;
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
            if (option == 6) return;
        } while (option < 0 || option > 6);
        sc.nextLine();
        System.out.println("Digite o novo dado: ");
        newData = sc.nextLine();

        assert petToUpdate != null;
        String oldPetName = petToUpdate.getName();
        Pet updatedPet = Validate.dataToUpdate(petToUpdate, option, newData);
        if (!updatedPet.getName().equalsIgnoreCase(oldPetName)) hasUpdatedName = true;
        FileService.updatePet(updatedPet, hasUpdatedName, oldPetName);
    }

    public static void deletePet() {
        Map<Integer, Pet> filteredList = listPetWithFilter();
        Pet petToDelete = null;
        if (filteredList.isEmpty()) {
            return;
        }
            System.out.println("Qual pet da lista você deseja deletar? Digite o número: ");
            System.out.println("Digite 0 para voltar ao menu inicial");
            int id = sc.nextInt();
            if (id == 0) {
                return;
            }
            for (Map.Entry<Integer, Pet> petEntry : filteredList.entrySet()) {
                if (petEntry.getKey() == id) {
                    petToDelete = petEntry.getValue();
                    break;
                }
            }
            boolean isConfirmed = false;
            String confirmation;
            do {
                System.out.println("Pet selecionado:");
                System.out.println(petToDelete);
                System.out.println("Você tem certeza que deseja excluir o cadastro do pet?");
                System.out.println("Digite [S] para Sim e [N] para Não");
                confirmation = sc.next();
            } while (!confirmation.equalsIgnoreCase("S") && !confirmation.equalsIgnoreCase("N"));

            if (confirmation.equalsIgnoreCase("S")) {
                isConfirmed = true;
            }
            FileService.deletePet(petToDelete, isConfirmed);
    }

}
