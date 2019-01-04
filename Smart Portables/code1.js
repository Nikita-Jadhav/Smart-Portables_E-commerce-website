var data = [
	["AZ","Arizona","1"],
	["CA","California","4"],
	["CO","Colorado","2"],
	["FL","Florida","1"],
	["GA","Georgia","1"],
	["IL","Illinois","3"],
	["IN","Indiana","2"],
	["MA","Massachusetts","2"],
	["MO","Montana","2"],
	["NC","North Carolina","1"],
	["NE","Nebraska","2"],
	["NY","New York","2"],
	["PA","Pennyslyvania","1"],
	["TX","Texas","1"],
	["","undefined","undefined"],
]

$(document).ready(function(){
for (var i in data) {
$("#"+data[i][0]).css("fill","#9dbc70");

$("#"+data[i][0]).css("opacity", data[i][2]/4);

}
});