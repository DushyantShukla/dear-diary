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
    $("#registration-toggler").click(function(){
        $("#box").slideToggle();
       
    });
    
});