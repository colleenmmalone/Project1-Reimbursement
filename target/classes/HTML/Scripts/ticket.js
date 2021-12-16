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
        getPending(response.emp);
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


function getPending(emp) {

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
}


function displayPending(responseP) {
	
    dataSection = document.getElementById("pendingTix");



    var headerArr = ["Ticket #","Submit Date","Purchase Date","Type","Amount","Employee","Status","Aprover ID","Receipt"];
    var myArr = ["19284","2021/11/30","2021/11/26","GAS","$50.41","harrisca","PENDING","---","Receipt"];

    var table = document.createElement('table');
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

        for(let word of arr){
  
            var td = document.createElement("td");
            var tdText = document.createTextNode(word);

            td.appendChild(tdText);
            tr.appendChild(td);
        }
    
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



