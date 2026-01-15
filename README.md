<p align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java">
  <img src="https://img.shields.io/badge/JDK-21+-informational?style=for-the-badge" alt="JDK 21+">
</p>
<h1 align="center">🐶 Sistema de Cadastro de Animais 🐱</h1>

## ℹ️ Sobre o Projeto
Sistema desenvolvido para organizar os cadastros dos animais de um abrigo, com um menu interativo e implementando as funcionalidades CRUD. A aplicação funciona via CLI (Interface de Linha de Comando).

 ### ✨ Funcionalidades do Sistema

- Cadastrar novo animal
- Listar todos os animais ou filtrar por critérios
- Atualizar o cadastro
- Excluir um cadastro

### :page_facing_up: Informações utilizados no cadastro
- Nome
- Tipo (Gato/Cachorro)
- Sexo
- Endereço onde foi encontrado
- Idade (em anos)
- Peso (em kg)
- Raça

### 📂 Armazenamento de dados
- Esse projeto utiliza **persistência de arquivos `.txt`** para salvar os dados.
- Os dados são armazenados no formato "Data e horário da criação do cadastro + T + Nome em maiúsculas". Exemplo: `20250817T1543BOB.txt`


### :computer: Tecnologias utilizadas
Este sistema foi desenvolvido exclusivamente em Java, sem o uso de frameworks externos. Seguem abaixo alguns dos conceitos aplicados:
- Orientação a Objetos
- Tratamento de Exceções
- Manipulação de Arquivos (Java IO)
- Collections: List e Map
- Estruturação em Camadas (models, repository e service)

## 🚀 Executando a Aplicação
### 🛠️ Pré-requisitos
- **JDK 21+** para compilar e executar o código.
- **Git 2** para clonar o repositório.

---
1. **Clone o repositório**
```
git clone https://github.com/alineaos/sistema-cadastro-animais.git
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
