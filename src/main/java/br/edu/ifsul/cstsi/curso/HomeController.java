package br.edu.ifsul.cstsi.curso;

//import br.edu.ifsul.cstsi.curso.curso.CursoController;
//import br.edu.ifsul.cstsi.curso.matricula.MatriculaController;
//import br.edu.ifsul.cstsi.curso.turma.TurmaController;

import br.edu.ifsul.cstsi.curso.Aluno.AlunoController;

import java.util.Scanner;
public class HomeController {

    private static final Scanner input = new Scanner(System.in);

    public static void main() {

        int opcao;
        do {
            System.out.print("\n-------  Home -------");
            System.out.print(
                    """
    
                        
                        1. Manter Aluno
                        2. Manter Matricula
                        3. Manter Turma
                        4. Manter Curso
                        Opção (Zero p/sair):\s""");
            opcao = input.nextInt();
            input.nextLine();
            switch (opcao) {
                case 1 -> AlunoController.main(null);
//                case 2 -> MatriculaController.main(null);
//                case 3 -> TurmaController.main(null);
//                case 4 -> CursoController.main(null);
                default -> {
                    if (opcao != 0) System.out.println("Opção inválida.");
                }
            }
        } while(opcao != 0) ;
        System.out.println("\n\n!!!!!!!! Fim da aplicação !!!!!!!!");
        input.close(); //libera o recurso
    }

}//fim classe