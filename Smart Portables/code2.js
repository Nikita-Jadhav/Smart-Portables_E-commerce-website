var data = [
	["AZ","Arizona","14.99"],
	["CA","California","482.8175"],
	["CO","Colorado","89.99"],
	["FL","Florida","149.99"],
	["GA","Georgia","14.99"],
	["IL","Illinois","491.6633333"],
	["IN","Indiana","95.635"],
	["MA","Massachusetts","1064.495"],
	["MO","Montana","114.495"],
	["NC","North Carolina","39.99"],
	["NE","Nebraska","378.275"],
	["NY","New York","377.445"],
	["PA","Pennyslyvania","27.88"],
	["TX","Texas","549.88"],
	["","undefined","undefined"],
]

$(document).ready(function(){
for (var i in data) {
$("#"+data[i][0]).css("fill","rgb(255, 206, 123)");

$("#"+data[i][0]).css("opacity", data[i][2]/1064.495);

}
});