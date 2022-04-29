
window.onload = function () {

    var a = document.querySelector('#fish');
    
    a.onmouseout = function(e) {
        style="cursor: pointer;"
        document.getElementById('fish').style.fontSize = "8pt";
        document.getElementById('fish').style.color = "black";
    }
  
    a.onmouseover = function(e) {
        style="cursor: pointer;"
      document.getElementById('fish').style.fontSize = "16pt";
      document.getElementById('fish').style.color = "red";
    };
  }