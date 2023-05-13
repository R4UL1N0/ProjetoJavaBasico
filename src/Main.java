import controllers.StudentsController;

public class Main {

    public static void main(String[] args) {

        System.out.println("APROVADOS:");
        StudentsController.showApprovedList();

        System.out.println("REPROVADOS:");
        StudentsController.showFlunkedList();

        System.out.println("ALUNOS NOTA 10:");
        StudentsController.showNota10();


        StudentsController.showLowestOnes();

        System.out.println("DO MAIOR PARA O MENOR:");
        StudentsController.showDescending();


        System.out.println("BEST THREE:");
        StudentsController.showBestThree();

        System.out.println("WORST THREE:");
        StudentsController.showWorstThree();


        // Agora vamos as atividades
        /*
        

        
        5. Faça uma lista com top 3 notas de alunos. Em caso de notas iguais coloque todos na mesma posição.
            - Ex:
                1º - Fulano : Nota = 10.0;
                   - Beltrano : Nota = 10.0;
                2º - Joãozinho : Nota = 9.0;
                3º - Mariazinha : Nota = 8.9;
            - Exiba os dados nesse formato: <posicao> - <nome> : Nota = <nota>
        6. Faça uma lista com as 3 menores notas de alunos. Em caso de notas iguais coloque todos na mesma posição. Exemplo igual a anterior
            - Exiba os dados nesse formato: <posicao> - <nome> : Nota = <nota>

         */
    }
}
