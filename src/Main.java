import java.util.*;

public class Main {

    public static FileService files = new FileService();
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
                    case 1 -> PetService.createPet();
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Letras e caracteres especiais não são aceitos. " +
                        "Por favor, digite um número entre 1 e 6");
                sc.nextLine();
            }



        } while (true);
    }


}
