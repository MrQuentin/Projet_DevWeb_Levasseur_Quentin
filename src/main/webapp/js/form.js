function Action1() {
    var fieldAjout = document.getElementById("Ajout");
    var fieldSuprimer = document.getElementById("Suprimer");
    fieldAjout.style.display = 'block';
    fieldSuprimer.style.display = 'none';
};
function Action2() {
    var fieldAjout = document.getElementById("Ajout");
    var fieldSuprimer = document.getElementById("Suprimer");
    fieldSuprimer.style.display = 'block';
    fieldAjout.style.display = 'none';
};
function  selectVersion() {
    var fieldVersion = document.getElementById("AjoutVersion");
    var fieldBlock = document.getElementById("AjoutBlock");
    fieldVersion.style.display = 'block';
    fieldBlock.style.display = 'none';
};
function  selectBlocks() {
    var fieldVersion = document.getElementById("AjoutVersion");
    var fieldBlock = document.getElementById("AjoutBlock");
    fieldVersion.style.display = 'none';
    fieldBlock.style.display = 'block';
};
function isSousVersion() {
    var selectField = document.getElementById("TypeVersionSelector");
    if (selectField.value == "SousVersion") {
        document.getElementById("SousVersion").style.display = 'block';
        document.getElementById("Versiontitre").style.display = 'none';
        document.getElementById("Versiontext").style.display = 'none';
        document.getElementById("Versionillustration").style.display = 'none';
    } else if (selectField.value == "Version") {
        document.getElementById("SousVersion").style.display = 'none';
        document.getElementById("Versiontitre").style.display = 'block';
        document.getElementById("Versiontext").style.display = 'block';
        document.getElementById("Versionillustration").style.display = 'block';
    } else {
        document.getElementById("SousVersion").style.display = 'none';
        document.getElementById("Versiontitre").style.display = 'none';
        document.getElementById("Versiontext").style.display = 'none';
        document.getElementById("Versionillustration").style.display = 'none';
    }
};
function isVersionFromSousVersionSelected(){
    var selector = document.querySelector("#TypeVersionForSVSelector").value;
    if (selector != "..."){
        document.getElementById("Versiontitre").style.display = 'block';
        document.getElementById("Versiontext").style.display = 'block';
        document.getElementById("Versionillustration").style.display = 'block';
    } else {
        document.getElementById("Versiontitre").style.display = 'none';
        document.getElementById("Versiontext").style.display = 'none';
        document.getElementById("Versionillustration").style.display = 'none';
    }
};
function displaySelectionAdd() {
    var selectField = document.getElementById("SelecttorAjoutB");
    if (selectField.value == "Categorie"){
        document.getElementById("Categorie").style.display = "none";
        document.getElementById("Type").style.display = "none";
        document.getElementById("titre").style.display = "block";
        document.getElementById("text").style.display = "none";
        document.getElementById("illustration").style.display = "block";
    } else if (selectField.value == "Type") {
        document.getElementById("Categorie").style.display = "block";
        document.getElementById("Type").style.display = "none";
        document.getElementById("titre").style.display = "none";
        document.getElementById("text").style.display = "none";
        document.getElementById("illustration").style.display = "block";
    } else if (selectField.value == "Item") {
        document.getElementById("Categorie").style.display = "block";
        document.getElementById("Type").style.display = "block";
        document.getElementById("titre").style.display = "block";
        document.getElementById("text").style.display = "block";
        document.getElementById("illustration").style.display = "block";
    } else {
        document.getElementById("Categorie").style.display = "none";
        document.getElementById("Type").style.display = "none";
        document.getElementById("titre").style.display = "none";
        document.getElementById("text").style.display = "none";
        document.getElementById("illustration").style.display = "none";
    }
};
function displaySelectionDel() {
    var selectField = document.getElementById("SelecttorDeleteB");
    if (selectField.value == "Categorie"){
        document.getElementById("RmvCategorySelector").style.display = "block";
        document.getElementById("RmvTypeSelector").style.display = "none";
        document.getElementById("RmvElementSelector").style.display = "none";
    } else if (selectField.value == "Type") {
        document.getElementById("RmvCategorySelector").style.display = "block";
        document.getElementById("RmvTypeSelector").style.display = "block";
        document.getElementById("RmvElementSelector").style.display = "none";
    } else if (selectField.value == "Item") {
        document.getElementById("RmvCategorySelector").style.display = "block";
        document.getElementById("RmvTypeSelector").style.display = "block";
        document.getElementById("RmvElementSelector").style.display = "block";
    } else {
        document.getElementById("RmvCategorySelector").style.display = "none";
        document.getElementById("RmvTypeSelector").style.display = "none";
        document.getElementById("RmvElementSelector").style.display = "none";
    }
};
function selectSupprimerVersion() {
    var fieldVersion = document.getElementById("DeleteVersion");
    var fieldBlock = document.getElementById("DeleteCategorie");
    fieldVersion.style.display = 'block';
    fieldBlock.style.display = 'none';
};

