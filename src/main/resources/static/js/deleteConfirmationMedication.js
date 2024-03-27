let confirmDialog = document.getElementById('myconfirm');
confirmDialog.addEventListener('show.bs.modal', function (event) {
    let button = event.relatedTarget;
    let link = button.getAttribute('data-bs-link');
    let text = button.getAttribute('data-bs-text');
    let modalTitle = confirmDialog.querySelector('.modal-title');
    modalTitle.textContent = 'Delete medication';
    let deleteButton = confirmDialog.querySelector('.delete-button');
    deleteButton.setAttribute('href', link);
    let user = document.getElementById('medication-txt');
    user.textContent = text;
});