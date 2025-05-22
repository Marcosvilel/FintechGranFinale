document.addEventListener('DOMContentLoaded', function() {
    // Get user data from session (simulated here - in a real app, this would come from your backend)
    const userData = {
        name: "Carlos Silva",
        email: "carlos.silva@example.com"
    };

    // Set user data in the DOM
    document.getElementById('user-name').textContent = userData.name;
    document.getElementById('user-email').textContent = userData.email;

    // Add click effect to the logout button
    const logoutBtn = document.querySelector('.logout-btn');
    if (logoutBtn) {
        logoutBtn.addEventListener('click', function(e) {
            e.preventDefault();

            // Show confirmation dialog
            Swal.fire({
                title: 'Tem certeza?',
                text: "Você será desconectado do sistema.",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Sim, sair!',
                cancelButtonText: 'Cancelar'
            }).then((result) => {
                if (result.isConfirmed) {
                    // In a real app, this would submit the form or make an API call
                    // For now, we'll just simulate a logout
                    window.location.href = 'login.html';
                }
            });
        });
    }

    // Add animation to profile icon on hover
    const profileIcon = document.querySelector('.profile-icon i');
    if (profileIcon) {
        profileIcon.addEventListener('mouseenter', function() {
            this.style.transform = 'scale(1.1)';
            this.style.transition = 'transform 0.3s ease';
        });

        profileIcon.addEventListener('mouseleave', function() {
            this.style.transform = 'scale(1)';
        });
    }

    // Change active sidebar item on click (in case user navigates back)
    const navLinks = document.querySelectorAll('.nav-link');
    navLinks.forEach(link => {
        link.addEventListener('click', function() {
            navLinks.forEach(l => l.classList.remove('active'));
            this.classList.add('active');
        });
    });

    // Simulate loading user data (optional)
    setTimeout(() => {
        document.querySelector('.profile-card').style.opacity = '1';
    }, 300);
});