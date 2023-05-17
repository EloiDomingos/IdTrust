import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.*;


public class Main {

    private static final Set<Recebivel> RECEBIVEIS;

    public static void main(String[] args) {
        System.out.println("Faça os exercícios abaixo usando os dados pré-criados na variável `RECEBIVEIS`.");
        System.out.println("Peço que prepare a resolução logo abaixo de cada enunciado.");
        System.out.println();

        System.out.println("1 - Print a soma agrupando as mesmas datas de vencimentos: ");
        Map<LocalDate, BigDecimal> somaPorDataVencimento = new HashMap<>();

        for (Recebivel recebivel : RECEBIVEIS) {
            LocalDate dataVencimento = recebivel.getDataVencimento();
            BigDecimal valor = recebivel.getValor();

            BigDecimal soma = somaPorDataVencimento.getOrDefault(dataVencimento, BigDecimal.ZERO);
            somaPorDataVencimento.put(dataVencimento, soma.add(valor));
        }

        for (Map.Entry<LocalDate, BigDecimal> entry : somaPorDataVencimento.entrySet()) {
            LocalDate dataVencimento = entry.getKey();
            BigDecimal soma = entry.getValue();

            System.out.println("Data de vencimento: " + dataVencimento);
            System.out.println("Soma dos recebíveis: " + soma);
            System.out.println();
        }


        System.out.println("2 - Print a soma dos recebiveis ja vencidos");
        BigDecimal somaRecebiveisVencidos = BigDecimal.ZERO;
        LocalDate dataAtual = LocalDate.now();

        for (Recebivel recebivel : RECEBIVEIS) {
            LocalDate dataVencimento = recebivel.getDataVencimento();

            if (dataVencimento.isBefore(dataAtual)) {
                somaRecebiveisVencidos = somaRecebiveisVencidos.add(recebivel.getValor());
            }
        }
        System.out.println("Soma dos recebíveis vencidos: " + somaRecebiveisVencidos);
        System.out.println();

        System.out.println("3 - Formate para moeda Real o valor do recebivel com vencimento 25/07/2023");
        LocalDate vencimentoProcurado = LocalDate.parse("2023-07-25");

        for (Recebivel recebivel : RECEBIVEIS) {
            if (recebivel.getDataVencimento().equals(vencimentoProcurado)) {
                NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
                String valorFormatado = formatoMoeda.format(recebivel.getValor());
                System.out.println("Valor do recebível com vencimento em 25/07/2023: " + valorFormatado);
                break;
            }
        }
        System.out.println();

        System.out
                .println("4 - Print o prazo em dias entre emissao e vencimento do recebivel com vencimento 12/10/2023: ");
        LocalDate dataProcurada = LocalDate.parse("2023-10-12");

        for (Recebivel recebivel : RECEBIVEIS) {
            if (recebivel.getDataVencimento().equals(dataProcurada)) {
                long prazoEmDias = ChronoUnit.DAYS.between((Temporal) recebivel.getDataEmissao(), recebivel.getDataVencimento());
                System.out.println("Prazo em dias entre a emissão e o vencimento: " + prazoEmDias  + " " + "dias");
                break;
            }
        }
        System.out.println();

        System.out.println("5 - Print a concatenação de todos os campos do recebivel separando por ;");
        for (Recebivel recebivel : RECEBIVEIS) {
            String concatenacao = recebivel.getCodigo() + ";" +
                    recebivel.getDataEmissao() + ";" +
                    recebivel.getDataVencimento() + ";" +
                    recebivel.getValor();

            System.out.println(concatenacao);
        }
        System.out.println();

        System.out.println("6 - Formate a data 2023-06-25 do recebivel para o formato dd/MM/yyyy");
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate dataAlvo = LocalDate.parse("2023-07-25");

        for (Recebivel rcb: RECEBIVEIS) {

            if(rcb.getDataVencimento().equals(dataAlvo)){
                LocalDate dataVencimento = rcb.getDataVencimento();
                String dataFormatada = dataVencimento.format(fmt);
                System.out.println("Data formatada: " + dataFormatada);
            }
        }

        System.out.println();
        System.out.println("Exercício extra:");
        System.out.println(
                "7 - Dado uma lista da variável `valores` abaixo, acrescente um novo valor de acordo com as regras a seguir:");
        System.out.println("    -  R$5,90 para valores menor e igual que R$100,00");
        System.out.println("    -  R$15,00 para valores menor que R$20,00");
        System.out.println("    -  R$4,33 para valores maior que R$100,00");
        System.out.println("    -  R$2,10 para valores maior que R$200,00");
        System.out.println("    -  R$3,55 para valores igual que R$150,00");
        System.out.println();
        System.out.println(
                "Print o novo resultado na saída da condição de validação e no final print a soma de todos os novos valores da lista");
        List<BigDecimal> valores = Arrays.asList(new BigDecimal("88.88"), new BigDecimal("17.01"),
                new BigDecimal("20.00"), new BigDecimal("150.00"), new BigDecimal("124.21"), new BigDecimal("247.09"),
                new BigDecimal("100.00"), new BigDecimal("4.99"));

        for (int i = 0; i < valores.size(); i++) {
            BigDecimal valor = valores.get(i);

            if(valor.compareTo(new BigDecimal("100.00")) <= 0){
                valor = valor.add(new BigDecimal(5.90));
            }

            if(valor.compareTo(new BigDecimal("20.00")) < 0){
                valor = valor.add(new BigDecimal(15.00));
            }

            if(valor.compareTo(new BigDecimal("100.00")) > 0){
                valor = valor.add(new BigDecimal(4.33));
            }

            if(valor.compareTo(new BigDecimal("200.00")) > 0){
                valor = valor.add(new BigDecimal(2.10));
            }

            if(valor.compareTo(new BigDecimal("150.00")) < 0){
                valor = valor.add(new BigDecimal(3.55));
            }

            valores.set(i, valor.setScale(2, BigDecimal.ROUND_HALF_EVEN));

        }

        BigDecimal somaTotal = BigDecimal.ZERO;
        for(int i = 0; i < valores.size(); i++){
            BigDecimal val = valores.get(i);
            somaTotal = somaTotal.add(val);
        }

        System.out.println(valores);
        System.out.println("Soma de todos os novos valores: " + somaTotal);
        System.out.println();

        System.out.println("Boa Sorte!");

    }

