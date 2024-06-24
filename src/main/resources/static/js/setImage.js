function setImage(event) {
    let image = document.getElementById("user-image")
    image.src = URL.createObjectURL(event.target.files[0])
    image.onload = function() {
        URL.revokeObjectURL(image.src); 
    };
}