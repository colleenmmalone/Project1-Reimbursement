document.getElementById('generate').onclick = writeTable;

function writeTable(){
    dataSection = document.getElementById("restData");
    var table = document.createElement('table');
    var headerArr = ["red","green","yellow"];
    var myArr = [{"red":"apple","green":"pear","yellow":"banana"},{"red":"cherry","green":"lime","yellow":"lemon"},{"red":"strawberry","green":"avocado","yellow":"mango"}];
    var trh = document.createElement('tr');

    var val = document.createElement('p');
    val.innerHTML = myArr[1].red;
    dataSection.appendChild(val);

    
    for(let word of headerArr){
        var th = document.createElement("th");
        var thText = document.createTextNode(word);

        th.appendChild(thText);
        trh.appendChild(th);
    }

    table.appendChild(trh);

    //for(let arr of myArr){
        for(i = 0; i<myArr.length;i++){
        var tr = document.createElement('tr');


            var tdR = document.createElement("td");
            var tdRText = document.createTextNode(myArr[i].red);
            tdR.appendChild(tdRText);
            tr.appendChild(tdR);

            var tdG = document.createElement("td");
            var tdGText = document.createTextNode(myArr[i].green);
            tdG.appendChild(tdGText);
            tr.appendChild(tdG);

            var tdY = document.createElement("td");
            var tdYText = document.createTextNode(myArr[i].yellow);
            tdY.appendChild(tdYText);
            tr.appendChild(tdY);

            
        
    
    table.appendChild(tr);
    }
    dataSection.appendChild(table);
    
}