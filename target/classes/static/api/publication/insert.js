document.getElementById("form-post").addEventListener("submit", function(event) {
    event.preventDefault()

    const token = sessionStorage.getItem("token")
    const message = document.getElementById("message").value

    post = {
        "content": message
    }

    fetch("http://localhost:8080/publications", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + token
        },
        body: JSON.stringify(post)
    })
    .then(response => {
        if (response.ok) {
            window.location.href = "../public/feed.html"
        } else {
            showData("Not was possible send your post", "red")
        }
    })

    function showData(msg, cl) {
        const divStatus = document.getElementById("status")

        if (divStatus.querySelector("p")) divStatus.querySelector("p").remove()

        const parag = document.createElement("p")
        parag.textContent = msg
        parag.style.color = cl

        divStatus.appendChild(parag)
    }
})