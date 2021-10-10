/* When the form is submitted is function is called */
function submitForm(){
    saveParish();
}

/* Function for insert/saving a new object of parish */
async function saveParish(){
    const selectedMunicipality = list[dropdownMunicipalities.selectedIndex];
    const createUrl = "api/parish/create-parish";
    const requestObject = {
        "parishCode": document.getElementById("code").value,
        "parishName": document.getElementById("name").value,
        "positivePercentage": document.getElementById("positivePercentage").value,
        "incidents": document.getElementById("incidents").value,
        "totalInfectedLastWeek": document.getElementById("totalInfectedLastWeek").value,
        "municipality": {
            "municipalityId": selectedMunicipality.municipalityId
        }
    }
    const parishStringified = JSON.stringify(requestObject);
    const postObject = {
        headers: {
            'Content-type': 'application/json'
        },
        method: 'POST',
        body: parishStringified
    };
    fetch(createUrl, postObject)
        .then(response => response.json())
        .then(data => {
            console.log(data);
    })
}

let list = getAllMunicipalities().then(x => list = x);
const dropdownMunicipalities = document.getElementById("dropdownMunicipalities");

/* Function to receive all municipalities by a GetMapping */
async function getAllMunicipalities(){

    const url = "api/municipality/get-all";
    const response = await fetch(url);
    const data = await response.json();

    data.forEach(x => {
        let element = document.createElement('option');
        element.value = x.municipalityId;
        element.text = x.municipalityName;
        dropdownMunicipalities.appendChild(element)
    })
    return data;
}