function selectSupprimerCategorie() {
    var fieldVersion = document.getElementById("DeleteVersion");
    var fieldBlock = document.getElementById("DeleteCategorie");
    fieldVersion.style.display = 'none';
    fieldBlock.style.display = 'block';
}


function detectFormDemand() {
    var radioAction = document.querySelectorAll("div#Action span.radio input");
    if(radioAction[0].checked){//Add
        var radioPage = document.querySelectorAll("div#Page span.checkbox input");
        if (radioPage[0].checked) {//Version
            var vtype = document.querySelector("#TypeVersionSelector");
            if (vtype.value == "Version") {//Version
                if (document.querySelector("#title").value != null) {
                    addVersion();
                } else {
                    alert("You can not create a version without title");
                }
            } else if (vtype.value == "SousVersion") {//Sous version
                if (document.querySelector("#TypeVersionForSVSelector").value != "...") {
                    if (document.querySelector("#title").value != null) {
                        var idversion = document.querySelector("#TypeVersionForSVSelector").value
                        addSousVersion(idversion.substring(7));
                    } else {
                        alert()
                    }
                } else {//Nothing
                    alert("You must choose to add a version or sous-Version !");
                }
            }
        } else if (radioPage[1].checked) {//Block
            var btype = document.querySelector("#SelecttorAjoutB");
                if (btype.value == "Categorie") {//categorie
                    addCategorie();
                } else if (btype.value == "Type") {//type
                    var idcat = document.querySelector("#CategorySelector").value;
                    addType(idcat.substring(9));
                } else if (btype.value == "Item") {//item
                    var iditem = document.querySelector("#TypeSelector").value;
                    addItem(iditem.substring(iditem.indexOf("type")+4));
                } else {//nothing
                    alert("You must choose to add a categorie, a type or an item");
                }
        } else {
            alert("You must select to add a block or a version !")
        }
    }else{//Delete
        var radioPage = document.querySelectorAll("div#RmvPage input");
        if (radioPage[0].checked){//Version
            var vtype = document.querySelector("#TypeVersionSelectorRmv");
            if (vtype.value == "Version"){//Version
                var vid = document.querySelector("#RmvElementTypeVersionSelector").value;
                deleteVersion(vid.substring(7));
            }else if(vtype.value == "SousVersion"){//Sous version
                var svid = document.querySelector("#RmvElementTypeSVersionSelector").value;
                deleteSousVersion(svid.substring(svid.indexOf("sv")+2))
            }else {//Nothing
                alert("You must choose to Delete a version or sous-Version !");
            }
        }else if (radioPage[1].checked){//Block
            var btype = document.querySelector("#SelecttorDeleteB");
            if (btype.value == "Categorie") {//categorie
                var cid = document.querySelector("#RmvCategorySelectorr").value;
                deleteCategorie(cid.substring(9));
            } else if (btype.value == "Type"){//type
                var tid = document.querySelector("#RmvTypeSelectorr").value
                deleteType(tid.substring(tid.indexOf("type")+4));
            } else if (btype.value == "Item"){//item
                var iid = document.querySelector("#RmvElementSelectorr").value;
                deleteItem(iid.substring(iid.indexOf("item")+4));
            } else {//nothing
                alert("You must choose to Delete a categorie, a type or an item");
            }
        }else{
            alert("You must select to Delete a block or a version !")
        }

    }
    return false;
};

