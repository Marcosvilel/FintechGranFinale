document.addEventListener('DOMContentLoaded', function() {
    // Elementos do DOM
    const reportTypeSelect = document.getElementById('reportType');
    const periodSelect = document.getElementById('period');
    const customFiltersDiv = document.getElementById('customFilters');
    const additionalFiltersDiv = document.getElementById('additionalFilters');
    const dataPreviewDiv = document.getElementById('dataPreview');
    const previewTable = document.getElementById('previewTable');
    const rowCountSpan = document.getElementById('rowCount');

    // Dados de exemplo (em produção viria de uma API)
    const sampleData = {
        transacoes: [
            { data: '15/05/2023', descricao: 'Supermercado', categoria: 'Alimentação', valor: -250.00, formaPagamento: 'Cartão de Crédito' },
            { data: '14/05/2023', descricao: 'Restaurante', categoria: 'Alimentação', valor: -120.00, formaPagamento: 'Cartão de Débito' },
            { data: '13/05/2023', descricao: 'Combustível', categoria: 'Transporte', valor: -180.00, formaPagamento: 'Dinheiro' }
        ],
        investimentos: [
            { tipo: 'Tesouro Direto', nome: 'IPCA+ 2026', valor: 12500.00, dataAplicacao: '10/01/2023', rentabilidade: '2,3%' },
            { tipo: 'Ações', nome: 'PETR4', valor: 8750.50, dataAplicacao: '15/03/2023', rentabilidade: '-1,1%' }
        ],
        metas: [
            { nome: 'Viagem Europa', valorAlvo: 25000.00, valorAtual: 18500.00, previsao: '12/2023', status: 'Em andamento' },
            { nome: 'Reserva Emergencial', valorAlvo: 15000.00, valorAtual: 12000.00, previsao: '08/2023', status: 'Em andamento' }
        ],
        cartoes: [
            { nome: 'Cartão Principal', limite: 5000.00, utilizado: 2450.00, vencimento: '10/06/2023', bandeira: 'Visa' },
            { nome: 'Cartão Secundário', limite: 3000.00, utilizado: 1250.00, vencimento: '15/06/2023', bandeira: 'Mastercard' }
        ]
    };

    // Configura os listeners
    setupEventListeners();

    function setupEventListeners() {
        // Listener para tipo de relatório
        reportTypeSelect.addEventListener('change', function() {
            updateAdditionalFilters(this.value);
            generatePreview(this.value);
        });

        // Listener para período
        periodSelect.addEventListener('change', function() {
            if (this.value === 'custom') {
                customFiltersDiv.style.display = 'flex';
            } else {
                customFiltersDiv.style.display = 'none';
                generatePreview(reportTypeSelect.value);
            }
        });

        // Listeners para datas personalizadas
        document.getElementById('startDate').addEventListener('change', function() {
            generatePreview(reportTypeSelect.value);
        });

        document.getElementById('endDate').addEventListener('change', function() {
            generatePreview(reportTypeSelect.value);
        });

        // Listeners para botões de exportação
        document.getElementById('exportExcel').addEventListener('click', exportToExcel);
        document.getElementById('exportPDF').addEventListener('click', exportToPDF);
        document.getElementById('exportPrint').addEventListener('click', printReport);
    }

    function updateAdditionalFilters(reportType) {
        additionalFiltersDiv.innerHTML = '';

        if (!reportType) {
            dataPreviewDiv.style.display = 'none';
            return;
        }

        // Adiciona filtros específicos para cada tipo de relatório
        switch(reportType) {
            case 'transacoes':
                addCategoryFilter();
                addPaymentMethodFilter();
                break;
            case 'investimentos':
                addInvestmentTypeFilter();
                break;
            case 'metas':
                addGoalStatusFilter();
                break;
            case 'cartoes':
                addCardTypeFilter();
                break;
        }

        dataPreviewDiv.style.display = 'block';
    }

    function addCategoryFilter() {
        const html = `
            <div class="filter-group">
                <label>Categorias</label>
                <div>
                    <label class="filter-option"><input type="checkbox" name="category" value="alimentacao" checked> Alimentação</label>
                    <label class="filter-option"><input type="checkbox" name="category" value="transporte" checked> Transporte</label>
                    <label class="filter-option"><input type="checkbox" name="category" value="lazer" checked> Lazer</label>
                    <label class="filter-option"><input type="checkbox" name="category" value="moradia" checked> Moradia</label>
                </div>
            </div>
        `;
        additionalFiltersDiv.innerHTML += html;

        // Adiciona listeners para os checkboxes
        document.querySelectorAll('input[name="category"]').forEach(checkbox => {
            checkbox.addEventListener('change', () => generatePreview(reportTypeSelect.value));
        });
    }

    function addPaymentMethodFilter() {
        const html = `
            <div class="filter-group">
                <label>Forma de Pagamento</label>
                <div>
                    <label class="filter-option"><input type="checkbox" name="payment" value="credito" checked> Crédito</label>
                    <label class="filter-option"><input type="checkbox" name="payment" value="debito" checked> Débito</label>
                    <label class="filter-option"><input type="checkbox" name="payment" value="dinheiro" checked> Dinheiro</label>
                    <label class="filter-option"><input type="checkbox" name="payment" value="pix" checked> PIX</label>
                </div>
            </div>
        `;
        additionalFiltersDiv.innerHTML += html;

        document.querySelectorAll('input[name="payment"]').forEach(checkbox => {
            checkbox.addEventListener('change', () => generatePreview(reportTypeSelect.value));
        });
    }

    function addInvestmentTypeFilter() {
        const html = `
            <div class="filter-group">
                <label>Tipo de Investimento</label>
                <div>
                    <label class="filter-option"><input type="checkbox" name="investmentType" value="tesouro" checked> Tesouro</label>
                    <label class="filter-option"><input type="checkbox" name="investmentType" value="acoes" checked> Ações</label>
                    <label class="filter-option"><input type="checkbox" name="investmentType" value="fii" checked> Fundos Imobiliários</label>
                    <label class="filter-option"><input type="checkbox" name="investmentType" value="cdb" checked> CDB</label>
                </div>
            </div>
        `;
        additionalFiltersDiv.innerHTML += html;

        document.querySelectorAll('input[name="investmentType"]').forEach(checkbox => {
            checkbox.addEventListener('change', () => generatePreview(reportTypeSelect.value));
        });
    }

    function generatePreview(reportType) {
        if (!reportType) return;

        const data = sampleData[reportType];
        if (!data) return;

        // Filtra os dados conforme os filtros selecionados
        let filteredData = [...data];

        // Aplica filtros adicionais
        switch(reportType) {
            case 'transacoes':
                filteredData = filterTransactions(data);
                break;
            case 'investimentos':
                filteredData = filterInvestments(data);
                break;
            // Outros casos podem ser adicionados aqui
        }

        // Atualiza a contagem de registros
        rowCountSpan.textContent = `${filteredData.length} registros encontrados`;

        // Gera os cabeçalhos da tabela
        const headers = Object.keys(filteredData[0] || {});
        previewTable.innerHTML = '';

        // Cria o cabeçalho
        const thead = previewTable.createTHead();
        const headerRow = thead.insertRow();

        headers.forEach(header => {
            const th = document.createElement('th');
            th.textContent = header.charAt(0).toUpperCase() + header.slice(1).replace(/([A-Z])/g, ' $1');
            headerRow.appendChild(th);
        });

        // Cria o corpo da tabela
        const tbody = previewTable.createTBody();

        filteredData.forEach(item => {
            const row = tbody.insertRow();
            headers.forEach(header => {
                const cell = row.insertCell();
                let value = item[header];

                // Formata valores monetários
                if (header.toLowerCase().includes('valor') && typeof value === 'number') {
                    value = value.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
                }

                cell.textContent = value;
            });
        });
    }

    function filterTransactions(data) {
        // Filtra por categorias selecionadas
        const selectedCategories = Array.from(document.querySelectorAll('input[name="category"]:checked')).map(cb => cb.value);
        let filtered = data;

        if (selectedCategories.length > 0) {
            filtered = filtered.filter(item => selectedCategories.includes(item.categoria.toLowerCase()));
        }

        // Filtra por formas de pagamento selecionadas
        const selectedPayments = Array.from(document.querySelectorAll('input[name="payment"]:checked')).map(cb => cb.value);

        if (selectedPayments.length > 0) {
            filtered = filtered.filter(item => {
                const payment = item.formaPagamento.toLowerCase();
                return selectedPayments.some(sp => payment.includes(sp));
            });
        }

        return filtered;
    }

    function filterInvestments(data) {
        const selectedTypes = Array.from(document.querySelectorAll('input[name="investmentType"]:checked')).map(cb => cb.value);

        if (selectedTypes.length === 0) return data;

        return data.filter(item => selectedTypes.includes(item.tipo.toLowerCase()));
    }

    function exportToExcel() {
        const reportType = reportTypeSelect.value;
        if (!reportType) {
            alert('Selecione um tipo de relatório primeiro');
            return;
        }

        const data = sampleData[reportType];
        if (!data || data.length === 0) {
            alert('Nenhum dado disponível para exportação');
            return;
        }

        // Cria uma planilha Excel
        const ws = XLSX.utils.json_to_sheet(data);
        const wb = XLSX.utils.book_new();
        XLSX.utils.book_append_sheet(wb, ws, "Relatorio");

        // Gera o arquivo e faz o download
        const fileName = `Relatorio_${reportType}_${new Date().toISOString().slice(0,10)}.xlsx`;
        XLSX.writeFile(wb, fileName);
    }

    function exportToPDF() {
        alert('Exportação para PDF será implementada aqui');
        // Implementação real usaria jsPDF
    }

    function printReport() {
        window.print();
    }

    // Inicializa a tela
    updateAdditionalFilters('');
});