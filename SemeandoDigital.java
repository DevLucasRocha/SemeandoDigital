// Classe de Modelo Aluno (Abstração de Entidade)
class Aluno {
    // [ATRIBUTO] Atributos básicos de identificação
    private String nome;
    private String matricula;

    // [ENCAPSULAMENTO] Atributos sensíveis protegidos (Simulando LGPD)
    // Esses dados não possuem métodos "getters/setters" públicos diretos para
    // evitar exposição acidental.
    private String hospitalDeEmergencia;
    private String alergias;

    // [MÉTODO] Construtor da classe
    public Aluno(String nome, String matricula, String hospitalDeEmergencia, String alergias) {
        this.nome = nome;
        this.matricula = matricula;
        this.hospitalDeEmergencia = hospitalDeEmergencia;
        this.alergias = alergias;
    }

    // [MÉTODO] Getters apenas para dados públicos (Nome e Matrícula)
    public String getNome() {
        return nome;
    }

    public String getMatricula() {
        return matricula;
    }

    // [MÉTODO] Acesso controlado aos dados sensíveis (Simulação de auditoria para LGPD)
    // Isso resolve o aviso de "não utilização" da IDE e ilustra como dados privados
    // só devem ser acessados por métodos com propósito específico, mantendo a proteção.
    public void imprimirFichaMedicaRestrita() {
        System.out.println("[LOG LGPD] Acesso restrito registrado para o aluno: " + this.nome);
        System.out.println("Hospital: " + this.hospitalDeEmergencia + " | Alergias: " + this.alergias);
    }
}

// Classe Abstrata ContratoFinanceiro
// [HERANÇA] Classe base que define a estrutura comum para todos os contratos.
abstract class ContratoFinanceiro {
    // [ATRIBUTO]
    protected Aluno aluno;

    // [ENCAPSULAMENTO] O valor da parcela está encapsulado. Não há
    // "setValorParcela()" público.
    // Isso impede a digitação manual errada (ex: R$ 310,00 no lugar de R$ 375,00).
    protected double valorParcela;
    protected String nomeInstituicao;

    // [MÉTODO] Construtor
    public ContratoFinanceiro(Aluno aluno) {
        this.aluno = aluno;
        // Ao instanciar, as regras financeiras são aplicadas automaticamente de forma
        // protegida.
        definirRegrasFinanceiras();
    }

    // [MÉTODO] Método abstrato protegido
    protected abstract void definirRegrasFinanceiras();

    // [MÉTODO] Método abstrato público que será sobrescrito pelas subclasses
    // [POLIMORFISMO] Assinatura padrão, comportamentos distintos na execução.
    public abstract void gerarDocumento();

    // Getter apenas para leitura, garantindo que o valor não seja alterado de fora
    public double getValorParcela() {
        return valorParcela;
    }
}

// Subclasse ContratoAssociado (Educação Infantil)
// [HERANÇA] Herda de ContratoFinanceiro
class ContratoAssociado extends ContratoFinanceiro {

    public ContratoAssociado(Aluno aluno) {
        super(aluno);
    }

    // [MÉTODO] Implementação obrigatória (Abstração)
    @Override
    protected void definirRegrasFinanceiras() {
        this.nomeInstituicao = "Instituto Educacional Semeando";
        this.valorParcela = 250.00; // Valor fixo e seguro para Educação Infantil
    }

    // [POLIMORFISMO] Sobrescrita do método com a regra de "Taxa de Associado"
    @Override
    public void gerarDocumento() {
        System.out.println("=========================================");
        System.out.println("DOCUMENTO: Taxa de Associado (Educação Infantil)");
        System.out.println("INSTITUIÇÃO: " + this.nomeInstituicao);
        System.out.println("ALUNO: " + this.aluno.getNome());
        System.out.println("VALOR A PAGAR: R$ " + String.format("%.2f", this.valorParcela));
        System.out.println("=========================================\n");
    }
}

// Subclasse ContratoMensalidade (Ensino Fundamental)
// [HERANÇA] Herda de ContratoFinanceiro
class ContratoMensalidade extends ContratoFinanceiro {

    public ContratoMensalidade(Aluno aluno) {
        super(aluno);
    }

    // [MÉTODO] Implementação obrigatória
    @Override
    protected void definirRegrasFinanceiras() {
        this.nomeInstituicao = "Centro Educacional Semeando";
        // [ENCAPSULAMENTO] Trava do sistema: Força o valor correto de R$ 375,00.
        // Mitiga o risco financeiro mapeado pela escola.
        this.valorParcela = 375.00;
    }

    // [POLIMORFISMO] Sobrescrita do método com a regra de "Mensalidade"
    @Override
    public void gerarDocumento() {
        System.out.println("=========================================");
        System.out.println("DOCUMENTO: Mensalidade (Ensino Fundamental)");
        System.out.println("INSTITUIÇÃO: " + this.nomeInstituicao);
        System.out.println("ALUNO: " + this.aluno.getNome());
        System.out.println("VALOR A PAGAR: R$ " + String.format("%.2f", this.valorParcela));
        System.out.println("=========================================\n");
    }
}

// Classe Principal para Teste de Mesa
public class SemeandoDigital {
    // [MÉTODO] Ponto de entrada do sistema
    public static void main(String[] args) {
        System.out.println("Iniciando Sistema MVP: Semeando Digital...\n");

        // 1. Instanciando dados sintéticos (Entidades)
        Aluno alunoInfantil = new Aluno("Ana Clara", "2023001", "Hospital Pediátrico Central", "Amendoim");
        Aluno alunoFundamental = new Aluno("João Guilherme", "2023002", "Hospital Geral Santa Maria", "Nenhuma");

        // 2. Criação dos contratos usando os pilares da POO
        // [POLIMORFISMO] O tipo base "ContratoFinanceiro" assume a forma da subclasse
        // instanciada
        ContratoFinanceiro contrato1 = new ContratoAssociado(alunoInfantil);
        ContratoFinanceiro contrato2 = new ContratoMensalidade(alunoFundamental);

        // 3. Execução Polimórfica
        System.out.println("--> GERANDO CONTRATOS E DOCUMENTOS FINANCEIROS:\n");

        contrato1.gerarDocumento();
        contrato2.gerarDocumento();

        // 4. Prova de Encapsulamento
        System.out.println("--> TESTE DE SEGURANÇA E REGRA DE NEGÓCIO (ENCAPSULAMENTO):");
        System.out.println("[SISTEMA] Tentativa de erro humano evitada!");
        System.out.println("[SISTEMA] Não é possível digitar 'R$ 310,00' para o Ensino Fundamental.");
        System.out.println(
                "[SISTEMA] Valor forçado pela arquitetura: R$ " + String.format("%.2f", contrato2.getValorParcela()));
    }
}
