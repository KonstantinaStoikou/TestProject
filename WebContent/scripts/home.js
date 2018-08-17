function chooseFile(content) {
	document.querySelector("form > button:first-of-type").style.boxShadow = "";
	document.querySelector("form > button:first-of-type").style.color = "#44826d";
	document.querySelector("form > button:nth-of-type(2)").style.boxShadow = "";
	document.querySelector("form > button:nth-of-type(2)").style.color = "#44826d";
	document.querySelector("form > button:nth-of-type(3)").style.boxShadow = "";
	document.querySelector("form > button:nth-of-type(3)").style.color = "#44826d";
	
	formChildren = document.getElementsByTagName("form")[0];
	if (content == "Photo") {
		if (formChildren.children.length > 6) {
			formChildren.removeChild(formChildren.lastChild);
			formChildren.removeChild(formChildren.lastChild);
		}
		var hiddenInput = document.createElement("input");
		hiddenInput.type = "hidden";
		hiddenInput.name = "hidden";
		hiddenInput.value= "image";
		document.getElementsByTagName("form")[0].appendChild(hiddenInput); // put it into the DOM
		
		var input = document.createElement("input");
		input.type = "file";
		input.name = "file";
		input.accept = "image/*"
		document.getElementsByTagName("form")[0].appendChild(input); // put it into the DOM
		document.querySelector("form > button:first-of-type").style.boxShadow = "inset 0 0 0 2px #44826d";
		document.querySelector("form > button:first-of-type").style.color = "#2e5a4c";
	} else if (content == "Video") {
		if (formChildren.children.length > 6) {
			formChildren.removeChild(formChildren.lastChild);
			formChildren.removeChild(formChildren.lastChild);
		}
		var hiddenInput = document.createElement("input");
		hiddenInput.type = "hidden";
		hiddenInput.name = "hidden";
		hiddenInput.value= "video";
		document.getElementsByTagName("form")[0].appendChild(hiddenInput); // put it into the DOM
		
		var input = document.createElement("input");
		input.type = "file";
		input.name = "file";
		input.accept = "video/*"
		document.getElementsByTagName("form")[0].appendChild(input); // put it into the DOM
		document.querySelector("form > button:nth-of-type(2)").style.boxShadow = "inset 0 0 0 2px #44826d";
		document.querySelector("form > button:nth-of-type(2)").style.color = "#2e5a4c";
	} else {
		if (formChildren.children.length > 6) {
			formChildren.removeChild(formChildren.lastChild);
			formChildren.removeChild(formChildren.lastChild);
		}
		var hiddenInput = document.createElement("input");
		hiddenInput.type = "hidden";
		hiddenInput.name = "hidden";
		hiddenInput.value= "audio";
		document.getElementsByTagName("form")[0].appendChild(hiddenInput); // put it into the DOM
		
		var input = document.createElement("input");
		input.type = "file";
		input.name = "file";
		input.accept = "audio/*"
		document.getElementsByTagName("form")[0].appendChild(input); // put it into the DOM
		document.querySelector("form > button:nth-of-type(3)").style.boxShadow = "inset 0 0 0 2px #44826d";
		document.querySelector("form > button:nth-of-type(3)").style.color = "#2e5a4c";
	}
}

