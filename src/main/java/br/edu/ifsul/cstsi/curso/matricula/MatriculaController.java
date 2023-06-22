package br.edu.ifsul.cstsi.curso.matricula;

import br.edu.ifsul.cstsi.curso.aluno.Aluno;
import br.edu.ifsul.cstsi.curso.aluno.AlunoService;
import br.edu.ifsul.cstsi.curso.turma.Turma;
import br.edu.ifsul.cstsi.curso.turma.TurmaService;
import org.springframework.stereotype.Controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Controller
public class MatriculaController {
    private static final Scanner input = new Scanner(System.in);

    private static br.edu.ifsul.cstsi.curso.matricula.MatriculaService matriculaService;
    private static br.edu.ifsul.cstsi.curso.turma.TurmaService turmaService;
    private static br.edu.ifsul.cstsi.curso.aluno.AlunoService alunoService;

    public MatriculaController(MatriculaService matriculaService, TurmaService turmaService, AlunoService alunoService) {
        MatriculaController.matriculaService = matriculaService;
        MatriculaController.turmaService = turmaService;
        MatriculaController.alunoService = alunoService;
    }

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.print("\n\"-------  Menu Matricula -------\"");
            System.out.print(
                    """

                            1. Inserir uma matricula
                            2. Atualizar uma matricula
                            3. Excluir uma matricula
                            4. Listar todas matriculas
                            5. Buscar matricula pelo código
                            6. Buscar matricula pela forma de pagamento

                            Opção (Zero p/sair):\s""");
            opcao = input.nextInt();
            input.nextLine();
            switch (opcao) {
                case 1 -> inserir();
                case 2 -> atualizar();
                case 3 -> excluir();
                case 4 -> selectmatriculas();
                case 5 -> selectmatriculasByCodMatricula();
                case 6 -> selectmatriculasByFormaPg();


                default -> {
                    if (opcao != 0) System.out.println("Opção inválida.");
                }
            }
        } while (opcao != 0);
    }

    private static void inserir() {
        Matricula matricula = new Matricula();
        System.out.println("\n++++++ Cadastro de matricula +++++");
        System.out.println("Digite o numero da matricula: ");
        matricula.setNum_mat(input.nextInt());

        input.nextLine();

        System.out.println("Digite a data (DD/MM/YYYY): ");
        LocalDate data = LocalDate.parse(input.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        matricula.setData(Date.valueOf(data));

        input.nextLine();

        System.out.println("Digite a forma de pagamento: ");
        matricula.setForma_pagto(input.nextLine());

        System.out.println("Turmas cadastradas: " + turmaService.getTurmas());
        System.out.println("Digite o id da turma para a matricula: ");
        Turma turma = turmaService.getTurmaByCodTurma(input.nextLong());
        matricula.setTurmaByCodTurma(turma);

        System.out.println("Alunos cadastrados: " + alunoService.getAlunos());
        System.out.println("Digite o id do aluno para a matricula: ");
        Aluno aluno = alunoService.getAlunoById(input.nextLong());
        matricula.setAlunoByCodAluno(aluno);

        System.out.println("Matricula salva com sucesso: " + matriculaService.insert(matricula));
    }

    private static void atualizar() {
        System.out.println("\n++++++ Alterar matricula +++++");
        Matricula matricula;
        int opcao = 0;
        do {
            System.out.println("\nMatriculas cadastradas: " + matriculaService.getMatriculas());
            System.out.println("\nDigite o codigo da matricula (Zero p/sair)");
            Long codigo = input.nextLong();
            input.nextLine();
            if (codigo == 0) {
                opcao = 0;
            } else {
                matricula = matriculaService.getMatriculaByCodMatricula(codigo);
                if (matricula == null) {
                    System.out.println("Codigo invalido.");
                } else {
                    System.out.println("Numero matricula: " + matricula.getNum_mat());
                    System.out.println("Alterar? (0-sim/1-não) ");
                    if (input.nextInt() == 0) {
                        input.nextLine();
                        System.out.println("Digite o novo numero: ");
                        matricula.setNum_mat(input.nextInt());
                    }

                    System.out.println("Data: " + matricula.getData());
                    System.out.println("Alterar? (0-sim/1-não)");
                    if (input.nextInt() == 0) {
                        input.nextLine();
                        System.out.println("Digite a nova data: ");
                        LocalDate data = LocalDate.parse(input.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        matricula.setData(Date.valueOf(data));
                    }

                    System.out.println("Forma pagmento: " + matricula.getForma_pagto());
                    System.out.println("Alterar? (0-sim/1-não)");
                    if (input.nextInt() == 0) {
                        input.nextLine();
                        System.out.println("Digite a nova forma: ");
                        matricula.setForma_pagto(input.nextLine());
                    }

                    System.out.println("Turma atual: " + matricula.getTurmaByCodTurma());
                    System.out.println("Alterar? (0-sim/1-não)");
                    if (input.nextInt() == 0) {
                        input.nextLine();

                        System.out.println("Turmas cadastradas: " + turmaService.getTurmas());
                        System.out.println("Digite o id da nova turma: ");
                        Turma turma = turmaService.getTurmaByCodTurma(input.nextLong());

                        matricula.setTurmaByCodTurma(turma);
                    }

                    System.out.println("Aluno atual: " + matricula.getAlunoByCodAluno());
                    System.out.println("Alterar? (0-sim/1-não) ");
                    if (input.nextInt() == 0) {
                        input.nextLine();

                        System.out.println("Alunos cadastrados: " + alunoService.getAlunos());
                        System.out.println("Digite o id do novo aluno: ");
                        Aluno aluno = alunoService.getAlunoById(input.nextLong());

                        matricula.setAlunoByCodAluno(aluno);
                    }

                    if (matriculaService.update(matricula) != null) {
                        System.out.println("Matricula atualizada com sucesso. " + matriculaService.getMatriculaByCodMatricula(matricula.getCod_matricula()));
                    } else {
                        System.out.println("Não foi possivel atualizar.");
                    }
                    opcao = 1;
                }
            }


        } while (opcao != 0);

    }

    private static void excluir(){
        System.out.println("\n+++++ Excluir uma matricula ++++++");
        Matricula matricula;
        int opcao = 0;
        do {
            System.out.println("\nDigite o codigo da matricula (Zero p/sair)");
            Long codigo = input.nextLong();
            input.nextLine();
            if(codigo == 0) {
                opcao = 0;
            } else if (codigo > 0){
                matricula = matriculaService.getMatriculaByCodMatricula(codigo);
                if (matricula == null){
                    System.out.println("Código invalido.");
                } else {
                    System.out.println(matricula);
                    System.out.println("Excluir esta matricula? (0-sim/1-não)");
                    if (input.nextInt() == 0){
                        input.nextLine();
                        matriculaService.delete(matricula.getCod_matricula());
                        System.out.println("Matricula excluida com sucesso.");
                    }
                }
            } else {
                System.out.println("Digite um codigo valido!!!");
            }
        } while (opcao != 0);
    }

    private static void selectmatriculas(){
        System.out.println("\nLista de matriculas cadastradas: " + matriculaService.getMatriculas());
    }

    private static void selectmatriculasByCodMatricula(){
        System.out.println("\nDigite o codigo da matricula: ");
        Matricula matricula = matriculaService.getMatriculaByCodMatricula(input.nextLong());
        input.nextLine();
        if (matricula != null){
            System.out.println(matricula);
        }else {
            System.out.println("Codigo nao encontrado");
        }
    }

    private static void selectmatriculasByFormaPg(){
        System.out.println("Digite a forma de pagamento: ");
        String forma = input.next();
        System.out.println("Chave de pesquisa: " + forma);
        List<Matricula> matriculas = matriculaService.getMatriculasByFormaPg(forma + "%");
        if (matriculas.isEmpty()){
            System.out.println("Não ha registros correspondentes");
        } else {
            System.out.println(matriculas);
        }
    }
}
