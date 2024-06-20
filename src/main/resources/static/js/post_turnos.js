window.addEventListener('load' , function(){

    const urlP ="/paciente";
    const settingsP ={
        method: 'GET'
    }

    fetch(urlP, settingsP)
    .then(response => response.json())
    .then(data =>{
        for(paciente of data){

            let option = `<option id="${paciente.id}" value="${paciente.id}">${paciente.nombre} ${paciente.apellido}</option>`;
            let selectElement = document.getElementById("select_paciente");

            selectElement.innerHTML = selectElement.innerHTML + option;
        }
    })
    const urlO="/odontologos";
    const settingsO ={
        method: 'GET'
    }
    fetch(urlO, settingsO)
    .then(response => response.json())
    .then(data =>{
        for(odontologo of data){
            
            let option =`<option id="${odontologo.id}" value="${odontologo.id}">${odontologo.nombre} ${odontologo.apellido}</option>`;
            let selectElement = this.document.getElementById("select_odontologo");

            selectElement.innerHTML= selectElement.innerHTML + option;
        }
    })

    const formulario = document.querySelector('#add_new_turno');
    formulario.addEventListener('submit', function(event){

        const formData= {
            fecha: document.querySelector('#fechaIngreso').value,
            paciente:{
                id: document.querySelector('#select_paciente').value,
            },
            odontologo: {
                id: document.querySelector('#select_odontologo').value

            } 
        };

        const url ="/turnos"
        const settings ={
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        }
        fetch(url, settings)
        .then(response => response.json())
        .then(data=> {
            let successAlert = '<div class="alert alert-success alert-dismissible">' +
            '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
            '<strong></strong> Turno agregado </div>'

            document.querySelector('#response').innerHTML = successAlert;
            document.querySelector('#response').style.display = "block";

        })
        event.preventDefault();
    })
  
})