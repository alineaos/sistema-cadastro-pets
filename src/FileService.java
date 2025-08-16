import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FileService {

    public static void savePet(Pet pet) {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
        String fileName = (localDateTime.format(formatter) + pet.getName()
                .toUpperCase()
                .replaceAll(" ", ""));
        File file = new File("petsCadastrados/" + fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Não foi possível criar o arquivo." + e.getMessage());
        }

        try (FileWriter fileWriter = new FileWriter(file);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(pet.toString());
        } catch (IOException e) {
            System.out.println("Não foi possível escrever no arquivo." + e.getMessage());
        }

    }

    public static void writeFile(File file, String text) {
        try (FileWriter fileWriter = new FileWriter(file);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(text);
        } catch (IOException e) {
            System.out.println("Não foi possível escrever no arquivo." + e.getMessage());
        }
    }

    public void readfile(File file) {
        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String linha;
            while ((linha = bufferedReader.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (IOException e) {
            System.out.println("Não foi possível ler o arquivo." + e.getMessage());
        }
    }

    public List<String> readForm() {
        File form = new File("petsCadastrados/formulario.txt");
        List<String> questions = new ArrayList<String>();
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


}
