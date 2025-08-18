package menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public void homeMenu() {
        System.out.println("*************");
        System.out.println("|MENU INICIAL|");
        System.out.println("*************");
        System.out.println("Escolha uma opção:");
        System.out.println("[1] Cadastrar um novo pet");
        System.out.println("[2] Listar todos os pets cadastrados");
        System.out.println("[3] Listar pets  por algum critério (nome, idade, raça, etc)");
        System.out.println("[4] Alterar os dados de um pet cadastrado");
        System.out.println("[5] Deletar um pet cadastrado");
        System.out.println("[6] Sair");
    }

    public void searchPetCriteriaMenu() {
        List<String> criterias = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        System.out.println("****************");
        System.out.println("|BUSCA DE PETS|");
        System.out.println("****************");
        int criteria;
        do {
            System.out.println("Você deseja realizar a busca com 1 ou 2 parâmetros?");
            System.out.println("Digite 0 para voltar ao menu anterior.");
            criteria = sc.nextInt();
        } while (criteria < 0 || criteria > 2);
        if (criteria == 0) return;
        for (int i = 1; i <= criteria; i++) {
            System.out.printf("Selecione o %dº critério\n", i);
            System.out.println("[1] Nome");
            System.out.println("[2] Sexo");
            System.out.println("[3] Idade");
            System.out.println("[4] Peso");
            System.out.println("[5] Raça");
            System.out.println("[6] Endereço");
            System.out.println("[7] Voltar para o menu anterior.");
            int option = sc.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Digite o nome: ");
                    criterias.add(sc.nextLine());
                    break;
                case 2:
                    System.out.println("Digite o sexo: ");
            }

        }


    }
}
