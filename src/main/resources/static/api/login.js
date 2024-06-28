document.getElementById("form-login").addEventListener("submit", function(event) {
    event.preventDefault()

    const login = document.getElementById("email").value
    const password = document.getElementById("password").value

    userAuth = {
        "login": login,
        "password": password
    }

    fetch('http://localhost:8080/authenticate', {
        method: "POST",
        headers: { 
            "Content-Type": "application/json"
        },
        body: JSON.stringify(userAuth)
    })
    .then(response => {
        if (response.ok) {
            return response.json()
        }
        return null
    })
    .then(data => {
        if (data) {
            sessionStorage.setItem("token", data.token)
        } else {
            showData("E-mail or Password not found", "red")
        }
    })
    .catch(error => {
        console.log(error)
    })

    function showData(text, cl) {
        const divStatus = document.getElementById("status")

        if (divStatus.querySelector("p")) divStatus.querySelector("p").remove()

        const parag = document.createElement("p")
        parag.textContent = text
        parag.style.color = cl

        divStatus.appendChild(parag)
    }
})