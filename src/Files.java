import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Files {

    public void createFile(String fileName){
        File file = new File("files/" + fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Não foi possível criar o arquivo." + e.getMessage());
        }
    }

    public void writefile(File file, String text){
        try(FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)){
        bufferedWriter.write(text);
        }catch (IOException e){
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

    public List<String> readForm(){
        File form = new File("files/formulario.txt");
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
