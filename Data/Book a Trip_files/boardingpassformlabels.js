(function() {
	'use strict'
	var i = 0;
	$(document).ready(function() {

		/**
		 * START BOARDING PASS
		 */
		$('#retrieveTable', this).each(function() {
			$('tr', this).each(function() {
				if ($(this).children('td').html().length > 0) {
					$('td:first', this).each(function() {
						$(this).replaceWith('<th scope="row" class="' + $(this).attr('class') + '" id="lbloption' + i + '">' + $(this).html() + '</th>');
					});
					i++;
				}
			});

			$('input', this).each(function() {
				$(this).prev('span').wrapInner('<label for="' + $(this).attr('id') + '"/>');
				$(this).attr('aria-describedby', $(this).parent().siblings('th').attr('id'));
			});

			$('select', this).each(function() {
				$(this).parent().prev('span').wrapInner('<label for="' + $(this).attr('id') + '"/>');
				$(this).attr('aria-describedby', $(this).parent().siblings('th').attr('id'));
			});

		});

		/**
		 * END BOARDING PASS
		 */
	});

})();
