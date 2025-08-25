package service;

import exceptions.PetValidateException;
import menu.Menu;
import models.Address;
import models.Pet;
import models.Validate;
import models.enums.PetSex;
import models.enums.PetType;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

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

    public static void listPetWithFilter() {
        Map<String, String> parameters = Menu.searchPetMenu();
        Set<Pet> filteredList = new HashSet<>();
        List<Pet> allPets = FileService.fileToPet();
        for (Pet pet : allPets) {
            boolean matchesAll = true;

            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                String criteriaKey = entry.getKey();
                String criteriaValue = entry.getValue();

                if(!Validate.petMatchesFilters(pet, criteriaKey, criteriaValue)){
                    matchesAll = false;
                    break;
                }
            }

            if(matchesAll){
                filteredList.add(pet);
            }
        }
        if(!filteredList.isEmpty()) {
            int i = 1;
            for (Pet p : filteredList) {
                System.out.println(i + "- " + p.petFilteredString());
                i++;
            }
        } else {
            System.out.println("Nenhum pet encontrado com os critérios selecionados.");
        }
    }

}
