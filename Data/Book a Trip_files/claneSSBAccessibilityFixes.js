$(document).ready(function () {

    (function () {
        //addding option to get clientId 
        $.extend(SKYSALES.Util, {
            getSkySalesClientID: function () {
                var objRegistry = {
                    "Search.aspx": "flightSearch",
                    "CustomerSearch.aspx": "flightSearch",
                    "LoyaltySearch.aspx": "flightSearch",
                    "AgentSearch.aspx": "flightSearch",
                    "SeatAddOnsChange.aspx": "unitMapContainer",
                    "CustomerSeatAddOnsChange.aspx": "unitMapContainer",
                    "SeatMapRetrieval.aspx": "controlGroupBookingRetrieve",
                    "TravelVoucherFlightsChange.aspx": "flightSearch"
                };

                var page = window.location.pathname.split("/").pop();

                if (objRegistry[page] == undefined) {
                    //pick sample control based from main form
                    var htmlControl = $("#SkySales :submit:not(:hidden)[id]").first();
                    if (htmlControl.length > 0) {
                        var clientID = htmlControl.prop("id").split(/_[A-Za-z0-9]+$/)[0];
                        return clientID;
                    }
                    else {
                        return "";
                    }
                }
                else {
                    //get control client id
                    var skySalesObjArray = SKYSALES.Util.createObjectArray;
                    try {
                        var clientID = $.grep(skySalesObjArray, function (n) { return (n.objNameBase == objRegistry[page]) })[0].json.clientId;
                        return clientID;
                    } catch (e) {
                        //Potential issue as per Navit
                        //console.log(e.message);
                    }
                }
            },
            ACCDCCalendar: {
                removeJQueryDatepickerUI: function () {
                    $(".datepickerDisplay").each(function () {
                        $(this).remove();
                    });
                    $(".ui-datepicker-trigger").each(function () {
                        if (this.nodeName.toUpperCase() == "IMG") {
                            $(this).remove();
                        }
                        else {
                            $(this).removeClass("ui-datepicker-trigger");
                        }
                    });
                    $('#ui-datepicker-div').remove();

                    $("*").each(function () {
                        $(this).off(".datepicker");
                        $(this).off(".dateTrigger.SKYSALES");
                    });
                },
                initBootstrap: function (options) {
                    var defaults = {
                        rootDiv: null,
                        selector: "a.accCalendar, button.accCalendar",
                        dpIDPair: ["date_picker_id_1", "date_picker_id_2"],
                        dpDisplayIDPair: ["date_picker_display_1", "date_picker_display_2"],
                        dpTriggerIDPair: ["link_for_date_picker_1_AccCalendar", "link_for_date_picker_2_AccCalendar"],
                        dpDayIDPair: [clientID + "_DropDownListMarketDay1", clientID + "_DropDownListMarketDay2"],
                        dpMonthYearIDPair: [clientID + "_DropDownListMarketMonth1", clientID + "_DropDownListMarketMonth2"]
                    };

                    options = $.extend({}, defaults, options);

                    $A.bootstrap = function (context) {
                        var context = context && context.nodeType === 1 ? context : document;
                        if ($A.setCalendar) {
                            var currDate = new Date();
                            var currYear = currDate.getFullYear();
                            var currMonth = currDate.getMonth();
                            var currDay = currDate.getDate();

                            $A.query(options.selector, (options.rootDiv != null ? options.rootDiv : context), function (i, o) {
                                if ($A.reg[o.id] && $A.reg[o.id].loaded) {
                                    var tdc = $A.reg[o.id];
                                    tdc.returnFocus = false;
                                    tdc.close();
                                    tdc.returnFocus = true;
                                }

                                //Set date format for CEBU below
                                var targ = $A.query('input[name="' + $A.getAttr(o, 'data-name') + '"]', context)[0];

                                //console.log(targ.nodeName);
                                $A.setCalendar(o.id, o, targ, false, function (ev, dc) {
                                    var vMonth = ((dc.range.current.month + 1).toString().length == 1 ? "0" + (dc.range.current.month + 1) : (dc.range.current.month + 1));
                                    var vDay = ((dc.range.current.mDay).toString().length == 1 ? "0" + dc.range.current.mDay : dc.range.current.mDay);
                                    var dateString = dc.range.current.year + '-' + vMonth + '-' + vDay;
                                    targ.value = dateString;
                                    $A.setAttr(targ, 'value', dateString);
                                    $(targ).parent().find("input[id^='date_picker_id']").val(dateString);
                                    dc.close();

                                    if (o.id == options.dpTriggerIDPair[0]) {
                                        var selectedYear = dc.range.current.year;
                                        var selectedMonth = dc.range.current.month;
                                        var selectedDay = dc.range.current.mDay;
                                        var minYear = currYear - 1;
                                        var maxYear = selectedYear;

                                        var disabledDates = {};
                                        for (var m = 0; m <= 11; m++) {
                                            disabledDates[m] = {};
                                            disabledDates[m]["disabled"] = {};
                                        }

                                        for (var y = minYear; y <= maxYear; y++) {
                                            var maxMonth = (y == maxYear ? selectedMonth : 11);
                                            for (var m = 0; m <= maxMonth; m++) {
                                                var disabledDays = [];
                                                var maxDays = (y == maxYear && m == selectedMonth ? selectedDay - 1 : 31);
                                                for (var d = 1; d <= maxDays; d++) {
                                                    disabledDays.push(d)
                                                }
                                                disabledDates[m].disabled[y] = disabledDays;
                                            }
                                        }

                                        //set
                                        $("#" + options.dpTriggerIDPair[1]).data("accdc.calendar", {
                                            "disabledDates": disabledDates,
                                            "selectedDate": new Date(selectedYear, selectedMonth, selectedDay),
                                            "flag": 0
                                        });

                                        //no more calendar popping-up automatically
                                        /*
                                        if (!$("#" + clientID + "_OpenJaw").is(":checked")) {
                                            //open datepicker for return date
                                            $("#" + options.dpTriggerIDPair[1] + " > span")[0].click() 
                                        }*/
                                    }

                                    if (o.id == options.dpTriggerIDPair[0]) {
                                        var displayDate = dc.range.current.year + '-' + vMonth + '-' + vDay;
                                        $("#" + options.dpIDPair[1]).val(displayDate);
                                        $("#" + options.dpDisplayIDPair[1]).val(displayDate);

                                        $("#" + options.dpDayIDPair[0]).val(vDay);
                                        $("#" + options.dpMonthYearIDPair[0]).val(dc.range.current.year + '-' + vMonth);

                                        var marketDay = $("#" + options.dpDayIDPair[0]);
                                        var marketDay2 = $("#" + options.dpDayIDPair[1]);
                                        //fallback if selected day value doesnt exist  
                                        if (marketDay.find("option[value='" + vDay + "']").length == 0) {
                                            marketDay.append($("<option value='" + vDay + "'/>").text(vDay))
                                        }
                                        if (marketDay2.find("option[value='" + vDay + "']").length == 0) {
                                            marketDay2.append($("<option value='" + vDay + "'/>").text(vDay))
                                        }
                                        marketDay.val(vDay);
                                        marketDay2.val(vDay);
                                        $("#" + options.dpMonthYearIDPair[1]).val(dc.range.current.year + '-' + vMonth);
                                    }
                                    else if (o.id == options.dpTriggerIDPair[1]) {
                                        var marketDay = $("#" + options.dpDayIDPair[1]);
                                        //fallback if selected day value doesnt exist  
                                        if (marketDay.find("option[value='" + vDay + "']").length == 0) {
                                            marketDay.append($("<option value='" + vDay + "'/>").text(vDay))
                                        }
                                        marketDay.val(vDay);
                                        $("#" + options.dpMonthYearIDPair[1]).val(dc.range.current.year + '-' + vMonth);
                                    }

                                });

                                if ($.inArray(o.id, options.dpTriggerIDPair) > -1) {
                                    $A.find(o.id, function (dc) {
                                        //disabled days - past
                                        var minYear = currYear - 1;
                                        var maxYear = currYear;

                                        for (var y = minYear; y <= maxYear; y++) {
                                            var maxMonth = (y == maxYear ? currMonth : 11);
                                            for (var m = 0; m <= maxMonth; m++) {
                                                var disabledDays = [];
                                                var maxDays = (y == maxYear && m == currMonth ? currDay - 1 : 31);
                                                for (var d = 1; d <= maxDays; d++) {
                                                    disabledDays.push(d)
                                                }
                                                dc.range[m].disabled[y] = disabledDays;
                                            }
                                        }

                                        //disabled days - future
                                        var maxDate = new Date();
                                        maxDate.setDate(maxDate.getDate() + 548);//548 days
                                        var minYear = maxDate.getFullYear();
                                        var maxYear = minYear + 1;

                                        for (var y = minYear; y <= maxYear; y++) {
                                            var minMonth = (y == minYear ? maxDate.getMonth() : 0);
                                            for (var m = minMonth; m <= 11; m++) {
                                                var disabledDays = [];
                                                var minDays = (y == minYear && m == maxDate.getMonth() ? maxDate.getDate() : 1);
                                                for (var d = minDays; d <= 31; d++) {
                                                    disabledDays.push(d)
                                                }
                                                dc.range[m].disabled[y] = disabledDays;
                                            }
                                        }
                                    });
                                }
                            });
                        }
                    };
                    $A.bootstrap();

                    (function ($) {
                        var func = $.datepicker.formatDate;
                        $.datepicker.formatDate = function () {
                            return func.apply(this, ["yy-mm-dd", arguments[arguments.length - 1]]);
                        }
                    })(jQuery);

                    for (var i in options.dpDisplayIDPair) {
                        var timestamp = Date.parse($("#" + options.dpDisplayIDPair[i]).val());
                        if (!isNaN(timestamp)) {
                            $("#" + options.dpDisplayIDPair[i]).val($.datepicker.formatDate(new Date(timestamp)));
                        }
                    }

                    
                }
            },
            initFlightSearchAccessibility: function () {
                var clientID = SKYSALES.Util.getSkySalesClientID();

                $("#" + clientID + "_ButtonSubmit").on("keyup", function (event) {
                    if (event.which == 13) {
                        $(".searchbutton").trigger('click');
                    }
                    return false;
                });
                //continue button for infant/child
                $("#" + clientID + "_DialogSubmitButton").on("keyup", function (e) {
                    if (e.which == 13) {
                        this.click();
                    }
                });

                $("#marketCityPair_2").attr("style", "padding-top: 50px;clear: both; display: none;");

            }
        });
    })();

    var clientID = SKYSALES.Util.getSkySalesClientID();

    //Display the active element and associated stack trace in the console
    //Run first then tab through active elements
    //The CSS verifies that the focus event occured
    (function () {
        var focus_css = '*:not(.main):focus { outline:3px dotted blue !important;} .calendar *:focus{outline:5px solid yellow !important;} .main:focus{outline:none}',
        head = document.getElementsByTagName('head')[0],
        style = document.createElement('style');
        style.type = 'text/css';
        if (style.styleSheet) {
            style.styleSheet.cssText = focus_css;
        } else {
            style.appendChild(document.createTextNode(focus_css));
        }
        head.appendChild(style);
        function showActiveElement() {
            //Potential issue as per Navit
            //console.log(window.document.activeElement);
            if (typeof console.trace !== 'undefined') {
                console.trace();
            }
        }
        var tags = document.getElementsByTagName('*');
        for (var i = 0; i < tags.length; i++) {
            tags[i].addEventListener('focus', showActiveElement, false);
        }
    })();



    //Use this function to add your own CSS to the test site
    (function () {
        var new_css, head, style;
        new_css = '/* Begin new CSS */';
        new_css += '.calender * { font-size:1rem }';
        new_css += '.year span {font-size:1rem;}';
        new_css += '.month span {font-size:1rem;}';
        new_css += 'input, select { margin:1px; }';
        new_css += '.newFare { margin-left: 40px;}';
        new_css += '#passengerContainer { clear: both; margin-left: auto;margin-right: auto;padding: 10px;width: 385px; font-size: 1.2em;}';

        new_css += '.passengerlist label { display:block; font-weight:bold; font-size:1.3rem; margin-bottom:5px; }';

        new_css += '/* END new CSS */';
        head = document.getElementsByTagName('head')[0];
        style = document.createElement('style');
        style.type = 'text/css';
        style.appendChild(document.createTextNode(new_css));
        head.appendChild(style);
    })();


    var pathName = window.location.pathname.toUpperCase();

    //111215 - So far all these fixes apply to appnijuan ER_Upgrade2.0 pages only
    //Fixes for Pattern IDs: 27552,27555,27559,27560
    if (pathName.match('/CUSTOMERPASSENGERLIST.ASPX')) {
        if ($('#popupDialogGuest').is(':visible')) {
            //Reset most of the CSS in the dialog. 
            //TODO: Add custom CSS to fix display issues
            //$('#popupDialogGuest *').each(function () {
            //    $(this).removeAttr('class');
            //});
            /*
            * Fix for Pattern ID: 27552
            * Made focusing the modal dialog possible and moved focus to it
            */
            $('#popupDialogGuest').attr('tabindex', -1).focus(); //27552
            /*
            * Fix for Pattern IDs: 27555, 27559
            * Created boundaries and a title for the modal dialog
            */
            $('#popupDialogGuest').prepend('<p aria-label="Beginning of add new guest dialog"></p>'); //27555
            $('#popupDialogGuest').prepend('<p style="font-weight:bold; font-size:1.5rem;">Add New Guest</p>'); //27559
            $('#popupDialogGuest').append('<p aria-label="End of add new guest dialog"></p>'); //27555
            //Put the cancel button in the tab order 
            $('#PassengerListView_btnSave + a.blue').attr('href', '#');
            /* Fix for Pattern ID: 27560
            * Removed the inaccessible jQuery UI dropdowns and cleared the styles on the SELECT elements that are actually used so that they are visible again
            */
            //$('select + span').each(function () {
            //    this.remove();
            //});
            //$('select').each(function () {
            //    this.style = '';
            //});
            $('#popupDialogGuest').css("min-height", "200px");
        }
    }
    //END if CustomerPassengerList.aspx
    //BEGIN ACCDC Calendar
    //Using ACCDC by Bryan Garaventa. See whatsock.com for more inforomation.

    if (pathName.match('SEARCH.ASPX') || pathName.match('SELECT.ASPX')) {
        SKYSALES.Util.initFlightSearchAccessibility();
        SKYSALES.Util.ACCDCCalendar.removeJQueryDatepickerUI();
        SKYSALES.Util.ACCDCCalendar.initBootstrap();
    }

    if (pathName.match('GUEST.ASPX')) {
        popUp = $("#PopUpReminder-Overlay");
        if (popUp.length > 0) {
            popUp.find("div>input[type='button']").focus().on("keyup", function (e) {
                e.stopPropagation();
                if (e.keyCode == 13 || e.keyCode == 27)
                    $(this).trigger("click");
            });
        }

        var options = {
            dpIDPair: ["date_picker_id_checkinDate", "date_picker_id_checkoutDate"],
            dpDisplayIDPair: ["date_picker_display_checkinDate", "date_picker_display_checkoutDate"],
            dpTriggerIDPair: ["link_for_date_picker_checkinDate_AccCalendar", "link_for_date_picker_checkoutDate_AccCalendar"],
            dpDayIDPair: [clientID + "_CheckinDay", clientID + "_CheckoutDay"],
            dpMonthYearIDPair: [clientID + "_CheckinMonthYear", clientID + "_CheckoutMonthYear"]
        };

        SKYSALES.Util.ACCDCCalendar.removeJQueryDatepickerUI();
        SKYSALES.Util.ACCDCCalendar.initBootstrap(options);
    }

    if (pathName.match('TRAVELVOUCHERFLIGHTSCHANGE.ASPX')) {
        SKYSALES.Util.ACCDCCalendar.removeJQueryDatepickerUI();
        SKYSALES.Util.ACCDCCalendar.initBootstrap();
    }

    //Fix for page title on SkySale site, pattern 27160
    document.title = $('#pageTitle>h1').text();
    //Fix for pattern 27224, giving the error icons null alts
    $('#errorSectionContent img').attr('alt', '');
    //Fix for pattern 27215
    if (pathName.match('SELECT.ASPX')) {
        $('.dayHeaderTodayImage > a').attr('aria-label', 'currently selected departure or return date');
    }

    //Fix for pattern 27213, flight details tooltips cannot be triggered with a keyboard
    //Made these span elements keyboard accessible using tabindex
    $('.flightInfoLink').each(function () { $(this).attr('tabindex', 0); });

    //BEGIN Fix for patterns 27157 and 27159
    //the seatpicker widget is not keyboard accessible
    //if (window.location.pathname.match('SeatAddOnsChange.aspx')) {
    if (pathName.match('SEAT')) {
        $('#unitMapPax').attr('role', 'application');
        $('#unitMapPax').attr('tabindex', 0);
        $('.row').attr('role', 'document');//Ensure that AT users can access the text content within the application using the arrow keys
        $('.aUnit').attr('tabindex', 0);
        $('.aUnit').attr('role', 'button');
        $('.aUnit').attr('aria-label', 'Pressing enter will select this seat');

        //Show the user instruction for using the seat picker with a keyboard
        var keyboardInstructions = '';
        keyboardInstructions += '<div id="keyboardInstructions">';
        keyboardInstructions += '<h2>Keyboard Instructions for Seat Picker</h2>';
        keyboardInstructions += '<ul>';
        keyboardInstructions += '<li>Use the UP and DOWN ARROW keys to select a seat input field</li>';
        keyboardInstructions += '<li>Use the RIGHT ARROW key to navigate from a seat input field to the seat picker</li>';
        keyboardInstructions += '<li>Use the LEFT and RIGHT ARROW keys to navigate within the seat picker and press ENTER to select a seat</li>';
        keyboardInstructions += '<li>Use the LEFT ARROW key to navigate from seat picker back to the seat input fields</li>';
        keyboardInstructions += '<li>Use the TAB key to navigate to and away from the seat picker</li>';
        keyboardInstructions += '</ul>';
        keyboardInstructions += '</div>';
        $('#seatappwindow').prepend(keyboardInstructions);

        //Style the keyboard instructions
        $('#keyboardInstructions').css('padding', '5px 5px 5px 20px');
        $('#keyboardInstructions').css('margin-bottom', '10px');
        $('#keyboardInstructions').css('background-color', 'yellow');
        $('#keyboardInstructions').css('font-size', '1rem');
        $('#keyboardInstructions').css('font-size', '1rem');

        //Make the keyboard instructions an alert
        $('#keyboardInstructions').attr('role', 'alert');

        //The keyboard usage instructions are hidden when the page loads
        //When the application receives focus the keyboard instructions are shown
        $('#keyboardInstructions').hide();
        $('#unitMapPax').focus(function () {
            $('#keyboardInstructions').show();
        });

        //If ENTER key is pressed move focus back to the active seat input element
        //also label the the seat as selected
        $('.aUnit').keypress(function (e) {
            e.stopPropagation();
            //If the seat is NOT selected trigger a click event
            var styleValue;
            styleValue = $(this).attr('style');
            if (!styleValue.match('JetAircraft_NS_Selected_0.gif')) {
                if (e.which === 13) {
                    $(this).trigger("click");//Ensure same functionality as a mouse user
                    var labelText = 'This seat is currently selected.';
                    $(this).attr('aria-label', labelText);
                    var seatID;
                    seatID = $(this).attr('id');
                    seatID = seatID.substr(seatID.length - 3).replace('_', '');
                    var seatInfo = $('.activeUnitInput').next().text();
                    seatInfo = seatInfo.replace(/PHP.*/, '');//clean out the the remove seat link

                    alert('You have selected seat ' + seatID + ' for passenger: ' + seatInfo);
                    $('.activeUnitInput')[0].focus();
                }
            }
        });

        $('.passengerlist input').attr('tabindex', -1);//Take the inputs out of the tab order for ARROW key navigation
        $('.passengerlist input').focus(function () { $(this).click(); });//To make keyboard usage the same as mouse usage

        //After the application received focus, pressing the DOWN ARROW key will put focus on the active seat input field
        $('#unitMapPax').on('keydown', function (e) {
            e.stopPropagation();
            if (e.which === 40) {
                $('.activeUnitInput')[0].focus();
            }
        });

        //If RIGHT ARROW key is pressed on one of seat input fields focus moves to the Remove Seat link
        $('.passengerlist input').each(function () {
            $(this).on('keydown', function (e) {
                e.stopPropagation();
                if (e.which === 39) {
                    $('.removeseatlink').focus();
                }
            });
        });

        //UP and DOWN ARROW keys should navigate through Departure and Return Seat Inputs
        //Filter out the hidden seat inputs
        var visibleInputIDs = [];
        $('.passengerlist input').each(function () {
            if (!$(this).attr('id').match('Hidden')) {
                visibleInputIDs.push($(this).attr('id'));
            }
        });

        //More verbose labelling for the seat input fields 
        //Get the Departure and Return header text to use in labels for the seat input fields
        var unitMapMarketHeaderText = [];
        $('.unitMapMarketHeader').each(function () {
            unitMapMarketHeaderText.push($(this).text());
        });

        for (var i = 0; i < visibleInputIDs.length; i++) {
            var labelText;
            var seatInputID;
            var currentValue;
            labelText = '';

            if (visibleInputIDs[i].match('EquipmentConfiguration_0')) {
                labelText += unitMapMarketHeaderText[0];
            }
            if (visibleInputIDs[i].match('EquipmentConfiguration_1')) {
                labelText += unitMapMarketHeaderText[1];
            }

            seatInputID = visibleInputIDs[i];
            currentValue = $('#' + seatInputID).val();
            labelText += ' The current value of this input field is ' + currentValue + ' ';
            labelText += $('#' + seatInputID).next().text();
            labelText = labelText.replace(/\/.*/, '');//clean out the the remove seat link
            labelText += 'Use the seat picker to pick your seat. Navigate to seat picker with the right arrow key';
            $('#' + seatInputID).attr('aria-label', labelText);
        }

        $('.passengerlist input').each(function () {

            $(this).on('keydown', function (e) {
                e.stopPropagation();
                var seatInputID = $(this).attr('id');
                var currentIndex = visibleInputIDs.indexOf(seatInputID);
                var nextSeatInputID = visibleInputIDs[currentIndex + 1];
                var previousSeatInputID = visibleInputIDs[currentIndex - 1];

                //If the down DOWN ARROW key is pressed move focus to the next seat input element
                if (e.which === 40) {
                    $('#' + nextSeatInputID).focus();
                }
                //If the UP ARROW key is pressed move focus to the previous seat input
                if (e.which === 38) {
                    $('#' + previousSeatInputID).focus();
                }
            });
        });

        $('.totalJourneyFee').blur(function () {
            $('.AssignandContinue>.button-arrow176x45').focus();
        });

        var duplicateIDs = [];//each .aUnit element is duplicated in the DOM by existing Cebu code 
        var uniqueIDs = [];//put the unique ids here
        var seatNums = [];
        var sortedSeatIDs = [];
        var seatIdPrefix;
        var seatID;
        var tabIndexVal;
        var nextSeatID;
        var previousSeatID;
        var currentIndex;
        var seatIdPrefix;

        //Departure and Return seat pickers need different prefixes
        var activeUnitInputID = $('.activeUnitInput').attr('id');
        if (activeUnitInputID.match('EquipmentConfiguration_0')) {
            seatIdPrefix = '0_Y_1_';
        }
        if (activeUnitInputID.match('EquipmentConfiguration_1')) {
            seatIdPrefix = '1_Y_1_';
        }


        //Populate and array with all the ids from the seats
        $('.aUnit').each(function () {
            duplicateIDs.push($(this).attr('id'));
        });

        $('.aUnit').attr('tabindex', -1);//Allow seats to receive focus but keep them out of the tab order

        //Remove the duplicate IDS
        $.each(duplicateIDs, function (i, el) {
            if ($.inArray(el, uniqueIDs) === -1) uniqueIDs.push(el);
        });

        for (var i = 0; i < uniqueIDs.length; i++) {
            var seatNum = uniqueIDs[i].substr(uniqueIDs[i].length - 3).replace('_', '0');//Make them sortable
            seatNums.push(seatNum);
        }

        //sort them now that the underscores have been replaced by 0's for the seat numbers < 10
        seatNums.sort();

        //Now that they are sorted create an array of .aUnit elements sorted by thier ID values
        for (i = 0; i < seatNums.length; i++) {
            seatNums[i] = seatNums[i].replace(/^0/, '');//now that they are sorted remove the leading 0's for the seat numbers < 10
            seatID = seatIdPrefix + seatNums[i];
            sortedSeatIDs.push(seatID);
        }

        //Label seats in the seat picker based on background image icons
        //Also alert the user when they try to selected an occupied or blocked seat
        $('.aUnit').each(function () {
            var styleValue = $(this).attr('style');
            var labelText;

            if (styleValue.match('JetAircraft_NS_Reserved_0.gif')) {
                $(this).unbind('keypress');
                labelText = 'This seat is occupied. Please select another seat.';
                $(this).attr('aria-label', labelText);
                //Alert the user that the seat is occupied if they press ENTER
                $(this).keypress(function (e) {
                    e.stopPropagation();
                    if (e.which === 13) {
                        alert(labelText);
                    }
                });
            }
            if (styleValue.match('JetAircraft_NS_FleetBlocked_0.gif')) {
                $(this).unbind('keypress');
                labelText = 'This seat is blocked. Please select another seat.'
                $(this).attr('aria-label', labelText);
                //Alert the user that the seat is blocked if they press ENTER
                $(this).keypress(function (e) {
                    e.stopPropagation();
                    if (e.which === 13) {
                        alert(labelText);
                    }
                });
            }
            if (styleValue.match('JetAircraft_NS_Selected_0.gif')) {
                labelText = 'This seat is currently selected.';
                $(this).attr('aria-label', labelText);
            }
            if (styleValue.match('JetAircraft_NS_Open_0.gif')) {
                labelText = 'This seat is open.';
                $(this).attr('aria-label', labelText);
            }
        });

        $('.aUnit').each(function () {
            $(this).on('keydown', function (e) {
                e.stopPropagation();
                var indexOffset = 1;
                seatID = $(this).attr('id');
                currentIndex = sortedSeatIDs.indexOf(seatID);
                nextSeatID = sortedSeatIDs[currentIndex + indexOffset];
                previousSeatID = sortedSeatIDs[currentIndex - indexOffset];

                //use the RIGHT ARROW key to navigate through seats in ascending order
                if (e.which === 39) {
                    $(this).trigger("mouseout");//Hide the old the tooltip now that you are moving away
                    $('#' + nextSeatID).focus();
                    $('#' + nextSeatID).trigger("mouseover");//Show the tooltip
                    //Now that focus has moved to the NEXT seat update the current index and the previous and next seat ids
                    currentIndex = sortedSeatIDs.indexOf(nextSeatID);
                    nextSeatID = sortedSeatIDs[currentIndex + indexOffset];
                    previousSeatID = sortedSeatIDs[currentIndex - indexOffset];
                }
                //use the LEFT ARROW key to navigate through seats in descending order
                if (e.which === 37) {
                    $(this).trigger("mouseout");//Hide the old the tooltip now that you are moving away
                    $('#' + previousSeatID).focus();
                    $('#' + previousSeatID).trigger("mouseover");//Show the tooltip
                    //Now that focus has moved to the PREVIOUS seat update the current index and the previous and next seat ids
                    currentIndex = sortedSeatIDs.indexOf(previousSeatID);
                    nextSeatID = sortedSeatIDs[currentIndex + indexOffset];
                    previousSeatID = sortedSeatIDs[currentIndex - indexOffset];
                }
            });
        });

        $('#JetAircraft').attr('tabindex', -1);//Needed to keep elements within the seatpicker application out of the tab order in order to enforce arrow key navigation only
        //If a SHIFT + TAB occurs on '#JetAircraft, focus the active seat input
        $('#JetAircraft').on('keydown', function (e) {
            e.stopPropagation();
            if (e.shiftKey && e.keyCode === 9) {
                $(this).blur(function () {
                    $('.activeUnitInput')[0].focus();
                });
            }
        });

        //Remove the remove seat link the from the tab order in order to access them with arrow keys only
        $('.removeseatlink').attr('tabindex', -1);

        //Take the next and previous station buttons out of the tab order so that you can only use the arrow keys within the application
        $('#prevstationbutton').attr('tabindex', -1);
        $('#nextstationbutton').attr('tabindex', -1);

        //Add missing labels to the next and previous station buttons
        $('#prevstationbutton').attr('aria-label', 'next station');
        $('#nextstationbutton').attr('aria-label', 'previous station')

        //PREVIOUS STATION BUTTON arrow key navigation
        $('#prevstationbutton').on('keydown', function (e) {
            e.stopPropagation();
            //If RIGHT ARROW key is pressed on the PREVIOUS STATION BUTTON, focus moves to the first next station button
            if (e.which === 39) {
                $('#nextstationbutton').focus();
            }
            //If LEFT ARROW key is pressed on the previous station button, focus moves to the remove seat link for the currently active seat input field
            if (e.which === 37) {
                $('.removeseatlink').focus();
            }
            if (e.which === 13) {
                $(this).trigger("click");//Ensure same functionality as a mouse user
            }
        });
        //NEXT STATION BUTTON arrow key navigation
        $('#nextstationbutton').on('keydown', function (e) {
            e.stopPropagation();
            //Departure and Return seat pickers need different prefixes
            var activeUnitInputID = $('.activeUnitInput').attr('id');
            if (activeUnitInputID.match('EquipmentConfiguration_0')) {
                var seatIdPrefix = '0';
            }
            if (activeUnitInputID.match('EquipmentConfiguration_1')) {
                var seatIdPrefix = '1';
            }

            //If RIGHT ARROW key is pressed on the NEXT STATION BUTTON, focus moves to the first seat input in the seat picker
            if (e.which === 39) {
                $('#' + seatIdPrefix + '_Y_1_1A').focus();
                $('#' + seatIdPrefix + '_Y_1_1A').trigger("mouseover");//Show the tooltip
            }
            //If LEFT ARROW key is pressed on the NEXT STATION BUTTON, focus moves to the previous station button
            if (e.which === 37) {
                $('#prevstationbutton').focus();
            }
            if (e.which === 13) {
                $(this).trigger("click");//Ensure same functionality as a mouse user
            }
        });

        //If RIGHT ARROW key is pressed on the Remove Seat link, focus moves to the previous station button
        $('.removeseatlink').each(function () {
            $(this).on('keydown', function (e) {
                e.stopPropagation();
                if (e.which === 39) {
                    $('#prevstationbutton').focus();
                }
            });
        });

        //If the LEFT ARROW key is pressed on the Remove Seat link, focus moves back to the active seat input element
        $('.removeseatlink').each(function () {
            $(this).on('keydown', function (e) {
                e.stopPropagation();
                if (e.which === 37) {
                    $('.activeUnitInput')[0].focus();
                }
            });
        });

        //Departure and Return seat pickers need different prefixes
        var activeUnitInputID = $('.activeUnitInput').attr('id');
        if (activeUnitInputID.match('EquipmentConfiguration_0')) {
            var seatIdPrefix = '0';
        }
        if (activeUnitInputID.match('EquipmentConfiguration_1')) {
            seatIdPrefix = '1';
        }

        //If the LEFT ARROW key is pressed on the first seat in picker, focus is moved to the next station button
        $('#' + seatIdPrefix + '_Y_1_1A').on('keydown', function (e) {
            e.stopPropagation();
            if (e.which === 37) {
                $('#nextstationbutton').focus();
            }
        });

        //Make sure the fees and user agreement are readable by AT's when using the seat picker application
        $('#unitMapSubmit').attr('role', 'document');
        $('#agreementInput').attr('role', 'document');

    }//END Fix for  patterns 27157 and 27159
    //END Fix for pattern 27157


    //Fix for airplane images pattern 27227
    $('.footnote img').attr('alt', '');

    //Another fix for airplane images pattern 27228
    $('table.flights .footnote>img').each(function () {
        if ($(this).attr('src').match('orange')) {
            $(this).attr('alt', 'Cebu Pacific Air');
        }
        if ($(this).attr('src').match('blue')) {
            $(this).attr('alt', 'Cebgo');
        }
        if ($(this).attr('src').match('green')) {
            $(this).attr('alt', 'Tigerair Singapore');
        }
    });


    //BEGIN fix for 27225
    //Removed jQuery UI Dropdowns, exposed the hidden underlying dropdowns that are natively accessible and labelled them.
    //Tested using T3UZPR, diana.chiong@cebupacificair.com, Manilla to Guam. It works.
    //Show the hidden dropddown
    $('#retrieveTable .hidden').css('display', 'block');
    $('#retrieveTable .hidden select').css({ 'visibility': 'visible', 'height': '100%' });
    //clean out the jQuery UI dropdowns
    $('#retrieveTable .hidden select + span').remove();
    $('#retrieveTable .hidden select + input').remove();
    $('#retrieveTable .dropDownDiv').remove();
    //Add date format information for screen readers
    $('#date_picker_display_1').before('<p aria-label="If your are typing in the date use the following format: YYYY-MM-DD. Otherwise use the calendar widget by activating the open calendar link below"></p>');
    $('#date_picker_display_2').before('<p aria-label="If your are typing in the date use the following format: YYYY-MM-DD. Otherwise use the calendar widget by activating the open calendar link below"></p>');
    $('.accCalendar').click(function () {
        $('.placeholder').remove();//These are causing a distorted look in IE9
        $('.selected').focus();//Move focus to the selected day when the calendar is opened
    });

});






