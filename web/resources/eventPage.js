/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


window.onload = function() {
    var pParameter = document.getElementById("eventObjects").innerHTML;
    var JSONFrames = JSON.parse(pParameter);
    console.log(JSONFrames); //tjekker Om JSON ligner det det skal
    
    var tableMeat = "<tr>" + 
            "<td>Name</td>" + 
            "<td>StartDate</td>" +
            "<td>EndDate</td>" + 
            "<td>EventFrames</td>" + 
            "</tr>";
    for (var i = 0; i < JSONFrames.length; i++) {
        tableMeat += "<tr>" + 
            "<td>" + JSONFrames[i].Name + "</td>" + 
            "<td>"+ JSONFrames[i].StartDate  + "</td>" +
            "<td>" + JSONFrames[i].EndDate + "</td>" + 
            "<td>";
            for (var k = 0; k < JSONFrames[i].EventFrames.length; k++) {
                tableMeat += "<table>" + 
                    "<tr>" +
                    "<td>" +
                    "StartDate" + 
                    "</td>" +
                    "<td>" +
                    "StartTime" + 
                    "</td>" +
                     "<td>" +
                    "endTime" + 
                    "</td>" +
                    "</tr>" +
                    "<tr>" +
                    "<td>" +
                    JSONFrames[i].EventFrames[k].StartDate + 
                    "</td>" +
                    "<td>" +
                    JSONFrames[i].EventFrames[k].StartTime + 
                    "</td>" +
                     "<td>" +
                    JSONFrames[i].EventFrames[k].EndTime + 
                    "</td>" +
                    "</tr>" +
                    "</table>";
                }
            tableMeat += "</td>" + 
            "</tr>";
        
    }
    
    
    document.getElementById("eventFrameTable").innerHTML = tableMeat;
    var parent = document.getElementById("tableDiv"); //finder div med et div1 id. Dette er et træ, hvor samaritObjects er inde i
    parent.removeChild(document.getElementById("eventObjects")); //sletter barnet fra div1, som er samaritObjects
    console.log(i);
        
}
    
    
    
    
    
    
