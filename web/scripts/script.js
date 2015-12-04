/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$( document ).ready(function() {
    
    function drop_Oversigt() {
        $('#navigation').dropit();
    };
    function drop_UserMenu() {
        $('#user_options').dropit();
    };
    
    
    
    drop_Oversigt();
    drop_UserMenu();
});

