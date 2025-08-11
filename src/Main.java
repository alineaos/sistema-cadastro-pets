import javax.swing.*;
import java.awt.event.ItemEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {

    public static Files files = new Files();
    public static Menu menu = new Menu();
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcaoMenu;
        do {
            menu.homeMenu();

            try {
                opcaoMenu = sc.nextInt();
                if (opcaoMenu < 1 || opcaoMenu > 6) {
                    System.out.println("Erro: Número inválido. Por favor, digite um número entre 1 e 6");
                }
                switch (opcaoMenu) {
                    case 1 -> registerPet();
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Letras e caracteres especiais não são aceitos. " +
                        "Por favor, digite um número entre 1 e 6");
                sc.nextLine();
            }
            break;


        } while (true);
    }

    public static void registerPet() {
        sc.nextLine();
        List<String> questions = files.readForm();
        String name="", type="", sex="", breed = "";
        String street="", number="", city= "";
        double age= 0, weight = 0;
        System.out.println("Para cadastrar um pet, responda as seguintes perguntas:");
        for (int i = 1; i <= (questions.size()); i++) {
            System.out.println(questions.get(i - 1));
            switch (i) {
                case 1:
                    name = sc.nextLine();
                    while (!name.matches("([a-zA-z\\s]+|)")) {
                        System.out.println("Erro: não é permitido o uso de números ou caracteres especiais.");
                        System.out.println("Por favor, digite o nome do pet novamente: ");
                        name = sc.nextLine();
                    }
                    break;
                case 2:
                    type = sc.nextLine();
                    while (!type.equalsIgnoreCase("cachorro") && !type.equalsIgnoreCase("gato")) {
                        System.out.println("Erro: Resposta inválida. Por favor, digite novamente.");
                        type = sc.nextLine();
                    }
                    break;
                case 3:
                    sex = sc.nextLine();
                    while (!sex.equalsIgnoreCase("f") && !sex.equalsIgnoreCase("m")) {
                        System.out.println("Erro: Resposta inválida. Por favor, digite novamente.");
                        sex = sc.nextLine();
                    }
                    break;
                case 4:
                    System.out.print("Digite a rua: ");
                    street = sc.nextLine();
                    System.out.print("Digite o número: ");
                    number = sc.nextLine();

                    System.out.print("Digite a cidade: ");
                    city = sc.nextLine();
                    while (street.isEmpty() || city.isEmpty()) {
                        if (street.isEmpty()) {
                            System.out.println("Erro: O nome da rua não pode estar em branco.");
                            System.out.println("Por favor, digite novamente.");
                            street = sc.nextLine();
                        } else if (city.isEmpty()) {
                            System.out.println("Erro: O nome da cidade não pode estar em branco.");
                            System.out.println("Por favor, digite novamente.");
                            city = sc.nextLine();
                        }
                    }
                    break;
                case 5:
                    System.out.println("A idade é em meses ou anos?");
                    String tempo = sc.nextLine();
                    while (!tempo.equalsIgnoreCase("meses") && !tempo.equalsIgnoreCase("anos")) {
                        System.out.println("Erro: Opção inválida. Por favor, digite novamente.");
                        tempo = sc.nextLine();
                    }
                    System.out.println("Digite a idade em " + tempo);
                    age = sc.nextDouble();
                    if (tempo.equalsIgnoreCase("meses")) age /= 12;
                    break;
                case 6:
                    weight = sc.nextDouble();
                    if (weight < 0.5 || weight > 60)
                        throw new RuntimeException("O peso precisa ser entre 0.5kg e 60kg.");
                    break;
                case 7:
                    breed = sc.nextLine();
                    while (!breed.matches("([a-zA-z\\s]+|)")) {
                        System.out.println("Erro: não é permitido o uso de números ou caracteres especiais.");
                        System.out.println("Por favor, digite a raça do pet novamente: ");
                        breed = sc.nextLine();
                    }
            }
        }
        Address address = new Address(street, number, city);
        PetType petType = PetType.selectType(type);
        PetSex petSex = PetSex.selectSex(sex);
        Pet petCreate = new Pet(name, petType, petSex, address, age, weight,breed);
        System.out.println(petCreate);
    }
}
