var data = [
	["NY","New York","1"],
	["IN","Indiana","1"],
	["IL","Illinois","1"],
	["CA","California","3"],
	["","undefined","undefined"],
]

$(document).ready(function(){
for (var i in data) {
$("#"+data[i][0]).css("fill","#9dbc70");

$("#"+data[i][0]).css("opacity", data[i][2]/3);

}
});