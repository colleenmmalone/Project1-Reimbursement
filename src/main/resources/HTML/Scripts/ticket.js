var emp;

function getUser() {

    //find out who is logged in
    var apiURL = "http://localhost:7001/whoisloggedin";

    fetch(apiURL)
        .then(response => response.json())
        .then(json => displayResponse(json))
        .catch(err => console.log("Request Failed", err));

    function displayResponse(response){
        emp = response;
        var responseSection = document.getElementById("restData"); 
        if(response.first_name==='null'){
            responseSection.innerHTML = 'You are not logged in!';
        }else{
            
            responseSection.innerHTML = `<h1>Hello, ${response.first_name}!</h1>`;
            if(response.id === 'EMPLOYEE'){
                getPending(response.email);
                getCompleted(response.email);
            }else{
                var admin = document.createElement('h3');
                admin.innerHTML = 'Admin View';
                responseSection.appendChild(admin);
                getAllPending();
                getAllCompleted();
            }
        }
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

function getAllPending() {
    var apiURL = "http://localhost:7001/admin/pending/";
    console.log(apiURL);
    fetch(apiURL)
        .then(response => response.json())
        .then(json => displayAllPending(json, email))
        .catch(err => console.log("Request Failed", err));
}

function getAllCompleted() {
    var apiURL = "http://localhost:7001/admin/completed/";
    console.log(apiURL);
    fetch(apiURL)
        .then(response => response.json())
        .then(json => displayAllCompleted(json, email))
        .catch(err => console.log("Request Failed", err));
}

function displayPending(responseP, email) {
    dataSection = document.getElementById("pendingTix");

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

    var headerArr = ["Ticket #","Submit Date","Purchase Date","Type","Amount","Employee","Aprover ID"];

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

        var td7 = document.createElement("td");
        var td7Text = document.createTextNode(response[i].approver);
        td7.appendChild(td7Text);
        tr.appendChild(td7);

        table.appendChild(tr);
    }
    dataSection.appendChild(table);
}

