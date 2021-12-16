document.getElementById('generate').onclick = writeTable;

function writeTable(){
    dataSection = document.getElementById("restData");
    var table = document.createElement('table');
    var headerArr = ["red","green","yellow"];
    var myArr = [["apple","pear","banana"],["cherry","lime","lemon"],["strawberry","avocado","mango"]];
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