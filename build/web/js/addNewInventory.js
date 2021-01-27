/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var counter = 1;
var limit = 999999;
function addInput() {



    var table = document.getElementById('ingredientTest');

    var rowCount = table.rows.length;
    var row = table.insertRow(-1);

    var cell1 = row.insertCell(0);
    var ingredientName = document.createElement("text");
    /*  meal.type= "text"; 
     meal.class="form-control";
     meal.name ="mealList[]";  */
    ingredientName.innerHTML = "<input type='text' class='form-control' name='ingredientName[]'>";
    cell1.appendChild(ingredientName);


    var cell2 = row.insertCell(1);
    var quantity = document.createElement("number");
    /*  meal.type= "text"; 
     meal.class="form-control";
     meal.name ="mealList[]";  */
    quantity.innerHTML = "<input type='number' class='form-control' name='quantity[]'>";
    cell2.appendChild(quantity);

    var cell3 = row.insertCell(2);
    var dateProduced = document.createElement("date");
    /*  meal.type= "text"; 
     meal.class="form-control";
     meal.name ="mealList[]";  */
    dateProduced.innerHTML = "<input type='date' class='form-control' name='dateProduced[]'>";
    cell3.appendChild(dateProduced);


}

function deleteIngredient() {
    var table =  document.getElementById('ingredientTest'); 
  var row = table.getElementsByTagName('tr');
      if (row.length != '1') {
          row[row.length - 1].outerHTML = '';
      }
}

function deleteMeal() {
    var table =  document.getElementById('mealTest'); 
  var row = table.getElementsByTagName('tr');
      if (row.length != '1') {
          row[row.length - 1].outerHTML = '';
      };
}

function addMealInput() {
var table = document.getElementById('mealTest');

    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);

    var cell1 = row.insertCell(0);
    var meal = document.createElement("text");
    /*  meal.type= "text"; 
     meal.class="form-control";
     meal.name ="mealList[]";  */
    meal.innerHTML = "<input type='text' class='form-control' name='mealName[]'>";
    cell1.appendChild(meal);


    var cell2 = row.insertCell(1);
    var mealQuantity = document.createElement("number");
    /*  meal.type= "text"; 
     meal.class="form-control";
     meal.name ="mealList[]";  */
    mealQuantity.innerHTML = "<input type='number' class='form-control' name='mealQuantity[]'>";
    cell2.appendChild(mealQuantity);

    var cell3 = row.insertCell(2);
    var mealDate = document.createElement("date");
    /*  meal.type= "text"; 
     meal.class="form-control";
     meal.name ="mealList[]";  */
    mealDate.innerHTML = "<input type='date' class='form-control' name='mealDate[]'>";
    cell3.appendChild(mealDate);

}