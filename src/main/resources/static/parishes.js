
getAllParishes();

/*
As long as the list from parishes is larger than 0 it creates rows to a table
and insert the value of the i into the innerHtml
 */
async function getAllParishes(){

    const parishesOverview = document.getElementById("parishesTable");

    const url = "/api/parish/all-parishes";

    const response = await fetch(url);
    const parishes = await response.json();

    for(let i = 0; i < parishes.length; i++){

        const row = parishesOverview.insertRow();

        //Checkbox is set automatically and depends on if the parish is lockdown
        const checkBox = row.insertCell();
        const check = document.createElement('input');
        check.setAttribute("type", "checkbox");

        if(parishes[i].isInLockdown === true){
            check.checked = true;
        }else{
            check.checked = false;
        }
        check.disabled = true;
        checkBox.appendChild(check);

        //Cell for parish code
        const parishCode = row.insertCell(1);
        parishCode.innerHTML = parishes[i].parishCode;
        parishCode.id = parishes[i].parishCode;

        //Cell for parish name
        const name = row.insertCell(2);
        name.innerHTML = parishes[i].parishName;
        name.id = parishes[i].parishName;

        //The positive percentage for the specific object
        //This is changeable, but is by default disabled
        const positivePercentage = row.insertCell(3);
        const editPositivePercentage = document.createElement('input');
        editPositivePercentage.setAttribute('type', 'number');
        editPositivePercentage.setAttribute('step', '.01')
        editPositivePercentage.value= parishes[i].positivePercentage;
        editPositivePercentage.disabled = true;
        positivePercentage.appendChild(editPositivePercentage);

        //The incidents for the specific object
        //This is changeable, but is by default disabled
        const incidents = row.insertCell(4);
        const editIncidents = document.createElement('input');
        editIncidents.setAttribute('type', 'number');
        editIncidents.setAttribute('step', '.01')
        editIncidents.value = parishes[i].incidents;
        editIncidents.disabled = true;
        incidents.appendChild(editIncidents);

        //The total infected last week for the specific object
        //This is changeable, but is by default disabled
        const totalInfectedLastWeek = row.insertCell(5);
        const editTotalInfectedLastWeek = document.createElement('input');
        editTotalInfectedLastWeek.setAttribute('type', 'number');
        editTotalInfectedLastWeek.value = parishes[i].totalInfectedLastWeek;
        editTotalInfectedLastWeek.disabled = true;
        totalInfectedLastWeek.appendChild(editTotalInfectedLastWeek);

        //The municipality name for the specific object
        //It is not possible to change this, what a mess it could be
        const municipality = row.insertCell(6);
        municipality.innerHTML = parishes[i].municipality.municipalityName;
        municipality.id = parishes[i].municipality.municipalityId;

        //Set the date for lockdown to N/A if it is not in a lockdown
        //If it is the date will be there
        //It is not possible to change this because the date is set to be the current date
        const lockdownStart = row.insertCell(7);
        if(parishes[i].lockdownStart === null){
            lockdownStart.innerHTML = 'N/A';
        }else{
            lockdownStart.innerHTML = parishes[i].lockdownStart;
        }

        //Button to edit
        //Has a function when it's clicked where is make disabled to false
        const editParish = row.insertCell(8);
        const editButton = document.createElement('button');
        editButton.setAttribute("class", "btn btn-warning");
        editButton.innerHTML = "Rediger";
        editButton.value = "Rediger";
        editButton.onclick = function (){

            if(editButton.value === "Rediger"){
                editButton.innerHTML = "Gem";
                editButton.value = "Gem";
                editButton.setAttribute("class", "btn btn-success");

                editPositivePercentage.disabled = false;
                editIncidents.disabled = false;
                editTotalInfectedLastWeek.disabled = false;

            }else{
                editButton.innerHTML = "Rediger";
                editButton.value = "Rediger";
                editButton.setAttribute("class", "btn btn-warning");

                editPositivePercentage.disabled = true;
                editIncidents.disabled = true;
                editTotalInfectedLastWeek.disabled = true;

                const updatedParish = {
                    "parishCode": parishCode.id,
                    "parishName": name.id,
                    "positivePercentage": editPositivePercentage.value,
                    "incidents": editIncidents.value,
                    "totalInfectedLastWeek": editTotalInfectedLastWeek.value,
                    "municipality": {
                        "municipalityId": municipality.id
                    }
                }

                const updatedStringified = JSON.stringify(updatedParish);

                if(confirm("Vil du gemme dine ændringer?") === true){
                    updateParish(updatedStringified);
                }
            }

        }
        editParish.appendChild(editButton);

        //Button for deleting a parish
        //Has a function when it is clicked if is confirmed the object will be deleted
        const deleteParish = row.insertCell(9);
        const deleteButton = document.createElement('button');
        deleteButton.setAttribute("class", "btn btn-danger");
        deleteButton.innerHTML = "Slet";
        deleteButton.onclick = function (){
            if(confirm("Vil du slette dette sogn?") === true){
                deleteParishFunc(parishes[i].parishCode);
            }
        }
        deleteParish.appendChild(deleteButton);
    }
}

//Function for updating the parish object
function updateParish(updatedParish){

    const updateUrl = "api/parish/update-parish";

    const postObject = {
        headers: {
            'Content-type': 'application/json'
        },
        method: 'PUT',
        body: updatedParish
    };

    fetch(updateUrl, postObject)
        .then(() => {
            location.reload();
        })
}

//Function for deleting the parish object
function deleteParishFunc(parishCode){
    const deleteUrl = "/api/parish/delete-parish/" + parishCode;

    const deleteObject = {
        method: 'DELETE'
    }
    fetch(deleteUrl, deleteObject)
        .then(() => {
            location.reload();
        })
}








