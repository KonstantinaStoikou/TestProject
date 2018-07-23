//code to toggle forms 

var xp = document.getElementById("xp");

xp.addEventListener('click', function(){
    // document.getElementById("add_xp").style.display = "block";
    document.getElementById("add_xp").classList.toggle("hidden");
    document.querySelector("#xp > i").classList.toggle("fa-plus-circle");
    document.querySelector("#xp > i").classList.toggle("fa-minus-circle");
});


var xp = document.getElementById("ed");

xp.addEventListener('click', function(){
    document.getElementById("add_ed").classList.toggle("hidden");
    document.querySelector("#ed > i").classList.toggle("fa-plus-circle");
    document.querySelector("#ed > i").classList.toggle("fa-minus-circle");
});


var xp = document.getElementById("sk");

xp.addEventListener('click', function(){
    document.getElementById("add_sk").classList.toggle("hidden");
    document.querySelector("#sk > i").classList.toggle("fa-plus-circle");
    document.querySelector("#sk > i").classList.toggle("fa-minus-circle");
});


//code to display chosen image

document.getElementById('getval').addEventListener('change', readURL, true);

function readURL() {
    var file = document.getElementById('getval').files[0];
    console.log(file);
    var reader = new FileReader();
    reader.onloadend = function() {
    	console.log("hello");
        document.getElementById('profile_photo').src = reader.result;
        console.log(document.getElementById('profile_photo').src);
    };
    if(file) {
        reader.readAsDataURL(file);
    }
    else {
    }
}
