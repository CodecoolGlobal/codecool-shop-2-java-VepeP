let sortTitle = document.getElementById('sort-title')
let items = document.getElementById('products')


document.querySelectorAll(".category-select").forEach( item => item.addEventListener("click", () => {
    let id = item.dataset.id
    sort("categories", id)
    sortTitle.innerHTML = item.dataset.name
}))
document.querySelectorAll(".supplier-select").forEach( item => item.addEventListener("click", () => {
    let id = item.dataset.id
    sort("suppliers", id)
    sortTitle.innerHTML = item.dataset.name

}))



async function sort (type, id) {
    let data = await fetchSortedItems(type, id)
    console.log("itt", type, id)

    let itemsHTML = ``

    for (let item of data){
        itemsHTML += `
        <div class="col col-sm-12 col-md-6 col-lg-4">
            <div class="card">
                <img class="" src='static/img/product_${item.id}.jpg' alt="" />
                <div class="card-header">
                    <h4 class="card-title" >${item.name}</h4>
                    <p class="card-text"> ${item.description}</p>
                </div>
                <div class="card-body">
                    <div class="card-text">
                        <p class="lead">${item.defaultPrice} ${item.defaultCurrency}</p>
                    </div>
                    <div class="card-text">
                        <a class="btn btn-success" href="#">Add to cart</a>
                    </div>
                </div>
            </div>
        </div>`

    }


    items.innerHTML = itemsHTML

}



async function fetchSortedItems(type, id) {
    try {
        const response = await fetch(`/${type}?id=${id}`)
        return await response.json()

    } catch (e) {
        console.log('error', e)
    }
}