document.querySelectorAll(".cartbutton").forEach(button => button.addEventListener("click", async ()=> {
    let id = button.dataset.id
    await addToCart(id)

}))

async function addToCart(id) {
    try {
        await fetch(`/cart?id=${id}`)
    } catch (e) {
        console.log('error', e)
    }
}