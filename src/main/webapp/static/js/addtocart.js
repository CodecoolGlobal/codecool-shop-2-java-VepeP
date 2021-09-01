document.querySelectorAll(".cartbutton").forEach(button => button.addEventListener("click", async ()=> {
    let id = button.dataset.id
    console.log('heló')
    await addToCart(id)

}))

async function addToCart(id) {
    try {
        await fetch(`/cart?id=${id}`)
    } catch (e) {
        console.log('error', e)
    }
}