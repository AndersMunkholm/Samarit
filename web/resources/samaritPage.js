/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//window er der hvor alle objecter ligger
window.onload = function setUpSamaritTable() {
    //document er html documentet som javascriptet er indsat ind i
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
    
    
    document.getElementById("samaritTable").innerHTML = tableMeat; //targer tableMeat og indsætter det ind i table
    
    var parent = document.getElementById("tableDiv"); //finder div med et div1 id. Dette er et træ, hvor samaritObjects er inde i
    parent.removeChild(document.getElementById("samaritObjects")); //sletter barnet fra div1, som er samaritObjects
   
    console.log(tableMeat);
    console.log(i); // logger hvor mange gange for loopet er kørt
    window.alert("Success!! Tryk F12 for at være sikker");

}