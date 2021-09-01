const button = document.getElementById("viewcartbutton")


button.addEventListener("click", async () => {
    let data = await fetchCart()

    let total = 0

    let cartitemsHTML = ``

    for (let item of data) {
        let subtotal = item.defaultPrice * item.quantity
        total += subtotal

        cartitemsHTML += `
        <tr>
                                <td>
                                    <div class="row">
                                        <div class="col-lg-2 Product-img">
                                            <img src='static/img/product_${item.id}.jpg' alt="..." class="img-responsive" />
                                        </div>
                                        <div class="col-lg-10">
                                            <h4 class="nomargin">${item.name}</h4>
                                        </div>
                                    </div>
                                </td>
                                <td>$${item.defaultPrice} </td>
                                <td data-th="Quantity">
                                    <input type="number" class="form-control text-center" value="${item.quantity}" min="0" max="168">
                                </td>
                                <td>USD ${subtotal}</td>
                                <td class="actions" data-th="" style="width:10%;">
                                    <button class="btn btn-warning btn-sm"><i class="fa fa-refresh"></i></button>
                                    <button class="btn btn-danger btn-sm"><i class="fa fa-trash-o"></i></button>
                                </td>
                            </tr>
        
        `

        console.log(cartitemsHTML)

        document.getElementById("cartitemsplace").innerHTML = cartitemsHTML
        document.getElementById("totalprice").innerText="Total:  " + total

    }

})

async function fetchCart() {
    try {
        const response = await fetch(`/getCart`)
        return await response.json()

    } catch (e) {
        console.log('error', e)
    }
}