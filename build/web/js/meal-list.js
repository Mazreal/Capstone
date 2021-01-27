/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var removeEvent = document.getElementById("removeEvent");

$(removeEvent).click(function(){$(eventHolder).addClass('hide').stop();});

$('.collapse').on('show.bs.collapse', function () {
    $('.collapse.in').collapse('hide');
});