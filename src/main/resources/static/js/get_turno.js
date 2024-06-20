window.addEventListener('load', function(){
    
    const url ='/turnos';
    const settings ={
        method: 'GET',
    }

    this.fetch(url, settings)
    .then(response=> response.json())
    .then(data => {
        for(turno of data){

            var table = this.document.getElementById("turnoTable").getElementsByTagName("tbody")[0];
            var turnoRow =table.insertRow();
            let tr_id =turno.id;
            turnoRow.id =tr_id;


            let deleteButton = '<button' +
            ' id=' + '\"' + 'btn_delete_' + turno.id + '\"' +
            ' type="button" onclick="deleteBy('+turno.id+')" class="btn btn-danger btn_delete">' +
            '&times' +
            '</button>';


            let updateButton = '<button' +
            ' id=' + '\"' + 'btn_id_' + turno.id + '\"' +
            ' type="button" onclick="findBy('+turno.id+')" class="btn btn-info btn_id">' +
            turno.id +
            '</button>';

            turnoRow.innerHTML = '<td>' + updateButton + '</td>' +
            '<td class=\"td_fecha\">' + turno.fecha + '</td>' +
            '<td class=\"td_paciente\">' + turno.paciente.nombre.toUpperCase() + " " + turno.paciente.apellido.toUpperCase() + '</td>' +
            '<td class=\"td_odontologo\">' + turno.odontologo.nombre.toUpperCase() + " " + turno.odontologo.apellido.toUpperCase() +  '</td>' +
            '<td>' + deleteButton + '</td>';

        };

    })
})