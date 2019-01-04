var thissvg;
var pos, thex, they;
var isFirefox = typeof InstallTrigger !== 'undefined';

$(document).ready(function(){
$("#timeline").height($("#chart").height());
$("#timeline").width($("#chart").width());


if(isFirefox == true) {
	var mysvg = document.getElementById("svg");
	var mysvgw = mysvg.getAttributeNS(null,'width')
	mysvg.getAttributeNS('height', mysvgw*0.618);

	  $(".state").mouseover( function(evt){
	    	thissvg = evt.target;
			$(thissvg)
				.css("cursor","pointer")

		});
		
	  $(".state").mouseout( function(evt){
			thissvg = evt.target;
			$(thissvg)

		});

	  $(window).resize(function(){
	  		mysvgw = mysvg.getAttributeNS(null,'width')
			mysvg.getAttributeNS('height', mysvgw*0.618);
		})


} else {
	$("#svg").height($("#svg").width()*0.618)
    $(".state").mouseover( function(){
    	thissvg = $(event.target).parent();
		$(thissvg)
			.css("cursor","pointer")

	});
	$(".state").mouseout( function(){
		thissvg = $(event.target).parent();
		$(thissvg)

	});

	$(window).resize(function(){
		$("#svg").height($("#svg").width()*0.618)
	})
}

$(".state").mouseover(function(e){
	if (navigator.userAgent.indexOf("Firefox")!=-1){
		
	    pos = $("#timeline").offset()
	    thex = e.pageX - pos.left + 10;
	    they = e.pageY - pos.top + 10;
	    if (thex > $("#timeline").width() - 200) { thex = $("#timeline").width() - 200 };
	    if (they > $("#timeline").height() - 200) { they = $("#timeline").height() - 100 };
	} else {

	    pos = $("#timeline").offset()
	    thex = event.pageX - pos.left + 10;
	    they = event.pageY - pos.top + 10;
	    if (thex > $("#timeline").width() - 200) { thex = $("#timeline").width() - 200 };
	    if (they > $("#timeline").height() - 200) { they = $("#timeline").height() - 100 };
	}

	
	for (var i=0; i<data.length; i++) {
	if (this.id == data[i][0]) {
$("#timeline").append('<div class="hoverbox" style="top:'+they+'px; left:'+thex+'px;"><div class="statename">'+data[i][1]+'</div><div class="statepct">'+data[i][2]+'</div></div>');
	} 
	

	}	
});

$(".state").mouseleave(function(){
	$(".hoverbox").remove();
});


});
