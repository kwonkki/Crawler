//Developer:Chris Lane
//Fix for pattern_id=27553
//https://amp.ssbbartgroup.com/public/reporting/view_pattern.php?pattern_id=27553
//Ensure keyboard focus returns properly from simulated dialogs(Focus is on the body tab after the ADD NEW GUEST dialog is closed)
//Dependencies: Thie fix is dependent on the fix for pattern_id=27558 
//Ensure that simulated dialogs can be closed via the keyboard(The ADD NEW GUEST dialog cannot be closed with the keyboard)
(function(){
    'use strict'
    $(document).ready(function () {
        if(window.location.pathname.match('CustomerPassengerList.aspx')) {
            $('#PassengerListView_btnSave + a').removeAttr('onclick');//To remove obtrusive call to SKYSALES.Util.removeUpdateDialog()
            //Combine the removal of the ADD NEW GUEST dialog with putting focus on the 
            //ADD NEW GUEST dialog button
            $('#PassengerListView_btnSave + a').click(function(){
                SKYSALES.Util.removeUpdateDialog();
                $('#PassengerListView_btnAdd').focus();
            });
        }
    });
}())

