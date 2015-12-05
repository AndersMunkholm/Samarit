/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


window.onload = function setUpSamaritTable() {
    var pParameterJSON = document.getElementById("samaritObjects").innerHTML; //tager alt indholdet af p og laver det til en Streng
    
    var samaritJSON = JSON.parse(pParameterJSON); //laver Strengen til json
    console.log(samaritJSON); //for at se hvilke objecter der er i pushed op, tryk F12 for at se det 
    
    var tableMeat = "";
    for (var i = 0; i < samaritJSON.length; i++) {  //går igennem alle objecter, Et object består af FirstName, LastName og mail
        tableMeat += "<tr>" + 
                "<td>" + samaritJSON[i].FirstName + "</td>"+ 
                "<td>" + samaritJSON[i].LastName + "</td>" + 
                "<td>" + samaritJSON[i].mail + "</td>"
                + "</tr>";     //ligger hvor object ind i en tabel
       
        
    }
    
    document.getElementById("samaritTable").innerHTML = tableMeat;
    
    document.removeChild(document.getElementById("samaritObjects"));
    
    console.log(tableMeat);
    console.log(i);
}