

document.getElementById("btn_registrarse").addEventListener("click", register);
document.getElementById("btn_iniciar-sesion").addEventListener("click", iniciarSesion);
document.getElementById("registrar").addEventListener("click", registro);
document.getElementById("entrar").addEventListener("click", entrar);
window.addEventListener("resize", anchoPagina);

// Variables
var contenedor_login_register = document.querySelector(".contenedor_login-register");
var formulario_login = document.querySelector(".formulario_login");
var formulario_register = document.querySelector(".formulario_register");
var caja_trasera_login = document.querySelector(".caja_trasera_login");
var caja_trasera_register = document.querySelector(".caja_trasera_register");

function anchoPagina(){
	if(window.innerWidth > 850){
		caja_trasera_login.style.display = "block";
		caja_trasera_register.style.display = "block";
	}else{
		caja_trasera_register.style.display = "block";
		caja_trasera_register.style.opacity = "1";
		caja_trasera_login.style.display = "none";
		formulario_login.style.display = "block";
		formulario_register.style.display = "none";
		contenedor_login_register.style.left = "8px";
	}
}

anchoPagina();

function iniciarSesion() {

	if (innerWidth > 850) {
		formulario_register.style.display = "none";
		contenedor_login_register.style.left = "0px";
		formulario_login.style.display = "block";
		caja_trasera_register.style.opacity = "1";
		caja_trasera_login.style.opacity = "0";
	} else {
		formulario_register.style.display = "none";
		contenedor_login_register.style.left = "0px";
		formulario_login.style.display = "block";
		caja_trasera_register.style.display = "block";
		caja_trasera_login.style.display = "none";
		contenedor_login_register.style.top = "2"; // Ajusta el valor según el ancho del contenedor
	}
}

function register() {

	if (innerWidth > 850) {
		formulario_register.style.display = "block";
		contenedor_login_register.style.left = "410px";
		formulario_login.style.display = "none";
		caja_trasera_register.style.opacity = "0";
		caja_trasera_login.style.opacity = "1";
	} else {
		formulario_register.style.display = "block";
		contenedor_login_register.style.left = "0px";
		formulario_login.style.display = "none";
		caja_trasera_register.style.display = "none";
		caja_trasera_login.style.display = "block";
		caja_trasera_login.style.opacity = "1";
	}
}

function registro() {
	event.preventDefault();

	const nombre = document.getElementById("nombreRegistro").value;
    const email = document.getElementById("emailRegistro").value;
	const date = document.getElementById("dateOfBirth").value;

	const fecha = new Date(date);
	const dia = (fecha.getDate() + 1).toString().padStart(2, '0');
	const mes = (fecha.getMonth() + 1).toString().padStart(2, '0');
	const anio = fecha.getFullYear().toString();

	const fechaForm = `${dia}/${mes}/${anio}`;

	const usuario = {
        name: nombre,
        email: email,
		dateOfBirth: fechaForm
    };

	console.log(JSON.stringify(usuario));


	fetch('http://localhost:8081/michelin/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(usuario)
    })
    .then(response => response.json())
    .then(data => {
        console.log(data); // Hacer algo con los datos de respuesta
    })
    .catch(error => {
        console.error('Error:', error); // Manejar cualquier error de la llamada
    });

}

function entrar() {
	event.preventDefault();
	const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

	const client = {
		email: email,
		password: password
	};

	fetch('http://localhost:8081/michelin/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
		body: JSON.stringify(client)
    })
    .then(response => response.json())
    .then(data => {
        console.log(data);
		if(data.message == "Éxito"){
			window.location.href = '/michelin/home';
		}
    })
    .catch(error => {
        console.error('Error:', error);
    });

}
