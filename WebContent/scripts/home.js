function chooseFile(content) {
	document.querySelector("form > button:first-of-type").style.boxShadow = "";
	document.querySelector("form > button:first-of-type").style.color = "#44826d";
	document.querySelector("form > button:nth-of-type(2)").style.boxShadow = "";
	document.querySelector("form > button:nth-of-type(2)").style.color = "#44826d";
	document.querySelector("form > button:nth-of-type(3)").style.boxShadow = "";
	document.querySelector("form > button:nth-of-type(3)").style.color = "#44826d";
	
	formChildren = document.getElementsByTagName("form")[0];
	if (content == "Photo") {
		if (formChildren.children.length > 5) {
			formChildren.removeChild(formChildren.lastChild);
			console.log(formChildren.children);
		}
		var input = document.createElement("input");
		input.type = "file";
		input.name = "photo";
		input.accept = "image/*"
		document.getElementsByTagName("form")[0].appendChild(input); // put it into the DOM
		document.querySelector("form > button:first-of-type").style.boxShadow = "inset 0 0 0 2px #44826d";
		document.querySelector("form > button:first-of-type").style.color = "#2e5a4c";
	} else if (content == "Video") {
		if (formChildren.children.length > 5) {
			formChildren.removeChild(formChildren.lastChild);
			console.log(formChildren.children);
		}
		var input = document.createElement("input");
		input.type = "file";
		input.name = "video";
		input.accept = "video/*"
		document.getElementsByTagName("form")[0].appendChild(input); // put it into the DOM
		document.querySelector("form > button:nth-of-type(2)").style.boxShadow = "inset 0 0 0 2px #44826d";
		document.querySelector("form > button:nth-of-type(2)").style.color = "#2e5a4c";
	} else {
		if (formChildren.children.length > 5) {
			formChildren.removeChild(formChildren.lastChild);
			console.log(formChildren.children);
		}
		var input = document.createElement("input");
		input.type = "file";
		input.name = "audio";
		input.accept = "audio/*"
		document.getElementsByTagName("form")[0].appendChild(input); // put it into the DOM
		document.querySelector("form > button:nth-of-type(3)").style.boxShadow = "inset 0 0 0 2px #44826d";
		document.querySelector("form > button:nth-of-type(3)").style.color = "#2e5a4c";
	}
}

