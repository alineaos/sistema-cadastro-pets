import java.util.List;
import java.util.Scanner;

public class PetService {
    public static final Scanner sc = new Scanner(System.in);
    public static final FileService files = new FileService();

    public static void createPet() {
        List<String> questions = files.readForm();
        String name = null, type = null, sex = null, breed = null;
        String street = null, number = null, city = null;
        double age = 0, weight = 0;
        System.out.println("Para cadastrar um pet, responda as seguintes perguntas:");
        for (int i = 1; i <= (questions.size()); i++) {
            System.out.println(questions.get(i - 1));
            switch (i) {
                case 1:
                    do {
                        name = sc.nextLine();
                        if (!name.matches("([a-zA-z\\s]+|)")) {
                            System.out.println("Erro: não é permitido o uso de números ou caracteres especiais.");
                            System.out.println("Por favor, digite o nome do pet novamente: ");
                        }
                    } while (!name.matches("([a-zA-z\\s]+|)"));
                    break;

                case 2:
                    do {
                        type = sc.nextLine();
                        if (!type.equalsIgnoreCase("cachorro") && !type.equalsIgnoreCase("gato")) {
                            System.out.println("Erro: Resposta inválida. Por favor, digite novamente.");
                        }
                    } while (!type.equalsIgnoreCase("cachorro") && !type.equalsIgnoreCase("gato"));
                    break;

                case 3:
                    do {
                        sex = sc.nextLine();
                        if (!sex.equalsIgnoreCase("f") && !sex.equalsIgnoreCase("m")) {
                            System.out.println("Erro: Resposta inválida. Por favor, digite novamente.");
                        }
                    } while (!sex.equalsIgnoreCase("f") && !sex.equalsIgnoreCase("m"));
                    break;

                case 4:
                    do {
                        System.out.print("Digite a rua: ");
                        street = sc.nextLine();
                        if (street.isEmpty()) {
                            System.out.println("Erro: O nome da rua não pode estar em branco.");
                            System.out.println("Por favor, digite novamente.");
                        }
                    } while (street.isEmpty());

                    System.out.print("Digite o número: ");
                    number = sc.nextLine();

                    do {
                        System.out.print("Digite a cidade: ");
                        city = sc.nextLine();
                        if (city.isEmpty()) {
                            System.out.println("Erro: O nome da cidade não pode estar em branco.");
                            System.out.println("Por favor, digite novamente.");

                        }
                    } while (city.isEmpty());
                    break;

                case 5:
                    String tempo;
                    do {
                        System.out.println("A idade é em meses ou anos?");
                        tempo = sc.nextLine();
                        if (!tempo.equalsIgnoreCase("meses") && !tempo.equalsIgnoreCase("anos")) {
                            System.out.println("Erro: Opção inválida. Por favor, digite novamente.");
                        }
                    } while (!tempo.equalsIgnoreCase("meses") && !tempo.equalsIgnoreCase("anos"));

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
                    sc.nextLine();
                    do {
                        breed = sc.nextLine();
                        if (!breed.matches("([a-zA-z\\s]+|)")) {
                            System.out.println("Erro: não é permitido o uso de números ou caracteres especiais.");
                            System.out.println("Por favor, digite a raça do pet novamente: ");
                        }

                    } while (!breed.matches("([a-zA-z\\s]+|)"));
            }
        }

        Address address = new Address(street, number, city);
        PetType petType = PetType.selectType(type);
        PetSex petSex = PetSex.selectSex(sex);
        Pet petCreated = new Pet(name, petType, petSex, address, age, weight, breed);
        FileService.savePet(petCreated);
    }

}
