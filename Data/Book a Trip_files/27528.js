//Developer:Chris Lane
//Fix for pattern_id=27528
//https://amp.ssbbartgroup.com/public/reporting/view_pattern.php?pattern_id=27528
//Ensure that when simulated dialogs are activated focus moves appropriately... (Focus does not move appropriately when the dialog is opened. It remains on the 'ADD NEW CARD BUTTON')	
(function(){
    'use strict';
    $(document).ready(function () {
        if(window.location.pathname.match('CustomerCreditCardDetails.aspx')) {
           //When the ADD NEW CARD button is activated the fieldset that is currently the ADD NEW CARD dialog's parent container
           //is wrapped in a container that can receive 
           //focus then move focus to that container.
           $('#AddCardLink').click(function(){
              $('#CreditCardInput').wrap('<div id="addNewCardDialog" tabindex=-1></div>');
              $('#addNewCardDialog').focus();
           });
        }
    });
}())

