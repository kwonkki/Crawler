//Developer:Chris Lane
//Fix for pattern_id=27526
//https://amp.ssbbartgroup.com/public/reporting/view_pattern.php?pattern_id=27526
//Ensure that keyboard focus remains within modal simulated dialogs... . (I was able to use the TAB key to focus elements outside of this dialog.)
(function(){
    'use strict';
    $(document).ready(function () {
        if(window.location.pathname.match('CustomerCreditCardDetails.aspx')) {
             //If a SHIFT + TAB occurs on the CARD TYPE field, focus the CANCEL button
             $('#ControlGroupCreditCardDetailsView_CreditCardInputCreditCardDetailsView_DropDownListPaymentMethodCode-button').on('keydown', function(e) {
                e.stopPropagation();
                if(e.shiftKey  &&  e.keyCode === 9) {
                    $(this).blur( function(){
                       $('#CancelCreditCard').focus();//put focus on the CANCEL button
                       $(this).unbind('blur');//So you can TAB forwards from this field later
                    });
                }
             });
            
             $('#CancelCreditCard').on('keydown', function(e) {
                e.stopPropagation();
                //If the TAB key is pressed on the CANCEL button, move focus to the CARD TYPE field
                if(e.keyCode === 9) {
                    $(this).unbind('blur');
                    $(this).blur( function(){
                       $('#ControlGroupCreditCardDetailsView_CreditCardInputCreditCardDetailsView_DropDownListPaymentMethodCode-button').focus();
                    });
                }
             });
        }
    });
}())

