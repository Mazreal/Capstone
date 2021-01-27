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

function myFunction() {
    confirm("Are you sure you want to delete this report?");
}
function myFunction1() {
    document.getElementById("myDropdown").classList.toggle("show");
}


/*
 // Get the elements
 var from_input = $('#startingDate').pickadate(),
 from_picker = from_input.pickadate('picker')
 var to_input = $('#endingDate').pickadate(),
 to_picker = to_input.pickadate('picker')
 
 // Check if there’s a “from” or “to” date to start with and if so, set their appropriate properties.
 if (from_picker.get('value')) {
 to_picker.set('min', from_picker.get('select'))
 }
 if (to_picker.get('value')) {
 from_picker.set('max', to_picker.get('select'))
 }
 
 // Apply event listeners in case of setting new “from” / “to” limits to have them update on the other end. If ‘clear’ button is pressed, reset the value.
 from_picker.on('set', function (event) {
 if (event.select) {
 to_picker.set('min', from_picker.get('select'))
 } else if ('clear' in event) {
 to_picker.set('min', false)
 }
 });
 
 to_picker.on('set', function (event) {
 if (event.select) {
 from_picker.set('max', to_picker.get('select'))
 } else if ('clear' in event) {
 from_picker.set('max', false)
 }
 });*/

function display(){
    
}

function search(){
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
        display();
     //document.getElementById("demo").innerHTML = this.responseText;
    }
  };
  xhttp.open("POST", "", true);
  xhttp.send();
}

function EnableDisableTextBox(chkPassport) {
    
    var deleteBtn = document.getElementById("delete");
    deleteBtn.disabled = chkPassport.checked ? true : false;
    if (!deleteBtn.disabled) {
        deleteBtn.focus();
    }
}

function init() {
    initDate();
}

window.onload = init();