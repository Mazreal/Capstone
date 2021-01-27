/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function initDate() {
    $('.dateselect').datepicker({
        format: 'mm/dd/yyyy'
        // startDate: '-3d'
    });

    // Close the dropdown menu if the user clicks outside of it
    var deleteLinks = document.querySelectorAll('.delete');
    for (var i = 0; i < deleteLinks.length; i++) {
        deleteLinks[i].addEventListener('click', function (event) {
            event.preventDefault();
            var choice = confirm(this.getAttribute('data-confirm'));
            if (choice) {
                window.location.href = this.getAttribute('href');
            }
        });
    }
}

//this is to disable and enable the end date checkbox
function EnableDisableTextBox(chkDate) {
    
    var deleteBtn = document.getElementById("delete");
    deleteBtn.disabled = chkDate.checked ? true : false;
    if (!deleteBtn.disabled) {
        deleteBtn.focus();
    }
}

function init() {
    initDate();
}

window.onload = init();