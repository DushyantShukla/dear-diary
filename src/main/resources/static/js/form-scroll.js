$(".form-scroll").on('click', function(event) {

    var target = $( $(this).attr('href') );

    if( target.length ) {
        event.preventDefault();
        $("#box").animate({
            scrollTop: target.offset().top
        }, 1000);
    }

});