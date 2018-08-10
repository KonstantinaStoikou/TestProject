var skill = document.getElementById("add_skill");

skill.addEventListener('click', function(){
    document.getElementById("skill_div").classList.toggle("hidden");
    document.querySelector(".new_info + span + button > i").classList.toggle("fa-plus-circle");
    document.querySelector(".new_info + span + button > i").classList.toggle("fa-minus-circle");
});

var id_counter = 0;

function addSkill() {
	var skill = document.getElementById("added_skill").value;
	var input = document.createElement("input");
	input.type = "text";
	input.value = skill;
	input.id = id_counter;
	input.readOnly = true;
	document.getElementById("skills_flex").appendChild(input);
	document.getElementById("added_skill").value = "";
	id_counter++;
}