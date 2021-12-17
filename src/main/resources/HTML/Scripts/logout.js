var apiURL = "/logout";

function logout(){
    console.log(apiURL);
    fetch(apiURL)     
        .catch(err => console.log("Request Failed", err));
}