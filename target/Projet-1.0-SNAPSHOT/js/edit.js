function updateSousVersion(){

    var sousversion = new Object();
    console.log("id = "+document.querySelector("#sousversionID").value)
    var id = document.querySelector("#sousversionID").value;
    sousversion.title = document.querySelector("#nomVersion").value;
    sousversion.description = document.querySelector("#descriptionVersion").value;
    sousversion.picture="";

    var requeteUpdateSousVersion = new XMLHttpRequest();
    requeteUpdateSousVersion.open("POST", "webservices/edit/sousversion");
    requeteUpdateSousVersion.responseType="json";
    requeteUpdateSousVersion.onload = function () {
        alert("You version is now Updated !");
        window.location = "version";
    }
    requeteUpdateSousVersion.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    requeteUpdateSousVersion.send("sousversion="+JSON.stringify(sousversion)+"&idSVersion="+id);
};

function testValidation(){
    console.log("test validation")
    if (document.querySelector("#nomVersion").value != null) {
        console.log("step 1 ok ! : "+ document.querySelector("#nomVersion").value)
        if (document.querySelector("#descriptionVersion").value != null) {
            console.log("step 2 ok ! : "+ document.querySelector("#descriptionVersion").value)
            updateSousVersion();
        } else {
            alert("Your description must not be empty !");
        }
    } else {
        alert ("Your name must not be empty !");
    }
}