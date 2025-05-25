document.addEventListener('DOMContentLoaded', function() {
    // DOM elements
    const transactionType = document.getElementById('transactionType');
    const transactionCategory = document.getElementById('transactionCategory');
    const applyFiltersBtn = document.getElementById('applyFilters');
    const filterCategory = document.getElementById('filterCategory');

    // Category options
    const categories = {
        income: [
            { value: 'salary', label: 'Salário' },
            { value: 'investment', label: 'Investimentos' },
            { value: 'freelance', label: 'Freelance' }
        ],
        expense: [
            { value: 'food', label: 'Alimentação' },
            { value: 'transport', label: 'Transporte' },
            { value: 'housing', label: 'Moradia' },
            { value: 'entertainment', label: 'Lazer' }
        ]
    };

    // Initialize the page
    function init() {
        updateCategoryOptions();
        setDefaultDate();
        setupEventListeners();
    }

    // Update category options based on transaction type
    function updateCategoryOptions() {
        const type = transactionType.value;
        transactionCategory.innerHTML = '<option value="">Selecione uma categoria</option>';

        const categoryGroup = categories[type];
        categoryGroup.forEach(category => {
            const option = document.createElement('option');
            option.value = category.value;
            option.textContent = category.label;
            transactionCategory.appendChild(option);
        });

        updateFilterCategories();
    }

    // Update filter category options
    function updateFilterCategories() {
        filterCategory.innerHTML = '<option value="">Todas categorias</option>';

        // Add income categories
        const incomeGroup = document.createElement('optgroup');
        incomeGroup.label = 'Entradas';
        categories.income.forEach(category => {
            const option = document.createElement('option');
            option.value = category.value;
            option.textContent = category.label;
            incomeGroup.appendChild(option);
        });
        filterCategory.appendChild(incomeGroup);

        // Add expense categories
        const expenseGroup = document.createElement('optgroup');
        expenseGroup.label = 'Saídas';
        categories.expense.forEach(category => {
            const option = document.createElement('option');
            option.value = category.value;
            option.textContent = category.label;
            expenseGroup.appendChild(option);
        });
        filterCategory.appendChild(expenseGroup);
    }

    // Set default date to today
    function setDefaultDate() {
        const today = new Date().toISOString().split('T')[0];
        document.getElementById('transactionDate').value = today;
    }

    // Setup event listeners
    function setupEventListeners() {
        // Transaction type change
        transactionType.addEventListener('change', updateCategoryOptions);

        // Apply filters
        applyFiltersBtn.addEventListener('click', applyFilters);
    }

    // Apply filters
    function applyFilters() {
        const filterType = document.querySelector('input[name="filterType"]:checked').value;
        const category = filterCategory.value;
        const startDate = document.getElementById('filterDateFrom').value;
        const endDate = document.getElementById('filterDateTo').value;

        const params = new URLSearchParams();
        params.append('acao', 'pagina-transacao');
        if (filterType !== 'all') params.append('filterType', filterType);
        if (category) params.append('category', category);
        if (startDate) params.append('startDate', startDate);
        if (endDate) params.append('endDate', endDate);

        window.location.href = `transacao?${params.toString()}`;
    }

    // Função para edição
    window.editarTransacao = function(id) {
        window.location.href = `transacao?acao=buscar-transacao&id=${id}`;
    }

    // // Função para exclusão
    // window.confirmarExclusao = function(id) {
    //     if (confirm('Tem certeza que deseja excluir esta transação?')) {
    //         fetch(`transacao?acao=excluir&id=${id}`, {
    //             method: 'POST'
    //         })
    //             .then(response => {
    //                 if (response.ok) {
    //                     window.location.reload();
    //                 } else {
    //                     alert('Erro ao excluir transação');
    //                 }
    //             })
    //             .catch(error => {
    //                 console.error('Error:', error);
    //                 alert('Erro ao excluir transação');
    //             });
    //     }
    // }

    // Initialize the application
    init();
});