var data = [
	["NY","New York","1"],
	["IN","Indiana","1"],
	["IL","Illinois","1"],
	["PA","Pennsylvania","1"],
	["CA","California","4"],
	["","undefined","undefined"],
]

$(document).ready(function(){
for (var i in data) {
$("#"+data[i][0]).css("fill","#58899e");

$("#"+data[i][0]).css("opacity", data[i][2]/4);

}
});