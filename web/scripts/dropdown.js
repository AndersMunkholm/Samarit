/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function getDropdownContent() {
    
    document.getElementById("myDropdown").classList.toggle("show");
}



window.onclick = function(event) { //sker hver gang der bliver clicket på et event, Det event kommer ind i functionen
  if (!event.target.matches('.dropbtn')) { //tjekker om det event der skete er sket på dropbtn id'et

    var dropdowns = document.getElementsByClassName("dropdown");
    var i;
    for (i = 0; i < dropdowns.length; i++) {
      var openDropdown = dropdowns[i];
      if (openDropdown.classList.contains('show')) {
        openDropdown.classList.remove('show');
      }
    }
  }
}



