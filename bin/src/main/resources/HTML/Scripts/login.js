var apiURL = '/login/'; // Endpoint you are sending a GET request to

document.getElementById('login').onclick = getData;

function getData() {  
    var Uemail = document.getElementById('email').value;
    var Upswd = document.getElementById('pswd').value; 

    var xhttp = new XMLHttpRequest(); // STEP 1: Create an XML Http Request object

    xhttp.onreadystatechange = receiveData; // STEP 2: Set a callback function for the readystatechange event

    var newLink = apiURL + '' + Uemail+'/'+Upswd;
    xhttp.open('GET', newLink); // STEP 3: Open the request 

    xhttp.send();// STEP 4: Send the request

    // This needs to be an inner function so that it has closure to xhttp.
    function receiveData() {

        var dataSection = document.getElementById('infobar'); // my div
        dataSection.innerHTML = ''; //clear contents of div

         if (xhttp.readyState === 4) { //if done
            if (xhttp.status === 200) { // HTTP status code is "OK"
            	var response = xhttp.responseText;
                response = JSON.parse(response); //parse a JSON object to a "readable" output
               	displayData(response); //calls displayData function
            } else {
                // Ready state is DONE but status code is not "OK"
                dataSection.innerHTML = 'Error making connection to server';
            }
        } else {
            // Ready state is not DONE
            dataSection.innerHTML = 'I\'m thinking....'; 
        } 
    }
}

function displayData(response) {
	if(response.first_name === "null"){
		dataSection.innerHTML = 'Invalid login. Please try again';
	}else{
		window.location.href = 'ticketCenter.html';
	}
}


