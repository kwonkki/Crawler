//Developer:Chris Lane
//Fix for pattern_id=27524
//https://amp.ssbbartgroup.com/public/reporting/view_pattern.php?pattern_id=27524
//Ensure links that spawn simulated dialogs indicate the fact(The ADD NEW CARD link does not indicate that it spawns a menu)
(function(){
    'use strict';
    $(document).ready(function () {
        if(window.location.pathname.match('CustomerCreditCardDetails.aspx')) {
            //Add button semantics for ADD NEW CARD to be considered a button by assistive
            //technologies. Also added a title and label to make assisitive technology users
            //aware that this button opens the ADD NEW CARD dialog
            $('#AddCardLink').attr('role','button');
            $('#AddCardLink').attr('title','This button opens the ADD NEW CARD dialog');
            $('#AddCardLink').attr('aria-label','This button opens the ADD NEW CARD dialog');
        }
    });
}())

