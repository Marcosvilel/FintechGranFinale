document.addEventListener('DOMContentLoaded', function() {
    // Sample transaction data
    const transactions = [
        {
            id: 1,
            type: 'income',
            amount: 3500.00,
            description: 'Salário',
            category: 'salary',
            date: '2023-10-05'
        },
        {
            id: 2,
            type: 'expense',
            amount: 420.75,
            description: 'Supermercado',
            category: 'food',
            date: '2023-10-03'
        },
        {
            id: 3,
            type: 'expense',
            amount: 1200.00,
            description: 'Aluguel',
            category: 'housing',
            date: '2023-10-01'
        },
        {
            id: 4,
            type: 'income',
            amount: 500.00,
            description: 'Freelance',
            category: 'freelance',
            date: '2023-09-28'
        }
    ];

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

    // DOM elements
    const transactionList = document.getElementById('transactionList');
    const transactionForm = document.getElementById('transactionForm');
    const transactionType = document.getElementById('transactionType');
    const transactionCategory = document.getElementById('transactionCategory');
    const saveTransactionBtn = document.getElementById('saveTransaction');
    const applyFiltersBtn = document.getElementById('applyFilters');
    const filterCategory = document.getElementById('filterCategory');

    // Initialize the page
    function init() {
        loadTransactions();
        updateCategoryOptions();
        setDefaultDate();
        setupEventListeners();
    }

    // Load transactions into the list
    function loadTransactions(filterType = 'all', filterCategory = '', startDate = '', endDate = '') {
        transactionList.innerHTML = '';

        let filteredTransactions = transactions;

        // Apply filters
        if (filterType !== 'all') {
            filteredTransactions = filteredTransactions.filter(t => t.type === filterType);
        }

        if (filterCategory) {
            filteredTransactions = filteredTransactions.filter(t => t.category === filterCategory);
        }

        if (startDate && endDate) {
            filteredTransactions = filteredTransactions.filter(t => {
                return t.date >= startDate && t.date <= endDate;
            });
        }

        // Display transactions
        if (filteredTransactions.length === 0) {
            transactionList.innerHTML = '<div class="text-center p-4 text-muted">Nenhuma transação encontrada</div>';
            return;
        }

        filteredTransactions.forEach(transaction => {
            const transactionItem = createTransactionElement(transaction);
            transactionList.appendChild(transactionItem);
        });

        updateSummaryCards();
    }

    // Create HTML for a transaction
    function createTransactionElement(transaction) {
        const transactionItem = document.createElement('div');
        transactionItem.className = 'transaction-item';

        const isIncome = transaction.type === 'income';
        const amountClass = isIncome ? 'income' : 'expense';
        const amountSign = isIncome ? '+' : '-';
        const iconClass = isIncome ? 'bi-cash-coin text-success' : 'bi-cart text-danger';
        const bgClass = isIncome ? 'bg-success bg-opacity-10' : 'bg-danger bg-opacity-10';

        const categoryLabel = getCategoryLabel(transaction.category, transaction.type);

        transactionItem.innerHTML = `
            <div>
                <div class="d-flex align-items-center">
                    <div class="me-3 ${bgClass} p-2 rounded">
                        <i class="bi ${iconClass}"></i>
                    </div>
                    <div>
                        <h6 class="mb-1">${transaction.description}</h6>
                        <span class="transaction-category">${categoryLabel}</span>
                    </div>
                </div>
            </div>
            <div class="text-end">
                <div class="transaction-amount ${amountClass}">${amountSign} R$ ${transaction.amount.toFixed(2)}</div>
                <div class="transaction-date">${formatDate(transaction.date)}</div>
            </div>
        `;

        return transactionItem;
    }

    // Update summary cards
    function updateSummaryCards() {
        const totalIncome = transactions
            .filter(t => t.type === 'income')
            .reduce((sum, t) => sum + t.amount, 0);

        const totalExpense = transactions
            .filter(t => t.type === 'expense')
            .reduce((sum, t) => sum + t.amount, 0);

        document.querySelector('.income-card .card-title').textContent = `R$ ${totalIncome.toFixed(2)}`;
        document.querySelector('.expense-card .card-title').textContent = `R$ ${totalExpense.toFixed(2)}`;
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

        // Also update filter categories
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

    // Get category label from value
    function getCategoryLabel(value, type) {
        const categoryGroup = categories[type];
        const category = categoryGroup.find(c => c.value === value);
        return category ? category.label : value;
    }

    // Format date for display
    function formatDate(dateString) {
        const options = { day: '2-digit', month: '2-digit', year: 'numeric' };
        return new Date(dateString).toLocaleDateString('pt-BR', options);
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

        // Save transaction
        saveTransactionBtn.addEventListener('click', function() {
            if (!transactionForm.checkValidity()) {
                transactionForm.reportValidity();
                return;
            }

            const newTransaction = {
                id: transactions.length + 1,
                type: transactionType.value,
                amount: parseFloat(transactionAmount.value),
                description: transactionDescription.value,
                category: transactionCategory.value,
                date: transactionDate.value
            };

            transactions.push(newTransaction);
            loadTransactions();

            // Reset form
            transactionForm.reset();
            setDefaultDate();

            // Close modal
            bootstrap.Modal.getInstance(document.getElementById('newTransactionModal')).hide();
        });

        // Apply filters
        applyFiltersBtn.addEventListener('click', function() {
            const filterType = document.querySelector('input[name="filterType"]:checked').value;
            const category = filterCategory.value;
            const startDate = filterDateFrom.value;
            const endDate = filterDateTo.value;

            loadTransactions(filterType, category, startDate, endDate);

            // Close modal
            bootstrap.Modal.getInstance(document.getElementById('filterModal')).hide();
        });
    }

    // Initialize the application
    init();
});