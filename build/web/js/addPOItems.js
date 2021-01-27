/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var counter = 1;
var limit = 999999;
function addInput() {
   
       
        
        var table = document.getElementById('testTable'); 
        
        var rowCount = table.rows.length; 
        var row = table.insertRow(-1); 
        
        var cell1 = row.insertCell(0); 
        var meal = document.createElement("text"); 
      /*  meal.type= "text"; 
        meal.class="form-control";
        meal.name ="mealList[]";  */ 
        meal.innerHTML = "<input type='text' class='form-control' name='mealItems[]'>"; 
        cell1.appendChild(meal);
        
        var cell2 = row.insertCell(1); 
        var quantity = document.createElement("number"); 
        /*quantity.type= "text"; 
        quantity.class="form-control";
        quantity.name ="quantity[]"; */ 
        quantity.innerHTML = "<input type='number' class='form-control' name='quantity[]'>";
        cell2.appendChild(quantity);
    

}

function deleteMealItems() {
  var table =  document.getElementById('testTable'); 
  var row = table.getElementsByTagName('tr');
      if (row.length != '1') {
          row[row.length - 1].outerHTML = '';
      }
}

