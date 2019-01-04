$(document).ready(function(){
	$('.responsive-buttons a').click(function() {
		$('.responsive-sidebar-hide').toggle();
		
		var text = $(this).text();
    		
		$(this).text(
       		 	text == "Show sidebar" ? "Hide sidebar" : "Show sidebar");
	});

	
	
});