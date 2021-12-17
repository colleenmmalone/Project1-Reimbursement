var apiURL = "/logout";

function logout(){
    console.log(apiURL);
    fetch(apiURL)

        .then(json => {
            window.location.href = "index.html"
        })
        .catch(err => console.log("Request Failed", err));
}

function isLoggedOut(json){
    console.log(json);
    if(json.email === 'null'){
        
        console.log("it is null");
    } else{
        console.log("not null");
    }
}