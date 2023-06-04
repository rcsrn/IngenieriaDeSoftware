document.getElementById("boton_cerrar_sesion").addEventListener("click", cierre);
document.getElementById("boton_actualizar_contrasena").addEventListener("click", actualiza);

function cierre() {
    const response = fetch('http://localhost:8081/michelin/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    });
    const jsonData = response.json();
    console.log(jsonData)
}

function actualiza() {
    const response = fetch('http://localhost:8081/michelin/update/password', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    });
    const jsonData = response.json();
    console.log(jsonData)
}
