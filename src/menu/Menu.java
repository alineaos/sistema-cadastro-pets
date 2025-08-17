package menu;

public class Menu {
    public void homeMenu(){
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

    public void searchPetMenu(){
        System.out.println("***************");
        System.out.println("|BUSCA DE PETS|");
        System.out.println("***************");

    }
}
