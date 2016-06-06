(function() {
    'use strict'

    $(document).ready(function() {

        /**
		 * START YOUR CONTACT INFORMATION
		 */
        //from guest.aspx
        var pathName = window.location.pathname.toUpperCase();
        if (!pathName.match('GUEST.ASPX')) return;

        $('#SkySales', this).each(function () {
            $('.contactform', this).each(function () {
                var contactTitle = $('.pagesubheader h2').text();

                //TODO: START title, first name, last name
                var titleSelect = $('#CONTROLGROUPGUEST_GuestContactInputView_DropDownListTitle', this);
                wrapDiv(titleSelect);
                setTitleFeatures(titleSelect, '#CONTROLGROUPGUEST_GuestContactInputView_DropDownListTitle-button', contactTitle);
                isRequired(titleSelect);

                var firstName = $('#CONTROLGROUPGUEST_GuestContactInputView_TextBoxFirstName', this);
                wrapDiv(firstName);
                setOnScreenLabel(firstName, '<span class="off-screen">' + contactTitle + '- </span>GIVEN NAME *');
                isRequired(firstName);


                var lastName = $('#CONTROLGROUPGUEST_GuestContactInputView_TextBoxLastName', this);
                wrapDiv(lastName);
                setOnScreenLabel(lastName, '<span class="off-screen">' + contactTitle + '- </span>FAMILY NAME/SURNAME *');
                isRequired(lastName);

                //TODO: END title, first name, last name

                //TODO: START Mobile and Alternate Phone fixes
                //NOTE: hiding these as the reading order is incorrect
                $('#test').hide();

                setPlaceholderandLabels($('#CONTROLGROUPGUEST_GuestContactInputView_TextBoxCountryHomePhone', this), "country code");
                setPlaceholderandLabels($('#CONTROLGROUPGUEST_GuestContactInputView_TextBoxCountryOtherPhone', this), "country code");

                setPlaceholderandLabels($('#CONTROLGROUPGUEST_GuestContactInputView_TextBoxAreaHomePhone', this), "area code");
                setPlaceholderandLabels($('#CONTROLGROUPGUEST_GuestContactInputView_TextBoxAreaOtherPhone', this), "area code");

                setPlaceholderandLabels($('#CONTROLGROUPGUEST_GuestContactInputView_TextBoxNumberHomePhone', this), "phone");
                setPlaceholderandLabels($('#CONTROLGROUPGUEST_GuestContactInputView_TextBoxNumberOtherPhone', this), "phone");

                function setPlaceholderandLabels(that, label) {
                    that.attr('title', label);
                }


                //http://stackoverflow.com/questions/20386796/wrapping-sets-of-elements-from-a-list-in-divs-using-jquery
                while ($('.phoneNumberContainer > input', this).length) {
                    isRequired($('.phoneNumberContainer > input'));
                    $('.phoneNumberContainer > input:lt(3)', this).wrapAll('<fieldset class="data-set-1" style="width:250px;" />');
                    $('.phoneNumberContainer > input:lt(3)', this).wrapAll('<fieldset class="data-set-2" style="width:250px;" />');
                }

                $('.data-set-1').prepend('<legend>' + $('#test .phoneNumberContainer').eq(0).html().replace('No.', '<span class="off-screen">Number</span><span aria-hidden="true">No.</span>') + '</legend>');
                $('.data-set-2').prepend('<legend>' + $('#test .phoneNumberContainer').eq(1).html().replace('No.', '<span class="off-screen">Number</span><span aria-hidden="true">No.</span>') + '</legend>');

                //TODO: END Mobile and Alternate Phone fix

                //TODO: START email addresses
                wrapDiv('#CONTROLGROUPGUEST_GuestContactInputView_TextBoxEmailAddress');
                wrapDiv('#CONTROLGROUPGUEST_GuestContactInputView_TextBoxConfirmEmail');

                $('.row.no-label.two-col:last').prev().hide();
                $('.row.no-label.two-col:last').wrapInner('<fieldset />');
                $('.row.no-label.two-col:last fieldset').prepend('<legend>' + $('.row.no-label.two-col:last').prev().children('.phoneNumberContainer').html() + '</legend>');

                var email1 = $('#CONTROLGROUPGUEST_GuestContactInputView_TextBoxEmailAddress', this);
                var email2 = $('#CONTROLGROUPGUEST_GuestContactInputView_TextBoxConfirmEmail', this);
                setOnScreenLabel(email1, 'EMAIL *');
                isRequired(email1);

                setOnScreenLabel(email2, 'RETYPE EMAIL *');
                isRequired(email2);

                //TODO: END email addresses
            });

            $('#pebContainer', this).children('p[1]').attr('id', 'p1');

            //START OF PASSENGER INFORMATION SECTION
            $('.passengerform', this).each(function() {
                //removing inappropriate use of fieldsets, which is pretty bad on this page.
                $('fieldset', this).attr('role', 'presentation');
                //problem with setting role=presentation instead of replaceing it is that we cannot use fieldset at all within this section as it will be invalid HTML

                //because of that, each label will have to append off-screen legend title
                var legendTitle = $('h2', this).text();
                var titleSelect2 = $('#CONTROLGROUPGUEST_PassengerInputViewGuestView_DropDownListTitle_0', this);
                wrapDiv(titleSelect2);
                setTitleFeatures(titleSelect2, '#CONTROLGROUPGUEST_PassengerInputViewGuestView_DropDownListTitle_0-button', legendTitle);

                var gFirstName = $('#CONTROLGROUPGUEST_PassengerInputViewGuestView_TextBoxFirstName_0', this);
                var gMiddleName = $('#CONTROLGROUPGUEST_PassengerInputViewGuestView_TextBoxMiddleName_0', this);
                var gLastName = $('#CONTROLGROUPGUEST_PassengerInputViewGuestView_TextBoxLastName_0', this);

                wrapDiv(gFirstName);
                var gFirstNameLabel = $('#CONTROLGROUPGUEST_PassengerInputViewGuestView_TextBoxFirstName_0Label', this);
                gFirstNameLabel.html('<span class="off-screen">' + legendTitle + '- </span>GIVEN NAME *');
                gFirstNameLabel.parent().show();
                isRequired(gFirstName);

                var gFirstNameLabelWrapper = gFirstNameLabel.parent();
                gFirstNameLabel = gFirstNameLabel.detach();
                gFirstNameLabelWrapper.remove();
                gFirstName.parent()
                    .css("margin-right", "0px")
                    .prepend(gFirstNameLabel.css("display", "block"));

                wrapDiv(gMiddleName);
                setOnScreenLabel(gMiddleName, '<span class="off-screen">' + legendTitle + '- </span>MIDDLE NAME *');
                isRequired(gMiddleName);

                wrapDiv(gLastName);
                var gLastNameLabel= $('#CONTROLGROUPGUEST_PassengerInputViewGuestView_TextBoxLastName_0Label', this);
                gLastNameLabel.html('<span class="off-screen">' + legendTitle + '- </span>FAMILY NAME/SURNAME *');
                gLastNameLabel.parent().show();
                isRequired(gLastName);

                var gLastNameLabelWrapper = gLastNameLabel.parent();
                gLastNameLabel = gLastNameLabel.detach();
                gLastNameLabelWrapper.remove();
                gLastName.parent()
                    .prepend(gLastNameLabel.css("display", "block"));

                //nationality
                var oldNationality = $('#CONTROLGROUPGUEST_PassengerInputViewGuestView_DropDownListNationality_0-button', this);
                var nationality = $('#CONTROLGROUPGUEST_PassengerInputViewGuestView_DropDownListNationality_0', this);
                nationality.parent().prev('.paxinputlabel').wrapInner('<label for="' + nationality.attr('id') + '" />');
                showProperSelect(oldNationality, nationality);

                //birthdate
                var bdMonthOld = $('#CONTROLGROUPGUEST_PassengerInputViewGuestView_DropDownListBirthDateMonth_0-button', this);
                var sBMonth = $('#CONTROLGROUPGUEST_PassengerInputViewGuestView_DropDownListBirthDateMonth_0', this);
                //setting Label
                var lblText = sBMonth.parent().prev('.paxinputlabel');
                showProperSelect(bdMonthOld, sBMonth);
                isRequired(sBMonth);
                sBMonth.attr('title', lblText.text() + "- Month");

                var bdDayOld = $('#CONTROLGROUPGUEST_PassengerInputViewGuestView_DropDownListBirthDateDay_0-button', this);
                var sBDay = $('#CONTROLGROUPGUEST_PassengerInputViewGuestView_DropDownListBirthDateDay_0', this);
                showProperSelect(bdDayOld, sBDay);
                isRequired(sBDay);
                sBDay.attr('title', lblText.text() + "- Day");

                var bdYearOld = $('#CONTROLGROUPGUEST_PassengerInputViewGuestView_DropDownListBirthDateYear_0-button', this);
                var sBYear = $('#CONTROLGROUPGUEST_PassengerInputViewGuestView_DropDownListBirthDateYear_0', this);
                showProperSelect(bdYearOld, sBYear);
                isRequired(sBYear);
                sBYear.attr('title', lblText.text() + "- Year");
                lblText.append(' *');

                //gender
                $('#gender_0_male', this).next().prepend('<span class="off-screen">Gender- </span>');
                $('#gender_0_male', this).next().wrap('<label for="gender_0_male" />');
                $('#gender_0_female', this).next().prepend('<span class="off-screen">Gender- </span>');
                $('#gender_0_female', this).next().wrap('<label for="gender_0_female" />');

                //membership number
                var membership = $('#CONTROLGROUPGUEST_PassengerInputViewGuestView_TextBoxtQAFFNumber0_0', this);
                membership.parent().prev().wrap('<label for="' + membership.attr('id') + '" />');

                var sCountryOld = $('#CONTROLGROUPGUEST_PassengerInputViewGuestView_DropDownListDocumentCountry0_0-button', this);
                var sCountry = $('#CONTROLGROUPGUEST_PassengerInputViewGuestView_DropDownListDocumentCountry0_0', this);
                showProperSelect(sCountryOld, sCountry);

                var redressNumber = $('#CONTROLGROUPGUEST_PassengerInputViewGuestView_TextBoxRedressNumber0_0', this);
                setOnScreenLabel(redressNumber, 'REDRESS NUMBER (IF ANY)');
                redressNumber.attr('value', '');

                //invalid for attribute on expiry date
                var expLabel = $('#CONTROLGROUPGUEST_PassengerInputViewGuestView_DropDownListDocumentDateDay0_0Label', this);
                expLabel.removeAttr('for');

                var expMonthOld = $('#CONTROLGROUPGUEST_PassengerInputViewGuestView_DropDownListDocumentDateMonth0_0-button', this);
                var expMonth = $('#CONTROLGROUPGUEST_PassengerInputViewGuestView_DropDownListDocumentDateMonth0_0', this);
                showProperSelect(expMonthOld, expMonth);
                expMonth.attr('title', expLabel.text() + "- Month");

                var expDayOld = $('#CONTROLGROUPGUEST_PassengerInputViewGuestView_DropDownListDocumentDateDay0_0-button', this);
                var expDay = $('#CONTROLGROUPGUEST_PassengerInputViewGuestView_DropDownListDocumentDateDay0_0', this);
                showProperSelect(expDayOld, expDay);
                expDay.attr('title', expLabel.text() + "- Day");

                var expYearOld = $('#CONTROLGROUPGUEST_PassengerInputViewGuestView_DropDownListDocumentDateYear0_0-button', this);
                var expYear = $('#CONTROLGROUPGUEST_PassengerInputViewGuestView_DropDownListDocumentDateYear0_0', this);
                showProperSelect(expYearOld, expYear);
                expYear.attr('title', expLabel.text() + "- Year");

                $("select[id^='CONTROLGROUPGUEST_']").filter(function () {
                    return $(this).height() <= 2 ? false : true;
                }).css("height", "31px");
            });
        });

        //passport
        $("[id^='CONTROLGROUPGUEST_PassengerInputViewGuestView_TextBoxDocumentNumber'").each(function () {
            setOnScreenLabel($(this), 'Passport Number');
            $(this).attr('value', '');
        });

        //END OF PASSENGER INFORMATION SECTION

        $('#pebFieldset, #sefFieldset, #mealFieldset').attr('role', 'presentation');

        //START OF SPORTS EQUIPMENT SECTION
        $('#sfDiv', this).each(function() {

            var setId= "txtGuest1";
            $('.sfContainer .paxNameSF01.paxNameSF_0').eq(0).attr('id', setId);
            var sports1Old = $('#CONTROLGROUPGUEST_SportsEquipmentGuestView_DropDownSsrOpt_Passenger0SSRSEF0-button', this);
            var sports1 = $('#CONTROLGROUPGUEST_SportsEquipmentGuestView_DropDownSsrOpt_Passenger0SSRSEF0', this);
            showProperSelect(sports1Old, sports1);
            setSportsLabel(sports1);
            sports1.attr('aria-describedby', setId);

            var sports2Old = $('#CONTROLGROUPGUEST_SportsEquipmentGuestView_DropDownSsrOpt_Passenger0SSRSEF1-button', this);
            var sports2 = $('#CONTROLGROUPGUEST_SportsEquipmentGuestView_DropDownSsrOpt_Passenger0SSRSEF1', this);
            showProperSelect(sports2Old, sports2);
            setSportsLabel(sports2);
            sports2.attr('aria-describedby', setId);
        });

        function setSportsLabel(that) {
            that.closest('.sfContainer').prev().find('th').wrapInner('<label for="' + that.attr('id') + '" />');
            //	that.attr('aria-labelledby', that.parent().prevAll('span').text());
        }

        //END OF SPORTS EQUIPMENT SECTION

        //START OF HOTEL SECTION
        $('#hotel-bookingsearch .titlebar', this).wrap('<legend style="width:100%" />');
        $('#hotelsearchbooking', this).each(function () {

            $('#hotelLocationMcDropDownHidden', this).removeAttr('class');
            $('#hotelLocationMcDropDown', this).addClass('hidden');

            //fixing invalid for attributes
            //anon
            $('#CONTROLGROUPGUEST_HotelSearchControlGroupGuestView_CityLabel', this).attr('for', 'CONTROLGROUPGUEST_HotelSearchControlGroupGuestView_LocationDropDown');
            var hotelLocationOld = $('#CONTROLGROUPGUEST_HotelSearchControlGroupGuestView_LocationDropDown-button', this);
            var hotelLocation = $('#CONTROLGROUPGUEST_HotelSearchControlGroupGuestView_LocationDropDown', this);
            showProperSelect(hotelLocationOld, hotelLocation);
            $('#CONTROLGROUPGUEST_HotelSearchControlGroupGuestView_Location-button', this).hide();
            $('#CONTROLGROUPGUEST_HotelSearchControlGroupGuestView_Location', this).hide();
            hotelLocation.css('width', 'auto');
            hotelLocation.val($("#CONTROLGROUPGUEST_HotelSearchControlGroupGuestView_Location").val()).trigger("change");

            var hotelRoomsOld = $('#CONTROLGROUPGUEST_HotelSearchControlGroupGuestView_RoomsDropDown-button', this);
            var hotelRooms = $('#CONTROLGROUPGUEST_HotelSearchControlGroupGuestView_RoomsDropDown', this);
            showProperSelect(hotelRoomsOld, hotelRooms);

            var hotelAdultsOld = $('#CONTROLGROUPGUEST_HotelSearchControlGroupGuestView_PaxTypeDropDown_PAXTYPE_ADT-button', this);
            var hotelAdults = $('#CONTROLGROUPGUEST_HotelSearchControlGroupGuestView_PaxTypeDropDown_PAXTYPE_ADT', this);
            showProperSelect(hotelAdultsOld, hotelAdults);

            hotelLocation.css("width", "90%");
            hotelLocation.on("change", function () {
                $("#CONTROLGROUPGUEST_HotelSearchControlGroupGuestView_Location").val($(this).val());
            });
            //hotelLocation.prop("disabled", "disabled");

            //member
            $('#CONTROLGROUPAGENTGUEST_HotelSearchControlGroupAgentGuestView_CityLabel', this).attr('for', 'CONTROLGROUPAGENTGUEST_HotelSearchControlGroupAgentGuestView_LocationDropDown');
            var hotelLocationOld = $('#CONTROLGROUPAGENTGUEST_HotelSearchControlGroupAgentGuestView_LocationDropDown-button', this);
            var hotelLocation = $('#CONTROLGROUPAGENTGUEST_HotelSearchControlGroupAgentGuestView_LocationDropDown', this);
            showProperSelect(hotelLocationOld, hotelLocation);
            $('#CONTROLGROUPAGENTGUEST_HotelSearchControlGroupAgentGuestView_Location-button', this).hide();
            $('#CONTROLGROUPAGENTGUEST_HotelSearchControlGroupAgentGuestView_Location', this).hide();
            hotelLocation.css('width', 'auto');
            hotelLocation.val($("#CONTROLGROUPAGENTGUEST_HotelSearchControlGroupAgentGuestView_Location").val()).trigger("change");

            var hotelRoomsOld = $('#CONTROLGROUPAGENTGUEST_HotelSearchControlGroupAgentGuestView_RoomsDropDown-button', this);
            var hotelRooms = $('#CONTROLGROUPAGENTGUEST_HotelSearchControlGroupAgentGuestView_RoomsDropDown', this);
            showProperSelect(hotelRoomsOld, hotelRooms);

            var hotelAdultsOld = $('#CONTROLGROUPAGENTGUEST_HotelSearchControlGroupAgentGuestView_PaxTypeDropDown_PAXTYPE_ADT-button', this);
            var hotelAdults = $('#CONTROLGROUPAGENTGUEST_HotelSearchControlGroupAgentGuestView_PaxTypeDropDown_PAXTYPE_ADT', this);
            showProperSelect(hotelAdultsOld, hotelAdults);

            hotelLocation.css("width", "90%");
            hotelLocation.on("change", function () {
                $("#CONTROLGROUPAGENTGUEST_HotelSearchControlGroupAgentGuestView_Location").val($(this).val());
            });
            //hotelLocation.prop("disabled", "disabled");

        });
        //END OF HOTEL SECTION

        /**
		 * END YOUR CONTACT INFORMATION
		 */
        if($("#dimmerDialogContentBody").data("uiTooltip") != undefined)
        $("#dimmerDialogContentBody").tooltip("disable");


        function wrapDiv(that) {
            return $(that).wrap('<div style="display:inline-block" />');
        }

        function setTitleFeatures(that, btn, legendTitle) {
            var exemptClass = $(that).hasClass("passengertitle");
            $(btn).hide();
            that.removeAttr('style').attr('class', 'ui-selectmenu ui-widget ui-state-default ui-corner-all ui-selectmenu-dropdown');
            that.parent().prepend('<label style="display:block;" for="' + that.attr('id') + '"><span class="off-screen">' + legendTitle + '- </span>TITLE *</label>');
            if (exemptClass) that
                .addClass("passengertitle")
                .attr("style", "margin-left:0px !important");
        }

        function setOnScreenLabel(that, txt) {
            return $('<label style="display:block;" for="' + that.attr('id') + '">' + txt + '</label>').insertBefore(that);

        }

        function isRequired(that) {
            var requiredEmpty = SKYSALES.Util.getAttributeValue(that, 'requiredempty');
            if (that.val() == requiredEmpty)
                that.attr('value', '');
            that.attr('aria-required', 'true');
        }

        function showProperSelect(implicit, trueSelect) {
            var iStyle = implicit.attr('style');
            trueSelect.removeAttr('style').attr('style', iStyle).attr('class', 'ui-selectmenu ui-widget ui-state-default ui-corner-all ui-selectmenu-dropdown').show();
            implicit.hide();

        }

    });

})();
