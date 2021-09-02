const userData = {
    name: "",
    email: "",
    address: "",
    city: "",
    state: "",
    zip: ""
};

let payment = {
    init: function () {
        this.initCheckoutButton();
    },

    initCheckoutButton: function() {
        let checkoutButton = document.querySelector('#checkout');

        checkoutButton.addEventListener('click', this.getFormData);
    },

    getFormData: function() {
        userData.name = document.querySelector('#fname').value;
        userData.email = document.querySelector('#email').value;
        userData.address = document.querySelector('#adr').value;
        userData.city = document.querySelector('#city').value;
        userData.state = document.querySelector('#state').value;
        userData.zip = document.querySelector('#zip').value;
    }
};

payment.init();