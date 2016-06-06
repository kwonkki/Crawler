(function() {
	'use strict'

	$(document).ready(function() {
		
		var style = document.createElement('style');
		style.type = 'text/css';
		var strStyle;
		strStyle = "#navDiv #tripmeter li {color: #4a505c;display: block;float: left;font-weight: normal;height: 31px;list-style-position:inside; margin:0; padding: 12px 10px 0 20px;text-align: center;}";
		strStyle +="#navDiv #tripmeter li::before {content: none;}"
		style.innerHTML = strStyle;
		document.getElementsByTagName('head')[0].appendChild(style);

		$('#navDiv', this).attr('role', 'navigation').attr('aria-label', 'Flight Progress');
		//This is invalid HTML and is not rendered correctly in JAWS; ul is not a valid child of an ol element
		$('#tripmeter > ul', this).each(function() {
			var idInfo = $(this).attr('id') ? "id='" + $(this).attr('id') + "'" : "";
			var listText = $.trim($(this).html());
			$(this).replaceWith('<li ' + idInfo + '>' + listText + '</li>');
		});

	});
})();
