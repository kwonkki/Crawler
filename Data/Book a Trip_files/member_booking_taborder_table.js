(function() {
	'use strict'

	$(document).ready(function() {

		/*
		* MY BOOKINGS
		*/
		//remove tabindex having a value of anything other than 0, will cause problems with the keyboard
		$('#ControlGroupCustomerBookingListView_BookingListCustomerBookingListView_LinkButtonAddBooking').removeAttr('tabindex');
		setPresentation($('table.bookings').eq(1));
		setPresentation($('table.bookings').eq(1).children('thead'));
		setPresentation($('table.bookings').eq(1).children('tbody'));

		$('table.bookings').eq(1).find('tr').each(function() {
			setPresentation(this);
			$('td', this).each(function() {
				setPresentation(this);
			});

			$('th', this).each(function() {
				//surefire way to ensure headings not rendered and set role="presentation"
				$(this).replaceWith('<td role="presentation">' + $(this).html() + '</td>');
			});
		});

		$('table.bookings:not(:eq(1))').each(function() {
			$('th', this).each(function() {
				if ($(this).html().length == 0) {
					$(this).append('<span class="off-screen">Booking Link</span>');
				}
			});
		});

	});

	function setPresentation(that) {
		$(that).attr('role', 'presentation');
	}

})();
