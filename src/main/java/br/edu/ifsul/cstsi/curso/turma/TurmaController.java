package br.edu.ifsul.cstsi.curso.turma;

import br.edu.ifsul.cstsi.curso.curso.Curso;
import br.edu.ifsul.cstsi.curso.curso.CursoService;
import org.springframework.stereotype.Controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Controller
public class TurmaController {

    private static final Scanner input = new Scanner(System.in);

    private static TurmaService turmaService;
    private static CursoService cursoService;

    public TurmaController(TurmaService turmaService, CursoService cursoService) {
        TurmaController.turmaService = turmaService;
        TurmaController.cursoService = cursoService;
    }

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.print("\n\"-------  Menu Turma -------\"");
            System.out.print(
                    """

                            1. Inserir uma turma
                            2. Atualizar uma turma
                            3. Excluir uma turma
                            4. Listar todas turmas
                            5. Buscar turma pelo código
                            6. Buscar turma pelo turno

                            Opção (Zero p/sair):\s""");
            opcao = input.nextInt();
            input.nextLine();
            switch (opcao) {
                case 1 -> inserir();
                case 2 -> atualizar();
                case 3 -> excluir();
                case 4 -> selectturmas();
                case 5 -> selectturmasByCodTurma();
                case 6 -> selectturmasByTurno();


                default -> {
                    if (opcao != 0) System.out.println("Opção inválida.");
                }
            }
        } while (opcao != 0);
    }

    //opcao 1
    private static void inserir() {
        Turma turma = new Turma();
        System.out.println("\n+++++ Cadastro de turma ++++++");
        System.out.println("Digite o turno: ");
        turma.setTurno(input.nextLine());

        System.out.println("Digite a data de inicio (DD/MM/YYYY): ");
        LocalDate data_inicio = LocalDate.parse(input.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        turma.setDt_ini(Date.valueOf(data_inicio));

        input.nextLine();

        System.out.println("Digite a data do fim (DD/MM/YYYY): ");
        LocalDate data_fim = LocalDate.parse(input.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        turma.setDt_fim(Date.valueOf(data_fim));

        input.nextLine();

        System.out.println("Digite a hora inicial (24h): ");
        turma.setHr_ini(input.nextInt());

        input.nextLine();

        System.out.println("Digite a hora final (24h): ");
        turma.setHr_fim(input.nextInt());

        input.nextLine();

        System.out.println("Digite a quantidade de vagas: ");
        turma.setQtd_vagas(input.nextInt());

        input.nextLine();

        System.out.println("Cursos cadastrados: " + cursoService.getCursos());
        System.out.println("Digite o id do curso para a turma: ");
        Curso curso = cursoService.getCursoById(input.nextLong());
        turma.setCursoByCodCurso(curso);

        System.out.println("Turma salva com sucesso: " + turmaService.insert(turma));
    }

    private static void atualizar() {
        System.out.println("\n++++++ Alterar uma turma ++++++");
        Turma turma;
        int opcao = 0;
        do {
            System.out.println("\nTurmas cadastradas: " + turmaService.getTurmas());
            System.out.println("\nDigite o codigo da turma (Zero p/ sair)");
            Long codigo = input.nextLong();
            input.nextLine();
            if (codigo == 0) {
                opcao = 0;
            } else {
                turma = turmaService.getTurmaByCodTurma(codigo);
                if (turma == null) {
                    System.out.println("Codigo invalido.");
                }else {
                    System.out.println("Turno: " + turma.getTurno());
                    System.out.println("Alterar? (0-sim/1-não) ");
                    if(input.nextInt() == 0){
                        input.nextLine();
                        System.out.println("Digite o novo turno: ");
                        turma.setTurno(input.nextLine());
                    }

                    System.out.println("Data inicial: " + turma.getDt_ini());
                    System.out.println("Alterar? (0-sim/1-não) ");
                    if(input.nextInt() == 0){
                        input.nextLine();
                        System.out.println("Digite a nova data de inicio: ");
                        LocalDate data_inicio = LocalDate.parse(input.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        turma.setDt_ini(Date.valueOf(data_inicio));

                    }

                    System.out.println("Data final: " + turma.getDt_fim());
                    System.out.println("Alterar? (0-sim/1-não) ");
                    if(input.nextInt() == 0){
                        input.nextLine();
                        System.out.println("Digite a nova data de fim: ");
                        LocalDate data_fim = LocalDate.parse(input.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        turma.setDt_fim(Date.valueOf(data_fim));
                    }

                    System.out.println("Hora inicial: " + turma.getHr_ini());
                    System.out.println("Alterar? (0-sim/1-não) ");
                    if(input.nextInt() == 0){
                        input.nextLine();
                        System.out.println("Digite a nova hora: ");
                        turma.setHr_ini(input.nextInt());
                    }

                    System.out.println("Hora final: " + turma.getHr_fim());
                    System.out.println("Alterar? (0-sim/1-não) ");
                    if(input.nextInt() == 0){
                        input.nextLine();
                        System.out.println("Digite a nova hora: ");
                        turma.setHr_fim(input.nextInt());
                    }

                    System.out.println("Vagas: " + turma.getQtd_vagas());
                    System.out.println("Alterar? (0-sim/1-não) ");
                    if(input.nextInt() == 0){
                        input.nextLine();
                        System.out.println("Digite a nova quantidade de vagas: ");
                        turma.setQtd_vagas(input.nextInt());
                    }

                    System.out.println("Curso atual: " + turma.getCursoByCodCurso());
                    System.out.print("Alterar? (0-sim/1-não) ");
                    if (input.nextInt() == 0) {
                        input.nextLine();

                        System.out.println("Cursos cadastrados: " + cursoService.getCursos());
                        System.out.println("Digite o id do novo curso: ");
                        Curso curso = cursoService.getCursoById(input.nextLong());
                        turma.setCursoByCodCurso(curso);


                    }

                    if (turmaService.update(turma) != null){
                        System.out.println("Turma atualizada com sucesso." + turmaService.getTurmaByCodTurma(turma.getCod_turma()));
                    }else {
                        System.out.println("Não foi possivel atualizar.");
                    }
                    opcao = 1;

                }
            }


        } while (opcao != 0);

    }


    //opcao 3
    private static void excluir() {
        System.out.println("\n+++++ Excluir uma turma ++++++");
        Turma turma;
        int opcao = 0;
        do {
            System.out.println("\nDigite o codigo da turma (Zero p/sair)");
            Long codigo = input.nextLong();
            input.nextLine();
            if(codigo==0) {
                opcao = 0;
            } else if (codigo > 0){
                turma = turmaService.getTurmaByCodTurma(codigo);
                if (turma == null){
                    System.out.println("Código invalido.");
                } else {
                    System.out.println(turma);
                    System.out.println("Excluir esta turma? (0-sim/1-não)");
                    if (input.nextInt() == 0){
                        input.nextLine();
                        turmaService.delete(turma.getCod_turma());
                        System.out.println("Turma excluida com sucesso.");
                    }
                }
            } else {
                System.out.println("Digite um codigo valido!!!");
            }
        } while (opcao != 0);
    }

    //opcao 4
    private static void selectturmas(){
        System.out.println("\nLista de turmas cadastradas." + turmaService.getTurmas());
    }

    //opcao 5
    private static void selectturmasByCodTurma(){
        System.out.println("\nDigite o codigo da turma: ");
        Turma turma = turmaService.getTurmaByCodTurma(input.nextLong());
        input.nextLine();
        if(turma != null){
            System.out.println(turma);
        }else {
            System.out.println("Codigo nao encontrado");
        }
    }

    //opcao 6
    private static void selectturmasByTurno(){
        System.out.println("Digite o turno: ");
        String turno = input.next();
        System.out.println("Chave de pesquisa: " + turno);
        List<Turma> turmas = turmaService.getTurmasByTurno(turno + "%");
        if (turmas.isEmpty()){
            System.out.println("Não há registros correspondentes.");
        } else {
            System.out.println(turmas);
        }
    }
}