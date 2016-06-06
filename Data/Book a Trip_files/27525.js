//Developer:Chris Lane
//Fix for pattern_id=27525
//https://amp.ssbbartgroup.com/public/reporting/view_pattern.php?pattern_id=27525
//Dependencies: This fix is dependent on the fix for 27528, which should be executed before this fix
(function(){
    'use strict';
    $(document).ready(function () {
        if(window.location.pathname.match('CustomerCreditCardDetails.aspx')) {
           //When the ADD NEW CARD button is activated the fieldset that is currently the ADD NEW CARD dialog's parent container
           //is wrapped in divs that indicate the beginning and ending of the dialog via title attributes and aria-labels
           $('#AddCardLink').click(function(){
              var text = 'beginning of ADD NEW CARD dialog';
              $('#CreditCardInput').before('<div id="beginDialog"></div>');
              $('#beginDialog').attr('title',text);
              $('#beginDialog').attr('aria-label',text);
              text = 'ending of ADD NEW CARD dialog';
              $('#CreditCardInput').after('<div id="endDialog"></div>');
              $('#endDialog').attr('title',text);
              $('#endDialog').attr('aria-label',text);
           });
        }
    });
}())

