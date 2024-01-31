function initAlert() {
    const alerts = document.querySelectorAll('.alert');
    alerts.forEach((alert) => {
        setTimeout(() => {
            alert.classList.add('d-none');
        }, 6000);
    });
}

window.addEventListener('load', () => {
    initAlert();
});