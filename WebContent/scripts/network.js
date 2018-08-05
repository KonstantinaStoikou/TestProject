//submit form when clicking on a user's div
function submitForm(i){
	document.getElementById("user_input").value = i;
	console.log(document.getElementById("user_input").value);
    document.getElementById("submit_form").submit();
}


//functions for search form focus/unfocus and hover/unhover shadow 
function focusFun() {
	var search_form = document.getElementById("search");
	search_form.style.boxShadow = "0 6px 10px 0 rgba(0, 0, 0, .14), 0 1px 18px 0 rgba(0, 0, 0, .12), 0 3px 5px -1px rgba(0, 0, 0, .2)"
}

function unfocusFun() {
	var search_form = document.getElementById("search");
	search_form.style.boxShadow = "0 3px 4px 0 rgba(0, 0, 0, .14), 0 3px 3px -2px rgba(0, 0, 0, .2), 0 1px 8px 0 rgba(0, 0, 0, .12)"
}

function hoverFun() {
	var search_form = document.getElementById("search");
	search_form.classList.add("onhover");
}

function unhoverFun() {
	var search_form = document.getElementById("search");
	search_form.classList.remove("onhover");
}

//functions for input hover/unhover effect when icon hovered/unhovered
function hoverIconFun() {
	var textbox = document.getElementById("textbox");
	textbox.style.boxShadow = "inset 0 0 0 3px #6bc6a7";	
}

function unhoverIconFun() {
	var textbox = document.getElementById("textbox");
	textbox.style.boxShadow = "inset 0 0 0 3px #44826d";
}