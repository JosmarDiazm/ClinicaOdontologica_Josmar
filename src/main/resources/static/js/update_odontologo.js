window.addEventListener('load', function(event){
    const formulario = document.querySelector('#update_odontologo_form');
    formulario.addEventListener('submit', function(event){
        const formData = {
            id: document.querySelector('#odontologo_id').value,
            numeroMatricula: document.querySelector('#matricula').value,
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
        }
        const url ='/odontologos';
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

    const url ="odontologos/"+id;
    const settings ={
        method: "GET"
    }
    fetch(url, settings)
    .then(response => response.json())
    .then(data =>{
        document.querySelector('#odontologo_id').value = odontologo.id;
        document.querySelector('#matricula').value = data.numeroMatricula;
        document.querySelector('#nombre').value = data.nombre;
        document.querySelector('#apellido').value = data.apellido;
        document.querySelector('#div_odontologo_updating').style.display ="block";

    }).catch(error =>{
        console.log(error);
    })
}