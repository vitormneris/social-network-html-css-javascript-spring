fetch('http://localhost:8080/users', {
    method: 'GET'
})
.then(response => {
    if (response.ok) {
        return response.json()
    }
})
.then(data => {
    showData(data)
})
.catch(error => {
    console.log(error)
});

function showData(data) {
    const div = document.getElementById('dataList')

    data.forEach(element => {
        const trList = document.createElement('tr')

        const tdId = document.createElement("td")
        tdId.textContent = element.id
        trList.appendChild(tdId)
        
        const tdName= document.createElement("td")
        tdName.textContent = element.username
        trList.appendChild(tdName)

        const tdEmail = document.createElement("td")
        tdEmail.textContent = element.email
        trList.appendChild(tdEmail)

        const tdPassword = document.createElement("td")
        tdPassword.textContent = element.password
        trList.appendChild(tdPassword)

        const tdUrlImage = document.createElement("td")
        const img = document.createElement("img")
        img.src = element.urlImage
        tdUrlImage.className = "td-img-class"
        tdUrlImage.appendChild(img)
        trList.appendChild(tdUrlImage)

        div.appendChild(trList)
    })
}