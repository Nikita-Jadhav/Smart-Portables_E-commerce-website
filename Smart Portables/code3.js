var data = [
	["AZ","Arizona","14.99"],
	["CA","California","1931.27"],
	["CO","Colorado","179.98"],
	["FL","Florida","149.99"],
	["GA","Georgia","14.99"],
	["IL","Illinois","1474.99"],
	["IN","Indiana","191.27"],
	["MA","Massachusetts","2128.99"],
	["MO","Montana","228.99"],
	["NC","North Carolina","39.99"],
	["NE","Nebraska","756.55"],
	["NY","New York","754.89"],
	["PA","Pennyslyvania","27.88"],
	["TX","Texas","549.88"],
	["","undefined","undefined"],
]

$(document).ready(function(){
for (var i in data) {
$("#"+data[i][0]).css("fill","rgb(220, 105, 81)");

$("#"+data[i][0]).css("opacity", data[i][2]/2128.99);

}
});