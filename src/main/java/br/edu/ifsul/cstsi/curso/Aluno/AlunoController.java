package br.edu.ifsul.cstsi.curso.Aluno;

import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Scanner;

@Controller
public class AlunoController {

    private static final Scanner input = new Scanner(System.in);
    private static AlunoService AlunoService;

    public AlunoController(AlunoService AlunoService) {
        AlunoController.AlunoService = AlunoService;
    }

    public static void main(String[] args) {

        int opcao;
        do {
            System.out.print("\n\"-------  MENU ALUNO -------\"");
            System.out.print(
                    """
                                
                            1. Inserir novo Aluno
                            2. Atualizar um Aluno
                            3. Excluir um Aluno 
                            4. Listar todos os Alunos
                            5. Buscar Aluno pelo código
                            6. Buscar Aluno pelo nome
                                                   
                            Opção (Zero p/sair):\s""");
            opcao = input.nextInt();
            input.nextLine();
            switch (opcao) {
                case 1 -> inserir();
                case 2 -> atualizar();
                case 3 -> excluir();
                case 4 -> selectalunos();
                case 5 -> selectalunosById();
                case 6 -> selectalunosByNome();

                default -> {
                    if (opcao != 0) System.out.println("Opção inválida.");
                }
            }
        } while (opcao != 0);
    }

    //opção 1
    private static void inserir() {
        //nextline para string
        //netint par inteiro

        Aluno aluno = new Aluno();
        System.out.println("\n++++++ Cadastro de novo aluno ++++++");
        System.out.print("Digite o nome do aluno: ");
        aluno.setNome(input.nextLine());

        System.out.print("Digite o cpf do aluno: ");
        aluno.setCpf(input.nextLine());

        System.out.println("Digite o rg do aluno: ");
        aluno.setRg(input.nextLine());

        System.out.println("Digite o telefone do aluno: ");
        aluno.setFone(input.nextLine());

        System.out.println("Digite o endereço do aluno: ");
        aluno.setEndereco(input.nextLine());

        System.out.println("aluno salvo com sucesso:" + AlunoService.insert(aluno));
    }

    //opção 2
    private static void atualizar() {
        System.out.println("\n++++++ Alterar um aluno ++++++");
        Aluno aluno;
        int opcao = 0;
        do {
            System.out.print("\nDigite o código do aluno (Zero p/sair): ");
            int codigo = input.nextInt();
            input.nextLine();
            if (codigo == 0) {
                opcao = 0;
            } else {
                aluno = AlunoService.getAlunoById(codigo);
                if (aluno == null) {
                    System.out.println("Código inválido.");
                } else {
                    System.out.println("Nome: " + aluno.getNome());
                    System.out.print("Alterar? (0-sim/1-não) ");
                    if (input.nextInt() == 0) {
                        input.nextLine();
                        System.out.println("Digite o novo nome do aluno: ");
                        aluno.setNome(input.nextLine());
                    }

                    System.out.println("Cpf: " + aluno.getCpf());
                    System.out.println("Alterar? (0-sim/1-não) ");
                    if (input.nextInt() == 0) {
                        input.nextLine();
                        System.out.println("Digite o novo cpf do aluno: ");
                        aluno.setCpf(input.nextLine());
                    }

                    System.out.println("Rg: " + aluno.getRg());
                    System.out.println("Alterar? (0-sim/1-não) ");
                    if (input.nextInt() == 0) {
                        input.nextLine();
                        System.out.println("Digite o novo rg do aluno: ");
                        aluno.setRg(input.nextLine());
                    }

                    System.out.println("Telefone: " + aluno.getFone());
                    System.out.println("Alterar? (0-sim/1-não) ");
                    if (input.nextInt() == 0) {
                        input.nextLine();
                        System.out.println("Digite o novo telefone do aluno: ");
                        aluno.setFone(input.nextLine());
                    }

                    System.out.println("Endereço: " + aluno.getEndereco());
                    System.out.println("Alterar? (0-sim/1-não) ");
                    if (input.nextInt() == 0) {
                        input.nextLine();
                        System.out.println("Digite o novo endereço do aluno: ");
                        aluno.setEndereco(input.nextLine());
                    }

                    if (AlunoService.update(aluno) != null) {
                        System.out.println("Aluno atualizado com sucesso!");
                    } else {
                        System.out.println("Não foi possível atualizar!");
                    }

                    opcao = 1;
                }
            }
        } while (opcao != 0);
    }

    //opção 3
    private static void excluir() {
        System.out.println("\n++++++ Excluir um aluno ++++++");
        Aluno aluno;
        int opcao = 0;
        do {
            System.out.print("\nDigite o código do aluno (Zero p/sair): ");
            int codigo = input.nextInt();
            input.nextLine();
            if (codigo == 0) {
                opcao = 0;
            } else if (codigo > 0) {
                aluno = AlunoService.getAlunoById(codigo);
                if (aluno == null) {
                    System.out.println("Código inválido.");
                } else {
                    System.out.println(aluno);
                    System.out.print("Excluir este aluno? (0-sim/1-não) ");
                    if (input.nextInt() == 0) {
                        input.nextLine();
                        AlunoService.delete(aluno.getCodAluno());
                        System.out.println("aluno excluído com sucesso:" + aluno);
                    }

                }
            } else {
                System.out.println("Digite um código válido!!!");
            }
        } while (opcao != 0);
    }

    //opção 4
    private static void selectalunos() {
        System.out.println("\nLista de alunos cadastrados no banco de dados:\n" + AlunoService.getAlunos());
    }

    //opção 5
    private static void selectalunosById() {
        System.out.print("\nDigite o código do aluno: ");
        Aluno aluno = AlunoService.getAlunoById(input.nextInt());
        input.nextLine();
        if (aluno != null) {
            System.out.println(aluno);
        } else {
            System.out.println("Código não localizado.");
        }
    }

    //opção 5
    private static void selectalunosByNome() {
        System.out.print("Digite o nome do aluno: ");
        String nome = input.next();
        System.out.println("Chave de pesquisa: " + nome);
        List<Aluno> alunos = AlunoService.getAlunosByNome(nome + "%");
        if (alunos.isEmpty()) {
            System.out.println("Não há registros correspondentes para: " + nome);
        } else {
            System.out.println(alunos);
        }
    }

}