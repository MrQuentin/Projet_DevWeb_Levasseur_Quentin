 function toggle_button(x) {
    var y = x.getAttribute('id') + '-son';
    var elmt = document.getElementById(y)
    if (elmt.style.display === 'none') {
        elmt.style.display = 'block';
    } else {
        elmt.style.display = 'none';
    }
}
