document.querySelectorAll(".category-select").forEach( item => item.addEventListener("click", async () => console.log(await fetchSortedItems(1))))
document.querySelectorAll(".supplier-select").forEach( item => item.addEventListener("click", () => console.log('clicked')))




async function fetchSortedItems(id) {
    try {
        const response = await fetch(`/categories?id=${id}`)
        const data = await response.json()
        return data

    } catch (e) {
        console.log('error', e)
    }
}