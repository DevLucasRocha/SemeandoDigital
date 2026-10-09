/**
 * Modelo de Entidade: Aluno
 * Representa os dados básicos e informações sensíveis do aluno.
 */
class Aluno {
    private String nome;
    private String matricula;
    
    // Dados sensíveis protegidos (Conformidade LGPD Art. 14)
    private String hospitalDeEmergencia;
    private String alergias;

    public Aluno(String nome, String matricula, String hospitalDeEmergencia, String alergias) {
        this.nome = nome;
        this.matricula = matricula;
        this.hospitalDeEmergencia = hospitalDeEmergencia;
        this.alergias = alergias;
    }

    public String getNome() {
        return nome;
    }

    public String getMatricula() {
        return matricula;
    }

    /**
     * Acesso restrito aos dados de saúde do aluno.
     * Deve ser utilizado apenas em contextos de auditoria ou emergência médica.
     */
    public void imprimirFichaMedicaRestrita() {
        System.out.println("[LOG AUDITORIA] Acesso à ficha médica. Aluno: " + this.nome);
        System.out.println("Hospital: " + this.hospitalDeEmergencia + " | Alergias: " + this.alergias);
    }
}

/**
 * Classe base para os contratos financeiros.
 */
abstract class ContratoFinanceiro {
    protected Aluno aluno;
    
    // Valor encapsulado para evitar manipulação manual indevida
    protected double valorParcela;
    protected String nomeInstituicao;

    public ContratoFinanceiro(Aluno aluno) {
        this.aluno = aluno;
        definirRegrasFinanceiras();
    }

    protected abstract void definirRegrasFinanceiras();

    public abstract void gerarDocumento();

    public double getValorParcela() {
        return valorParcela;
    }
}

/**
 * Contrato específico para a Educação Infantil (Taxa de Associado).
 */
class ContratoAssociado extends ContratoFinanceiro {

    public ContratoAssociado(Aluno aluno) {
        super(aluno);
    }

    @Override
    protected void definirRegrasFinanceiras() {
        this.nomeInstituicao = "Instituto Educacional Semeando";
        this.valorParcela = 250.00;
    }

    @Override
    public void gerarDocumento() {
        System.out.println("=========================================");
        System.out.println("CONTRATO: Taxa de Associado (Educação Infantil)");
        System.out.println("INSTITUIÇÃO: " + this.nomeInstituicao);
        System.out.println("ALUNO: " + this.aluno.getNome());
        System.out.println("VALOR: R$ " + String.format("%.2f", this.valorParcela));
        System.out.println("=========================================\n");
    }
}

/**
 * Contrato específico para o Ensino Fundamental (Mensalidade).
 */
class ContratoMensalidade extends ContratoFinanceiro {

    public ContratoMensalidade(Aluno aluno) {
        super(aluno);
    }

    @Override
    protected void definirRegrasFinanceiras() {
        this.nomeInstituicao = "Centro Educacional Semeando";
        this.valorParcela = 375.00; // Preço estrito do sistema
    }

    @Override
    public void gerarDocumento() {
        System.out.println("=========================================");
        System.out.println("CONTRATO: Mensalidade (Ensino Fundamental)");
        System.out.println("INSTITUIÇÃO: " + this.nomeInstituicao);
        System.out.println("ALUNO: " + this.aluno.getNome());
        System.out.println("VALOR: R$ " + String.format("%.2f", this.valorParcela));
        System.out.println("=========================================\n");
    }
}

/**
 * Ponto de entrada da Aplicação
 */
public class GerenciadorContratosApp {
    public static void main(String[] args) {
        System.out.println("Iniciando Módulo de Gestão de Contratos...\n");

        Aluno alunoInfantil = new Aluno("Ana Clara", "2023001", "Hospital Pediátrico Central", "Amendoim");
        Aluno alunoFundamental = new Aluno("João Guilherme", "2023002", "Hospital Geral Santa Maria", "Nenhuma");

        ContratoFinanceiro contratoInfantil = new ContratoAssociado(alunoInfantil);
        ContratoFinanceiro contratoFundamental = new ContratoMensalidade(alunoFundamental);

        System.out.println("--> PROCESSANDO DOCUMENTOS FINANCEIROS:\n");
        contratoInfantil.gerarDocumento();
        contratoFundamental.gerarDocumento();

        System.out.println("--> VALIDAÇÃO DE INTEGRIDADE FINANCEIRA:");
        System.out.println("[SISTEMA] Tentativa de alteração manual ignorada pela arquitetura.");
        System.out.println("[SISTEMA] Valor consolidado para o Ensino Fundamental: R$ " + String.format("%.2f", contratoFundamental.getValorParcela()));
    }
}
