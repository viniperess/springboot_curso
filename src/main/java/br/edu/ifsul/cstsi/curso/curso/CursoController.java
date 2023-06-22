package br.edu.ifsul.cstsi.curso.curso;

import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Scanner;

@Controller
public class CursoController {
    private static final Scanner input = new Scanner(System.in);

    private static CursoService cursoService;

    public CursoController(CursoService cursoService) {
        CursoController.cursoService = cursoService;
    }
    public static void main(String[] args) {
        int opcao;
        do {
            System.out.print("\n\"-------  MENU CURSO -------\"");
            System.out.print(
                    """
    
                        1. Inserir novo curso
                        2. Atualizar um curso
                        3. Excluir um curso 
                        4. Listar todos os cursos
                        5. Buscar curso pelo código
                        6. Buscar curso pelo nome
                       
                        Opção (Zero p/sair):\s""");
            opcao = input.nextInt();
            input.nextLine();
            switch (opcao) {
                case 1 -> inserir();
                case 2 -> atualizar();
                case 3 -> excluir();
                case 4 -> selectcursos();
                case 5 -> selectcursosById();
                case 6 -> selectcursosByNome();

                default -> {
                    if (opcao != 0) System.out.println("Opção inválida.");
                }
            }
        } while (opcao != 0);
    }

    //opção 1
    private static void inserir(){
        Curso curso = new Curso();
        System.out.println("\n+++++ Cadastro novo Curso +++++");
        System.out.println("Digite o nome do curso: ");
        curso.setNome(input.nextLine());

        System.out.println("Digite o Pré requisito do curso");
        curso.setPre_requisito(input.nextLine());

        System.out.println("Digite o valor do curso: ");
        curso.setValor(input.nextDouble());
        input.nextLine();

        System.out.println("Digite a carga horaria do curso: ");
        curso.setCarga_hora(input.nextInt());
        input.nextLine();

        System.out.println(curso);
        System.out.println("Curso salvo com sucesso: " + cursoService.insert(curso));

    }

    //opção 2
    private static void atualizar() {
        System.out.println("\n+++++ Atualizar um curso ++++++");
        Curso curso;
        int opcao = 0;
        do {
            System.out.println("\nDigite o código do curso (Zero p/ sair):");
            Long codigo = input.nextLong();
            input.nextLine();
            if (codigo == 0) {
                opcao = 0;
            } else {
                curso = cursoService.getCursoById(codigo);
                if (curso == null) {
                    System.out.println("Código invalido.");
                } else {
                    System.out.println("Nome: " + curso.getNome());
                    System.out.println("Alterar? (0-sim/1-não) ");
                    if (input.nextInt() == 0) {
                        input.nextLine();
                        System.out.println("Digite o novo nome: ");
                        curso.setNome(input.nextLine());
                    }

                    System.out.println("Pré requisito: " + curso.getPre_requisito());
                    System.out.println("Alterar? (0-sim/1-não)");
                    if (input.nextInt() == 0) {
                        input.nextLine();
                        System.out.println("Digite o novo pré requisito: ");
                        curso.setPre_requisito(input.nextLine());
                    }

                    System.out.println("Valor: " + curso.getValor());
                    System.out.println("Alterar? (0-sim/1-não)");
                    if (input.nextInt() == 0) {
                        input.nextLine();
                        System.out.println("Digite o novo valor: ");
                        curso.setValor(input.nextDouble());
                    }

                    System.out.println("Carga Horaria: " + curso.getCarga_hora());
                    System.out.println("Alterar? (0-sim/1-não)");
                    if (input.nextInt() == 0) {
                        input.nextLine();
                        System.out.println("Digite a nova carga horaria: ");
                        curso.setCarga_hora(input.nextInt());
                    }

                    if (cursoService.update(curso) != null) {
                        System.out.println("Curso atualizado com sucesso.");
                    } else {
                        System.out.println("Não foi possivel atualizar o curso.");
                    }

                    opcao = 1;
                }
            }
        } while (opcao != 0);
    }
    //opcao 3
    private static void excluir(){
            System.out.println("\n+++++ Excluir um curso +++++");
            Curso curso;
            int opcao = 0;
            do {
                System.out.println("Digite o código do curso (Zero p/ sair)");
                Long codigo = input.nextLong();
                input.nextLine();
                if(codigo == 0) {
                    opcao = 0;
                }else if (codigo > 0){
                    curso = cursoService.getCursoById(codigo);
                    if(curso == null){
                        System.out.println("Codigo invalido.");
                    } else {
                        System.out.println(curso);
                        System.out.println("Excluir esse curso? (0-sim/1-não)");
                        if(input.nextInt() == 0){
                            input.nextLine();
                            cursoService.delete(curso.getCod_curso());
                            System.out.println("Curso excluido: " + curso);
                        }
                    }
                } else {
                    System.out.println("Digite um codigo valido!!!");
                }
            } while (opcao != 0);
        }

        //opcao 4
        private static void selectcursos(){
            System.out.println("\nLista de cursos cadastrados:\n" + cursoService.getCursos());
        }

        //opcao 5
    private static void selectcursosById(){
        System.out.println("\nDigite o codigo do curso: ");
        Curso curso = cursoService.getCursoById(input.nextLong());
        input.nextLine();
        if(curso != null) {
            System.out.println(curso);
        } else {
            System.out.println("Codigo invalido");
        }
    }

  //opcao 6
  private static void selectcursosByNome(){
      System.out.println("Digite o nome do curso: ");
      String nome = input.next();
      System.out.println("Chave de pesquisa: " + nome);
      List<Curso> cursos = cursoService.getCursosByNome(nome + "%");
      if(cursos.isEmpty()) {
          System.out.println("Não há registros correspondentes para: " + nome);
      } else {
          System.out.println(cursos);
      }

  }

}
