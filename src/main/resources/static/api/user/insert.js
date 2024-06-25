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
    .then(response => {
        if (response.status === 201) {
            console.log("CREATED WITH SUCCESSFULLY")
            showMessage("Account created successfully.")
        }
    })
    .catch(error => {
        console.log(error)
    })
})

function showMessage(msg) {
    const divStatus = document.getElementById("status")

    const pag = document.createElement("p")
    pag.textContent = msg

    divStatus.appendChild(pag)
}
