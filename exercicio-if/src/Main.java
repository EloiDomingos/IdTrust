import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite a sua idade: ");
        int idade = scanner.nextInt();
        System.out.println("Digite o seu sexo: ");
        String sexo = scanner.next();

        if (idade >= 65 && sexo.equals("masculino")){
            System.out.println("APOSENTADO");
        } else if (idade >= 60 && sexo.equals("feminino")) {
            System.out.println("APOSENTADA");
        } else if (idade > 13 &&  idade < 18) {
            System.out.println("ADOLESCENTE");
        } else if (idade <= 13) {
            System.out.println("CRIANÇA");
        } else {
            System.out.println("ADULTO");
        }

        scanner.close();

    }
}