document.getElementById('addNewTix').onclick = postData; 

var emp;

function getUser() {

    var apiURL = "http://localhost:7001/whoisloggedin";

    fetch(apiURL)
        .then(response => response.json())
        .then(json => displayName(json))
        .catch(err => console.log("Request Failed", err));


function displayName(response){
    console.log(response);
  
    dataSection = document.getElementById("header");
    dataSection.innerHTML = `Hello, ${response.first_name}!`;
    emp = response.email;

}
}







function postData() {  
    
    var category = document.getElementById('category').value; // my textboxes
    var amt = document.getElementById('amt').value;
    //var emp = response.email;
    console.log(emp);

    var month = document.getElementById('month').value;
    var day = document.getElementById('day').value;
    var year = document.getElementById('year').value;
    var purchased = year + '-' + month + '-' + day;

    var apiURL = "http://localhost:7001/newTix.html/" + purchased +"/" + category + "/"+ amt + "/" + emp;
    console.log(apiURL);
    fetch(apiURL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(),
        })
        .then(json => {
            window.location.href = "ticketCenter.html"
        })
        .catch(err => console.log("Request Failed", err));
}