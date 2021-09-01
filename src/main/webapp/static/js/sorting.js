document.querySelectorAll(".category-select").forEach( item => item.addEventListener("click", async () => {
    let id = item.dataset.id
    sort("categories", id)
}))
document.querySelectorAll(".supplier-select").forEach( item => item.addEventListener("click", () => console.log('clicked')))


let sortTitle = document.getElementById('sortTitle')
let items = document.getElementById('products')


function sort (type, id) {
    let data = fetchSortedItems(id)


}



async function fetchSortedItems(type, id) {
    try {
        const response = await fetch(`/${type}?id=${id}`)
        const data = await response.json()
        return data

    } catch (e) {
        console.log('error', e)
    }
}