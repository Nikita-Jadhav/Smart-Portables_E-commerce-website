var data = [
	["NY","New York","5"],
	["IN","Indiana","5"],
	["IL","Illinois","5"],
	["PA","Pennsylvania","4"],
	["CA","California","3"],
	["","undefined","undefined"],
]

$(document).ready(function(){
for (var i in data) {
$("#"+data[i][0]).css("fill","#58899e");

$("#"+data[i][0]).css("opacity", data[i][2]/5);

}
});