/**
 * Serviço de processamento de contratos no Front-end.
 * Responsável por aplicar as regras de negócio de precificação e formatação do contrato.
 */
document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('contratoForm');
    const selectTipo = document.getElementById('tipoMatricula');
    const inputValor = document.getElementById('valorParcela');
    const resultadoContainer = document.getElementById('resultadoContainer');

    // Listener para aplicar as regras de precificação automaticamente
    selectTipo.addEventListener('change', (e) => {
        const tipo = e.target.value;
        
        if (tipo === 'infantil') {
            inputValor.value = 'R$ 250,00';
        } else if (tipo === 'fundamental') {
            inputValor.value = 'R$ 375,00'; // Preço bloqueado via regra de negócio
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
            alert("Selecione a categoria da matrícula.");
            return;
        }

        let nomeInstituicao = '';
        let tituloDocumento = '';
        let valorFinal = '';

        // Formatação dinâmica do contrato com base na categoria
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
                    <span class="total-label">Valor do Contrato</span>
                    <span class="total-value">${valor}</span>
                </div>
            </div>
        `;
        
        resultadoContainer.classList.remove('hidden');
        resultadoContainer.scrollIntoView({ behavior: 'smooth', block: 'start' });
    }
});
