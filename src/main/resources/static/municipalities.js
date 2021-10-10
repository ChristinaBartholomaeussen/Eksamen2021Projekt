getAllMunicipalities();

/*
As long as the list from data is larger than 0 it creates rows to a table
and insert the value of the i into the innerHtml
 */
async function getAllMunicipalities(){

    const municipalitiesOverview = document.getElementById("municipalitiesTable");

    const url = "api/municipality/get-all";
    const response = await fetch(url);
    const data = await response.json();

    for(let i = 0; i < data.length; i++){

        const row = municipalitiesOverview.insertRow();

        const municipalitiyCode = row.insertCell(0);
        municipalitiyCode.innerHTML = data[i].municipalityCode;

        const municipalityName = row.insertCell(1);
        municipalityName.innerHTML = data[i].municipalityName;

        const municipalityPositivePercentage = row.insertCell(2);
        municipalityPositivePercentage.innerHTML = data[i].positivePercentage;

    }
}
