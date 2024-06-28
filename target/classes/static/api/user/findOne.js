const token = sessionStorage.getItem("token")

fetch("http://localhost:8080/users/profile", {
    method: "GET", 
    headers: {
        "Authorization": "Bearer " + token
    }
}) 
.then(response => {
    if (response.ok) {
        return response.json()
    } 
})
.then(data =>{
    showData(data)
})
.catch(error => {
    console.log(error)
})


function showData(data) {

    const divContent = document.getElementById("content")
    const divName = document.getElementById("name")
    const divEmail = document.getElementById("email")
    const divPassword = document.getElementById("password")
 
    const imgUser = document.createElement("img")
    imgUser.src = data.urlImage
    imgUser.className = "img-class"
    divContent.insertBefore(imgUser, divContent.firstChild)

    const spanName = document.createElement("span")
    spanName.textContent = data.username 
    divName.appendChild(spanName)

    const spanEmail = document.createElement("span")
    spanEmail.textContent = data.login
    divEmail.appendChild(spanEmail)

    const spanPassword = document.createElement("span")
    spanPassword.textContent = data.password
    divPassword.appendChild(spanPassword)
}