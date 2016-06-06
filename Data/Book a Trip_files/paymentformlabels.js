(function () {
    'use strict'

    $(document).ready(function () {

        /**
		 * START PAYMENT INFORMATION
		 */
        var pathName = window.location.pathname.toUpperCase();
        if (!pathName.match('PAYMENT.ASPX')) return;

        var forLabelling = [
                {
                    "id": ["ExternalAccount_PaymentMethodCodeClone_VI", "ExternalAccount_PaymentMethodCodeClone_MC", "ExternalAccount_PaymentMethodCodeClone_TP"],
                    "placeholder": "CREDIT CARD*",
                    "containerStyle": { width: "190px" }
                },
                {
                    "id": ["CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_VI_CC\\:\\:VerificationCode",
                            "CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_MC_CC\\:\\:VerificationCode"],
                    "placeholder": "VALIDATION NUMBER*"
                },
                {
                    "id": ["CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_VI_EXPDAT_MONTH",
                        "CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_MC_EXPDAT_MONTH",
                        "CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_TP_EXPDAT_MONTH"],
                    "placeholder": "EXPIRATION DATE*",
                    "title": "month",
                    "containerStyle": { width: "160px" }
                },
                {
                    "id": ["CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_VI_EXPDAT_YEAR",
                        "CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_MC_EXPDAT_YEAR",
                        "CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_TP_EXPDAT_YEAR"],
                    "title": "year",
                    "placeholder": "&nbsp;&nbsp;",
                    "containerStyle": { width: "160px" },
                    "labelStyle": { width: "100px" }
                },
                {
                    "id": ["CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_VI_STATEPROV",
                    "CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_MC_STATEPROV"],
                    "containerStyle": { width: "385px" }
                },
                {
                    "id": ["CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_VI_POSTALCODE",
                    "CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_MC_POSTALCODE"],
                    "labelStyle": { "margin-left": "10px" }
                },
                {
                    "id": ["CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_VI_COUNTRYCODE",
                    "CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_MC_COUNTRYCODE"],
                    "placeholder": "PHONE NUMBER* (EX. 63-32-9999999)",
                    "title": "country code"
                },
                {
                    "id": ["CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_VI_AREACODE",
                        "CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_MC_AREACODE"],
                    "placeholder": "&nbsp;&nbsp;",
                    "title": "area code",
                    "containerStyle": { "left": "-167px" }
                },
                {
                    "id": ["CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_VI_PHONE",
                        "CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_MC_PHONE"],
                    "placeholder": "&nbsp;&nbsp;",
                    "title": "phone number",
                    "containerStyle": { "left": "-165px" }
                },
                {
                    "id": [
                        "CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_VI_ACCTNO",
                        "CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_VI_ISSBANK",
                        "CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_VI_ISSBANKLOC",
                        "CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_VI_FIRSTNAME",
                        "CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_VI_CC\\:\\:AccountHolderName",
                        "CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_VI_ADDRESS",
                        "CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_VI_TOWNCITY",
                        "CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_VI_COUNTRY",
                        "CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_MC_ACCTNO",
                        "CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_MC_ISSBANK",
                        "CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_MC_ISSBANKLOC",
                        "CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_MC_FIRSTNAME",
                        "CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_MC_CC\\:\\:AccountHolderName",
                        "CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_MC_ADDRESS",
                        "CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_MC_TOWNCITY",
                        "CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_MC_COUNTRY",
                        "CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_VI_EMAIL",
                        "CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_MC_EMAIL",
                        "CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_TP_ACCTNO"
                    ]
                }
        ];

        $.each(forLabelling, function () {
            var controlIDs = $.makeArray(this.id);

            for (var i in controlIDs) {
                var _this = $("#" + controlIDs[i]);
                var _placeholder = (this.placeholder != undefined ? this.placeholder : _this.attr("placeholder"));

                if (_placeholder == undefined && _this.is("select")) {
                    _placeholder = _this.find("option").first().text();
                    $("#" + _this.attr("id") + "-button").find("span.ui-selectmenu-status").empty();
                }

                insertLabel(_this, {
                    title: (this.title == undefined ? _placeholder : this.title),
                    placeholder: _placeholder,
                    labelStyle: this.labelStyle,
                    containerStyle: this.containerStyle
                });
            }
        });

        function insertLabel(e, opt) { //title,placeholder,labelStyle,containerStyle
            var id = $(e).attr("id");
            var label = null;
            if (typeof id == "undefined") return;
            if ($("label[for='" + id + "']").length > 0) {
                label = $("label[for='" + id + "']").detach();
            }
            else {
                label = $("<label for='" + (id).replace(/\\/g, "") + "'><span class='off-screen'>" + opt.title + "</span>" + opt.placeholder + "</label>");
            }

            label
                .html("<span class='off-screen'>" + opt.title + "</span>" + opt.placeholder)
                .attr("style", "float:left;font-size:13px")
                .removeClass("hidden")
                .insertBefore($(e).attr("placeholder", ""));

            if (typeof opt.labelStyle === "object") {
                label.css(opt.labelStyle);
            }

            if (typeof opt.containerStyle === "object") {
                $(e).parent().css(opt.containerStyle);
            }

            if ($(e).data("required.validation") == "true" || $(e).data("required.validation")) {
                $(e).attr("aria-required", "true");
            }
        }

        //$('#SkySales', this).each(function() {

        ////remove invalid fieldset placement  this will negatively impact how screen readers announce
        ////and force teh inability to accurately use a fieldset/legend
        ////$('#ExternalAccount_VI', this).replaceWith(function(){
        ////    return $('<div id="ExternalAccount_VI" class="form" />').css({
        ////        "padding": "0 0 5px",
        ////        "margin": "10px 0 0",
        ////        "border": "0",
        ////        "float": "left",
        ////        "width": "875px",
        ////        "clear": "both",
        ////        "display": "block",
        ////        "position": "relative",
        ////        "border-top": "2px solid #BFBFBF !important"
        ////    }).append($(this).find("div.row").css({
        ////        "float": "left",
        ////        "position": "relative"
        ////    }).contents())
        ////});

        ////credit card / payment		
        //var paymentCardOld=$('#ExternalAccount_PaymentMethodCodeClone_VI-button', this);
        //var paymentCard=$('#ExternalAccount_PaymentMethodCodeClone_VI', this);
        //showProperSelect(paymentCardOld, paymentCard);
        //setOnScreenLabel(paymentCard, 'CREDIT CARD *');

        //var creditCardId="CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_VI_ACCTNO";
        //var creditCardNumberLabel= $('label[for=' + creditCardId+']', this);	
        //creditCardNumberLabel.removeAttr('style');
        //setCorrectAsteriskPosition(creditCardNumberLabel);
        //wrapDiv(creditCardNumberLabel);
        //var creditCardNumber=$('#' + creditCardId, this);
        //creditCardNumber.removeAttr('placeholder');
        //isRequired(creditCardNumber);

        //var ccBankId='CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_VI_ISSBANK';
        //var ccBank=$('#' + ccBankId, this);
        //setOnScreenLabel(ccBank, 'ISSUING BANK *');
        //ccBank.attr('aria-required', 'true');
        //ccBank.removeAttr('placeholder');
        ////remove issueing  ank in the wrong location in the DOM with wrong text
        //$('label[for="'+ ccBankId +'"]').eq(1).removeAttr('for').text('').hide();

        //var ccBankLocationId='CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_VI_ISSBANKLOC';
        //var ccBankLocationOld= $('#CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_VI_ISSBANKLOC-button', this);
        //var ccBankLocation= $('#' + ccBankLocationId,  this);
        //showProperSelect(ccBankLocationOld, ccBankLocation);
        //setOnScreenLabel(ccBankLocation, 'BANK LOCATION *');
        //isRequired(ccBankLocation);
        ////remove bank location label in the wrong location in the DOM with wrong text
        //$('label[for="'+ ccBankLocationId +'"]').eq(1).removeAttr('for').text('').hide();

        //var validationCodeId='CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_VI_CC\\:\\:VerificationCode';
        //var validationCodeLabel= $('label[for='+ validationCodeId+']', this);
        //setCorrectAsteriskPosition(validationCodeLabel);
        //var validationCode=$('#' + validationCodeId, this);
        //isRequired(validationCode);


        //var expirationDateIdOld='CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_VI_EXPDAT_YEAR';
        //var expirationDateIdNew='CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_VI_EXPDAT_MONTH';
        ////fix invalid label
        //var expirationDateLabel= $('label[for=' + expirationDateIdOld +']', this);
        //expirationDateLabel.removeAttr('for')		
        //setCorrectAsteriskPosition(expirationDateLabel);

        //var expirationDateMonthOld= $('#CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_VI_EXPDAT_MONTH-button', this);
        //var expirationDateMonth= $('#' + expirationDateIdNew, this);
        //showProperSelect(expirationDateMonthOld, expirationDateMonth);
        //expirationDateMonth.attr('title', 'Month');
        //isRequired(expirationDateMonth);

        //var expirationDateYearOld= $('#CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_VI_EXPDAT_YEAR-button', this);
        //var expirationDateYear=$('#CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_VI_EXPDAT_YEAR', this);
        //showProperSelect(expirationDateYearOld, expirationDateYear);
        //expirationDateYear.attr('title', 'Year');
        //isRequired(expirationDateYear);

        ////wrap fieldset
        //expirationDateMonth.parent().parent('.row.no-label').wrapInner('<fieldset />');
        //expirationDateMonth.parent().prev('.rowname').wrap('<legend />');

        ////card holder / billing address
        //var givenNameId='CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_VI_FIRSTNAME';
        //var givenName= $('#' + givenNameId, this);
        //setOnScreenLabel(givenName, 'GIVEN NAME *');
        //givenName.attr('placeholder', '');
        //isRequired(givenName);
        ////remove firstname in the wrong spot with wrong text
        //$('label[for="'+ givenNameId +'"]').eq(1).removeAttr('for').text('').hide();

        //var lastNameId='#CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_VI_CC\\:\\:AccountHolderName'
        //var lastNameLabel= $(lastNameId).prev('label');
        //lastNameLabel.removeAttr('style');		
        //lastNameLabel.text('FAMILY NAME/SURNAME');
        //setCorrectAsteriskPosition(lastNameLabel);
        //wrapDiv(lastNameLabel);
        //var lastName=$(lastNameId, this);
        //isRequired(lastName);
        //lastName.removeAttr('placeholder');


        //var addressId='CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_VI_ADDRESS';
        //var addressLabel= $('label[for='+ addressId + ']', this);
        //addressLabel.removeAttr('style');
        //setCorrectAsteriskPosition(addressLabel);
        //wrapDiv(addressLabel);
        //var address=$('#' + addressId, this);
        //address.removeAttr('placeholder');
        //isRequired(address);


        //var cityId='CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_VI_TOWNCITY';
        //var cityLabel=$('label[for='+ cityId + ']', this);
        //cityLabel.removeAttr('style');
        //setCorrectAsteriskPosition(cityLabel);
        //wrapDiv(cityLabel);
        //var city=$('#' + cityId, this);
        //city.removeAttr('placeholder');
        //isRequired(city);


        //var countryId='CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_VI_COUNTRY';
        //var countryOld=$('#CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_VI_COUNTRY-button', this);
        //var country=$('#' + countryId, this);
        //showProperSelect(countryOld, country);
        //setOnScreenLabel(country, 'COUNTRY *');
        ////remove country label in the wrong location in the DOM with wrong text
        //$('label[for="'+ countryId +'"]').eq(1).removeAttr('for').text('').hide();


        //var stateOld=$('#CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_VI_STATEPROV-button', this);
        //var state=$('#CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_VI_STATEPROV', this);
        //showProperSelect(stateOld, state);
        //setOnScreenLabel(state, 'STATE / PROVINCE *');
        //isRequired(state);

        //var zipCodeId= 'CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_VI_POSTALCODE';
        //var zipCodeLabel=$('label[for='+ zipCodeId + ']', this);
        //zipCodeLabel.removeAttr('style');
        //setCorrectAsteriskPosition(zipCodeLabel);
        //wrapDiv(zipCodeLabel);
        //var zipCode=$('#' + zipCodeId, this);
        //zipCode.removeAttr('placeholder');
        //isRequired(zipCode);


        //var emailId='CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_VI_EMAIL';
        //var emailLabel=$('label[for='+ emailId + ']', this);
        //emailLabel.removeAttr('style');
        //setCorrectAsteriskPosition(emailLabel);
        //wrapDiv(emailLabel);
        //var email=$('#' + emailId, this);
        //email.removeAttr('placeholder');
        //isRequired(email);


        ////Phone Number field
        //var phoneCountryId='CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_VI_COUNTRYCODE';
        //$('#'+ phoneCountryId, this).parent().parent().wrapInner('<fieldset />');
        //var phoneDescription= $('#labelPhoneNumber', this);

        //$('#'+ phoneCountryId, this).parent().parent().prepend('<legend>Phone Number *</legend');		
        //var phoneCountryLabel=$('label[for='+ phoneCountryId + ']', this);
        //phoneCountryLabel.removeClass('hidden').addClass('off-screen');
        //phoneCountryLabel.text(phoneCountryLabel.text().replace('of phone number', ''));
        //setCorrectAsteriskPosition(phoneCountryLabel);
        //var phoneCountry=$('#'+ phoneCountryId, this);
        //phoneCountry.attr('aria-describedby',  phoneDescription.attr('id'));
        //isRequired(phoneCountry);

        //var phoneAreaId= 'CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_VI_AREACODE';
        //var phoneAreaLabel=$('label[for='+ phoneAreaId + ']', this);
        //phoneAreaLabel.removeClass('hidden').addClass('off-screen');
        //phoneAreaLabel.text(phoneAreaLabel.text().replace('of phone number', ''));
        //setCorrectAsteriskPosition(phoneAreaLabel);
        //var phoneArea=$('#'+ phoneAreaId, this);
        //phoneArea.attr('aria-describedby',  phoneDescription.attr('id'));
        //isRequired(phoneArea);


        //var phoneNumberId= 'CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_VI_PHONE';
        //var phoneNumberLabel=$('label[for='+ phoneNumberId + ']', this);
        //phoneNumberLabel.removeClass('hidden').addClass('off-screen');
        //phoneNumberLabel.text(phoneNumberLabel.text().replace('of phone number', ''));
        //setCorrectAsteriskPosition(phoneNumberLabel);
        //var phoneNumber=$('#'+ phoneNumberId, this);
        //phoneNumber.attr('aria-describedby',  phoneDescription.attr('id'));
        //isRequired(phoneNumber);



        //});
        ///**
        // * END YOUR PAYMENT INFORMATION
        // */

        //function setCorrectAsteriskPosition(that){
        //	//moving from asterisk from start to end
        //	that.text(that.text().replace(/\*/g, ''));
        //	that.append(' *');
        //}
        //function wrapDiv(that) {
        //	return $(that).wrap('<div />');
        //}

        //function setTitleFeatures(that, btn, legendTitle) {
        //	$(btn).hide();
        //	that.removeAttr('style').attr('class', 'ui-selectmenu ui-widget ui-state-default ui-corner-all ui-selectmenu-dropdown');
        //	that.parent().prepend('<label for="' + that.attr('id') + '"><span class="off-screen">' + legendTitle + '- </span>TITLE *</label>');
        //}

        //function setOnScreenLabel(that, txt) {
        //	return $('<label style="display:block;" for="' + that.attr('id') + '">' + txt + '</label>').insertBefore(that);

        //}

        //function isRequired(that) {
        //	that.attr('aria-required', 'true');
        //}

        //function showProperSelect(implicit, trueSelect) {
        //	var iStyle = implicit.attr('style');
        //	trueSelect.removeAttr('style').attr('style', iStyle).attr('class', 'ui-selectmenu ui-widget ui-state-default ui-corner-all ui-selectmenu-dropdown').show();
        //	implicit.hide();

        //}
    });
})();
{ }
