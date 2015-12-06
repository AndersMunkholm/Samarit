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
        
        
    }
        
        
}
    
    
    
    
    
    
