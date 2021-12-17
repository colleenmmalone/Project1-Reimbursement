

var apiURL = '/register.html/';

	document.getElementById('registerButton').onclick = getData; 

function getData() {  
    
    var firstName = document.getElementById('firstName').value; // my textboxes
    var lastName = document.getElementById('lastName').value;
    var email = document.getElementById('email').value;

    var month = document.getElementById('month').value;
    var day = document.getElementById('day').value;
    var year = document.getElementById('year').value;
    var birthday = year + '-' + month + '-' + day;

    var pswd1 = document.getElementById('pswd').value; 
    var pswd2 = document.getElementById('confpswd').value; 

    var dataSection = document.getElementById('infobar');

    if(pswd1 === pswd2){
        var xhttp = new XMLHttpRequest(); // STEP 1: Create an XML Http Request object

        xhttp.onreadystatechange = newUser; // STEP 2: Set a callback function for the readystatechange event

        var newLink = apiURL + firstName + '/' + lastName  + '/' + email  + '/' + pswd1  + '/' + birthday;
        xhttp.open('GET', newLink);  // STEP 3: Open the request     

        xhttp.send(); // STEP 4: Send the request

        function newUser() {

            dataSection.innerHTML = '';

            if (xhttp.readyState === 4) { //if done
                if (xhttp.status === 200) { // HTTP status code is "OK"
                    dataSection.innerHTML = 'You have been registered!';
                    window.location.href = 'ticketCenter.html'
                } else {
                    // Ready state is DONE but status code is not "OK"
                    dataSection.innerHTML = 'Something went wrong';

                }
            } else {
                // Ready state is not DONE
                dataSection.innerHTML = 'I\'m thinking....'; 
            } 
        }
    }else{
        dataSection.innerHTML = 'Passwords do not match. Please try again'; 
    }
}


