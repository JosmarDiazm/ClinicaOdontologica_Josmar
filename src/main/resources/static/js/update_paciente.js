window.addEventListener('load', function(event){
    const formulario = document.querySelector('#update_paciente_form');
    formulario.addEventListener('submit', function(event){
        const formData = {
            id: document.querySelector('#paciente_id').value,
            nombre: document.querySelector('#nombre').value,
            apellido:document.querySelector('#apellido').value,
            cedula: document.querySelector('#cedula').value,
            domicilio:{
                calle: document.querySelector('#calle').value,
                numero: document.querySelector('#numero').value,
                localidad: document.querySelector('#localidad').value,
                provincia: document.querySelector('#provincia').value,
            },
            email: document.querySelector('#email').value
        }
        const url ='/paciente/actualizar';
        const settings ={
            method: 'PUT',
            headers:{
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
        fetch(url, settings)
            .then(response => response.text())
            .then(data => {
                console.log(data)
            })
        event.preventDefault();
        location.reload();

    })
})
function findBy(id){

    const url ="paciente/buscar/"+id;
    const settings ={
        method: "GET"
    }
    fetch(url, settings)
        .then(response => response.json())
        .then(data =>{
            document.querySelector('#paciente_id').value = paciente.id;
            document.querySelector('#nombre').value = data.nombre;
            document.querySelector('#apellido').value = data.apellido;
            document.querySelector('#cedula').value = data.cedula;
            document.querySelector('#calle').value = data.domicilio.calle;
            document.querySelector('#numero').value = data.domicilio.numero;
            document.querySelector('#localidad').value = data.domicilio.localidad;
            document.querySelector('#provincia').value = data.domicilio.provincia;
            document.querySelector('#email').value = data.email;
            document.querySelector('#div_paciente_updating').style.display ="block";

        }).catch(error =>{
        console.log(error);
    })
}