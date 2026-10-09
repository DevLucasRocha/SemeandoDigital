document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('contratoForm');
    const selectTipo = document.getElementById('tipoMatricula');
    const inputValor = document.getElementById('valorParcela');
    const resultadoContainer = document.getElementById('resultadoContainer');

    // [REFLEXO DO ENCAPSULAMENTO]
    // A inteligência de precificação fica no sistema, não no usuário.
    // O evento 'change' escuta a seleção e aplica a regra de negócio rigorosamente.
    // O campo HTML possui o atributo 'readonly' garantindo que o usuário não possa digitar valores.
    selectTipo.addEventListener('change', (e) => {
        const tipo = e.target.value;
        
        if (tipo === 'infantil') {
            inputValor.value = 'R$ 250,00';
        } else if (tipo === 'fundamental') {
            // Trava de segurança visual: Preenche automaticamente R$ 375,00
            // Impedindo de forma sistêmica a falha manual (R$ 310,00) mapeada pela escola.
            inputValor.value = 'R$ 375,00';
        } else {
            inputValor.value = 'R$ 0,00';
        }
    });

    form.addEventListener('submit', (e) => {
        e.preventDefault();

        const nome = document.getElementById('nome').value;
        const matricula = document.getElementById('matricula').value;
        const tipo = selectTipo.value;
        
        if (!tipo) {
            alert("Por favor, selecione o tipo de matrícula.");
            return;
        }

        // [REFLEXO DO POLIMORFISMO]
        // Baseado na seleção, a mesma ação "Gerar Documento" produz 
        // resultados com "formas" e identidades institucionais diferentes.
        let nomeInstituicao = '';
        let tituloDocumento = '';
        let valorFinal = '';

        if (tipo === 'infantil') {
            nomeInstituicao = 'Instituto Educacional Semeando';
            tituloDocumento = 'Taxa de Associado';
            valorFinal = 'R$ 250,00';
        } else if (tipo === 'fundamental') {
            nomeInstituicao = 'Centro Educacional Semeando';
            tituloDocumento = 'Mensalidade Escolar';
            valorFinal = 'R$ 375,00';
        }

        renderizarDocumento(nomeInstituicao, tituloDocumento, nome, matricula, valorFinal);
    });

    function renderizarDocumento(instituicao, titulo, aluno, matricula, valor) {
        resultadoContainer.innerHTML = `
            <div class="document-card">
                <div class="document-header">
                    <h3>${instituicao}</h3>
                    <p>${titulo}</p>
                </div>
                <div class="document-body">
                    <div class="data-row">
                        <span class="data-label">Aluno(a)</span>
                        <span class="data-value">${aluno}</span>
                    </div>
                    <div class="data-row">
                        <span class="data-label">Matrícula</span>
                        <span class="data-value">${matricula}</span>
                    </div>
                </div>
                <div class="document-footer">
                    <span class="total-label">Valor a Pagar</span>
                    <span class="total-value">${valor}</span>
                </div>
            </div>
        `;
        
        resultadoContainer.classList.remove('hidden');
        
        // Efeito de rolagem suave até o card gerado
        resultadoContainer.scrollIntoView({ behavior: 'smooth', block: 'start' });
    }
});
