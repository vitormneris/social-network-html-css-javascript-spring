
fetch('http://localhost:8080/publications', {
    method: "GET"
}).then(
    response => {
        if (response.status === 200) {
            return response.json()
        }
}).then( data => {
    showData(data)
}).catch(error => {
    console.log(error)
})

function showData(data) {
    const divConteiner = document.getElementById("container")

    data.forEach(contents => {

        const date = new Date(contents.date)
        const dateFormat = ("0" + date.getDate()).slice(-2) + "/" + ("0" + (date.getMonth() + 1)).slice(-2) + "/" + date.getFullYear()
        const hourFormat = ("0" + date.getHours()).slice(-2) + ":" + ("0" + date.getMinutes()).slice(-2) + ":" + ("0" + date.getSeconds()).slice(-2) 

        const divItem = document.createElement("div")
        divItem.className = "items"

        const divInfoContent = document.createElement("div")
        divInfoContent.className = "info-content"

        const spa = document.createElement("span")
        spa.textContent = "Published by " + contents.author.username + " at " + dateFormat + " " + hourFormat
 
        divInfoContent.appendChild(spa)

        const divTextContent = document.createElement("div")
        divTextContent.className = "text-content"

        const parag = document.createElement("p")
        parag.textContent = contents.content

        divTextContent.appendChild(parag)

        divItem.appendChild(divInfoContent)
        divItem.appendChild(divTextContent)

        divConteiner.appendChild(divItem)
    })
}