//const { resolveTypeReferenceDirective } = require("typescript");

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
        //var apiURLP = '';
        //var apiURLC = '';
        /*if(response.id==='ADMIN'){
            apiURLP = apiURLP + '/ADMIN';
            apiURLC = apiURLC + '/ADMIN';
        }
*/
        //
        //getCompleted(response.email);
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

/*
    //retrieve pending tickets
    var xhttpP = new XMLHttpRequest();
    xhttpP.onreadystatechange = receivePending;
    xhttpP.open('GET', '/pending/' + emp); 
    xhttpP.send();
    
    function receivePending() {
        var pendingSection = document.getElementById("pendingTix");
        
        if (xhttpP.readyState === 4) { 
            if (xhttpP.status === 200) { 
                var responseP = xhttpP.responseText;               
                //responseP = JSON.parse(responseP);
                var head = document.createElement("h3");
                head.innerHTML = `Pending ${responseP[1].purchased}`;
                pendingSection.appendChild(head);
                displayPending(responseP);               
            } else {
                pendingSection.innerHTML = 'Could not retrieve pending tickets';
            }
        } else {
            pendingSection.innerHTML = 'I\'m thinking....'; 
        } 
    }
*/



function displayPending(responseP, email) {
	console.log(responseP);
    console.log(responseP[1]);
    console.log(responseP[1].purchased);
    dataSection = document.getElementById("pendingTix");

    var head = document.createElement("h3");
    head.innerHTML = "Pending";
    dataSection.appendChild(head);

    var headerArr = ["Ticket #","Submit Date","Purchase Date","Type","Amount","Employee"];
    //var headerArr = ["Ticket #","Submit Date","Purchase Date","Type","Amount","Employee","Status","Aprover ID","Receipt"];

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
        td5.appendChild(td6Text);
        tr.appendChild(td6);




        table.appendChild(tr);
    }
    dataSection.appendChild(table);
}
/*ction displayCompleted(email) {
	
    dataSection = document.getElementById("completedTix");
    var table = document.createElement('table');
    var headerArr = ["Ticket #","Submit Date","Purchase Date","Type","Amount","Employee","Status","Aprover ID","Receipt"];
    var myArr = ["19284","2021/11/30","2021/11/26","GAS","$50.41","harrisca","PENDING","---","Receipt"];
    var trh = document.createElement('tr');

    for(let word of headerArr){
        var th = document.createElement("th");
        var thText = document.createTextNode(word);

        th.appendChild(thText);
        trh.appendChild(th);
    }

    table.appendChild(trh);

    for(let arr of myArr){
        var tr = document.createElement('tr');

        for(let word of myArr){
            var td = document.createElement("td");
            var tdText = document.createTextNode(word);

            td.appendChild(tdText);
            tr.appendChild(td);
        }
    
    table.appendChild(tr);
    }
    dataSection.appendChild(table);
}

*/



