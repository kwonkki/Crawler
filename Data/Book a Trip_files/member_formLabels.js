(function() {
	'use strict'

	$(document).ready(function() {
		$('#SkySales', this).each(function() {
			/**
			 * MY CREDIT CARD INFORMATION- BILLING
			 */
			var addressId = 'ControlGroupCreditCardDetailsView_CreditCardInputCreditCardDetailsView_TextBoxStreetAddress1';
			var addressLabel = $('label[for=' + addressId + ']', this);
			var address = $('#' + addressId, this);
			setOnScreenLabel(address, address.val(), "block");
			setCorrectAsteriskPosition(addressLabel);
			wrapDiv(addressLabel);
			address.attr('value', "");
			isRequired(address);

			var cityId = 'ControlGroupCreditCardDetailsView_CreditCardInputCreditCardDetailsView_TextBoxCity';
			var cityLabel = $('label[for=' + cityId + ']', this);
			wrapDiv(cityLabel);
			var city = $('#' + cityId, this);
			setOnScreenLabel(city, city.val(), "block");
			setCorrectAsteriskPosition(cityLabel);
			city.attr('value', "");
			isRequired(city);

			var countryId = 'ControlGroupCreditCardDetailsView_CreditCardInputCreditCardDetailsView_DropDownListCountry';
			var countryOld = $('#ControlGroupCreditCardDetailsView_CreditCardInputCreditCardDetailsView_DropDownListCountry-button', this);
			var country = $('#' + countryId, this);
			showProperSelect(countryOld, country);
			setOnScreenLabel(country, 'COUNTRY *', "block");

			var stateOld = $('#ControlGroupCreditCardDetailsView_CreditCardInputCreditCardDetailsView_DropDownListState-button', this);
			var state = $('#ControlGroupCreditCardDetailsView_CreditCardInputCreditCardDetailsView_DropDownListState', this);
			showProperSelect(stateOld, state);
			setOnScreenLabel(state, 'STATE / PROVINCE *', "block");
			isRequired(state);

			var zipCodeId = 'ControlGroupCreditCardDetailsView_CreditCardInputCreditCardDetailsView_TextBoxPostalCode';
			var zipCodeLabel = $('label[for=' + zipCodeId + ']', this);
			wrapDiv(zipCodeLabel);
			var zipCode = $('#' + zipCodeId, this);
			setOnScreenLabel(zipCode, zipCode.val(), "block");
			setCorrectAsteriskPosition(zipCodeLabel);
			zipCode.attr('value', "");
			isRequired(zipCode);

			var emailId = 'ControlGroupCreditCardDetailsView_CreditCardInputCreditCardDetailsView_TextBoxEmail';
			var emailLabel = $('label[for=' + emailId + ']', this);
			wrapDiv(emailLabel);
			var email = $('#' + emailId, this);
			setOnScreenLabel(email, email.val(), "block");
			setCorrectAsteriskPosition(emailLabel);
			email.attr('value', "");
			isRequired(email);

			//Phone Number field
			var phoneCountryId = 'ControlGroupCreditCardDetailsView_CreditCardInputCreditCardDetailsView_TextBoxCountryBillingNumber';
			var txtPhone = "Phone Number";
			$('#' + phoneCountryId, this).parent().parent().parent().prepend('<div>' + txtPhone + ' *</div>');
			var phoneCountryLabel = $('label[for=' + phoneCountryId + ']', this);
			var phoneCountry = $('#' + phoneCountryId, this);
			isRequired(phoneCountry);
			phoneCountry.attr('title', txtPhone + " country code");

			var phoneAreaId = 'ControlGroupCreditCardDetailsView_CreditCardInputCreditCardDetailsView_TextBoxAreaBillingNumber';
			var phoneAreaLabel = $('label[for=' + phoneAreaId + ']', this);
			var phoneArea = $('#' + phoneAreaId, this);
			isRequired(phoneArea);
			phoneArea.attr('title', txtPhone + " area code");

			var phoneNumberId = 'ControlGroupCreditCardDetailsView_CreditCardInputCreditCardDetailsView_TextBoxNumberBillingNumber';
			var phoneNumberLabel = $('label[for=' + phoneNumberId + ']', this);
			var phoneNumber = $('#' + phoneNumberId, this);
			isRequired(phoneNumber);
			phoneNumber.attr('title', txtPhone + " phone");

			/**
			 * MY BOOKINGS CONFIRMATION NUMBER
			 */
			var confirmId = "ControlGroupCustomerBookingListView_BookingListCustomerBookingListView_ConfirmationNumber";
			var confirmLabel = $('#' + confirmId, this).parent().text().trim();
			$('#' + confirmId, this).parent(':contains("' + confirmLabel + '")').each(function() {
				var newText = $(this).html().replace(confirmLabel, '<label for="' + confirmId + '">' + confirmLabel + '</label>');
				$(this).html(newText);
			});

		});

		function setCorrectAsteriskPosition(that) {
			//moving from asterisk from start to end
			that.text(that.text().replace(/\*/g, ''));
			that.append(' *');
		}

		function wrapDiv(that) {
			return $(that).wrap('<div />');
		}

		function setTitleFeatures(that, btn, legendTitle) {
			$(btn).hide();
			that.removeAttr('style').attr('class', 'ui-selectmenu ui-widget ui-state-default ui-corner-all ui-selectmenu-dropdown');
			that.parent().prepend('<label for="' + that.attr('id') + '"><span class="off-screen">' + legendTitle + '- </span>TITLE *</label>');
		}

		function setOnScreenLabel(that, txt, display) {
			return $('<label style="display:' + display + '" for="' + that.attr('id') + '">' + txt + '</label>').insertBefore(that);

		}

		function isRequired(that) {
			that.attr('aria-required', 'true');
		}

		function showProperSelect(implicit, trueSelect) {
			var iStyle = implicit.attr('style');
			trueSelect.removeAttr('style').attr('style', iStyle).attr('class', 'ui-selectmenu ui-widget ui-state-default ui-corner-all ui-selectmenu-dropdown').show();
			implicit.hide();
		}

	});

})(); {
}