    static {
        Set<Recebivel> rs = new HashSet<>();
        rs.add(Recebivel.create("1H01R6HA1", LocalDate.parse("2023-05-02"), LocalDate.parse("2023-08-09"),
                new BigDecimal("146.99")));
        rs.add(Recebivel.create("1H01R6HB1", LocalDate.parse("2023-05-02"), LocalDate.parse("2023-05-08"),
                new BigDecimal("592.18")));
        rs.add(Recebivel.create("1H01R6HC1", LocalDate.parse("2023-05-03"), LocalDate.parse("2023-06-28"),
                new BigDecimal("98.20")));
        rs.add(Recebivel.create("1H01R6HD1", LocalDate.parse("2023-05-06"), LocalDate.parse("2023-09-19"),
                new BigDecimal("726.01")));
        rs.add(Recebivel.create("1H01R6HE1", LocalDate.parse("2023-05-02"), LocalDate.parse("2023-05-08"),
                new BigDecimal("81.88")));
        rs.add(Recebivel.create("1H01R6HF1", LocalDate.parse("2023-05-03"), LocalDate.parse("2023-07-15"),
                new BigDecimal("221.34")));
        rs.add(Recebivel.create("1H01R6HG1", LocalDate.parse("2023-05-02"), LocalDate.parse("2023-07-25"),
                new BigDecimal("711.98")));
        rs.add(Recebivel.create("1H01R6HH1", LocalDate.parse("2023-05-05"), LocalDate.parse("2023-10-10"),
                new BigDecimal("100.27")));
        rs.add(Recebivel.create("1H01R6HI1", LocalDate.parse("2023-05-02"), LocalDate.parse("2023-10-12"),
                new BigDecimal("3021.83")));
        rs.add(Recebivel.create("1H01R6HJ1", LocalDate.parse("2023-05-03"), LocalDate.parse("2023-09-19"),
                new BigDecimal("1930.76")));

        RECEBIVEIS = Collections.unmodifiableSet(rs);
    }

    public static class Recebivel {

        public static Recebivel create(String codigo, LocalDate dataEmissao, LocalDate dataVencimento,
                                       BigDecimal valor) {
            Recebivel r = new Recebivel();
            r.codigo = codigo;
            r.dataEmissao = dataEmissao;
            r.dataVencimento = dataVencimento;
            r.valor = valor;
            return r;
        }

        private String codigo;
        private LocalDate dataEmissao;
        private LocalDate dataVencimento;
        private BigDecimal valor;

        public String getCodigo() {
            return codigo;
        }

        public Object getDataEmissao() {
            return dataEmissao;
        }

        public void setDataVencimento() {
            this.dataVencimento = dataVencimento;
        }

        public LocalDate getDataVencimento() {
            return dataVencimento;
        }

        public BigDecimal getValor() {
            return valor;
        }

        @Override
        public String toString() {
            return "Recebivel{" +
                    "codigo='" + codigo + '\'' +
                    ", dataEmissao=" + dataEmissao +
                    ", dataVencimento=" + dataVencimento +
                    ", valor=" + valor +
                    '}';
        }



    }

}

