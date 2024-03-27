let confirmDialog = document.getElementById('myconfirm');
confirmDialog.addEventListener('show.bs.modal', function (event) {
    let button = event.relatedTarget;
    let link = button.getAttribute('data-bs-link');
    let text = button.getAttribute('data-bs-text');
    let modalTitle = confirmDialog.querySelector('.modal-title');
    modalTitle.textContent = 'Delete disease';
    let deleteButton = confirmDialog.querySelector('.delete-button');
    deleteButton.setAttribute('href', link);
    let disease = document.getElementById('disease-txt');
    disease.textContent = text;
});