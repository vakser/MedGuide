let confirmDialog = document.getElementById('myconfirm');
confirmDialog.addEventListener('show.bs.modal', function (event) {
    let button = event.relatedTarget;
    let link = button.getAttribute('data-bs-link');
    let text = button.getAttribute('data-bs-text');
    let modalTitle = confirmDialog.querySelector('.modal-title');
    modalTitle.textContent = 'Delete user';
    let deleteButton = confirmDialog.querySelector('.delete-button');
    deleteButton.setAttribute('href', link);
    let user = document.getElementById('user-txt');
    user.textContent = text;
});