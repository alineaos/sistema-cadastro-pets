package service;

import models.Address;
import models.Pet;
import models.enums.PetSex;
import models.enums.PetType;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FileService {
    public static final File[] files = new File("petsCadastrados/").listFiles();

    public static void savePet(Pet pet) {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
        String fileName = (localDateTime.format(formatter) + pet.getName()
                .toUpperCase()
                .replaceAll(" ", ""));
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("petsCadastrados/" + fileName))) {
            bw.write(pet.toString());
            System.out.println("Pet cadastrado com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao cadastrar o pet:" + e.getMessage());
        }

    }


    public static List<String> petsFileReader() {
        List<String> allPets = new ArrayList<>();
        assert files != null;
        for (File file : files) {

            if (file.getName().equals("formulario.txt")) continue;

            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                StringBuilder sb = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    sb.append(line.replaceAll("[1-7]- ", "")).append(" - ");
                }

                if (sb.length() > 3) {
                    sb.setLength(sb.length() - 3);
                }
                allPets.add(sb.toString());
            } catch (IOException e) {
                System.out.println("Erro ao listar os pets: " + e.getMessage());
            }
        }
        return allPets;
    }

    public static List<Pet> fileToPet() {
        List<Pet> pets = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                List<String> aux = new ArrayList<>();
                if (file.getName().equals("formulario.txt")) continue;

                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        aux.add(line.replaceAll("[1-7]- ", ""));
                    }
                    if (!aux.isEmpty()) {
                        String[] address = aux.get(3).split(",");
                        Address fileToAdress = new Address(address[0], address[1], address[2]);
                        Pet filePet = new Pet(aux.get(0),
                                PetType.selectType(aux.get(1)),
                                PetSex.selectSex(aux.get(2).substring(0, 1)),
                                fileToAdress,
                                Validate.stringToAge(aux.get(4)),
                                Validate.stringToWeight(aux.get(5)),
                                aux.get(6));
                        pets.add(filePet);
                    }
                } catch (IOException e) {
                    System.out.println("Erro ao listar os pets: " + e.getMessage());
                }
            }
        }
        return pets;
    }

    public List<String> readForm() {
        File form = new File("petsCadastrados/formulario.txt");
        List<String> questions = new ArrayList<>();
        try (FileReader fileReader = new FileReader(form);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String question;
            while ((question = bufferedReader.readLine()) != null) {
                questions.add(question);
            }
        } catch (IOException e) {
            System.out.println("Não foi possível ler o formulário." + e.getMessage());
        }

        return questions;
    }

    public static void updatePet(Pet updatedPet, boolean hasUpdatedName, String oldPetName) {
        File fileToUpdate = null;

        assert files != null;
        for (File f : files) {
            if (f.getName().contains(oldPetName.toUpperCase().replaceAll(" ", ""))) {
                fileToUpdate = f;
                break;
            }
        }

        if (fileToUpdate == null) {
            System.out.println("Erro: O arquivo que contém '" + oldPetName + "' em seu nome não foi encontrado.");
            return;
        }
        if (hasUpdatedName) {
            String originalDate = fileToUpdate.getName().substring(0, 13);
            String newName = originalDate + updatedPet.getName()
                    .toUpperCase()
                    .replaceAll(" ", "");
            File renameFile = new File(fileToUpdate.getParent(), newName);
            if (fileToUpdate.renameTo(renameFile)) {
                fileToUpdate  = renameFile;
                System.out.println("Arquivo renomeado com sucesso");
            } else {
                System.out.println("Erro ao renomear o arquivo.");
                return;
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileToUpdate))) {
            bw.write(updatedPet.toString());
            System.out.println("Pet atualizado com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao atualizar o pet: " + e.getMessage());
        }
    }

    public static void deletePet(Pet petToDelete, boolean isConfirmed){
        if (isConfirmed){
            File fileToDelete = null;

            assert files != null;
            for (File f : files) {
                if (f.getName().contains(petToDelete.getName().toUpperCase().replaceAll(" ", ""))) {
                    fileToDelete = f;
                    break;
                }
            }
            if (fileToDelete == null){
                System.out.println("Não foi possível encontrar o arquivo do pet selecionado no sistema.");
                return;
            }
            if (fileToDelete.delete()){
                System.out.println("Cadastro deletado do sistema com sucesso.");
            } else {
                System.out.println("Erro ao deletar o cadastro do sistema.");
            }
        }
    }
}
