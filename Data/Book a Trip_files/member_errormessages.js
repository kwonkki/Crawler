(function() {
	'use strict'

	$(document).ready(function() {
		var blnClicked = false;
		$('#SkySales', this).each(function() {
			$('#errorDiv').attr('tabindex', '-1');
			//my bookings
			var btnAddConfirmation = $('#ControlGroupCustomerBookingListView_BookingListCustomerBookingListView_LinkButtonAddBooking');
			$(btnAddConfirmation).click(function() {
				checkCurrentError();
				getValue('ControlGroupCustomerBookingListView_BookingListCustomerBookingListView_ConfirmationNumber', this);
			});

			//my credit cards
			var btnAddCard = $('#ControlGroupCreditCardDetailsView_CreditCardInputCreditCardDetailsView_LinkButtonSaveAddress');
			$(btnAddCard).click(function() {
				checkCurrentError();
				$('#AddressField input, select').each(function() {
				//	console.log($(this).attr('id'));
					getValue($(this).attr('id'), btnAddCard);
				});
			});
		});

	});

	function checkCurrentError() {
		if ($('#errorDiv ul li').length > 0) {
			$('#errorDiv ul li').remove();
		}
		return false;
	}

	function getValue(inputId, btnId) {
		if ($('#' + inputId).hasClass('validationError')) {
			setErrorMessage(btnId, $('#' + inputId).attr('requirederror'));
			$('#errorDiv').focus();
		}
	}

	function setErrorMessage(that, msg) {
		$('#errorDiv').children('iframe').remove();
		if ($('#errorDiv').children('ul').length == 0) {
			$('#errorDiv').prepend('<p>The following errors were found. Please correct and resubmit.</p>');
			$('#errorDiv').wrapInner('<ul />');
		}
		$('#errorDiv ul').append('<li><span class="data-set-error-a11y">' + msg + '</span></li>');
	}

})();