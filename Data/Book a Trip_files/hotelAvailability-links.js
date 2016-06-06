(function() {
	'use strict'

	$(document).ready(function() {
		$('.hotelAvailabilityContainer', this).each(function(){
			$('.hotelLocationMap, .hotelInfoLink, .roomSelect.btn', this).each(function(){
				if (! $(this).attr('href')){
					$(this).attr('href', 'javascript:void(0);');
				}
			});
			
			//found Wifi images with no alt attribute: add attribute
			$('img[src$="hotels-icon-wifi.png"]', this).attr('alt', '');
		});

	});
})();
