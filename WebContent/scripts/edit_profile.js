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
