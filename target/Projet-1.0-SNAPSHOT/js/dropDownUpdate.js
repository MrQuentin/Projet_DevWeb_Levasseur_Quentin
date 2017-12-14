function typeAddUpdater() {

    var categorieSelector = document.querySelector("#CategorySelector");
    //console.log(categorieSelector.value);
    var typeSelector = document.querySelector("#TypeSelector");
    typeSelector[0].selected='selected';
    for (var i = 0 ; i < typeSelector.length ; i++) {
        //console.log(typeSelector[i].value + "   " + typeSelector[i].value.indexOf(categorieSelector.value) != -1)
        if (typeSelector[i].value.indexOf(categorieSelector.value) != -1) {
            typeSelector[i].style.display="block";
        } else {
            typeSelector[i].style.display="none";
        }
    }
    typeSelector[0].style.display='block';
};
function sousversionDeleteUpdater() {
    var versionSelector = document.querySelector("#RmvElementTypeVersionSelector");
    var sousversionSelector = document.querySelector("#RmvElementTypeSVersionSelector");
    sousversionSelector[0].selected='selected';
    for (var i = 0 ; i < sousversionSelector.length ; i++) {
        //console.log(typeSelector[i].value + "   " + typeSelector[i].value.indexOf(categorieSelector.value) != -1)
        if (sousversionSelector[i].value.indexOf(versionSelector.value) != -1) {
            sousversionSelector[i].style.display="block";
        } else {
            sousversionSelector[i].style.display="none";
        }
    }
    sousversionSelector[0].style.display='block';
};

function typeDeleteUpdater() {
    var categorieSelector = document.querySelector("#RmvCategorySelectorr");
    console.log(categorieSelector.value);
    var typeSelector = document.querySelector("#RmvTypeSelectorr");
    typeSelector[0].selected='selected';
    document.querySelector("#RmvElementSelectorr")[0].selected='selected';
    itemDeleteUpdater();
    for (var i = 0 ; i < typeSelector.length ; i++) {
        console.log(typeSelector[i].value + "   " + typeSelector[i].value.indexOf(categorieSelector.value) != -1)
        if (typeSelector[i].value.indexOf(categorieSelector.value) != -1) {
            typeSelector[i].style.display="block";
        } else {
            typeSelector[i].style.display="none";
        }
    }
    typeSelector[0].style.display='block';
};

function itemDeleteUpdater() {
    var typeSelector = document.querySelector("#RmvTypeSelectorr");
    //console.log(categorieSelector.value);
    var itemSelector = document.querySelector("#RmvElementSelectorr");
    itemSelector[0].selected='selected';
    for (var i = 0 ; i < itemSelector.length ; i++) {
        if (itemSelector[i].value.indexOf(typeSelector.value) != -1) {
            itemSelector[i].style.display="block";
        } else {
            itemSelector[i].style.display="none";
        }
    }
    itemSelector[0].style.display='block';
}