(function() {
	'use strict'

	$(document).ready(function() {

/*
 * The opening of the dialog is already in place, however need this to set focus properly 
 * and properly disable the elements not part of the dialog
 * 
 */
		$('.mealOpen').click(function(){
			$(this).blur();
			$('#mealsHandler').show(function(){
					$(this).siblings('div, p, a, span, form').attr('aria-hidden', 'true').attr('tabindex', '-1');	
			})
			$('#mealsHandler div[role="dialog"]').focus();
		});

		$('#mealsHandler div[role="dialog"]').each(function() {
			//removing aria-describedby as everything in that dialog is being rendered and is cluttering the screen reader
			//removing aria-labelledby as it is referring to an empty span element
			//add aria-label
			$(this).removeAttr('aria-labelledby').removeAttr('aria-describedby').attr('aria-label', "Meals for " + $(this).find('h3').text().trim());
			$(this).prepend('<div class="off-screen">Start of dialog</div>');
			$(this).append('<div class="off-screen">End of dialog</div>');

		});

		//additional alternative text issues
		$('.mealPanelControl', this).each(function() {
			$('thead th', this).each(function() {
				var cellIndex = $(this).index();
				$('img', this).each(function() {
					var imgAlt = $(this).parent().parent().parent().next('tbody').find('th, td').eq(cellIndex).children('div:first').text().trim();
					$(this).attr('alt', imgAlt);
				});
			});

		});

	});

})();
