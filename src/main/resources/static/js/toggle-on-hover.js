//$(document).ready(function() { 
//	 $("#registration").hover(
//			    function() {
//			        $("#box").slideDown(500);
//			    }, function() {
//			        $("#box").slideUp(500);
//			    });
//		});
//

$(document).ready(function(){
//    $("#registration-toggler").click(function(){
//        $("#box").slideToggle();
//       
//    });
	$("#registration-toggler").click(function(e){ // <----you missed the '.' here in your selector.
        e.stopPropagation();
        $("#box").slideToggle();
    });
    $("#box").click(function(e){
        e.stopPropagation();
    });

    $(document).click(function(){
        $("#box").slideUp();
    });
});

