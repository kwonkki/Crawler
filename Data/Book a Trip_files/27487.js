//Developer:Chris Lane
//Fix for pattern_id=27487
//https://amp.ssbbartgroup.com/public/reporting/view_pattern.php?pattern_id=27487
//Ensure keyboard focus returns properly from simulated dialogs. Focus moves to the beginning of the page when the dialog is closed
//URL:http://149.122.30.78/CustomerCreditCardDetails.aspx#f4db2d34cabe78ddd5a708df853e0053-DIV
(function(){
    'use strict';
    $(document).ready(function () {
        if(window.location.pathname.match('CustomerCreditCardDetails.aspx')) {
           //When the CANCEL button in the ADD NEW CARD dialog is activated, focus is moved to the ADD NEW CARD button
           $('#CancelCreditCard').click(function(){
               $('#AddCardLink').focus();
           });
            
        }
    });
}())

