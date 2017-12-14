function connectDisplay () {
  var elmt = document.getElementById("myBody");
  var x = elmnt.scrollTop;
  if (x == 0) {
    document.getElementById("ConnectBar").style.display='block';
  } else {
    document.getElementById("ConnectBar").style.display='none';
  }
}
