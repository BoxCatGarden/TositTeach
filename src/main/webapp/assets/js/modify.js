var x = document.getElementById("schedule");
var y = document.getElementById("modifybutton");
var z = document.getElementById("savebutton");

y.onclick = function make(){
	document.getElementById("schedule").readOnly = false;
	y.style.display = "none";
	z.style.display = "inline-block";
};

z.onclick = function save(){
	x.readOnly = true;
	z.style.display = "none";
	y.style.display = "inline-block";
};