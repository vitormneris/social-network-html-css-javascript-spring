document.getElementById("form-insert").addEventListener("submit", function(event) {
    event.preventDefault();

    const name = document.getElementById("name").value
    const email = document.getElementById("email").value
    const password = document.getElementById("password").value
    const image = document.getElementById("image").files[0]

    const formData = new FormData();

    formData.append("user", new Blob([JSON.stringify({
        username: name,
        email: email,
        password: password
    })], { type: "application/json" }));

    formData.append("file", image);

    fetch('http://localhost:8080/users', {
        method: 'POST',
        body: formData
    })
    .then(async response => {
        if (response.status === 201) {
            console.log("CREATED WITH SUCCESSFULLY")
            showMessage("Account created successfully.", "green")
        } else {
            console.log("ERROR. NOT CREATED")
            const errorJson = JSON.parse(await response.text())
            showMessage(errorJson.message, "red")
        }
    })
    .catch(error => {
        console.log(error)
    })
})

function showMessage(msg,cl) {
    const divStatus = document.getElementById("status")
    
    if (divStatus.querySelector("p")) divStatus.querySelector("p").remove()

    const pag = document.createElement("p")
    pag.textContent = msg
    pag.style.color = cl

    divStatus.appendChild(pag)
}
