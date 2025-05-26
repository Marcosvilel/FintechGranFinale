document.addEventListener("DOMContentLoaded", () => {
    const addGoalBtn = document.getElementById("addGoalBtn");
    const saveGoalBtn = document.getElementById("saveGoalBtn");
    const goalForm = document.getElementById("goalForm");
    const goalsContainer = document.getElementById("goals-container");

    let goals = JSON.parse(localStorage.getItem("goals")) || [];

    function renderGoals() {
        goalsContainer.innerHTML = "";
        let totalTarget = 0, totalSaved = 0;

        goals.forEach((goal, index) => {
            totalTarget += goal.target;
            totalSaved += goal.current;

            const card = document.createElement("div");
            card.className = "goal-card";

            const percent = Math.min((goal.current / goal.target) * 100, 100).toFixed(1);

            card.innerHTML = `
                <div class="goal-header">
                    <h5 class="goal-title">${goal.name}</h5>
                    <span class="goal-priority priority-${goal.priority}">${goal.priority}</span>
                </div>
                <div class="goal-progress">
                    <div class="progress">
                        <div class="progress-bar bg-success" style="width: ${percent}%;"></div>
                    </div>
                </div>
                <div class="goal-details">
                    <span class="goal-amount">R$ ${goal.current.toFixed(2)} / R$ ${goal.target.toFixed(2)}</span>
                    <span class="goal-deadline">${goal.deadline}</span>
                </div>
                <div class="goal-actions mt-2">
                    <button class="action-btn edit-btn" data-index="${index}"><i class="bi bi-pencil"></i></button>
                    <button class="action-btn delete-btn" data-index="${index}"><i class="bi bi-trash"></i></button>
                </div>
            `;
            goalsContainer.appendChild(card);
        });

        document.getElementById("total-goals").textContent = totalTarget.toFixed(2);
        document.getElementById("total-saved").textContent = totalSaved.toFixed(2);
        document.getElementById("remaining").textContent = (totalTarget - totalSaved).toFixed(2);
    }

    addGoalBtn.addEventListener("click", () => {
        goalForm.reset();
        document.getElementById("goalId").value = "";
        document.getElementById("modalTitle").textContent = "Adicionar Nova Meta";
        new bootstrap.Modal(document.getElementById("goalModal")).show();
    });

    saveGoalBtn.addEventListener("click", () => {
        const id = document.getElementById("goalId").value;
        const name = document.getElementById("goalName").value.trim();
        const target = parseFloat(document.getElementById("goalTarget").value);
        const current = parseFloat(document.getElementById("goalCurrent").value) || 0; // Default 0 se vazio
        const deadline = document.getElementById("goalDeadline").value;
        const priority = document.getElementById("goalPriority").value;

        // Validações melhoradas
        if (!name) {
            alert("Por favor, informe o nome da meta.");
            return;
        }

        if (isNaN(target) || target <= 0) {
            alert("O valor alvo deve ser um número positivo.");
            return;
        }

        if (isNaN(current) || current < 0) {
            alert("O valor atual deve ser um número positivo ou zero.");
            return;
        }

        if (!deadline) {
            alert("Por favor, selecione uma data limite.");
            return;
        }

        const goal = {
            name,
            target,
            current,
            deadline,
            priority
        };

        if (id === "") {
            // Adiciona nova meta
            goals.push(goal);
        } else {
            // Atualiza meta existente
            goals[parseInt(id)] = goal;
        }

        // Salva no localStorage
        localStorage.setItem("goals", JSON.stringify(goals));

        // Fecha o modal
        const modal = bootstrap.Modal.getInstance(document.getElementById("goalModal"));
        modal.hide();

        // Atualiza a exibição
        renderGoals();
    });


    goalsContainer.addEventListener("click", (e) => {
        if (e.target.closest(".edit-btn")) {
            const index = e.target.closest(".edit-btn").dataset.index;
            const g = goals[index];
            document.getElementById("goalId").value = index;
            document.getElementById("goalName").value = g.name;
            document.getElementById("goalTarget").value = g.target;
            document.getElementById("goalCurrent").value = g.current;
            document.getElementById("goalDeadline").value = g.deadline;
            document.getElementById("goalPriority").value = g.priority;
            document.getElementById("modalTitle").textContent = "Editar Meta";
            new bootstrap.Modal(document.getElementById("goalModal")).show();
        }

        if (e.target.closest(".delete-btn")) {
            const index = e.target.closest(".delete-btn").dataset.index;
            goals.splice(index, 1);
            localStorage.setItem("goals", JSON.stringify(goals));
            renderGoals();
        }
    });

    renderGoals();
});