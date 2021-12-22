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
                document.getElementById("newTix").remove();
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
        .then(json => displayPending(json))
        .catch(err => console.log("Request Failed", err));
}

function getCompleted(email) {
    var apiURL = "http://localhost:7001/completed/" + email;
    console.log(apiURL);
    fetch(apiURL)
        .then(responseC => responseC.json())
        .then(json => displayCompleted(json))
        .catch(err => console.log("Request Failed", err));
}

function getAllPending() {
    var apiURL = "http://localhost:7001/admin/pending/";
    console.log(apiURL);
    fetch(apiURL)
        .then(response => response.json())
        .then(json => displayPending(json))
        .catch(err => console.log("Request Failed", err));
}

function getAllCompleted() {
    var apiURL = "http://localhost:7001/admin/completed/";
    console.log(apiURL);
    fetch(apiURL)
        .then(response => response.json())
        .then(json => displayCompleted(json))
        .catch(err => console.log("Request Failed", err));
}

function displayPending(responseP, email) {
    dataSection = document.getElementById("pendingTix");

    var headerArr = ["","Ticket #","Submit Date","Purchase Date","Type","Amount","Employee"];
    
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

        var td = document.createElement("td");
        var c = document.createElement("input");
        c.setAttribute("type", "checkbox");
        c.setAttribute("id", responseP[i].tixNum);
        td.appendChild(c);       
        tr.appendChild(td);

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
        var td6Text = document.createTextNode(responseP[i].emp);
        td6.appendChild(td6Text);
        tr.appendChild(td6);

        table.appendChild(tr);
    }
    dataSection.appendChild(table);

    if (emp.id === 'ADMIN'){
        var approve = document.createElement("button");       
        approve.setAttribute("id", "approveB");
        approve.innerText = "Approve";    
        dataSection.appendChild(approve);
        document.getElementById("approveB").onclick = approveF;
        
        var deny = document.createElement("button");
        deny.innerHTML = "Deny";
        deny.setAttribute("id", "denyB");
        dataSection.appendChild(deny);
        document.getElementById("denyB").onclick = denyF;

    }else{
        var del = document.createElement("button");
        del.setAttribute("id", "delB");
        del.innerHTML = "Delete";
        dataSection.appendChild(del);
        document.getElementById("delB").onclick = delF;
    }
}

function displayCompleted(response) {
    dataSection = document.getElementById("completedTix");

    var headerArr = ["Ticket #","Submit Date","Purchase Date","Type","Amount","Employee","Status","Approver ID"];

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
        var td6Text = document.createTextNode(response[i].emp);
        td6.appendChild(td6Text);
        tr.appendChild(td6);

        var td6a = document.createElement("td");
        var td6aText = document.createTextNode(response[i].status);
        td6a.appendChild(td6aText);
        tr.appendChild(td6a);

        var td7 = document.createElement("td");
        var td7Text = document.createTextNode(response[i].approver);
        td7.appendChild(td7Text);
        tr.appendChild(td7);

        table.appendChild(tr);
    }
    dataSection.appendChild(table);
}

function approveF(){
    if(confirm("Confirm approve?")){
        const checkboxes = document.querySelectorAll('input[type="checkbox"]:checked');
        let tix = [];
        checkboxes.forEach((checkbox) => {
            tix.push(checkbox.id);
        });
    
        for(i=0 ; i< tix.length ; i++){
            var apiURL = "http://localhost:7001/approve/" + emp.email +"/" + tix[i];
            console.log(apiURL);
            fetch(apiURL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(),
            })
            .then(function(response) {
                if(response.ok) {
                    location.reload();
            }})
            .catch(err => console.log("Request Failed", err));
        }
    }else{
        console.log("process canceled");
    }
}

function denyF(){
    if(confirm("Confirm deny?")){
        const checkboxes = document.querySelectorAll('input[type="checkbox"]:checked');
        let tix = [];
        checkboxes.forEach((checkbox) => {
            tix.push(checkbox.id);
        });
    
        for(i=0 ; i< tix.length ; i++){
            var apiURL = "http://localhost:7001/deny/" + emp.email +"/" + tix[i];
            console.log(apiURL);
            fetch(apiURL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(),
            })
            .then(function(response) {
                if(response.ok) {
                    location.reload();
            }})
            .catch(err => console.log("Request Failed", err));
        }
    }else{
        console.log("process canceled");
    }
}

function delF(){
    if(confirm("Confirm delete?")){
        const checkboxes = document.querySelectorAll('input[type="checkbox"]:checked');
        let tix = [];
        checkboxes.forEach((checkbox) => {
            tix.push(checkbox.id);
        });
    
        for(i=0 ; i< tix.length ; i++){
            var apiURL = "http://localhost:7001/delete/" + emp.email +"/" + tix[i];
            console.log(apiURL);
            fetch(apiURL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(),
            })
            .then(function(response) {
                if(response.ok) {
                    location.reload();
            }})
            .catch(err => console.log("Request Failed", err));
        }
    }else{
        console.log("process canceled");
    }
}

function getChecked(){
    const checkboxes = document.querySelectorAll('input[type="checkbox"]:checked');
    let tix = [];
    checkboxes.forEach((checkbox) => {
        tix.push(checkbox.id);
    });

}

