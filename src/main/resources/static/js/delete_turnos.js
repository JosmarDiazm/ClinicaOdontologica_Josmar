function deleteBy(id){
    const url ="/turnos/"+id;
    const settings = {
        method: 'DELETE'
    }
    fetch(url, settings)
    .then(response => response.text())
    .then(data => {
        document.getElementById(id).style.display = "none";

    }).catch(error => {
        console.log(error);
    })
}