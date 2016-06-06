(function() {
	'use strict'

	$(document).ready(function() {

		//Your contact Information
		$('#helpPhoneImage').attr('alt', 'Alternate Phone Number Help');

		//hotel availability
		$('.hotelAvailabilityContainer .hotel-item', this).each(function() {
			var hotelHeading = $('h3 a', this).text();
			$('a', this).each(function() {
				$('img[src$="hotels-icon-map.png"]', this).attr('alt', hotelHeading + ' Map');
			});

			$('h3', this).each(function() {
				$('img[src$="10STAR.png"]', this).attr('alt', '1 star');
				$('img[src$="20STAR.png"]', this).attr('alt', '2 stars');
				$('img[src$="30STAR.png"]', this).attr('alt', '3 stars');
				$('img[src$="40STAR.png"]', this).attr('alt', '4 stars');
				$('img[src$="50STAR.png"]', this).attr('alt', '5 stars');
			});
		});
		
				//meal panel
		$('.mealBundlePanelControl img', this).each(function(){
			setNullAlt($(this));
		});

		$('.availabilityInputColumnViewSectionContent', this).siblings('div').each(function() {
			setNullAlt($(this).children('div').find('img[src$="orange-icon-5J.png"]', this));
			setNullAlt($(this).children('div').find('img[src$="blue-icon-DG.png"]', this));
			setNullAlt($(this).children('div').find('img[src$="green-icon.png"]', this))
		});;
			
		$('.availabilityInputColumnViewSectionContent', this).each(function() {

			
			$('> table', this).each(function() {

				setNullAlt($('img[src$="Airfare_Bundle_Flight.png"]', this));
				setNullAlt($('img[src$="Airfare_Bundle_baggage.png"]', this));
				setNullAlt($('img[src$="Airfare_Bundle_meal.png"]', this));
			});


			// setNullAlt($(this).next('div').find('#5JPlaneIcon', this));
			// setNullAlt($(this).next('div').find('#DGPlaneIcon', this));
			// setNullAlt($(this).next('div').find('#TRPlaneIcon', this));

		});

	});

	function setNullAlt(that) {
		$(that).attr('alt', '');
	}

})();
