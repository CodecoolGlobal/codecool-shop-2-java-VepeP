const userData = {
    name: "",
    email: "",
    address: "",
    city: "",
    state: "",
    zip: ""
};

const cardData = {
    name: "",
    number: "",
    month: "",
    year: "",
    cvv: ""
}

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

        cardData.name = document.querySelector('#cname').value;
        cardData.number = document.querySelector('#ccnum').value;
        cardData.month = document.querySelector('#expmonth').value;
        cardData.year = document.querySelector('#expyear').value;
        cardData.cvv = document.querySelector('#cvv').value;

        payment.checkData();
    },

    checkData: function() {
        let access = true;

        for (let data in userData) {
            if (userData[data] === "") {
                alert(`Please enter your ${data}`);
                access = false;
                break;
            }
        }

        for (let data in cardData) {
            if (cardData[data] === "") {
                alert(`Please enter ${data} from your Card`);
                access = false;
                break;
            }
        }

        if (access) payment.dataPost();
    },

    sendEmail: function (order){
        console.log(order);
        console.log(order.id);
        console.log(order[id]);
        fetch("/sendEmail?orderId=" + order.id, {
            method: 'GET',
            credentials: 'same-origin',
            headers: {
                'Content-Type': 'application/json'
            }})
            .then();
    },

    dataPost: function() {
        fetch("/user-data", {
            method: 'POST',
            credentials: 'same-origin',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(userData)})
            .then(response => response.json())
            .then(json_response => payment.sendEmail(json_response));

        window.location.href = "/";
    }
};

payment.init();