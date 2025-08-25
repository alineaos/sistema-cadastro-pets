package models;

import exceptions.PetValidateException;

public class Validate {
    final static String NAO_INFORMADO = "NÃO INFORMADO";

    public static String isEmpty(String line) {
        if (line.isBlank()) {
            return NAO_INFORMADO;
        }
        return line;
    }

    public static boolean validateName(String name) {
        if (!name.matches("([a-zA-z\\s]+|)")) {
            throw new PetValidateException("Não é permitido o uso de números ou caracteres especiais.");
        }
        return true;
    }

    public static boolean validateType(String type) {
        if (!type.equalsIgnoreCase("gato") && !type.equalsIgnoreCase("cachorro")) {
            System.out.println("Resposta inválida. Por favor, digite novamente. (Cachorro/Gato)");
            return false;
        }
        return true;
    }

    public static boolean validateSex(String sex) {
        if (!sex.equalsIgnoreCase("f") && !sex.equalsIgnoreCase("m")) {
            System.out.println("Resposta inválida. Por favor, digite novamente. (F/M)");
            return false;
        }
        return true;
    }

    public static boolean validateStreet(String street) {
        if (street.isEmpty()) {
            System.out.println("O nome da rua não pode estar em branco.");
            System.out.println("Por favor, digite novamente.");
            return false;
        }
        return true;
    }

    public static boolean validateCity(String city) {
        if (city.isEmpty()) {
            System.out.println("O nome da cidade não pode estar em branco.");
            System.out.println("Por favor, digite novamente.");
            return false;
        }
        return true;
    }

    public static boolean validateDate(String date) {
        if (!date.equalsIgnoreCase("meses") && !date.equalsIgnoreCase("anos") && !date.isEmpty()) {
            System.out.println("Opção inválida. Por favor, digite novamente. (Meses/Anos)");
            return false;
        }
        return true;
    }

    public static boolean validateAge(String age) {
        if (Double.parseDouble(age) > 20) {
            throw new PetValidateException("A idade não pode ser maior que 20 anos.");
        }
        return true;
    }

    public static boolean validateWeight(String weight) {
        if (Double.parseDouble(weight) < 0.5 || Double.parseDouble(weight) > 60) {
            throw new PetValidateException("O peso precisa estar entre 0.5kg e 60kg.");
        }
        return true;
    }

    public static boolean validateBreed(String breed) {
        if (!breed.matches("([a-zA-z\\s]+|)")) {
            System.out.println("Erro: não é permitido o uso de números ou caracteres especiais.");
            System.out.println("Por favor, digite a raça do pet novamente: ");
        }
        return true;
    }

    public static Double stringToAge(String age) {
        if (age.equalsIgnoreCase("NÃO INFORMADO")) {
            return null;
        }
        return Double.parseDouble(age.replace(" anos", "").replace(",", "."));
    }

    public static Double stringToWeight(String age) {
        if (age.equalsIgnoreCase("NÃO INFORMADO")) {
            return null;
        }
        return Double.parseDouble(age.replace("kg", "").replace(",", "."));
    }

    public static boolean petMatchesFilters(Pet pet, String key, String value){
        return switch (key) {
            case "Name" -> pet.getName().toLowerCase().contains(value.toLowerCase().toLowerCase());
            case "Type" -> pet.getType().getClassification().toLowerCase().contains(value.toLowerCase());
            case "Sex" -> pet.getSex().getClassification().toLowerCase().contains(value.toLowerCase());
            case "Age" -> pet.getAge() != null && pet.getAge().toString().toLowerCase().contains(value.toLowerCase());
            case "Weight" ->
                    pet.getWeight() != null && pet.getWeight().toString().toLowerCase().contains(value.toLowerCase());
            case "Breed" -> pet.getBreed().toLowerCase().contains(value.toLowerCase());
            case "Address" -> pet.getAddress().toString().toLowerCase().contains(value.toLowerCase());
            default -> false;
        };
    }
}
