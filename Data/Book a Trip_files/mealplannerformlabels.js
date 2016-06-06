(function() {
	'use strict'

	$(document).ready(function() {

		/**
		 * START MEAL PLANNER DIALOG
		 */

		$('.mealBundlePanelControl', this).each(function() {
			$('tr', this).each(function() {
				var guest1 = $('td:first .mealPaxName', this).text();
				$('input[type="radio"]', this).each(function() {
					var implicitLabel = $(this).next('span').text();
					$(this).attr('title', guest1 + ': ' + implicitLabel);
				});
			});
		});

		$('.mealPanelControl', this).each(function() {
			$('thead th:first', this).attr('class', 'off-screen').text('Guest Name');
			$('tbody tr td:first', this).replaceWith('<th scope="row">' + $('tbody tr td:first', this).html() + '</th>');
			
			var selectOld= $('a[id$=button]', this);
			var selectProper= $('select', this);
			showProperSelect(selectOld, selectProper);
			
			$('select', this).each(function(){
				$(this).parent().parent().prev().find('span').wrapInner('<label for="' + $(this).attr('id') + '" />');	
			});
		});	
		
		/**
		 * END MEAL PLANNER DIALOG
		 */

		function showProperSelect(implicit, trueSelect) {
			var iStyle = implicit.attr('style');
			trueSelect.removeAttr('style').attr('style', iStyle).attr('class', 'ui-selectmenu ui-widget ui-state-default ui-corner-all ui-selectmenu-dropdown').show();
			implicit.hide();

			if (implicit.attr('aria-disabled') == 'true')
			{
			    trueSelect.removeAttr('style').attr('style', iStyle).attr('class', 'ui-selectmenu ui-widget ui-state-default ui-corner-all ui-selectmenu-dropdown ui-selectmenu-disabled ui-state-disabled').show();
			}
		}
	});

})();