function addVersion() {
    var version = new Object();
    var title = document.querySelector("#title").value;
    version.id = -1;
    version.name = title;
    version.sousVersions=null;
    version.illustration_name="";

    var requeteAddVersion = new XMLHttpRequest();
    requeteAddVersion.open("POST", "webservices/form/add/version");
    requeteAddVersion.responseType="json";
    requeteAddVersion.onload = function () {
        warnRedirect();
    }
    requeteAddVersion.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    requeteAddVersion.send("version="+JSON.stringify(version));
};
function addSousVersion(idversion) {
    var sousversion = new Object();
    var title = document.querySelector("#title").value;
    var description = document.querySelector("#description").value;
    sousversion.id=-1;
    sousversion.title=title;
    sousversion.description=description;
    sousversion.picture="";

    var requeteAddSousVersion = new XMLHttpRequest();
    requeteAddSousVersion.open("POST", "webservices/form/add/sousversion");
    requeteAddSousVersion.responseType="json";
    requeteAddSousVersion.onload = function () {
        warnRedirect();
    }
    requeteAddSousVersion.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    requeteAddSousVersion.send("sousversion="+JSON.stringify(sousversion)+"&idversion="+idversion);

};
function addCategorie() {
    var categorie = new Object();
    var title = document.querySelector("#titleb").value;
    categorie.id=-1;
    categorie.name=title;
    categorie.illustration_name="";
    categorie.typeMap=null;
    var requeteAddCategorie = new XMLHttpRequest();
    requeteAddCategorie.open("POST", "webservices/form/add/categorie");
    requeteAddCategorie.responseType="json";
    requeteAddCategorie.onload = function () {
        warnRedirect();
    }
    requeteAddCategorie.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    requeteAddCategorie.send("categorie="+JSON.stringify(categorie));
};
function addType(idCategorie) {
    var type = new Object();
    var title = document.querySelector("#titleb").value;
    type.id=-1;
    type.name=title;
    type.illustration="";
    type.itemsList=null;

    var requeteAddType = new XMLHttpRequest();
    requeteAddType.open("POST", "webservices/form/add/type");
    requeteAddType.responseType="json";
    requeteAddType.onload = function () {
        warnRedirect();
    }
    requeteAddType.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    requeteAddType.send("type="+JSON.stringify(type)+"&idcategorie="+idCategorie);
};
function addItem(idType) {
    var item = new Object();
    var title = document.querySelector("#titleb").value;
    var description = document.querySelector("#descriptionb").value;
    item.id=-1;
    item.name=title;
    item.description=description;
    item.illutration="";

    var requeteAddItem = new XMLHttpRequest();
    requeteAddItem.open("POST", "webservices/form/add/item");
    requeteAddItem.responseType="json";
    requeteAddItem.onload = function () {
        warnRedirect();
    }
    requeteAddItem.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    requeteAddItem.send("item="+JSON.stringify(item)+"&idtype="+idType);

};
function deleteVersion(idVersion) {
    var requeteDeleteVersion = new XMLHttpRequest();
    requeteDeleteVersion.open("DELETE", "webservices/form/delete/version/"+idVersion,true);
    requeteDeleteVersion.responseType="text";
    requeteDeleteVersion.onload = function () {
        warnRedirectDel();
    }
    requeteDeleteVersion.send();
}

function deleteSousVersion(idSousVersion) {
    var requeteDeleteSousVersion = new XMLHttpRequest();
    requeteDeleteSousVersion.open("DELETE", "webservices/form/delete/sousversion/"+idSousVersion,true);
    requeteDeleteSousVersion.responseType="text";
    requeteDeleteSousVersion.onload = function () {
        warnRedirectDel();
    }
    requeteDeleteSousVersion.send();
};
function deleteCategorie(idCategorie) {
    var requeteDeleteCategorie = new XMLHttpRequest();
    requeteDeleteCategorie.open("DELETE", "webservices/form/delete/categorie/"+idCategorie,true);
    requeteDeleteCategorie.responseType="text";
    requeteDeleteCategorie.onload = function () {
        warnRedirectDel();
    }
    requeteDeleteCategorie.send();
};

function deleteType(idType) {
    var requeteDeleteType = new XMLHttpRequest();
    requeteDeleteType.open("DELETE", "webservices/form/delete/type/"+idType,true);
    requeteDeleteType.responseType="text";
    requeteDeleteType.onload = function () {
        warnRedirectDel();
    }
    requeteDeleteType.send();
};

function deleteItem(idItem) {
    var requeteDeleteItem = new XMLHttpRequest();
    requeteDeleteItem.open("DELETE", "webservices/form/delete/item/"+idItem,true);
    requeteDeleteItem.responseType="text";
    requeteDeleteItem.onload = function () {
        warnRedirectDel();
    }
    requeteDeleteItem.send();
};

function warnRedirect() {
    alert("Your element have been added successfully !");
    window.location = "form";
};
function warnRedirectDel(){
    alert("Your element have been deleted successfully !")
    window.location = "form";
}

