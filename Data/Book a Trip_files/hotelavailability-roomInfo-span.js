(function() {
	'use strict'

	$(document).ready(function() {
		$('span.loading.blue.roomInfoLink').each(function() {
			$(this).attr('role', 'link').attr('tabindex', '0');
			setKeydown($(this).attr('onclick'), $(this));
		});

		function setKeydown(onclickLink, that) {
			that.attr('onkeydown', 'if((event.keyCode==13) || (event.keyCode==32)) { return ' + onclickLink + '}');
		}

	});
})();
