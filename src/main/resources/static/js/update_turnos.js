window.addEventListener('load', function(){
    const urlP ="/paciente";
    const settingsP ={
        method: 'GET'
    }

    fetch(urlP, settingsP)
    .then(response => response.json())
    .then(data =>{
        for(paciente of data){

            let option = `<option id ="${paciente.id}" value ="${paciente.id}">${paciente.nombre} ${paciente.apellido}</option>`;
            let selectElement = document.getElementById("select_paciente");

            selectElement.innerHTML =selectElement.innerHTML + option;
        }
    })
    const urlO ="/odontologos";
    const settingO ={
        method: 'GET'
    }
    fetch(urlO, settingO)
    .then(response => response.json())
    .then(data =>{
        for(odontologo of data){

            let option = `<option id ="${odontologo.id}" value ="${odontologo.id}">${odontologo.nombre} ${odontologo.apellido}</option>`;
            let selectElement =this.document.getElementById("select_odontologo");

            selectElement.innerHTML = selectElement.innerHTML + option;
        }
    })

    const formulario = document.querySelector('#update_turno_form');
    formulario.addEventListener('submit', function(event){

        const formData={
            id: document.querySelector('#turno_id').value,
            fecha: document.querySelector('#fechaIngreso').value,
            paciente:{
                id: document.querySelector('#select_paciente').value,
            },
            odontologo:{
                id: document.querySelector('#select_odontologo').value
            }
        };

        const url ="/turnos"
        const setting={
            method: 'PUT',
            headers:{
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        }

        fetch(url, setting)
        .then(response => response.text())
        .then(data =>{
            location.reload();
        })
        event.preventDefault();
    })

})


function findBy(id){
    const url ="turnos/"+id;
    const settings ={
        method: 'GET'
    }
    fetch(url, settings)
    .then(response => response.json())
    .then(data =>{
        document.querySelector('#turno_id').value = data.id;
        document.querySelector('#fechaIngreso').value = data.fecha;
        document.querySelector('#select_paciente').value = data.paciente.id;
        document.querySelector('#select_odontologo').value = data.odontologo.id;
        document.querySelector('#div_turno_updating').style.display ="block";

    }).catch(error =>{
        console.log(error)
    })
}