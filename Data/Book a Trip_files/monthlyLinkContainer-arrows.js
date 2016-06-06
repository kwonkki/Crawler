(function() {
	'use strict'

	$(document).ready(function() {

		var availContainer = $('.availabilityInputColumnViewSectionContent');
		for (var i = 0; i < availContainer.length; i++) {
			var headerText = $('.availabilityInputColumnViewSectionContent').eq(i).prevAll(':eq(1)');
			headerText.attr('id', 'flightInfoHeader' + i);
			
			var firstChild= headerText.children(':first').text().trim();
			var secondChild= headerText.children(':nth-child(2)').text().trim();
			var leftArrow="&#10096;";
			$('.availabilityInputColumnViewSectionContent').eq(i).find('.leftArrowButton').children('a')
			.html(setArrowLabels('Previous', firstChild, secondChild, leftArrow));
			
			var rightArrow="&#10097;";
			$('.availabilityInputColumnViewSectionContent').eq(i).find('.rightArrowButton').children('a')
			.html(setArrowLabels('Next', firstChild, secondChild, rightArrow));
		}
		
		
		//also noticed currently select anchor elements not announcing date info and there are two of the same text
		$('.dayHeaderTodayImage a', this).each(function(){
			$(this).removeAttr('aria-label');
			$(this).append('<span class="off-screen">- selected</span>');
		});
		
	});
	
	function setArrowLabels(direction, text1, text2, arrow){
		var labelText= '<span class="off-screen">' + direction + ' ' + text1 + ' dates- ' + text2+ '</span>';
		labelText +='<span class="highContrast" aria-hidden="true">'+ arrow + '</span>';
		return labelText;
	}

})();
