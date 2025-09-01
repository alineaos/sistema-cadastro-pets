# Sistema de Cadastros de Pets

# ℹ️ Sobre o Projeto
Esse projeto visa criar um <ins>sistema para o cadastro de pets de um abrigo de animais</ins>. Toda a aplicação funciona através da CLI (Interface de Linha de Comando). Apenas a linguagem de programação Java foi utilizada durante a criação do sistema

Os administradores do abrigo podem usar as seguintes funcionalidades:

- Cadastrar um novo animal
- Listar todos os animais cadastrados
- Listar os animais cadastrados utilizando critérios
- Alterar o cadastro de um animal
- Deletar o cadastro de um animal

Para cadastrar um animal, o usuário deverá preencher o nome, tipo (gato ou cachorro), sexo do animal, endereço onde foi encontrado, idade, peso e raça.

Cada cadastro será armazenado em um arquivo .txt, no qual o nome recebe a data e hora no momento da criação do arquivo e o nome do pet.

## 🚀 Executando o Projeto
### 🛠️ Pré-requisitos
- JDK 21
- Git 2

---
1. **Clone o repositório**
```
git clone https://github.com/alineaos/sistema-cadastro-pets.git
```

2. **Navegue até a pasta do repositório**
```
cd sistema-cadastro-pets
```

3. **Compile o projeto**
```
javac -d bin -sourcepath src src/Main.java
```

4. **Execute a aplicação**
```
java -cp bin Main
```

---

### Projeto proposto por Lucas Carrilho - [@devmagro](https://www.linkedin.com/in/karilho/)

[Link original](https://github.com/karilho/desafioCadastro)