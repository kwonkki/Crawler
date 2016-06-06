(function() {
	'use strict'

	$(document).ready(function() {
		$('#navDiv', this).attr('role', 'navigation').attr('aria-label', 'Flight Progress');

		//fix invalid html which is incorrectly being announced in JAWS and unable to activate links using a keyboard
		//https://amp.ssbbartgroup.com/public/reporting/view_pattern.php?pattern_id=27093
		
		$('#tripmeter > ul', this).each(function() {
			var idInfo =  $(this).attr('id') ? "id='" + $(this).attr('id') + "'" : "";
			var listText = $.trim($(this).html());
			$(this).replaceWith('<li ' + idInfo + '>' + listText + '</li>');
		});
	});
})(); 