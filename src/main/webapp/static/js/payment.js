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

        payment.checkData();
    },

    checkData: function() {
        let access = false;

        for (let data in userData) {
            if (userData[data] === "") {
                alert(`Please enter your ${data}`);
                access = false;
                break;
            }
            else access = true;
        }

        if (access) payment.dataPost();
    },

    dataPost: function() {
        fetch("/user-data", {
            method: 'POST',
            credentials: 'same-origin',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(userData)})
            .then();
    }
};

payment.init();