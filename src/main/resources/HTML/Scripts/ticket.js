

function getUser() {

    //find out who is logged in
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = receiveData;
    xhttp.open('GET', '/whoisloggedin'); 
    xhttp.send();
    
    function receiveData() {
        var responseSection = document.getElementById("restData");
        
        if (xhttp.readyState === 4) { 
            if (xhttp.status === 200) { 
                var response = xhttp.responseText;
                response = JSON.parse(response);
                displayResponse(response);
                
            } else {
                responseSection.innerHTML = 'Something went wrong. Try reloading';
            }
        } else {
            responseSection.innerHTML = 'I\'m thinking....'; 
        } 
    }
}

function displayResponse(response){
    var responseSection = document.getElementById("restData"); 
    if(response.first_name==='null'){
        responseSection.innerHTML = 'You are not logged in!';
    }else{
        responseSection.innerHTML = `<h1>Hello, ${response.first_name}!</h1>`;
        getPending(response.email);
        getCompleted(response.email);
    }
}

function getPending(email) {

    var apiURL = "http://localhost:7001/pending/" + email;
    console.log(apiURL);
    fetch(apiURL)
        .then(responseP => responseP.json())
        .then(json => displayPending(json, email))
        .catch(err => console.log("Request Failed", err));
}

function getCompleted(email) {

    var apiURL = "http://localhost:7001/completed/" + email;
    console.log(apiURL);
    fetch(apiURL)
        .then(responseC => responseC.json())
        .then(json => displayCompleted(json, email))
        .catch(err => console.log("Request Failed", err));
}

function displayPending(responseP, email) {
    dataSection = document.getElementById("pendingTix");

/*    var head = document.createElement("h3");
    head.innerHTML = "Pending";
    dataSection.appendChild(head);
*/

    var headerArr = ["Ticket #","Submit Date","Purchase Date","Type","Amount","Employee"];
    
    var table = document.createElement('table');
    var trh = document.createElement('tr');

    for(let word of headerArr){
        var th = document.createElement("th");
        var thText = document.createTextNode(word);

        th.appendChild(thText);
        trh.appendChild(th);
    }

    table.appendChild(trh);

    for(i=0 ; i < responseP.length ; i++){
        console.log(responseP[i]);
        var tr = document.createElement('tr');  

        var td1 = document.createElement("td");
        var td1Text = document.createTextNode(responseP[i].tixNum);
        td1.appendChild(td1Text);
        tr.appendChild(td1);

        var td2 = document.createElement("td");
        var td2Text = document.createTextNode(responseP[i].submitted);
        td2.appendChild(td2Text);
        tr.appendChild(td2);

        var td3 = document.createElement("td");
        var td3Text = document.createTextNode(responseP[i].purchased);
        td3.appendChild(td3Text);
        tr.appendChild(td3);

        var td4 = document.createElement("td");
        var td4Text = document.createTextNode(responseP[i].category);
        td4.appendChild(td4Text);
        tr.appendChild(td4);

        var td5 = document.createElement("td");
        var td5Text = document.createTextNode(responseP[i].amt);
        td5.appendChild(td5Text);
        tr.appendChild(td5);

        var td6 = document.createElement("td");
        var td6Text = document.createTextNode(email);
        td6.appendChild(td6Text);
        tr.appendChild(td6);

        table.appendChild(tr);
    }
    dataSection.appendChild(table);
}

function displayCompleted(response, email) {
    dataSection = document.getElementById("completedTix");

    var headerArr = ["Ticket #","Submit Date","Purchase Date","Type","Amount","Employee","Status","Aprover ID","Receipt"];

    var table = document.createElement('table');
    var trh = document.createElement('tr');

    for(let word of headerArr){
        var th = document.createElement("th");
        var thText = document.createTextNode(word);

        th.appendChild(thText);
        trh.appendChild(th);
    }

    table.appendChild(trh);

    for(i=0 ; i < response.length ; i++){
        console.log(response[i]);
        var tr = document.createElement('tr');  

        var td1 = document.createElement("td");
        var td1Text = document.createTextNode(response[i].tixNum);
        td1.appendChild(td1Text);
        tr.appendChild(td1);

        var td2 = document.createElement("td");
        var td2Text = document.createTextNode(response[i].submitted);
        td2.appendChild(td2Text);
        tr.appendChild(td2);

        var td3 = document.createElement("td");
        var td3Text = document.createTextNode(response[i].purchased);
        td3.appendChild(td3Text);
        tr.appendChild(td3);

        var td4 = document.createElement("td");
        var td4Text = document.createTextNode(response[i].category);
        td4.appendChild(td4Text);
        tr.appendChild(td4);

        var td5 = document.createElement("td");
        var td5Text = document.createTextNode(response[i].amt);
        td5.appendChild(td5Text);
        tr.appendChild(td5);

        var td6 = document.createElement("td");
        var td6Text = document.createTextNode(email);
        td6.appendChild(td6Text);
        tr.appendChild(td6);

        table.appendChild(tr);
    }
    dataSection.appendChild(table);
}

