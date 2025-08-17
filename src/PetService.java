import exceptions.PetValidateException;

import java.util.InputMismatchException;
import java.util.List;
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
                        } catch (PetValidateException e) {
                            System.out.println("Erro: " + e.getMessage());
                            System.out.println("Digite a idade novamente.");
                        }
                    } while (!isValid);

                    if (age != null) {
                        String date;
                        System.out.println("A idade é em meses ou anos?");
                        do {
                            date = sc.nextLine();
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
                        } catch (PetValidateException e) {
                            System.out.println("Erro: " + e.getMessage());
                            System.out.println("Digite o peso novamente.");
                        }
                    } while (!isValid);
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
        //FileService.savePet(petCreated);
        System.out.println(petCreated);
    }

}
