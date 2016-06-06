(function() {
	'use strict'
	$(document).ready(function() {
		$('.viewbooking', this).each(function() {
			var confirmationText = $(this).next().children('strong').text();
			$('a', this).each(function() {
				if ($(this).text().trim().length > 0) {
					setOffScreenText(this, 'for ' + confirmationText, false);
				} else {
					$(this).css('display', 'none');
				}
			});
		});
		$('ul.viewall', this).each(function() {
			var pageHead = $(this).prev().children('h2').html();
			$('li a', this).each(function() {
				if ($(this).text().trim().length > 0) {
					setOffScreenText(this, pageHead + " page ", true);
				} else {
					$(this).css('display', 'none');
				}
			});
		});
		function setOffScreenText(that, txt, infront) {
			if (infront) {
				$(that).prepend('<span class="off-screen"> ' + txt + '</span>');
			} else {
				$(that).append('<span class="off-screen"> ' + txt + '</span>');
			}
		}

	});
})();
