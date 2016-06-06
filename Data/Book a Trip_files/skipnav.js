(function() {
	'use strict'

	$(document).ready(function() {
		
	    //dynamically prepend skip to main content to body
	    //$('.main').attr('id', 'DeltaPlaceHolderMain').attr('tabindex', '-1');
	    var skipId = $('.main').attr("id");
	    if (skipId == undefined) {
	        skipId = "DeltaPlaceHolderMain";
	    }

	    $('.main').attr('id', skipId).attr('tabindex', '-1');
	    $('<a href="#' + skipId + '">Skip to main content</a>').prependTo('body');
});
})();