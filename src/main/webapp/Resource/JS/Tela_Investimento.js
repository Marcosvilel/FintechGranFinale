document.addEventListener('DOMContentLoaded', function() {
    // Dados de exemplo (em uma aplicação real, isso viria de uma API)
    const investmentsData = [
        {
            id: 1,
            type: 'tesouro',
            name: 'Tesouro Direto IPCA+ 2026',
            amount: 12500.00,
            performance: 2.3,
            details: 'IPCA+ 2026'
        },
        {
            id: 2,
            type: 'acoes',
            name: 'Ações Diversificadas',
            amount: 8750.50,
            performance: -1.1,
            details: 'PETR4, VALE3, ITUB4'
        },
        {
            id: 3,
            type: 'fii',
            name: 'Fundos Imobiliários',
            amount: 4180.25,
            performance: 0.8,
            details: 'XPML11, HGLG11'
        }
    ];

    // Elementos do DOM
    const investmentsContainer = document.getElementById('investments-container');
    const addInvestmentBtn = document.getElementById('add-investment');
    const viewChartsBtn = document.getElementById('view-charts');
    const investmentModal = new bootstrap.Modal(document.getElementById('investmentModal'));
    const saveInvestmentBtn = document.getElementById('saveInvestment');
    const investmentForm = document.getElementById('investmentForm');
    const totalInvestidoElement = document.getElementById('total-investido');
    const rendimentoMensalElement = document.getElementById('rendimento-mensal');

    // Carrega os investimentos na página
    function loadInvestments() {
        investmentsContainer.innerHTML = '';

        let totalInvestido = 0;
        let totalRendimento = 0;

        investmentsData.forEach(investment => {
            totalInvestido += investment.amount;
            totalRendimento += (investment.amount * investment.performance / 100);

            const investmentCard = document.createElement('div');
            investmentCard.className = 'investment-card';
            investmentCard.innerHTML = `
                <div class="investment-info">
                    <h3>${investment.name}</h3>
                    <p>${investment.details}</p>
                </div>
                <div class="investment-value">
                    <p>R$ ${investment.amount.toFixed(2).replace('.', ',')}</p>
                    <span class="${investment.performance >= 0 ? 'positive' : 'negative'}">
                        ${investment.performance >= 0 ? '+' : ''}${investment.performance.toFixed(1)}%
                    </span>
                    <button class="delete-investment" data-id="${investment.id}">
                        <i class="fas fa-trash"></i>
                    </button>
                </div>
            `;
            investmentsContainer.appendChild(investmentCard);
        });

        // Atualiza totais
        totalInvestidoElement.textContent = totalInvestido.toFixed(2).replace('.', ',');
        rendimentoMensalElement.textContent = totalRendimento.toFixed(2).replace('.', ',');

        // Adiciona eventos aos botões de deletar
        document.querySelectorAll('.delete-investment').forEach(btn => {
            btn.addEventListener('click', function() {
                const id = parseInt(this.getAttribute('data-id'));
                deleteInvestment(id);
            });
        });
    }

    // Adiciona um novo investimento
    function addInvestment(investment) {
        investmentsData.push(investment);
        loadInvestments();
    }

    // Remove um investimento
    function deleteInvestment(id) {
        if (confirm('Tem certeza que deseja excluir este investimento?')) {
            const index = investmentsData.findIndex(item => item.id === id);
            if (index !== -1) {
                investmentsData.splice(index, 1);
                loadInvestments();
            }
        }
    }

    // Evento para abrir o modal de adicionar investimento
    addInvestmentBtn.addEventListener('click', function() {
        investmentModal.show();
    });

    // Evento para salvar o novo investimento
    saveInvestmentBtn.addEventListener('click', function() {
        const type = document.getElementById('investmentType').value;
        const name = document.getElementById('investmentName').value;
        const amount = parseFloat(document.getElementById('investmentAmount').value);
        const date = document.getElementById('investmentDate').value;

        if (!type || !name || isNaN(amount) || !date) {
            alert('Preencha todos os campos corretamente!');
            return;
        }

        // Gera um ID único (em produção, isso viria do backend)
        const newId = investmentsData.length > 0 ?
            Math.max(...investmentsData.map(item => item.id)) + 1 : 1;

        // Cria o novo investimento
        const newInvestment = {
            id: newId,
            type: type,
            name: name,
            amount: amount,
            performance: Math.random() * 10 - 2, // Simula um rendimento aleatório entre -2% e +8%
            details: name,
            date: date
        };

        addInvestment(newInvestment);
        investmentModal.hide();
        investmentForm.reset();
    });

    // Evento para visualizar gráficos
    viewChartsBtn.addEventListener('click', function() {
        alert('Funcionalidade de gráficos será implementada aqui!');
        // Em uma implementação real, isso abriria um modal com gráficos
    });

    // Inicializa a página
    loadInvestments();
});