document.getElementById('editPersonal').onclick = displayPersonal; 
document.getElementById('editPassword').onclick = editPassword;

var emp;
var empPass;


function getUser() {
    var apiURL = "/whoisloggedin";

    fetch(apiURL)
        .then(response => response.json())
        .then(json => displayName(json))
        .catch(err => console.log("Request Failed", err));

    function displayName(response){
        emp = response;
        empPass = response.pswd;
        console.log(emp);
        console.log(response);

        var intro = document.getElementById("intro");
        intro.innerHTML = `Hello, ${emp.first_name}!`
        var contact = document.createElement("p");
        var nm = document.createElement("p");
        var bday = document.createElement("p");

        contact.innerHTML = `Contact&emsp;&ensp;&ensp;${emp.email}`;
        document.getElementById("email").appendChild(contact);

        nm.innerHTML = `Name&emsp;&emsp;&emsp;${emp.first_name} ${emp.last_name}`;
        document.getElementById("personal").appendChild(nm);

        bday.innerHTML = `Birthday&emsp;&emsp;${emp.birthday}`;
        document.getElementById("personal").appendChild(bday);
    }
}

function displayPersonal(){
    var infobar = document.getElementById("personalInfo");
    infobar.innerHTML = "";
    var display = document.getElementById("personalUpdate");
    display.innerHTML = "<input type='text' placeholder='First Name' id='firstName'><br><input type='text' placeholder='Last Name' id='lastName'><br>";
    var bday = document.createElement("div");
    bday.innerHTML = "Birthday: <select id='month'><option selected value='---'>---</option><option value='01'>January</option><option value='02'>February</option><option value='03'>March</option><option value='04'>April</option><option value='05'>May</option><option value='06'>June</option><option value='07'>July</option><option value='08'>August</option><option value='09'>September</option> <option value='10'>October</option> <option value='11'>November</option><option value='12'>December</option></select>"
            +" <select id='day'><option selected value='---'>---</option><option value='01'>01</option><option value='02'>02</option><option value='03'>03</option><option value='04'>04</option><option value='05'>05</option><option value='06'>06</option><option value='07'>07</option><option value='08'>08</option><option value='09'>09</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option>"
            +"<option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select> <input id='year' type='number' min=1900 max=2025 step=1 /><br>";
    display.appendChild(bday);
    var butt = document.createElement("div");
    butt.innerHTML = "<button class='edit' id='changeName'>Update</button>";
    display.appendChild(butt);
    document.getElementById('changeName').onclick = editPersonal;
}

function editPassword(){
    var infobar =document.getElementById("emailResponse")
    infobar.innerHTML = "";
    var oldP = document.getElementById("oldP").value;
    var newP = document.getElementById("newP").value;
    var confP = document.getElementById("confP").value;

    if(oldP === '' || newP === '' || confP === ''){
        infobar.append("Please fill all values");
    }else{
        if(oldP === empPass){
            if(newP === confP){
                var apiURL = "http://localhost:7001/settings/password/" + emp.email +"/" + newP;
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
            }else{
                infobar.append("The new passwords do not match");
    
            }
        }else{
            infobar.append("Please enter your current password correctly");
        }
    }

}

function editPersonal(){
    var changes = false;
    var newFN = document.getElementById("firstName").value;
    var newLN = document.getElementById("lastName").value;
    var newM = document.getElementById("month").value;
    var newD = document.getElementById("day").value;
    var newY = document.getElementById("year").value;
    var newB;

    if(newD === '---' || newM === '---' || newY === 0){
        newB = emp.birthday;
        console.log("no new birthday");
    }else{
        newB = newY+"-"+newM+"-"+newD;
        changes = true;
        console.log(newB);
    }

    if(newFN ===''){
        newFN = emp.first_name;
        console.log("no new FN");
    }else{       
        changes = true;
        console.log(newFN);
    }

    if(newLN ===''){
        newLN = emp.last_name;
        console.log("no new LN");
    }else{       
        changes = true;
        console.log(newLN);
    }

    if(changes === true){
        console.log("do the stuff");

        var apiURL = "http://localhost:7001/settings/personal/" + emp.email +"/" + newFN + "/"+ newLN + "/" + newB;
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
    }else{
        var infobar = document.getElementById("personalInfo");
        infobar.innerHTML = "No changes were made";
    }
}
